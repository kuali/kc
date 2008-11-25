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
import org.kuali.kra.award.bo.AwardIndirectCostRate;

/**
 * 
 * This class tests the <code>AwardDocumentRule</code> class
 */
public class AwardDocumentRuleTest {
    List<AwardIndirectCostRate> awardIndirectCostRateList;
    AwardIndirectCostRate awardIndirectCostRate1;
    AwardIndirectCostRate awardIndirectCostRate2;
    AwardDocumentRule awardDocumentRule;

    @Before
    public void setUp() throws Exception {
        awardIndirectCostRateList = new ArrayList<AwardIndirectCostRate>();
        awardDocumentRule = new AwardDocumentRule();
        awardIndirectCostRate1 = new AwardIndirectCostRate();
        awardIndirectCostRate2 = new AwardIndirectCostRate();
        awardIndirectCostRate1.setApplicableIndirectCostRate(new KualiDecimal(5));
        awardIndirectCostRate1.setFiscalYear("2008");
        awardIndirectCostRate1.setIdcRateTypeCode(5);
        awardIndirectCostRate1.setOnCampusFlag("N");
        awardIndirectCostRate1.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardIndirectCostRate1.setStartDate(new Date(new Long("1183316613046")));        
        awardIndirectCostRate1.setEndDate(new Date(new Long("1214852613046")));
        awardIndirectCostRate2.setApplicableIndirectCostRate(new KualiDecimal(5));
        awardIndirectCostRate2.setFiscalYear("2008");
        awardIndirectCostRate2.setIdcRateTypeCode(5);
        awardIndirectCostRate2.setOnCampusFlag("F");
        awardIndirectCostRate2.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardIndirectCostRate2.setStartDate(new Date(new Long("1183316613046")));        
        awardIndirectCostRate2.setEndDate(new Date(new Long("1214852613046")));
        awardIndirectCostRateList.add(awardIndirectCostRate1);
        awardIndirectCostRateList.add(awardIndirectCostRate2);
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardIndirectCostRateList = null;
        awardDocumentRule = null;
        awardIndirectCostRate1 = null;
        awardIndirectCostRate2 = null;
    }

    @Test
    public final void testIsIndirectCostRateInputInPairs() {
        Assert.assertTrue(awardDocumentRule.isIndirectCostRateInputInPairs(awardIndirectCostRateList));
    }

}
