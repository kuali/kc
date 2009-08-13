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
package org.kuali.kra.award.paymentreports.awardreports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardReportTermRuleImplTest {
    
    AwardReportTermRuleImpl awardReportTermRule;
    AwardReportTerm awardReportTerm;
    List<AwardReportTerm> awardReportTermItems;

    @Before
    public void setUp() throws Exception {
        awardReportTermRule = new AwardReportTermRuleImpl();
        awardReportTerm = new AwardReportTerm();
        awardReportTermItems = new ArrayList<AwardReportTerm>();
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRule = null;
        awardReportTerm = null;
        awardReportTermItems = null;
    }
    
    @Test
    public final void testIsUnique(){
        AwardReportTerm awardReportTermItem = new AwardReportTerm();
        awardReportTermItem.setAwardNumber("2");
        awardReportTermItem.setSequenceNumber(2);
        awardReportTermItem.setReportClassCode("5");
        awardReportTermItem.setReportCode("5");
        awardReportTermItem.setFrequencyBaseCode("5");
        awardReportTermItem.setFrequencyCode("5");
        awardReportTermItem.setDueDate(new Date(10000332));
        awardReportTermItems.add(awardReportTermItem);
        
        awardReportTermItem = new AwardReportTerm();
        awardReportTermItem.setAwardNumber("2");
        awardReportTermItem.setSequenceNumber(2);
        awardReportTermItem.setReportClassCode("5");
        awardReportTermItem.setReportCode("5");
        awardReportTermItem.setFrequencyBaseCode("5");
        awardReportTermItem.setFrequencyCode("5");
        awardReportTermItem.setDueDate(new Date(10000332));
        
        Assert.assertFalse(awardReportTermRule.isUnique(awardReportTermItems, awardReportTermItem));
        
        awardReportTermItem = new AwardReportTerm();
        awardReportTermItem.setAwardNumber("2");
        awardReportTermItem.setSequenceNumber(2);
        awardReportTermItem.setReportClassCode("6");
        awardReportTermItem.setReportCode("5");
        awardReportTermItem.setFrequencyBaseCode("5");
        awardReportTermItem.setFrequencyCode("5");
        awardReportTermItem.setDueDate(new Date(10000332));
        Assert.assertTrue(awardReportTermRule.isUnique(awardReportTermItems, awardReportTermItem));
    }

    @Test
    public final void testEvaluateRuleForReportCode() {
        awardReportTerm.setReportCode("5");
        Assert.assertTrue(awardReportTermRule.isReportCodeFieldComplete(awardReportTerm));
        awardReportTerm.setReportCode(null);
        Assert.assertFalse(awardReportTermRule.isReportCodeFieldComplete(awardReportTerm));        
    }

    @Test
    public final void testEvaluateRuleForFrequency() {
        awardReportTerm.setFrequencyCode("5");
        Assert.assertTrue(awardReportTermRule.isFrequencyCodeFieldComplete(awardReportTerm));
        awardReportTerm.setFrequencyCode(null);
        Assert.assertFalse(awardReportTermRule.isFrequencyCodeFieldComplete(awardReportTerm));
    }

    @Test
    public final void testEvaluateRuleForFrequencyBase() {
        awardReportTerm.setFrequencyBaseCode("5");
        Assert.assertTrue(awardReportTermRule.isFrequencyBaseCodeFieldComplete(awardReportTerm));
        awardReportTerm.setFrequencyBaseCode(null);
        Assert.assertFalse(awardReportTermRule.isFrequencyBaseCodeFieldComplete(awardReportTerm));
    }

    @Test
    public final void testEvaluateRuleForDistribution() {
        awardReportTerm.setOspDistributionCode("5");
        Assert.assertTrue(awardReportTermRule.isDistributionFieldComplete(awardReportTerm));
        awardReportTerm.setOspDistributionCode(null);
        Assert.assertFalse(awardReportTermRule.isDistributionFieldComplete(awardReportTerm));
    }

}
