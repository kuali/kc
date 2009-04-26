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
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class InstituteRateMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "InstituteRateMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from institute_rates where RATE_CLASS_CODE = '4' and RATE_TYPE_CODE = '2' and ACTIVITY_TYPE_CODE = '1' and FISCAL_YEAR = '2012' and start_date = to_date ('12/31/2011','MM/DD?YYYY')");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update institute_rates set rate = 10.0 where RATE_CLASS_CODE = '4' and RATE_TYPE_CODE = '2' and ACTIVITY_TYPE_CODE = '1' and FISCAL_YEAR = '1980' and start_date = to_date ('07/01/1979','MM/DD?YYYY') and on_off_campus_flag = 'F'");
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
    public void testCopyInstituteRateMaintenanceDocument() throws Exception {
        HtmlPage instituteRateMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Institute Rate");
        setFieldValue(instituteRateMaintenanceLookupPage,"activityTypeCode","1");
        setFieldValue(instituteRateMaintenanceLookupPage,"rateClassCode", "4");
        setFieldValue(instituteRateMaintenanceLookupPage,"rateTypeCode", "2");
        setFieldValue(instituteRateMaintenanceLookupPage,"fiscalYear", "1980");
        setFieldValue(instituteRateMaintenanceLookupPage,"onOffCampusFlag", "F");
        HtmlPage searchPage = clickOn(instituteRateMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage instituteRateMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Institute Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Institute Rate - copy test");

        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.activityTypeCode", "1");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "4");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateTypeCode", "2");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.fiscalYear", "2012");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.startDate", "12/31/2011");
        // This is actually for on campus
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.onOffCampusFlag", "on");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.unitNumber", "000001");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.instituteRate", "9.9");

                
        HtmlPage routedPage = clickOn(instituteRateMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Institute Rates Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteRate instituteRate = (InstituteRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteRate.getActivityTypeCode(),"1");
        assertEquals(instituteRate.getRateClassCode(),"4");
        assertEquals(instituteRate.getRateTypeCode(),"2");
        assertEquals(instituteRate.getFiscalYear(),"2012");
        assertEquals(instituteRate.getOnOffCampusFlag(),true);
        assertEquals(instituteRate.getUnitNumber(),"000001");
        assertEquals(instituteRate.getStartDate().toString(),"2011-12-31");
        assertEquals(instituteRate.getInstituteRate().toString(),"9.90");


    }

    @Test
    public void testEditInstituteRateMaintenanceDocument() throws Exception {
        HtmlPage instituteRateMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Institute Rate");
        setFieldValue(instituteRateMaintenanceLookupPage,"activityTypeCode","1");
        setFieldValue(instituteRateMaintenanceLookupPage,"rateClassCode", "4");
        setFieldValue(instituteRateMaintenanceLookupPage,"rateTypeCode", "2");
        setFieldValue(instituteRateMaintenanceLookupPage,"fiscalYear", "1980");
        setFieldValue(instituteRateMaintenanceLookupPage,"onOffCampusFlag", "F");
        HtmlPage searchPage = clickOn(instituteRateMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage instituteRateMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Institute Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteRateMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(instituteRateMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Institute Rates - edit test");
        setFieldValue(instituteRateMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.instituteRate", "9.9");

                
        HtmlPage routedPage = clickOn(instituteRateMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Institute Rates Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteRate instituteRate = (InstituteRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteRate.getActivityTypeCode(),"1");
        assertEquals(instituteRate.getRateClassCode(),"4");
        assertEquals(instituteRate.getRateTypeCode(),"2");
        assertEquals(instituteRate.getFiscalYear(),"1980");
        assertEquals(instituteRate.getOnOffCampusFlag(),false);
        assertEquals(instituteRate.getUnitNumber(),"000001");
        assertEquals(instituteRate.getStartDate().toString(),"1979-07-01");
        assertEquals(instituteRate.getInstituteRate().toString(),"9.90");

    }


    @Test
    public void testCreateNewInstituteRate() throws Exception {
        HtmlPage instituteRateMaintenancePage = getMaintenanceDocumentPage("Institute Rate","org.kuali.kra.bo.InstituteRate","Kuali :: Institute Rates Maintenance Document");
        String documentNumber = getFieldValue(instituteRateMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(instituteRateMaintenancePage,"Edit Institute Rates New * Activity Type Code: * Fiscal Year: * On/Off Campus Flag: unchecked * Rate Class Code: * Rate Type Code: * Start Date: * Unit Number: * Rate: ");
        setFieldValue(instituteRateMaintenancePage, "document.documentHeader.documentDescription", "Institute Rates - test");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.activityTypeCode", "1");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.rateClassCode", "4");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.rateTypeCode", "2");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.fiscalYear", "2012");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.startDate", "12/31/2011");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.onOffCampusFlag", "on");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.unitNumber", "000001");
        setFieldValue(instituteRateMaintenancePage, "document.newMaintainableObject.instituteRate", "9.9");
        HtmlPage routedInstituteRateMaintenanceDocumentPage = clickOn(instituteRateMaintenancePage, "methodToCall.route", "Kuali :: Institute Rates Maintenance Document");
        
        assertContains(routedInstituteRateMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedInstituteRateMaintenanceDocumentPage,"New Activity Type Code: 1 Fiscal Year: 2012 On/Off Campus Flag: Yes Rate Class Code: 4 Rate Type Code: 2 Start Date: 12/31/2011 Unit Number: 000001 Rate: 9.90 ");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        InstituteRate instituteRate = (InstituteRate)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(instituteRate.getActivityTypeCode(),"1");
        assertEquals(instituteRate.getRateClassCode(),"4");
        assertEquals(instituteRate.getRateTypeCode(),"2");
        assertEquals(instituteRate.getFiscalYear(),"2012");
        assertEquals(instituteRate.getOnOffCampusFlag(),true);
        assertEquals(instituteRate.getUnitNumber(),"000001");
        assertEquals(instituteRate.getStartDate().toString(),"2011-12-31");
        assertEquals(instituteRate.getInstituteRate().toString(),"9.90");
        
    }



}
