/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.web;

import org.junit.Test;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class tests additional fields data set. 
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_reference_type.sql", delimiter = ";")}))
public class ProtocolAdditionalFieldsWebTest extends ProtocolWebTestBase {  
    
    private static final String PROTOCOL_RESEARCHAREA_LOOKUP_TAG = "ResearchAreas";
    private static final String PROTOCOL_RESEARCHAREA_SEARCHFIELD_ID = "researchAreaCode";
    private static final String PROTOCOL_RESEARCHAREA_SEARCHFIELD_VALUE = "01.0508";
    private static final String PROTOCOL_RESEARCHAREA_TABLE_ID = "tab-AdditionalInformation-div";
    
    private static final String ADDED_ROW_DATA = "01.0508:TaxidermyTaxidermist";
    
    private static final String PROTOCOL_RESEARCHAREA_DELETEMETHOD = "methodToCall.deleteProtocolResearchArea.line1";
    
    private static final String PROTOCOL_FDAAPPLICATIONNUMBER_ID = "document.protocolList[0].fdaApplicationNumber";
    private static final String PROTOCOL_FDAAPPLICATIONNUMBER = "A11111";
    
    private static final String PROTOCOL_REFERENCENUMBER1_ID = "document.protocolList[0].referenceNumber1";
    private static final String PROTOCOL_REFERENCENUMBER1 = "0000";
    
    private static final String PROTOCOL_REFERENCENUMBER2_ID = "document.protocolList[0].referenceNumber2";
    private static final String PROTOCOL_REFERENCENUMBER2 = "0010";
    
    private static final String PROTOCOL_DESCRIPTION_ID =  "document.protocolList[0].description";
    private static final String PROTOCOL_DESCRIPTION_TEXTAREA_ID = "methodToCall.updateTextArea.((`document.protocolList[0].description:protocolProtocol:Summary/Keywords`))";

    private static final String PROTOCOL_TEXTAREA =  "keyword_to_test1";
    private static final String PROTOCOL_TEXTAREA2 = "test should be done based on feature";
    
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID = "newProtocolReference.protocolReferenceTypeCode";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE = "4";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE = "COAG";
    
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY_ID = "newProtocolReference.referenceKey";
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY = "My Test";
    
    private static final String PROTOCOL_REFERENCE_ADDMETHOD ="methodToCall.addProtocolReference.anchor";
    private static final String PROTOCOL_REFERENCE_DELETEMETHOD = "methodToCall.deleteProtocolReference.line0";
    
    private static final String PROTOCOL_COMMENT_ID =  "newProtocolReference.comments";
    private static final String PROTOCOL_COMMENT_TEXTAREA_ID = "methodToCall.updateTextArea.((`newProtocolReference.comments:protocolProtocol:Comments:false`))";
    
    /**
     * This method tests the Area of Research sub panel 
     * a. Adds new record
     * b. saves it
     * c. checks to see if record was added
     * d. deletes it
     * f. saves it
     * g. checks to see if it is deleted
     * @throws Exception
     */
    @Test
    public void testAreaOfResearchPanel() throws Exception {
        HtmlPage protocolPage = getProtocolHomePage();
        setProtocolRequiredFields(protocolPage);
        
        // Lookup and add additional fields to populate for this test case, save, and verify data
        protocolPage = multiLookup(protocolPage, PROTOCOL_RESEARCHAREA_LOOKUP_TAG, PROTOCOL_RESEARCHAREA_SEARCHFIELD_ID, PROTOCOL_RESEARCHAREA_SEARCHFIELD_VALUE);
        HtmlTable table = getTable(protocolPage, PROTOCOL_RESEARCHAREA_TABLE_ID);
        assertEquals(4, table.getRowCount());
        assertContains(protocolPage, ADDED_ROW_DATA);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        
        // Delete additional fields, save, and verify no data after save
        protocolPage = clickOnByName(protocolPage, PROTOCOL_RESEARCHAREA_DELETEMETHOD, true);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        assertDoesNotContain(protocolPage, ADDED_ROW_DATA);
    }
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public void testAdditionalInformationPanel() throws Exception {
        HtmlPage protocolPage = getProtocolHomePage();
        setProtocolRequiredFields(protocolPage);
        
        // Fill out additional fields to populate for this test case and save
        setFieldValue(protocolPage, PROTOCOL_FDAAPPLICATIONNUMBER_ID, PROTOCOL_FDAAPPLICATIONNUMBER);
        setFieldValue(protocolPage, PROTOCOL_REFERENCENUMBER1_ID, PROTOCOL_REFERENCENUMBER1);
        setFieldValue(protocolPage, PROTOCOL_REFERENCENUMBER2_ID, PROTOCOL_REFERENCENUMBER2);
        setFieldValue(protocolPage, PROTOCOL_DESCRIPTION_ID, PROTOCOL_TEXTAREA);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        
        // Refresh document
        setProtocolDocument(null, protocolPage);
    
        // Assert additional fields
        assertEquals(PROTOCOL_FDAAPPLICATIONNUMBER, getProtocolDocument().getProtocol().getFdaApplicationNumber());
        assertEquals(PROTOCOL_REFERENCENUMBER1, getProtocolDocument().getProtocol().getReferenceNumber1());
        assertEquals(PROTOCOL_REFERENCENUMBER2, getProtocolDocument().getProtocol().getReferenceNumber2());
        assertEquals(PROTOCOL_TEXTAREA, getProtocolDocument().getProtocol().getDescription());
        
    }
   
    /**
     * This method is used to test Expanded Text Area of summary/keywords field in Additional Fields.
     * @throws Exception
     */
    @Test
    public void testSummaryKeywordExpandedTextArea() throws Exception {
        HtmlPage protocolPage = getProtocolHomePage();
        checkExpandedTextArea(protocolPage, PROTOCOL_DESCRIPTION_ID, PROTOCOL_DESCRIPTION_TEXTAREA_ID, PROTOCOL_TEXTAREA, PROTOCOL_TEXTAREA2);
    }

    /**
     * This method tests the Other Identifiers sub panel 
     * a. Adds new record
     * b. saves it
     * c. checks to see if record was added
     * d. deletes it
     * f. saves it
     * g. checks to see if it is deleted
     * @throws Exception
     */
    @Test
    public void testOtherIdentifiersPanel() throws Exception {
        HtmlPage protocolPage = getProtocolHomePage();
        setProtocolRequiredFields(protocolPage);
        
        // Fill out and add additional fields to populate for this test case, save, and verify data
        setFieldValue(protocolPage, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE);
        setFieldValue(protocolPage, PROTOCOL_REFERENCE_REFERENCEKEY_ID, PROTOCOL_REFERENCE_REFERENCEKEY);
        protocolPage = clickOnByName(protocolPage, PROTOCOL_REFERENCE_ADDMETHOD, true);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        assertContains(protocolPage, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertContains(protocolPage, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        // Delete additional fields, save, and verify no data after save
        protocolPage = clickOnByName(protocolPage, PROTOCOL_REFERENCE_DELETEMETHOD, true);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        assertDoesNotContain(protocolPage, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertDoesNotContain(protocolPage, PROTOCOL_REFERENCE_REFERENCEKEY);
    }

    /**
     * This method is used to test Expanded Text Area of comment field in Other Identifiers.
     * @throws Exception
     */
    @Test
    public void testCommentExpandedTextArea() throws Exception {
        HtmlPage protocolPage = getProtocolHomePage();
        checkExpandedTextArea(protocolPage, PROTOCOL_COMMENT_ID, PROTOCOL_COMMENT_TEXTAREA_ID, PROTOCOL_TEXTAREA, PROTOCOL_TEXTAREA2);
    }
}
