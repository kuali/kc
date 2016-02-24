/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.impl.rest;


import org.kuali.coeus.sys.framework.auth.AuthServiceUserLoginFilter;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component("authServiceRestUtilService")
public class AuthServiceRestUtilServiceImpl implements AuthServiceRestUtilService {

	private static final String AUTHORIZATION_PREFIX = "Bearer ";
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Override
	public HttpHeaders getAuthServiceStyleHttpHeadersForUser() {
		return getAuthServiceStyleHttpHeadersForToken(getAuthTokenValueForCurrentUser());
	}

	@Override
	public HttpHeaders getAuthServiceStyleHttpHeadersForToken(final String authTokenValueForCurrentUser) {
		HttpHeaders headers = new HttpHeaders();
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
