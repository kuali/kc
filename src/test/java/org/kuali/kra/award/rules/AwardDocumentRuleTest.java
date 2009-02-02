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
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardApprovedSubaward;
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.bo.AwardReportTerm;

/**
 * 
 * This class tests the <code>AwardDocumentRule</code> class
 */
public class AwardDocumentRuleTest extends AwardDocumentRule{
    List<AwardFandaRate> awardFandaRateList;
    AwardFandaRate awardFandaRate1;
    AwardFandaRate awardFandaRate2;
    AwardDocumentRule awardDocumentRule;
    AwardCostShare awardCostShare;
    AwardApprovedSubaward awardApprovedSubaward1;
    AwardApprovedSubaward awardApprovedSubaward2;
    List<AwardApprovedSubaward> awardApprovedSubawards;
    AwardReportTerm awardReportTerm;

    @Before
    public void setUp() throws Exception {
        awardFandaRateList = new ArrayList<AwardFandaRate>();
        awardDocumentRule = new AwardDocumentRule();
        awardFandaRate1 = new AwardFandaRate();
        awardFandaRate2 = new AwardFandaRate();
        awardCostShare = new AwardCostShare();
        awardApprovedSubaward1 = new AwardApprovedSubaward();
        awardApprovedSubaward2 = new AwardApprovedSubaward();
        awardReportTerm = new AwardReportTerm();
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        
        awardFandaRate1.setApplicableFandaRate(new KualiDecimal(5));
        awardFandaRate1.setFiscalYear("2008");
        awardFandaRate1.setFandaRateTypeCode(5);
        awardFandaRate1.setOnCampusFlag("N");
        awardFandaRate1.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardFandaRate1.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate1.setEndDate(new Date(new Long("1214852613046")));
        awardFandaRate2.setApplicableFandaRate(new KualiDecimal(5));
        awardFandaRate2.setFiscalYear("2008");
        awardFandaRate2.setFandaRateTypeCode(5);
        awardFandaRate2.setOnCampusFlag("F");
        awardFandaRate2.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardFandaRate2.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate2.setEndDate(new Date(new Long("1214852613046")));
        awardFandaRateList.add(awardFandaRate1);
        awardFandaRateList.add(awardFandaRate2);
        
        awardCostShare.setCostSharePercentage(new KualiDecimal(50));
        awardCostShare.setDestination("12345");
        awardCostShare.setSource("54321");
        awardCostShare.setCommitmentAmount(new KualiDecimal(25000));
        awardCostShare.setFiscalYear("2009");
        
        awardApprovedSubaward1.setOrganizationName("test organization 1");
        awardApprovedSubaward1.setAmount(new KualiDecimal(10000));
        awardApprovedSubaward2.setOrganizationName("test organization 2");
        awardApprovedSubaward2.setAmount(new KualiDecimal(10000));
        awardApprovedSubawards.add(awardApprovedSubaward1);
        awardApprovedSubawards.add(awardApprovedSubaward2);
        
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardFandaRateList = null;
        awardDocumentRule = null;
        awardFandaRate1 = null;
        awardFandaRate2 = null;
        awardCostShare = null;
    }

    @Test
    public final void testIsFandaRateInputInPairs() {
        awardFandaRateList.add(new AwardFandaRate());
        awardFandaRateList.add(new AwardFandaRate());
        awardFandaRateList.get(2).setFandaRateTypeCode(6);
        awardFandaRateList.get(2).setOnCampusFlag("F");
        awardFandaRateList.get(3).setFandaRateTypeCode(6);        
        awardFandaRateList.get(3).setOnCampusFlag("N");        
        Assert.assertTrue(awardDocumentRule.isFandaRateInputInPairs(awardFandaRateList));
        awardFandaRateList.get(0).setOnCampusFlag("F");
        awardFandaRateList.get(1).setOnCampusFlag("F");
        awardFandaRateList.get(2).setOnCampusFlag("F");        
        awardFandaRateList.get(3).setOnCampusFlag("F");
        Assert.assertFalse(awardDocumentRule.isFandaRateInputInPairs(awardFandaRateList));
    }
    
    @Test
    public final void testTestCostShareSourceAndDestinationForEquality(){
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        Assert.assertTrue(awardDocumentRule.testCostShareSourceAndDestinationForEquality(awardCostShare, errorMap));
        awardCostShare.setDestination("54321");
        Assert.assertFalse(awardDocumentRule.testCostShareSourceAndDestinationForEquality(awardCostShare, errorMap));
        awardCostShare.setDestination("12345");
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test
    public final void testTestCostShareFiscalYearRange(){
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        Assert.assertTrue(awardDocumentRule.testCostShareFiscalYearRange(awardCostShare, errorMap));
        awardCostShare.setFiscalYear("1800");
        Assert.assertFalse(awardDocumentRule.testCostShareFiscalYearRange(awardCostShare, errorMap));
        awardCostShare.setFiscalYear("2600");
        Assert.assertFalse(awardDocumentRule.testCostShareFiscalYearRange(awardCostShare, errorMap));
        awardCostShare.setFiscalYear("2009");
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test
    public final void testTestApprovedSubawardDuplicateOrganization(){
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        String errorPath = "test errorPath";
        int currentIndex = 0;
        Assert.assertTrue(awardDocumentRule.testApprovedSubawardDuplicateOrganization(awardApprovedSubawards, awardApprovedSubaward1, errorMap, currentIndex, errorPath));
        awardApprovedSubaward1.setOrganizationName("test organization 2");
        Assert.assertFalse(awardDocumentRule.testApprovedSubawardDuplicateOrganization(awardApprovedSubawards, awardApprovedSubaward1, errorMap, currentIndex, errorPath));
        awardApprovedSubaward1.setOrganizationName("test organization 1");
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test
    public final void testTestApprovedSubawardAmount(){
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        Assert.assertTrue(awardDocumentRule.testApprovedSubawardAmount(awardApprovedSubaward1, errorMap));
        awardApprovedSubaward1.setAmount(new KualiDecimal(0));
        Assert.assertFalse(awardDocumentRule.testApprovedSubawardAmount(awardApprovedSubaward1, errorMap));
        awardApprovedSubaward1.setAmount(new KualiDecimal(-100));
        Assert.assertFalse(awardDocumentRule.testApprovedSubawardAmount(awardApprovedSubaward1, errorMap));
        awardApprovedSubaward1.setAmount(new KualiDecimal(10000));
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @Test
    public final void testEvaluateBusinessRuleForReportCodeField(){
        int i=0;
        awardReportTerm.setReportClassCode("1");
        awardReportTerm.setReportCode("5");        
        Assert.assertTrue(awardDocumentRule.isValidReportCode(awardReportTerm, getReportCodes("1")));
        
        awardReportTerm.setReportClassCode("1");
        awardReportTerm.setReportCode("36");
        Assert.assertFalse(awardDocumentRule.isValidReportCode(awardReportTerm, getReportCodes("1")));
    }
    
    @Test
    public final void testIsValidFrequencyBase(){
        awardReportTerm.setFrequencyBaseCode("5");
        Assert.assertTrue(awardDocumentRule.isValidFrequencyBase(awardReportTerm, getFrequencyBaseCodes("5")));
        awardReportTerm.setFrequencyBaseCode("6");
        Assert.assertFalse(awardDocumentRule.isValidFrequencyBase(awardReportTerm, getFrequencyBaseCodes("5")));
    }
    
    protected List<KeyLabelPair> getReportCodes(String reportClassCode){        
        List<KeyLabelPair> reportCodes = new ArrayList<KeyLabelPair>();
        reportCodes.add(new KeyLabelPair("5", "Final"));
        reportCodes.add(new KeyLabelPair("39", "SF Something"));
        return reportCodes;
    }
    
    protected List<KeyLabelPair> getFrequencyCodes(String reportClassCode, String reportCode){        
        List<KeyLabelPair> frequencyCodes = new ArrayList<KeyLabelPair>();
        frequencyCodes.add(new KeyLabelPair("5", "Final"));
        return frequencyCodes;
    }
    
    protected List<KeyLabelPair> getFrequencyBaseCodes(String frequencyCode){        
        List<KeyLabelPair> frequencyBaseCodes = new ArrayList<KeyLabelPair>();
        frequencyBaseCodes.add(new KeyLabelPair("5", "Final"));
        return frequencyBaseCodes;
    }
   

}
