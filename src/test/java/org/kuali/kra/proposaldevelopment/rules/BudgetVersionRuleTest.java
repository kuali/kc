/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionCollection;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddBudgetVersionEvent;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.DocumentBase;

/**
 *
 * @see org.kuali.kra.proposaldevelopment.rules.BudgetVersionRule
 */
public class BudgetVersionRuleTest {
    private static final String VERSION_NAME = "test version";
    private static final String DEFAULT_BUD_VER_NAME = "Default Budget Name";

    private BudgetVersionCollection proposal;
    

    @Before
    public void setUp() throws Exception {
        proposal = new PseudoProposalDevelopmentDocument();
    }
        
    @Test
    public void testExistingBudgetVersion() {
        addNewBudgetVersion(proposal, VERSION_NAME);
        
        boolean ruleStatus = new AddBudgetVersionEvent("", (Document) proposal, VERSION_NAME).invokeRuleMethod(new BudgetVersionRule());
        assertFalse(ruleStatus);
    }

    @Test
    public void testNewBudgetVersion() {
        boolean ruleStatus = new AddBudgetVersionEvent("", (Document) proposal, VERSION_NAME).invokeRuleMethod(new BudgetVersionRule());

        assertTrue(ruleStatus);
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        assertTrue(new AddBudgetVersionEvent("", (Document) proposal, DEFAULT_BUD_VER_NAME).invokeRuleMethod(new BudgetVersionRule()));
    }
    
    /**
     * 
     * This method tests the Null Name field (a.k.a. documentDescription/newBudgetVersionName) negative case.
     * @throws Exception
     */
    @Test
    public void testNegativeNullName() throws Exception {
        assertFalse(new AddBudgetVersionEvent("", (Document) proposal, null).invokeRuleMethod(new BudgetVersionRule()));
    }
    
    /**
     * 
     * This method tests the empty Name field (a.k.a. documentDescription/newBudgetVersionName) negative case.
     * @throws Exception
     */
    @Test
    public void testNegativeEmptyName() throws Exception {
        assertFalse(new AddBudgetVersionEvent("", (Document) proposal, "").invokeRuleMethod(new BudgetVersionRule()));
    }
    
    /**
     * Adds a fake {@link BudgetVersionOverview} with <code>name</code> to the given {@link ProposalDevelopmentDocument}
     *
     * @param document document to add {@link BudgetVersionOverview} to
     * @param name of the {@link BudgetVersionOverview} to add
     */
    public void addNewBudgetVersion(BudgetVersionCollection document, String name) {
        document.getBudgetDocumentVersions().add(new PseudoBudgetVersionOverview(name));
    }

    /**
     * Fake {@link BudgetDocument} with a name constructor
     */
    public class PseudoBudgetVersionOverview extends BudgetDocumentVersion {
        public PseudoBudgetVersionOverview(String name) {
//            setDocumentDescription(name);
        }
    }

    /**
     * Fake {@link ProposalDevelopmentDocument} to get around using Spring for quicker unit tests that test just what we want to test.
     *
     */
    public class PseudoProposalDevelopmentDocument extends DocumentBase implements BudgetVersionCollection {
        private List<BudgetDocumentVersion> overviews;

        public PseudoProposalDevelopmentDocument() {
            setBudgetDocumentVersions(new ArrayList<BudgetDocumentVersion>());
        }

        public List<BudgetDocumentVersion> getBudgetDocumentVersions() {
            return overviews;
        }
        
        public void setBudgetDocumentVersions(List<BudgetDocumentVersion> budgetVersionOverviews) {
            overviews = budgetVersionOverviews;
        }
    }
}

