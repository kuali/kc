/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package edu.yale.its.tp.cas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.WebAuthenticationService;
import org.kuali.kra.infrastructure.KraServiceLocator;

import edu.yale.its.tp.cas.auth.AuthHandler;
import edu.yale.its.tp.cas.auth.PasswordHandler;
import edu.yale.its.tp.cas.auth.TrustHandler;
import edu.yale.its.tp.cas.ticket.GrantorCache;
import edu.yale.its.tp.cas.ticket.LoginTicketCache;
import edu.yale.its.tp.cas.ticket.ServiceTicket;
import edu.yale.its.tp.cas.ticket.ServiceTicketCache;
import edu.yale.its.tp.cas.ticket.TicketException;
import edu.yale.its.tp.cas.ticket.TicketGrantingTicket;

/**
 * Handles logins for the Central Authentication Service.
 */
public class Login extends HttpServlet {

    // *********************************************************************
    // KFSConstants

    // cookie IDs
    private static final String TGC_ID = "CASTGC";
    private static final String PRIVACY_ID = "CASPRIVACY";

    // parameters
    private static final String SERVICE = "service";
    private static final String RENEW = "renew";
    private static final String GATEWAY = "gateway";

    // *********************************************************************
    // Private state

    private GrantorCache tgcCache;
    private ServiceTicketCache stCache;
    private LoginTicketCache ltCache;
    private AuthHandler handler;
    private String loginForm, genericSuccess, serviceSuccess, confirmService, redirect;
    private ServletContext app;

    // *********************************************************************
    // Initialization

    public void init(ServletConfig config) throws ServletException {
        // retrieve the context and the caches
        app = config.getServletContext();
        tgcCache = (GrantorCache) app.getAttribute("tgcCache");
        stCache = (ServiceTicketCache) app.getAttribute("stCache");
        ltCache = (LoginTicketCache) app.getAttribute("ltCache");

        try {
            // create an instance of the right authentication handler
            String handlerName = app.getInitParameter("edu.yale.its.tp.cas.authHandler");
            if (handlerName == null)
                throw new ServletException("need edu.yale.its.tp.cas.authHandler");
            handler = (AuthHandler) Class.forName(handlerName).newInstance();
            if (!(handler instanceof TrustHandler) && !(handler instanceof PasswordHandler))
                throw new ServletException("unrecognized handler type: " + handlerName);
        }
        catch (InstantiationException ex) {
            throw new ServletException(ex.toString());
        }
        catch (ClassNotFoundException ex) {
            throw new ServletException(ex.toString());
        }
        catch (IllegalAccessException ex) {
            throw new ServletException(ex.toString());
        }

        // retrieve a relative URL for the login form
        loginForm = app.getInitParameter("edu.yale.its.tp.cas.loginForm");
        serviceSuccess = app.getInitParameter("edu.yale.its.tp.cas.serviceSuccess");
        genericSuccess = app.getInitParameter("edu.yale.its.tp.cas.genericSuccess");
        confirmService = app.getInitParameter("edu.yale.its.tp.cas.confirmService");
        redirect = app.getInitParameter("edu.yale.its.tp.cas.redirect");
        if (loginForm == null || genericSuccess == null || redirect == null || confirmService == null)
            throw new ServletException("need edu.yale.its.tp.cas.loginForm, " + "-genericSuccess, -serviceSuccess, -redirect, and -confirmService");
    }


    // *********************************************************************
    // Request handling

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // avoid caching (in the stupidly numerous ways we must)
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", -1);

        // check to see whether we've been sent a valid TGC
        Cookie[] cookies = request.getCookies();
        TicketGrantingTicket tgt = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(TGC_ID)) {
                    tgt = (TicketGrantingTicket) tgcCache.getTicket(cookies[i].getValue());
                    if (tgt == null)
                        continue;
                    // unless RENEW is set, let the user through to the service.
                    // otherwise, fall through and we'll be handled by authentication
                    // below. Note that tgt is still active.
                    if (request.getParameter(RENEW) == null) {
                        grantForService(request, response, tgt, request.getParameter(SERVICE), false);
                        return;
                    }
                }
            }
        }

        // if not, but if we're passed "gateway", then simply bounce back
        if (request.getParameter(SERVICE) != null && request.getParameter(GATEWAY) != null) {
            request.setAttribute("serviceId", request.getParameter(SERVICE));
            app.getRequestDispatcher(redirect).forward(request, response);
            return;
        }

        // if not, then see if our AuthHandler can help
        if (handler instanceof TrustHandler) {
            // try to get a trusted username by interpreting the request
            String trustedUsername = ((TrustHandler) handler).getUsername(request);
            if (trustedUsername != null) {
                // success: send a new TGC if we don't have a valid TGT from above
                if (tgt == null) {
                    tgt = sendTgc(trustedUsername, request, response);
                }
                else if (!tgt.getUsername().equals(trustedUsername)) {
                    // we're coming into a renew=true as a different user...
                    // expire the old tgt
                    tgt.expire();
                    // and send a new one
                    tgt = sendTgc(trustedUsername, request, response);
                }
                sendPrivacyCookie(request, response);
                grantForService(request, response, tgt, request.getParameter(SERVICE), true);
                return;
            }
            else {
                // failure: nothing else to be done
                throw new ServletException("unable to authenticate user");
            }
        }
        else if (handler instanceof PasswordHandler && request.getParameter("username") != null && request.getParameter("lt") != null) {
            // do we have a valid login ticket?
            if (ltCache.getTicket(request.getParameter("lt")) != null) {
                // do we have a valid username and password?
                if (((PasswordHandler) handler).authenticate(request, request.getParameter("username"), request.getParameter("password"))) {
                    // success: send a new TGC if we don't have a valid TGT from above
                    if (tgt == null) {
                        tgt = sendTgc(request.getParameter("username"), request, response);
                    }
                    else if (!tgt.getUsername().equals(request.getParameter("username"))) {
                        // we're coming into a renew=true as a different user...
                        // expire the old tgt
                        tgt.expire();
                        // and send a new one
                        tgt = sendTgc(request.getParameter("username"), request, response);
                    }
                    sendPrivacyCookie(request, response);
                    grantForService(request, response, tgt, request.getParameter(SERVICE), true);
                    return;
                }
                else {
                    // failure: record failed password authentication
                    request.setAttribute("edu.yale.its.tp.cas.badUsernameOrPassword", "");
                }
            }
            else {
                // failure: record invalid login ticket
                request.setAttribute("edu.yale.its.tp.cas.badLoginTicket", "");
                // horrible way of logging, I know
                System.out.println("Login.java: " + new Date() + ": invalid login ticket from " + request.getRemoteAddr());
            }
        }

        // record the service in the request
        request.setAttribute("edu.yale.its.tp.cas.service", request.getParameter(SERVICE));

        // no success yet, so generate a login ticket and forward to the
        // login form
        try {
            String lt = ltCache.addTicket();
            request.setAttribute("edu.yale.its.tp.cas.lt", lt);
        }
        catch (TicketException ex) {
            throw new ServletException(ex);
        }
        // check if the password field should be shown and set a flag to be used by the JSP
        request.setAttribute( "showPasswordField", ((KualiConfigurationService) KraServiceLocator.getService("kualiConfigurationService")).isProductionEnvironment() || ((WebAuthenticationService) KraServiceLocator.getService("webAuthenticationService")).isValidatePassword());
        app.getRequestDispatcher(loginForm).forward(request, response);
    }

    /**
     * Grants a service ticket for the given service, using the given TicketGrantingTicket. If no 'service' is specified, simply
     * forward to message conveying generic success.
     */
    private void grantForService(HttpServletRequest request, HttpServletResponse response, TicketGrantingTicket t, String serviceId, boolean first) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            if (serviceId != null) {
                ServiceTicket st = new ServiceTicket(t, serviceId, first);
                String token = stCache.addTicket(st);
                request.setAttribute("serviceId", serviceId);
                request.setAttribute("token", token);
                if (!first) {
                    if (privacyRequested(request)) {
                        app.getRequestDispatcher(confirmService).forward(request, response);
                    }
                    else {
                        request.setAttribute("first", "false");
                        app.getRequestDispatcher(serviceSuccess).forward(request, response);
                    }
                }
                else {
                    request.setAttribute("first", "true");
                    app.getRequestDispatcher(serviceSuccess).forward(request, response);
                }
            }
            else
                app.getRequestDispatcher(genericSuccess).forward(request, response);
        }
        catch (TicketException ex) {
            throw new ServletException(ex.toString());
        }
    }

    /**
     * Creates, sends (to the given ServletResponse), and returns a TicketGrantingTicket for the given username.
     */
    private TicketGrantingTicket sendTgc(String username, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            TicketGrantingTicket t = new TicketGrantingTicket(username);
            String token = tgcCache.addTicket(t);
            Cookie tgc = new Cookie(TGC_ID, token);
            if (request.getRequestURI().startsWith("https") || ((KualiConfigurationService) KraServiceLocator.getService("kualiConfigurationService")).isProductionEnvironment() ) {
                tgc.setSecure(true);
            }
            tgc.setMaxAge(-1);
            tgc.setPath(request.getContextPath());
            response.addCookie(tgc);
            return t;
        }
        catch (TicketException ex) {
            throw new ServletException(ex.toString());
        }
    }

    /**
     * If the user has so requested, creates and sends (to the given ServletResponse) a cookie recording the fact that the user
     * wants to be warned before using CAS's single-sign-on capabilities.
     */
    private void sendPrivacyCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (request.getParameter("warn") != null) {
            // send the cookie if it's requested
            Cookie privacy = new Cookie(PRIVACY_ID, "enabled");
            privacy.setSecure(true);
            privacy.setMaxAge(-1);
            privacy.setPath(request.getContextPath());
            response.addCookie(privacy);
        }
        else if (privacyRequested(request)) {
            // delete the cookie if it's there but *not* requested this time
            Cookie privacy = new Cookie(PRIVACY_ID, "disabled");
            privacy.setSecure(true);
            privacy.setMaxAge(0);
            privacy.setPath(request.getContextPath());
            response.addCookie(privacy);
        }
    }

    /**
     * Returns true if privacy has been requested, false otherwise.
     */
    private boolean privacyRequested(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++)
                if (cookies[i].getName().equals(PRIVACY_ID) && cookies[i].getValue().equals("enabled"))
                    return true;
        }
        return false;
    }

}
