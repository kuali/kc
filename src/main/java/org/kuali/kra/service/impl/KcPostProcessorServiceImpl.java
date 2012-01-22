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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.service.impl.PostProcessorServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Extends the {@PostProcessorService} to record the actual user performing an action on a workflow status change.
 */
public class KcPostProcessorServiceImpl extends PostProcessorServiceImpl implements PostProcessorService {

    private static final Log LOG = LogFactory.getLog(KcPostProcessorServiceImpl.class);
    
    private WorkflowDocumentService workflowDocumentService;
    
    @Override
    public ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception {
        try {
            establishGlobalVariables();
            
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
        
        return super.doRouteStatusChange(statusChangeEvent);
        
    }
    
    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }
    
}