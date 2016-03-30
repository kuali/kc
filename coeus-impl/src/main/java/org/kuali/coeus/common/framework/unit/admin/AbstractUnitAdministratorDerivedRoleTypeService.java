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
package org.kuali.coeus.common.framework.unit.admin;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractUnitAdministratorDerivedRoleTypeService extends DerivedRoleTypeServiceBase {
    
    public abstract List<? extends AbstractUnitAdministrator> getUnitAdministrators(Map<String, String> qualifiers);
    
    @Override
    public boolean hasDerivedRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String,String> qualification) {
        
        for (RoleMembership roleMember : getRoleMembersFromDerivedRole(namespaceCode, roleName, qualification)) {
            if (StringUtils.equals(roleMember.getMemberId(), principalId)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        List<RoleMembership> members = new ArrayList<RoleMembership>();

        String unitAdminTypeCode = getUnitAdministratorTypeCode(qualification, roleName);
        return getUnitAdministrators(qualification).stream().filter(unitAdministrator -> {
        	return StringUtils.isNotBlank(unitAdministrator.getPersonId()) &&
                    (StringUtils.isBlank(unitAdminTypeCode) || StringUtils.equals(unitAdministrator.getUnitAdministratorTypeCode(), unitAdminTypeCode));
        }).map(unitAdmin -> { return unitAdmin.getPersonId(); })
        .distinct()
        .map(adminId -> { 
        	return RoleMembership.Builder.create(null, null, adminId, MemberType.PRINCIPAL, null).build(); 
        }).collect(Collectors.toList());

    }

    /**
     * Default implementation takes a qualifications map and returns the sub qualifier from the qualifications
     * @return can return null if none is configured. In this case it will be assumed all unit admins should be returned for the given units
     */
	protected String getUnitAdministratorTypeCode(Map<String, String> qualifications, String roleName) {
		return qualifications.get(KcKimAttributes.SUB_QUALIFIER);
	}
    
    @Override
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        return true;
    }    
}
