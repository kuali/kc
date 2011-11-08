/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase;

public class UnitRoleTypeServiceImpl extends KimRoleTypeServiceBase {

    @Override
    public AttributeSet validateAttributes(String kimTypeId, AttributeSet attributes) { 
        AttributeSet validationErrors = new AttributeSet();
        if(roleQualifiedByProposalKey(attributes) || 
                roleQualifiedByProtocolKey(attributes) || roleQualifiedByCommitteeKey(attributes) || 
                roleQualifiedByAwardKey(attributes) || roleQualifiedByAwardKey(attributes)) {
            return validationErrors;
        } else if(roleQualifiedByUnitOnly(attributes)) {
            validationErrors = super.validateAttributes(kimTypeId, attributes);
        }
        
        return validationErrors;
    }
    
    @Override
    public boolean performMatch(AttributeSet qualification, AttributeSet roleQualifier) {
        if(roleQualifiedByProposalKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.PROPOSAL) && StringUtils.equals(qualification.get(KcKimAttributes.PROPOSAL), 
                    roleQualifier.get(KcKimAttributes.PROPOSAL));
        } else if(roleQualifiedByProtocolKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.PROTOCOL) && StringUtils.equals(qualification.get(KcKimAttributes.PROTOCOL), 
                    roleQualifier.get(KcKimAttributes.PROTOCOL));
        } else if(roleQualifiedByCommitteeKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.COMMITTEE) && StringUtils.equals(qualification.get(KcKimAttributes.COMMITTEE), 
                    roleQualifier.get(KcKimAttributes.COMMITTEE));
        } else if(roleQualifiedByAwardKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.AWARD) && StringUtils.equals(qualification.get(KcKimAttributes.AWARD), 
                    roleQualifier.get(KcKimAttributes.AWARD));
        } else if(roleQualifiedByTimeAndMoneyKey(roleQualifier)) {
            return qualification.containsKey(KcKimAttributes.TIMEANDMONEY) && StringUtils.equals(qualification.get(KcKimAttributes.TIMEANDMONEY), 
                    roleQualifier.get(KcKimAttributes.TIMEANDMONEY));
        } else if(roleQualifiedByUnitOnly(roleQualifier)) {
            return (qualification.containsKey(KcKimAttributes.UNIT_NUMBER) &&  StringUtils.equals(qualification.get(KcKimAttributes.UNIT_NUMBER), 
                    roleQualifier.get(KcKimAttributes.UNIT_NUMBER)) || performWildCardMatching(qualification, roleQualifier));
        } 
        
        return false; 
    }

    protected boolean roleQualifiedByProposalKey(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.PROPOSAL);
    }
    
    protected boolean roleQualifiedByProtocolKey(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.PROTOCOL);
    }
    
    protected boolean roleQualifiedByCommitteeKey(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.COMMITTEE);
    }
    
    protected boolean roleQualifiedByAwardKey(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.AWARD);
    }
    
    protected boolean roleQualifiedByTimeAndMoneyKey(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.TIMEANDMONEY);
    }
    
    protected boolean roleQualifiedByUnitOnly(AttributeSet roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) && !roleQualifier.containsKey(KcKimAttributes.SUBUNITS);
    }
    
    protected boolean performWildCardMatching(AttributeSet qualification, AttributeSet roleQualifier) {
        if(qualification.containsKey(KcKimAttributes.UNIT_NUMBER) && qualification.get(KcKimAttributes.UNIT_NUMBER).equalsIgnoreCase("*") && roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) ) {
            return true;
        }
        //If necessary, we can include logic for other pattern matching later.
        //Not found necessary at this time.
        return false;
    }

    
    @Override
    public List<String> getQualifiersForExactMatch() {
        List<String> attributes = new ArrayList<String>();
        attributes.add(KcKimAttributes.PROPOSAL);
        attributes.add(KcKimAttributes.PROTOCOL);
        attributes.add(KcKimAttributes.COMMITTEE);
        attributes.add(KcKimAttributes.AWARD);
        attributes.add(KcKimAttributes.TIMEANDMONEY);
        attributes.add(KcKimAttributes.UNIT_NUMBER);
        return attributes; 
    }
    
    
}
