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
package org.kuali.coeus.sys.impl.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.OptimisticLockException;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.*;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.LegacyUtils;
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
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("documentDictionaryService")
    private DocumentDictionaryService documentDictionaryService;

    protected void performKCWorkaround(DocumentRouteStatusChange statusChangeEvent) throws Exception {
        try {
            establishPostProcessorUserSession();

            String routeHeaderId = statusChangeEvent.getDocumentId();

            List<ActionTaken> actionsTaken = workflowDocumentService.getActionsTaken(routeHeaderId);

            ActionTaken lastActionTaken = null;
            for (ActionTaken actionTaken : actionsTaken) {
                if (lastActionTaken == null || actionTaken.getActionDate().toDate().after(lastActionTaken.getActionDate().toDate())) {
                    lastActionTaken = actionTaken;
                }
            }

            if (lastActionTaken != null) {
                String lastPrincipalId = lastActionTaken.getPrincipalId();
                GlobalVariables.getUserSession().addObject(LAST_ACTION_PRINCIPAL_ID, (Object) lastPrincipalId);
            }
        } catch (WorkflowException e) {
            LOG.error("caught exception while handling route status change", e);

            throw new RuntimeException("post processor caught exception while handling route status change: " + e.getMessage(), e);
        }
    }

    @Override
    public ProcessDocReport doRouteStatusChange(final DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return doInContext(statusChangeEvent.getDocumentId(),
                new Callable<ProcessDocReport>() {
                    public ProcessDocReport call() throws Exception {
                        try {
                            if (LOG.isInfoEnabled()) {
                                LOG.info(new StringBuffer("started handling route status change from ").append(
                                        statusChangeEvent.getOldRouteStatus()).append(" to ").append(
                                        statusChangeEvent.getNewRouteStatus()).append(" for document ").append(
                                        statusChangeEvent.getDocumentId()));
                            }

                            Document document = documentService.getByDocumentHeaderId(
                                    statusChangeEvent.getDocumentId());
                            if (document == null) {
                                if (!KewApiConstants.ROUTE_HEADER_CANCEL_CD.equals(
                                        statusChangeEvent.getNewRouteStatus())) {
                                    throw new RuntimeException(
                                            "unable to load document " + statusChangeEvent.getDocumentId());
                                }
                            } else {
                                performKCWorkaround(statusChangeEvent);
                                document.doRouteStatusChange(statusChangeEvent);
                                // PLEASE READ BEFORE YOU MODIFY:
                                // we dont want to update the document on a Save, as this will cause an
                                // OptimisticLockException in many cases, because the DB versionNumber will be
                                // incremented one higher than the document in the browser, so when the user then
                                // hits Submit or Save again, the versionNumbers are out of synch, and the
                                // OptimisticLockException is thrown. This is not the optimal solution, and will
                                // be a problem anytime where the user can continue to edit the document after a
                                // workflow state change, without reloading the form.
                                if (!document.getDocumentHeader().getWorkflowDocument().isSaved()) {
                                    documentService.updateDocument(document);
                                }
                            }
                            if (LOG.isInfoEnabled()) {
                                LOG.info(new StringBuffer("finished handling route status change from ").append(
                                        statusChangeEvent.getOldRouteStatus()).append(" to ").append(
                                        statusChangeEvent.getNewRouteStatus()).append(" for document ").append(
                                        statusChangeEvent.getDocumentId()));
                            }
                        } catch (Exception e) {
                            logAndRethrow("route status", e);
                        }
                        return new ProcessDocReport(true, "");
                    }
                });
    }

    @Override
    public ProcessDocReport doRouteLevelChange(DocumentRouteLevelChange levelChangeEvent) throws Exception {
        return postProcessorService.doRouteLevelChange(levelChangeEvent);
    }

    @Override
    public ProcessDocReport doDeleteRouteHeader(DeleteEvent event) throws Exception {
        return postProcessorService.doDeleteRouteHeader(event);
    }

    @Override
    public ProcessDocReport doActionTaken(ActionTakenEvent event) throws Exception {
        return postProcessorService.doActionTaken(event);
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
    protected void logAndRethrow(String changeType, Exception e) throws RuntimeException {
        LOG.error("caught exception while handling " + changeType + " change", e);
        logOptimisticDetails(5, e);

        throw new RuntimeException("post processor caught exception while handling " + changeType + " change: " + e.getMessage(), e);
    }

    /* Replicating utilitity methods from rice post processor service */
    protected void logOptimisticDetails(int depth, Throwable t) {
        if ((depth > 0) && (t != null)) {
            if (t instanceof OptimisticLockException) {
                OptimisticLockException o = (OptimisticLockException) t;

                LOG.error("source of OptimisticLockException = " + o.getSourceObject().getClass().getName() + " ::= " + o.getSourceObject());
            }
            else {
                Throwable cause = t.getCause();
                if (cause != t) {
                    logOptimisticDetails(--depth, cause);
                }
            }
        }
    }

    /* Replicating utilitity methods from rice post processor service */
    protected UserSession establishPostProcessorUserSession() throws WorkflowException {
        if (GlobalVariables.getUserSession() == null) {
            return new UserSession(KRADConstants.SYSTEM_USER);
        } else {
            return GlobalVariables.getUserSession();
        }
    }

    /* Replicating utilitity methods from rice post processor service */
    protected <T> T doInContext(String documentId, Callable<T> callable) throws Exception {
        boolean inLegacyContext = establishLegacyDataContextIfNeccesary(documentId);
        try {
            return GlobalVariables.doInNewGlobalVariables(establishPostProcessorUserSession(), callable);
        } finally {
            clearLegacyDataContextIfExists(inLegacyContext);
        }
    }

    /* Replicating utilitity methods from rice post processor service */
    protected boolean establishLegacyDataContextIfNeccesary(String documentId) {
        String documentTypeName = workflowDocumentService.getDocumentTypeName(documentId);
        DocumentEntry documentEntry = documentDictionaryService.getDocumentEntry(documentTypeName);

        if (LegacyUtils.isKnsDocumentEntry(documentEntry)) {
            LegacyUtils.beginLegacyContext();

            return true;
        }
        return false;
    }

    /* Replicating utilitity methods from rice post processor service */
    protected void clearLegacyDataContextIfExists(boolean inLegacyContext) {
        if (inLegacyContext) {
            LegacyUtils.endLegacyContext();
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

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentDictionaryService getDocumentDictionaryService() {
        return documentDictionaryService;
    }

    public void setDocumentDictionaryService(DocumentDictionaryService documentDictionaryService) {
        this.documentDictionaryService = documentDictionaryService;
    }
}