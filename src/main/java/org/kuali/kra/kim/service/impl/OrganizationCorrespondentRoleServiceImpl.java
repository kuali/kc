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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.OrganizationCorrespondent;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.OrganizationService;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.framework.role.RoleTypeService;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

/**
 * Checks whether the principal is an OrganizationCorrespondent for the given Organization ID.
 */
public class OrganizationCorrespondentRoleServiceImpl extends DerivedRoleTypeServiceBase implements RoleTypeService {
    
    private OrganizationService organizationService;
    
    @Override
    public boolean hasDerivedRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification) {
        
        String organizationId = qualification.get(KcKimAttributes.ORGANIZATION_ID);
        if (StringUtils.isNotBlank(organizationId)) {
            List<OrganizationCorrespondent> organizationCorrespondents = organizationService.retrieveOrganizationCorrespondentsByOrganizationId(organizationId);
            for (OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents) {
                if (organizationCorrespondent.getPersonId().equals(principalId)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        String organizationId = qualification.get(KcKimAttributes.ORGANIZATION_ID);
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        
        if (StringUtils.isNotBlank(organizationId)) {
            List<OrganizationCorrespondent> organizationCorrespondents = organizationService.retrieveOrganizationCorrespondentsByOrganizationId(organizationId);
            for ( OrganizationCorrespondent organizationCorrespondent : organizationCorrespondents ) {
                if ( StringUtils.isNotBlank(organizationCorrespondent.getPersonId()) ) {
                    members.add( RoleMembership.Builder.create(null, null, organizationCorrespondent.getPersonId(), MemberType.PRINCIPAL, null).build() );
                }
            }
        }
            
        return members;
    }
}
