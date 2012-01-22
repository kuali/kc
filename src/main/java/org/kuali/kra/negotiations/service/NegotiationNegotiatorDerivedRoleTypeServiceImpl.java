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
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

/**
 * Negotiation Derived Role for the Negotiator of each Negotiation. 
 */
public class NegotiationNegotiatorDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    
    /**
     * Constructs a NegotiationPersonDerivedRoleTypeServiceImpl.java.
     */
    public NegotiationNegotiatorDerivedRoleTypeServiceImpl() {
    }   
    
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String,String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        
        String negotiationId = qualification.get(KcKimAttributes.NEGOTIATION);
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        
        if (StringUtils.isNotBlank(negotiationId)) {
            Negotiation negotiation = getBusinessObjectService().findBySinglePrimaryKey(Negotiation.class, negotiationId);
            if (negotiation != null) {
                if (StringUtils.isNotBlank(negotiation.getNegotiatorPersonId())) {
                    members.add(RoleMembership.Builder.create(null, null, negotiation.getNegotiatorPersonId(), MemberType.PRINCIPAL, null).build());
                }
            }
        }
            
        return members;
    }
    @Override
    protected List<String> getRequiredAttributes() {
        List<String> requiredAttributes = new ArrayList<String>();
        requiredAttributes.add(KcKimAttributes.NEGOTIATION);
        return requiredAttributes;
    }
}
