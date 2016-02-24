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
