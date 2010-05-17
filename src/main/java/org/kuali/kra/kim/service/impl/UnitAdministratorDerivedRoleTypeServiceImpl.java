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

import java.util.List;

import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimRoleTypeService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

/**
 * Checks whether the principal is a Unit Administrator for the given unit.
 */
public class UnitAdministratorDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase implements KimRoleTypeService {
    
    private UnitService unitService;
    
    @Override
    public boolean hasApplicationRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification) {
        
        String unitNumber = qualification.get(KcKimAttributes.UNIT_NUMBER);
        
        List<UnitAdministrator> unitAdministrators = unitService.retrieveUnitAdministratorsByUnitNumber(unitNumber);
        for (UnitAdministrator unitAdministrator : unitAdministrators) {
            if (unitAdministrator.getPersonId().equals(principalId)) {
                return true;
            }
        }
        
        return false;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
}
