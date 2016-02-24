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
package org.kuali.kra.subaward.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubAwardRequisitionerDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembership> members = new ArrayList<RoleMembership>();
        String subAwardId = qualification.get(KcKimAttributes.SUBAWARD);
        if (subAwardId != null && subAwardId.matches("\\d+")) {
            SubAward subAward = getBusinessObjectService().findBySinglePrimaryKey(SubAward.class, Long.parseLong(subAwardId));
            if (subAward != null) {
                members.add(RoleMembership.Builder.create(null, null, subAward.getRequisitionerId(), MemberType.PRINCIPAL, null).build());
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
