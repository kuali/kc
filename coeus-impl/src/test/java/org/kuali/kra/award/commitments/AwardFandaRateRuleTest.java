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
package org.kuali.kra.award.commitments;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;

/**
 * This class tests <code>AwardFandaRateRule</code>
 */
public class AwardFandaRateRuleTest {
    private final static String PROPERTY_PREFIX = "DUMMY_PREFIX";
    
    AwardFandaRateRule awardFandaRateRule;
    AwardFandaRate awardFandaRate;    

    @Before
    public void setUp() throws Exception {
        awardFandaRateRule = new AwardFandaRateRule();
        awardFandaRateRule.setErrorReporter(new ErrorReporterImpl());
        awardFandaRate = new AwardFandaRate();
        awardFandaRate.setApplicableFandaRate(new ScaleTwoDecimal(5));
        awardFandaRate.setFiscalYear("2008");
        awardFandaRate.setFandaRateTypeCode("5");
        awardFandaRate.setOnCampusFlag("N");
        awardFandaRate.setUnderrecoveryOfIndirectCost(new ScaleTwoDecimal(1000));
        awardFandaRate.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate.setEndDate(new Date(new Long("1214852613046")));
        GlobalVariables.setMessageMap(new MessageMap());
    }

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
        awardFandaRate.setApplicableFandaRate(new ScaleTwoDecimal(-5));
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
