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
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class InstituteLaRateMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "InstituteLaRateMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from institute_la_rates where RATE_CLASS_CODE = '12' and RATE_TYPE_CODE = '1' and FISCAL_YEAR = '2012' and start_date = to_date ('12/31/2011','MM/DD?YYYY')");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update institute_la_rates set rate = 0.0 where RATE_CLASS_CODE = '12' and RATE_TYPE_CODE = '1' and unit_number = '000001' and FISCAL_YEAR = '2003' and start_date = to_date ('07/01/2002','MM/DD?YYYY') and on_off_campus_flag = 'F'");
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
    public void testCopyInstituteLaRateMaintenanceDocument() throws Exception {
        HtmlPage instituteLaRateMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Institute La Rate");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"rateClassCode", "12");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"rateTypeCode", "1");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"fiscalYear", "2003");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"onOffCampusFlag", "F");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"unitNumber", "000001");
        HtmlPage searchPage = clickOn(instituteLaRateMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage instituteLaRateMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Institute La Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Institute La Rate - copy test");

        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "12");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateTypeCode", "1");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.fiscalYear", "2012");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.startDate", "12/31/2011");
        // This is actually for on campus
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.onOffCampusFlag", "on");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.unitNumber", "000001");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.instituteRate", "9.9");

                
        HtmlPage routedPage = clickOn(instituteLaRateMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Institute La Rates Maintenance Document");        

        assertDoesNotContain(routedPage,"error(s) found on page");
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteLaRate instituteLaRate = (InstituteLaRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteLaRate.getRateClassCode(),"12");
        assertEquals(instituteLaRate.getRateTypeCode(),"1");
        assertEquals(instituteLaRate.getFiscalYear(),"2012");
        assertEquals(instituteLaRate.getOnOffCampusFlag(),true);
        assertEquals(instituteLaRate.getUnitNumber(),"000001");
        assertEquals(instituteLaRate.getStartDate().toString(),"2011-12-31");
        assertEquals(instituteLaRate.getInstituteRate().toString(),"9.90");


    }

    @Test
    public void testEditInstituteLaRateMaintenanceDocument() throws Exception {
        HtmlPage instituteLaRateMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Institute La Rate");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"rateClassCode", "12");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"rateTypeCode", "1");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"fiscalYear", "2003");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"onOffCampusFlag", "F");
        setFieldValue(instituteLaRateMaintenanceLookupPage,"unitNumber", "000001");
        HtmlPage searchPage = clickOn(instituteLaRateMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage instituteLaRateMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Institute La Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteLaRateMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Institute La Rates - edit test");
        setFieldValue(instituteLaRateMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.instituteRate", "9.9");

                
        HtmlPage routedPage = clickOn(instituteLaRateMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Institute La Rates Maintenance Document");
        
        assertDoesNotContain(routedPage,"error(s) found on page");
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteLaRate instituteLaRate = (InstituteLaRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteLaRate.getRateClassCode(),"12");
        assertEquals(instituteLaRate.getRateTypeCode(),"1");
        assertEquals(instituteLaRate.getFiscalYear(),"2003");
        assertEquals(instituteLaRate.getOnOffCampusFlag(),false);
        assertEquals(instituteLaRate.getUnitNumber(),"000001");
        assertEquals(instituteLaRate.getStartDate().toString(),"2002-07-01");
        assertEquals(instituteLaRate.getInstituteRate().toString(),"9.90");

    }


    @Test
    public void testCreateNewInstituteLaRate() throws Exception {
        HtmlPage instituteLaRateMaintenancePage = getMaintenanceDocumentPage("Institute La Rate","org.kuali.kra.bo.InstituteLaRate","Kuali :: Institute La Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteLaRateMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(instituteLaRateMaintenancePage,"Edit Institute La Rates New * Fiscal Year: * On/Off Campus Flag: unchecked * Rate Class Code: * Rate Type Code: * Start Date: * Unit Number: * Rate: ");
        setFieldValue(instituteLaRateMaintenancePage, "document.documentHeader.documentDescription", "Institute La Rates - test");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.rateClassCode", "12");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.rateTypeCode", "1");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.fiscalYear", "2012");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.startDate", "12/31/2011");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.onOffCampusFlag", "on");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.unitNumber", "000001");
        setFieldValue(instituteLaRateMaintenancePage, "document.newMaintainableObject.instituteRate", "9.9");
        HtmlPage routedInstituteLaRateMaintenanceDocumentPage = clickOn(instituteLaRateMaintenancePage, "methodToCall.route", "Kuali :: Institute La Rates Maintenance Document");
        
        assertDoesNotContain(routedInstituteLaRateMaintenanceDocumentPage,"error(s) found on page");
        assertContains(routedInstituteLaRateMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedInstituteLaRateMaintenanceDocumentPage,"New Fiscal Year: 2012 On/Off Campus Flag: Yes Rate Class Code: 12 Rate Type Code: 1 Start Date: 12/31/2011 Unit Number: 000001 Rate: 9.90 ");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteLaRate instituteLaRate = (InstituteLaRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteLaRate.getRateClassCode(),"12");
        assertEquals(instituteLaRate.getRateTypeCode(),"1");
        assertEquals(instituteLaRate.getFiscalYear(),"2012");
        assertEquals(instituteLaRate.getOnOffCampusFlag(),true);
        assertEquals(instituteLaRate.getUnitNumber(),"000001");
        assertEquals(instituteLaRate.getStartDate().toString(),"2011-12-31");
        assertEquals(instituteLaRate.getInstituteRate().toString(),"9.90");
        
    }


}
