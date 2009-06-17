/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.kra.service.UnitService;

public class UnitMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
     
    /**
     * Constructs a UnitMaintenanceDocumentRule.java.
     */
    public UnitMaintenanceDocumentRule() {
        super();
    }

    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return moveUnit(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return moveUnit(document);
    }
    /**
     * 
     * Units cannot be moved to its own descendants.
     * This method returns false if it is moving to its own descendants.
     * @param maintenanceDocument
     * @return
     */
    private boolean moveUnit(MaintenanceDocument maintenanceDocument) {

        boolean valid = true;
        Unit unit=(Unit)maintenanceDocument.getNewMaintainableObject().getBusinessObject();
        String unitNumber=unit.getUnitNumber();
        String parentUnitNumber=unit.getParentUnitNumber();
        List<Unit> allSubUnits = KraServiceLocator.getService(UnitService.class).getAllSubUnits(unitNumber); 
        for (Unit subunits : allSubUnits) {  
            if(subunits.getUnitNumber().equals(parentUnitNumber)){
                GlobalVariables.getErrorMap().putError("document.newMaintainableObject.parentUnitNumber", KeyConstants.MOVE_UNIT_OWN_DESCENDANTS,
                        new String[] { unit.getParentUnitNumber(), unit.getParentUnitNumber() });
                valid=false;
            }
            
        }

        
    return valid;
}
}
