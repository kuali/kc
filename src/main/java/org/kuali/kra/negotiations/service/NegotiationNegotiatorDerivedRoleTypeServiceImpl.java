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
package org.kuali.kra.negotiations.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

/**
 * Negotiation Derived Role for the Negotiator of each Negotiation. 
 */
public class NegotiationNegotiatorDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {
    
    /**
     * Constructs a NegotiationPersonDerivedRoleTypeServiceImpl.java.
     */
    public NegotiationNegotiatorDerivedRoleTypeServiceImpl() {
        requiredAttributes.add(KcKimAttributes.NEGOTIATION);
    }   
    
    @Override
    public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        
        String negotiationId = qualification.get(KcKimAttributes.NEGOTIATION);
        List<RoleMembershipInfo> members = new ArrayList<RoleMembershipInfo>();
        
        if (StringUtils.isNotBlank(negotiationId)) {
            Negotiation negotiation = getBusinessObjectService().findBySinglePrimaryKey(Negotiation.class, negotiationId);
            if (negotiation != null) {
                if (StringUtils.isNotBlank(negotiation.getNegotiatorPersonId())) {
                    members.add(new RoleMembershipInfo(null, null, negotiation.getNegotiatorPersonId(), Role.PRINCIPAL_MEMBER_TYPE, null));
                }
            }
        }
            
        return members;
    }
}
