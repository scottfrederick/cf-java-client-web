package org.example.config;

import org.example.model.CloudFoundryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	@ConfigurationProperties(prefix = "cf")
	public CloudFoundryProperties cloudFoundryProperties() {
		return new CloudFoundryProperties();
	}
}
