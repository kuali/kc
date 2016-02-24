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
package org.kuali.coeus.common.budget.impl.calculator;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.impl.calculator.Boundary;
import org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval;
import org.kuali.coeus.common.budget.impl.calculator.BreakupIntervalServiceImpl;
import org.kuali.coeus.common.budget.impl.calculator.RateAndCost;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateClassBaseExclusion;
import org.kuali.coeus.common.budget.framework.rate.RateClassBaseInclusion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
/**
 * This class is to test different scenarios of BreakupInterval calculation
 */
public class BreakupIntervalServiceTest {
	
	protected BreakupIntervalServiceImpl breakupIntervalService;
	protected List<RateClassBaseInclusion> defaultRateClassBaseInclusions;
	protected List<RateClassBaseExclusion> defaultRateClassBaseExclusions;
	
	@Before
	public void setup() {
		defaultRateClassBaseInclusions = new ArrayList<>();
		defaultRateClassBaseInclusions.add(getInclusion(1L, "1", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(2L, "1", null, "5", null));
		defaultRateClassBaseInclusions.add(getInclusion(3L, "1", null, "8", null));
		defaultRateClassBaseInclusions.add(getInclusion(4L, "10", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(5L, "11", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(6L, "12", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(7L, "5", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(8L, "8", null, "0", null));
		defaultRateClassBaseInclusions.add(getInclusion(10L, "5", "3", "10", null));
		defaultRateClassBaseInclusions.add(getInclusion(11L, "8", "2", "10", null));
		
		defaultRateClassBaseExclusions = new ArrayList<>();
		defaultRateClassBaseExclusions.add(getExclusion(1L, "1", null, "5", "3"));
		defaultRateClassBaseExclusions.add(getExclusion(1L, "1", null, "8", "2"));
		defaultRateClassBaseExclusions.add(getExclusion(1L, "5", "3", "0", null));
		defaultRateClassBaseExclusions.add(getExclusion(1L, "8", "2", "0", null));

		BusinessObjectService boService = mock(BusinessObjectService.class);
		when(boService.findAll(RateClassBaseInclusion.class)).thenReturn(defaultRateClassBaseInclusions);
		when(boService.findBySinglePrimaryKey(eq(RateClassBaseInclusion.class), anyLong())).thenAnswer(new Answer<RateClassBaseInclusion>() {
			 public RateClassBaseInclusion answer(InvocationOnMock invocation) {
		         Long id = (Long) invocation.getArguments()[1];
		         return defaultRateClassBaseInclusions.stream()
		        		 .filter(rateInclusion -> id.equals(rateInclusion.getRateClassBaseInclusionId()))
		        		 .findFirst().orElse(null);
			 }
		});
		when(boService.findAll(RateClassBaseExclusion.class)).thenReturn(defaultRateClassBaseExclusions);
		
		ParameterService parameterService = mock(ParameterService.class);
		when(parameterService.parameterExists(Constants.MODULE_NAMESPACE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, BreakupIntervalServiceImpl.BREAKUP_SERVICE_USE_NEW_CALCULATION_PARAM)).thenReturn(true);
		when(parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, "breakupServiceUseNewCalculation")).thenReturn(false);
		breakupIntervalService = new BreakupIntervalServiceImpl();
		breakupIntervalService.setBusinessObjectService(boService);
		breakupIntervalService.setParameterService(parameterService);
	}

    /**
     * Test method for {@link org.kuali.coeus.common.budget.framework.calculator.BreakupIntervalService#calculate(org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculate() {
        BreakUpInterval bi  = createBreakupInterval();
        breakupIntervalService.calculate(bi);
        List<RateAndCost> rateAndCosts = bi.getRateAndCosts();
        validateResults(rateAndCosts.get(0),3375,675,6750,1350);
        validateResults(rateAndCosts.get(1),1250,250,5000,1000);
        validateResults(rateAndCosts.get(2),500,100,5000,1000);
        validateResults(rateAndCosts.get(3),250,50,5000,1000);
        validateResults(rateAndCosts.get(6),7.50,1.50,250,50);
        validateResults(rateAndCosts.get(7),5,1,250,50);
    }
    protected void validateResults(RateAndCost rateAndCost, double calculatedCost, double calculatedCostSharing, double baseAmount, double baseCostSharing) {
        assertEquals(new ScaleTwoDecimal(calculatedCost), rateAndCost.getCalculatedCost());
        assertEquals(new ScaleTwoDecimal(calculatedCostSharing), rateAndCost.getCalculatedCostSharing());
        assertEquals(new ScaleTwoDecimal(baseAmount), rateAndCost.getBaseAmount());
        assertEquals(new ScaleTwoDecimal(baseCostSharing), rateAndCost.getBaseCostSharingAmount());
    }
    
    @Test
    public void testCalculateWithApplyFlag() {
        BreakUpInterval bi  = createBreakupInterval();
        RateAndCost rc = bi.getRateAndCosts().get(1);
        rc.setApplyRateFlag(false);
        bi.getRateAndCosts().get(2).setApplyRateFlag(false);
        breakupIntervalService.calculate(bi);
        List<RateAndCost> rateAndCosts = bi.getRateAndCosts();
        validateResults(rateAndCosts.get(0),2500,500,5000,1000);
        validateResults(rateAndCosts.get(1),0,0,0,0);
        validateResults(rateAndCosts.get(2),0,0,0,0);
        validateResults(rateAndCosts.get(3),250,50,5000,1000);
        validateResults(rateAndCosts.get(6),7.50,1.50,250,50);
        validateResults(rateAndCosts.get(7),5,1,250,50);
    }
    /**
     * Test method for {@link org.kuali.coeus.common.budget.framework.calculator.BreakupIntervalService#calculate(org.kuali.coeus.common.budget.impl.calculator.BreakUpInterval)}.
     */
    @Test
    public void testCalculateOverrecovery() {
        BreakUpInterval bi1 = createBreakupInterval();
        bi1.setURRatesBean(getBudgetRate("1",40,1L,"2011",40,true,"1","2","O","07/01/2009","000001"));
        breakupIntervalService.calculate(bi1);
        assertEquals(bi1.getUnderRecovery(), new ScaleTwoDecimal(-810.0));
    }
    @Test
    public void testCalculateUnderrecoveryWithApplyFlag() {
        BreakUpInterval bi = createBreakupInterval();
        RateAndCost ohRateItem = bi.getRateAndCosts().get(0);
        ohRateItem.setApplyRateFlag(false);
        breakupIntervalService.calculate(bi);
        assertEquals(new ScaleTwoDecimal(4050.00), bi.getUnderRecovery());
    }

    protected BreakUpInterval createBreakupInterval() {
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

    protected RateAndCost getRateCost(String rateClassCode, String rateTypeCode, String rateClassType,double rate) {
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

    protected QueryList<RateAndCost> createRateAndCosts() {
        QueryList<RateAndCost> rateAndCosts = new QueryList<RateAndCost>();
        rateAndCosts.add(getRateCost("1", "1", "O",50));
        rateAndCosts.add(getRateCost("5", "1", "E",25));
        rateAndCosts.add(getRateCost("8", "1", "V",10));
        rateAndCosts.add(getRateCost("10", "1", "Y",5));
        rateAndCosts.add(getRateCost("11", "1", "L",5));
        rateAndCosts.add(getRateCost("12", "1", "L",5));
        rateAndCosts.add(getRateCost("5", "3", "E",3));
        rateAndCosts.add(getRateCost("8", "2", "V",2));
        return rateAndCosts;
    }
    protected QueryList<BudgetRate> getBudgetRates() {
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
    protected QueryList<BudgetLaRate> getBudgetLArates() {
        QueryList<BudgetLaRate> rates=new QueryList<BudgetLaRate>();
        BudgetLaRate br7 = getBudgetLaRate(5,1L,"2010",5,true,"10","1","Y","07/01/2009","000001");
        BudgetLaRate br8 = getBudgetLaRate(5,1L,"2010",5,true,"11","1","L","07/01/2009","000001");
        BudgetLaRate br9 = getBudgetLaRate(5,1L,"2010",5,true,"12","1","L","07/01/2009","000001");
        rates.add(br7);
        rates.add(br8);
        rates.add(br9);
        return rates;
    }

    protected BudgetRate getBudgetRate(String actCode,double rate,Long bgtId,String year,
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

    protected RateClass getRateClass(String rateClassCode,String rateClassType) {
        RateClass rc = new RateClass();
        rc.setCode(rateClassCode);
        rc.setRateClassTypeCode(rateClassType);
        return rc;
    }
    
    protected RateClassBaseInclusion getInclusion(Long id, String rateClassCode, String rateTypeCode, 
    		String rateClassCodeIncl, String rateTypeCodeIncl) {
    	RateClassBaseInclusion rateClassBaseInclusion = new RateClassBaseInclusion();
    	rateClassBaseInclusion.setRateClassBaseInclusionId(id);
    	rateClassBaseInclusion.setRateClassCode(rateClassCode);
    	rateClassBaseInclusion.setRateTypeCode(rateTypeCode);
    	rateClassBaseInclusion.setRateClassCodeIncl(rateClassCodeIncl);
    	rateClassBaseInclusion.setRateTypeCodeIncl(rateTypeCodeIncl);
    	return rateClassBaseInclusion;
    }
    
    protected RateClassBaseExclusion getExclusion(Long id, String rateClassCode, String rateTypeCode, 
    		String rateClassCodeIncl, String rateTypeCodeIncl) {
    	RateClassBaseExclusion rateClassBaseInclusion = new RateClassBaseExclusion();
    	rateClassBaseInclusion.setRateClassBaseExclusionId(id);
    	rateClassBaseInclusion.setRateClassCode(rateClassCode);
    	rateClassBaseInclusion.setRateTypeCode(rateTypeCode);
    	rateClassBaseInclusion.setRateClassCodeExcl(rateClassCodeIncl);
    	rateClassBaseInclusion.setRateTypeCodeExcl(rateTypeCodeIncl);
    	return rateClassBaseInclusion;
    }


    protected BudgetLaRate getBudgetLaRate(double rate,Long bgtId,String year,
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

    protected Date getDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateString);
        }catch (ParseException e) {
            fail(dateString +" is not parsable");
            return null;
        }
    }

}
