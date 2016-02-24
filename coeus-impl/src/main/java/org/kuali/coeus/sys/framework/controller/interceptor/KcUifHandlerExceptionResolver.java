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
package org.kuali.coeus.sys.framework.controller.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.service.ViewService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.controller.UifHandlerExceptionResolver;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.IncidentReportForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A KC specific exception handler that delegates to a rice krad exception handler.
 */
//not exactly sure how to do this with autowiring since exception handlers are special
public class KcUifHandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver, Ordered {
	private static final Logger LOG = Logger.getLogger(KcUifHandlerExceptionResolver.class);
	HandlerExceptionResolver innerHandler;

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	UserSession requestSession = (UserSession) request.getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
		if (System.identityHashCode(GlobalVariables.getUserSession()) != System.identityHashCode(requestSession)) {
    		LOG.error("User Session Difference Detected. GlobalVariables session = " + GlobalVariables.getUserSession() + ", request session = " + requestSession);
    	}
    	GlobalVariables.setUserSession(requestSession);
    	
        //Avoids NPE in rice incident handler
        if (handler == null) {
            return innerHandler.resolveException(request, response, NullHandler.INSTANCE, ex);
        }

        return innerHandler.resolveException(request, response, handler, ex);
    }
    
    public void setInnerHandler(HandlerExceptionResolver innerHandler) {
        this.innerHandler = innerHandler;
    }

    private static final class NullHandler{
        private static final NullHandler INSTANCE = new NullHandler();
    }
}
