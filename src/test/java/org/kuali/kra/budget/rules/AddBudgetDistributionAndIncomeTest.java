/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.rules;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetDocumentContainer;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * This class is parent for AddXxx Tests, where Xxx is a BudgetDistributionandIncomeComponent
 */
public abstract class AddBudgetDistributionAndIncomeTest extends TestCase {

    protected BudgetDocument document;
    
    protected abstract void addArtifactToDocument(Object artifact);
    protected abstract Object generateReferenceArtifact();
    protected abstract Object generateDifferentArtifact();
    protected abstract Object generateEmptyArtifact();
    protected abstract Object generateRuleEvent(Object artifact);
    protected abstract boolean processRules(Object ruleEvent);
    
    /**
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        document = new MockBudgetDocument();        
        GlobalVariables.setKualiForm(new MockBudgetForm(document));
        addArtifactToDocument(generateReferenceArtifact());
    }

    /**
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @After
    public void tearDown() {
        GlobalVariables.clear();
        document = null;
        GlobalVariables.setKualiForm(null);
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
    private class MockBudgetDocument extends BudgetDocument {

        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            // do nothing
        }
    }
    
    /**
     * This class simply stores a budget document
     */
    @SuppressWarnings("serial")
    private class MockBudgetForm extends KualiForm implements BudgetDocumentContainer {
        private BudgetDocument document;
        
        public MockBudgetForm(BudgetDocument document) {
            super();
            this.document = document;
        }
        
        public BudgetDocument getBudgetDocument() { return document; }
        public void setDocument(BudgetDocument document) { this.document = document; }
    }   
}