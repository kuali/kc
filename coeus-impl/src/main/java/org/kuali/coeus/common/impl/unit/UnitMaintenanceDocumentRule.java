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
package org.kuali.coeus.common.impl.unit;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

public class UnitMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
     

    public UnitMaintenanceDocumentRule() {
        super();
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return moveUnit(document);
    }
    
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
        Unit unit=(Unit)maintenanceDocument.getNewMaintainableObject().getDataObject();
        String unitNumber=unit.getUnitNumber();
        String parentUnitNumber=unit.getParentUnitNumber();
        List<Unit> allSubUnits = KcServiceLocator.getService(UnitService.class).getAllSubUnits(unitNumber);
        for (Unit subunits : allSubUnits) {  
            if(subunits.getUnitNumber().equals(parentUnitNumber)){
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.parentUnitNumber", KeyConstants.MOVE_UNIT_OWN_DESCENDANTS,
                        new String[] { unit.getParentUnitNumber(), unit.getParentUnitNumber() });
                valid=false;
            }
            
        }

        
    return valid;
}
}
