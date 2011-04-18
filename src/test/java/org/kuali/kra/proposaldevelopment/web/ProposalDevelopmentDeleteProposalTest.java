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

import org.junit.Test;
import org.kuali.kra.budget.web.BudgetSeleniumHelper;

public class ProposalDevelopmentDeleteProposalTest extends ProposalDevelopmentCompleteSeleniumTest {

    private String proposalDocumentNumber;
    
    //override complete test from parent so we don't test the same thing again.
    public void testProposalDevelopmentComplete() throws Exception {
        
    }
    
    @Test
    public void testDeleteProposal() {
        proposalDocumentNumber = createProposal();
        
        deleteProposal();
        
        this.docSearch(proposalDocumentNumber);
        this.assertSelectorContains("div.topblurb table td", "The Development Proposal has been deleted.");
    }
    
    @Test
    public void testDeleteHierarchy() {
        proposalDocumentNumber = createProposal();
        
        clickProposalDevelopmentActionsPage();
        clickExpandAll();
        click("createHierarchy");

        clickProposalDevelopmentActionsPage();
        click("deleteProposal");
        assertSelectorContains("div.left-errmsg", "A Proposal is in Hierarchy so it can not be deleted.");
    }
    
    public String createProposal() {
        createProposalDevelopment();
        
        addKeyPersonnel();
        
        addCustomData();
        
        addQuestions();
        
        addPermissions();
        
        addBudget();
        
        return getDocumentNumber();        
    }
    
    protected void deleteProposal() {
        clickProposalDevelopmentActionsPage();
        click("deleteProposal");
        click("processAnswer.button0");
    }
}
