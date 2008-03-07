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

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class ProposalYnqWebTest extends ProposalDevelopmentWebTestBase{
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String DOCUMENT_SAVED = "Document was successfully saved";
    private static final String BUTTON_SAVE = "save";
    private static final String PROPOSAL_QUESTION = "NSF Beginning Investigator?";
    private static final String ERROR_REVIEW_DATE = "Review Date is required";
    private static final String ERROR_EXPLANATION = "Explanation is required";

    private static final String QUESTIONS_TABLE_ID1 = "Organization";
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005894";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "08/14/2007";
    private static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "08/21/2007";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_TYPE_CODE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-CARD";

    private static final String QUESTIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.questions.x";
    private static final String RADIO_FIELD_VALUE = "Y"; // answer Yes to all questions

    private HtmlPage proposalYNQPage;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        /* Save with basic/mandatory data and verify data saved */
        HtmlPage proposalPage = getNewProposalYnqPage();
        /* get proposal questions page */
        proposalPage = clickOn(proposalPage, QUESTIONS_LINK_NAME);
        setProposalYNQPage(proposalPage);
    }

    @Test
    public void testInitProposalYnq() throws Exception {
        HtmlPage proposalPage = getProposalYnqPage();

        /* check if question is displayed */
        assertContains(proposalPage, PROPOSAL_QUESTION);
        
        /* save questions panel - fields are not mandatory*/
        proposalPage = clickOn(proposalPage, BUTTON_SAVE);

        assertContains(proposalPage, DOCUMENT_SAVED);
        
    }

    @Test
    public void testProposalYnqForValidation() throws Exception {
        HtmlPage proposalPage = getProposalYnqPage();
        /* Answer all questions */
        for(int i=0; i<4; i++) {
            String fieldName = "document.proposalYnq[" + i + "].answer";
            setFieldValue(proposalPage,fieldName , RADIO_FIELD_VALUE);
        }
        /* save questions panel */
        proposalPage = clickOn(proposalPage, BUTTON_SAVE);
        /* page should contain errors - review date and explanation required */
        assertContains(proposalPage, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalPage, ERROR_REVIEW_DATE);
        assertContains(proposalPage, ERROR_EXPLANATION);
    }


    private void setProposalYNQPage(HtmlPage ynqPage) {
        this.proposalYNQPage = ynqPage;
    }
    
    private HtmlPage getProposalYnqPage() {
        return this.proposalYNQPage;
    }
    
    private HtmlPage getNewProposalYnqPage() throws Exception {
        
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                DEFAULT_PROPOSAL_TYPE_CODE,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        /* Save with basic/mandatory data and verify data saved */
        proposalPage = clickOn(proposalPage, BUTTON_SAVE);
        assertContains(proposalPage, DOCUMENT_SAVED);
        assertDoesNotContain(proposalPage, ERRORS_FOUND_ON_PAGE);
        return proposalPage;
    }


}

