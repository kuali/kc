/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.auth;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Is the user allowed to approve protocols?
 */
public class ProtocolApproveOtherAuthorizer extends ProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        ProtocolDocument protocolDocument = (ProtocolDocument) task.getProtocol().getProtocolDocument();

        return kraWorkflowService.isUserActionRequested(protocolDocument, userId) && 
            (!kraWorkflowService.isDocumentOnNode(protocolDocument, Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME) || 
                    (kraWorkflowService.isUserAdHocRequestRecipient(protocolDocument, userId, null) && !kraWorkflowService.isUserRouteRespRequestRecipient(protocolDocument, userId, null))
            );
    }


    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}