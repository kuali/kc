package org.kuali.coeus.sys.impl.rest;

import java.util.Arrays;

import org.kuali.coeus.sys.framework.auth.AuthServiceUserLoginFilter;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.kuali.coeus.sys.framework.rest.RestServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component("authServiceRestUtilService")
public class AuthServiceRestUtilServiceImpl implements AuthServiceRestUtilService {

	private static final String AUTHORIZATION_PREFIX = "Bearer ";
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	private static final String KUALICO_VERSION_MEDIA_TYPE = "application/vnd.kuali.v%s+json";
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Override
	public HttpHeaders getAuthServiceStyleHttpHeadersForUser(RestServiceConstants.RestApiVersions version) {
		return getAuthServiceStyleHttpHeadersForToken(version,
				getAuthTokenValueForCurrentUser());
	}

	@Override
	public HttpHeaders getAuthServiceStyleHttpHeadersForToken(RestServiceConstants.RestApiVersions version, final String authTokenValueForCurrentUser) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType(String.format(KUALICO_VERSION_MEDIA_TYPE, version.getVersion()))));
		headers.set(AUTHORIZATION_HEADER_NAME, authTokenValueForCurrentUser.startsWith(AUTHORIZATION_PREFIX) 
				? authTokenValueForCurrentUser 
				: AUTHORIZATION_PREFIX + authTokenValueForCurrentUser);
		return headers;
	}
	
	protected String getAuthTokenValueForCurrentUser() {
		return AuthServiceUserLoginFilter.getAuthToken(globalVariableService.getUserSession());
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

}
