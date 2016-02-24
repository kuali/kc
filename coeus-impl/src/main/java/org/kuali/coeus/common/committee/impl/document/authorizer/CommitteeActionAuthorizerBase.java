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
