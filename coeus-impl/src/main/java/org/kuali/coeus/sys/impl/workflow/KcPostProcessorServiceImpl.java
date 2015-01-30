/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.impl.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.*;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * A {@link PostProcessorService} to record the actual user performing an action on a workflow status change.
 */
@Component("kcPostProcessorService")
@Transactional
public class KcPostProcessorServiceImpl implements PostProcessorService {

    public static final String LAST_ACTION_PRINCIPAL_ID = "lastActionPrincipalId";

    private static final Log LOG = LogFactory.getLog(KcPostProcessorServiceImpl.class);

    @Autowired
    @Qualifier("kewWorkflowDocumentService")
    private WorkflowDocumentService workflowDocumentService;

    @Autowired
    @Qualifier("postProcessorService")
    private PostProcessorService postProcessorService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public ProcessDocReport doRouteStatusChange(final DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), new Callable<ProcessDocReport>() {
            @Override
            public ProcessDocReport call() throws Exception {
                establishLastActionPrincipalId(statusChangeEvent.getDocumentId());
                return postProcessorService.doRouteStatusChange(statusChangeEvent);
            }
        });
    }

    @Override
    public ProcessDocReport doRouteLevelChange(final DocumentRouteLevelChange levelChangeEvent) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), new Callable<ProcessDocReport>() {
            @Override
            public ProcessDocReport call() throws Exception {
                establishLastActionPrincipalId(levelChangeEvent.getDocumentId());
                return postProcessorService.doRouteLevelChange(levelChangeEvent);
            }
        });
    }

    @Override
    public ProcessDocReport doDeleteRouteHeader(DeleteEvent event) throws Exception {
        return postProcessorService.doDeleteRouteHeader(event);
    }

    @Override
    public ProcessDocReport doActionTaken(final ActionTakenEvent event) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), new Callable<ProcessDocReport>() {
            @Override
            public ProcessDocReport call() throws Exception {
                establishLastActionPrincipalId(event.getDocumentId());
                return postProcessorService.doActionTaken(event);
            }
        });
    }

    @Override
    public ProcessDocReport afterActionTaken(ActionType actionPerformed, ActionTakenEvent event) throws Exception {
        return postProcessorService.afterActionTaken(actionPerformed, event);
    }

    @Override
    public ProcessDocReport beforeProcess(BeforeProcessEvent processEvent) throws Exception {
        return postProcessorService.beforeProcess(processEvent);
    }

    @Override
    public ProcessDocReport afterProcess(AfterProcessEvent processEvent) throws Exception {
        return postProcessorService.afterProcess(processEvent);
    }

    @Override
    public List<String> getDocumentIdsToLock(DocumentLockingEvent lockingEvent) throws Exception {
        return postProcessorService.getDocumentIdsToLock(lockingEvent);
    }

    /**
     * This finds the last workflow action taken on the Document that corresponds to the passed in event.  It then finds
     * the principal who triggered that event and places the principal id in a {@link GlobalVariableService#getUserSession()}.
     * Once in the UserSession, the principal Id can be used with in any workflow callbacks.
     *
     * @param routeHeaderId the route header id (document id)
     */
    protected void establishLastActionPrincipalId(final String routeHeaderId) {

        final ActionTaken lastActionTaken = findLastActionTaken(routeHeaderId);

        if (lastActionTaken != null) {
            globalVariableService.getUserSession().addObject(LAST_ACTION_PRINCIPAL_ID, lastActionTaken.getPrincipalId());
        }
    }

    /**
     * Finds the last action taken on a Document.
     * @param routeHeaderId the route header id (document id)
     * @return the last action taken or null if non could be found.
     */
    protected ActionTaken findLastActionTaken(String routeHeaderId) {
        final List<ActionTaken> actionsTaken = workflowDocumentService.getActionsTaken(routeHeaderId);

        if (actionsTaken != null) {
            ActionTaken lastActionTaken = null;
            for (ActionTaken actionTaken : actionsTaken) {
                if (lastActionTaken == null || actionTaken.getActionDate().toDate().after(lastActionTaken.getActionDate().toDate())) {
                    lastActionTaken = actionTaken;
                }
            }
            return lastActionTaken;
        }
        return null;
    }

    /* Replicating utilitity methods from rice post processor service */
    protected UserSession establishPostProcessorUserSession() throws WorkflowException {
        if (globalVariableService.getUserSession() == null) {
            return new UserSession(KRADConstants.SYSTEM_USER);
        } else {
            return globalVariableService.getUserSession();
        }
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    public void setPostProcessorService(PostProcessorService postProcessorService) {
        this.postProcessorService = postProcessorService;
    }

    public WorkflowDocumentService getWorkflowDocumentService() {
        return workflowDocumentService;
    }

    public PostProcessorService getPostProcessorService() {
        return postProcessorService;
    }
}
