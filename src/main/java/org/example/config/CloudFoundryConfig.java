package org.example.config;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.example.model.CloudFoundryProperties;
import org.example.web.SampleRestLogCallback;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class CloudFoundryConfig {
	@Bean
	@ConfigurationProperties(prefix = "cf")
	public CloudFoundryProperties cloudFoundryProperties() {
		return new CloudFoundryProperties();
	}

	@Bean
	public CloudFoundryClient cloudFoundryClient(CloudFoundryProperties cloudFoundryProperties) {
		return getCloudFoundryClient(cloudFoundryProperties);
	}

	private CloudFoundryClient getCloudFoundryClient(CloudFoundryProperties cloudFoundryProperties) {
		CloudCredentials credentials = getCloudCredentials(cloudFoundryProperties);

		CloudFoundryClient client = new CloudFoundryClient(credentials,
				getTargetURL(cloudFoundryProperties.getTarget()),
				cloudFoundryProperties.getOrg(),
				cloudFoundryProperties.getSpace(), null, cloudFoundryProperties.isTrustSelfSignedCerts());

		if (cloudFoundryProperties.isVerbose()) {
			client.registerRestLogListener(new SampleRestLogCallback());
		}

		if (cloudFoundryProperties.getUsername() != null) {
			client.login();
		}

		return client;
	}

	private CloudCredentials getCloudCredentials(CloudFoundryProperties cloudFoundryProperties) {
		return new CloudCredentials(cloudFoundryProperties.getUsername(), cloudFoundryProperties.getPassword());
	}

	private URL getTargetURL(String target) {
		try {
			return getTargetURI(target).toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private URI getTargetURI(String target) {
		try {
			return new URI(target);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
