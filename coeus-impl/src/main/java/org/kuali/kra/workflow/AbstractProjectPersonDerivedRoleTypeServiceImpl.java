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
package org.kuali.kra.workflow;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractProjectPersonDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    protected abstract List<? extends AbstractProjectPerson> getProjectPersons(Map<String, String> qualification);
    
    /**
     * Filter the list of negotiation persons by their role. Typically the role name
     * is used to indicate PI, COI or KP. If the role name does not match any known
     * role the list is not filtered.
     */
    protected List<? extends AbstractProjectPerson> filterListByRole(List<? extends AbstractProjectPerson> persons, String roleName) {
        List<AbstractProjectPerson> newPersons = new ArrayList<AbstractProjectPerson>();
        if (StringUtils.equals(roleName, Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(roleName, Constants.CO_INVESTIGATOR_ROLE)
                || StringUtils.equals(roleName, Constants.KEY_PERSON_ROLE)) {
            for(AbstractProjectPerson person : persons) {
                if (StringUtils.equals(person.getRoleCode(), roleName)) {
                    newPersons.add(person);
                }
            }
        }
        return newPersons;
    }

    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembership> members = new ArrayList<RoleMembership>();
        List<? extends AbstractProjectPerson> projectPersons = getProjectPersons(qualification);
        String subQualification = qualification.get(KcKimAttributes.SUB_QUALIFIER);
        
        if (projectPersons != null && !projectPersons.isEmpty()) {
            if (!StringUtils.equals(roleName, Constants.ALL_INVESTIGATORS) && !StringUtils.equals(roleName, Constants.PRINCIPAL_INVESTIGATOR)) {
                projectPersons = filterListByRole(projectPersons, roleName);
            }

            if (StringUtils.isNotBlank(subQualification)) {
                projectPersons = filterListByRole(projectPersons, subQualification);
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
    
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        return true;
    }
}
