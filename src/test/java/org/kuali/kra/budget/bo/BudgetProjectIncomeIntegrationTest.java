/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.bo;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeComponent;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

public class BudgetProjectIncomeIntegrationTest extends BudgetDistributionAndIncomeIntegrationTest {

    private static final Integer BUDGET_PERIOD_1 = 1;
    private static final Integer BUDGET_PERIOD_2 = 2;
    private static final String DESCRIPTION_1 = "Budget Project Income 1";
    private static final String DESCRIPTION_2 = "Budget Project Income 2";
    private static final String DESCRIPTION_3 = "Budget Project Income 3";
    private static final KualiDecimal PROJECT_INCOME_1 = new KualiDecimal(1000.00);
    private static final KualiDecimal PROJECT_INCOME_2 = new KualiDecimal(2000.00);
    private static final KualiDecimal PROJECT_INCOME_3 = new KualiDecimal(3000.00);
    
    @Before
    public void setUp() throws Exception {
        super.setUp();        
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }

    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_BudgetPeriodNumber() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(null, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.getBudget().add(budgetProjectIncome);
        
        getDocumentService().saveDocument(budgetDocument);
    }
    
    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_Description() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_2, null);
        budgetDocument.getBudget().add(budgetProjectIncome);
        
        getDocumentService().saveDocument(budgetDocument);
    }

    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_ProjectIncome() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(BUDGET_PERIOD_2, null, DESCRIPTION_2);
        budgetDocument.getBudget().add(budgetProjectIncome);
        
        getDocumentService().saveDocument(budgetDocument);
    }
    
    private BudgetProjectIncome createBudgetProjectIncome(Integer budgetPeriodNumber, KualiDecimal projectIncome, String description) {
        BudgetProjectIncome budgetProjectIncome = new BudgetProjectIncome();
        budgetProjectIncome.setBudgetPeriodNumber(budgetPeriodNumber);
        budgetProjectIncome.setProjectIncome(projectIncome);
        budgetProjectIncome.setDescription(description);
        return budgetProjectIncome;
    }

    @Override
    protected void addBudgetDistributionAndIncomeComponent(BudgetDistributionAndIncomeComponent component) {
        BudgetProjectIncome budgetProjectIncome = (BudgetProjectIncome) component;
        budgetProjectIncome.setBudgetPeriodId(budgetDocument.getBudget().getBudgetPeriod(budgetProjectIncome.getBudgetPeriodNumber()-1).getBudgetPeriodId());
        budgetDocument.getBudget().add((BudgetProjectIncome) component);
        
    }

    @Override
    protected BudgetDistributionAndIncomeComponent[] createBudgetDistributionAndIncomeComponentCollection() {
        BudgetDistributionAndIncomeComponent[] projectIncomes = { createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1), 
                                                                  createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_2, DESCRIPTION_2),
                                                                  createBudgetProjectIncome(BUDGET_PERIOD_2, PROJECT_INCOME_3, DESCRIPTION_3) };
        return projectIncomes;
    }

    @Override
    protected List<? extends BudgetDistributionAndIncomeComponent> getBudgetDistributionAndIncomeComponents(BudgetDocument budgetDocument) {
        return budgetDocument.getBudget().getBudgetProjectIncomes();
    }

    @Override
    protected BudgetDistributionAndIncomeComponent createBudgetDistributionAndIncomeComponent() {
        return createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1);
    }
}
