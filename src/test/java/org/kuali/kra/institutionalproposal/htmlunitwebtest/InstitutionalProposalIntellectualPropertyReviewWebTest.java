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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class InstitutionalProposalIntellectualPropertyReviewWebTest extends InstitutionalProposalWebTestBase {
    
    private static final String IP_REVIEW_TAB_NAME = "intellectualPropertyReview.x";
    private static final String EDIT_IP_REVIEW_BUTTON = "methodToCall.editIntellectualPropertyReview";
    
    HtmlPage ipReviewPage;
    String documentNumber;
    
    /**
     * The set up method calls the parent super method and gets the 
     * Institutional Proposal Home page after that.
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.InstitutionalProposalWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        initializeIpReviewPage();
    }
    
    /**
     * This method calls parent tear down method and than sets institutionalproposalHomePage to null
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.institutionalproposalWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        ipReviewPage = null;
    }
    
    /**
     * Tests creating a new maintenance document to enter the initial IP record.
     * @throws Exception
     */
    @Test
    public void testCreateIpReviewRecord() throws Exception {
        HtmlPage ipReviewMaintenancePage = clickOn(ipReviewPage, EDIT_IP_REVIEW_BUTTON);
        assertContains(ipReviewMaintenancePage, "Page Title");
        setFieldValue(ipReviewMaintenancePage, "ipReviewer", "00000008");
    }
    
    /**
     * Tests editing the existing IP record via a maintenance document.
     * @throws Exception
     */
    @Test
    public void testEditIpReviewRecord() throws Exception {
        
    }
    
    private void initializeIpReviewPage() throws Exception {
        HtmlPage proposalHomePage = getProposalHomePage();
        documentNumber = "";
        ipReviewPage = clickOnTab(proposalHomePage, IP_REVIEW_TAB_NAME);
    }

}
