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
package org.kuali.kra.award;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
        
        awardFandaRate1.setApplicableFandaRate(new ScaleTwoDecimal(5));
        awardFandaRate1.setFiscalYear("2008");
        awardFandaRate1.setFandaRateTypeCode("5");
        awardFandaRate1.setOnCampusFlag("N");
        awardFandaRate1.setUnderrecoveryOfIndirectCost(new ScaleTwoDecimal(1000));
        awardFandaRate1.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate1.setEndDate(new Date(new Long("1214852613046")));
        awardFandaRate2.setApplicableFandaRate(new ScaleTwoDecimal(5));
        awardFandaRate2.setFiscalYear("2008");
        awardFandaRate2.setFandaRateTypeCode("5");
        awardFandaRate2.setOnCampusFlag("F");
        awardFandaRate2.setUnderrecoveryOfIndirectCost(new ScaleTwoDecimal(1000));
        awardFandaRate2.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate2.setEndDate(new Date(new Long("1214852613046")));
        awardFandaRateList.add(awardFandaRate1);
        awardFandaRateList.add(awardFandaRate2);
        
        
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @After
    public void tearDown() throws Exception {
        awardFandaRateList = null;
        awardDocumentRule = null;
        awardFandaRate1 = null;
        awardFandaRate2 = null;
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
    
    protected List<KeyValue> getReportCodes(String reportClassCode){        
        List<KeyValue> reportCodes = new ArrayList<KeyValue>();
        reportCodes.add(new ConcreteKeyValue("5", "Final"));
        reportCodes.add(new ConcreteKeyValue("39", "SF Something"));
        return reportCodes;
    }
    
    protected List<KeyValue> getFrequencyBaseCodes(String frequencyCode){        
        List<KeyValue> frequencyBaseCodes = new ArrayList<KeyValue>();
        frequencyBaseCodes.add(new ConcreteKeyValue("5", "Final"));
        return frequencyBaseCodes;
    }
   

}
