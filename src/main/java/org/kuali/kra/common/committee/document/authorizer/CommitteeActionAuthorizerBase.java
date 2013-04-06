/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.document.authorizer;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;

/**
 * The CommitteeBase Action Authorizer checks to see if the user has 
 * permission to perform committee actions. 
 */
public abstract class CommitteeActionAuthorizerBase extends CommitteeAuthorizerBase {
    
    private CommitteeServiceBase committeeService;

    /**
     * @see org.kuali.kra.protocol.document.authorizer.CommitteeAuthorizerBase#isAuthorized(java.lang.String, org.kuali.kra.protocol.document.authorization.CommitteeTaskBase)
     */
    public boolean isAuthorized(String userId, CommitteeTaskBase task) {
        CommitteeBase committee = task.getCommittee();
        return StringUtils.equals(committee.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().getStatus().getLabel(), "FINAL")
                && committee.getCommitteeId() != null
                && committeeService.getCommitteeById(committee.getCommitteeId()).getId().equals(committee.getId())
                
// TODO *********commented the code below during IACUC refactoring*********                 
//                && hasPermission(userId, committee, PermissionConstants.PERFORM_IACUC_COMMITTEE_ACTIONS);
                
                && hasPermission(userId, committee, getPermissionNameForPerformCommitteeActionsCodeHook());
    }
    
    protected abstract String getPermissionNameForPerformCommitteeActionsCodeHook();
    
    /**
     * Set the CommitteeBase Service.  Usually injected by the Spring Framework.
     * @param committeeService
     */
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }

}
