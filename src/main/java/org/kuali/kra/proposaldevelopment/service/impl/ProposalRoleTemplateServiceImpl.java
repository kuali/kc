/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.service.ProposalRoleService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * The Proposal Role Template Service Implementation.
 */
public class ProposalRoleTemplateServiceImpl implements ProposalRoleTemplateService {
    private KraAuthorizationService kraAuthorizationService;
    private RoleService roleManagementService;
    private ProposalRoleService proposalRoleService;
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the proposal authorization service
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }


    public void setProposalRoleService(ProposalRoleService proposalRoleService) {
        this.proposalRoleService = proposalRoleService;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService#addUsers(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void addUsers(ProposalDevelopmentDocument doc) {
        String creatorUserId = getCreator(doc);
        Map<String, String> roleIdMap = new HashMap<String, String>();
        Role role = null;
        
        Collection<RoleMembership> proposalRoleTemplates = getRoleTemplates(doc.getDevelopmentProposal().getOwnedByUnitNumber());
        for (RoleMembership proposalRoleTemplate : proposalRoleTemplates) {
            String personId = proposalRoleTemplate.getMemberId();
            if (personId != null && !StringUtils.equals(personId, creatorUserId)) {
                if(StringUtils.isEmpty(roleIdMap.get(proposalRoleTemplate.getRoleId()))){
                    role = roleManagementService.getRole(proposalRoleTemplate.getRoleId());
                    roleIdMap.put(proposalRoleTemplate.getRoleId(), role.getName());
                }
                kraAuthorizationService.addRole(personId, roleIdMap.get(proposalRoleTemplate.getRoleId()), doc); 
            }
        }
    }
    
    /**
     * Gets the creator of the proposal.  Actually, I'm being sneaky.  The addUsers method is only
     * used when the proposal is being created.  Therefore, the current user corresponds to the
     * creator of the proposal.
     * @param doc the proposal development document
     * @return the creator's username
     */
    public String getCreator(ProposalDevelopmentDocument doc) {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * Get the role templates for the proposal.  The retrieved role templates correspond
     * to the proposal's lead unit.
     * @param unitNumber the lead unit of the proposal
     * @return the collection of role templates
     */
    protected Collection<RoleMembership> getRoleTemplates(String unitNumber) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put("unitNumber", unitNumber);
        List<Role> proposalRoles = proposalRoleService.getRolesForDisplay();
        Collection<RoleMembership> membershipInfoList = new ArrayList<RoleMembership>();
        Collection<String> memberIds = null;
        RoleMembership.Builder roleMembershipBuilder = null;
        for(Role role : proposalRoles) {
            memberIds = roleManagementService.getRoleMemberPrincipalIds(role.getNamespaceCode(), role.getName(), qualifiedRoleAttributes);
            if(CollectionUtils.isNotEmpty(memberIds)) {
                for(String memberId : memberIds) {
                    roleMembershipBuilder = RoleMembership.Builder.create(role.getId(), null, memberId, MemberType.PRINCIPAL, null);
                    membershipInfoList.add(roleMembershipBuilder.build());
                }
                roleMembershipBuilder = null;
                memberIds = null;
            }
        }
        return membershipInfoList;
    }

}
