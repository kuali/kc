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
package org.kuali.coeus.sys.impl.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.sys.framework.auth.AuthServicePushService;
import org.kuali.coeus.sys.framework.auth.AuthServicePushStatus;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;

public class AuthServiceBulkPushAction extends KualiAction {
    private static final Log LOG = LogFactory.getLog(AuthServiceBulkPushAction.class);
    private static final String PUSH_MESSAGE_KEY = "info.user.bulk.push.complete";
    private static final String AUTH_SERVICE_BULK_PUSH = "AuthServiceBulkPush";

    private transient AuthServicePushService authServicePushService;
    private transient GlobalVariableService globalVariableService;

    public ActionForward pushAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (question == null) {
            return this.performQuestionWithoutInput(mapping, form, request, response, AUTH_SERVICE_BULK_PUSH, "Are you sure you want to push all users to the auth service", KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CANCEL, "");
        } else if (AUTH_SERVICE_BULK_PUSH.equals(question)) {
            final String buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if (ConfirmationQuestion.YES.equals(buttonClicked)) {
            	AuthServicePushStatus status = getAuthServicePushService().pushAllUsers();
                getGlobalVariableService().getMessageList().add(PUSH_MESSAGE_KEY, new String[] {new Integer(status.getNumberOfUsers()).toString(), 
                			new Integer(status.getNumberAdded()).toString(), new Integer(status.getNumberUpdated()).toString(), 
                			new Integer(status.getNumberSame()).toString(), new Integer(status.getNumberRemoved()).toString(),
                			new Integer(status.getErrors().size()).toString()});
            }
        }

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }

        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

	public AuthServicePushService getAuthServicePushService() {
		if (authServicePushService == null) {
			authServicePushService = KcServiceLocator.getService(AuthServicePushService.class);
		}
		return authServicePushService;
	}

	public void setAuthServicePushService(AuthServicePushService authServicePushService) {
		this.authServicePushService = authServicePushService;
	}
}
