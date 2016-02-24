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
package org.kuali.kra.iacuc.auth;

import java.util.Collections;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.DocumentDetail;

public class IacucProtocolAssignReviewersCmtSelAuthorizer extends IacucProtocolAuthorizer {

	private KcWorkflowService kraWorkflowService;

	@Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        IacucProtocol protocol = task.getProtocol();
        return (isOnNode(protocol) || willBeOnNode(userId, protocol)) && 
            hasPermission(userId, protocol, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
    }

    public boolean isOnNode(IacucProtocol protocol) {
        return kraWorkflowService.isDocumentOnNode(protocol.getProtocolDocument(), Constants.IACUC_PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME);
    }

    // look to insure our next node won't be "DepartmentReview", which means the protocol will require
    // departmental approval before being assigned reviewers
    public boolean willBeOnNode(String userId, IacucProtocol protocol) {
        boolean results = true;
        RoutingReportCriteria.Builder reportCriteriaBuilder = RoutingReportCriteria.Builder.createByDocumentId(protocol.getProtocolDocument().getDocumentNumber());
        reportCriteriaBuilder.setTargetPrincipalIds(Collections.singletonList(userId));
        WorkflowDocumentActionsService info = GlobalResourceLoader.getService("rice.kew.workflowDocumentActionsService");
        
        try { 
            DocumentDetail results1 = info.executeSimulation(reportCriteriaBuilder.build());
            for(ActionRequest actionRequest : results1.getActionRequests() ){
                if (Constants.PROTOCOL_APPROVAL_NODE_NAME.equals(actionRequest.getNodeName())) {
                    results = false;
                }
            }
        } catch (Exception e) {}
        return results;
    }
    
	
    protected KcWorkflowService getKraWorkflowService() {
		return kraWorkflowService;
	}

	public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
		this.kraWorkflowService = kraWorkflowService;
	}

}
