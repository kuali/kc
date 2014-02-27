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
