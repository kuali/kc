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
import org.kuali.kra.bo.CustomAttributeDocument;
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
                        @UnitTestSql("delete from CUSTOM_ATTRIBUTE_DOCUMENT where DOCUMENT_TYPE_CODE = 'STTC' and CUSTOM_ATTRIBUTE_ID = 1")

                }
        )
    )

public class CustomAttributeDocumentMaintenanceDocumentTest  extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "CustomAttributeDocumentMaintenanceDocument";

    @Test
    public void testDocumentCreation() throws Exception {
        testDocumentCreation(DOCTYPE);
    }



    @Test
    public void testCreateNewCustomAttribute() throws Exception {
        HtmlPage customAttributeDocumentMaintenancePage = getMaintenanceDocumentPage("Custom Attribute Document","org.kuali.kra.bo.CustomAttributeDocument","Kuali :: CustomAttributeDocument Maintenance Document");
        String documentNumber = getFieldValue(customAttributeDocumentMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(customAttributeDocumentMaintenancePage,"Edit CustomAttributeDocument New * Custom Attribute ID: * Document Type Code: Required: unchecked Type Name:");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.documentHeader.financialDocumentDescription", "Custom Attribute Document - test");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.customAttributeId", "1");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.documentTypeCode", "STTC");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.typeName", "Test Type");
        HtmlPage routedCustomAttributeDocumentPage = clickOn(customAttributeDocumentMaintenancePage, "methodToCall.route", "Kuali :: CustomAttributeDocument Maintenance Document");
        
        assertContains(routedCustomAttributeDocumentPage, "Document was successfully submitted.");
        assertContains(routedCustomAttributeDocumentPage,"New Custom Attribute ID: 1 Document Type Code: STTC Required: No Type Name: Test Type ");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttributeDocument.getCustomAttributeId(),new Integer(1));
        assertEquals(customAttributeDocument.getTypeName(),"Test Type");
        assertEquals(customAttributeDocument.getDocumentTypeCode(),"STTC");
        
    }


}


