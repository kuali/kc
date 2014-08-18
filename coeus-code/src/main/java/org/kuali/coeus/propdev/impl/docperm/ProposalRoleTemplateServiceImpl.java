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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The Proposal Role Template Service Implementation.
 */
@Component("proposalRoleTemplateService")
public class ProposalRoleTemplateServiceImpl implements ProposalRoleTemplateService {

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleManagementService;

    @Autowired
    @Qualifier("proposalRoleService")
    private ProposalRoleService proposalRoleService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

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


    public void setProposalRoleService(ProposalRoleService proposalRoleService) {
        this.proposalRoleService = proposalRoleService;
    }

    @Override
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
    protected String getCreator(ProposalDevelopmentDocument doc) {
        return globalVariableService.getUserSession().getPrincipalId();
    }
    
    /**
     * Create the original set of Proposal Users for a new Proposal Development Document.
     * The creator the proposal is assigned to the AGGREGATOR role.
     */
     public void initializeProposalUsers(ProposalDevelopmentDocument doc) {
         // Assign the creator of the proposal to the AGGREGATOR role.
         String userId = getCreator(doc);
         if (!kraAuthorizationService.hasRole(userId, doc, RoleConstants.AGGREGATOR))
             kraAuthorizationService.addRole(userId, RoleConstants.AGGREGATOR, doc);
         
         // Add the users defined in the role templates for the proposal's lead unit
         addUsers(doc);
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

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public RoleService getRoleManagementService() {
        return roleManagementService;
    }

    public ProposalRoleService getProposalRoleService() {
        return proposalRoleService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
