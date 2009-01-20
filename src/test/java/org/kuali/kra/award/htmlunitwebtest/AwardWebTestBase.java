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
package org.kuali.kra.award.htmlunitwebtest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Base class for all htmlunit tests involving the Award Document Pages.
 * 
 */

@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_COST_SHARE_TYPE.sql", delimiter = ";")}))
        
@SuppressWarnings("unchecked")
public abstract class AwardWebTestBase extends KraWebTestBase {
    
    protected static final String CONTACTS_LINK_NAME = "contacts.x";
    protected static final String TIME_AND_MONEY_LINK_NAME = "timeAndMoney.x";
    protected static final String PAYMENT_REPORTS_AND_TERMS_LINK_NAME = "paymentReportsAndTerms.x";
    protected static final String SPECIAL_REVIEW_LINK_NAME = "specialReview.x";
    protected static final String CUSTOM_DATA_LINK_NAME = "customData.x";
    protected static final String QUESTIONS_LINK_NAME = "questions.x";
    protected static final String PERMISSIONS_LINK_NAME = "permissions.x";
    protected static final String NOTES_AND_ATTACHMENTS_LINK_NAME = "notesAndAttachments.x";
    protected static final String AWARD_ACTIONS_LINK_NAME = "awardActions.x";
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    protected static final String PROPOSAL_SPONSOR_CODE_ID = "document.sponsorCode";
    protected static final String PROPOSAL_TITLE_ID = "document.title";
    protected static final String PROPOSAL_REQUESTED_START_DATE_ID = "document.requestedStartDateInitial";
    protected static final String PROPOSAL_REQUESTED_END_DATE_ID = "document.requestedEndDateInitial";
    protected static final String PROPOSAL_ACTIVITY_TYPE_CODE_ID = "document.activityTypeCode";
    protected static final String PROPOSAL_TYPE_CODE_ID = "document.proposalTypeCode";
    protected static final String PROPOSAL_OWNED_BY_UNIT_ID = "document.ownedByUnitNumber";
    
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Award Development Web Test";    
    
    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    protected static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    protected static final String ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST = "table or view does not exist";
    private HtmlPage awardHomePage;    
    
    /**
     * Web test setup overloading. Sets up Portal Page and ProposalDevelopment page access.
     * 
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setAwardHomePage(buildAwardDocumentPage());
    }
        
    /**
     * Create a new instance of the award document page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the award document page
     * @throws IOException
     */
    protected final HtmlPage buildAwardDocumentPage() throws Exception {
        HtmlPage retval = clickOn(getPortalPage(), "Create Award", "Kuali Portal Index");
        retval = getInnerPages(retval).get(0);
        System.out.println(retval.getTitleText());
        assertTrue("Kuali :: Award Document".equals(retval.getTitleText()));
        return retval;
    }

    /**
     * Web test tear down overloading.
     * 
     * @see org.kuali.kra.KraWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Gets the Award Home web page for creating a new Award document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal development web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Award Home web page.
     */
    protected final HtmlPage getAwardHomePage() {
        return this.awardHomePage;
    }
    
    /**
     * Sets the award page for tests. Typically, run out of <code>{@link #setUp()}</code>
     * 
     * @param awardHomePage <code>{@link HtmlPage}</code> instance for the test
     */
    protected final void setAwardHomePage(HtmlPage awardHomePage) {
        this.awardHomePage = awardHomePage;
    }
    
    /**
     * As we build the Award Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Award Home page.
     * 
     * Sets the Award Document's required fields to legal default values.
     * @param page the Proposal Development web page.
     */
    protected void setDefaultRequiredFields(HtmlPage page) {
        setRequiredFields(page, DEFAULT_DOCUMENT_DESCRIPTION);
                                /*,DEFAULT_PROPOSAL_SPONSOR_CODE,
                                DEFAULT_PROPOSAL_TITLE,
                                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                                DEFAULT_PROPOSAL_TYPE_CODE,
                                DEFAULT_PROPOSAL_OWNED_BY_UNIT);*/
    }
    
    /**
     * As we build the Award Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Award Home page.
     * 
     * Sets the required fields for a Award document.
     * 
     * @param page the Proposal Development web page.
     * @param description the value for the description field.
     */
    protected void setRequiredFields(HtmlPage page, String description){//, String sponsorCode, String title, String startDate, String endDate, String activityType, String proposalType, String ownedByUnit) {
        setFieldValue(page, DOCUMENT_DESCRIPTION_ID, description);
        /*setFieldValue(page, PROPOSAL_SPONSOR_CODE_ID, sponsorCode);
        setFieldValue(page, PROPOSAL_TITLE_ID, title);
        setFieldValue(page, PROPOSAL_REQUESTED_START_DATE_ID, startDate);
        setFieldValue(page, PROPOSAL_REQUESTED_END_DATE_ID, endDate);
        setFieldValue(page, PROPOSAL_ACTIVITY_TYPE_CODE_ID, activityType);
        setFieldValue(page, PROPOSAL_TYPE_CODE_ID, proposalType);
        setFieldValue(page, PROPOSAL_OWNED_BY_UNIT_ID, ownedByUnit);*/
    }
    /**
     * 
     * This method is to test the <code>ExtendedTextArea</code> tag
     * @param page
     * @param textAreaFieldName
     * @param moreTextToBeAdded
     * @param action
     * @param textAreaLabel
     * @param tabIndex
     * @throws Exception
     */
    protected void testTextAreaPopup(HtmlPage page, String textAreaFieldName,String moreTextToBeAdded,String action,String textAreaLabel,String tabIndex) throws Exception{
//        assertNotNull(webClient);
//        boolean javaScriptEnabled = webClient.isJavaScriptEnabled(); 
//        webClient.setJavaScriptEnabled(false);

        HtmlPage textAreaPopupPage = clickOn(page, "methodToCall.updateTextArea.((#"+textAreaFieldName+":"+action+":"+textAreaLabel+"#))"+tabIndex);
        String currentValue = getFieldValue(textAreaPopupPage, textAreaFieldName);
        String completeText = currentValue+moreTextToBeAdded;
        setFieldValue(textAreaPopupPage, textAreaFieldName, completeText);
        super.assertContains(textAreaPopupPage, textAreaLabel);
        HtmlPage textAreasAddedPage = clickOn(textAreaPopupPage,"methodToCall.postTextAreaToParent.anchor"+tabIndex);
        assertEquals(getFieldValue(textAreasAddedPage, textAreaFieldName), completeText);
//        webClient.setJavaScriptEnabled(javaScriptEnabled);
    }
    /**
     * 
     * This method checks the values mentioned in the map against the values in from the page.
     * It uses getFieldValue(HtmlPage,string) method to get the value from page by using key.
     * @param page
     * @param keyValues
     */
    protected void validatePage(HtmlPage page, Map<String, String> keyValues) {
        Iterator<String> it = keyValues.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            assertEquals(getFieldValue(page, key), keyValues.get(key));
        }
    }
    
    /**
     * 
     * Get the Award Time & Money Web Page. To do this, we first
     * get the Award Home page and fill in the required
     * fields with some default values.  We can then navigate to the
     * Award Time & Money Web Page.
     * @return
     * @throws Exception
     */
    protected HtmlPage getAwardTimeAndMoneyPage() throws Exception {
        HtmlPage awardHomePage = this.getAwardHomePage();
        this.setDefaultRequiredFields(awardHomePage);
        HtmlPage awardTimeAndMoneyPage = clickOnTab(awardHomePage, TIME_AND_MONEY_LINK_NAME);
        return awardTimeAndMoneyPage;
    }
    
    /**
     * Get the Award Actions Web Page. To do this, we first
     * get the Award Home page and fill in the required
     * fields with some default values.  We can then navigate to the
     * Award Actions Web Page.
     * 
     * @return the Award Actions Web Page.
     * @throws Exception
     */
    protected HtmlPage getAwardActionsPage() throws Exception {
        HtmlPage awardHomePage = this.getAwardHomePage();
        this.setDefaultRequiredFields(awardHomePage);
        HtmlPage awardActionsPage = clickOnTab(awardHomePage, AWARD_ACTIONS_LINK_NAME);
        return awardActionsPage;
    }
            
    protected HtmlPage clickOnTab(HtmlPage page, String tabName) throws Exception {
        HtmlElement element = getElementByNameEndsWith(page, tabName);
        return clickOn(element);
    }
    
    /**
     * As we build the Award Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Award Home page.
     * This method checks document fields against the passed in values
     * @param doc the document to check values against
     * @param description to check
     * @throws WorkflowException
     */
    protected void verifySavedRequiredFields(AwardDocument doc, String description) throws WorkflowException {
        assertEquals(description, doc.getDocumentHeader().getDocumentDescription());
    }

}
