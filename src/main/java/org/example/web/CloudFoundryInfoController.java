package org.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CloudFoundryInfoController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CloudFoundryInfoController.class);

	@Value("${cf.target}")
	private String cloudfoundryTarget;

	public CloudFoundryInfoController() {
	}

	@MessageMapping("/request")
	@SendTo("/topic/messages")
	public Map<String, String> cloudFoundryInfo() throws Exception {
		return getCloudInfo();
	}

	private Map<String, String> getCloudInfo() {
		Map<String, String> results = new HashMap<>(2);
		results.put("PWS info", getInfo("https://api.run.pivotal.io/v2/info"));
		results.put("private info", getInfo(cloudfoundryTarget + "/v2/info"));
		return results;
	}

	private String getInfo(String url) {
		log.info("Getting info from {}", url);
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		log.info("Done getting info from {}", url);
		return response;
	}
}
