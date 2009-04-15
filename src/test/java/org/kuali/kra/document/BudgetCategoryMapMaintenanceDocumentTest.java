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
import org.kuali.core.document.MaintenanceDocumentBase;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.budget.bo.BudgetCategoryMap;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.test.SQLDataLoader;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetCategoryMapMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "BudgetCategoryMapMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from budget_category_maps where mapping_name='S2STEST' and target_category_CODE = '99'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update budget_category_maps set description = 'Computer Services' where mapping_name='S2S' and target_category_CODE = '82'");
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
    public void testCopyBudgetCategoryMapMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMapMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Maps");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"mappingName","S2S");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"categoryType","O");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"targetCategoryCode","82");
        HtmlPage searchPage = clickOn(budgetCategoryMapMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Budget Category Maps Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.financialDocumentDescription", "Budget Category Maps - copy test");

        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.mappingName", "S2STEST");
        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.categoryType", "O");
        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.targetCategoryCode", "99");
        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy budget category maps");
                
        HtmlPage routedPage = clickOn(budgetCategoryMapMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Budget Category Maps Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMap budgetCategoryMap = (BudgetCategoryMap)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMap.getCategoryType(),"O");
        assertEquals(budgetCategoryMap.getTargetCategoryCode(),"99");
        assertEquals(budgetCategoryMap.getMappingName(),"S2STEST");
        assertEquals(budgetCategoryMap.getDescription(),"test copy budget category maps");


    }

    @Test
    public void testEditBudgetCategoryMapMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMapMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Maps");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"mappingName","S2S");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"categoryType","O");
        setFieldValue(budgetCategoryMapMaintenanceLookupPage,"targetCategoryCode","82");
        HtmlPage searchPage = clickOn(budgetCategoryMapMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage budgetCategoryMapMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Budget Category Maps Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.financialDocumentDescription", "Budget Category Maps - edit test");

        setFieldValue(budgetCategoryMapMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit budget category maps");
                
        HtmlPage routedPage = clickOn(budgetCategoryMapMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Budget Category Maps Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMap budgetCategoryMap = (BudgetCategoryMap)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMap.getCategoryType(),"O");
        assertEquals(budgetCategoryMap.getTargetCategoryCode(),"82");
        assertEquals(budgetCategoryMap.getMappingName(),"S2S");
        assertEquals(budgetCategoryMap.getDescription(),"test edit budget category maps");

    }


    @Test
    public void testCreateNewBudgetCategoryMap() throws Exception {
        HtmlPage budgetCategoryMapMaintenancePage = getMaintenanceDocumentPage("Budget Category Maps","org.kuali.kra.budget.bo.BudgetCategoryMap","Kuali :: Budget Category Maps Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMapMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(budgetCategoryMapMaintenancePage,"Edit Budget Category Maps New * Mapping Name: * Target Category Code: Category Type: * Description:");

        setFieldValue(budgetCategoryMapMaintenancePage, "document.documentHeader.financialDocumentDescription", "Budget Category Maps - new test");

        setFieldValue(budgetCategoryMapMaintenancePage, "document.newMaintainableObject.mappingName", "S2STEST");
        setFieldValue(budgetCategoryMapMaintenancePage, "document.newMaintainableObject.categoryType", "O");
        setFieldValue(budgetCategoryMapMaintenancePage, "document.newMaintainableObject.targetCategoryCode", "99");
        setFieldValue(budgetCategoryMapMaintenancePage, "document.newMaintainableObject.description", "test new budget category maps");

        HtmlPage routedBudgetCategoryMapMaintenanceDocumentPage = clickOn(budgetCategoryMapMaintenancePage, "methodToCall.route", "Kuali :: Budget Category Maps Maintenance Document");
        
        assertContains(routedBudgetCategoryMapMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedBudgetCategoryMapMaintenanceDocumentPage,"New Mapping Name: S2STEST Target Category Code: 99 Category Type: O Description: test new budget category maps");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMap budgetCategoryMap = (BudgetCategoryMap)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMap.getCategoryType(),"O");
        assertEquals(budgetCategoryMap.getTargetCategoryCode(),"99");
        assertEquals(budgetCategoryMap.getMappingName(),"S2STEST");
        assertEquals(budgetCategoryMap.getDescription(),"test new budget category maps");
        
    }



}
