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
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerTestUnitTestData(
        @UnitTestData(
                sqlStatements = {
                        @UnitTestSql("delete from CUSTOM_ATTRIBUTE_DOCUMENT where DOCUMENT_TYPE_CODE = 'INPR' and CUSTOM_ATTRIBUTE_ID = 99"),
                        @UnitTestSql("delete from CUSTOM_ATTRIBUTE where ID = 99"),
                        @UnitTestSql("update CUSTOM_ATTRIBUTE_DOCUMENT set type_name = '' where CUSTOM_ATTRIBUTE_ID = 7"),
                        @UnitTestSql("INSERT INTO CUSTOM_ATTRIBUTE (ID,NAME,LABEL,DATA_TYPE_CODE,DATA_LENGTH,DEFAULT_VALUE,LOOKUP_CLASS,LOOKUP_RETURN,GROUP_NAME,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR, OBJ_ID) VALUES (99,'test99','Test 99','3',10,null,null,null,'Test group',to_date('2010-01-28 12:00:00','YYYY-MM-DD HH24:MI:SS'),'quicksta',1,'29B87CA0-366A-A250-6D6A-63FEB05B4584')")
                        ,@UnitTestSql("commit")
                }
        )
    )
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_doc_type_sys_param.sql", delimiter = ";")
 }))

public class CustomAttributeDocumentMaintenanceDocumentTest  extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "CustomAttributeDocumentMaintenanceDocument";
    private static final String INPR_TYPE = "INPR";

    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    public void testCopyCustomAttributeDocument() throws Exception {
        HtmlPage customAttributeDocuemntMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Custom Attribute Document");
        setFieldValue(customAttributeDocuemntMaintenanceLookupPage,"customAttributeId","7");
        HtmlPage searchPage = clickOn(customAttributeDocuemntMaintenanceLookupPage, "search");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref("maintenance.do?documentTypeName=PRDV&businessObjectClassName=org.kuali.kra.bo.CustomAttributeDocument&methodToCall=copy&customAttributeId=7");
        HtmlPage customAttributeDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: CustomAttributeDocument Maintenance Document");
        String documentNumber = getFieldValue(customAttributeDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Custom Attribute Document - copy test");

        setFieldValue(customAttributeDocumentMaintenanceCopyPage, "document.newMaintainableObject.customAttributeId", "99");
        setFieldValue(customAttributeDocumentMaintenanceCopyPage, "document.newMaintainableObject.documentTypeName", INPR_TYPE);
        setFieldValue(customAttributeDocumentMaintenanceCopyPage, "document.newMaintainableObject.typeName", "test type name");
                
        HtmlPage routedPage = clickOn(customAttributeDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: CustomAttributeDocument Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttributeDocument.getCustomAttributeId(),new Integer(99));
        assertEquals(customAttributeDocument.getTypeName(),"test type name");
        assertEquals(customAttributeDocument.getDocumentTypeName(),INPR_TYPE);


    }

    @Test
    public void testEditCustomAttributeDocument() throws Exception {
        HtmlPage customAttributeDocuemntMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Custom Attribute Document");
        setFieldValue(customAttributeDocuemntMaintenanceLookupPage,"customAttributeId","7");
        HtmlPage searchPage = clickOn(customAttributeDocuemntMaintenanceLookupPage, "search");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref("maintenance.do?documentTypeName=PRDV&businessObjectClassName=org.kuali.kra.bo.CustomAttributeDocument&methodToCall=edit&customAttributeId=7");
        HtmlPage customAttributeDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: CustomAttributeDocument Maintenance Document");
        String documentNumber = getFieldValue(customAttributeDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

        setFieldValue(customAttributeDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Custom Attribute Document - edit test");

        setFieldValue(customAttributeDocumentMaintenanceEditPage, "document.newMaintainableObject.typeName", "test type name");
                
        HtmlPage routedPage = clickOn(customAttributeDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: CustomAttributeDocument Maintenance Document");
        
        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttributeDocument.getCustomAttributeId(),new Integer(7));
        assertEquals(customAttributeDocument.getTypeName(),"test type name");
        assertEquals(customAttributeDocument.getDocumentTypeName(),"PRDV");


    }


    @Test
    public void testCreateNewCustomAttribute() throws Exception {
        HtmlPage customAttributeDocumentMaintenancePage = getMaintenanceDocumentPage("Custom Attribute Document","org.kuali.kra.bo.CustomAttributeDocument","Kuali :: CustomAttributeDocument Maintenance Document");
        String documentNumber = getFieldValue(customAttributeDocumentMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(customAttributeDocumentMaintenancePage,"Edit CustomAttributeDocument New * Custom Attribute ID: * Document Type Code: select Required: unchecked Type Name:");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.documentHeader.documentDescription", "Custom Attribute Document - test");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.customAttributeId", "99");
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.documentTypeName", INPR_TYPE);
        setFieldValue(customAttributeDocumentMaintenancePage, "document.newMaintainableObject.typeName", "Test Type");
        HtmlPage routedCustomAttributeDocumentPage = clickOn(customAttributeDocumentMaintenancePage, "methodToCall.route", "Kuali :: CustomAttributeDocument Maintenance Document");
        
        assertContains(routedCustomAttributeDocumentPage, "Document was successfully submitted.");
        assertContains(routedCustomAttributeDocumentPage,"New Custom Attribute ID: 99 Document Type Code: Institutional Proposal Required: No Type Name: Test Type Active: No ");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(customAttributeDocument.getCustomAttributeId(),new Integer(99));
        assertEquals(customAttributeDocument.getTypeName(),"Test Type");
        assertEquals(customAttributeDocument.getDocumentTypeName(),INPR_TYPE);
        
    }


}


