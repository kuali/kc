package org.kuali.coeus.sys.framework.rest;

public class RestServiceConstants {

	public static enum RestApiVersions {
		VER_1("1");
		private String version;
		private RestApiVersions(String version) {
			this.version = version;
		}
		public String getVersion() {
			return version;
		}
	}
	
	public final class Configuration {
		public static final String AUTH_USERS_URL = "auth.users.url";
		public static final String AUTH_BASE_URL = "auth.base.url";
	}
	
	private RestServiceConstants() {
		throw new RuntimeException("constants class. cannot be instantiated.");
	}
}
