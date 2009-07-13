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

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for InstitutionalProposal Home Page. 
 */
public class InstitutionalProposalHomeWebTest extends InstitutionalProposalWebTestBase {

    HtmlPage proposalHomePage;
    
    private static final String SAVE_METHOD = "methodToCall.save";
    protected static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    /**
     * The set up method calls the parent super method and gets the 
     * Institutional Proposal Home page after that.
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.InstitutionalProposalWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        proposalHomePage = getProposalHomePage();
    }
    
    /**
     * This method calls parent tear down method and than sets institutionalproposalHomePage to null
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.institutionalproposalWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        proposalHomePage = null;
    }
    
    @SuppressWarnings("unused")
    private void savePageAndVerifySave() throws IOException {
        proposalHomePage = clickOn(proposalHomePage, SAVE_METHOD);
        assertContains(proposalHomePage, SAVE_SUCCESS_MESSAGE);
    }
}
