package org.example.web;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.CloudRoute;
import org.cloudfoundry.client.lib.domain.CloudService;
import org.example.model.CloudFoundryInfo;
import org.example.model.CloudFoundryProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CloudFoundryInfoController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CloudFoundryInfoController.class);

	private CloudFoundryProperties cloudFoundryProperties;
	private final CloudFoundryClient cloudFoundryClient;

	public CloudFoundryInfoController(CloudFoundryProperties cloudFoundryProperties, CloudFoundryClient cloudFoundryClient) {
		this.cloudFoundryProperties = cloudFoundryProperties;
		this.cloudFoundryClient = cloudFoundryClient;
	}

	@MessageMapping("/request")
	@SendTo("/topic/messages")
	public CloudFoundryInfo cloudFoundryInfo() throws Exception {
		return getCloudInfo(cloudFoundryClient);
	}

	private CloudFoundryInfo getCloudInfo(CloudFoundryClient client) {
		return new CloudFoundryInfo(getServices(client), getRoutes(client), getApps(client));
	}

	private List<CloudApplication> getApps(CloudFoundryClient client) {
		log.info("Getting applications");
		List<CloudApplication> applications = client.getApplications();
		log.info("Done getting applications");
		return applications;
	}

	private List<CloudRoute> getRoutes(CloudFoundryClient client) {
		log.info("Getting routes");
		List<CloudRoute> routes = client.getRoutes(cloudFoundryProperties.getDomain());
		log.info("Done getting routes");
		return routes;
	}

	private List<CloudService> getServices(CloudFoundryClient client) {
		log.info("Getting services");
		List<CloudService> services = client.getServices();
		log.info("Done getting services");
		return services;
	}
}
