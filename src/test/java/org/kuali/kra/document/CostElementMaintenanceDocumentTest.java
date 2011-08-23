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

import org.junit.Test;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CostElementMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "CostElementMaintenanceDocument";
    private static final String LOOKUP_PAGE_TITLE = "Object Code";
    private static final String MAINTENANCE_PAGE_TITLE = "Kuali :: Cost Element Maintenance Document";
    
    private static final String COST_ELEMENT_1 = Long.toString(System.currentTimeMillis()%1000000);
    private static final String COST_ELEMENT_2 = Long.toString((System.currentTimeMillis()+1)%1000000);
    
    @Override
    public void tearDown() throws Exception {
//        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from COST_ELEMENT where cost_element = '999'");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("update COST_ELEMENT set description = 'Raw Materials' where cost_element = '420310'");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("commit");
//        sqlDataLoader.runSql();

        super.tearDown();
    }

    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    public void testCopyCostElementMaintenanceDocument() throws Exception {
        HtmlPage costElementMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(costElementMaintenanceLookupPage,"costElement","420310");
        setFieldValue(costElementMaintenanceLookupPage,"budgetCategoryCode","3");
        setFieldValue(costElementMaintenanceLookupPage,"onOffCampusFlag","N");
        HtmlPage searchPage = clickOn(costElementMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage costElementMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Cost Element - copy test");

        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.budgetCategoryCode", "3");
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy cost element");
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.costElement", COST_ELEMENT_1);
        // This is actually for on campus
        setFieldValue(costElementMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.onOffCampusFlag", "on");

        HtmlPage routedPage = clickOn(costElementMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CostElement costElement = (CostElement)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(costElement.getCostElement(),COST_ELEMENT_1);
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
        HtmlPage costElementMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(costElementMaintenanceLookupPage,"costElement","420310");
        setFieldValue(costElementMaintenanceLookupPage,"budgetCategoryCode","3");
        setFieldValue(costElementMaintenanceLookupPage,"onOffCampusFlag","N");
        HtmlPage searchPage = clickOn(costElementMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage costElementMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Cost Element - edit test");
        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit cost element");
        setFieldValue(costElementMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.onOffCampusFlag", "on");

        HtmlPage routedPage = clickOn(costElementMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
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
        HtmlPage costElementMaintenancePage = getMaintenanceDocumentPage(LOOKUP_PAGE_TITLE, "org.kuali.kra.budget.core.CostElement", MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(costElementMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(costElementMaintenancePage,"Edit Cost Element New * Object Code Name: Budget Category Code: * Description: * On/Off Campus Flag: unchecked");
        setFieldValue(costElementMaintenancePage, "document.documentHeader.documentDescription", "Cost Element - test");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.budgetCategoryCode", "3");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.description", "test new cost element");
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.costElement", COST_ELEMENT_2);
        setFieldValue(costElementMaintenancePage, "document.newMaintainableObject.onOffCampusFlag", "on");
        HtmlPage routedCostElementMaintenanceDocumentPage = clickOn(costElementMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedCostElementMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedCostElementMaintenanceDocumentPage,"New Object Code Name: " + COST_ELEMENT_2 + " Budget Category Code: 3 Description: test new cost element On/Off Campus Flag: Yes");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CostElement costElement = (CostElement)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(costElement.getCostElement(),COST_ELEMENT_2);
        assertEquals(costElement.getBudgetCategoryCode(),"3");
        assertEquals(costElement.getDescription(),"test new cost element");
        assertEquals(costElement.getOnOffCampusFlag(),true);
        
    }



}
