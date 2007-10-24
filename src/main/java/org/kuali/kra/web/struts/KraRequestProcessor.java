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
package org.kuali.kra.web.struts;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kuali.RiceConstants;
import org.kuali.core.UserSession;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.service.WebAuthenticationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.Timer;
import org.kuali.core.web.struts.action.KualiRequestProcessor;
import edu.iu.uis.eden.user.WorkflowUser;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.Core;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.exception.WorkflowException;
import edu.iu.uis.eden.user.UserId;
import edu.iu.uis.eden.user.UserService;

public class KraRequestProcessor extends KualiRequestProcessor {
    private static Logger LOG = Logger.getLogger(KraRequestProcessor.class);
    
    /** TODO Override KNS webAuthenticationService in Rice, instead of overriding request processor
     * and doing all the below hackery to maintain a user and workflow session */
    
    /**
     * override of the pre process for all struts requests which will ensure that we have the appropriate state for user
     * sessions for all of our requests, also populating the GlobalVariables class with our UserSession for convenience
     * to the non web layer based classes and implementations
     */
    @Override
    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
    Timer t0 = new Timer("KualiRequestProcessor.processPreprocess");
    
    String id = null;
    WorkflowUser workflowUser = null;
    try {
        UserSession userSession = null;
        edu.iu.uis.eden.web.session.UserSession kewUserSession = null;
        WebAuthenticationService webAuthenticationService = (WebAuthenticationService) KraServiceLocator.getService("webAuthenticationService");
        if (!isUserSessionEstablished(request)) {
            id = webAuthenticationService.getNetworkId(request);
            UserId userId = webAuthenticationService.getUserId(request);
            userSession = new UserSession(id);
            workflowUser = ((UserService) KEWServiceLocator.getUserService()).getWorkflowUser(userId);
            kewUserSession = new edu.iu.uis.eden.web.session.UserSession(workflowUser);
        } else {
            userSession = (UserSession) request.getSession().getAttribute(org.kuali.RiceConstants.USER_SESSION_KEY);
            kewUserSession = (edu.iu.uis.eden.web.session.UserSession) request.getSession().getAttribute(EdenConstants.USER_SESSION_KEY);
        }
        if (request.getParameter(EdenConstants.IDOCANDLER_BACKDOOR_ID_PARAMETER) != null
                && request.getParameter(EdenConstants.IDOCANDLER_BACKDOOR_ID_PARAMETER).trim().length() > 0) {
            if (Core.getCurrentContextConfig().getProperty("rice.user") != null
                    && !new Boolean(Core.getCurrentContextConfig().getProperty("rice.user"))) {
                userSession = (UserSession) request.getSession().getAttribute(RiceConstants.USER_SESSION_KEY);
                kewUserSession = (edu.iu.uis.eden.web.session.UserSession) request.getSession().getAttribute(EdenConstants.USER_SESSION_KEY);
            }
            userSession.setBackdoorUser(request.getParameter(EdenConstants.IDOCANDLER_BACKDOOR_ID_PARAMETER));
            kewUserSession.setBackdoorId(request.getParameter(EdenConstants.IDOCANDLER_BACKDOOR_ID_PARAMETER));
        }
    
        if (!userSession.getUniversalUser().isActiveForAnyModule()) {
        throw new RuntimeException(
            "You cannot log in, because you are not an active Kuali user.\nPlease ask someone to activate your account, if you need to use Kuali Financial Systems.\nThe user id provided was: "
                + userSession.getUniversalUser().getPersonUserIdentifier() + ".\n");
        }
        request.getSession().setAttribute(org.kuali.RiceConstants.USER_SESSION_KEY, userSession);
        request.getSession().setAttribute(EdenConstants.USER_SESSION_KEY, kewUserSession);
        
        GlobalVariables.setUserSession(userSession);
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setMessageList(new ArrayList());
        GlobalVariables.setAuditErrorMap(new HashMap());
    } catch (UserNotFoundException e) {
        LOG.error("Caught a User Not found exception: " + id, e);
        throw new RuntimeException("Invalid User: " + id, e);
    } catch (WorkflowException e) {
        LOG.error("Caught a ResourceUnavailableException: " + id, e);
        throw new RuntimeException("ResourceUnavailableException: ", e);
    }
        t0.log();
        return true;
    }
    
    /**
     * Checks if the user who made the request has a UserSession established
     * 
     * @param request
     *                the HTTPServletRequest object passed in
     * @return true if the user session has been established, false otherwise
     */
    private boolean isUserSessionEstablished(HttpServletRequest request) {
        Timer t0 = new Timer("KualiRequestProcessor.isUserSessionEstablished");
        boolean result = (request.getSession().getAttribute(org.kuali.RiceConstants.USER_SESSION_KEY) != null);
        t0.log();
        return result;
    }
}
