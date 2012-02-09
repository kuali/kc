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
package org.kuali.kra.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public abstract class AbstractProjectPersonDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    protected abstract List<? extends AbstractProjectPerson> getProjectPersons(Map<String, String> qualification);
    
    /**
     * Filter the list of negotiation persons by their role. Typically the role name
     * is used to indicate PI, COI or KP. If the role name does not match any known
     * role the list is not filtered.
     * @param persons
     * @param roleName
     */
    protected void filterListByRole(List<? extends AbstractProjectPerson> persons, String roleName) {
        if (StringUtils.equals(roleName, Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(roleName, Constants.CO_INVESTIGATOR_ROLE)
                || StringUtils.equals(roleName, Constants.KEY_PERSON_ROLE)) {
            Iterator<? extends AbstractProjectPerson> iter = persons.iterator();
            while (iter.hasNext()) {
                AbstractProjectPerson person = iter.next();
                if (!StringUtils.equals(person.getRoleCode(), roleName)) {
                    iter.remove();
                }
            }
        }
        
    }

    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembership> members = new ArrayList<RoleMembership>();
        List<? extends AbstractProjectPerson> projectPersons = getProjectPersons(qualification);
        String subQualification = qualification.get(KcKimAttributes.SUB_QUALIFIER);
        
        if (projectPersons != null && !projectPersons.isEmpty()) {
            filterListByRole(projectPersons, roleName);
            if (StringUtils.isNotBlank(subQualification)) {
                filterListByRole(projectPersons, subQualification);
            }
            for (AbstractProjectPerson proposalPerson : projectPersons) {
                if (proposalPerson.getPerson() != null) {
                    members.add( RoleMembership.Builder.create(null, null, proposalPerson.getPerson().getPersonId(), MemberType.PRINCIPAL, null).build() );
                }
            }
        }
            
        return members;
    }

    @Override
    public boolean hasDerivedRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification){
        List<RoleMembership> members = getRoleMembersFromDerivedRole(namespaceCode, roleName, qualification);
        for (RoleMembership member : members) {
            if (StringUtils.equals(member.getMemberId(), principalId)) {
                return true;
            }
        }
        return false;
    }
}
