/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import edu.iu.uis.eden.exception.WorkflowException;

import org.junit.After;
import org.junit.Before;

/**
 * Base class for all htmlunit tests involving the Proposal Development Page.
 * 
 * @author $Author: gthomas $
 * @version $Revision: 1.10 $
 */
public abstract class ProposalDevelopmentWebTestBase extends KraWebTestBase {
    
    private static final String ABSTRACTS_ATTACHMENTS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x";
    private static final String ACTIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.actions.x";
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.financialDocumentDescription";
    protected static final String PROPOSAL_SPONSOR_CODE_ID = "document.sponsorCode";
    protected static final String PROPOSAL_TITLE_ID = "document.title";
    protected static final String PROPOSAL_REQUESTED_START_DATE_ID = "document.requestedStartDateInitial";
    protected static final String PROPOSAL_REQUESTED_END_DATE_ID = "document.requestedEndDateInitial";
    protected static final String PROPOSAL_ACTIVITY_TYPE_CODE_ID = "document.activityTypeCode";
    protected static final String PROPOSAL_TYPE_CODE_ID = "document.proposalTypeCode";
    protected static final String PROPOSAL_OWNED_BY_UNIT_ID = "document.ownedByUnitNumber";
    
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    protected static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005770";
    protected static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    protected static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "08/14/2007";
    protected static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "08/21/2007";
    protected static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "2"; // Dept Research
    protected static final String DEFAULT_PROPOSAL_TYPE_CODE = "1"; // New
    protected static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-CARD";
    
    private HtmlPage proposalDevelopmentPage;
    private HtmlPage portalPage;
    
    /**
     * Web test setup overloading. Sets up Portal Page and ProposalDevelopment page access.
     * 
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        setProposalDevelopmentPage(buildProposalDevelopmentPage());
    }
        
    /**
     * Create a new instance of the proposal development page by clicking on the link to the portal page. The resulting page of the click
     *  through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the proposal development page
     * @throws IOException
     */
    protected final HtmlPage buildProposalDevelopmentPage() throws IOException {
        HtmlPage retval = clickOn(getPortalPage(), "Proposal Development", "Kuali Portal Index");
        retval = getInnerPages(retval).get(0);
        assertTrue("Kuali :: Proposal Development Document".equals(retval.getTitleText()));
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
     * Gets the Proposal Development web page for creating a new Proposal document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal development web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Proposal Development web page.
     */
    protected final HtmlPage getProposalDevelopmentPage() {
        return this.proposalDevelopmentPage;
    }
    
    /**
     * Sets the proposal development page for tests. Typically, run out of <code>{@link #setUp()}</code>
     * 
     * @param proposalDevelopmentPage <code>{@link HtmlPage}</code> instance for the test
     */
    protected final void setProposalDevelopmentPage(HtmlPage proposalDevelopmentPage) {
        this.proposalDevelopmentPage = proposalDevelopmentPage;
    }
    
    /**
     * Sets the Proposal Development's required fields to legal default values.
     * @param page the Proposal Development web page.
     */
    protected void setDefaultRequiredFields(HtmlPage page) {
        setRequiredFields(page, DEFAULT_DOCUMENT_DESCRIPTION,
                                DEFAULT_PROPOSAL_SPONSOR_CODE,
                                DEFAULT_PROPOSAL_TITLE,
                                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                                DEFAULT_PROPOSAL_TYPE_CODE,
                                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
    }
    
    /**
     * Sets the required fields for a Proposal Development document.
     * 
     * @param page the Proposal Development web page.
     * @param description the value for the description field.
     * @param sponsorCode the value for the sponsor code field.
     * @param title the value for the title field.
     * @param startDate the value for the requested start date field.
     * @param endDate the value for the requested end date field.
     * @param activityType the value for the activity type code.
     * @param proposalType the value for the proposal type code.
     * @param ownedByUnit the value for the owned by unit field.
     */
    protected void setRequiredFields(HtmlPage page, String description, String sponsorCode, String title, String startDate, String endDate, String activityType, String proposalType, String ownedByUnit) {
        setFieldValue(page, DOCUMENT_DESCRIPTION_ID, description);
        setFieldValue(page, PROPOSAL_SPONSOR_CODE_ID, sponsorCode);
        setFieldValue(page, PROPOSAL_TITLE_ID, title);
        setFieldValue(page, PROPOSAL_REQUESTED_START_DATE_ID, startDate);
        setFieldValue(page, PROPOSAL_REQUESTED_END_DATE_ID, endDate);
        setFieldValue(page, PROPOSAL_ACTIVITY_TYPE_CODE_ID, activityType);
        setFieldValue(page, PROPOSAL_TYPE_CODE_ID, proposalType);
        setFieldValue(page, PROPOSAL_OWNED_BY_UNIT_ID, ownedByUnit);
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
        assertNotNull(webClient);
        boolean javaScriptEnabled = webClient.isJavaScriptEnabled(); 
        webClient.setJavaScriptEnabled(false);

        HtmlPage textAreaPopupPage = clickOn(page, "methodToCall.updateTextArea.((#"+textAreaFieldName+":"+action+":"+textAreaLabel+"#))"+tabIndex);
        String currentValue = getFieldValue(textAreaPopupPage, textAreaFieldName);
        String completeText = currentValue+moreTextToBeAdded;
        setFieldValue(textAreaPopupPage, textAreaFieldName, completeText);
        super.assertContains(textAreaPopupPage, textAreaLabel);
        HtmlPage textAreasAddedPage = clickOn(textAreaPopupPage,"methodToCall.postTextAreaToParent.anchor"+tabIndex);
        assertEquals(getFieldValue(textAreasAddedPage, textAreaFieldName), completeText);
        webClient.setJavaScriptEnabled(javaScriptEnabled);
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
     * Get the Abstracts & Attachments Web Page. To do this, we first
     * get the Proposal Development Web Page and fill in the required
     * fields with some default values.  We can then navigate to the
     * Abstracts & Attachments Web Page.
     * 
     * @return the Abstracts & Attachments Web Page.
     * @throws Exception
     */
    protected HtmlPage getAbstractsAndAttachmentsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        this.setDefaultRequiredFields(proposalPage);
        HtmlPage abstractsAndAttachmentsPage = clickOn(proposalPage, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        return abstractsAndAttachmentsPage;
    }
    
    /**
     * Get the Actions Web Page. To do this, we first get the Proposal Development 
     * Web Page and fill in the required fields with some default values.  We can 
     * then navigate to the Actions Web Page.
     * 
     * @return the Actions Web Page.
     * @throws Exception
     */
    protected HtmlPage getActionsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        this.setDefaultRequiredFields(proposalPage);
        HtmlPage actionsPage = clickOn(proposalPage, ACTIONS_LINK_NAME);
        return actionsPage;
    }
    
    /**
     * Click on the Actions Hyperlink.
     *
     * @return the Actions Web Page.
     * @throws Exception
     */
    protected HtmlPage clickActionsHyperlink(HtmlPage page) throws Exception {
        return clickOn(page, ACTIONS_LINK_NAME);
    }

    /**
     * This method checks document fields against the passed in values
     * @param doc the document to check values against
     * @param activityType to check
     * @param ownedByUnit to check
     * @param description to check
     * @param sponsorCode to check
     * @param title toi check
     * @param requestedStartDateInitial to check
     * @param requestedEndDateInitial to check
     * @param proposalTypeCode to check
     * @throws WorkflowException
     */
    protected void verifySavedRequiredFields(ProposalDevelopmentDocument doc, String activityType, String ownedByUnitNumber, String description, String sponsorCode, String title, String requestedStartDateInitial, String requestedEndDateInitial, String proposalTypeCode) throws WorkflowException {
        assertEquals(activityType, doc.getActivityTypeCode());
        assertEquals(ownedByUnitNumber, doc.getOwnedByUnitNumber());
        assertEquals(description, doc.getDocumentHeader().getFinancialDocumentDescription());
        assertEquals(sponsorCode, doc.getSponsorCode());
        assertEquals(title, doc.getTitle());
        assertEquals(requestedStartDateInitial, doc.getRequestedStartDateInitial().toString());
        assertEquals(requestedEndDateInitial, doc.getRequestedEndDateInitial().toString());
        assertEquals(proposalTypeCode, doc.getProposalTypeCode());
    }

}
