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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddProposalBudgetVersionRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalBudgetVersionEvent;

public class AddProposalBudgetVersionRuleTest extends ProposalDevelopmentRuleTestBase {

    private AddProposalBudgetVersionRule rule;
    private final String DEFAULT_BUD_VER_NAME = "Default Budget Name";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new AddProposalBudgetVersionRuleImpl();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertTrue(rule.processAddProposalBudgetVersion(new AddProposalBudgetVersionEvent(document, DEFAULT_BUD_VER_NAME)));
    }
    
    /**
     * 
     * This method tests the Null Name field (a.k.a. documentDescription/newBudgetVersionName) negative case.
     * @throws Exception
     */
    @Test
    public void testNegativeNullName() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertFalse(rule.processAddProposalBudgetVersion(new AddProposalBudgetVersionEvent(document, null)));
        
    }
    
    /**
     * 
     * This method tests the empty Name field (a.k.a. documentDescription/newBudgetVersionName) negative case.
     * @throws Exception
     */
    @Test
    public void testNegativeEmptyName() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertFalse(rule.processAddProposalBudgetVersion(new AddProposalBudgetVersionEvent(document, "")));
        
    }
}
