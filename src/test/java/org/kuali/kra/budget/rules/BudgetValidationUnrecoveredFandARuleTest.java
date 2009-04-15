/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.rule.BudgetValidationUnrecoveredFandARule;
import org.kuali.kra.budget.rule.event.BudgetValidationUnrecoveredFandAEvent;

public class BudgetValidationUnrecoveredFandARuleTest extends TestCase {
    private static final BudgetDecimal AMOUNT = new BudgetDecimal(100.00);
    private static final RateDecimal APPLICABLE_RATE = new RateDecimal(19.875);
    private final String ON_CAMPUS = "Y";
    private static final int BUDGET_FISCAL_YEAR = 2008;
    private static final String SOURCE_ACCOUNT = "12345A";
    
    private BudgetUnrecoveredFandA unrecoveredFandA;
    private BudgetValidationUnrecoveredFandARule unrecoveredFandARule;
    
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        unrecoveredFandARule = new BudgetUnrecoveredFandARuleImpl();
    }

    @After
    public void tearDown() {
        GlobalVariables.clear();
        unrecoveredFandARule = null;
    }
    
    @Test
    public void testValidatingRequiredFields_AllRequiredFieldsFilled() throws Exception { 
        unrecoveredFandA = new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, AMOUNT, APPLICABLE_RATE, ON_CAMPUS, SOURCE_ACCOUNT);        
        Assert.assertTrue(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_FiscalYearMissing() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA(null, AMOUNT, APPLICABLE_RATE, ON_CAMPUS, SOURCE_ACCOUNT);        
        Assert.assertFalse(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_NoneSet() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA();        
        Assert.assertFalse(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(4, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_AmountMissing() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, null, APPLICABLE_RATE, ON_CAMPUS, SOURCE_ACCOUNT);
        //Amount is set to 0.00 if it is null
        Assert.assertTrue(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_CampusMissing() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, AMOUNT, APPLICABLE_RATE, null, SOURCE_ACCOUNT);        
        Assert.assertFalse(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_ApplicableRateMissing() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, AMOUNT, null, ON_CAMPUS, SOURCE_ACCOUNT);        
        Assert.assertFalse(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_SourceAccountMissing() throws Exception {
        unrecoveredFandA = new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, AMOUNT, APPLICABLE_RATE, ON_CAMPUS, null);        
        Assert.assertFalse(unrecoveredFandARule.processBudgetValidationUnrecoveredFandABusinessRules(getEvent(unrecoveredFandA)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }
    
    private BudgetValidationUnrecoveredFandAEvent getEvent(BudgetUnrecoveredFandA unrecoveredFandA) {
        return new BudgetValidationUnrecoveredFandAEvent(null, null, null, unrecoveredFandA);
    }
}
