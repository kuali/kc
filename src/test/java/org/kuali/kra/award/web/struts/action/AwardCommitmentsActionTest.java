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
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.bo.CostShareType;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class Tests the methods in AwardAction.java
 */

public class AwardCommitmentsActionTest {
    
    AwardCommitmentsAction awardCommitmentsAction;
    Award award;
    AwardFandaRate awardFandaRate;
    AwardCostShare awardCostShare;
    public static final int ZERO = 0;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCommitmentsAction = new AwardCommitmentsAction();
        award = new Award();    
        //initialize BO's
        awardFandaRate = new AwardFandaRate();
        awardCostShare = new AwardCostShare();
      //initialize awardFandaRate
        awardFandaRate.setApplicableFandaRate(new KualiDecimal(5));
        awardFandaRate.setFiscalYear("2008");
        awardFandaRate.setFandaRateTypeCode(5);
        awardFandaRate.setOnCampusFlag("N");
        awardFandaRate.setUnderrecoveryOfIndirectCost(new KualiDecimal(1000));
        awardFandaRate.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate.setEndDate(new Date(new Long("1214852613046")));
        //initialize awardCostShare
        awardCostShare.setCostSharePercentage(new KualiDecimal(55));
        awardCostShare.setCostShareType(new CostShareType());
        awardCostShare.setDestination("testAccount1");
        awardCostShare.setSource("testAccount2");
        awardCostShare.setFiscalYear("2008");
        awardCostShare.setCommitmentAmount(new KualiDecimal(34000));
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        award = null;
        awardFandaRate = null;
        awardCostShare = null;
        awardCommitmentsAction = null;
    }
    
    @Test
    public void testAddFandaRateToAward(){
        Assert.assertTrue(awardCommitmentsAction.addFandaRateToAward(award, awardFandaRate));
    }
    
    @Test
    public void testDeleteFandaRateFromAward(){        
        awardCommitmentsAction.addFandaRateToAward(award, awardFandaRate);        
        awardCommitmentsAction.deleteFandaRateFromAward(award, 0);
        Assert.assertEquals(ZERO, award.getAwardFandaRate().size());        
    }
    
    @Test
    public void testAddCostShare(){
        Assert.assertTrue(awardCommitmentsAction.addCostShareToAward(award, awardCostShare));
    }
    
    @Test
    public void testDeleteCostShareFromAward(){        
        awardCommitmentsAction.addCostShareToAward(award, awardCostShare);        
        awardCommitmentsAction.deleteCostShareFromAward(award, 0);
        Assert.assertEquals(ZERO, award.getAwardCostShares().size());        
    }

}
