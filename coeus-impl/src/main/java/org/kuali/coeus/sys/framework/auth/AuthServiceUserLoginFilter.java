package org.kuali.coeus.sys.framework.auth;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.filter.UserLoginFilter;

public class AuthServiceUserLoginFilter extends UserLoginFilter {
	
	@Override
	public void updateUserSession(UserSession userSession, HttpServletRequest request) {
		if (userSession != null) {
			userSession.addObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR, 
					request.getSession().getAttribute(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR));
			userSession.addObject(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR,
					request.getSession().getAttribute(AuthServiceFilter.AUTH_SERVICE_FILTER_AUTHED_USER_ATTR));
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
