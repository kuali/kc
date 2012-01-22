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
package org.kuali.kra.budget.rates;

import static org.kuali.kra.budget.calculator.RateClassType.EMPLOYEE_BENEFITS;
import static org.kuali.kra.budget.calculator.RateClassType.OVERHEAD;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public class BudgetRateServiceDecorator<T extends BudgetParent> extends BudgetRatesServiceImpl<T> {
    
    private static final String AWARD_EB_RATE_CLASS_CODE = "awardBudgetEbRateClassCode";
    private static final String AWARD_EB_RATE_TYPE_CODE = "awardBudgetEbRateTypeCode";
    private static final String DEFAULT_FNA_RATE_CLASS_CODE = "defaultFnARateClassCode";
    private ParameterService parameterService;
    private BudgetCalculationService budgetCalculationService;
    @Override
    protected Collection<InstituteRate> getInstituteRates(BudgetDocument<T> budgetDocument){
        Collection<InstituteRate> institueRates = super.getInstituteRates(budgetDocument);
        if(isAwardBudget(budgetDocument)){
            return syncRatesIfAward(budgetDocument,institueRates);
        }else{
            return institueRates;
        }
    }

    /**
     * This method...
     * @param budgetDocument
     * @return
     */
    private boolean isAwardBudget(BudgetDocument<T> budgetDocument) {
        return budgetDocument.getParentDocument().getClass().equals(AwardDocument.class);
    }
    
    private Collection<InstituteRate> syncRatesIfAward(BudgetDocument<T> budgetDocument, Collection<InstituteRate> institueRates) {
        Award award = (Award)budgetDocument.getParentDocument().getBudgetParent();
        return filterInstituteRatesForAward(budgetDocument,award,institueRates);
    }

    private Collection<InstituteRate> filterInstituteRatesForAward(BudgetDocument<T> budgetDocument,Award award, Collection<InstituteRate> instituteRates) {
        List<AwardFandaRate> awardFnARates = award.getAwardFandaRate();
        Collection<InstituteRate> instituteRatesForAward = new ArrayList<InstituteRate>();  
        List<InstituteRate> awardEbRates = createAwardEBInstituteRates(award);
        if(awardFnARates.isEmpty() && awardEbRates.isEmpty()) return instituteRates;
        
        for (AwardFandaRate awardFnARate : awardFnARates) {
            InstituteRate awardRate = createAwardFnAInstitueRate(awardFnARate,award);
            instituteRatesForAward.add(awardRate);
            budgetDocument.getBudget().setOhRateClassCode(awardRate.getRateClassCode());
        }
        if(!instituteRatesForAward.isEmpty()){
            QueryList<InstituteRate> qlInstituteRates = new QueryList<InstituteRate>(instituteRatesForAward);
            qlInstituteRates.sort("startDate");
            InstituteRate firstRate = qlInstituteRates.get(0);
            if(firstRate.getStartDate().after(award.getRequestedStartDateInitial())){
                firstRate.setStartDate(award.getRequestedStartDateInitial());
            }
        }
        for (InstituteRate awardEBRate : awardEbRates) {
            instituteRatesForAward.add(awardEBRate);
        }
        for (InstituteRate instituteRate : instituteRates) {
            if((!awardFnARates.isEmpty() && instituteRate.getRateClassType().equals(OVERHEAD.getRateClassType())) ||
                    (!awardEbRates.isEmpty() & instituteRate.getRateClassType().equals(EMPLOYEE_BENEFITS.getRateClassType()))){
                continue;
            }
            instituteRatesForAward.add(instituteRate);
        }
        return instituteRatesForAward;
    }

    private List<InstituteRate> createAwardEBInstituteRates(Award award) {
        List<InstituteRate> awardEBInstituteRates = new ArrayList<InstituteRate>();
        KualiDecimal specialEbRateOnCampus = award.getSpecialEbRateOnCampus();
        if(specialEbRateOnCampus!=null){
            awardEBInstituteRates.add(createEBInstituteRate(award,specialEbRateOnCampus,Boolean.TRUE));
        }
        KualiDecimal specialEbRateOffCampus = award.getSpecialEbRateOffCampus();
        if(specialEbRateOffCampus!=null){
            awardEBInstituteRates.add(createEBInstituteRate(award,specialEbRateOffCampus,Boolean.FALSE));
        }
        return awardEBInstituteRates;
    }

    private InstituteRate createEBInstituteRate(Award award, KualiDecimal specialEbRate, Boolean onCampusFlag) {
        InstituteRate awardInstituteRate = new InstituteRate();
        BudgetDecimal applicableRate = new BudgetDecimal(specialEbRate.bigDecimalValue());
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
        return getParameterService().getParameterValueAsString(BudgetDocument.class, AWARD_EB_RATE_CLASS_CODE);
    }

    private String getDefaultEBRateTypeCode() {
        return getParameterService().getParameterValueAsString(BudgetDocument.class, AWARD_EB_RATE_TYPE_CODE);
    }

    private InstituteRate createAwardFnAInstitueRate(AwardFandaRate awardFnARate,Award award) {
        InstituteRate awardInstituteRate = new InstituteRate();
        BudgetDecimal applicableRate = new BudgetDecimal(awardFnARate.getApplicableFandaRate().bigDecimalValue());
        awardInstituteRate.setActivityTypeCode(award.getActivityTypeCode());
        awardInstituteRate.setStartDate(awardFnARate.getStartDate());
        awardInstituteRate.setFiscalYear(awardFnARate.getFiscalYear());
        awardInstituteRate.setInstituteRate(applicableRate);
        awardInstituteRate.setUnitNumber(award.getUnitNumber());
        String awardFnArateTypeCode = awardFnARate.getFandaRateTypeCode().toString();
        awardInstituteRate.setRateTypeCode(awardFnArateTypeCode);
        awardInstituteRate.setRateType(awardFnARate.getFandaRateType());
        awardInstituteRate.setRateClassCode(awardFnARate.getFandaRateType().getRateClassCode());
        Boolean onCampusFlag = new Boolean(awardFnARate.getOnCampusFlag().equals("N"));
        awardInstituteRate.setOnOffCampusFlag(onCampusFlag);
        awardInstituteRate.setNonEditableRateFlag(true);
        awardInstituteRate.refreshReferenceObject("rateClass");
        return awardInstituteRate;
    }

    /**
     * This method...
     * @return
     */
    private String getDefaultFnARateClassCode() {
        return getParameterService().getParameterValueAsString(BudgetDocument.class,DEFAULT_FNA_RATE_CLASS_CODE);
    }


    private RateType createRateType(String rateClassCode, String awardFnArateTypeCode,String rateTypeDesc) {
        RateType rateType = new RateType();
        rateType.setRateTypeCode(awardFnArateTypeCode);
        rateType.setRateClassCode(rateClassCode);
        rateType.setDescription(rateTypeDesc);
        
        return rateType;
    }

    public void syncAllBudgetRates(BudgetDocument<T> budgetDocument) {
        if(isAwardBudget(budgetDocument) ){
            if(isOutOfSyncAwardRates(budgetDocument.getBudget())){
                super.syncAllBudgetRates(budgetDocument);
                repopulateAllCalcAmounts(budgetDocument);
            }
        }else{
            super.syncAllBudgetRates(budgetDocument);
        }
    }
    
    public void syncParentDocumentRates(BudgetDocument<T> budgetDocument) {
        if (isAwardBudget(budgetDocument)) {
            if (!hasNoRatesFromParent(budgetDocument.getBudget())
                    && isOutOfSyncAwardRates((Award)budgetDocument.getParentDocument().getBudgetParent(),budgetDocument.getBudget())) {
                //need to sync just budget specific rates now
                syncBudgetRatesForRateClassType(OVERHEAD.getRateClassType(), budgetDocument);
                syncBudgetRatesForRateClassType(EMPLOYEE_BENEFITS.getRateClassType(), budgetDocument);
                repopulateAllCalcAmounts(budgetDocument);
            }
        }
    }
    private void repopulateAllCalcAmounts(BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
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
        Award award = (Award)budget.getBudgetDocument().getParentDocument().getBudgetParent();
        return award.getAwardFandaRate().isEmpty() && award.getSpecialEbRateOffCampus()==null && award.getSpecialEbRateOnCampus()==null;
    }

//    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument<T> budgetDocument){
//      super.getBudgetRates(rateClassTypes, budgetDocument);
//    }
    
    private boolean isOutOfSyncAwardRates(Budget budget) {
        Award award = (Award)budget.getBudgetDocument().getParentDocument().getBudgetParent();
        if(budget.getInstituteRates().isEmpty()){
            populateInstituteRates(budget.getBudgetDocument());
        }
        if(hasNoRatesFromParent(budget)){
            return isOutOfSync(budget);
        }else{
            return isOutOfSyncAwardRates(award,budget);
        }
    }
    
    private boolean isOutOfSyncAwardRates(Award award,Budget budget) {
        List<AwardFandaRate> fnaRates = award.getAwardFandaRate();
        QueryList<BudgetRate> budgetRates = new QueryList<BudgetRate>(budget.getBudgetRates());
        boolean ratesOutOfSync = false;
        if(!fnaRates.isEmpty()){
            Equals eqOhRateClassType = new Equals("rateClassType",OVERHEAD.getRateClassType());
            List<BudgetRate> filteredOhRates = budgetRates.filter(eqOhRateClassType);
            ratesOutOfSync = fnaRates.size()!=filteredOhRates.size();
            if(!ratesOutOfSync){
                for (BudgetRate budgetRate : filteredOhRates) {
                    ratesOutOfSync = !fnaRatesContains(fnaRates,budgetRate);
                    if(ratesOutOfSync) return ratesOutOfSync;
                }
            }
            
        }
        Equals eqEbRateClassType = new Equals("rateClassType",EMPLOYEE_BENEFITS.getRateClassType());
        KualiDecimal specialEbRateOnCampus = award.getSpecialEbRateOnCampus();
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
        KualiDecimal specialEbRateOffCampus = award.getSpecialEbRateOffCampus();
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
    public boolean performSyncFlag(BudgetDocument<T> budgetDocument) {
        return isAwardBudget(budgetDocument) && isOutOfSyncAwardRates(budgetDocument.getBudget());
    }
    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return parameterService;
    }

    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Gets the budgetCalculationService attribute. 
     * @return Returns the budgetCalculationService.
     */
    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    /**
     * Sets the budgetCalculationService attribute value.
     * @param budgetCalculationService The budgetCalculationService to set.
     */
    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    

}
