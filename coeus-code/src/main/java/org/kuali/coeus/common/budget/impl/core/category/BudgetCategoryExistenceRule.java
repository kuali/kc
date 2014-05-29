/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.core.category;

import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryMapping;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.HashMap;
import java.util.Map;

public class BudgetCategoryExistenceRule extends KcMaintenanceDocumentRuleBase {
    

    public BudgetCategoryExistenceRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkExistence(document);
    }
    
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
        String budgetCategoryCode;
        if (maintenanceDocument.getNewMaintainableObject().getDataObject() instanceof BudgetCategoryMapping) {
            BudgetCategoryMapping budgetCategoryMapping = (BudgetCategoryMapping) maintenanceDocument.getNewMaintainableObject().getDataObject();
            budgetCategoryCode=budgetCategoryMapping.getBudgetCategoryCode();
        } else {
            CostElement costElement = (CostElement) maintenanceDocument.getNewMaintainableObject().getDataObject();
            budgetCategoryCode=costElement.getBudgetCategoryCode();
            
        }
        Map pkMap = new HashMap();
        pkMap.put("code", budgetCategoryCode);
        valid=checkExistenceFromTable(BudgetCategory.class,pkMap,"code", "Budget Category");


        return valid;

    }
    

}
