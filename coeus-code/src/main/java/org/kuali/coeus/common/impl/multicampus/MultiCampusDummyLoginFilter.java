/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.impl.multicampus;

import org.kuali.coeus.common.framework.multicampus.MultiCampusConstants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusIdentityService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.location.api.campus.Campus;
import org.kuali.rice.location.api.campus.CampusService;
import org.kuali.rice.location.api.services.LocationApiServiceLocator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A login filter which forwards to a login page that allows for the desired authentication ID and campus code to be entered without the need for a password.
 * Adapted from {@link org.kuali.rice.krad.web.filter.DummyLoginFilter}.
 */
public class MultiCampusDummyLoginFilter implements Filter {
    
    private String loginPath;
    private boolean showPassword = false;
    private List<Campus> campuses;

    @Override
    public void init(FilterConfig config) throws ServletException {
        loginPath = config.getInitParameter("loginPath");
        showPassword = Boolean.valueOf(config.getInitParameter("showPassword"));
        campuses = new ArrayList<Campus>(getCampusService().findAllCampuses());
        if (loginPath == null) {
            loginPath = "/WEB-INF/jsp/multicampus_dummy_login.jsp";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsreq = (HttpServletRequest) request;
            UserSession session = null;
            if (hsreq.getSession().getAttribute(KRADConstants.USER_SESSION_KEY) != null) {
                session = (UserSession) hsreq.getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
            }
            if (session == null) {
                IdentityService auth = getIdentityService();
                request.setAttribute("showPasswordField", showPassword);
                request.setAttribute("campuses", campuses);
                final String user = request.getParameter("__login_user");
                final String password = request.getParameter("__login_pw");
                final String campusCode = request.getParameter("__login_campusCode");
                
                final String multiCampusUser = getMultiCampusIdentityService().getMultiCampusPrincipalName(user, campusCode);
                if (user != null) {
                    // Very simple password checking. Nothing hashed or encrypted. This is strictly for demonstration purposes only.
                    final Principal principal = showPassword ? auth.getPrincipalByPrincipalNameAndPassword(multiCampusUser, password)
                                                                : auth.getPrincipalByPrincipalName(multiCampusUser);
                    if (principal == null) {
                        handleInvalidLogin(request, response);  
                        return;
                    } else {
                        // wrap the request with the remote user
                        // UserLoginFilter and WebAuthenticationService will create the session
                        request = new HttpServletRequestWrapper(hsreq) {
                            public String getRemoteUser() {
                                return multiCampusUser;
                            } 
                        };
                        hsreq.getSession().setAttribute(MultiCampusConstants.USER_CAMPUS_CODE_KEY, campusCode);
                    }
                } else {
                    // no session has been established and this is not a login form submission, so forward to login page
                    request.getRequestDispatcher(loginPath).forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
    
    /**
     * Handles and invalid login attempt.
     *  
     * @param request the incoming request
     * @param response the outgoing response
     * @throws ServletException if unable to handle the invalid login
     * @throws IOException if unable to handle the invalid login
     */
    private void handleInvalidLogin(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.setAttribute("invalidAuth", Boolean.TRUE);
        request.getRequestDispatcher(loginPath).forward(request, response);
    }

    @Override
    public void destroy() {
    }

    protected CampusService getCampusService() {
        return LocationApiServiceLocator.getCampusService();
    }

    protected MultiCampusIdentityService getMultiCampusIdentityService() {
        return KcServiceLocator.getService(MultiCampusIdentityService.class);
    }

    protected IdentityService getIdentityService() {
        return KimApiServiceLocator.getIdentityService();
    }
}
