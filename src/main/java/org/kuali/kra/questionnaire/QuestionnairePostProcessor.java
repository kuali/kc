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

import java.rmi.RemoteException;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.workflow.KcPostProcessor;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class is for Questionnaire maintenance.  Adding code to mark document finalized if
 * it is blanket by a user who is not initiator.  If this is not done, then the doc status is 'processed'.
 */
public class QuestionnairePostProcessor extends KcPostProcessor {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(QuestionnairePostProcessor.class);

    @Override
    public boolean doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) throws RemoteException {
        boolean routeStatusChange = super.doRouteStatusChange(statusChangeEvent);
        if (KEWConstants.ROUTE_HEADER_PROCESSED_CD.equals(statusChangeEvent.getNewRouteStatus()) && !isApproveByInitiator(statusChangeEvent.getRouteHeaderId().toString())) {
        try {
            DocumentRouteHeaderValue document = getRouteHeaderService().getRouteHeader(statusChangeEvent.getRouteHeaderId());
            document.markDocumentFinalized();
        } catch (Exception e) {
            LOG.debug("mark Questionnaire doc 'finalized' failed "+e.getMessage());
        }
        }
        return routeStatusChange;
    }

    private boolean isApproveByInitiator(String docId) {
        boolean isInitiator = true;
        DocumentRouteHeaderValue document = getRouteHeaderService().getRouteHeader(Long.parseLong(docId));
        if (CollectionUtils.isNotEmpty(document.getActionsTaken())) {
            isInitiator = document.getActionsTaken().get(0).getPrincipalId().equals(GlobalVariables.getUserSession().getPrincipalId());
        }
        return isInitiator;
    }

    private RouteHeaderService getRouteHeaderService() {
        return KraServiceLocator.getService(RouteHeaderService.class);
    }

}
