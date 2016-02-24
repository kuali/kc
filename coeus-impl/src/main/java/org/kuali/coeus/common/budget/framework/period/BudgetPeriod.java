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
package org.kuali.coeus.common.budget.framework.period;

import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetNumberOfMonthsService;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PERIODS")
public class BudgetPeriod extends KcPersistableBusinessObjectBase implements BudgetPeriodContract {

    private static final long serialVersionUID = -7318331486891820078L;

    @PortableSequenceGenerator(name = "SEQ_BUDGET_PERIOD_NUMBER")
    @GeneratedValue(generator = "SEQ_BUDGET_PERIOD_NUMBER")
    @Id
    @Column(name = "BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;
    
    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Column(name = "COMMENTS")
    @Lob
    private String comments;

    @Column(name = "COST_SHARING_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal costSharingAmount;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "TOTAL_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalCost;

    @Column(name = "TOTAL_COST_LIMIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalCostLimit;

    @Column(name = "TOTAL_DIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalDirectCost;

    @Column(name = "TOTAL_INDIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalIndirectCost;

    @Column(name = "UNDERRECOVERY_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal underrecoveryAmount;

    @OneToMany(mappedBy="budgetPeriodBO", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetLineItem> budgetLineItems;

    @Column(name = "NUM_PARTICIPANTS")
    private Integer numberOfParticipants;

    @Column(name = "TOTAL_DIRECT_COST_LIMIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal directCostLimit;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "budgetPeriodObj",orphanRemoval = true, cascade = CascadeType.ALL)
    private BudgetModular budgetModular;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    private Budget budget;

    // expences total for 'totals' page  
    // if 'totalCost' is intended for 'totals' page, then this is not needed  
    @Transient
    private ScaleTwoDecimal expenseTotal;

    @Transient
    private Date oldEndDate;

    @Transient
    private Date oldStartDate;

    //this is for lookup from award budget
    @Transient
    private String budgetParentId;

    @Transient
    private String institutionalProposalNumber;

    @Transient
    private Integer institutionalProposalVersion;

    // This is a BO and hence will not be shared between threads. dateFormatter here is thread safe.  
    @Transient
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    public BudgetPeriod() {
        budgetLineItems = new ArrayList<BudgetLineItem>();
    }

    @Override
    public Long getBudgetId() {
        return getBudget().getBudgetId();
    }

    /**
	 * Factory method for creating a BudgetPeriodDateComparator
	 * @return
	 */
    public static Comparator<BudgetPeriod> getBudgetPeriodDateComparator() {
        return new BudgetPeriodDateComparator();
    }

    @Override
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

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount == null ? ScaleTwoDecimal.ZERO : costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public ScaleTwoDecimal getTotalCost() {
        return totalCost == null ? new ScaleTwoDecimal(0) : totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public ScaleTwoDecimal getTotalCostLimit() {
        return totalCostLimit == null ? new ScaleTwoDecimal(0) : totalCostLimit;
    }

    public void setTotalCostLimit(ScaleTwoDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    @Override
    public ScaleTwoDecimal getTotalDirectCost() {
        return totalDirectCost == null ? new ScaleTwoDecimal(0) : totalDirectCost;
    }

    public void setTotalDirectCost(ScaleTwoDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    @Override
    public ScaleTwoDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ? new ScaleTwoDecimal(0) : totalIndirectCost;
    }

    public void setTotalIndirectCost(ScaleTwoDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    @Override
    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ? new ScaleTwoDecimal(0) : underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
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

    @Override
    public BudgetModular getBudgetModular() {
        return budgetModular;
    }

    public void setBudgetModular(BudgetModular budgetModular) {
        this.budgetModular = budgetModular;
    }

    @Override
    public List<BudgetLineItem> getBudgetLineItems() {
        Collections.sort(budgetLineItems);
        return budgetLineItems;
    }

    public void setBudgetLineItems(List<BudgetLineItem> budgetLineItems) {
        this.budgetLineItems = budgetLineItems;
    }

    /**
     * This method determines whether
     * @return
     */
    public boolean isReadOnly() {
        return budgetLineItems != null && budgetLineItems.size() > 0;
    }

    public ScaleTwoDecimal getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(ScaleTwoDecimal expenseTotal) {
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
    
    public int getPeriodFiscalYear() {
    	return calculateFiscalYear(budget.loadFiscalYearStart());
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

    @Override
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    /**
     * Gets the sum of the Total Underreovery Amount for all line items.
     * @return the amount.
     */
    public final ScaleTwoDecimal getSumUnderreoveryAmountFromLineItems() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getUnderrecoveryAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the CostSharing Amount for all line items.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumCostSharingAmountFromLineItems() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getCostSharingAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the Total CostSharing Amount for all line items.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumTotalCostSharingAmountFromLineItems() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getTotalCostSharingAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the Direct Cost Amount for all line items.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumDirectCostAmountFromLineItems() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getDirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Indirect Cost Amount for all line items.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumIndirectCostAmountFromLineItems() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetLineItem lineItem : this.getBudgetLineItems()) {
            amount = amount.add(lineItem.getIndirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Total Cost Amount for all line items.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumTotalCostAmountFromLineItems() {
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

    @Override
    public ScaleTwoDecimal getDirectCostLimit() {
        return directCostLimit == null ? ScaleTwoDecimal.ZERO : directCostLimit;
    }

    public void setDirectCostLimit(ScaleTwoDecimal directCostLimit) {
        this.directCostLimit = directCostLimit;
    }

    public String getNumberOfMonths() {
        return String.valueOf(this.getProposalBudgetNumberOfMonthsService().getNumberOfMonth(this.getStartDate(), this.getEndDate()));
    }

    protected ProposalBudgetNumberOfMonthsService getProposalBudgetNumberOfMonthsService() {
        return KcServiceLocator.getService(ProposalBudgetNumberOfMonthsService.class);
    }

    @Override
    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}
