/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.budget.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class is parent for AddXxx Tests, where Xxx is a BudgetDistributionandIncomeComponent
 */
public abstract class AddBudgetDistributionAndIncomeTest {

    protected AwardBudgetDocument document;
    
    protected abstract void addArtifactToDocument(Object artifact);
    protected abstract Object generateReferenceArtifact();
    protected abstract Object generateDifferentArtifact();
    protected abstract Object generateEmptyArtifact();
    protected abstract Object generateRuleEvent(Object artifact);
    protected abstract boolean processRules(Object ruleEvent);

    @Before
    public void setUp() {
        GlobalVariables.setMessageMap(new MessageMap());
        document = new MockBudgetDocument();        
        KNSGlobalVariables.setKualiForm(new MockBudgetForm(document));
        addArtifactToDocument(generateReferenceArtifact());
    }

    @After
    public void tearDown() {
        GlobalVariables.clear();
        document = null;
        KNSGlobalVariables.setKualiForm(null);
    }
    
    /**
     * 
     * This method tests adding null values
     * @throws Exception
     */
    @Test
    public void testAdding_AllNulls() throws Exception {
        Object ruleEvent = generateRuleEvent(generateEmptyArtifact());
        Assert.assertTrue(processRules(ruleEvent));
    }
    
    /**
     * This method tests adding duplicate row
     * @throws Exception
     */
    @Test
    public void testAdding_DuplicateElement() throws Exception {
        Object ruleEvent = generateRuleEvent(generateReferenceArtifact());
        Assert.assertFalse(processRules(ruleEvent));
    }
    
    /**
     * This method tests adding non-duplicate row
     * @throws Exception
     */
    @Test
    public void testAdding_NotDuplicateElement() throws Exception {
        Object ruleEvent = generateRuleEvent(generateDifferentArtifact());
        Assert.assertTrue(processRules(ruleEvent));
    }

    /**
     * This class stubs out call to refreshReferenceObject
     */
    @SuppressWarnings("serial")
    private class MockBudgetDocument extends AwardBudgetDocument {

        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            // do nothing
        }
    }
    
    /**
     * This class simply stores a budget document
     */
    @SuppressWarnings("serial")
    private class MockBudgetForm extends KualiForm {
        private AwardBudgetDocument document;
        
        public MockBudgetForm(AwardBudgetDocument document) {
            super();
            this.document = document;
        }
        
        public AwardBudgetDocument getBudgetDocument() { return document; }
        public void setDocument(AwardBudgetDocument document) { this.document = document; }
    }   
}
