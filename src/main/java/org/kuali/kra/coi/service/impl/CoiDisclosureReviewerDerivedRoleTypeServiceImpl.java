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
package org.kuali.kra.coi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

/**
 * 
 * This class determines the assigned reviewers for a coi disclosure.
 */
public class CoiDisclosureReviewerDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CoiDisclosureReviewerDerivedRoleTypeServiceImpl.class);
    private static final String DISCLOSURE = "disclosure";
    
    private KcPersonService kcPersonService;
    
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(DISCLOSURE);
    }
    
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();

        String disclosureId = qualification.get(DISCLOSURE);
        CoiDisclosure disclosure = getCoiDisclosureById(disclosureId);
        if (disclosure != null) {
            List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();
            if (CollectionUtils.isNotEmpty(userRoles)) {
                for (CoiUserRole userRole : userRoles) {
                    if (StringUtils.equalsIgnoreCase(userRole.getRoleName(), RoleConstants.COI_REVIEWER)) {
                        members.add(new RoleMembershipInfo(null, null, getPersonId(userRole.getUserId()), Role.PRINCIPAL_MEMBER_TYPE, null));
                    }

                }
            }
        }
        
        return members;
    }
    
    @Override
    public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        boolean hasRole = false;
        String disclosureId = qualification.get(DISCLOSURE);
        CoiDisclosure disclosure = getCoiDisclosureById(disclosureId);
        if (disclosure != null) {
            List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();
            if (CollectionUtils.isNotEmpty(userRoles)) {
                String personId = null;
                for (CoiUserRole userRole : userRoles) {
                    personId = getPersonId(userRole.getUserId());
                    if (StringUtils.equals(personId, principalId)) {
                        hasRole = true;
                        break;
                    }
                }
            }
        }
        return hasRole;
    }
    
    private CoiDisclosure getCoiDisclosureById(String disclosureId) {
        CoiDisclosure disclosure = null;
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("coiDisclosureId", disclosureId);
        disclosure = (CoiDisclosure)getBusinessObjectService().findByPrimaryKey(CoiDisclosure.class, params);
        
        return disclosure;
    }
    
    private String getPersonId(String userName) {
        String personId = null;
        
        KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
        if (person != null) {
            personId = person.getPersonId();
        }
        
        return personId;
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
