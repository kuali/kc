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

/**
 * Tests the basic submission of a development proposal.
 */
public class ProposalDevelopmentCompleteSeleniumTest extends KcSeleniumTestBase {
    
    private ProposalDevelopmentSeleniumHelper helper;
    private BudgetSeleniumHelper budgetHelper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
        budgetHelper = BudgetSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        budgetHelper = null;
        
        super.tearDown();
    }

    /**
     * Test the basic submission of a development proposal.
     */
    @Test
    public void testProposalDevelopmentComplete() throws Exception {
        helper.createProposalDevelopment();
        
        helper.addKeyPersonnel();
        
        helper.addCustomData();
        
        helper.addQuestions();
        
        helper.addBudget();
        helper.openBudget(0);
        budgetHelper.addPersonnel();
        budgetHelper.addNonPersonnel();
        budgetHelper.returnToProposal();
        helper.finalizeBudget(0);
        
        helper.addPermissions();

        helper.submit();
    }

}