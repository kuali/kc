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
package org.kuali.kra.document;

import org.junit.Test;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetCategoryTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "BudgetCategoryTypeMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from budget_category_TYPE where budget_category_TYPE_CODE = 'A'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update budget_category_TYPE set description = 'Travel' where budget_category_TYPE_CODE = 'T'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("commit");
        sqlDataLoader.runSql();

        super.tearDown();
    }

    @Test
    public void testDocumentCreation() throws Exception {
        testDocumentCreation(DOCTYPE);
    }

    @Test
    public void testCopyBudgetCategoryTypeMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Type");
        setFieldValue(budgetCategoryTypeMaintenanceLookupPage,"budgetCategoryTypeCode","T");
        HtmlPage searchPage = clickOn(budgetCategoryTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Budget Category Type Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Budget Category Type - copy test");

        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryTypeCode", "A");
        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.sortId", "9");
        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy budget category type");
                
        HtmlPage routedPage = clickOn(budgetCategoryTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Budget Category Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryType budgetCategoryType = (BudgetCategoryType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryType.getBudgetCategoryTypeCode(),"A");
        assertEquals(budgetCategoryType.getSortId(),"9");
        assertEquals(budgetCategoryType.getDescription(),"test copy budget category type");


    }

    @Test
    public void testEditBudgetCategoryTypeMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Type");
        setFieldValue(budgetCategoryTypeMaintenanceLookupPage,"budgetCategoryTypeCode","T");
        HtmlPage searchPage = clickOn(budgetCategoryTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage budgetCategoryTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Budget Category Type Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Budget Category Type - edit test");

        setFieldValue(budgetCategoryTypeMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit budget category type");
                
        HtmlPage routedPage = clickOn(budgetCategoryTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Budget Category Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryType budgetCategoryType = (BudgetCategoryType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryType.getBudgetCategoryTypeCode(),"T");
        assertEquals(budgetCategoryType.getDescription(),"test edit budget category type");

    }


    @Test
    public void testCreateNewBudgetCategoryType() throws Exception {
        HtmlPage budgetCategoryTypeMaintenancePage = getMaintenanceDocumentPage("Budget Category Type","org.kuali.kra.proposaldevelopment.budget.bo.BudgetCategoryType","Kuali :: Budget Category Type Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(budgetCategoryTypeMaintenancePage,"Edit Budget Category Types New * Budget Category Type: * Description: Sort Id:");
        setFieldValue(budgetCategoryTypeMaintenancePage, "document.documentHeader.documentDescription", "Budget Category Type - test");
        setFieldValue(budgetCategoryTypeMaintenancePage, "document.newMaintainableObject.budgetCategoryTypeCode", "A");
        setFieldValue(budgetCategoryTypeMaintenancePage, "document.newMaintainableObject.sortId", "9");
        setFieldValue(budgetCategoryTypeMaintenancePage, "document.newMaintainableObject.description", "test new budget category type");
        HtmlPage routedBudgetCategoryTypeMaintenanceDocumentPage = clickOn(budgetCategoryTypeMaintenancePage, "methodToCall.route", "Kuali :: Budget Category Type Maintenance Document");
        
        assertContains(routedBudgetCategoryTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedBudgetCategoryTypeMaintenanceDocumentPage,"New Budget Category Type: A Description: test new budget category type Sort Id: 9");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryType budgetCategoryType = (BudgetCategoryType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryType.getBudgetCategoryTypeCode(),"A");
        assertEquals(budgetCategoryType.getSortId(),"9");
        assertEquals(budgetCategoryType.getDescription(),"test new budget category type");
        
    }

    
}
