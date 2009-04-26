/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.budget.bo.BudgetCategoryMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BudgetCategoryMappingMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "BudgetCategoryMappingMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from budget_category_mapping where mapping_name='TEST' and target_category_CODE = '99'");
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
    public void testCopyBudgetCategoryMappingMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMappingMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Mapping");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"mappingName","NSF_194");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"budgetCategoryCode","10");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"targetCategoryCode","39");
        HtmlPage searchPage = clickOn(budgetCategoryMappingMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Budget Category Mapping Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Budget Category Mapping - copy test");

        setFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.mappingName", "TEST");
        setFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryCode", "10");
        setFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.targetCategoryCode", "99");
                
        HtmlPage routedPage = clickOn(budgetCategoryMappingMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Budget Category Mapping Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMapping budgetCategoryMapping = (BudgetCategoryMapping)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMapping.getBudgetCategoryCode(),"10");
        assertEquals(budgetCategoryMapping.getTargetCategoryCode(),"99");
        assertEquals(budgetCategoryMapping.getMappingName(),"TEST");


    }

    /**
     * 
     * The maintainable fields are all PKs.   SO, not sure whether we want this edit test.
     * We can not change anything.
     * @throws Exception
     */
    @Test
    public void testEditBudgetCategoryMappingMaintenanceDocument() throws Exception {
        HtmlPage budgetCategoryMappingMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Budget Category Mapping");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"mappingName","NSF_194");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"budgetCategoryCode","10");
        setFieldValue(budgetCategoryMappingMaintenanceLookupPage,"targetCategoryCode","39");
        HtmlPage searchPage = clickOn(budgetCategoryMappingMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage budgetCategoryMappingMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Budget Category Mapping Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(budgetCategoryMappingMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Budget Category Mapping - edit test");

                
        HtmlPage routedPage = clickOn(budgetCategoryMappingMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Budget Category Mapping Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMapping budgetCategoryMapping = (BudgetCategoryMapping)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMapping.getBudgetCategoryCode(),"10");
        assertEquals(budgetCategoryMapping.getTargetCategoryCode(),"39");
        assertEquals(budgetCategoryMapping.getMappingName(),"NSF_194");

    }


    @Test
    public void testCreateNewBudgetCategoryMapping() throws Exception {
        HtmlPage budgetCategoryMappingMaintenancePage = getMaintenanceDocumentPage("Budget Category Mapping","org.kuali.kra.budget.bo.BudgetCategoryMapping","Kuali :: Budget Category Mapping Maintenance Document");
        String documentNumber = getFieldValue(budgetCategoryMappingMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(budgetCategoryMappingMaintenancePage,"Edit Budget Category Mapping New * Budget Category Code: * Mapping Name: * Target Category Code:");

        setFieldValue(budgetCategoryMappingMaintenancePage, "document.documentHeader.documentDescription", "Budget Category Mapping - new test");

        setFieldValue(budgetCategoryMappingMaintenancePage, "document.newMaintainableObject.mappingName", "TEST");
        setFieldValue(budgetCategoryMappingMaintenancePage, "document.newMaintainableObject.budgetCategoryCode", "10");
        setFieldValue(budgetCategoryMappingMaintenancePage, "document.newMaintainableObject.targetCategoryCode", "99");

        HtmlPage routedBudgetCategoryMappingMaintenanceDocumentPage = clickOn(budgetCategoryMappingMaintenancePage, "methodToCall.route", "Kuali :: Budget Category Mapping Maintenance Document");
        
        assertContains(routedBudgetCategoryMappingMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedBudgetCategoryMappingMaintenanceDocumentPage,"New Budget Category Code: 10 Mapping Name: TEST Target Category Code: 99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        BudgetCategoryMapping budgetCategoryMapping = (BudgetCategoryMapping)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(budgetCategoryMapping.getBudgetCategoryCode(),"10");
        assertEquals(budgetCategoryMapping.getTargetCategoryCode(),"99");
        assertEquals(budgetCategoryMapping.getMappingName(),"TEST");
        
    }



}
