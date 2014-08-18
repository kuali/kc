/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.unit.admin;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the business rules for adding a new Unit Administrator.
 */
public class UnitAdministratorMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return validateMultipleUnitAdministratorTypes(document);
    }
    
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
                if (StringUtils.equals(newUnitAdministratorType.getCode(), unitAdministrator.getUnitAdministratorTypeCode())) {
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
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

}