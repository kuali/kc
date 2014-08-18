/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionCollection;
import org.kuali.coeus.common.budget.impl.version.BudgetVersionRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.DocumentBase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @see org.kuali.coeus.common.budget.impl.version.BudgetVersionRule
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
        assertFalse(new AddBudgetVersionEvent("", (Document) proposal, (String)null).invokeRuleMethod(new BudgetVersionRule()));
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
     * Adds a fake {@link org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview} with <code>name</code> to the given {@link ProposalDevelopmentDocument}
     *
     * @param document document to add {@link org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview} to
     * @param name of the {@link org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview} to add
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
        
        public void refreshBudgetDocumentVersions() {
            
        }
    }
}

