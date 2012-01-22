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
package org.kuali.kra.award.commitments;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests <code>AwardFandaRateRule</code>
 */
public class AwardFandaRateRuleTest {
    private final static String PROPERTY_PREFIX = "DUMMY_PREFIX";
    
    AwardFandaRateRule awardFandaRateRule;
    AwardFandaRate awardFandaRate;    

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardFandaRateRule = new AwardFandaRateRule();
        awardFandaRate = new AwardFandaRate();
        awardFandaRate.setApplicableFandaRate(new KualiDecimal(5));
        awardFandaRate.setFiscalYear("2008");
        awardFandaRate.setFandaRateTypeCode("5");
        awardFandaRate.setOnCampusFlag("N");
        awardFandaRate.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardFandaRate.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate.setEndDate(new Date(new Long("1214852613046")));
        GlobalVariables.setMessageMap(new MessageMap());
    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardFandaRateRule = null;
        awardFandaRate = null;
    }

    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardFandaRateRule#evaluateRuleForApplicableFandaRate(org.kuali.kra.award.commitments.AwardFandaRate)}.
     */
    @Test
    public final void testEvaluateRuleForApplicableIndirectCostRate() {
        Assert.assertTrue(awardFandaRateRule.evaluateRuleForApplicableFandaRate(awardFandaRate, PROPERTY_PREFIX));
    }
    
    @Test
    public final void testEvaluateRuleForApplicableIndirectCostRateNull() {
        awardFandaRate.setApplicableFandaRate(null);
        Assert.assertFalse(awardFandaRateRule.evaluateRuleForApplicableFandaRate(awardFandaRate, PROPERTY_PREFIX));
    }
    
    @Test
    public final void testEvaluateRuleForApplicableIndirectCostRateEmpty() {
        awardFandaRate.setApplicableFandaRate(new KualiDecimal(-5));
        Assert.assertFalse(awardFandaRateRule.evaluateRuleForApplicableFandaRate(awardFandaRate, PROPERTY_PREFIX));
    }

    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardFandaRateRule#evaluateRuleForFandaRateTypeCode(org.kuali.kra.award.commitments.AwardFandaRate)}.
     */
    @Test
    public final void testEvaluateRuleForIdcRateTypeCode() {
        Assert.assertTrue(awardFandaRateRule.evaluateRuleForFandaRateTypeCode(awardFandaRate, PROPERTY_PREFIX));
    }

    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardFandaRateRule#evaluateRuleForFiscalYear(org.kuali.kra.award.commitments.AwardFandaRate)}.
     */
    @Test
    public final void testEvaluateRuleForFiscalYear() {
        Assert.assertTrue(awardFandaRateRule.evaluateRuleForFiscalYear(awardFandaRate, PROPERTY_PREFIX));        
    }

    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardFandaRateRule#evaluateRuleForStartAndEndDates(org.kuali.kra.award.commitments.AwardFandaRate)}.
     */
    @Test
    public final void testEvaluateRuleForStartAndEndDates() {
        Assert.assertTrue(awardFandaRateRule.evaluateRuleForStartAndEndDates(awardFandaRate, PROPERTY_PREFIX));        
    }

}
