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
