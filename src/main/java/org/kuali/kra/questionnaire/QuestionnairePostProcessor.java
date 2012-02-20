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
package org.kuali.kra.questionnaire;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.workflow.KcPostProcessor;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is for Questionnaire maintenance.  Adding code to mark document finalized if
 * it is blanket by a user who is not initiator.  If this is not done, then the doc status is 'processed'.
 */
public class QuestionnairePostProcessor extends KcPostProcessor {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(QuestionnairePostProcessor.class);

    @Override
    public ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception {
        ProcessDocReport routeStatusChange = super.doRouteStatusChange(statusChangeEvent);
        if (KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equals(statusChangeEvent.getNewRouteStatus()) && !isApproveByInitiator(statusChangeEvent.getDocumentId().toString())) {
        try {
            DocumentRouteHeaderValue document = getRouteHeaderService().getRouteHeader(statusChangeEvent.getDocumentId());
            document.markDocumentFinalized();
        } catch (Exception e) {
            LOG.debug("mark Questionnaire doc 'finalized' failed "+e.getMessage());
        }
        }
        return routeStatusChange;
    }

    private boolean isApproveByInitiator(String docId) {
        DocumentRouteHeaderValue document = getRouteHeaderService().getRouteHeader(docId);
        String initiatorId = document.getInitiatorWorkflowId();
        
        ActionTaken lastActionTaken = null;
        for (ActionTakenValue actionTakenValue : document.getActionsTaken()) {
            ActionTaken actionTaken = ActionTakenValue.to(actionTakenValue);
            ActionType actionTakenType = actionTaken.getActionTaken();
            boolean isApprovalAction = actionTakenType.equals(ActionType.APPROVE) || actionTakenType.equals(ActionType.BLANKET_APPROVE);
            boolean isLaterAction = lastActionTaken != null && actionTaken.getActionDate().toDate().after(lastActionTaken.getActionDate().toDate());
            if (lastActionTaken == null || (isApprovalAction && isLaterAction)) {
                lastActionTaken = actionTaken;
            }
        }

        return StringUtils.equals(initiatorId, lastActionTaken.getPrincipalId());
    }

    private RouteHeaderService getRouteHeaderService() {
        return KraServiceLocator.getService(RouteHeaderService.class);
    }

}
