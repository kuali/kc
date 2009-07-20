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


/**
 * This class is integration test for Instituional Proposal Financial tab.
 */
public class InstitutionalProposalFinancialWebTest extends InstitutionalProposalHomeWebTest {

    private static final String SAVE_METHOD = "methodToCall.save";
    
    /**
     * The set up method calls the parent super method.
     * @see org.kuali.kra.award.htmlunitwebtest.InstitutionalProposalWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    
    /**
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.InstitutionalProposalHomeWebTest#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * 
     * This method tests the recalculate on Award Cost Share Recalculate on Time & Money page.
     * @throws Exception
     */
    @Test
    public void testFinancialRecalculate() throws Exception{
        
        setFieldValue(proposalHomePage, "document.institutionalProposal.totalDirectCostInitial", "200000");
        setFieldValue(proposalHomePage, "document.institutionalProposal.totalIndirectCostInitial", "50000");
        setFieldValue(proposalHomePage, "document.institutionalProposal.totalDirectCostTotal", "300000");
        setFieldValue(proposalHomePage, "document.institutionalProposal.totalIndirectCostTotal", "75000");
        proposalHomePage = clickOn(proposalHomePage, SAVE_METHOD);
        assertContains(proposalHomePage, SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(proposalHomePage,
                        "document.institutionalProposal.totalDirectCostInitial","100000");
        setFieldValue(proposalHomePage,
                        "document.institutionalProposal.totalIndirectCostInitial","25000");
        
        HtmlPage institutionalProposalHomePageAfterRecalculate = 
                clickOn(proposalHomePage,"methodToCall.recalculateTotals.anchorFinancial");
        System.out.println(institutionalProposalHomePageAfterRecalculate.asText());
        assertContains(institutionalProposalHomePageAfterRecalculate,"125000");
    }
}
