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
package org.kuali.kra.iacuc.kim.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.personnel.IacucProtocolAffiliationType;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

import java.util.*;
import java.util.stream.Collectors;


public class IacucProtocolAffiliateTypeDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {

    protected List<String> requiredAttributes = new ArrayList<>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembership> members = new ArrayList<>();

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);       
        IacucProtocol protocol = getProtocol(protocolNumber);
        
        if (protocol != null && CollectionUtils.isNotEmpty(protocol.getProtocolPersons())) {
            members.addAll(protocol.getProtocolPersons().stream()
                    .filter(person ->
                            StringUtils.equals(getAffiliationType(person.getAffiliationTypeCode()), roleName) &&
                                    StringUtils.isNotBlank(person.getPerson().getPersonId()))
                    .map(person -> RoleMembership.Builder.create(null, null, person.getPerson().getPersonId(), MemberType.PRINCIPAL, null).build())
                    .collect(Collectors.toList()));
        }
        
        return members;
    }
    
    @Override
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        
        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        IacucProtocol protocol = getProtocol(protocolNumber);

        if (protocol != null && CollectionUtils.isNotEmpty(protocol.getProtocolPersons())) {
            for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
                //Find protocol person that matches the principal id
                if (StringUtils.equals(principalId, person.getPersonId())) {
                    if (StringUtils.equals(roleName, getAffiliationType(person.getAffiliationType().getAffiliationTypeCode()))) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private IacucProtocol getProtocol(String protocolNumber) {
        return getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, Collections.singletonMap("protocolNumber", protocolNumber));
    }

    private String getAffiliationType(Integer affiliationTypeCode) {
        String result = null;
        
        if (affiliationTypeCode != null) {
            Collection<IacucProtocolAffiliationType> affiliationTypes =
                    getBusinessObjectService().findMatching(IacucProtocolAffiliationType.class, Collections.singletonMap("affiliationTypeCode", affiliationTypeCode.toString()));
            if (CollectionUtils.isNotEmpty(affiliationTypes)) {
                result = affiliationTypes.iterator().next().getDescription();
            }
        }
        return result;        
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
