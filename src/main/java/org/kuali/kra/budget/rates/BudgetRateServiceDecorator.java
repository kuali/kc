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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetRateServiceDecorator extends BudgetRatesServiceImpl {
    

    private void populateAwardBudgetRates( BudgetDocument budgetDocument) {
        Budget budget = budgetDocument.getBudget();
        Award award = (Award)budgetDocument.getParentDocument().getBudgetParent();
        List<BudgetRate> awardBudgetRates = new ArrayList<BudgetRate>();
        populateBudgetRatesFromAwardFnARates(budget, award, awardBudgetRates);
//        populateBudgetRatesFromAwardBenifitRates(budget, award, awardBudgetRates);
        
        budget.setBudgetRates(awardBudgetRates);
    }

    private void populateBudgetRatesFromAwardBenifitRates(Budget budget, Award award, List<BudgetRate> awardBudgetRates) {
        List<AwardFandaRate> awardFnARates = award.getAwardFandaRate();
        if(!awardFnARates.isEmpty()){
            List<BudgetRate> existingRates = budget.getBudgetRates();
            for (AwardFandaRate awardFnARate : awardFnARates) {
                awardBudgetRates.add(createBudgetRate(budget, awardFnARate));
            }
            QueryList<BudgetRate> qlBudgetRates = new QueryList<BudgetRate>(awardBudgetRates);
            qlBudgetRates.sort("startDate");
            BudgetRate firstRate = qlBudgetRates.get(0);
            if(firstRate.getStartDate().after(budget.getStartDate())){
                firstRate.setStartDate(budget.getStartDate());
            }
            awardBudgetRates = qlBudgetRates.toArrayList();
            for (BudgetRate budgetRate : existingRates) {
                if(!budgetRate.getRateClassType().equals(org.kuali.kra.budget.calculator.RateClassType.OVERHEAD.getRateClassType())){
                    awardBudgetRates.add(budgetRate);
                }
            }
        }
    }

    /**
     * This method...
     * @param budget
     * @param award
     * @param awardBudgetRates
     * @return
     */
    private void populateBudgetRatesFromAwardFnARates(Budget budget, Award award, List<BudgetRate> awardBudgetRates) {
        List<AwardFandaRate> awardFnARates = award.getAwardFandaRate();
        if(!awardFnARates.isEmpty()){
            List<BudgetRate> existingRates = budget.getBudgetRates();
            for (AwardFandaRate awardFnARate : awardFnARates) {
                awardBudgetRates.add(createBudgetRate(budget, awardFnARate));
            }
            QueryList<BudgetRate> qlBudgetRates = new QueryList<BudgetRate>(awardBudgetRates);
            qlBudgetRates.sort("startDate");
            BudgetRate firstRate = qlBudgetRates.get(0);
            if(firstRate.getStartDate().after(budget.getStartDate())){
                firstRate.setStartDate(budget.getStartDate());
            }
            awardBudgetRates = qlBudgetRates.toArrayList();
            for (BudgetRate budgetRate : existingRates) {
                if(!budgetRate.getRateClassType().equals(org.kuali.kra.budget.calculator.RateClassType.OVERHEAD.getRateClassType())){
                    awardBudgetRates.add(budgetRate);
                }
            }
        }
    }

    /**
     * This method...
     * @param budget
     * @param awardFnARate
     * @return
     */
    private BudgetRate createBudgetRate(Budget budget, AwardFandaRate awardFnARate) {
        BudgetRate awardBudgetRate = new BudgetRate();
        awardBudgetRate.setBudgetId(budget.getBudgetId());
        awardBudgetRate.setBudget(budget);
        BudgetDecimal applicableRate = new BudgetDecimal(awardFnARate.getApplicableFandaRate().bigDecimalValue());
        awardBudgetRate.setApplicableRate(applicableRate);
        awardBudgetRate.setActivityTypeCode(budget.getBudgetDocument().getParentDocument().getBudgetParent().getActivityTypeCode());
        awardBudgetRate.setStartDate(awardFnARate.getStartDate());
        awardBudgetRate.setFiscalYear(awardFnARate.getFiscalYear());
        awardBudgetRate.setOldApplicableRate(applicableRate);
        awardBudgetRate.setInstituteRate(applicableRate);
        awardBudgetRate.setUnitNumber(budget.getBudgetDocument().getParentDocument().getBudgetParent().getUnitNumber());
        String awardFnArateTypeCode = awardFnARate.getFandaRateTypeCode().toString();
        awardBudgetRate.setRateTypeCode(awardFnArateTypeCode);
        awardFnARate.refreshReferenceObject("fandaRateType");
        awardBudgetRate.setRateType(createRateType("1",awardFnArateTypeCode,awardFnARate.getFandaRateType().getDescription()));
        awardBudgetRate.setRateClassCode("1");
        Boolean onCampusFlag = new Boolean(awardFnARate.getOnCampusFlag().equals("N"));
        awardBudgetRate.setOnOffCampusFlag(onCampusFlag);
        awardBudgetRate.setFlatRateCalculation(true);
        awardBudgetRate.refreshReferenceObject("rateClass");
        return awardBudgetRate;
    }

    private RateType createRateType(String rateClassCode, String awardFnArateTypeCode,String rateTypeDesc) {
        RateType rateType = new RateType();
        rateType.setRateTypeCode(awardFnArateTypeCode);
        rateType.setRateClassCode(rateClassCode);
        rateType.setDescription(rateTypeDesc);
        
        return rateType;
    }

    public List getBudgetPeriods() {
        return super.getBudgetPeriods();
    }

    public Map getBudgetRateClassMap(String rateClassType) {
        return super.getBudgetRateClassMap(rateClassType);
    }

    public Collection getBudgetRateClasses(String rateClassType) {
        return super.getBudgetRateClasses(rateClassType);
    }

    public boolean isOutOfSyncForRateAudit(BudgetDocument budgetDocument) {
        return super.isOutOfSyncForRateAudit(budgetDocument);
    }

    public void populateBudgetRatesForNewVersion(BudgetDocument budgetDocument) {
        super.populateBudgetRatesForNewVersion(budgetDocument);
    }

    public void resetAllBudgetRates(Budget budget) {
        super.resetAllBudgetRates(budget);
        syncRatesIfAward(budget);
    }

    public void resetBudgetRatesForRateClassType(String rateClassType, Budget budget) {
        super.resetBudgetRatesForRateClassType(rateClassType, budget);
        syncRatesIfAward(budget);
    }

    public void syncAllBudgetRates(BudgetDocument budgetDocument) {
        super.syncAllBudgetRates(budgetDocument);
        syncRatesIfAward(budgetDocument);
    }

    public void syncBudgetRateCollectionsToExistingRates(List rateClassTypes, BudgetDocument budgetDocument) {
        super.syncBudgetRateCollectionsToExistingRates(rateClassTypes, budgetDocument);
        syncRatesIfAward(budgetDocument);
    }

    public void syncBudgetRatesForRateClassType(String rateClassType, BudgetDocument budgetDocument) {
        super.syncBudgetRatesForRateClassType(rateClassType, budgetDocument);
        syncRatesIfAward(budgetDocument);
    }

    public void viewLocation(String viewLocation, Integer budgetPeriod, Budget budget) {
        super.viewLocation(viewLocation, budgetPeriod, budget);
    }

    public void getBudgetRates(List rateClassTypes, BudgetDocument budgetDocument) {
        super.getBudgetRates(rateClassTypes, budgetDocument);
        syncRatesIfAward(budgetDocument);
    }

    /**
     * This method...
     * @param budgetDocument
     */
    private void syncRatesIfAward(BudgetDocument budgetDocument) {
        if(budgetDocument.getParentDocument().getClass().equals(AwardDocument.class)){
            populateAwardBudgetRates(budgetDocument);
        }
    }
    private void syncRatesIfAward(Budget budget) {
        syncRatesIfAward(budget.getBudgetDocument());
    }

}
