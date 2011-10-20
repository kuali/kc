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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;


public class ProtocolAffiliateTypeDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {
    private static final org.apache.log4j.Logger LOG = 
        org.apache.log4j.Logger.getLogger(ProtocolAffiliateTypeDerivedRoleTypeServiceImpl.class);
    
    protected List<String> requiredAttributes = new ArrayList<String>();
    {
        requiredAttributes.add(KcKimAttributes.PROTOCOL);
    }
    
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);

        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();

        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);       
        Protocol protocol = getProtocol(protocolNumber);
        
        if (protocol != null && CollectionUtils.isNotEmpty(protocol.getProtocolPersons())) {
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                if (StringUtils.equals(getAffiliationType(person.getAffiliationTypeCode()), roleName) &&
                    StringUtils.isNotBlank(person.getPerson().getPersonId())) {
                    members.add(new RoleMembershipInfo(null, null, person.getPerson().getPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null));
    
                }
            }
        }
        
        return members;
    }
    
    @Override
    public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, String roleName,
            AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        
        String protocolNumber = qualification.get(KcKimAttributes.PROTOCOL);
        
        Protocol protocol = getProtocol(protocolNumber);

        if (protocol != null && CollectionUtils.isNotEmpty(protocol.getProtocolPersons())) {
            for (ProtocolPerson person : protocol.getProtocolPersons()) {
                //Find protocol person that matches the principal id
                if (StringUtils.equals(principalId, person.getPersonId())) {
                    if (StringUtils.equals(roleName, getAffiliationType(person.getAffiliationTypeCode()))) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private Protocol getProtocol(String protocolNumber) {
        Map<String,Object> keymap = new HashMap<String,Object>();
        keymap.put("protocolNumber", protocolNumber);
        return (Protocol) getBusinessObjectService().findByPrimaryKey(Protocol.class, keymap);
    }

    private String getAffiliationType(Integer affiliationTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("affiliationTypeCode", affiliationTypeCode.toString());
        List<AffiliationType> affiliationTypes = 
            (List<AffiliationType>) getBusinessObjectService().findMatching(AffiliationType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(affiliationTypes)) {
            result = affiliationTypes.get(0).getDescription();
        }
        
        return result;        
    }
    
}
