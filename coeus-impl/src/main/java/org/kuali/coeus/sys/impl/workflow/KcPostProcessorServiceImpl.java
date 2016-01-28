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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.workflow.LastActionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.*;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.LegacyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A {@link PostProcessorService} to record the actual user performing an action on a workflow status change.
 */
@Component("kcPostProcessorService")
@Transactional
public class KcPostProcessorServiceImpl implements PostProcessorService {

    private static final Log LOG = LogFactory.getLog(KcPostProcessorServiceImpl.class);

    private static final String KC_POST_PROCESSOR_LEGACY_SAVE = "KC_POST_PROCESSOR_LEGACY_SAVE";

    @Autowired
    @Qualifier("lastActionService")
    private LastActionService lastActionService;

    @Autowired
    @Qualifier("postProcessorService")
    private PostProcessorService postProcessorService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    /**
     * This is an exact copy of Rice's default doRouteStatusChangeMethod except that this method does not do a full document save.
     */
    public ProcessDocReport doNonDocumentSavingRouteStatusChange(final DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return LegacyUtils.doInLegacyContext(statusChangeEvent.getDocumentId(), establishPostProcessorUserSession(), () -> {
            try {
                if (LOG.isInfoEnabled()) {
                    LOG.info(new StringBuilder("started handling route status change from ").append(
                            statusChangeEvent.getOldRouteStatus()).append(" to ").append(
                            statusChangeEvent.getNewRouteStatus()).append(" for document ").append(
                            statusChangeEvent.getDocumentId()));
                }

                Document document = documentService.getByDocumentHeaderId(statusChangeEvent.getDocumentId());
                if (document == null) {
                    if (!KewApiConstants.ROUTE_HEADER_CANCEL_CD.equals(statusChangeEvent.getNewRouteStatus())) {
                        throw new RuntimeException("unable to load document " + statusChangeEvent.getDocumentId());
                    }
                } else {
                    document.doRouteStatusChange(statusChangeEvent);
                    if (!document.getDocumentHeader().getWorkflowDocument().isSaved()) {
                        document.getDocumentHeader().getWorkflowDocument().saveDocumentData();
                    }

                }
                if (LOG.isInfoEnabled()) {
                    LOG.info(new StringBuilder("finished handling route status change from ").append(
                            statusChangeEvent.getOldRouteStatus()).append(" to ").append(
                            statusChangeEvent.getNewRouteStatus()).append(" for document ").append(
                            statusChangeEvent.getDocumentId()));
                }
            } catch (Exception e) {
                logAndRethrow("route status", e);
            }
            return new ProcessDocReport(true, "");
        });
    }

    private void logAndRethrow(String changeType, Exception e) throws RuntimeException {
        LOG.error("caught exception while handling " + changeType + " change", e);
        logOptimisticDetails(5, e);

        throw new RuntimeException("post processor caught exception while handling " + changeType + " change: " + e.getMessage(), e);
    }

    private void logOptimisticDetails(int depth, Throwable t) {
        if ((depth > 0) && (t != null)) {
            Object sourceObject = null;
            boolean optLockException = false;
            if ( t instanceof javax.persistence.OptimisticLockException ) {
                sourceObject = ((javax.persistence.OptimisticLockException)t).getEntity();
                optLockException = true;
            } else if ( t instanceof OptimisticLockingFailureException) {
                sourceObject = (t).getMessage();
                optLockException = true;
            } else if ( t.getClass().getName().equals( "org.apache.ojb.broker.OptimisticLockException" ) ) {
                try {
                    sourceObject = PropertyUtils.getSimpleProperty(t, "sourceObject");
                } catch (Exception ex) {
                    LOG.warn( "Unable to retrieve source object from OJB OptimisticLockException", ex );
                }
                optLockException = true;
            }
            if ( optLockException ) {
                if (sourceObject != null) {
                    if ( sourceObject instanceof String ) {
                        LOG.error("source of OptimisticLockException Unknown.  Message: " + sourceObject);
                    } else {
                        LOG.error("source of OptimisticLockException = " + sourceObject.getClass().getName() + " ::= " + sourceObject);
                    }
                }
            } else {
                Throwable cause = t.getCause();
                if (cause != t) {
                    logOptimisticDetails(--depth, cause);
                }
            }
        }
    }

    @Override
    public ProcessDocReport doRouteStatusChange(final DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), () -> {
            if (parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_SYSTEM, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KC_POST_PROCESSOR_LEGACY_SAVE)) {
                return postProcessorService.doRouteStatusChange(statusChangeEvent);
            } else {
                return this.doNonDocumentSavingRouteStatusChange(statusChangeEvent);
            }
        });
    }

    @Override
    public ProcessDocReport doRouteLevelChange(final DocumentRouteLevelChange levelChangeEvent) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), () -> postProcessorService.doRouteLevelChange(levelChangeEvent));
    }

    @Override
    public ProcessDocReport doDeleteRouteHeader(DeleteEvent event) throws Exception {
        return postProcessorService.doDeleteRouteHeader(event);
    }

    @Override
    public ProcessDocReport doActionTaken(final ActionTakenEvent event) throws Exception {
        return globalVariableService.doInNewGlobalVariables(establishPostProcessorUserSession(), () -> postProcessorService.doActionTaken(event));
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

    /* Replicating utilitity methods from rice post processor service */
    protected UserSession establishPostProcessorUserSession() throws WorkflowException {
        if (globalVariableService.getUserSession() == null) {
            return new UserSession(KRADConstants.SYSTEM_USER);
        } else {
            return globalVariableService.getUserSession();
        }
    }

    public void setPostProcessorService(PostProcessorService postProcessorService) {
        this.postProcessorService = postProcessorService;
    }

    public PostProcessorService getPostProcessorService() {
        return postProcessorService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public LastActionService getLastActionService() {
        return lastActionService;
    }

    public void setLastActionService(LastActionService lastActionService) {
        this.lastActionService = lastActionService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
