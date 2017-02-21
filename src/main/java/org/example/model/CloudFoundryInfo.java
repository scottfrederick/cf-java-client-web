package org.example.model;

import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.CloudService;

import java.util.List;

public class CloudFoundryInfo {
	private List<CloudService> services;
	private List<CloudApplication> applications;

	public CloudFoundryInfo(List<CloudService> services, List<CloudApplication> applications) {
		this.services = services;
		this.applications = applications;
	}

	public List<CloudService> getServices() {
		return services;
	}

	public List<CloudApplication> getApplications() {
		return applications;
	}
}
