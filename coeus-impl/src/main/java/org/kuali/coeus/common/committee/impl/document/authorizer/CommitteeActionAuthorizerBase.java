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
package org.kuali.coeus.common.committee.impl.document.authorizer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;

/**
 * The CommitteeBase Action Authorizer checks to see if the user has 
 * permission to perform committee actions. 
 */
public abstract class CommitteeActionAuthorizerBase extends CommitteeAuthorizerBase {
    
    private CommitteeServiceBase committeeService;

    public boolean isAuthorized(String userId, CommitteeTaskBase task) {
        CommitteeBase committee = task.getCommittee();
        return StringUtils.equals(committee.getCommitteeDocument().getDocumentHeader().getWorkflowDocument().getStatus().getLabel(), "FINAL")
                && committee.getCommitteeId() != null
                && committeeService.getCommitteeById(committee.getCommitteeId()).getId().equals(committee.getId())
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
