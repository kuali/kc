/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.budget;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class AwardBudgetExt extends Budget {

    private static final long serialVersionUID = 1L;

    private String awardBudgetStatusCode;

    private String awardBudgetTypeCode;

    private ScaleTwoDecimal obligatedTotal;

    private ScaleTwoDecimal obligatedAmount = ScaleTwoDecimal.ZERO;

    private AwardBudgetStatus awardBudgetStatus;

    private AwardBudgetType awardBudgetType;

    private String description;

    private String budgetInitiator;

    private BudgetVersionOverview prevBudget;

    private List<ScaleTwoDecimal> budgetsTotals;

    private List<AwardBudgetLimit> awardBudgetLimits;

    private BudgetVersionOverview firstBudget;
    
    private SortedMap<CostElement, ScaleTwoDecimal> objectCodeBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> calculatedExpenseBudgetTotals;

    private SortedMap<String, ScaleTwoDecimal> totalBudgetSummaryTotals;

    private SortedMap<String, ScaleTwoDecimal> objectCodePersonnelFringeBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> personnelCalculatedExpenseBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> nonPersonnelCalculatedExpenseBudgetTotals;

    public AwardBudgetExt() {
        super();
        awardBudgetLimits = new ArrayList<AwardBudgetLimit>();
    }

    public AwardBudgetPeriodExt getNewBudgetPeriod() {
        return new AwardBudgetPeriodExt();
    }

    public BudgetLineItem getNewBudgetLineItem() {
        return new AwardBudgetLineItemExt();
    }

    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new AwardBudgetPersonnelDetailsExt();
    }

    public String getAwardBudgetStatusCode() {
        return awardBudgetStatusCode;
    }

    public void setAwardBudgetStatusCode(String awardBudgetStatusCode) {
        this.awardBudgetStatusCode = awardBudgetStatusCode;
    }

    public String getAwardBudgetTypeCode() {
        return awardBudgetTypeCode;
    }

    public void setAwardBudgetTypeCode(String awardBudgetTypeCode) {
        this.awardBudgetTypeCode = awardBudgetTypeCode;
    }

    public AwardBudgetStatus getAwardBudgetStatus() {
        return awardBudgetStatus;
    }

    public void setAwardBudgetStatus(AwardBudgetStatus awardBudgetStatus) {
        this.awardBudgetStatus = awardBudgetStatus;
    }

    public AwardBudgetType getAwardBudgetType() {
        return awardBudgetType;
    }

    public void setAwardBudgetType(AwardBudgetType awardBudgetType) {
        this.awardBudgetType = awardBudgetType;
    }

    /**
     * Gets the ohRatesNonEditable attribute. 
     * @return Returns the ohRatesNonEditable.
     */
    public boolean getOhRatesNonEditable() {
        Award award = (Award) getBudgetDocument().getParentDocument().getBudgetParent();
        return award.getAwardFandaRate().isEmpty() ? false : true;
    }

    /**
     * Gets the ebRatesNonEditable attribute. 
     * @return Returns the ebRatesNonEditable.
     */
    public boolean getEbRatesNonEditable() {
        Award award = (Award) getBudgetDocument().getParentDocument().getBudgetParent();
        return ((award.getSpecialEbRateOffCampus() != null && award.getSpecialEbRateOffCampus().isPositive()) || (award.getSpecialEbRateOnCampus() != null && award.getSpecialEbRateOnCampus().isPositive())) ? true : false;
    }

    /**
     * Gets the obligatedTotal attribute - which actually contains the BudgetTotalCostLimit which is
     * the lesser of the obligated distributable amount and the award total cost budget limit. 
     * @return Returns the obligatedTotal.
     */
    public ScaleTwoDecimal getObligatedTotal() {
        return obligatedTotal == null ? ScaleTwoDecimal.ZERO : obligatedTotal;
    }

    /**
     * Sets the obligatedTotal attribute value -  which should actually be the BudgetTotalCostLimit which is
     * the lesser of the obligated distributable amount and the award total cost budget limit.
     * @param obligatedTotal The obligatedTotal to set.
     */
    public void setObligatedTotal(ScaleTwoDecimal obligatedAmount) {
        this.obligatedTotal = obligatedAmount;
    }

    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount == null ? ScaleTwoDecimal.ZERO : obligatedAmount;
    }

    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the budgetInitiator attribute. 
     * @return Returns the budgetInitiator.
     */
    public String getBudgetInitiator() {
        return budgetInitiator;
    }

    /**
     * Sets the budgetInitiator attribute value.
     * @param budgetInitiator The budgetInitiator to set.
     */
    public void setBudgetInitiator(String budgetInitiator) {
        this.budgetInitiator = budgetInitiator;
    }

    public BudgetVersionOverview getPrevBudget() {
        if (prevBudget == null && this.getBudgetDocument() != null) {
            Integer version = 0;
            for (BudgetDocumentVersion budgetDocumentVersion : this.getBudgetDocument().getParentDocument().getBudgetDocumentVersions()) {
                for (BudgetVersionOverview budgetVersionOverview : budgetDocumentVersion.getBudgetVersionOverviews()) {
                    if (budgetVersionOverview != null && budgetVersionOverview.getBudgetVersionNumber() > version 
                    		&& getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED).equals(
                    				((AwardBudgetVersionOverviewExt) budgetVersionOverview).getAwardBudgetStatusCode()) 
                    		&& budgetVersionOverview.getBudgetVersionNumber() < this.getBudgetVersionNumber()) {
                        version = budgetVersionOverview.getBudgetVersionNumber();
                        prevBudget = budgetVersionOverview;
                    }
                }
            }
            if (prevBudget == null) {
                prevBudget = new BudgetVersionOverview();
                prevBudget.setCostSharingAmount(ScaleTwoDecimal.ZERO);
                prevBudget.setTotalCost(ScaleTwoDecimal.ZERO);
                prevBudget.setTotalCostLimit(ScaleTwoDecimal.ZERO);
                prevBudget.setTotalDirectCost(ScaleTwoDecimal.ZERO);
                prevBudget.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
                prevBudget.setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);
            }
        }
        return prevBudget;
    }

    public BudgetVersionOverview getFirstBudget() {
        if (firstBudget == null && this.getBudgetDocument() != null) {
            Integer version = 0;
            for (BudgetDocumentVersion budgetDocumentVersion : this.getBudgetDocument().getParentDocument().getBudgetDocumentVersions()) {
                for (BudgetVersionOverview budgetVersionOverview : budgetDocumentVersion.getBudgetVersionOverviews()) {
                    if (budgetVersionOverview != null && getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED).equals(
                                    ((AwardBudgetVersionOverviewExt)budgetVersionOverview).getAwardBudgetStatusCode())) {
                        firstBudget = budgetVersionOverview;
                        return firstBudget;
                    }
                }
            }
            if (firstBudget == null) {
                firstBudget = new BudgetVersionOverview();
                firstBudget.setCostSharingAmount(getCostSharingAmount());
                firstBudget.setTotalCost(getTotalCost());
                firstBudget.setTotalCostLimit(getTotalCostLimit());
                firstBudget.setTotalDirectCost(getTotalDirectCost());
                firstBudget.setTotalIndirectCost(getTotalIndirectCost());
                firstBudget.setUnderrecoveryAmount(getUnderrecoveryAmount());
            }
        }
        return firstBudget;
    }
    
    public void setPrevBudget(BudgetVersionOverview prevBudget) {
        this.prevBudget = prevBudget;
    }

    public List<ScaleTwoDecimal> getBudgetsTotals() {
        addBudgetTotals();
        return budgetsTotals;
    }

    public void setBudgetsTotals(List<ScaleTwoDecimal> budgetsTotals) {
        this.budgetsTotals = budgetsTotals;
    }

    private void addBudgetTotals() {
        List<ScaleTwoDecimal> totals = new ArrayList<ScaleTwoDecimal>();
        totals.add(this.getTotalCost().add(getPrevBudget().getTotalCost()));
        totals.add(this.getTotalDirectCost().add(getPrevBudget().getTotalDirectCost()));
        totals.add(this.getTotalIndirectCost().add(getPrevBudget().getTotalIndirectCost()));
        totals.add(this.getUnderrecoveryAmount().add(getPrevBudget().getUnderrecoveryAmount()));
        totals.add(this.getCostSharingAmount().add(getPrevBudget().getCostSharingAmount()));
        this.setBudgetsTotals(totals);
    }

    public String getRebudgetFlag() {
        String rebudgetTypeCode = getParameterValue(KeyConstants.AWARD_BUDGET_TYPE_REBUDGET);
        return Boolean.toString(getAwardBudgetTypeCode().equals(rebudgetTypeCode));
    }
    
    private String getParameterValue(String parameter) {
        return CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(AwardBudgetDocument.class, parameter);
    }

    public List<AwardBudgetLimit> getAwardBudgetLimits() {
        return awardBudgetLimits;
    }

    public void setAwardBudgetLimits(List<AwardBudgetLimit> awardBudgetLimits) {
        this.awardBudgetLimits = awardBudgetLimits;
    }

    public SortedMap<CostElement, ScaleTwoDecimal> getObjectCodeBudgetTotals() {
        return objectCodeBudgetTotals;
    }

    public void setObjectCodeBudgetTotals(SortedMap<CostElement, ScaleTwoDecimal> objectCodeBudgetTotals) {
        this.objectCodeBudgetTotals = objectCodeBudgetTotals;
    }

    public SortedMap<RateType, ScaleTwoDecimal> getCalculatedExpenseBudgetTotals() {
        return calculatedExpenseBudgetTotals;
    }

    public void setCalculatedExpenseBudgetTotals(SortedMap<RateType, ScaleTwoDecimal> calculatedExpenseBudgetTotals) {
        this.calculatedExpenseBudgetTotals = calculatedExpenseBudgetTotals;
    }

    public SortedMap<String, ScaleTwoDecimal> getTotalBudgetSummaryTotals() {
        return totalBudgetSummaryTotals;
    }

    public void setTotalBudgetSummaryTotals(SortedMap<String, ScaleTwoDecimal> totalBudgetSummaryTotals) {
        this.totalBudgetSummaryTotals = totalBudgetSummaryTotals;
    }

    public SortedMap<String, ScaleTwoDecimal> getObjectCodePersonnelFringeBudgetTotals() {
        return objectCodePersonnelFringeBudgetTotals;
    }

    public void setObjectCodePersonnelFringeBudgetTotals(SortedMap<String, ScaleTwoDecimal> objectCodePersonnelFringeBudgetTotals) {
        this.objectCodePersonnelFringeBudgetTotals = objectCodePersonnelFringeBudgetTotals;
    }

    public SortedMap<RateType, ScaleTwoDecimal> getPersonnelCalculatedExpenseBudgetTotals() {
        return personnelCalculatedExpenseBudgetTotals;
    }

    public void setPersonnelCalculatedExpenseBudgetTotals(SortedMap<RateType, ScaleTwoDecimal> personnelCalculatedExpenseBudgetTotals) {
        this.personnelCalculatedExpenseBudgetTotals = personnelCalculatedExpenseBudgetTotals;
    }

    public SortedMap<RateType, ScaleTwoDecimal> getNonPersonnelCalculatedExpenseBudgetTotals() {
        return nonPersonnelCalculatedExpenseBudgetTotals;
    }

    public void setNonPersonnelCalculatedExpenseBudgetTotals(SortedMap<RateType, ScaleTwoDecimal> nonPersonnelCalculatedExpenseBudgetTotals) {
        this.nonPersonnelCalculatedExpenseBudgetTotals = nonPersonnelCalculatedExpenseBudgetTotals;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List deletionAwareList = super.buildListOfDeletionAwareLists();
        List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodSummaryCalculatedAmounts = new ArrayList<AwardBudgetPeriodSummaryCalculatedAmount>();
        for (BudgetPeriod persistableBusinessObject : getBudgetPeriods()) {
            awardBudgetPeriodSummaryCalculatedAmounts.addAll(((AwardBudgetPeriodExt) persistableBusinessObject).getAwardBudgetPeriodFringeAmounts());
            awardBudgetPeriodSummaryCalculatedAmounts.addAll(((AwardBudgetPeriodExt) persistableBusinessObject).getAwardBudgetPeriodFnAAmounts());
        }
        deletionAwareList.add(awardBudgetPeriodSummaryCalculatedAmounts);
        deletionAwareList.add(awardBudgetLimits);
        return deletionAwareList;
    }

    /**
     * Gets the sum of the Direct Cost Amount for all budget periods.
     * @return the amount
     */
    public ScaleTwoDecimal getSumDirectCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalDirectCost());
        }
        return amount;
    }
}
