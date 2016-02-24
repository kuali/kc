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
package org.kuali.kra.award.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.CostShareType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

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
        awardFandaRate.setApplicableFandaRate(new ScaleTwoDecimal(5));
        awardFandaRate.setFiscalYear("2008");
        awardFandaRate.setFandaRateTypeCode("5");
        awardFandaRate.setOnCampusFlag("N");
        awardFandaRate.setUnderrecoveryOfIndirectCost(new ScaleTwoDecimal(1000));
        awardFandaRate.setStartDate(new Date(new Long("1183316613046")));        
        awardFandaRate.setEndDate(new Date(new Long("1214852613046")));
        //initialize awardCostShare
        awardCostShare.setCostSharePercentage(new ScaleTwoDecimal(55));
        awardCostShare.setCostShareType(new CostShareType());
        awardCostShare.setDestination("testAccount1");
        awardCostShare.setSource("testAccount2");
        awardCostShare.setProjectPeriod("2008");
        awardCostShare.setCommitmentAmount(new ScaleTwoDecimal(34000));
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
}
