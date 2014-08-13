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
