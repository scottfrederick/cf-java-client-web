package org.example.web;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudInfo;
import org.example.model.CloudFoundryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("java-client")
public class JavaClientCloudFoundryService implements CloudFoundryService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JavaClientCloudFoundryService.class);

	private CloudFoundryClient client;

	@Autowired
	public JavaClientCloudFoundryService(CloudFoundryClient client) {
		this.client = client;
	}

	public CloudFoundryInfo getCloudFoundryInfo() {
		return new CloudFoundryInfo(getInfo());
	}

	private CloudInfo getInfo() {
		log("Getting info");
		CloudInfo cloudInfo = client.getCloudInfo();
		log("Done getting info");
		return cloudInfo;
	}

	private void log(String msg) {
		log.info(msg + " on thread {}", Thread.currentThread().getId());
	}
}
