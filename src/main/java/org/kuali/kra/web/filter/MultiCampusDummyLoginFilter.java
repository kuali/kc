/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.location.api.campus.CampusContract;
import org.kuali.rice.location.impl.campus.CampusBo;

/**
 * A login filter which forwards to a login page that allows for the desired authentication ID and campus code to be entered without the need for a password.
 * Adapted from {@link org.kuali.rice.kew.web.DummyLoginFilter}.
 */
public class MultiCampusDummyLoginFilter implements Filter {
    
    private String loginPath;
    private boolean showPassword = false;
    private List<CampusContract> campuses;
    public void init(FilterConfig config) throws ServletException {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
        loginPath = config.getInitParameter("loginPath");
        showPassword = new Boolean(config.getInitParameter("showPassword"));
        campuses = new ArrayList<CampusContract>(businessObjectService.findAll(CampusBo.class));
        if (loginPath == null) {
            loginPath = "/WEB-INF/jsp/multicampus_dummy_login.jsp";
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsreq = (HttpServletRequest) request;
            UserSession session = null;
            if (hsreq.getSession().getAttribute(KRADConstants.USER_SESSION_KEY) != null) {
                session = (UserSession) hsreq.getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
            }
            if (session == null) {
                IdentityService auth = KimApiServiceLocator.getIdentityService();
                MultiCampusIdentityService multiCampusAuth = KraServiceLocator.getService(MultiCampusIdentityService.class);
                request.setAttribute("showPasswordField", showPassword);
                request.setAttribute("campuses", campuses);
                final String user = request.getParameter("__login_user");
                final String password = request.getParameter("__login_pw");
                final String campusCode = request.getParameter("__login_campusCode");
                
                final String multiCampusUser = multiCampusAuth.getMultiCampusPrincipalName(user, campusCode);
                if (user != null) {
                    // Very simple password checking. Nothing hashed or encrypted. This is strictly for demonstration purposes only.
                    final PrincipalContract principal = showPassword ? auth.getPrincipalByPrincipalNameAndPassword(multiCampusUser, password) 
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
                        hsreq.getSession().setAttribute(Constants.USER_CAMPUS_CODE_KEY, campusCode);
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

    public void destroy() {
    }
}
