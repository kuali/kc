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
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CostElementMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "CostElementMaintenanceDocument";
    private static final String CE_MAINT_TITLE = "Object Code";
    
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from cost_element where cost_element = '999'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update cost_element set description = 'Raw Materials' where cost_element = '420310'");
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
    public void testCopyCostElementMaintenanceDocument() throws Exception {
        HtmlPage costElementMaintenanceLookupPage = getMaintenanceDocumentLookupPage(CE_MAINT_TITLE);
        setFieldValue(costElementMaintenanceLookupPage,"costElement","420310");
        setFieldValue(costElementMaintenanceLookupPage,"budgetCategoryCode","3");
        setFieldValue(costElementMaintenanceLookupPage,"onOffCampusFlag","N");
        HtmlPage searchPage = clickOn(costElementMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage costElementMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Cost Element Maintenance Document");
        String documentNumber = getFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Cost Element - copy test");

        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryCode", "3");
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy cost element");
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.costElement", "999");
        // This is actually for on campus
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.onOffCampusFlag", "on");

        HtmlPage routedPage = clickOn(costElementMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Cost Element Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CostElement costElement = (CostElement)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(costElement.getCostElement(),"999");
        assertEquals(costElement.getBudgetCategoryCode(),"3");
        assertEquals(costElement.getDescription(),"test copy cost element");
        assertEquals(costElement.getOnOffCampusFlag(),true);


    }

    /**
     * 
     * Can't edit any field because all are PKs.
     * @throws Exception
     */
    @Test
    public void testEditCostElementMaintenanceDocument() throws Exception {
        HtmlPage costElementMaintenanceLookupPage = getMaintenanceDocumentLookupPage(CE_MAINT_TITLE);
        setFieldValue(costElementMaintenanceLookupPage,"costElement","420310");
        setFieldValue(costElementMaintenanceLookupPage,"budgetCategoryCode","3");
        setFieldValue(costElementMaintenanceLookupPage,"onOffCampusFlag","N");
        HtmlPage searchPage = clickOn(costElementMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage costElementMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Cost Element Maintenance Document");
        String documentNumber = getFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Cost Element - edit test");
        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit cost element");
        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.onOffCampusFlag", "on");

        HtmlPage routedPage = clickOn(costElementMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Cost Element Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CostElement costElement = (CostElement)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(costElement.getCostElement(),"420310");
        assertEquals(costElement.getBudgetCategoryCode(),"3");
        assertEquals(costElement.getDescription(),"test edit cost element");
        assertEquals(costElement.getOnOffCampusFlag(),true);

    }


    @Test
    public void testCreateNewCostElementMaintenanceDocument() throws Exception {
        HtmlPage costElementMaintenancePage = getMaintenanceDocumentPage(CE_MAINT_TITLE, "org.kuali.kra.proposaldevelopment.budget.bo.CostElement", "Kuali :: Cost Element Maintenance Document");
        String documentNumber = getFieldValue(costElementMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(costElementMaintenancePage,"Edit Cost Element New * Object Code Name: Budget Category Code: * Description: * On/Off Campus Flag: unchecked");
        setFieldValue(costElementMaintenancePage, "document.documentHeader.documentDescription", "Cost Element - test");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.budgetCategoryCode", "3");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.description", "test new cost element");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.costElement", "999");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.onOffCampusFlag", "on");
        HtmlPage routedCostElementMaintenanceDocumentPage = clickOn(costElementMaintenancePage, "methodToCall.route", "Kuali :: Cost Element Maintenance Document");
        
        assertContains(routedCostElementMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedCostElementMaintenanceDocumentPage,"New Object Code Name: 999 Budget Category Code: 3 Description: test new cost element On/Off Campus Flag: Yes");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CostElement costElement = (CostElement)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(costElement.getCostElement(),"999");
        assertEquals(costElement.getBudgetCategoryCode(),"3");
        assertEquals(costElement.getDescription(),"test new cost element");
        assertEquals(costElement.getOnOffCampusFlag(),true);
        
    }



}
