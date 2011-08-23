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
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CustomAttributeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {
    private static final String LOOKUP_PAGE_TITLE = "Custom Attribute";
    private static final String MAINTENANCE_PAGE_TITLE = "Kuali :: CustomAttribute Maintenance Document";
    private static final String DOCTYPE = "CustomAttributeMaintenanceDocument";
    private static final String ID_1 = Long.toString(System.currentTimeMillis()%1000000);
    private static final String ID_2 = Long.toString((System.currentTimeMillis()+1)%1000000);

    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    public void testCopyCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(customAttributeMaintenanceLookupPage,"id","7");
        HtmlPage searchPage = clickOn(customAttributeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage customAttributeMaintenanceCopyPage = clickOn(copyLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(customAttributeMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeMaintenanceCopyPage, "document.documentHeader.documentDescription", "Custom Attribute - copy test");

        setFieldValue(customAttributeMaintenanceCopyPage, "document.newMaintainableObject.id", ID_1);
                
        HtmlPage routedPage = clickOn(customAttributeMaintenanceCopyPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(ID_1));
        assertEquals(customAttribute.getGroupName(),"Project Details");
        assertEquals(customAttribute.getLabel(),"Inventions");
        assertEquals(customAttribute.getName(),"inventions");
        assertEquals(customAttribute.getDataLength(),new Integer(30));
        assertEquals(customAttribute.getDataTypeCode(),"1");


    }

    @Test
    public void testEditCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(customAttributeMaintenanceLookupPage,"id","7");
        HtmlPage searchPage = clickOn(customAttributeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage customAttributeMaintenanceEditPage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentDescription", "Custom Attribute - edit test");

        setFieldValue(customAttributeMaintenanceEditPage, "document.newMaintainableObject.dataLength", "35");
                
        HtmlPage routedPage = clickOn(customAttributeMaintenanceEditPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(7));
        assertEquals(customAttribute.getGroupName(),"Project Details");
        assertEquals(customAttribute.getLabel(),"Inventions");
        assertEquals(customAttribute.getName(),"inventions");
        assertEquals(customAttribute.getDataLength(),new Integer(35));
        assertEquals(customAttribute.getDataTypeCode(),"1");

        
        // reset the length value back, otherwise it may affect customattributeserviceimpltest
        customAttributeMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(customAttributeMaintenanceLookupPage,"id","7");
        searchPage = clickOn(customAttributeMaintenanceLookupPage, "search");
        
        editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        customAttributeMaintenanceEditPage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        documentNumber = getFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentDescription", "Custom Attribute - edit test");

        setFieldValue(customAttributeMaintenanceEditPage, "document.newMaintainableObject.dataLength", "30");
                
        routedPage = clickOn(customAttributeMaintenanceEditPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedPage, "Document was successfully submitted.");

    }

    @Test
    public void testCreateNewCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenancePage = getMaintenanceDocumentPage(LOOKUP_PAGE_TITLE,"org.kuali.kra.bo.CustomAttribute",MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(customAttributeMaintenancePage, "document.documentHeader.documentNumber");
        
        assertContains(customAttributeMaintenancePage,"CustomAttribute New * Id: Data Length: * Data Type Code: select Default Value:");
        assertContains(customAttributeMaintenancePage,"* Label: Lookup Class: select Lookup Return: select * Name");
        
        setFieldValue(customAttributeMaintenancePage, "document.documentHeader.documentDescription", "Custom Attribute - test");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.id", ID_2);
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.dataLength", "8");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.dataTypeCode", "1");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.groupName", "test group");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.label", "Test 99");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.name", "test99");
        webClient.setJavaScriptEnabled(false);
        // javascript is not working for onchange in lookupclass??
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.lookupClass", "org.kuali.kra.bo.DegreeType");
        webClient.setJavaScriptEnabled(true);
        // can't set lookup return here bc ajax is not working, so it's empty
        //setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.lookupReturn", "roleId");
        HtmlPage routeErrorCustomDataPage = clickOn(customAttributeMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);        
        assertContains(routeErrorCustomDataPage, "1 error(s) found on page");
        assertContains(routeErrorCustomDataPage, "Errors found in this Section: Lookup Return is a required field");

        // lookupreturn should be loaded during page reload
        setFieldValue(routeErrorCustomDataPage, Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN, "degreeCode");
        HtmlPage routedCustomDataPage = clickOn(routeErrorCustomDataPage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedCustomDataPage, "Document was successfully submitted.");
        assertContains(routedCustomDataPage,"New Id: "+ID_2+" Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: Degree Type Lookup Return: Degree Code Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(ID_2));
        assertEquals(customAttribute.getGroupName(),"test group");
        assertEquals(customAttribute.getLabel(),"Test 99");
        assertEquals(customAttribute.getName(),"test99");
        assertEquals(customAttribute.getDataLength(),new Integer(8));
        assertEquals(customAttribute.getDataTypeCode(),"1");
        
    }


}
