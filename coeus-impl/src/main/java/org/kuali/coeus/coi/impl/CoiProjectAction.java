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
package org.kuali.coeus.coi.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class CoiProjectAction extends KualiAction {
    private static final Log LOG = LogFactory.getLog(CoiProjectAction.class);
    private static final String PUSH_MESSAGE_KEY = "info.project.push.started";
    private static final String PROJECT_PUSH = "ProjectPush";

    private transient Collection<ProjectRetrievalService> retrievalServices;
    private transient ProjectPublisher projectPublisher;
    private transient GlobalVariableService globalVariableService;

    public ActionForward publishAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (question == null) {
            return this.performQuestionWithoutInput(mapping, form, request, response, PROJECT_PUSH, "Are you sure you want to push all projects to COI", KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CANCEL, "");
        } else if (PROJECT_PUSH.equals(question)) {
            final String buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if (ConfirmationQuestion.YES.equals(buttonClicked)) {
                getRetrievalServices().stream()
                        .peek(s -> {
                            if (LOG.isInfoEnabled()) {
                                LOG.info("Retrieving Projects from: " + s.getClass().getName());
                            }
                        })
                        .flatMap(s -> s.retrieveProjects().stream())
                        .forEach(p -> getProjectPublisher().publishProject(p));

                getGlobalVariableService().getMessageList().add(PUSH_MESSAGE_KEY);
            }
        }

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    public ProjectPublisher getProjectPublisher() {
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    public Collection<ProjectRetrievalService> getRetrievalServices() {
        if (retrievalServices == null) {
            retrievalServices = KcServiceLocator.getServicesOfType(ProjectRetrievalService.class);
        }

        return retrievalServices;
    }

    public void setRetrievalServices(Collection<ProjectRetrievalService> retrievalServices) {
        this.retrievalServices = retrievalServices;
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
}
