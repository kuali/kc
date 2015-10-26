package org.kuali.coeus.sys.framework.rest;

import org.springframework.http.HttpHeaders;

public interface AuthServiceRestUtilService {

	/**
	 * Gets auth server style headers for the given version. Depends on there being an active usersession that was authenticated via AuthServiceFilter.
	 * @return Headers for connecting to auth service style rest endpoints. Will contain invalid/blank authentication token in the case that
	 * a valid user session does not exist.
	 */
	HttpHeaders getAuthServiceStyleHttpHeadersForUser(RestServiceConstants.RestApiVersions version);
	
	HttpHeaders getAuthServiceStyleHttpHeadersForToken(RestServiceConstants.RestApiVersions version, String authToken);
}
