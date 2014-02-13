/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.budget.parameters;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.deepcopy.DeepCopyIgnore;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetNumberOfMonthsService;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class BudgetPeriod extends BudgetAssociate {

    private static final long serialVersionUID = -7318331486891820078L;

    @DeepCopyIgnore
    private Long budgetPeriodId;

    private Integer budgetPeriod;

    private String comments;

    private BudgetDecimal costSharingAmount;

    private Date endDate;

    private Date startDate;

    private BudgetDecimal totalCost;

    private BudgetDecimal totalCostLimit;

    private BudgetDecimal totalDirectCost;

    private BudgetDecimal totalIndirectCost;

    private BudgetDecimal underrecoveryAmount;

    private List<BudgetLineItem> budgetLineItems;
    
    private Integer numberOfParticipants;

    // expences total for 'totals' page 
    // if 'totalCost' is intended for 'totals' page, then this is not needed 
    private BudgetDecimal expenseTotal;

    private Date oldEndDate;

    private Date oldStartDate;

    private BudgetModular budgetModular;

    private Budget budget;

    //this is for lookup from award budget 
    private String budgetParentId;

    private String institutionalProposalNumber;

    private Integer institutionalProposalVersion;

    private BudgetDecimal directCostLimit;
    

    // This is a BO and hence will not be shared between threads. dateFormatter here is thread safe. 
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    public BudgetPeriod() {
        budgetLineItems = new ArrayList<BudgetLineItem>();
    }

    /**
	 * Factory method for creating a BudgetPeriodDateComparator
	 * @return
	 */
    public static Comparator<BudgetPeriod> getBudgetPeriodDateComparator() {
        return new BudgetPeriodDateComparator();
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public String getLabel() {
        return new StringBuilder().append(budgetPeriod).append(": ").append(getDateRangeLabel()).toString();
    }

    public String getDateRangeLabel() {
        return new StringBuilder().append(dateFormatter.format(startDate)).append(" - ").append(dateFormatter.format(endDate)).toString();
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount == null ? BudgetDecimal.ZERO : costSharingAmount;
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BudgetDecimal getTotalCost() {
        return totalCost == null ? new BudgetDecimal(0) : totalCost;
    }

    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BudgetDecimal getTotalCostLimit() {
        return totalCostLimit == null ? new BudgetDecimal(0) : totalCostLimit;
    }

    public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost == null ? new BudgetDecimal(0) : totalDirectCost;
    }

    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ? new BudgetDecimal(0) : totalIndirectCost;
    }

    public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public BudgetDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ? new BudgetDecimal(0) : underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    /**
     * Gets BudgetLineItem from budgetLineItems list at index.
     * 
     * @param index
     * @return BudgetLineItem at index
     */
    public BudgetLineItem getBudgetLineItem(int index) {
        List<BudgetLineItem> items = getBudgetLineItems();
        while (items.size() <= index) {
            items.add(getNewBudgetLineItem());
        }
        return items.get(index);
    }

    public BudgetLineItem getNewBudgetLineItem() {
        return new BudgetLineItem();
    }

    public BudgetModular getBudgetModular() {
        return budgetModular;
    }

    public void setBudgetModular(BudgetModular budgetModular) {
        this.budgetModular = budgetModular;
    }

    /**
     * Gets the budgetLineItems attribute. 
     * @return Returns the budgetLineItems.
     */
    public List<BudgetLineItem> getBudgetLineItems() {
        Collections.sort(budgetLineItems);
        return budgetLineItems;
    }

    /**
     * Sets the budgetLineItems attribute value.
     * @param budgetLineItems The budgetLineItems to set.
     */
    public void setBudgetLineItems(List<BudgetLineItem> budgetLineItems) {
        this.budgetLineItems = budgetLineItems;
    }

    /**
     * This method determines whether
     * @return
     */
    public boolean isReadOnly() {
        //return (budgetPeriod != null && budgetPeriod == 1) || budgetLineItems.size() > 0; 
        return budgetLineItems != null && budgetLineItems.size() > 0;
    }

    public BudgetDecimal getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(BudgetDecimal expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public int calculateFiscalYear(Date fiscalYearStartDateTime) {
        if (startDate == null || fiscalYearStartDateTime == null) {
            return 0;
        }
        Date fiscalYearStartDate = DateUtils.clearTimeFields(fiscalYearStartDateTime);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(startDate);
        int startDateYear = calendar.get(Calendar.YEAR);
        calendar.setTime(fiscalYearStartDate);
        calendar.set(Calendar.YEAR, startDateYear);
        Date startDateYearFiscalYearBeginning = new Date(calendar.getTimeInMillis());
        return startDate.compareTo(startDateYearFiscalYearBeginning) < 0 ? startDateYear : ++startDateYear;
    }

    public String getDateRange() {
        StringBuffer dateRange = new StringBuffer();
        if (this.getStartDate() != null && this.getEndDate() != null) {
            dateRange.append(getDateRangeLabel());
        }
        return dateRange.toString();
    }

    /**
     * This class compares two BudgetPeriods to determine which should be considered earlier.
     */
    private static class BudgetPeriodDateComparator implements Comparator<BudgetPeriod>, Serializable {

        private static final long serialVersionUID = 201595688750841114L;

        private static final int FIRST_EQUALS_SECOND = 0;

        private static final int FIRST_LESS_THAN_SECOND = -1;

        public int compare(BudgetPeriod bp1, BudgetPeriod bp2) {
            int result = compareDates(bp1.getStartDate(), bp2.getStartDate());
            if (result == FIRST_EQUALS_SECOND) {
                result = compareDates(bp1.getEndDate(), bp2.getEndDate());
            }
            return result;
        }

        private int compareDates(Date d1, Date d2) {
            if (d1 != null) {
                return d1.compareTo(d2);
            } else {
                return (d2 != null) ? FIRST_LESS_THAN_SECOND : FIRST_EQUALS_SECOND;
            }
        }
    }

    public Date getOldEndDate() {
        return oldEndDate;
    }

    public void setOldEndDate(Date oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

    public Date getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(Date oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    /**
     * Gets the sum of the Total Underreovery Amount for all line items.
     * @param lineItems the budget line items
     * @return the amount.
     */
    public final BudgetDecimal getSumUnderreoveryAmountFromLineItems() {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getUnderrecoveryAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the CostSharing Amount for all line items.
     * @return the amount
     */
    public final BudgetDecimal getSumCostSharingAmountFromLineItems() {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getCostSharingAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the Total CostSharing Amount for all line items.
     * @return the amount
     */
    public final BudgetDecimal getSumTotalCostSharingAmountFromLineItems() {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getTotalCostSharingAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the Direct Cost Amount for all line items.
     * @return the amount
     */
    public final BudgetDecimal getSumDirectCostAmountFromLineItems() {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getDirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Indirect Cost Amount for all line items.
     * @return the amount
     */
    public final BudgetDecimal getSumIndirectCostAmountFromLineItems() {
        BudgetDecimal amount = BudgetDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getIndirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Total Cost Amount for all line items.
     * @return the amount
     */
    public final BudgetDecimal getSumTotalCostAmountFromLineItems() {
        return this.getSumDirectCostAmountFromLineItems().add(this.getSumIndirectCostAmountFromLineItems());
    }

    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget attribute value.
     * @param budget The budget to set.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Sets the budgetParentId attribute value.
     * @param budgetParentId The budgetParentId to set.
     */
    public void setBudgetParentId(String budgetParentId) {
        this.budgetParentId = budgetParentId;
    }

    /**
     * Gets the budgetParentId attribute. 
     * @return Returns the budgetParentId.
     */
    public String getBudgetParentId() {
        return budgetParentId;
    }

    public String getInstitutionalProposalNumber() {
        return institutionalProposalNumber;
    }

    public void setInstitutionalProposalNumber(String institutionalProposalNumber) {
        this.institutionalProposalNumber = institutionalProposalNumber;
    }

    public Integer getInstitutionalProposalVersion() {
        return institutionalProposalVersion;
    }

    public void setInstitutionalProposalVersion(Integer institutionalProposalVersion) {
        this.institutionalProposalVersion = institutionalProposalVersion;
    }

    /**
     * Gets the directCostLimit attribute. 
     * @return Returns the directCostLimit.
     */
    public BudgetDecimal getDirectCostLimit() {
        return directCostLimit == null ? BudgetDecimal.ZERO : directCostLimit;
    }

    /**
     * Sets the directCostLimit attribute value.
     * @param directCostLimit The directCostLimit to set.
     */
    public void setDirectCostLimit(BudgetDecimal directCostLimit) {
        this.directCostLimit = directCostLimit;
    }
    
    public String getNumberOfMonths() {
        return String.valueOf(this.getProposalBudgetNumberOfMonthsService().getNumberOfMonth(this.getStartDate(), this.getEndDate()));
    }
    
    protected ProposalBudgetNumberOfMonthsService getProposalBudgetNumberOfMonthsService() {
        return KcServiceLocator.getService(ProposalBudgetNumberOfMonthsService.class);
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}
