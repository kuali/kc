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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.kra.service.UnitAclLoadService;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * @see org.kuali.kra.service.UnitAclLoadService
 */
public class UnitAclLoadServiceImpl implements UnitAclLoadService {

    private KraAuthorizationService kraAuthorizationService;
    private RoleManagementService roleManagementService;
    private SystemAuthorizationService systemAuthorizationService;
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the proposal authorization service
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setRoleManagementService(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    /**
     * @see org.kuali.kra.service.UnitAclLoadService#loadUnitAcl(org.kuali.kra.document.ResearchDocumentBase)
     */
    public void loadUnitAcl(Permissionable permissionable) {
        String creatorUserId = getCreator();
        Map<String, String> roleIdMap = new HashMap<String, String>();
        Role role = null;
        
        Collection<RoleMembershipInfo> kraRoleTemplates = getDocumentDefaultAcl(permissionable.getLeadUnitNumber(), permissionable.getDocumentRoleTypeCode());
        for (RoleMembershipInfo kraRoleTemplate : kraRoleTemplates) {
            String personId = kraRoleTemplate.getMemberId();
            if (personId != null && !StringUtils.equals(personId, creatorUserId)) {
                if(StringUtils.isEmpty(roleIdMap.get(kraRoleTemplate.getRoleId()))){
                    role = roleManagementService.getRole(kraRoleTemplate.getRoleId());
                    roleIdMap.put(kraRoleTemplate.getRoleId(), role.getRoleName());
                }
                kraAuthorizationService.addRole(personId, roleIdMap.get(kraRoleTemplate.getRoleId()), permissionable); 
            }
        }
    }
    
    /**
     * Gets the creator of the document.  Actually, I'm being sneaky.  The addUsers method is only
     * used when the document is being created.  Therefore, the current user corresponds to the
     * creator of the document.
     * @return the creator's username
     */
    public String getCreator() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    /**
     * Get the access control list for the document type as specified by the system administrator.
     * The retrieved access control list correspond to the document's unit.
     * @param unitNumber of the document
     * @param documentTypeCode
     * @return the access control list for the document type
     */
    protected Collection<RoleMembershipInfo> getDocumentDefaultAcl(String unitNumber, String documentTypeCode) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put("unitNumber", unitNumber);
        List<String> roleIds = new ArrayList<String>();
        List<Role> proposalRoles = systemAuthorizationService.getRoles(documentTypeCode);
        for(Role role : proposalRoles) {
            roleIds.add(role.getRoleId());
        }
        List<RoleMembershipInfo> membershipInfoList = roleManagementService.getRoleMembers(roleIds, new AttributeSet(qualifiedRoleAttributes));
        return membershipInfoList;
        
    }
}
