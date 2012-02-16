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
package org.kuali.kra.service.impl;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.OptimisticLockException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.service.impl.PostProcessorServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Extends the {@PostProcessorService} to record the actual user performing an action on a workflow status change.
 */
public class KcPostProcessorServiceImpl extends PostProcessorServiceImpl implements PostProcessorService {

    private static final Log LOG = LogFactory.getLog(KcPostProcessorServiceImpl.class);
    
    private WorkflowDocumentService workflowDocumentService;
    
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
                GlobalVariables.getUserSession().addObject(Constants.LAST_ACTION_PRINCIPAL_ID, (Object) lastPrincipalId);
            }
        } catch (WorkflowException e) {
            LOG.error("caught exception while handling route status change", e);

            throw new RuntimeException("post processor caught exception while handling route status change: " + e.getMessage(), e);
        }
    }
    
    @Override
    public ProcessDocReport doRouteStatusChange(final DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return GlobalVariables.doInNewGlobalVariables(establishPostProcessorUserSession(),
                new Callable<ProcessDocReport>() {
                    public ProcessDocReport call() throws Exception {
                        DocumentService documentService = KRADServiceLocatorWeb.getDocumentService();
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
    
    /* Replicating utilitity methods from super class */
    /* will request for those to be made protected */
    private void logAndRethrow(String changeType, Exception e) throws RuntimeException {
        LOG.error("caught exception while handling " + changeType + " change", e);
        logOptimisticDetails(5, e);

        throw new RuntimeException("post processor caught exception while handling " + changeType + " change: " + e.getMessage(), e);
    }

    /* Replicating utilitity methods from super class */
    /* will request for those to be made protected */
    private void logOptimisticDetails(int depth, Throwable t) {
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
    
    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }
    
}