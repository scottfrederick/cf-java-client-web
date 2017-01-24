package org.example.web;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.example.model.CloudFoundryInfo;
import org.example.model.CloudFoundryProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CloudFoundryInfoController {

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
		return new CloudFoundryInfo(client.getServices(),
				client.getRoutes(cloudFoundryProperties.getDomain()),
				client.getApplications());
	}
}
