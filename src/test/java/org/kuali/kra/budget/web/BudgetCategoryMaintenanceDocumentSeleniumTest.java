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
package org.kuali.kra.budget.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Budget Category maintenance document.
 */
public class BudgetCategoryMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Budget Category";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Budget Category Maintenance Document";
    
    private static final String BUDGET_CATEGORY_CODE_ID = "budgetCategoryCode";
    private static final String BUDGET_CATEGORY_TYPE_CODE_ID = "budgetCategoryTypeCode";
    private static final String DESCRIPTION_ID = "description";
        
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Budget Category - Test Create";
    private static final String CREATE_DESCRIPTION = "Test Create Budget Category";
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Budget Category - Test Edit";
    private static final String EDIT_DESCRIPTION_1 = "Test Edit Budget Category (Before)";
    private static final String EDIT_DESCRIPTION_2 = "Test Edit Budget Category (After)";
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Budget Category - Test Copy";
    private static final String COPY_DESCRIPTION_1 = "Test Copy Budget Category (Before)";
    private static final String COPY_DESCRIPTION_2 = "Test Copy Budget Category (After)";
    
    private static final String DEFAULT_BUDGET_CATEGORY_TYPE_CODE = "O";
        
    private MaintenanceDocumentSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = MaintenanceDocumentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test creating a budget category.
     *
     * @throws Exception
     */
    @Test
    public void testCreateBudgetCategory() throws Exception {
        String budgetCategoryCode = getNewBudgetCategoryCode();
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, budgetCategoryCode, CREATE_DESCRIPTION);
        
        verifyExistingMaintenanceDocument(documentNumber, budgetCategoryCode, CREATE_DESCRIPTION);
        
    }
    
    /**
     * Test editing a budget category.
     *
     * @throws Exception
     */
    @Test
    public void testEditBudgetCategory() throws Exception {
        String budgetCategoryCode = getNewBudgetCategoryCode();
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, budgetCategoryCode, EDIT_DESCRIPTION_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(BUDGET_CATEGORY_CODE_ID, budgetCategoryCode);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, BudgetCategory.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(DESCRIPTION_ID, EDIT_DESCRIPTION_2);
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, budgetCategoryCode, EDIT_DESCRIPTION_2);
    }

    /**
     * Test copying a budget category.
     *
     * @throws Exception
     */
    @Test
    public void testCopyBudgetCategory() throws Exception {
        String budgetCategoryCode1 = getNewBudgetCategoryCode();
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, budgetCategoryCode1, COPY_DESCRIPTION_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(BUDGET_CATEGORY_CODE_ID, budgetCategoryCode1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, BudgetCategory.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String budgetCategoryCode2 = getNewBudgetCategoryCode();
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(BUDGET_CATEGORY_CODE_ID, budgetCategoryCode2);
        fieldValues.put(DESCRIPTION_ID, COPY_DESCRIPTION_2);
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, budgetCategoryCode2, COPY_DESCRIPTION_2);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param budgetCategoryCode the budget category code
     * @param description the description
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String budgetCategoryCode, String description) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, BudgetCategory.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(BUDGET_CATEGORY_CODE_ID, budgetCategoryCode);
        fieldValues.put(BUDGET_CATEGORY_TYPE_CODE_ID, DEFAULT_BUDGET_CATEGORY_TYPE_CODE);
        fieldValues.put(DESCRIPTION_ID, description);
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param budgetCategoryCode the budget category code
     * @param description the description
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String budgetCategoryCode, String description) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        BudgetCategory budgetCategory = (BudgetCategory) document.getNewMaintainableObject().getDataObject();
        assertEquals(budgetCategoryCode, budgetCategory.getBudgetCategoryCode());
        assertEquals(DEFAULT_BUDGET_CATEGORY_TYPE_CODE, budgetCategory.getBudgetCategoryTypeCode());
        assertEquals(description, budgetCategory.getDescription());
    }
    
    /**
     * Create a new unique budget category code.
     * 
     * @return a new unique budget category code
     */
    @SuppressWarnings("unchecked")
    private String getNewBudgetCategoryCode() {
        int maxBudgetCategoryCode = 1;
        Collection<BudgetCategory> budgetCategories = getBusinessObjectService().findAll(BudgetCategory.class);
        for (BudgetCategory budgetCategory : budgetCategories) {
            int budgetCategoryCode = NumberUtils.toInt(budgetCategory.getBudgetCategoryCode(), 1);
            if (budgetCategoryCode > maxBudgetCategoryCode) {
                maxBudgetCategoryCode = budgetCategoryCode;
            }
        }
        
        return String.valueOf(maxBudgetCategoryCode + 1);
    }

}