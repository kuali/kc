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
package org.kuali.kra.award.web.struts.action;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardIndirectCostRate;

/**
 * 
 * This class Tests the methods in AwardAction.java
 */

public class AwardTimeAndMoneyActionTest {
    
    AwardTimeAndMoneyAction awardTimeAndMoneyAction;
    Award award;
    AwardIndirectCostRate awardIndirectCostRate;
    public static final String MOCK_FORWARD_STRING = "FORWARD_STRING";
    public static final String MOCK_DOC_ID_REQUEST_PARAMETER = "21";
    public static final String MOCK_EXPECTED_RESULT_STRING = "FORWARD_STRING?docId=21";
    public static final int ZERO = 0;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardTimeAndMoneyAction = new AwardTimeAndMoneyAction();
        award = new Award();        
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
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardTimeAndMoneyAction = null;
    }
    
    @Test
    public void testAddFandaRateToAward(){
        Assert.assertTrue(awardTimeAndMoneyAction.addFandaRateToAward(award, awardIndirectCostRate));
    }
    
    @Test
    public void testDeleteFandaRateFromAward(){        
        awardTimeAndMoneyAction.addFandaRateToAward(award, awardIndirectCostRate);        
        awardTimeAndMoneyAction.deleteFandaRateFromAward(award, 0);
        Assert.assertEquals(ZERO, award.getAwardIndirectCostRate().size());        
    }

}
