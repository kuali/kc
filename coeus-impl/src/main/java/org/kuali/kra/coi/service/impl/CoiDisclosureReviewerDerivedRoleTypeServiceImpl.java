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
package org.kuali.kra.coi.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class determines the assigned reviewers for a coi disclosure.
 */
public class CoiDisclosureReviewerDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    private static final String DISCLOSURE = "coiDisclosureId";
    
    private KcPersonService kcPersonService;
    
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(DISCLOSURE);
    }
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        List<RoleMembership> members = new ArrayList<RoleMembership>();

        String disclosureId = qualification.get(DISCLOSURE);
        CoiDisclosure disclosure = getCoiDisclosureById(disclosureId);
        if (disclosure != null) {
            List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();
            if (CollectionUtils.isNotEmpty(userRoles)) {
                for (CoiUserRole userRole : userRoles) {
                    if (StringUtils.equalsIgnoreCase(userRole.getRoleName(), RoleConstants.COI_REVIEWER)) {
                        members.add(RoleMembership.Builder.create(null, null, getPersonId(userRole.getUserId()), MemberType.PRINCIPAL, null).build());
                    }

                }
            }
        }
        
        return members;
    }
    
    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        boolean hasRole = false;
        String disclosureId = qualification.get(DISCLOSURE);
        CoiDisclosure disclosure = getCoiDisclosureById(disclosureId);
        if (disclosure != null) {
            List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();
            if (CollectionUtils.isNotEmpty(userRoles)) {
                String personId = null;
                for (CoiUserRole userRole : userRoles) {
                    if (StringUtils.equals(RoleConstants.COI_REVIEWER, userRole.getRoleName())) {
                        personId = getPersonId(userRole.getUserId());
                        if (StringUtils.equals(personId, principalId)) {
                            hasRole = true;
                            break;
                        }
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
        List<CoiDisclosure> disclosures = (List<CoiDisclosure>)getBusinessObjectService().findMatching(CoiDisclosure.class, params);
        if (CollectionUtils.isNotEmpty(disclosures)) {
            disclosure = disclosures.get(0);
        }
        
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
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    /*
     * Should override if derivedRoles should not to be cached.
     */
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        super.dynamicRoleMembership(namespaceCode, roleName);
        return true;
    }
}
