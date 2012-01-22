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
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.uif.RemotableAttributeError;
import org.kuali.rice.kns.kim.role.RoleTypeServiceBase;

public class UnitHierarchyRoleTypeServiceImpl extends RoleTypeServiceBase {
    
    private UnitService unitService;
    
    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    protected boolean roleQualifiedByAwardKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.AWARD);
    }
    
    protected boolean roleQualifiedByTimeAndMoneyKey(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.TIMEANDMONEY);
    }

    protected boolean performWildCardAwardMatching(Map<String,String> qualification, Map<String,String> roleQualifier) {
        String awardKeyFromroleQualifier = roleQualifier.get(KcKimAttributes.AWARD);
        String awardKeyFromroleQualification = qualification.get(KcKimAttributes.AWARD);
        
        if(StringUtils.isNotEmpty(awardKeyFromroleQualification) && awardKeyFromroleQualifier.startsWith(awardKeyFromroleQualification)) {
            return true;
        }
        //If necessary, we can include logic for other pattern matching later.
        //Not found necessary at this time.
        return false;
    }
    
    @Override
    public boolean performMatch(Map<String,String> qualification, Map<String,String> roleQualifier) {
        //Temp AuthZ fix for Awards Module
        if(roleQualifiedByAwardKey(roleQualifier)) {
            return (qualification.containsKey(KcKimAttributes.AWARD) && StringUtils.equals(qualification.get(KcKimAttributes.AWARD), 
                    roleQualifier.get(KcKimAttributes.AWARD)) || performWildCardAwardMatching(qualification, roleQualifier)); 
        } else if(roleQualifiedByTimeAndMoneyKey(roleQualifier)) {
            return (qualification.containsKey(KcKimAttributes.TIMEANDMONEY) && StringUtils.equals(qualification.get(KcKimAttributes.TIMEANDMONEY), 
                    roleQualifier.get(KcKimAttributes.TIMEANDMONEY)));
        } 
       //Temp AuthZ fix Ends here
        
        if (roleQualifiedByUnitHierarchy(roleQualifier) && qualification.containsKey(KcKimAttributes.UNIT_NUMBER)) {
            return (performWildCardMatching(qualification, roleQualifier) || unitQualifierMatches(qualification, roleQualifier) || unitQualifierMatchesHierarchy(qualification, roleQualifier));
        }
        
        return false; 
    }
    
    @Override
    public List<RemotableAttributeError> validateAttributes(String kimTypeId, Map<String, String> attributes) { 
        List<RemotableAttributeError> validationErrors = new ArrayList<RemotableAttributeError>();
        if(roleQualifiedByAwardKey(attributes) || roleQualifiedByTimeAndMoneyKey(attributes)) {
            return validationErrors;
        } else if(roleQualifiedByUnitHierarchy(attributes) && attributes.containsKey(KcKimAttributes.UNIT_NUMBER)) {
            validationErrors = super.validateAttributes(kimTypeId, attributes);
        }
        
        return validationErrors;
    }
    
    protected boolean roleQualifiedByUnitHierarchy(Map<String,String> roleQualifier) {
        return roleQualifier.containsKey(KcKimAttributes.UNIT_NUMBER) && roleQualifier.containsKey(KcKimAttributes.SUBUNITS);
    }
    
    protected boolean unitQualifierMatches(Map<String,String> qualification, Map<String,String> roleQualifier) {
        return StringUtils.equals(qualification.get(KcKimAttributes.UNIT_NUMBER), roleQualifier.get(KcKimAttributes.UNIT_NUMBER));
    }
    
    protected boolean unitQualifierMatchesHierarchy(Map<String,String> qualification, Map<String,String> roleQualifier) {
        boolean qualifierMatches = false;
        String unitNumber = qualification.get(KcKimAttributes.UNIT_NUMBER);
        
        while (!qualifierMatches) {
            final Unit unit = this.unitService.getUnit(unitNumber);
            if (unit == null) {
                break;
            }
            final String parentUnitNumber = unit.getParentUnitNumber();
            if (parentUnitNumber == null) {
                break;
            }
            qualifierMatches = ( StringUtils.equals(parentUnitNumber, roleQualifier.get(KcKimAttributes.UNIT_NUMBER)) && StringUtils.equalsIgnoreCase("Y", roleQualifier.get(KcKimAttributes.SUBUNITS)));
            unitNumber = parentUnitNumber;
        }
        
        return qualifierMatches;
    }
    
    protected boolean performWildCardMatching(Map<String,String> qualification, Map<String,String> roleQualifier) {
        if(qualification.get(KcKimAttributes.UNIT_NUMBER).equalsIgnoreCase("*")) {
            return true;
        }
        //If necessary, we can include logic for other pattern matching later.
        //Not found necessary at this time.
        return false;
    }
    
    @Override
    public List<String> getUniqueAttributes(String kimTypeId){
        return new ArrayList<String>();
    }    
    
}
