/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.budget.calculator;


import org.junit.Test;
import org.kuali.coeus.common.budget.framework.calculator.*;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.impl.calculator.Boundary;
import org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval;
import org.kuali.coeus.common.budget.impl.calculator.RateAndCost;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
/**
 * This class is to test different scenarios of BreakupInterval calculation
 */
public class BreakupIntervalServiceTest  extends KcIntegrationTestBase {

    /**
     * Test method for {@link org.kuali.coeus.common.budget.framework.calculator.BreakupIntervalService#calculate(org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculate() {
        BreakUpInterval bi  = createBreakupInterval();
        KcServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
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
        assertEquals(rateAndCost.getCalculatedCost(),new ScaleTwoDecimal(calculatedCost));
        assertEquals(rateAndCost.getCalculatedCostSharing(),new ScaleTwoDecimal(calculatedCostSharing));
        assertEquals(rateAndCost.getBaseAmount(),new ScaleTwoDecimal(baseAmount));
        assertEquals(rateAndCost.getBaseCostSharingAmount(),new ScaleTwoDecimal(baseCostSharing));
    }
    
    @Test
    public void testCalculateWithApplyFlag() {
        BreakUpInterval bi  = createBreakupInterval();
        RateAndCost rc = bi.getRateAndCosts().get(1);
        rc.setApplyRateFlag(false);
        bi.getRateAndCosts().get(2).setApplyRateFlag(false);
        KcServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
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
     * Test method for {@link org.kuali.coeus.common.budget.framework.calculator.BreakupIntervalService#calculate(org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculateOverrecovery() {
        BreakUpInterval bi1 = createBreakupInterval();
        bi1.setURRatesBean(getBudgetRate("1",40,1L,"2011",40,true,"1","2","O","07/01/2009","000001"));
        KcServiceLocator.getService(BreakupIntervalService.class).calculate(bi1);
        assertEquals(bi1.getUnderRecovery(), new ScaleTwoDecimal(-810.0));
    }
    @Test
    public void testCalculateUnderrecoveryWithApplyFlag() {
        BreakUpInterval bi = createBreakupInterval();
        RateAndCost ohRateItem = bi.getRateAndCosts().get(0);
        ohRateItem.setApplyRateFlag(false);
        KcServiceLocator.getService(BreakupIntervalService.class).calculate(bi);
        assertEquals(bi.getUnderRecovery(), new ScaleTwoDecimal(4050.00));
    }

    private BreakUpInterval createBreakupInterval() {
        BreakUpInterval bi1  = new BreakUpInterval();
        bi1.setApplicableAmt(new ScaleTwoDecimal(5000));
        bi1.setApplicableAmtCostSharing(new ScaleTwoDecimal(1000));
        bi1.setBoundary(new Boundary(getDate("01/01/2010"),getDate("06/30/2010")));
        bi1.setBudgetPeriod(1);
        bi1.setBudgetId(1L);
        bi1.setBudgetProposalLaRates(getBudgetLArates());
        bi1.setBudgetProposalRates(getBudgetRates());
        bi1.setLineItemNumber(1);
        bi1.setRateAndCosts(createRateAndCosts());
        bi1.setRateNumber(1);
        return bi1;
    }

    private RateAndCost getRateCost(String rateClassCode, String rateTypeCode, String rateClassType,double rate) {
        RateAndCost rateCost = new RateAndCost();
        rateCost.setApplyRateFlag(true);
        rateCost.setRateClassType(rateClassType);
        rateCost.setRateClassCode(rateClassCode);
        rateCost.setRateTypeCode(rateTypeCode);
        rateCost.setAppliedRate(new ScaleTwoDecimal(rate));
        rateCost.setCalculatedCost(ScaleTwoDecimal.ZERO);
        rateCost.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
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
        BudgetRate brO1 = getBudgetRate("1",50,1L,"2010",50,true,"1","1","O","07/01/2009","000001");
        BudgetRate brO2 = getBudgetRate("1",45,1L,"2010",45,true,"2","1","O","07/01/2009","000001");
        BudgetRate brO3 = getBudgetRate("1",40,1L,"2010",40,true,"3","1","O","07/01/2009","000001");
        BudgetRate brE = getBudgetRate("1",25,1L,"2010",25,true,"5","1","E","07/01/2009","000001");
        BudgetRate brV = getBudgetRate("1",10,1L,"2010",10,true,"8","1","V","07/01/2009","000001");
        BudgetRate brELA = getBudgetRate("1",3,1L,"2010",3,true,"5","3","E","07/01/2009","000001");
        BudgetRate brVLA = getBudgetRate("1",2,1L,"2010",2,true,"8","2","V","07/01/2009","000001");
        BudgetRate brOt = getBudgetRate("1",8,1L,"2010",8,true,"9","1","X","07/01/2009","000001");
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
        BudgetLaRate br7 = getBudgetLaRate(5,1L,"2010",5,true,"10","1","Y","07/01/2009","000001");
        BudgetLaRate br8 = getBudgetLaRate(5,1L,"2010",5,true,"11","1","L","07/01/2009","000001");
        BudgetLaRate br9 = getBudgetLaRate(5,1L,"2010",5,true,"12","1","L","07/01/2009","000001");
        rates.add(br7);
        rates.add(br8);
        rates.add(br9);
        return rates;
    }

    private BudgetRate getBudgetRate(String actCode,double rate,Long bgtId,String year,
                                    double instRate,boolean campFlag,String rcCode,String rtCode,String rct,String stDate,String unitNumber) {
        BudgetRate br1 = new BudgetRate();
        br1.setActivityTypeCode(actCode);
        br1.setApplicableRate(new ScaleTwoDecimal(rate));
        br1.setBudgetId(bgtId);
        br1.setFiscalYear(year);
        br1.setInstituteRate(new ScaleTwoDecimal(instRate));
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
        rc.setCode(rateClassCode);
        rc.setRateClassTypeCode(rateClassType);
        return rc;
    }


    private BudgetLaRate getBudgetLaRate(double rate,Long bgtId,String year,
            double instRate,boolean campFlag,String rcCode,String rtCode,String rct,String stDate,String unitNumber) {
        BudgetLaRate br1 = new BudgetLaRate();
        br1.setApplicableRate(new ScaleTwoDecimal(rate));
        br1.setBudgetId(bgtId);
        br1.setFiscalYear(year);
        br1.setInstituteRate(new ScaleTwoDecimal(instRate));
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
