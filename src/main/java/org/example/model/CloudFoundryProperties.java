package org.example.model;

public class CloudFoundryProperties {
	private String target;
	private String username;
	private String password;
	private String org;
	private String space;
	private String domain;
	private boolean trustSelfSignedCerts;
	private boolean verbose;
	private boolean debug;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean isTrustSelfSignedCerts() {
		return trustSelfSignedCerts;
	}

	public void setTrustSelfSignedCerts(boolean trustSelfSignedCerts) {
		this.trustSelfSignedCerts = trustSelfSignedCerts;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
