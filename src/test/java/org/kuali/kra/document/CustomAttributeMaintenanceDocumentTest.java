/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.document;

import java.io.IOException;

import org.junit.Test;
import org.kuali.core.document.MaintenanceDocumentBase;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
@PerTestUnitTestData(
    @UnitTestData(
            sqlStatements = {
                    @UnitTestSql("delete from CUSTOM_ATTRIBUTE where id = 99")

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
    public void testCreateNewCustomAttribute() throws Exception {
        HtmlPage customAttributeMaintenancePage = getMaintenanceDocumentPage("Custom Attribute","org.kuali.kra.bo.CustomAttribute","Kuali :: CustomAttribute Maintenance Document");
        String documentNumber = getFieldValue(customAttributeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(customAttributeMaintenancePage,"CustomAttribute New * Id: Data Length: * Data Type Code: select: Default Value: Group Name: * Label: Lookup Class: select: Lookup Return: select: * Name");
        setFieldValue(customAttributeMaintenancePage, "document.documentHeader.financialDocumentDescription", "Custom Attribute - test");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.id", "99");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.dataLength", "8");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.dataTypeCode", "1");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.groupName", "test group");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.label", "Test 99");
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.name", "test99");
        webClient.setJavaScriptEnabled(false);
        // javascript is not working for onchange in lookupclass??
        setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.lookupClass", "org.kuali.kra.bo.UserRole");
        webClient.setJavaScriptEnabled(true);
        // can't set lookup return here bc ajax is not working, so it's empty
        //setFieldValue(customAttributeMaintenancePage, "document.newMaintainableObject.lookupReturn", "roleId");
        HtmlPage routeErrorCustomDataPage = clickOn(customAttributeMaintenancePage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");        
        assertContains(routeErrorCustomDataPage, "1 error(s) found on page");
        assertContains(routeErrorCustomDataPage, "Errors found in this Section: Lookup Return is a required field");

        // lookupreturn should be loaded during page reload
        setFieldValue(routeErrorCustomDataPage, "document.newMaintainableObject.lookupReturn", "roleId");
        HtmlPage routedCustomDataPage = clickOn(routeErrorCustomDataPage, "methodToCall.route", "Kuali :: CustomAttribute Maintenance Document");
        
        assertContains(routedCustomDataPage, "Document was successfully submitted.");
        assertContains(routedCustomDataPage,"New Id: 99 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: org.kuali.kra.bo.UserRole Lookup Return: roleId Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttribute customAttribute = (CustomAttribute)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttribute.getId(),new Integer(99));
        assertEquals(customAttribute.getGroupName(),"test group");
        assertEquals(customAttribute.getLabel(),"Test 99");
        assertEquals(customAttribute.getName(),"test99");
        assertEquals(customAttribute.getDataLength(),new Integer(8));
        assertEquals(customAttribute.getDataTypeCode(),"1");
        
    }


}
