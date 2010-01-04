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
package org.kuali.kra.budget.rates;

import static org.kuali.kra.budget.calculator.RateClassType.EMPLOYEE_BENEFITS;
import static org.kuali.kra.budget.calculator.RateClassType.OVERHEAD;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AbstractInstituteRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.KualiDecimal;

import com.sun.org.apache.bcel.internal.generic.FNEG;

public class BudgetRateServiceDecorator extends BudgetRatesServiceImpl {
    
    private static final String AWARD_EB_RATE_CLASS_CODE = "awardBudgetEbRateClassCode";
    private static final String AWARD_EB_RATE_TYPE_CODE = "awardBudgetEbRateTypeCode";
    private static final String DEFAULT_FNA_RATE_CLASS_CODE = "defaultFnARateClassCode";
    private ParameterService parameterService;

    @Override
    protected Collection<InstituteRate> getInstituteRates(BudgetDocument budgetDocument){
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
    private boolean isAwardBudget(BudgetDocument budgetDocument) {
        return budgetDocument.getParentDocument().getClass().equals(AwardDocument.class);
    }
    
    private Collection<InstituteRate> syncRatesIfAward(BudgetDocument budgetDocument, Collection<InstituteRate> institueRates) {
        Award award = (Award)budgetDocument.getParentDocument().getBudgetParent();
        return filterInstituteRatesForAward(award,institueRates);
    }

    private Collection<InstituteRate> filterInstituteRatesForAward(Award award, Collection<InstituteRate> instituteRates) {
        List<AwardFandaRate> awardFnARates = award.getAwardFandaRate();
        Collection<InstituteRate> instituteRatesForAward = new ArrayList<InstituteRate>();  
        List<InstituteRate> awardEbRates = createAwardEBInstituteRates(award);
        if(awardFnARates.isEmpty() && awardEbRates.isEmpty()) return instituteRates;
        
        for (AwardFandaRate awardFnARate : awardFnARates) {
            InstituteRate awardRate = createAwardFnAInstitueRate(awardFnARate,award);
            instituteRatesForAward.add(awardRate);
        }
        QueryList<InstituteRate> qlInstituteRates = new QueryList<InstituteRate>(instituteRatesForAward);
        qlInstituteRates.sort("startDate");
        InstituteRate firstRate = qlInstituteRates.get(0);
        if(firstRate.getStartDate().after(award.getRequestedStartDateInitial())){
            firstRate.setStartDate(award.getRequestedStartDateInitial());
        }
        

        for (InstituteRate awardEBRate : awardEbRates) {
            instituteRatesForAward.add(awardEBRate);
        }
        for (InstituteRate instituteRate : instituteRates) {
            if(!instituteRate.getRateClassType().equals(OVERHEAD.getRateClassType()) &&
                    !instituteRate.getRateClassType().equals(EMPLOYEE_BENEFITS.getRateClassType())){
                instituteRatesForAward.add(instituteRate);
            }
        }
        return instituteRatesForAward;
    }

    private List<InstituteRate> createAwardEBInstituteRates(Award award) {
        List<InstituteRate> awardEBInstituteRates = new ArrayList<InstituteRate>();
        KualiDecimal specialEbRateOnCampus = award.getSpecialEbRateOnCampus();
        if(specialEbRateOnCampus!=null && specialEbRateOnCampus.isPositive()){
            awardEBInstituteRates.add(createEBInstituteRate(award,specialEbRateOnCampus,Boolean.TRUE));
        }
        KualiDecimal specialEbRateOffCampus = award.getSpecialEbRateOffCampus();
        if(specialEbRateOffCampus!=null && specialEbRateOffCampus.isPositive()){
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
        awardInstituteRate.refreshReferenceObject("rateType");
        awardInstituteRate.refreshReferenceObject("rateClass");
        return awardInstituteRate;
    }

    private String getDefaultEBRateClassCode() {
        return getParameterService().getParameterValue(BudgetDocument.class, AWARD_EB_RATE_CLASS_CODE);
    }

    private String getDefaultEBRateTypeCode() {
        return getParameterService().getParameterValue(BudgetDocument.class, AWARD_EB_RATE_TYPE_CODE);
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
        awardFnARate.refreshReferenceObject("fandaRateType");
        awardInstituteRate.setRateType(createRateType(getDefaultFnARateClassCode(),awardFnArateTypeCode,awardFnARate.getFandaRateType().getDescription()));
        awardInstituteRate.setRateClassCode(getDefaultFnARateClassCode());
        Boolean onCampusFlag = new Boolean(awardFnARate.getOnCampusFlag().equals("N"));
        awardInstituteRate.setOnOffCampusFlag(onCampusFlag);
        awardInstituteRate.refreshReferenceObject("rateClass");
        return awardInstituteRate;
    }

    /**
     * This method...
     * @return
     */
    private String getDefaultFnARateClassCode() {
        return getParameterService().getParameterValue(BudgetDocument.class,DEFAULT_FNA_RATE_CLASS_CODE);
    }


    private RateType createRateType(String rateClassCode, String awardFnArateTypeCode,String rateTypeDesc) {
        RateType rateType = new RateType();
        rateType.setRateTypeCode(awardFnArateTypeCode);
        rateType.setRateClassCode(rateClassCode);
        rateType.setDescription(rateTypeDesc);
        
        return rateType;
    }

    public void getBudgetRates(List<RateClassType> rateClassTypes, BudgetDocument budgetDocument){
      super.getBudgetRates(rateClassTypes, budgetDocument);
      if(isAwardBudget(budgetDocument) && isOutOfSyncAwardRates(budgetDocument.getBudget())){
          syncAllBudgetRates(budgetDocument);
      }
    }
    
    private boolean isOutOfSyncAwardRates(Budget budget) {
        Award award = (Award)budget.getBudgetDocument().getParentDocument().getBudgetParent();
        return award.isOutOfRatesSync(budget);
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
    

}
