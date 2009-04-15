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
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.test.SQLDataLoader;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RateClassTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "RateClassTypeMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from RATE_CLASS_TYPE where RATE_CLASS_TYPE = 'A'");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update RATE_CLASS_TYPE set description = 'OTHER' where RATE_CLASS_TYPE = 'X'");
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
    public void testCopyRateClassTypeMaintenanceDocument() throws Exception {

        HtmlPage rateClassTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Class Type");
        setFieldValue(rateClassTypeMaintenanceLookupPage,"rateClassType","X");
        HtmlPage searchPage = clickOn(rateClassTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage rateClassTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Rate Class Type Maintenance Document");
        String documentNumber = getFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.financialDocumentDescription", "Rate Class Type - copy test");

        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassType", "A");
        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.sortId", "9");
        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.prefixActivityType", "on");
        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy rate class type");
                
        HtmlPage routedPage = clickOn(rateClassTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Rate Class Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateClassType rateClassType = (RateClassType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateClassType.getRateClassType(),"A");
        assertEquals(rateClassType.getSortId(),"9");
        assertEquals(rateClassType.getPrefixActivityType(),true);
        assertEquals(rateClassType.getDescription(),"test copy rate class type");


    }

    @Test
    public void testEditRateClassTypeMaintenanceDocument() throws Exception {
        HtmlPage rateClassTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Class Type");
        setFieldValue(rateClassTypeMaintenanceLookupPage,"rateClassType","X");
        HtmlPage searchPage = clickOn(rateClassTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage rateClassTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Rate Class Type Maintenance Document");
        String documentNumber = getFieldValue(rateClassTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.financialDocumentDescription", "Rate Class Type - edit test");

        setFieldValue(rateClassTypeMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit rate class type");
                
        HtmlPage routedPage = clickOn(rateClassTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Rate Class Type Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateClassType rateClassType = (RateClassType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateClassType.getRateClassType(),"X");
        assertEquals(rateClassType.getSortId(),"7");
        assertEquals(rateClassType.getPrefixActivityType(),false);
        assertEquals(rateClassType.getDescription(),"test edit rate class type");

    }


    @Test
    public void testCreateNewRateClassType() throws Exception {
        HtmlPage rateClassTypeMaintenancePage = getMaintenanceDocumentPage("Rate Class Type","org.kuali.kra.budget.bo.RateClassType","Kuali :: Rate Class Type Maintenance Document");
        String documentNumber = getFieldValue(rateClassTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(rateClassTypeMaintenancePage,"Edit Rate Class Types New * Rate Class Type: * Description: * Prefix Activity Type?: unchecked Sort Id:");
        setFieldValue(rateClassTypeMaintenancePage, "document.documentHeader.financialDocumentDescription", "Rate Class Type - test");
        setFieldValue(rateClassTypeMaintenancePage, "document.newMaintainableObject.rateClassType", "A");
        setFieldValue(rateClassTypeMaintenancePage, "document.newMaintainableObject.sortId", "9");
        setFieldValue(rateClassTypeMaintenancePage, "document.newMaintainableObject.prefixActivityType", "on");
        setFieldValue(rateClassTypeMaintenancePage, "document.newMaintainableObject.description", "test new rate class type");
        HtmlPage routedRateClassTypeMaintenanceDocumentPage = clickOn(rateClassTypeMaintenancePage, "methodToCall.route", "Kuali :: Rate Class Type Maintenance Document");
        
        assertContains(routedRateClassTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedRateClassTypeMaintenanceDocumentPage,"New Rate Class Type: A Description: test new rate class type Prefix Activity Type?: Yes Sort Id: 9");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        RateClassType rateClassType = (RateClassType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(rateClassType.getRateClassType(),"A");
        assertEquals(rateClassType.getSortId(),"9");
        assertEquals(rateClassType.getPrefixActivityType(),true);
        assertEquals(rateClassType.getDescription(),"test new rate class type");
        
    }



}
