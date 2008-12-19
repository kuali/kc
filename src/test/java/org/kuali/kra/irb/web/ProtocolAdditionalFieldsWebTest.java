/*
 * Copyright 2006-2008 The Kuali Foundation
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
        @UnitTestFile(filename = "classpath:sql/dml/load_research_areas.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_reference_type.sql", delimiter = ";")}))
public class ProtocolAdditionalFieldsWebTest extends ProtocolWebTestBase {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolAdditionalFieldsWebTest.class);
    
    //Test field for Additional Sub Panel
    protected static final String PROTOCOL_ISBILLABLE_ID =  "document.protocol.billable";
    protected static final String PROTOCOL_ISBILLABLE =  "on";
    
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER_ID = "document.protocol.fdaApplicationNumber";
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER = "A11111";
    
    protected static final String PROTOCOL_REFERENCENUMBER1_ID = "document.protocol.referenceNumber1";
    protected static final String PROTOCOL_REFERENCENUMBER1 = "0000";
    
    protected static final String PROTOCOL_REFERENCENUMBER2_ID = "document.protocol.referenceNumber2";
    protected static final String PROTOCOL_REFERENCENUMBER2 = "0010";
    
    protected static final String PROTOCOL_DESCRIPTION_ID =  "document.protocol.description";
    protected static final String PROTOCOL_DESCRIPTION =  "keyword_to_test1";
    protected static final String PROTOCOL_DESCRIPTION2 = "test should be done based on feature";
    
    private static final String FIRST_ROW_DATA = "01.0508:TaxidermyTaxidermist ";
    
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID = "newProtocolReference.protocolReferenceTypeCode";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE = "4";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE = "COAG";
    
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY_ID = "newProtocolReference.referenceKey";
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY = "My Test";
    
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testAdditionalFields() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        
        assertTrue("Kuali :: Protocol Document".equalsIgnoreCase(page.getTitleText()));
        //Required Fields to begin with for saving protocol document
        setRequiredFields(page);
        
        //Additional Fields to populate for this test case
        
        super.setFieldValue(page, PROTOCOL_ISBILLABLE_ID, PROTOCOL_ISBILLABLE);
        super.setFieldValue(page, PROTOCOL_FDAAPPLICATIONNUMBER_ID, PROTOCOL_FDAAPPLICATIONNUMBER);
        super.setFieldValue(page, PROTOCOL_REFERENCENUMBER1_ID, PROTOCOL_REFERENCENUMBER1);
        super.setFieldValue(page, PROTOCOL_REFERENCENUMBER2_ID, PROTOCOL_REFERENCENUMBER2);
        super.setFieldValue(page, PROTOCOL_DESCRIPTION_ID, PROTOCOL_DESCRIPTION);
        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText());
        
        super.hasError(resultPage);
        
        setProtocolDocument(null, resultPage); //Can also be set by child if required
        
        //Assert Required Fields
        verifySavedRequiredFields();        
    
        //Assert Additional Fields
        assertTrue(getProtocolDocument().getProtocol().isBillable());
        assertEquals(PROTOCOL_FDAAPPLICATIONNUMBER, getProtocolDocument().getProtocol().getFdaApplicationNumber());
        assertEquals(PROTOCOL_REFERENCENUMBER1, getProtocolDocument().getProtocol().getReferenceNumber1());
        assertEquals(PROTOCOL_REFERENCENUMBER2, getProtocolDocument().getProtocol().getReferenceNumber2());
        assertEquals(PROTOCOL_DESCRIPTION, getProtocolDocument().getProtocol().getDescription());
        
    }
    
    @Test
    public void testExpandedTextArea() throws Exception {
         
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        super.checkExpandedTextArea(page, PROTOCOL_DESCRIPTION_ID, "methodToCall.kraUpdateTextArea.((#document.protocol.description:protocolProtocol:Summary/Keywords#))", 
                PROTOCOL_DESCRIPTION, PROTOCOL_DESCRIPTION2);
    }
    
    @Test
    public void testProtoclResearchAreaPanel() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        
        assertTrue("Kuali :: Protocol Document".equalsIgnoreCase(page.getTitleText()));
        //Required Fields to begin with for saving protocol document
        setRequiredFields(page);
        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText());
        
        //performing science Research Area lookup 
        HtmlPage pageWithResearchAreaLookup = multiLookup(resultPage, "ResearchAreas", "description", "T*");
        HtmlTable table = getTable(pageWithResearchAreaLookup, "tab-AdditionalInformation-div");
        assertEquals(table.getRowCount(), 3);
        
        //verify data returned by Research Area lookup 
        assertContains(pageWithResearchAreaLookup, FIRST_ROW_DATA);
        
        //Invoke save method by clicking save button on form
        HtmlPage afterSavePage = super.saveDoc(pageWithResearchAreaLookup);
        
        assertNotNull(afterSavePage);
        assertEquals("Kuali :: Protocol Document", afterSavePage.getTitleText());
        
        HtmlPage pageAfterDelete = clickOn(afterSavePage, "methodToCall.deleteProtocolResearchArea.line0.anchor5");
        
        assertDoesNotContain(pageAfterDelete, FIRST_ROW_DATA);
        
        //Invoke save method by clicking save button on form
        HtmlPage saveAfterDeletePage = super.saveDoc(pageAfterDelete);
        
        //Test after save to make sure list is delete aware
        assertDoesNotContain(saveAfterDeletePage, FIRST_ROW_DATA);
    }

    @Test
    public void testProtoclOtherIdentifierPanel() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        
        assertTrue("Kuali :: Protocol Document".equalsIgnoreCase(page.getTitleText()));
        //Required Fields to begin with for saving protocol document
        setRequiredFields(page);
        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText());
        
        setProtocolDocument(null, resultPage); //Can also be set by child if required
        
        super.setFieldValue(resultPage,PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE);
        super.setFieldValue(resultPage,PROTOCOL_REFERENCE_REFERENCEKEY_ID, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        HtmlPage pageAfterAdd= clickOn(resultPage,"methodToCall.addProtocolReference.anchor");
        
        setProtocolDocument(null, pageAfterAdd); //Can also be set by child if required
                
        //Invoke save method by clicking save button on form
        HtmlPage resultPageWithOtherIdentifiers = super.saveDoc(pageAfterAdd);
        
        assertContains(resultPageWithOtherIdentifiers, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertContains(resultPageWithOtherIdentifiers, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        
        HtmlPage pageAfterDelete = clickOn(resultPageWithOtherIdentifiers, "methodToCall.deleteProtocolReference.line0.anchor5");
        
        assertDoesNotContain(pageAfterDelete, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertDoesNotContain(pageAfterDelete, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        //Invoke save method by clicking save button on form
        HtmlPage saveAfterDeletePage = super.saveDoc(pageAfterDelete);
        
        //Test after save to make sure list is delete aware
        assertDoesNotContain(saveAfterDeletePage, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertDoesNotContain(saveAfterDeletePage, PROTOCOL_REFERENCE_REFERENCEKEY);
        
    }
    
}
