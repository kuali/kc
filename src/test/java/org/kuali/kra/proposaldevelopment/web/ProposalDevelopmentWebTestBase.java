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

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class ProposalDevelopmentWebTestBase extends KraWebTestBase {
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.financialDocumentDescription";
    protected static final String PROPOSAL_SPONSOR_CODE_ID = "document.sponsorCode";
    protected static final String PROPOSAL_TITLE_ID = "document.title";
    protected static final String PROPOSAL_REQUESTED_START_DATE_ID = "document.requestedStartDateInitial";
    protected static final String PROPOSAL_REQUESTED_END_DATE_ID = "document.requestedEndDateInitial";
    protected static final String PROPOSAL_ACTIVITY_TYPE_CODE_ID = "document.activityTypeCode";
    protected static final String PROPOSAL_TYPE_CODE_ID = "document.proposalTypeCode";
    protected static final String PROPOSAL_OWNED_BY_UNIT_ID = "document.ownedByUnitNumber";
    
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    protected static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "123456";
    protected static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    protected static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "08/14/2007";
    protected static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "08/21/2007";
    protected static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "2"; // Dept Research
    protected static final String DEFAULT_PROPOSAL_TYPE_CODE = "1"; // New
    protected static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-CARD";
    
    /**
     * Gets the Proposal Development web page for creating a new Proposal document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal development web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Proposal Development web page.
     * @throws IOException
     */
    protected HtmlPage getProposalDevelopmentPage() throws IOException {
        HtmlPage portalPage = getPortalPage();
        HtmlPage page = clickOn(portalPage, "Proposal Development", "Kuali Portal Index");
        HtmlPage proposalPage = getInnerPages(page).get(0);
        assertTrue("Kuali :: Proposal Development Document".equals(proposalPage.getTitleText()));
        return proposalPage;
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
        HtmlPage textAreaPopupPage = clickOn(page, "methodToCall.updateTextArea.((#"+textAreaFieldName+":"+action+":"+textAreaLabel+"#))"+tabIndex);
        String currentValue = getFieldValue(textAreaPopupPage, textAreaFieldName);
        String completeText = currentValue+moreTextToBeAdded;
        setFieldValue(textAreaPopupPage, textAreaFieldName, completeText);
        super.assertContains(textAreaPopupPage, textAreaLabel);
        HtmlPage textAreasAddedPage = clickOn(textAreaPopupPage,"methodToCall.postTextAreaToParent.anchor"+tabIndex);
        assertEquals(getFieldValue(textAreasAddedPage, textAreaFieldName), completeText);
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
}
