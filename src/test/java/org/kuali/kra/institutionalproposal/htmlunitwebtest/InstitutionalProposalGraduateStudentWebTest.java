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

/**
 * 
 * This is the integration test for InstitutionalProposal Graduate Student Tab. 
 */
public class InstitutionalProposalGraduateStudentWebTest extends InstitutionalProposalHomeWebTest {

    private static final String SAVE_METHOD = "methodToCall.save";
    
    /**
     * The set up method calls the parent super method and gets the 
     * Institutional Proposal Home page after that.
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.InstitutionalProposalWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    /**
     * This method calls parent tear down method and than sets institutionalproposalHomePage to null
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.institutionalproposalWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testInstitutionalProposalAddGraduateStudentData() throws Exception {
        setFieldValue(proposalHomePage, "document.institutionalProposal.gradStudHeadcount", "123");
        setFieldValue(proposalHomePage, "document.institutionalProposal.gradStudPersonMonths", "34.25");
        
        proposalHomePage = clickOn(proposalHomePage, SAVE_METHOD);
        assertContains(proposalHomePage, SAVE_SUCCESS_MESSAGE);
    }
    
}
