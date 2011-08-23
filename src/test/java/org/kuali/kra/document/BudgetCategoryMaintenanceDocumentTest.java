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
package org.kuali.kra.document;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetCategoryMaintenanceDocumentTest extends MaintenanceDocumentTestBase {
    private static final String LOOKUP_PAGE_TITLE = "Budget Category";
    private static final String MAINTENANCE_PAGE_TITLE = "Kuali :: Budget Category Maintenance Document";
    private static final String DOCTYPE = "BudgetCategoryMaintenanceDocument";
    private static final String BUDGET_CATEGORY_CODE_1 = Long.toString(System.currentTimeMillis()%1000);
    private static final String BUDGET_CATEGORY_CODE_2 = Long.toString((System.currentTimeMillis()+1)%1000);
    @Override
    public void tearDown() throws Exception {
//        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from BUDGET_CATEGORY where budget_category_CODE = '99'");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("update BUDGET_CATEGORY set description = 'Duplicating' where budget_category_CODE = '10'");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("commit");
//        sqlDataLoader.runSql();

        super.tearDown();
    }

    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    @Ignore
    public void testCopyBudgetCategoryMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(budgetCategoryMaintenanceLookupPage,"budgetCategoryCode","10");
        setFieldValue(budgetCategoryMaintenanceLookupPage,"budgetCategoryTypeCode","O");
        HtmlPage searchPage = clickOn(budgetCategoryMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage budgetCategoryMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Budget Category - copy test");

        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryCode", BUDGET_CATEGORY_CODE_1);
        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryTypeCode", "O");
        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy budget category");
                
        HtmlPage routedPage = clickOn(budgetCategoryMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategory budgetCategory = (BudgetCategory)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategory.getBudgetCategoryCode(),BUDGET_CATEGORY_CODE_1);
        assertEquals(budgetCategory.getBudgetCategoryTypeCode(),"O");
        assertEquals(budgetCategory.getDescription(),"test copy budget category");


    }

    @Test
    public void testEditBudgetCategoryMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(budgetCategoryMaintenanceLookupPage,"budgetCategoryCode","10");
        setFieldValue(budgetCategoryMaintenanceLookupPage,"budgetCategoryTypeCode","O");
             HtmlPage searchPage = clickOn(budgetCategoryMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage budgetCategoryMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(budgetCategoryMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Budget Category - edit test");

        setFieldValue(budgetCategoryMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit budget category");
                
        HtmlPage routedPage = clickOn(budgetCategoryMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategory budgetCategory = (BudgetCategory)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategory.getBudgetCategoryCode(),"10");
        assertEquals(budgetCategory.getBudgetCategoryTypeCode(),"O");
        assertEquals(budgetCategory.getDescription(),"test edit budget category");

    }


    @Test
    @Ignore
    public void testCreateNewBudgetCategory() throws Exception {
        HtmlPage budgetCategoryMaintenancePage = getMaintenanceDocumentPage(LOOKUP_PAGE_TITLE,"org.kuali.kra.budget.core.BudgetCategory",MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(budgetCategoryMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(budgetCategoryMaintenancePage,"Edit Budget Category New * Budget Category Code: Category Type: * Description:");
        setFieldValue(budgetCategoryMaintenancePage, "document.documentHeader.documentDescription", "Budget Category - test");
        setFieldValue(budgetCategoryMaintenancePage, "document.newMaintainableObject.budgetCategoryCode", BUDGET_CATEGORY_CODE_2);
        setFieldValue(budgetCategoryMaintenancePage, "document.newMaintainableObject.budgetCategoryTypeCode", "O");
        setFieldValue(budgetCategoryMaintenancePage, "document.newMaintainableObject.description", "test new budget category");
        HtmlPage routedBudgetCategoryMaintenanceDocumentPage = clickOn(budgetCategoryMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedBudgetCategoryMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedBudgetCategoryMaintenanceDocumentPage,"New Budget Category Code: "+BUDGET_CATEGORY_CODE_2+" Category Type: O Description: test new budget category");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategory budgetCategory = (BudgetCategory)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategory.getBudgetCategoryCode(),BUDGET_CATEGORY_CODE_2);
        assertEquals(budgetCategory.getBudgetCategoryTypeCode(),"O");
        assertEquals(budgetCategory.getDescription(),"test new budget category");
        
    }


}
