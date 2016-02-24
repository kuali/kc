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
package org.kuali.kra.award.paymentreports.awardreports;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AwardReportTermRuleImplTest extends KcIntegrationTestBase {
    
    AwardReportTermRuleImpl awardReportTermRule;
    AwardReportTerm awardReportTerm;
    List<AwardReportTerm> awardReportTermItems;

    @Before
    public void setUp() throws Exception {
        awardReportTermRule = new AwardReportTermRuleImpl();
        awardReportTerm = buildAwardReportTerm();
        awardReportTermItems = new ArrayList<AwardReportTerm>();
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRule = null;
        awardReportTerm = null;
        awardReportTermItems = null;
    }
    
    private AwardReportTerm buildAwardReportTerm() {
        AwardReportTerm awardReportTerm = new AwardReportTerm();
        awardReportTerm.setReportCode("5");
        awardReportTerm.setFrequencyCode("5");
        awardReportTerm.setFrequencyBaseCode("5");
        awardReportTerm.setOspDistributionCode("5");
        return awardReportTerm;
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
    public final void testValidAwardReportTerm(){
        Assert.assertTrue(awardReportTermRule.validateRequiredFields(awardReportTerm, ""));
    }

    @Test
    public final void testEvaluateRuleForReportCode() {
        awardReportTerm.setReportCode(null);
        Assert.assertFalse(awardReportTermRule.validateRequiredFields(awardReportTerm, ""));        
    }

    @Test
    public final void testEvaluateRuleForFrequency() {
        awardReportTerm.setFrequencyCode(null);
        Assert.assertFalse(awardReportTermRule.validateRequiredFields(awardReportTerm, ""));
    }

    @Test
    public final void testEvaluateRuleForFrequencyBase() {
        awardReportTerm.setFrequencyBaseCode(null);
        Assert.assertFalse(awardReportTermRule.validateRequiredFields(awardReportTerm, ""));
    }

    @Test
    public final void testEvaluateRuleForDistribution() {
        awardReportTerm.setOspDistributionCode(null);
        Assert.assertFalse(awardReportTermRule.validateRequiredFields(awardReportTerm, ""));
    }

}
