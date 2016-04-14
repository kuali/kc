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
package org.kuali.coeus.sys.framework.auth;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.filter.UserLoginFilter;

public class AuthServiceUserLoginFilter extends UserLoginFilter {
	
	@Override
	public void updateUserSession(UserSession userSession, HttpServletRequest request) {
		if (userSession != null) {
			String authToken = (String) request.getSession().getAttribute(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR);
			AuthUser authUser = (AuthUser) request.getSession().getAttribute(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR);
			if (authToken != null) {
				userSession.addObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR, authToken);
			} else {
				userSession.removeObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR);
			}
			if (authUser != null) {
				userSession.addObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR, authUser);
			} else {
				userSession.removeObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR);
			}
	        GlobalVariables.setUserSession(userSession);
		}
	}
	
	public static String getAuthToken(UserSession userSession) {
		return (String) userSession.getObjectMap().get(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR);
	}
	
	public static AuthUser getAuthUserObject(UserSession userSession) {
		return (AuthUser) userSession.getObjectMap().get(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR);
	}
}
