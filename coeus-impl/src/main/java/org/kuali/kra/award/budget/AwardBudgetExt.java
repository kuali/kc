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
package org.kuali.kra.award.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class AwardBudgetExt extends Budget {

    private static final long serialVersionUID = 1L;
    
    private Award award;
    
    private Long awardId;

    private String awardBudgetStatusCode;

    private String awardBudgetTypeCode;

    private ScaleTwoDecimal obligatedTotal;

    private ScaleTwoDecimal obligatedAmount = ScaleTwoDecimal.ZERO;

    private AwardBudgetStatus awardBudgetStatus;

    private AwardBudgetType awardBudgetType;

    private String description;

    private String budgetInitiator;

    private AwardBudgetExt prevBudget;

    private List<ScaleTwoDecimal> budgetsTotals;

    private List<AwardBudgetLimit> awardBudgetLimits;

    private AwardBudgetExt firstBudget;
    
    private SortedMap<CostElement, ScaleTwoDecimal> objectCodeBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> calculatedExpenseBudgetTotals;

    private SortedMap<String, ScaleTwoDecimal> totalBudgetSummaryTotals;

    private SortedMap<String, ScaleTwoDecimal> objectCodePersonnelFringeBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> personnelCalculatedExpenseBudgetTotals;

    private SortedMap<RateType, ScaleTwoDecimal> nonPersonnelCalculatedExpenseBudgetTotals;
    
    private AwardBudgetDocument awardBudgetDocument;
    
    private ScaleTwoDecimal totalDirectCostInclPrev;
    private ScaleTwoDecimal totalIndirectCostInclPrev;
    private ScaleTwoDecimal totalCostInclPrev;

    public AwardBudgetExt() {
        super();
        setParentDocumentTypeCode("AWRD");
        awardBudgetLimits = new ArrayList<>();
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

    public boolean getOhRatesNonEditable() {
        Award award = getBudgetDocument().getBudget().getBudgetParent();
        return !award.getAwardFandaRate().isEmpty();
    }

    public boolean getEbRatesNonEditable() {
        Award award = getBudgetDocument().getBudget().getBudgetParent();
        return ((award.getSpecialEbRateOffCampus() != null && award.getSpecialEbRateOffCampus().isPositive()) || (award.getSpecialEbRateOnCampus() != null && award.getSpecialEbRateOnCampus().isPositive()));
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
     * @param obligatedAmount The obligatedTotal to set.
     */
    public void setObligatedTotal(ScaleTwoDecimal obligatedAmount) {
        this.obligatedTotal = obligatedAmount;
    }

    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount == null ? ScaleTwoDecimal.ZERO : obligatedAmount;
    }

    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudgetInitiator() {
        return budgetInitiator;
    }

    public void setBudgetInitiator(String budgetInitiator) {
        this.budgetInitiator = budgetInitiator;
    }

    public AwardBudgetExt getPrevBudget() {
        if (prevBudget == null && this.getBudgetDocument() != null) {
            Integer version = 0;
            for (AwardBudgetExt budgetVersion : (this.getBudgetParent()).getBudgets()) {
                if (budgetVersion != null && budgetVersion.getBudgetVersionNumber() > version
                        && getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED).equals(
                                budgetVersion.getAwardBudgetStatusCode())
                        && budgetVersion.getBudgetVersionNumber() < this.getBudgetVersionNumber()) {
                    version = budgetVersion.getBudgetVersionNumber();
                    prevBudget = budgetVersion;
                }

            }
            if (prevBudget == null) {
                prevBudget = new AwardBudgetExt();
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

    public AwardBudgetExt getFirstBudget() {
        if (firstBudget == null && this.getBudgetDocument() != null) {

            for (AwardBudgetExt budgetVersion : this.getBudgetParent().getBudgets()) {
                if (budgetVersion != null && getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED).equals(
                                budgetVersion.getAwardBudgetStatusCode())) {
                    firstBudget = budgetVersion;
                    return firstBudget;
                }
            }
            if (firstBudget == null) {
                firstBudget = new AwardBudgetExt();
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
    
    public void setPrevBudget(AwardBudgetExt prevBudget) {
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
        List<ScaleTwoDecimal> totals = new ArrayList<>();
        totals.add(this.getTotalCost().add(getPrevBudget().getTotalCost()));
        totals.add(this.getTotalDirectCost().add(getPrevBudget().getTotalDirectCost()));
        totals.add(this.getTotalIndirectCost().add(getPrevBudget().getTotalIndirectCost()));
        totals.add(this.getUnderrecoveryAmount().add(getPrevBudget().getUnderrecoveryAmount()));
        totals.add(this.getCostSharingAmount().add(getPrevBudget().getCostSharingAmount()));
        this.setBudgetsTotals(totals);
    }
    
    public boolean isRebudget() {
    	return StringUtils.equals(getAwardBudgetTypeCode(), getParameterValue(KeyConstants.AWARD_BUDGET_TYPE_REBUDGET));
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
        List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodSummaryCalculatedAmounts = new ArrayList<>();
        for (BudgetPeriod persistableBusinessObject : getBudgetPeriods()) {
            awardBudgetPeriodSummaryCalculatedAmounts.addAll(((AwardBudgetPeriodExt) persistableBusinessObject).getAwardBudgetPeriodFringeAmounts());
            awardBudgetPeriodSummaryCalculatedAmounts.addAll(((AwardBudgetPeriodExt) persistableBusinessObject).getAwardBudgetPeriodFnAAmounts());
        }

        @SuppressWarnings("unchecked")
        final List<Object> deletionAwareList = super.buildListOfDeletionAwareLists();
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

	public AwardBudgetDocument getBudgetDocument() {
		return awardBudgetDocument;
	}

	public void setBudgetDocument(AwardBudgetDocument budgetDocument) {
		this.awardBudgetDocument = budgetDocument;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	@Override
	public Award getBudgetParent() {
		return award;
	}

	@Override
	public String getParentDocumentKey() {
		return awardId.toString();
	}

	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	
    public java.util.Date getBudgetStartDate() {
        return getAward().getAwardAmountInfos().get(getAward().getAwardAmountInfos().size() - 1).getCurrentFundEffectiveDate();
    }

    public java.util.Date getBudgetEndDate() {
        return getAward().getAwardAmountInfos().get(getAward().getAwardAmountInfos().size() - 1).getObligationExpirationDate();
    }

    @Override
    public List<DocumentNextvalue> getNextValues() {
    	return getBudgetDocument().getDocumentNextvalues();
    }

    @Override
    public DocumentNextvalue getNewNextValue() {
    	return new DocumentNextvalue();
    }

    @Override
    public void add(DocumentNextvalue nextValue) {
    	getBudgetDocument().getDocumentNextvalues().add(nextValue);
    }

    public void initInclPreviousTotals() {
    	AwardBudgetExt prevBudget = getPrevBudget();
    	if (prevBudget != null) {
    		prevBudget.initInclPreviousTotals();
			setTotalCostInclPrev(getTotalCost().add(prevBudget.getTotalCostInclPrev()));
			setTotalDirectCostInclPrev(getTotalDirectCost().add(prevBudget.getTotalDirectCostInclPrev()));
			setTotalIndirectCostInclPrev(getTotalIndirectCost().add(prevBudget.getTotalIndirectCostInclPrev()));
		} else {
			setTotalCostInclPrev(getTotalCost());
			setTotalDirectCostInclPrev(getTotalDirectCost());
			setTotalIndirectCostInclPrev(getTotalIndirectCost());			
		}
    }
    
	public ScaleTwoDecimal getTotalDirectCostInclPrev() {
		if (totalDirectCostInclPrev == null) {
			initInclPreviousTotals();
		}
		return totalDirectCostInclPrev;
	}

	public void setTotalDirectCostInclPrev(ScaleTwoDecimal totalDirectCostInclPrev) {
		this.totalDirectCostInclPrev = totalDirectCostInclPrev;
	}

	public ScaleTwoDecimal getTotalIndirectCostInclPrev() {
		if (totalIndirectCostInclPrev == null) {
			initInclPreviousTotals();
		}
		return totalIndirectCostInclPrev;
	}

	public void setTotalIndirectCostInclPrev(
			ScaleTwoDecimal totalIndirectCostInclPrev) {
		this.totalIndirectCostInclPrev = totalIndirectCostInclPrev;
	}

	public ScaleTwoDecimal getTotalCostInclPrev() {
		if (totalCostInclPrev == null) {
			initInclPreviousTotals();
		}
		return totalCostInclPrev;
	}

	public void setTotalCostInclPrev(ScaleTwoDecimal totalCostInclPrev) {
		this.totalCostInclPrev = totalCostInclPrev;
	}
}
