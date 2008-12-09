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
import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class tests additional fields data set. 
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_research_areas.sql", delimiter = ";") }))
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
    
    private static final String JS_SELECT_ALL = "selectAllResearchAreas(document)";
    
    private static final String CHECKBOX_CHECKED = "on";
    private static final String CHECKBOX_UNCHECKED = "off";
    private String researchAreaStatus = CHECKBOX_UNCHECKED;
    
    private static final String RESEARCH_AREA_CHECKBOX_FIELD = "document.protocol.protocolResearchAreas[0].selectResearchArea";
    private static final String BUTTON_SELECT_ALL = "methodToCall.selectAllProtocolDocument.anchor";
    private static final String BUTTON_DELETE_SELECTED = "methodToCall.deleteSelectedProtocolDocument.anchor";
    
    private static final String FIRST_ROW_DATA = "1 TaxidermyTaxidermist";
    private static final String FIRST_ROW_DATA_CHECKED = "1 TaxidermyTaxidermist";
    private static final String FIRST_ROW_DATA_UNCHECKED = "1 TaxidermyTaxidermist";
    
    private static final String SECOND_ROW_DATA = "2 Turf and Turfgrass Management";
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
        
        super.checkExpandedTextArea(page, PROTOCOL_DESCRIPTION_ID, PROTOCOL_DESCRIPTION, PROTOCOL_DESCRIPTION2);
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
        
        /* performing science Research Area lookup */
        HtmlPage pageWithResearchAreaLookup = multiLookup(resultPage, "ResearchAreas", "description", "T*");
        HtmlTable table = getTable(pageWithResearchAreaLookup, "tab-AdditionalInformation-div");
        assertEquals(table.getRowCount(), 3);
        
        /* verify data returned by Research Area lookup */
        researchAreaStatus = getFieldValue(pageWithResearchAreaLookup, RESEARCH_AREA_CHECKBOX_FIELD);
        assertContains(pageWithResearchAreaLookup, FIRST_ROW_DATA);
        assertEquals(researchAreaStatus, CHECKBOX_UNCHECKED);
        
        //Invoke save method by clicking save button on form
        HtmlPage afterSavePage = super.saveDoc(pageWithResearchAreaLookup);
        
        assertNotNull(afterSavePage);
        assertEquals("Kuali :: Protocol Document", afterSavePage.getTitleText());
        
        /* Test javascript for select all */
        ScriptResult scriptResult = afterSavePage.executeJavaScriptIfPossible(JS_SELECT_ALL, "onSubmit", afterSavePage.getDocumentElement());
        HtmlPage pageAfterSelectAll = (HtmlPage)scriptResult.getNewPage();

        /* verify data after select all */
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_CHECKED);
        
        /* uncheck first row */
        setFieldValue(pageAfterSelectAll, RESEARCH_AREA_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_UNCHECKED);
        
        /* check server side select all */
        HtmlPage pageAfterSelect = clickOn(pageAfterSelectAll,BUTTON_SELECT_ALL);

        /* verify data after server side select all */
        researchAreaStatus = getFieldValue(pageAfterSelect, RESEARCH_AREA_CHECKBOX_FIELD);
        assertEquals(researchAreaStatus, CHECKBOX_CHECKED);
        
        /* uncheck first row */
        setFieldValue(pageAfterSelect, RESEARCH_AREA_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelect, FIRST_ROW_DATA_UNCHECKED);
        
        /* check delete selected function - delete all rows other than one unchecked above*/
        HtmlPage pageAfterDeleteSelected = clickOn(pageAfterSelectAll,BUTTON_DELETE_SELECTED);
        assertDoesNotContain(pageAfterDeleteSelected, SECOND_ROW_DATA);
        assertContains(pageAfterDeleteSelected, FIRST_ROW_DATA_UNCHECKED);
        
        //Invoke save method by clicking save button on form
        HtmlPage pageComplete = super.saveDoc(pageAfterDeleteSelected);
        
        assertNotNull(pageComplete);
        assertEquals("Kuali :: Protocol Document", pageComplete.getTitleText());
    }
}
