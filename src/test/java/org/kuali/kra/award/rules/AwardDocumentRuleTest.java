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
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class tests the <code>AwardDocumentRule</code> class
 */
public class AwardDocumentRuleTest extends AwardDocumentRule{
    List<AwardFandaRate> awardFandaRateList;
    AwardFandaRate awardFandaRate1;
    AwardFandaRate awardFandaRate2;
    AwardDocumentRule awardDocumentRule;
    AwardReportTerm awardReportTerm;

    @Before
    public void setUp() throws Exception {
        awardFandaRateList = new ArrayList<AwardFandaRate>();
        awardDocumentRule = new AwardDocumentRule();
        awardFandaRate1 = new AwardFandaRate();
        awardFandaRate2 = new AwardFandaRate();
        awardReportTerm = new AwardReportTerm();
        
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
        
        
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardFandaRateList = null;
        awardDocumentRule = null;
        awardFandaRate1 = null;
        awardFandaRate2 = null;
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
