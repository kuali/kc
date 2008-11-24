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

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.AwardIndirectCostRate;

/**
 * This class tests <code>AwardIndirectCostRateRule</code>
 */
public class AwardIndirectCostRateRuleTest {
    
    AwardIndirectCostRate awardIndirectCostRate;
    AwardIndirectCostRateRule awardIndirectCostRateRule;

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardIndirectCostRateRule = new AwardIndirectCostRateRule();
        awardIndirectCostRate = new AwardIndirectCostRate();
        awardIndirectCostRate.setApplicableIndirectCostRate(new KualiDecimal(5));
        awardIndirectCostRate.setFiscalYear("2008");
        awardIndirectCostRate.setIdcRateTypeCode(5);
        awardIndirectCostRate.setOnCampusFlag("N");
        awardIndirectCostRate.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardIndirectCostRate.setStartDate(new Date(new Long("1183316613046")));        
        awardIndirectCostRate.setEndDate(new Date(new Long("1214852613046")));        
    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardIndirectCostRate = null;
        awardIndirectCostRateRule = null;
    }

    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardIndirectCostRateRule#evaluateRuleForApplicableIndirectCostRate(org.kuali.kra.award.bo.AwardIndirectCostRate)}.
     */
    @Test
    public final void testEvaluateRuleForApplicableIndirectCostRate() {
        Assert.assertTrue(awardIndirectCostRateRule.evaluateRuleForApplicableIndirectCostRate(awardIndirectCostRate));
    }

    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardIndirectCostRateRule#evaluateRuleForIdcRateTypeCode(org.kuali.kra.award.bo.AwardIndirectCostRate)}.
     */
    @Test
    public final void testEvaluateRuleForIdcRateTypeCode() {
        Assert.assertTrue(awardIndirectCostRateRule.evaluateRuleForIdcRateTypeCode(awardIndirectCostRate));
    }

    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardIndirectCostRateRule#evaluateRuleForFiscalYear(org.kuali.kra.award.bo.AwardIndirectCostRate)}.
     */
    @Test
    public final void testEvaluateRuleForFiscalYear() {
        Assert.assertTrue(awardIndirectCostRateRule.evaluateRuleForFiscalYear(awardIndirectCostRate));        
    }

    /**
     * Test method for {@link org.kuali.kra.award.rules.AwardIndirectCostRateRule#evaluateRuleForStartAndEndDates(org.kuali.kra.award.bo.AwardIndirectCostRate)}.
     */
    @Test
    public final void testEvaluateRuleForStartAndEndDates() {
        Assert.assertTrue(awardIndirectCostRateRule.evaluateRuleForStartAndEndDates(awardIndirectCostRate));        
    }

}
