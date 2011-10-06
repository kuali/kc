/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.web.BudgetSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class ProposalDevelopmentDeleteProposalSeleniumTest extends KcSeleniumTestBase {
    
    private ProposalDevelopmentSeleniumHelper proposalDevelopmentHelper;
    private BudgetSeleniumHelper budgetHelper;
    
    private String proposalDocumentNumber;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        proposalDevelopmentHelper = ProposalDevelopmentSeleniumHelper.instance(driver);
        budgetHelper = BudgetSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        proposalDevelopmentHelper = null;
        budgetHelper = null;
        
        super.tearDown();
    }
    
    @Test
    public void testDeleteProposal() {
        proposalDocumentNumber = createProposal();
        
        proposalDevelopmentHelper.clickProposalDevelopmentActionsPage();
        proposalDevelopmentHelper.click("deleteProposal");
        proposalDevelopmentHelper.clickYesAnswer();
        
        proposalDevelopmentHelper.docSearch(proposalDocumentNumber);
        proposalDevelopmentHelper.assertSelectorContains("div.topblurb table td", "The Development Proposal has been deleted.");
    }
    
    @Test
    public void testDeleteHierarchy() {
        proposalDocumentNumber = createProposal();
        
        proposalDevelopmentHelper.clickProposalDevelopmentActionsPage();
        proposalDevelopmentHelper.clickExpandAll();
        proposalDevelopmentHelper.click("createHierarchy");
        proposalDevelopmentHelper.click("deleteProposal");
        proposalDevelopmentHelper.assertSelectorContains("div.left-errmsg", "A Proposal is in Hierarchy so it can not be deleted.");
    }
    
    private String createProposal() {
        proposalDevelopmentHelper.createProposalDevelopment();
        
        proposalDevelopmentHelper.addKeyPersonnel();
        
        proposalDevelopmentHelper.addCustomData();
        
        proposalDevelopmentHelper.addQuestions();
        
        proposalDevelopmentHelper.addPermissions();
        
        proposalDevelopmentHelper.addBudget();
        proposalDevelopmentHelper.openBudget(0);
        budgetHelper.addPersonnel();
        budgetHelper.addNonPersonnel();
        budgetHelper.returnToProposal();
        proposalDevelopmentHelper.finalizeBudget(0);
        
        return proposalDevelopmentHelper.getDocumentNumber();        
    }

}