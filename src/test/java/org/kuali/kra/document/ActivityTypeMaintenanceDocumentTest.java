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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ActivityTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {
    private static final String LOOKUP_PAGE_TITLE = "Activity Type";
    private static final String MAINTENANCE_PAGE_TITLE = "Kuali :: Activity Type Maintenance Document";
    
    private static final String ACTIVITY_TYPE_CODE_1 = Long.toString(System.currentTimeMillis()%1000);
    private static final String ACTIVITY_TYPE_CODE_2 = Long.toString((System.currentTimeMillis()+2)%1002);

    @Override
    public void tearDown() throws Exception {

        super.tearDown();
    }


    private static final String DOCTYPE = "ActivityTypeMaintenanceDocument";

    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    public void testCopyActivityTypeMaintenanceDocument() throws Exception {
        HtmlPage activityTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(activityTypeMaintenanceLookupPage,"activityTypeCode","1");
        HtmlPage searchPage = clickOn(activityTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage activityTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(activityTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(activityTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Activity Type - copy test");

        setFieldValue(activityTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.activityTypeCode", ACTIVITY_TYPE_CODE_1);
        setFieldValue(activityTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy activity type");
                
        HtmlPage routedPage = clickOn(activityTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ActivityType activityType = (ActivityType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(activityType.getActivityTypeCode(),ACTIVITY_TYPE_CODE_1);
        assertEquals(activityType.getDescription(),"test copy activity type");


    }

    @Test
    public void testEditActivityTypeMaintenanceDocument() throws Exception {
        HtmlPage activityTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(activityTypeMaintenanceLookupPage,"activityTypeCode","1");
        HtmlPage searchPage = clickOn(activityTypeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage activityTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(activityTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(activityTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Activity Type - edit test");

        setFieldValue(activityTypeMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit activity type");
                
        HtmlPage routedPage = clickOn(activityTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ActivityType activityType = (ActivityType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(activityType.getActivityTypeCode(),"1");
        assertEquals(activityType.getDescription(),"test edit activity type");

    }


    @Test
    public void testCreateNewActivityType() throws Exception {
        HtmlPage activityTypeMaintenancePage = getMaintenanceDocumentPage(LOOKUP_PAGE_TITLE,"org.kuali.kra.proposaldevelopment.bo.ActivityType",MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(activityTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(activityTypeMaintenancePage,"Edit Activity Types New * Activity Type: * Description: ");
        setFieldValue(activityTypeMaintenancePage, "document.documentHeader.documentDescription", "Activity Type - test");
        setFieldValue(activityTypeMaintenancePage, "document.newMaintainableObject.activityTypeCode", ACTIVITY_TYPE_CODE_2);
        setFieldValue(activityTypeMaintenancePage, "document.newMaintainableObject.description", "test new activity type");
        setFieldValue(activityTypeMaintenancePage, "document.newMaintainableObject.higherEducationFunctionCode", "IPR");
        HtmlPage routedActivityTypeMaintenanceDocumentPage = clickOn(activityTypeMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedActivityTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(routedActivityTypeMaintenanceDocumentPage,"New Activity Type: " + ACTIVITY_TYPE_CODE_2 + " Description: test new activity type");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        ActivityType activityType = (ActivityType)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(activityType.getActivityTypeCode(),ACTIVITY_TYPE_CODE_2);
        assertEquals(activityType.getDescription(),"test new activity type");

    }
}