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
package org.kuali.kra.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public class PastApproversDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    private WorkflowDocumentService workflowDocumentService;
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembership> members = new ArrayList<RoleMembership>();
        String documentNumber = qualification.get(KimConstants.AttributeConstants.DOCUMENT_NUMBER);
        if (documentNumber != null) {
            List<ActionTaken> actions = getWorkflowDocumentService().getActionsTaken(documentNumber);
            
            if (actions != null && !actions.isEmpty()) {
                for (ActionTaken action : actions) {
                    if (action.getActionTaken() == ActionType.APPROVE) {
                        members.add( RoleMembership.Builder.create(null, null, action.getPrincipalId(), MemberType.PRINCIPAL, null).build() );
                    }
                }
            }
        }
            
        return members;
    }

    @Override
    public boolean hasDerivedRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){
        List<RoleMembership> members = getRoleMembersFromDerivedRole(namespaceCode, roleName, qualification);
        for (RoleMembership member : members) {
            if (StringUtils.equals(member.getMemberId(), principalId)) {
                return true;
            }
        }
        return false;
    }

    public WorkflowDocumentService getWorkflowDocumentService() {
        return workflowDocumentService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

}
