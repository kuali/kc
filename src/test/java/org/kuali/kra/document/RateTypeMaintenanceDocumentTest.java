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
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RateTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "RateTypeMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from RATE_TYPE where RATE_TYPE_CODE = '99' and RATE_CLASS_CODE='10'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update RATE_TYPE set description = 'Lab Allocation - Salaries' where RATE_TYPE_CODE = '1' and RATE_CLASS_CODE='10'");
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
    public void testCopyRateTypeMaintenanceDocument() throws Exception {
        HtmlPage rateTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Type");
        setFieldValue(rateTypeMaintenanceLookupPage,"rateClassCode","10");
        setFieldValue(rateTypeMaintenanceLookupPage,"rateTypeCode","1");
        HtmlPage searchPage = clickOn(rateTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage rateTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Rate Type Maintenance Document");
        String documentNumber = getFieldValue(rateTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(rateTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Rate Type - copy test");

        setFieldValue(rateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "10");
        setFieldValue(rateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateTypeCode", "99");
        setFieldValue(rateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy rate type");
                
        HtmlPage routedPage = clickOn(rateTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Rate Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateType rateType = (RateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateType.getRateClassCode(),"10");
        assertEquals(rateType.getRateTypeCode(),"99");
        assertEquals(rateType.getDescription(),"test copy rate type");


    }

    @Test
    public void testEditRateTypeMaintenanceDocument() throws Exception {
        HtmlPage rateTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Type");
        setFieldValue(rateTypeMaintenanceLookupPage,"rateClassCode","10");
        setFieldValue(rateTypeMaintenanceLookupPage,"rateTypeCode","1");
        HtmlPage searchPage = clickOn(rateTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage rateTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Rate Type Maintenance Document");
        String documentNumber = getFieldValue(rateTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(rateTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Rate Type - edit test");

        setFieldValue(rateTypeMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit rate type");
                
        HtmlPage routedPage = clickOn(rateTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Rate Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateType rateType = (RateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateType.getRateClassCode(),"10");
        assertEquals(rateType.getRateTypeCode(),"1");
        assertEquals(rateType.getDescription(),"test edit rate type");

    }


    @Test
    public void testCreateNewRateType() throws Exception {
        HtmlPage rateTypeMaintenancePage = getMaintenanceDocumentPage("Rate Type","org.kuali.kra.budget.bo.RateType","Kuali :: Rate Type Maintenance Document");
        String documentNumber = getFieldValue(rateTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(rateTypeMaintenancePage,"Edit Rate Type New * Rate Class Code: * Rate Type Code: * Description: ");
        setFieldValue(rateTypeMaintenancePage, "document.documentHeader.documentDescription", "Rate Type - test");
        setFieldValue(rateTypeMaintenancePage, "document.newMaintainableObject.rateClassCode", "10");
        setFieldValue(rateTypeMaintenancePage, "document.newMaintainableObject.rateTypeCode", "99");
        setFieldValue(rateTypeMaintenancePage, "document.newMaintainableObject.description", "test new rate type");
        HtmlPage routedRateTypeMaintenanceDocumentPage = clickOn(rateTypeMaintenancePage, "methodToCall.route", "Kuali :: Rate Type Maintenance Document");
        
        assertContains(routedRateTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedRateTypeMaintenanceDocumentPage,"New Rate Class Code: 10 Rate Type Code: 99 Description: test new rate type");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateType rateType = (RateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateType.getRateClassCode(),"10");
        assertEquals(rateType.getRateTypeCode(),"99");
        assertEquals(rateType.getDescription(),"test new rate type");
        
    }



}
