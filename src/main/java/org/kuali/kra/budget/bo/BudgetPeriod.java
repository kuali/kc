/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.bo;

import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.util.DateUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.rice.jpa.annotations.Sequence;

@Entity
@Table(name="BUDGET_PERIODS")
@Sequence(name="SEQ_BUDGET_PERIOD_NUMBER", property="budgetPeriodId")
public class BudgetPeriod extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = -7318331486891820078L;
    @Id
	@Column(name="BUDGET_PERIOD_NUMBER")
	private Long budgetPeriodId;
    
    @Column(name="BUDGET_PERIOD")
	private Integer budgetPeriod;
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	@Column(name="VERSION_NUMBER")
	private Integer budgetVersionNumber;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="COMMENTS")
	private String comments;
	@Column(name="COST_SHARING_AMOUNT")
	private BudgetDecimal costSharingAmount;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="TOTAL_COST")
	private BudgetDecimal totalCost;
	@Column(name="TOTAL_COST_LIMIT")
	private BudgetDecimal totalCostLimit;
	@Column(name="TOTAL_DIRECT_COST")
	private BudgetDecimal totalDirectCost;
	@Column(name="TOTAL_INDIRECT_COST")
	private BudgetDecimal totalIndirectCost;
	@Column(name="UNDERRECOVERY_AMOUNT")
	private BudgetDecimal underrecoveryAmount;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetLineItem.class, mappedBy="myBudgetPeriod")
	private List<BudgetLineItem> budgetLineItems;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="BUDGET_PERIOD_NUMBER", insertable=false, updatable=false)
    private BudgetModular budgetModular;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="VERSION_NUMBER", insertable = false, updatable = false)})
    private BudgetDocument budgetDocument;
	
	// expences total for 'totals' page
	// if 'totalCost' is intended for 'totals' page, then this is not needed
	@Transient
    private BudgetDecimal expenseTotal;
	
	@Transient
    private Date oldEndDate;
	
	@Transient
    private Date oldStartDate;
    
    @Transient
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	public BudgetPeriod(){
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
        return new StringBuilder()
		            .append(budgetPeriod)
		            .append(": ")
		            .append(getDateRangeLabel())
		            .toString();
	}

	public String getDateRangeLabel() {
	    return new StringBuilder().append(dateFormatter.format(startDate)).append(" - ").append(dateFormatter.format(endDate)).toString();
	}
	
	public void setBudgetPeriod(Integer budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BudgetDecimal getCostSharingAmount() {
		return costSharingAmount == null ?  BudgetDecimal.ZERO : costSharingAmount;
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
		return totalCost == null ?  new BudgetDecimal(0) : totalCost;
	}

	public void setTotalCost(BudgetDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BudgetDecimal getTotalCostLimit() {
		return totalCostLimit == null ?  new BudgetDecimal(0) : totalCostLimit;
	}

	public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
		this.totalCostLimit = totalCostLimit;
	}

	public BudgetDecimal getTotalDirectCost() {
		return totalDirectCost == null ?  new BudgetDecimal(0) : totalDirectCost;
	}

	public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
		this.totalDirectCost = totalDirectCost;
	}

	public BudgetDecimal getTotalIndirectCost() {
		return totalIndirectCost == null ?  new BudgetDecimal(0) : totalIndirectCost;
	}

	public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
		this.totalIndirectCost = totalIndirectCost;
	}

	public BudgetDecimal getUnderrecoveryAmount() {
		return underrecoveryAmount == null ?  new BudgetDecimal(0) : underrecoveryAmount;
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
        while (getBudgetLineItems().size() <= index) {
            getBudgetLineItems().add(new BudgetLineItem());
        }
        return getBudgetLineItems().get(index);
    }
    
	public BudgetModular getBudgetModular() {
        return budgetModular;
    }

    public void setBudgetModular(BudgetModular budgetModular) {
        this.budgetModular = budgetModular;
    }

    @SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetBudgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("comments", getComments());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("endDate", getEndDate());
		hashMap.put("startDate", getStartDate());
		hashMap.put("totalCost", getTotalCost());
		hashMap.put("totalCostLimit", getTotalCostLimit());
		hashMap.put("totalDirectCost", getTotalDirectCost());
		hashMap.put("totalIndirectCost", getTotalIndirectCost());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    /**
     * Gets the budgetLineItems attribute. 
     * @return Returns the budgetLineItems.
     */
    public List<BudgetLineItem> getBudgetLineItems() {
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
        if(startDate == null || fiscalYearStartDateTime == null) { return 0; }

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
        if (this.getStartDate() != null) {
            dateRange.append(this.getStartDate().toString());
        }
        dateRange.append(" - ");
        if (this.getEndDate() != null) {
            dateRange.append(this.getEndDate().toString());
        }
        return dateRange.toString();
    }
    
    /**
     * This class compares two BudgetPeriods to determine which should be considered earlier.
     */
    private static class BudgetPeriodDateComparator implements Comparator<BudgetPeriod> {
        private final static int FIRST_EQUALS_SECOND = 0;
        private final static int FIRST_LESS_THAN_SECOND = -1;
        
        public int compare(BudgetPeriod bp1, BudgetPeriod bp2) {
            int result = compareDates(bp1.getStartDate(), bp2.getStartDate());
            if(result == FIRST_EQUALS_SECOND) {
                result = compareDates(bp1.getEndDate(), bp2.getEndDate());
            }
            return result;
        }
        
        private int compareDates(Date d1, Date d2) {
            if(d1 != null) {
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

    public BudgetDocument getBudgetDocument() {
        return budgetDocument;
    }

    public void setBudgetDocument(BudgetDocument budgetDocument) {
        this.budgetDocument = budgetDocument;
    }
}

