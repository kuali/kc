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
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class tests additional fields data set. 
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_reference_type.sql", delimiter = ";")}))
public class ProtocolAdditionalFieldsWebTest extends ProtocolWebTestBase {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolAdditionalFieldsWebTest.class);
    
    protected static final String PROTOCOL_DOCUMENT_NAME = "Kuali :: Protocol Document";
    protected static final String NEW_PROTOCOL = "Create Protocol";
    protected static final String KUALI_PORTAL_INDEX = "Kuali Portal Index";
    
    //Test field for Additional Sub Panel
    protected static final String PROTOCOL_ISBILLABLE_ID =  "document.protocolList[0].billable";
    protected static final String PROTOCOL_ISBILLABLE =  "on";
    
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER_ID = "document.protocolList[0].fdaApplicationNumber";
    protected static final String PROTOCOL_FDAAPPLICATIONNUMBER = "A11111";
    
    protected static final String PROTOCOL_REFERENCENUMBER1_ID = "document.protocolList[0].referenceNumber1";
    protected static final String PROTOCOL_REFERENCENUMBER1 = "0000";
    
    protected static final String PROTOCOL_REFERENCENUMBER2_ID = "document.protocolList[0].referenceNumber2";
    protected static final String PROTOCOL_REFERENCENUMBER2 = "0010";
    
    protected static final String PROTOCOL_DESCRIPTION_ID =  "document.protocolList[0].description";
    protected static final String PROTOCOL_DESCRIPTION_TEXTAREA_ID = "methodToCall.kraUpdateTextArea.((#document.protocolList[0].description:protocolProtocol:Summary/Keywords#))";

    protected static final String PROTOCOL_COMMENT_ID =  "newProtocolReference.comments";
    protected static final String PROTOCOL_COMMENT_TEXTAREA_ID = "methodToCall.kraUpdateTextArea.((#newProtocolReference.comments:protocolProtocol:Comments:false#))";

    
    protected static final String PROTOCOL_TEXTAREA =  "keyword_to_test1";
    protected static final String PROTOCOL_TEXTAREA2 = "test should be done based on feature";
        
    private static final String FIRST_ROW_DATA = "01.0508:TaxidermyTaxidermist ";
    
    private static final String  PROTOCOL_RESEARCHAREA_DELETEMETHOD = "methodToCall.deleteProtocolResearchArea.line0.anchor3";
    
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID = "newProtocolReference.protocolReferenceTypeCode";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE = "4";
    private static final String PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE = "COAG";
    
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY_ID = "newProtocolReference.referenceKey";
    private static final String PROTOCOL_REFERENCE_REFERENCEKEY = "My Test";
    
    private static final String PROTOCOL_REFERENCE_DELETEMETHOD = "methodToCall.deleteProtocolReference.line0.anchor3";
    private static final String PROTOCOL_REFERENCE_ADDMETHOD ="methodToCall.addProtocolReference.anchor";
    /**
     * This method asserts the form's additional field value persistence. 
     * @throws Exception
     */
    @Test
    public  void testAdditionalFields() throws Exception {
        
        //Click to create new protocol link
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        
        assertTrue(PROTOCOL_DOCUMENT_NAME.equalsIgnoreCase(page.getTitleText()));
        
        //Required Fields to begin with for saving protocol document
        //setRequiredFields(page);
        setProtocolRequiredFields(page);
        
        //Additional Fields to populate for this test case
        
        //super.setFieldValue(page, PROTOCOL_ISBILLABLE_ID, PROTOCOL_ISBILLABLE);
        super.setFieldValue(page, PROTOCOL_FDAAPPLICATIONNUMBER_ID, PROTOCOL_FDAAPPLICATIONNUMBER);
        super.setFieldValue(page, PROTOCOL_REFERENCENUMBER1_ID, PROTOCOL_REFERENCENUMBER1);
        super.setFieldValue(page, PROTOCOL_REFERENCENUMBER2_ID, PROTOCOL_REFERENCENUMBER2);
        super.setFieldValue(page, PROTOCOL_DESCRIPTION_ID, PROTOCOL_TEXTAREA);
        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals(PROTOCOL_DOCUMENT_NAME, resultPage.getTitleText());
        
        super.hasError(resultPage);
        
        setProtocolDocument(null, resultPage); //Can also be set by child if required
        
        //Assert Required Fields
        verifySavedRequiredFields();        
    
        //Assert Additional Fields
/*        assertTrue(getProtocolDocument().isBillable());
        assertEquals(PROTOCOL_FDAAPPLICATIONNUMBER, getProtocolDocument().getFdaApplicationNumber());
        assertEquals(PROTOCOL_REFERENCENUMBER1, getProtocolDocument().getReferenceNumber1());
        assertEquals(PROTOCOL_REFERENCENUMBER2, getProtocolDocument().getReferenceNumber2());
        assertEquals(PROTOCOL_DESCRIPTION, getProtocolDocument().getDescription());*/
        //assertTrue(getProtocolDocument().getProtocol().isBillable());
        assertEquals(PROTOCOL_FDAAPPLICATIONNUMBER, getProtocolDocument().getProtocol().getFdaApplicationNumber());
        assertEquals(PROTOCOL_REFERENCENUMBER1, getProtocolDocument().getProtocol().getReferenceNumber1());
        assertEquals(PROTOCOL_REFERENCENUMBER2, getProtocolDocument().getProtocol().getReferenceNumber2());
        assertEquals(PROTOCOL_TEXTAREA, getProtocolDocument().getProtocol().getDescription());
        
    }
    
    /**
     * This method test billable authz, to make sure only IRB Admin is allowed to edit billable checkbox
     * @throws Exception
     */
    @Test
    public void testBillable() throws Exception {
      //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        
        assertTrue(PROTOCOL_DOCUMENT_NAME.equalsIgnoreCase(page.getTitleText()));
        
        HtmlElement expandedField = getElement(page, PROTOCOL_ISBILLABLE_ID);
        
        //Assert that quickstart user doesn't have permission to edit billable checkbox
        assertEquals("disabled",expandedField.getAttributeValue("disabled"));
        
        //Required Fields to begin with for saving protocol document
        //setRequiredFields(page);
        setProtocolRequiredFields(page);

       
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals(PROTOCOL_DOCUMENT_NAME, resultPage.getTitleText());
        
        super.hasError(resultPage);
        
        //Set user with KRA Admin permission
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        setPortalPage(buildPageFromUrl("http://localhost:" + getPort() + "/kra-dev/",KUALI_PORTAL_INDEX));
        
        //Search doc saved earlier to edit check box field
        HtmlPage editablePage = super.saveAndSearchDoc(resultPage);
        
        //Check the check box
        super.setFieldValue(editablePage, PROTOCOL_ISBILLABLE_ID, PROTOCOL_ISBILLABLE);
        
        //Save document 
        HtmlPage resultPageWithBillable = super.saveDoc(editablePage);
        
        assertNotNull(resultPageWithBillable);
        assertEquals(PROTOCOL_DOCUMENT_NAME, resultPageWithBillable.getTitleText());
        
        super.hasError(resultPageWithBillable);

        HtmlElement expandedFieldAfterSave = getElement(page, PROTOCOL_ISBILLABLE_ID);
        
        //Check after save checkbox value persistance
        assertEquals("on",expandedFieldAfterSave.getAttributeValue("value"));
    }
   
    /**
     * This method is used to test Expanded Text Area of summary/keywords field in Additional Fields.
     * @throws Exception
     */
    @Test
    public void testSummaryKeywordExpandedTextArea() throws Exception {
         
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        super.checkExpandedTextArea(page, PROTOCOL_DESCRIPTION_ID, PROTOCOL_DESCRIPTION_TEXTAREA_ID, PROTOCOL_TEXTAREA, PROTOCOL_TEXTAREA2);
    }
    
    /**
     * This method tests the Research Area sub panel 
     * a. Adds new record
     * b. saves it
     * c. checks to see if record was added
     * d. deletes it
     * f. saves it
     * g. checks to see if it is deleted
     * @throws Exception
     */
    @Test
    public void testProtoclResearchAreaPanel() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        
        assertTrue(PROTOCOL_DOCUMENT_NAME.equalsIgnoreCase(page.getTitleText()));
        //Required Fields to begin with for saving protocol document
        //setRequiredFields(page);
        setProtocolRequiredFields(page);

        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals(PROTOCOL_DOCUMENT_NAME, resultPage.getTitleText());
        
        //performing science Research Area lookup 
        HtmlPage pageWithResearchAreaLookup = multiLookup(resultPage, "ResearchAreas", "description", "T*");
        HtmlTable table = getTable(pageWithResearchAreaLookup, "tab-AdditionalInformation-div");
        assertEquals(table.getRowCount(), 3);
        
        //verify data returned by Research Area lookup 
        assertContains(pageWithResearchAreaLookup, FIRST_ROW_DATA);
        
        //Invoke save method by clicking save button on form
        HtmlPage afterSavePage = super.saveDoc(pageWithResearchAreaLookup);
        
        assertNotNull(afterSavePage);
        assertEquals(PROTOCOL_DOCUMENT_NAME, afterSavePage.getTitleText());
        
        HtmlPage pageAfterDelete = clickOn(afterSavePage, PROTOCOL_RESEARCHAREA_DELETEMETHOD);
        
        assertDoesNotContain(pageAfterDelete, FIRST_ROW_DATA);
        
        //Invoke save method by clicking save button on form
        HtmlPage saveAfterDeletePage = super.saveDoc(pageAfterDelete);
        
        //Test after save to make sure list is delete aware
        assertDoesNotContain(saveAfterDeletePage, FIRST_ROW_DATA);
    }

    /**
     * This method tests the Other Identifier sub panel 
     * a. Adds new record
     * b. saves it
     * c. checks to see if record was added
     * d. deletes it
     * f. saves it
     * g. checks to see if it is deleted
     * @throws Exception
     */
    @Test
    public void testProtoclOtherIdentifierPanel() throws Exception {
        
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        
        assertTrue(PROTOCOL_DOCUMENT_NAME.equalsIgnoreCase(page.getTitleText()));
        //Required Fields to begin with for saving protocol document
        //setRequiredFields(page);
        setProtocolRequiredFields(page);

        
        //Invoke save method by clicking save button on form
        HtmlPage resultPage = super.saveDoc(page);
        
        assertNotNull(resultPage);
        assertEquals("Kuali :: Protocol Document", resultPage.getTitleText());
        
        setProtocolDocument(null, resultPage); //Can also be set by child if required
        
        super.setFieldValue(resultPage,PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_ID, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE);
        super.setFieldValue(resultPage,PROTOCOL_REFERENCE_REFERENCEKEY_ID, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        HtmlPage pageAfterAdd= clickOnByName(resultPage, PROTOCOL_REFERENCE_ADDMETHOD, true);
        
        setProtocolDocument(null, pageAfterAdd); //Can also be set by child if required
                
        //Invoke save method by clicking save button on form
        HtmlPage resultPageWithOtherIdentifiers = super.saveDoc(pageAfterAdd);
        
        assertContains(resultPageWithOtherIdentifiers, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertContains(resultPageWithOtherIdentifiers, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        
        HtmlPage pageAfterDelete = clickOn(resultPageWithOtherIdentifiers, PROTOCOL_REFERENCE_DELETEMETHOD);
        
        assertDoesNotContain(pageAfterDelete, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertDoesNotContain(pageAfterDelete, PROTOCOL_REFERENCE_REFERENCEKEY);
        
        //Invoke save method by clicking save button on form
        HtmlPage saveAfterDeletePage = super.saveDoc(pageAfterDelete);
        
        //Test after save to make sure list is delete aware
        assertDoesNotContain(saveAfterDeletePage, PROTOCOL_REFERENCE_PROTOCOLREFERENCETYPECODE_VALUE);
        assertDoesNotContain(saveAfterDeletePage, PROTOCOL_REFERENCE_REFERENCEKEY);
        
    }

    /**
     * This method is used to test Expanded Text Area of comment field in Other Identifier.
     * @throws Exception
     */
    @Test
    public void testCommentExpandedTextArea() throws Exception {
         
        //Click to create new protocol link
        HtmlPage page = clickOn(getPortalPage(), NEW_PROTOCOL,KUALI_PORTAL_INDEX);
        page = getInnerPages(page).get(0);
        super.checkExpandedTextArea(page, PROTOCOL_COMMENT_ID, PROTOCOL_COMMENT_TEXTAREA_ID, PROTOCOL_TEXTAREA, PROTOCOL_TEXTAREA2);
    }
}
