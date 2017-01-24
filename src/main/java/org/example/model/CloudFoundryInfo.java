package org.example.model;

import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.CloudRoute;
import org.cloudfoundry.client.lib.domain.CloudService;

import java.util.List;

public class CloudFoundryInfo {
	private List<CloudService> services;
	private List<CloudRoute> routes;
	private List<CloudApplication> applications;

	public CloudFoundryInfo(List<CloudService> services, List<CloudRoute> routes, List<CloudApplication> applications) {
		this.services = services;
		this.routes = routes;
		this.applications = applications;
	}

	public List<CloudService> getServices() {
		return services;
	}

	public List<CloudRoute> getRoutes() {
		return routes;
	}

	public List<CloudApplication> getApplications() {
		return applications;
	}
}
