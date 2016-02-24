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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcPostProcessor;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;

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
        return KcServiceLocator.getService(RouteHeaderService.class);
    }

}
