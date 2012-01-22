/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.budget.core.BudgetCategoryMapping;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.rice.kns.document.MaintenanceDocument;

public class BudgetCategoryExistenceRule extends KraMaintenanceDocumentRuleBase {
    

    public BudgetCategoryExistenceRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }

    /**
     * 
     * This method is to check the existence of budgetcategorycode in table.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkExistence(MaintenanceDocument maintenanceDocument) {


        boolean valid= true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        // shared by budgetcategorymapping & costelement
        // TODO : refactoring this - must have a better way to handle this sharing
        String budgetCategoryCode;
        if (maintenanceDocument.getNewMaintainableObject().getDataObject() instanceof BudgetCategoryMapping) {
            BudgetCategoryMapping budgetCategoryMapping = (BudgetCategoryMapping) maintenanceDocument.getNewMaintainableObject().getDataObject();
            budgetCategoryCode=budgetCategoryMapping.getBudgetCategoryCode();
        } else {
            CostElement costElement = (CostElement) maintenanceDocument.getNewMaintainableObject().getDataObject();
            budgetCategoryCode=costElement.getBudgetCategoryCode();
            
        }
        Map pkMap = new HashMap();
        pkMap.put("budgetCategoryCode", budgetCategoryCode);
        valid=checkExistenceFromTable(BudgetCategory.class,pkMap,"budgetCategoryCode", "Budget Category");


        return valid;

    }
    

}
