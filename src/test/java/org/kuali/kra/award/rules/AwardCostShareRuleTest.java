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
package org.kuali.kra.award.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.event.AwardCostShareRuleEvent;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class tests <code>AwardCostShareRule</code>
 */
public class AwardCostShareRuleTest {
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_DESTINATION = "12345";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer PERCENTAGE = 50;
    private static final Integer COMMITMENT_AMOUNT = 10000;
    AwardCostShareRuleImpl awardCostShareRule;
    AwardCostShare awardCostShare;
    AwardDocument awardDocument;
    AwardCostShareRuleEvent awardCostShareRuleEvent;
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCostShareRule = new AwardCostShareRuleImpl();
        awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setFiscalYear(TEST_FISCAL_YEAR);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        GlobalVariables.setErrorMap(new ErrorMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCostShareRule = null;
        awardCostShare = null;
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardCostShareRule#processCommonValidations
     * (org.kuali.kra.award.bo.AwardCostShare)}.
     */
    @Test
    public final void testProcessCommonValidations() {
        Assert.assertTrue(awardCostShareRule.processCommonValidations(awardCostShare));
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.rules
     * .AwardCostShareRule#validateCostShareSourceAndDestinationForEquality
     * (org.kuali.kra.award.bo.AwardCostShare)}.
     */
    @Test
    public final void testValidateCostShareSourceAndDestinationForEquality() {
        Assert.assertTrue
            (awardCostShareRule.validateCostShareSourceAndDestinationForEquality(awardCostShare));
        awardCostShare.setSource(TEST_DESTINATION);
        Assert.assertFalse
            (awardCostShareRule.validateCostShareSourceAndDestinationForEquality(awardCostShare));
        awardCostShare.setSource(TEST_SOURCE);
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardCostShareRule#validateCostShareFiscalYearRange
     * (org.kuali.kra.award.bo.AwardCostShare)}.
     */
    @Test
    public final void testValidateCostShareFiscalYearRange() {
        Assert.assertTrue(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setFiscalYear(TEST_INVALID_FISCAL_YEAR);
        Assert.assertFalse(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setSource(TEST_FISCAL_YEAR);
    }
    
    


}
