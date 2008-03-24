/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetProjectIncomeIntegrationTest extends BudgetDistributionAndIncomeIntegrationTest {

    private static final Integer BUDGET_PERIOD_1 = 1;
    private static final Integer BUDGET_PERIOD_2 = 2;
    private static final Integer BUDGET_PERIOD_3 = 3;
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
        GlobalVariables.setUserSession(null);
    }

//    @Test
//    public void testSaveProjectIncome_MultipleInstances() throws Exception {
//        BudgetProjectIncome[] projectIncomes = createBudgetProjectIncomeCollection();
//        for(BudgetProjectIncome projectIncome: projectIncomes) {
//            budgetDocument.add(projectIncome);
//        }
//        
//        documentService.saveDocument(budgetDocument);
//        
//        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//        assertNotNull(savedDocument);        
//        assertEquals(projectIncomes.length, savedDocument.getBudgetProjectIncomes().size());
//    }
//    
//    @Test
//    public void testDeleteProjectIncome_MultipleInstances() throws Exception {
//        BudgetProjectIncome[] projectIncomes = createBudgetProjectIncomeCollection();
//        for(BudgetProjectIncome projectIncome: projectIncomes) {
//            budgetDocument.add(projectIncome);
//        }
//        
//        documentService.saveDocument(budgetDocument);
//        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//        
//        for(int i = 0, expectedSize = 2; i < projectIncomes.length; i++, expectedSize--) {
//            savedDocument.remove(projectIncomes[0]);
//            documentService.saveDocument(budgetDocument);
//            savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//            assertEquals(expectedSize, savedDocument.getBudgetProjectIncomes().size());
//        }        
//    }

    @Test
    public void testDeleteProjectIncome_SingleInstance() throws Exception {
        BudgetProjectIncome projectIncome = createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.add(projectIncome);        
        documentService.saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertEquals(1, savedDocument.getBudgetProjectIncomes().size());
        
        savedDocument.remove(projectIncome);        
        documentService.saveDocument(savedDocument);
        
        savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(0, savedDocument.getBudgetProjectIncomes().size());
    }
    
    @Test
    public void testSave_SingleInstance() throws Exception {
        BudgetProjectIncome projectIncome = createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.add(projectIncome);
        documentService.saveDocument(budgetDocument);

        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetProjectIncomes().size());        
    }

    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_BudgetPeriodNumber() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(null, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.add(budgetProjectIncome);
        
        documentService.saveDocument(budgetDocument);
    }
    
    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_ProjectIncome() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(BUDGET_PERIOD_2, null, DESCRIPTION_2);
        budgetDocument.add(budgetProjectIncome);
        
        documentService.saveDocument(budgetDocument);
    }
    
    @Test(expected=ValidationException.class)
    public void testSave_MissingRequiredField_Description() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(BUDGET_PERIOD_3, PROJECT_INCOME_2, null);
        budgetDocument.add(budgetProjectIncome);
        
        documentService.saveDocument(budgetDocument);
    }
    
    private BudgetProjectIncome createBudgetProjectIncome(Integer budgetPeriodNumber, KualiDecimal projectIncome, String description) {
        BudgetProjectIncome budgetProjectIncome = new BudgetProjectIncome();
        budgetProjectIncome.setBudgetPeriodNumber(budgetPeriodNumber);
        budgetProjectIncome.setProjectIncome(projectIncome);
        budgetProjectIncome.setDescription(description);
        return budgetProjectIncome;
    }

//    private BudgetProjectIncome[] createBudgetProjectIncomeCollection() {
//        BudgetProjectIncome[] projectIncomes = { createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1), 
//                                                    createBudgetProjectIncome(BUDGET_PERIOD_2, PROJECT_INCOME_2, DESCRIPTION_2),
//                                                    createBudgetProjectIncome(BUDGET_PERIOD_3, PROJECT_INCOME_3, DESCRIPTION_3) };
//        return projectIncomes;
//    }
}
