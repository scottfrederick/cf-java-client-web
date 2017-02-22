package org.example.web;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.CloudInfo;
import org.cloudfoundry.client.lib.domain.CloudService;
import org.example.model.CloudFoundryInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CloudFoundryInfoController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CloudFoundryInfoController.class);

	private final CloudFoundryClient cloudFoundryClient;

	public CloudFoundryInfoController(CloudFoundryClient cloudFoundryClient) {
		this.cloudFoundryClient = cloudFoundryClient;
	}

	@MessageMapping("/request")
	@SendTo("/topic/messages")
	public CloudFoundryInfo cloudFoundryInfo() throws Exception {
		return getCloudInfo(cloudFoundryClient);
	}

	private CloudFoundryInfo getCloudInfo(CloudFoundryClient client) {
		return new CloudFoundryInfo(getInfo(client), getServices(client), getApps(client));
	}

	private CloudInfo getInfo(CloudFoundryClient client) {
		log("Getting info");
		CloudInfo cloudInfo = client.getCloudInfo();
		log("Done getting info");
		return cloudInfo;
	}

	private List<CloudApplication> getApps(CloudFoundryClient client) {
		log("Getting applications");
		List<CloudApplication> applications = client.getApplications();
		log("Done getting applications");
		return applications;
	}

	private List<CloudService> getServices(CloudFoundryClient client) {
		log("Getting services");
		List<CloudService> services = client.getServices();
		log("Done getting services");
		return services;
	}

	private void log(String msg) {
		log.info(msg + " on thread {}", Thread.currentThread().getId());
	}
}
