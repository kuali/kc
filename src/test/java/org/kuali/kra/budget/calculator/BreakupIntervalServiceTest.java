/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.rates.BudgetLaRate;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

/**
 * This class is to test different scenarios of BreakupInterval calculation
 */
public class BreakupIntervalServiceTest  extends KcUnitTestBase{

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.kuali.kra.budget.calculator.BreakupIntervalService#calculate(org.kuali.kra.budget.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculate() {
        BreakUpInterval bi  = createBreakupInterval();
        KraServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
        List<RateAndCost> rateAndCosts = bi.getRateAndCosts();
        RateAndCost rateAndCost1 = rateAndCosts.get(0);
        validateResults(rateAndCost1,3375,675,6750,1350);
        RateAndCost rateAndCost2 = rateAndCosts.get(1);
        validateResults(rateAndCost2,1250,250,5000,1000);
        RateAndCost rateAndCost3 = rateAndCosts.get(2);
        validateResults(rateAndCost3,500,100,5000,1000);
        RateAndCost rateAndCost4 = rateAndCosts.get(3);
        validateResults(rateAndCost4,250,50,5000,1000);
        RateAndCost rateAndCost5 = rateAndCosts.get(6);
        validateResults(rateAndCost5,7.50,1.50,250,50);
        RateAndCost rateAndCost6 = rateAndCosts.get(7);
        validateResults(rateAndCost6,5,1,250,50);
    }
    private void validateResults(RateAndCost rateAndCost, double calculatedCost, double calculatedCostSharing, double baseAmount, double baseCostSharing) {
        assertEquals(rateAndCost.getCalculatedCost(),new BudgetDecimal(calculatedCost));
        assertEquals(rateAndCost.getCalculatedCostSharing(),new BudgetDecimal(calculatedCostSharing));
        assertEquals(rateAndCost.getBaseAmount(),new BudgetDecimal(baseAmount));
        assertEquals(rateAndCost.getBaseCostSharingAmount(),new BudgetDecimal(baseCostSharing));
    }
    
    @Test
    public void testCalculateWithApplyFlag() {
        BreakUpInterval bi  = createBreakupInterval();
        RateAndCost rc = bi.getRateAndCosts().get(1);
        rc.setApplyRateFlag(false);
        bi.getRateAndCosts().get(2).setApplyRateFlag(false);
        KraServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
        List<RateAndCost> rateAndCosts = bi.getRateAndCosts();
        RateAndCost rateAndCost1 = rateAndCosts.get(0);
        validateResults(rateAndCost1,2500,500,5000,1000);
        RateAndCost rateAndCost2 = rateAndCosts.get(1);
        validateResults(rateAndCost2,0,0,0,0);
        RateAndCost rateAndCost3 = rateAndCosts.get(2);
        validateResults(rateAndCost3,0,0,0,0);
        RateAndCost rateAndCost4 = rateAndCosts.get(3);
        validateResults(rateAndCost4,250,50,5000,1000);
        RateAndCost rateAndCost5 = rateAndCosts.get(6);
        validateResults(rateAndCost5,7.50,1.50,250,50);
        RateAndCost rateAndCost6 = rateAndCosts.get(7);
        validateResults(rateAndCost6,5,1,250,50);
    }
    /**
     * Test method for {@link org.kuali.kra.budget.calculator.BreakupIntervalService#calculate(org.kuali.kra.budget.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculateOverrecovery() {
        BreakUpInterval bi1 = createBreakupInterval();
        bi1.setURRatesBean(getBudgetRate("1",40,1l,"2011",40,true,"1","2","O","07/01/2009","000001"));
        KraServiceLocator.getService(BreakupIntervalService.class).calculate(bi1);
        assertEquals(bi1.getUnderRecovery(), new BudgetDecimal(-810.0));
    }
    @Test
    public void testCalculateUnderrecoveryWithApplyFlag() {
        BreakUpInterval bi = createBreakupInterval();
        RateAndCost ohRateItem = bi.getRateAndCosts().get(0);
        ohRateItem.setApplyRateFlag(false);
        KraServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
        assertEquals(bi.getUnderRecovery(), new BudgetDecimal(4050.00));
    }

    /**
     * This method...
     * @return
     */
    private BreakUpInterval createBreakupInterval() {
        BreakUpInterval bi1  = new BreakUpInterval();
        bi1.setApplicableAmt(new BudgetDecimal(5000));
        bi1.setApplicableAmtCostSharing(new BudgetDecimal(1000));
        bi1.setBoundary(new Boundary(getDate("01/01/2010"),getDate("06/30/2010")));
        bi1.setBudgetPeriod(1);
        bi1.setBudgetId(1l);
        bi1.setBudgetProposalLaRates(getBudgetLArates());
        bi1.setBudgetProposalRates(getBudgetRates());
        bi1.setLineItemNumber(1);
        bi1.setRateAndCosts(createRateAndCosts());
        bi1.setRateNumber(1);
        return bi1;
    }


    /**
     * This method...
     * @param rateClassCode
     * @param rateTypeCode
     * @param rateClassType
     * @param rateAndCosts
     */
    private RateAndCost getRateCost(String rateClassCode, String rateTypeCode, String rateClassType,double rate) {
        RateAndCost rateCost = new RateAndCost();
        rateCost.setApplyRateFlag(true);
        rateCost.setRateClassType(rateClassType);
        rateCost.setRateClassCode(rateClassCode);
        rateCost.setRateTypeCode(rateTypeCode);
        rateCost.setAppliedRate(new BudgetDecimal(rate));
        rateCost.setCalculatedCost(BudgetDecimal.ZERO);
        rateCost.setCalculatedCostSharing(BudgetDecimal.ZERO);
        return rateCost;
    }

    private QueryList<RateAndCost> createRateAndCosts() {
        QueryList<RateAndCost> rateAndCosts = new QueryList<RateAndCost>();
        RateAndCost rc1 = getRateCost("1", "1", "O",50);
        rateAndCosts.add(rc1);
        RateAndCost rc2 = getRateCost("5", "1", "E",25);
        rateAndCosts.add(rc2);
        RateAndCost rc3 = getRateCost("8", "1", "V",10);
        rateAndCosts.add(rc3);
        RateAndCost rc4 = getRateCost("10", "1", "Y",5);
        rateAndCosts.add(rc4);
        RateAndCost rc5 = getRateCost("11", "1", "L",5);
        rateAndCosts.add(rc5);
        RateAndCost rc6 = getRateCost("12", "1", "L",5);
        rateAndCosts.add(rc6);
        RateAndCost rc7 = getRateCost("5", "3", "E",3);
        rateAndCosts.add(rc7);
        RateAndCost rc8 = getRateCost("8", "2", "V",2);
        rateAndCosts.add(rc8);
        return rateAndCosts;
    }
    private QueryList<BudgetRate> getBudgetRates() {
        QueryList<BudgetRate> rates=new QueryList<BudgetRate>();
        BudgetRate brO1 = getBudgetRate("1",50,1l,"2010",50,true,"1","1","O","07/01/2009","000001");
        BudgetRate brO2 = getBudgetRate("1",45,1l,"2010",45,true,"2","1","O","07/01/2009","000001");
        BudgetRate brO3 = getBudgetRate("1",40,1l,"2010",40,true,"3","1","O","07/01/2009","000001");
        BudgetRate brE = getBudgetRate("1",25,1l,"2010",25,true,"5","1","E","07/01/2009","000001");
        BudgetRate brV = getBudgetRate("1",10,1l,"2010",10,true,"8","1","V","07/01/2009","000001");
        BudgetRate brELA = getBudgetRate("1",3,1l,"2010",3,true,"5","3","E","07/01/2009","000001");
        BudgetRate brVLA = getBudgetRate("1",2,1l,"2010",2,true,"8","2","V","07/01/2009","000001");
        BudgetRate brOt = getBudgetRate("1",8,1l,"2010",8,true,"9","1","X","07/01/2009","000001");
        rates.add(brO1);
        rates.add(brO2);
        rates.add(brO3);
        rates.add(brE);
        rates.add(brV);
        rates.add(brOt);
        rates.add(brELA);
        rates.add(brVLA);
        return rates;
    }
    private QueryList<BudgetLaRate> getBudgetLArates() {
        QueryList<BudgetLaRate> rates=new QueryList<BudgetLaRate>();
        BudgetLaRate br7 = getBudgetLaRate(5,1l,"2010",5,true,"10","1","Y","07/01/2009","000001");
        BudgetLaRate br8 = getBudgetLaRate(5,1l,"2010",5,true,"11","1","L","07/01/2009","000001");
        BudgetLaRate br9 = getBudgetLaRate(5,1l,"2010",5,true,"12","1","L","07/01/2009","000001");
        rates.add(br7);
        rates.add(br8);
        rates.add(br9);
        return rates;
    }

    /**
     * This method...
     * @return
     */
    private BudgetRate getBudgetRate(String actCode,double rate,Long bgtId,String year,
                                    double instRate,boolean campFlag,String rcCode,String rtCode,String rct,String stDate,String unitNumber) {
        BudgetRate br1 = new BudgetRate();
        br1.setActivityTypeCode(actCode);
        br1.setApplicableRate(new BudgetDecimal(rate));
        br1.setBudgetId(bgtId);
        br1.setFiscalYear(year);
        br1.setInstituteRate(new BudgetDecimal(instRate));
        br1.setOnOffCampusFlag(campFlag);
        br1.setRateClassCode(rcCode);
        br1.setRateTypeCode(rtCode);
        br1.setRateClass(getRateClass(rcCode,rct));
        br1.setStartDate(new java.sql.Date(getDate(stDate).getTime()));
        br1.setUnitNumber(unitNumber);
        return br1;
    }

    private RateClass getRateClass(String rateClassCode,String rateClassType) {
        RateClass rc = new RateClass();
        rc.setRateClassCode(rateClassCode);
        rc.setRateClassType(rateClassType);
        return rc;
    }


    private BudgetLaRate getBudgetLaRate(double rate,Long bgtId,String year,
            double instRate,boolean campFlag,String rcCode,String rtCode,String rct,String stDate,String unitNumber) {
        BudgetLaRate br1 = new BudgetLaRate();
        br1.setApplicableRate(new BudgetDecimal(rate));
        br1.setBudgetId(bgtId);
        br1.setFiscalYear(year);
        br1.setInstituteRate(new BudgetDecimal(instRate));
        br1.setOnOffCampusFlag(campFlag);
        br1.setRateClassCode(rcCode);
        br1.setRateTypeCode(rtCode);
        br1.setRateClass(getRateClass(rcCode,rct));
        br1.setStartDate(new java.sql.Date(getDate(stDate).getTime()));
        br1.setUnitNumber(unitNumber);
        return br1;
    }

    private Date getDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateString);
        }catch (ParseException e) {
            fail(dateString +" is not parsable");
            return null;
        }
    }

}
