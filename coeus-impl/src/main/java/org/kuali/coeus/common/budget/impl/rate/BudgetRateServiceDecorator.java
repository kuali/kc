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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.And;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.query.operator.LesserThan;
import org.kuali.coeus.common.budget.framework.query.operator.Or;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("budgetRatesService")
public class BudgetRateServiceDecorator extends BudgetRatesServiceImpl {
    
    private static final String AWARD_EB_RATE_CLASS_CODE = "awardBudgetEbRateClassCode";
    private static final String AWARD_EB_RATE_TYPE_CODE = "awardBudgetEbRateTypeCode";

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Override
    protected Collection<InstituteRate> getInstituteRates(Budget budget){
        Collection<InstituteRate> instituteRates = super.getInstituteRates(budget);
        if(isAwardBudget(budget)){
            return syncRatesIfAward(budget,instituteRates);
        }else{
            return instituteRates;
        }
    }


    private boolean isAwardBudget(Budget budget) {
        return budget.getBudgetParent().getDocument().getClass().equals(AwardDocument.class);
    }
    
    private Collection<InstituteRate> syncRatesIfAward(Budget budget, Collection<InstituteRate> institueRates) {
        Award award = (Award)budget.getBudgetParent();
        return filterInstituteRatesForAward(award,institueRates);
    }

    private Collection<InstituteRate> filterInstituteRatesForAward(Award award, Collection<InstituteRate> instituteRates) {
        List<AwardFandaRate> awardFnARates = award.getAwardFandaRate();
        Collection<InstituteRate> instituteRatesForAward = new ArrayList<>();
        List<InstituteRate> awardEbRates = createAwardEBInstituteRates(award);
        if(awardFnARates.isEmpty() && awardEbRates.isEmpty()) return instituteRates;
        
        for (AwardFandaRate awardFnARate : awardFnARates) {
            InstituteRate awardRate = createAwardFnAInstitueRate(awardFnARate,award,instituteRates);
            instituteRatesForAward.add(awardRate);
        }
        if(!instituteRatesForAward.isEmpty()){
            QueryList<InstituteRate> qlInstituteRates = new QueryList<>(instituteRatesForAward);
            qlInstituteRates.sort("startDate");
            InstituteRate firstRate = qlInstituteRates.get(0);
            if(firstRate.getStartDate().after(award.getRequestedStartDateInitial())){
                firstRate.setStartDate(award.getRequestedStartDateInitial());
            }
        }
        instituteRatesForAward.addAll(awardEbRates.stream().collect(Collectors.toList()));
        for (InstituteRate instituteRate : instituteRates) {
            if((!awardFnARates.isEmpty() && instituteRate.getRateClassType().equals(RateClassType.OVERHEAD.getRateClassType())) ||
                    (!awardEbRates.isEmpty() & instituteRate.getRateClassType().equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType()))){
                continue;
            }
            instituteRatesForAward.add(instituteRate);
        }
        return instituteRatesForAward;
    }

    private List<InstituteRate> createAwardEBInstituteRates(Award award) {
        List<InstituteRate> awardEBInstituteRates = new ArrayList<>();
        ScaleTwoDecimal specialEbRateOnCampus = award.getSpecialEbRateOnCampus();
        if(specialEbRateOnCampus!=null){
            awardEBInstituteRates.add(createEBInstituteRate(award,specialEbRateOnCampus,Boolean.TRUE));
        }
        ScaleTwoDecimal specialEbRateOffCampus = award.getSpecialEbRateOffCampus();
        if(specialEbRateOffCampus!=null){
            awardEBInstituteRates.add(createEBInstituteRate(award,specialEbRateOffCampus,Boolean.FALSE));
        }
        return awardEBInstituteRates;
    }

    private InstituteRate createEBInstituteRate(Award award, ScaleTwoDecimal specialEbRate, Boolean onCampusFlag) {
        InstituteRate awardInstituteRate = new InstituteRate();
        ScaleTwoDecimal applicableRate = new ScaleTwoDecimal(specialEbRate.bigDecimalValue());
        awardInstituteRate.setActivityTypeCode(award.getActivityTypeCode());
        awardInstituteRate.setStartDate(award.getRequestedStartDateInitial());
        Calendar cal = Calendar.getInstance();
        cal.setTime(award.getRequestedStartDateInitial());
        int year = cal.get(Calendar.YEAR);
        awardInstituteRate.setFiscalYear(""+year);
        awardInstituteRate.setInstituteRate(applicableRate);
        awardInstituteRate.setUnitNumber(award.getUnitNumber());
        String awardEBRateTypeCode = getDefaultEBRateTypeCode();
        String awardEBRateClassCode = getDefaultEBRateClassCode();
        awardInstituteRate.setRateTypeCode(awardEBRateTypeCode);
        awardInstituteRate.setRateType(createRateType(awardEBRateClassCode,awardEBRateTypeCode,"Special EB Rate"));
        awardInstituteRate.setRateClassCode(awardEBRateClassCode);
        awardInstituteRate.setOnOffCampusFlag(onCampusFlag);
        awardInstituteRate.setNonEditableRateFlag(true);
        awardInstituteRate.refreshReferenceObject("rateType");
        awardInstituteRate.refreshReferenceObject("rateClass");
        return awardInstituteRate;
    }

    private String getDefaultEBRateClassCode() {
        return getParameterService().getParameterValueAsString(Budget.class, AWARD_EB_RATE_CLASS_CODE);
    }

    private String getDefaultEBRateTypeCode() {
        return getParameterService().getParameterValueAsString(Budget.class, AWARD_EB_RATE_TYPE_CODE);
    }

    private InstituteRate createAwardFnAInstitueRate(AwardFandaRate awardFnARate,Award award, Collection<InstituteRate> instituteRates) {
        InstituteRate awardInstituteRate = filterInstituteRate(awardFnARate,award,instituteRates);
        ScaleTwoDecimal applicableRate = new ScaleTwoDecimal(awardFnARate.getApplicableFandaRate().bigDecimalValue());
        awardInstituteRate.setActivityTypeCode(award.getActivityTypeCode());
        awardInstituteRate.setStartDate(awardFnARate.getStartDate());
        awardInstituteRate.setFiscalYear(awardFnARate.getFiscalYear());
        awardInstituteRate.setExternalApplicableRate(applicableRate);
        if (awardInstituteRate.getInstituteRate()==null) { 
            awardInstituteRate.setInstituteRate(applicableRate);
        }
        String awardFnArateTypeCode = awardFnARate.getFandaRateTypeCode();
        awardInstituteRate.setRateTypeCode(awardFnArateTypeCode);
        awardInstituteRate.setRateType(awardFnARate.getFandaRateType());
        awardInstituteRate.setRateClassCode(awardFnARate.getFandaRateType().getRateClassCode());
        Boolean onCampusFlag = awardFnARate.getOnCampusFlag().equals("N");
        awardInstituteRate.setOnOffCampusFlag(onCampusFlag);
        awardInstituteRate.setNonEditableRateFlag(true);
        awardInstituteRate.refreshReferenceObject("rateClass");
        return awardInstituteRate;
    }

    private InstituteRate filterInstituteRate(AwardFandaRate awardFnARate, Award award,Collection<InstituteRate> instituteRates) {
        QueryList<InstituteRate> qlInstituteRates = new QueryList<>(instituteRates);
        Equals eqActivityType = new Equals("activityTypeCode",award.getActivityTypeCode());
        Equals eqCampusFlag = new Equals("onOffCampusFlag",awardFnARate.getOnCampusFlag().equals("N"));
        Equals eqRateClassCode = new Equals("rateClassCode",awardFnARate.getFandaRateType().getRateClassCode());
        Equals eqRateTypeCode = new Equals("rateTypeCode",awardFnARate.getFandaRateTypeCode());
        And campFlagAndActTypeAndUnitNum = new And(eqActivityType,eqCampusFlag);
        And rateClassAndRateType = new And(eqRateClassCode,eqRateTypeCode);
        And filterCondition = new And(campFlagAndActTypeAndUnitNum,rateClassAndRateType);
        QueryList<InstituteRate> qlfilteredList = qlInstituteRates.filter(filterCondition);
        Equals eqStartDate = new Equals("startDate",awardFnARate.getStartDate());
        LesserThan ltStartDate = new LesserThan("startDate",awardFnARate.getStartDate());
        Or ltOrEqStartDate = new Or(eqStartDate,ltStartDate);
        qlfilteredList = qlfilteredList.filter(ltOrEqStartDate);
        qlfilteredList.sort("startDate",false);
        return qlfilteredList.isEmpty() ? new InstituteRate() : qlfilteredList.get(0);
    }


    private RateType createRateType(String rateClassCode, String awardFnArateTypeCode,String rateTypeDesc) {
        RateType rateType = new RateType();
        rateType.setRateTypeCode(awardFnArateTypeCode);
        rateType.setRateClassCode(rateClassCode);
        rateType.setDescription(rateTypeDesc);
        
        return rateType;
    }

    @Override
    public void syncAllBudgetRates(Budget budget) {
        if(isAwardBudget(budget) ){
            if(isOutOfSyncAwardRates(budget)){
                super.syncAllBudgetRates(budget);
                repopulateAllCalcAmounts(budget);
            }
        }else{
            super.syncAllBudgetRates(budget);
        }
    }

    @Override
    public void syncParentDocumentRates(Budget budget) {
        if (isAwardBudget(budget)) {
            if (!hasNoRatesFromParent(budget)
                    && isOutOfSyncAwardRates((Award)budget.getBudgetParent(),budget)) {
                //need to sync just budget specific rates now
                syncBudgetRatesForRateClassType(RateClassType.OVERHEAD.getRateClassType(), budget);
                syncBudgetRatesForRateClassType(RateClassType.EMPLOYEE_BENEFITS.getRateClassType(), budget);
                repopulateAllCalcAmounts(budget);
            }
        }
    }
    private void repopulateAllCalcAmounts(Budget budget) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : budgetLineItems) {
                getBudgetCalculationService().rePopulateCalculatedAmount(budget, budgetLineItem);
                List<BudgetPersonnelDetails> personnelDetailList = budgetLineItem.getBudgetPersonnelDetailsList();
                for (BudgetPersonnelDetails budgetPersonnelDetails : personnelDetailList) {
                    budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
                    getBudgetCalculationService().rePopulateCalculatedAmount(budget, budgetPersonnelDetails);
                }
            }
        }
    }

    private boolean hasNoRatesFromParent(Budget budget) {
        Award award = (Award)budget.getBudgetParent();
        return award.getAwardFandaRate().isEmpty() && award.getSpecialEbRateOffCampus()==null && award.getSpecialEbRateOnCampus()==null;
    }
    
    private boolean isOutOfSyncAwardRates(Budget budget) {
        Award award = (Award)budget.getBudgetParent();
        if(budget.getInstituteRates().isEmpty()){
            populateInstituteRates(budget);
        }
        if(hasNoRatesFromParent(budget)){
            return isOutOfSync(budget);
        }else{
            return isOutOfSyncAwardRates(award,budget);
        }
    }
    
    private boolean isOutOfSyncAwardRates(Award award,Budget budget) {
        List<AwardFandaRate> fnaRates = award.getAwardFandaRate();
        QueryList<BudgetRate> budgetRates = new QueryList<>(budget.getBudgetRates());
        boolean ratesOutOfSync = false;
        if(!fnaRates.isEmpty()){
            Equals eqOhRateClassType = new Equals("rateClassType",RateClassType.OVERHEAD.getRateClassType());
            List<BudgetRate> filteredOhRates = budgetRates.filter(eqOhRateClassType);
            ratesOutOfSync = fnaRates.size()!=filteredOhRates.size();
            if(!ratesOutOfSync){
                for (BudgetRate budgetRate : filteredOhRates) {
                    ratesOutOfSync = !fnaRatesContains(fnaRates,budgetRate);
                    if(ratesOutOfSync) return ratesOutOfSync;
                }
            }
            
        }
        Equals eqEbRateClassType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        ScaleTwoDecimal specialEbRateOnCampus = award.getSpecialEbRateOnCampus();
        if(specialEbRateOnCampus!=null){
            Equals eqOnCampus = new Equals("onOffCampusFlag",Boolean.TRUE);
            And onCampusEbRateClassType = new And(eqEbRateClassType,eqOnCampus);
            List<BudgetRate> filteredEbRates = budgetRates.filter(onCampusEbRateClassType);
            ratesOutOfSync=filteredEbRates.size()!=1;
            if(!ratesOutOfSync){
                BudgetRate budgetEbOnCampusRate = filteredEbRates.get(0);
                ratesOutOfSync = !budgetEbOnCampusRate.getApplicableRate().bigDecimalValue().equals(specialEbRateOnCampus.bigDecimalValue());
            }
        }
        ScaleTwoDecimal specialEbRateOffCampus = award.getSpecialEbRateOffCampus();
        if(!ratesOutOfSync && specialEbRateOffCampus!=null){
            Equals eqOffCampus = new Equals("onOffCampusFlag",Boolean.FALSE);
            And offCampusEbRateClassType = new And(eqEbRateClassType,eqOffCampus);
            List<BudgetRate> filteredOffCampusEbRates = budgetRates.filter(offCampusEbRateClassType);
            ratesOutOfSync=filteredOffCampusEbRates.size()!=1;
            if(!ratesOutOfSync){
                BudgetRate budgetEbOnCampusRate = filteredOffCampusEbRates.get(0);
                ratesOutOfSync = !budgetEbOnCampusRate.getApplicableRate().bigDecimalValue().equals(specialEbRateOffCampus.bigDecimalValue());
            }
        }

        return ratesOutOfSync;
    }

    
    private boolean fnaRatesContains(List<AwardFandaRate> fnaRates, BudgetRate budgetRate) {
        for (AwardFandaRate awardFandaRate : fnaRates) {
            if(awardFandaRate.equals(budgetRate)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean performSyncFlag(Budget budget) {
        return isAwardBudget(budget) && isOutOfSyncAwardRates(budget);
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    

}
