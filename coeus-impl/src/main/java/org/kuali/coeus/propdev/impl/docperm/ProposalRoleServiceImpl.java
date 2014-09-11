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
package org.kuali.coeus.propdev.impl.docperm;

import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccessConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("proposalRoleService")
public class ProposalRoleServiceImpl implements ProposalRoleService {

    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public SystemAuthorizationService getSystemAuthorizationService() {
        return systemAuthorizationService;
    }

    public List<Role> getRolesForDisplay() {
        return systemAuthorizationService.getRolesByType(RoleConstants.PROPOSAL_ROLE_TYPE, DocumentAccessConstants.DOC_LEVEL_KIM_TYPE_NAME, DocumentAccessConstants.DOC_LEVEL_KIM_TYPE_NAMESPACE);
    }
}
