package org.example.web;

import org.example.model.CloudFoundryInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CloudFoundryInfoController {

	private final CloudFoundryService cloudFoundryService;

	public CloudFoundryInfoController(CloudFoundryService cloudFoundryService) {
		this.cloudFoundryService = cloudFoundryService;
	}

	@MessageMapping("/request")
	@SendTo("/topic/messages")
	public CloudFoundryInfo cloudFoundryInfo() {
		return cloudFoundryService.getCloudFoundryInfo();
	}

}
