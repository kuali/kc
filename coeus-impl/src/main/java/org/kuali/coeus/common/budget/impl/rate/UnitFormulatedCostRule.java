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
package org.kuali.coeus.common.budget.impl.rate;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.budget.impl.rate.UnitFormulatedCost;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnitFormulatedCostRule extends KcMaintenanceDocumentRuleBase {


    public UnitFormulatedCostRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkForUnitCostExistence(document);
    }
    /**
     * 
     * This method is to check the existence of terms of the Sponsor Template.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkForUnitCostExistence(MaintenanceDocument maintenanceDocument) {
        boolean valid = true;
        UnitFormulatedCost newCost = (UnitFormulatedCost) maintenanceDocument.getNewMaintainableObject().getDataObject();

        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("formulatedTypeCode", newCost.getFormulatedTypeCode());
        values.put("unitNumber", newCost.getUnitNumber());
        Collection<UnitFormulatedCost> costs = businessObjectService.findMatching(UnitFormulatedCost.class, values);
        for (UnitFormulatedCost cost : costs) {
            if (!ObjectUtils.equals(newCost.getUnitFormulatedCostId(), cost.getUnitFormulatedCostId())) {
                ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
                errorReporter.reportError("document.newMaintainableObject.formulatedTypeCode",
                        KeyConstants.ERROR_FORUMLATED_COST_DUPLICATE);
                return false;
            }
        }
        return true;
    }
    
}
