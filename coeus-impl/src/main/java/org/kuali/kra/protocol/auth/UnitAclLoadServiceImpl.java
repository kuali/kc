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
package org.kuali.kra.protocol.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.type.KimType;

import java.util.*;

public class UnitAclLoadServiceImpl implements UnitAclLoadService {

    private KcAuthorizationService kraAuthorizationService;
    private RoleService roleManagementService;
    private SystemAuthorizationService systemAuthorizationService;
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the proposal authorization service
     */
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public void loadUnitAcl(Permissionable permissionable, String creatorPrincipalId) {
        Map<String, String> roleIdMap = new HashMap<String, String>();
        
        Collection<RoleMembership> kraRoleTemplates = getDocumentDefaultAcl(permissionable.getLeadUnitNumber(), permissionable.getDocumentRoleTypeCode());
        for (RoleMembership kraRoleTemplate : kraRoleTemplates) {
            String personId = kraRoleTemplate.getMemberId();
            if (personId != null && !StringUtils.equals(personId, creatorPrincipalId)) {
                String key = kraRoleTemplate.getRoleId() + "|" + personId;
                if (StringUtils.isEmpty(roleIdMap.get(key))) {
                    Role role = roleManagementService.getRole(kraRoleTemplate.getRoleId());
                    roleIdMap.put(key, role.getName());
                    kraAuthorizationService.addDocumentLevelRole(personId, role.getName(), permissionable);
                }
            }
        }
    }

    /**
     * Get the access control list for the document type as specified by the system administrator.
     * The retrieved access control list correspond to the document's unit.
     * @param unitNumber of the document
     * @param documentTypeCode
     * @return the access control list for the document type
     */
    protected Collection<RoleMembership> getDocumentDefaultAcl(String unitNumber, String documentTypeCode) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put("unitNumber", unitNumber);
        List<String> roleIds = new ArrayList<String>();
        List<Role> roles = systemAuthorizationService.getRoles(documentTypeCode);
        for(Role role : roles) {
            if (isAccessListRole(role)) {
            roleIds.add(role.getId());
        }
        }
        List<RoleMembership> membershipInfoList = roleManagementService.getRoleMembers(roleIds,new HashMap<String,String>(qualifiedRoleAttributes));
        
        return membershipInfoList;
    }
        
    /* 
     * This method filters out derived roles and roles that are not based on Unit or workflow
     */
    protected boolean isAccessListRole(Role role) {
        KimType type = systemAuthorizationService.getKimTypeInfoForRole(role);
        return (!StringUtils.startsWith(type.getName(), "Derived Role") && !StringUtils.startsWith(type.getName(), "Default"));
    }
    
}
