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
package org.kuali.kra.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Defines the business rules for adding a new Unit Administrator.
 */
public class UnitAdministratorMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {

    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return validateMultipleUnitAdministratorTypes(document);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return validateMultipleUnitAdministratorTypes(document);
    }
    
    /**
     * Validates whether the Unit Administrator Type of the new Unit Administrator allows multiple Unit Administrators to be associated with the Type.
     * @param document
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean validateMultipleUnitAdministratorTypes(MaintenanceDocument document) {
        boolean isValid = true;
        
        UnitAdministrator newUnitAdministrator = (UnitAdministrator) document.getNewMaintainableObject().getDataObject();
        Unit newUnit = newUnitAdministrator.getUnit();
        UnitAdministratorType newUnitAdministratorType = newUnitAdministrator.getUnitAdministratorType();
        
        // skip validation if the object is being deleted.  
        if (document.getNewMaintainableObject().getMaintenanceAction().equals("Delete") ||
           (document.getOldMaintainableObject().getMaintenanceAction().equals("Delete")))
        {
            return true;
        }
       
        if (!newUnitAdministratorType.getMultiplesFlag()) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("unitNumber", newUnit.getUnitNumber());
            Collection<UnitAdministrator> unitAdministrators = getBusinessObjectService().findMatching(UnitAdministrator.class, fieldValues);
            
            for (UnitAdministrator unitAdministrator : unitAdministrators) {
                if (StringUtils.equals(newUnitAdministratorType.getUnitAdministratorTypeCode(), unitAdministrator.getUnitAdministratorTypeCode())) {
                    isValid = false;
                    GlobalVariables.getMessageMap().putError("document.newMaintainableObject.unitAdministratorTypeCode", 
                            KeyConstants.UNIT_ADMINISTRATOR_MULTIPLE_TYPES_NOT_ALLOWED, newUnitAdministratorType.getDescription());
                    break;
                }
            }
        }
        
        return isValid;
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

}