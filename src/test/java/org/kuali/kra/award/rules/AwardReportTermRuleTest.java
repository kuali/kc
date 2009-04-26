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
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardReportTermRuleTest {
    
    AwardReportTermRule awardReportTermRule;
    AwardReportTerm awardReportTerm;

    @Before
    public void setUp() throws Exception {
        awardReportTermRule = new AwardReportTermRule();
        awardReportTerm = new AwardReportTerm();
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRule = null;
        awardReportTerm = null;
    }    

    @Test
    public final void testEvaluateRuleForReportCode() {
        awardReportTerm.setReportCode("5");
        Assert.assertTrue(awardReportTermRule.evaluateRuleForReportCode(awardReportTerm, 0));
        awardReportTerm.setReportCode(null);
        Assert.assertFalse(awardReportTermRule.evaluateRuleForReportCode(awardReportTerm, 0));        
    }

    @Test
    public final void testEvaluateRuleForFrequency() {
        awardReportTerm.setFrequencyCode("5");
        Assert.assertTrue(awardReportTermRule.evaluateRuleForFrequency(awardReportTerm, 0));
        awardReportTerm.setFrequencyCode(null);
        Assert.assertFalse(awardReportTermRule.evaluateRuleForFrequency(awardReportTerm, 0));
    }

    @Test
    public final void testEvaluateRuleForFrequencyBase() {
        awardReportTerm.setFrequencyBaseCode("5");
        Assert.assertTrue(awardReportTermRule.evaluateRuleForFrequencyBase(awardReportTerm, 0));
        awardReportTerm.setFrequencyBaseCode(null);
        Assert.assertFalse(awardReportTermRule.evaluateRuleForFrequencyBase(awardReportTerm, 0));
    }

    @Test
    public final void testEvaluateRuleForDistribution() {
        awardReportTerm.setOspDistributionCode("5");
        Assert.assertTrue(awardReportTermRule.evaluateRuleForDistribution(awardReportTerm, 0));
        awardReportTerm.setOspDistributionCode(null);
        Assert.assertFalse(awardReportTermRule.evaluateRuleForDistribution(awardReportTerm, 0));
    }

    @Test
    public final void testEvaluateRuleForDueDate() {
        awardReportTerm.setDueDate(new Date(new Long("10004232")));
        Assert.assertTrue(awardReportTermRule.evaluateRuleForDueDate(awardReportTerm, 0));
        awardReportTerm.setDueDate(null);
        Assert.assertFalse(awardReportTermRule.evaluateRuleForDueDate(awardReportTerm, 0));
    }

}
