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
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
@PerTestUnitTestData(
    @UnitTestData(
            sqlStatements = {
                    @UnitTestSql("delete from CUSTOM_ATTRIBUTE where id = 999")
                    ,@UnitTestSql("update  CUSTOM_ATTRIBUTE set data_length=30 where id = 7")
                    ,@UnitTestSql("commit")
            }
    )
)

public class CustomAttributeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "CustomAttributeMaintenanceDocument";


    @Test
    public void testDocumentCreation() throws Exception {
        testDocumentCreation(DOCTYPE);
    }

    @Test
    public void testCopyCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Custom Attribute");
        setFieldValue(customAttributeMaintenanceLookupPage,"id","7");
        HtmlPage searchPage = clickOn(customAttributeMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref("maintenance.do?businessObjectClassName=org.kuali.kra.bo.CustomAttribute&methodToCall=copy&id=7");
        HtmlPage customAttributeMaintenanceCopyPage = clickOn(copyLink, "Kuali :: CustomAttribute Maintenance Document");
        String documentNumber = getFieldValue(customAttributeMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeMaintenanceCopyPage, "document.documentHeader.documentDescription", "Custom Attribute - copy test");

        setFieldValue(customAttributeMaintenanceCopyPage, "document.newMaintainableObject.id", "999");
                
        HtmlPage routedPage = clickOn(customAttributeMaintenanceCopyPage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(999));
        assertEquals(customAttribute.getGroupName(),"Project Details");
        assertEquals(customAttribute.getLabel(),"Inventions");
        assertEquals(customAttribute.getName(),"inventions");
        assertEquals(customAttribute.getDataLength(),new Integer(30));
        assertEquals(customAttribute.getDataTypeCode(),"1");


    }

    @Test
    public void testEditCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Custom Attribute");
        setFieldValue(customAttributeMaintenanceLookupPage,"id","7");
        HtmlPage searchPage = clickOn(customAttributeMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref("maintenance.do?businessObjectClassName=org.kuali.kra.bo.CustomAttribute&methodToCall=edit&id=7");
        HtmlPage customAttributeMaintenanceEditPage = clickOn(editLink, "Kuali :: CustomAttribute Maintenance Document");
        String documentNumber = getFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeMaintenanceEditPage, "document.documentHeader.documentDescription", "Custom Attribute - edit test");

        setFieldValue(customAttributeMaintenanceEditPage, "document.newMaintainableObject.dataLength", "35");
                
        HtmlPage routedPage = clickOn(customAttributeMaintenanceEditPage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");
        
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


    }

    @Test
    public void testCreateNewCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenancePage = getMaintenanceDocumentPage("Custom Attribute","org.kuali.kra.bo.CustomAttribute","Kuali :: CustomAttribute Maintenance Document");
        String documentNumber = getFieldValue(customAttributeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(customAttributeMaintenancePage,"CustomAttribute New * Id: Data Length: * Data Type Code: select Default Value: * Group Name: * Label: Lookup Class: select Lookup Return: select * Name");
        setFieldValue(customAttributeMaintenancePage, "document.documentHeader.documentDescription", "Custom Attribute - test");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.id", "999");
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
        HtmlPage routeErrorCustomDataPage = clickOn(customAttributeMaintenancePage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");        
        assertContains(routeErrorCustomDataPage, "1 error(s) found on page");
        assertContains(routeErrorCustomDataPage, "Errors found in this Section: Lookup Return is a required field");

        // lookupreturn should be loaded during page reload
        setFieldValue(routeErrorCustomDataPage, Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN, "degreeCode");
        HtmlPage routedCustomDataPage = clickOn(routeErrorCustomDataPage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");
        
        assertContains(routedCustomDataPage, "Document was successfully submitted.");
        assertContains(routedCustomDataPage,"New Id: 999 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: Degree Type Lookup Return: Degree Code Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(999));
        assertEquals(customAttribute.getGroupName(),"test group");
        assertEquals(customAttribute.getLabel(),"Test 99");
        assertEquals(customAttribute.getName(),"test99");
        assertEquals(customAttribute.getDataLength(),new Integer(8));
        assertEquals(customAttribute.getDataTypeCode(),"1");
        
    }


}
