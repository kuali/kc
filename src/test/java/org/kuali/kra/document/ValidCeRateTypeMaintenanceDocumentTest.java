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
import org.kuali.kra.budget.rates.ValidCeRateType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ValidCeRateTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "ValidCeRateTypeMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from valid_ce_RATE_TYPEs where cost_element= '421925' and RATE_TYPE_CODE = '4' and RATE_CLASS_CODE='5'");
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
    public void testCopyValidCeRateTypeMaintenanceDocument() throws Exception {
        HtmlPage validCeRateTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Valid Cost Element Rate Type");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"costElement","421925");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"rateClassCode","10");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"rateTypeCode","1");
        HtmlPage searchPage = clickOn(validCeRateTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage validCeRateTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Valid Cost Element Rate Types Maintenance Document");
        String documentNumber = getFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Valid Cost Element Rate Type - copy test");

        setFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "5");
        setFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateTypeCode", "4");
        setFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.costElement", "421925");
                
        HtmlPage routedPage = clickOn(validCeRateTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Valid Cost Element Rate Types Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ValidCeRateType validCeRateType = (ValidCeRateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCeRateType.getRateClassCode(),"5");
        assertEquals(validCeRateType.getRateTypeCode(),"4");
        assertEquals(validCeRateType.getCostElement(),"421925");


    }

    /**
     * 
     * Can't edit any field because all are PKs.
     * @throws Exception
     */
    @Test
    public void testEditValidCeRateTypeMaintenanceDocument() throws Exception {
        HtmlPage validCeRateTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Valid Cost Element Rate Type");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"costElement","421925");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"rateClassCode","10");
        setFieldValue(validCeRateTypeMaintenanceLookupPage,"rateTypeCode","1");
        HtmlPage searchPage = clickOn(validCeRateTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage validCeRateTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Valid Cost Element Rate Types Maintenance Document");
        String documentNumber = getFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(validCeRateTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Valid Cost Element Rate Type - edit test");
                
        HtmlPage routedPage = clickOn(validCeRateTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Valid Cost Element Rate Types Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ValidCeRateType validCeRateType = (ValidCeRateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCeRateType.getRateClassCode(),"10");
        assertEquals(validCeRateType.getRateTypeCode(),"1");
        assertEquals(validCeRateType.getCostElement(),"421925");

    }


    @Test
    public void testCreateNewValidCeRateTypeMaintenanceDocument() throws Exception {
        HtmlPage validCeRateTypeMaintenancePage = getMaintenanceDocumentPage("Valid Cost Element Rate Type","org.kuali.kra.proposaldevelopment.budget.bo.ValidCeRateType","Kuali :: Valid Cost Element Rate Types Maintenance Document");
        String documentNumber = getFieldValue(validCeRateTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(validCeRateTypeMaintenancePage,"Edit Valid Cost Element Rate Types New * Cost Element: * Rate Class Code: * Rate Type Code:");
        setFieldValue(validCeRateTypeMaintenancePage, "document.documentHeader.documentDescription", "Valid Cost Element Rate Type - test");
        setFieldValue(validCeRateTypeMaintenancePage, "document.newMaintainableObject.rateClassCode", "5");
        setFieldValue(validCeRateTypeMaintenancePage, "document.newMaintainableObject.rateTypeCode", "4");
        setFieldValue(validCeRateTypeMaintenancePage, "document.newMaintainableObject.costElement", "421925");
        HtmlPage routedValidCeRateTypeMaintenanceDocumentPage = clickOn(validCeRateTypeMaintenancePage, "methodToCall.route", "Kuali :: Valid Cost Element Rate Types Maintenance Document");
        
        assertContains(routedValidCeRateTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedValidCeRateTypeMaintenanceDocumentPage,"New Cost Element: 421925 Rate Class Code: 5 Rate Type Code: 4");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ValidCeRateType validCeRateType = (ValidCeRateType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCeRateType.getRateClassCode(),"5");
        assertEquals(validCeRateType.getRateTypeCode(),"4");
        assertEquals(validCeRateType.getCostElement(),"421925");
        
    }


}
