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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.framework.persistence.BooleanNFConverter;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.util.ObjectUtils;

@Entity
@Table(name = "BUDGET_DETAILS")
public class BudgetLineItem extends BudgetLineItemBase implements HierarchyMaintainable, Comparable<BudgetLineItem>, BudgetLineItemContract {

    @Column(name = "COST_SHARING_AMOUNT")
    private ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;

    @Column(name = "BUDGET_DETAILS_ID")
    @Id
    @PortableSequenceGenerator(name = "SEQ_BUDGET_DETAILS_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_DETAILS_ID")
    private Long budgetLineItemId; 

    @Column(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false)
    private Long budgetPeriodId; 

    @Column(name = "LINE_ITEM_NUMBER")
    private Integer lineItemNumber; 

    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod; 

    @Column(name = "APPLY_IN_RATE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean applyInRateFlag; 

    @Column(name = "BASED_ON_LINE_ITEM")
    private Integer basedOnLineItem; 

    @Column(name = "BUDGET_CATEGORY_CODE")
    private String budgetCategoryCode; 

    @Column(name = "BUDGET_JUSTIFICATION")
    @Lob
    private String budgetJustification; 

    @Column(name = "COST_ELEMENT")
    private String costElement; 

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Transient
    private ScaleTwoDecimal totalCostSharingAmount; 

    @Column(name = "END_DATE")
    private Date endDate; 

    @Column(name = "LINE_ITEM_COST")
    private ScaleTwoDecimal lineItemCost = ScaleTwoDecimal.ZERO; 

    @Column(name = "LINE_ITEM_DESCRIPTION")
    private String lineItemDescription; 

    @Column(name = "LINE_ITEM_SEQUENCE")
    private Integer lineItemSequence;

    @Column(name = "ON_OFF_CAMPUS_FLAG")
    @Convert(converter = BooleanNFConverter.class)
    private Boolean onOffCampusFlag;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "UNDERRECOVERY_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal underrecoveryAmount = ScaleTwoDecimal.ZERO;

    @Column(name = "SUBMIT_COST_SHARING")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean submitCostSharingFlag = Boolean.TRUE;

    @Column(name = "IS_FORMULATED_COST_ELELMENT")
    private Boolean formulatedCostElementFlag;

    @OneToMany(mappedBy="budgetLineItem", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts;

    @OneToMany(mappedBy="budgetLineItem", orphanRemoval= true, cascade = { CascadeType.ALL })
    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;

    @Column(name = "SUBAWARD_NUMBER", insertable = false, updatable = false)
    private Integer subAwardNumber;
    
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false), @JoinColumn(name = "SUBAWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private BudgetSubAwards budgetSubAward;
    
    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "HIERARCHY_PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal hierarchyProposal;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @OneToMany(mappedBy="budgetLineItem", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetRateAndBase> budgetRateAndBaseList;

    @OneToMany(mappedBy="budgetLineItem", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetFormulatedCostDetail> budgetFormulatedCosts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "BUDGET_CATEGORY_CODE", referencedColumnName = "BUDGET_CATEGORY_CODE", insertable = false, updatable = false)
    private BudgetCategory budgetCategory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "COST_ELEMENT", referencedColumnName = "COST_ELEMENT", insertable = false, updatable = false)
    private CostElement costElementBO;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    private Budget budget;

    //ignore the budget period bo during deep copy as any link up the budget object graph
    //will cause generateAllPeriods to consume large amounts of memory
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "BUDGET_PERIOD_NUMBER", referencedColumnName = "BUDGET_PERIOD_NUMBER")
    private BudgetPeriod budgetPeriodBO;

    @Transient
    private transient boolean displayTotalDetail;

    @Transient
    private transient ScaleTwoDecimal objectTotal;

    @Transient
    private boolean budgetPersonnelLineItemDeleted;

    @Transient
    private Date oldStartDate;

    @Transient
    private Date oldEndDate;

    public BudgetLineItem() {
        super();
        budgetPersonnelDetailsList = new ArrayList<>();
        budgetLineItemCalculatedAmounts = new ArrayList<>();
        budgetRateAndBaseList = new ArrayList<>();
        displayTotalDetail = false;
        objectTotal = new ScaleTwoDecimal(0);
        budgetFormulatedCosts = new ArrayList<>();
    }
    @Override
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    @Override
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    @Override
    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    @Override
    public Integer getLineItemNumber() {
        return lineItemNumber;
    }

    @Override
    public void setLineItemNumber(Integer lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    @Override
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    @Override
    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    @Override
    public Boolean getApplyInRateFlag() {
        return applyInRateFlag;
    }

    @Override
    public void setApplyInRateFlag(Boolean applyInRateFlag) {
        this.applyInRateFlag = applyInRateFlag;
    }

    @Override
    public Integer getBasedOnLineItem() {
        return basedOnLineItem;
    }

    @Override
    public void setBasedOnLineItem(Integer basedOnLineItem) {
        this.basedOnLineItem = basedOnLineItem;
    }

    @Override
    public String getBudgetCategoryCode() {
        return budgetCategoryCode;
    }

    @Override
    public void setBudgetCategoryCode(String budgetCategoryCode) {
        this.budgetCategoryCode = budgetCategoryCode;
    }

    @Override
    public String getBudgetJustification() {
        return budgetJustification;
    }

    @Override
    public void setBudgetJustification(String budgetJustification) {
        this.budgetJustification = budgetJustification;
    }

    @Override
    public String getCostElement() {
        return costElement;
    }

    @Override
    public void setCostElement(String costElement) {
        this.costElement = costElement;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getLineItemDescription() {
        return lineItemDescription;
    }

    @Override
    public void setLineItemDescription(String lineItemDescription) {
        this.lineItemDescription = lineItemDescription;
    }

    @Override
    public Integer getLineItemSequence() {
        return lineItemSequence;
    }

    @Override
    public void setLineItemSequence(Integer lineItemSequence) {
        this.lineItemSequence = lineItemSequence;
    }

    @Override
    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    @Override
    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public List<BudgetFormulatedCostDetail> getBudgetFormulatedCosts() {
        return budgetFormulatedCosts;
    }

    @Override
    public void setBudgetFormulatedCosts(List<BudgetFormulatedCostDetail> budgetFormulatedCosts) {
        this.budgetFormulatedCosts = budgetFormulatedCosts;
    }

    @Override
    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    @Override
    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    @Override
    public CostElement getCostElementBO() {
        return costElementBO;
    }

    @Override
    public void setCostElementBO(CostElement costElementBO) {
        this.costElementBO = costElementBO;
    }

    @Override
    public BudgetPeriod getBudgetPeriodBO() {
        return budgetPeriodBO;
    }

    @Override
    public void setBudgetPeriodBO(BudgetPeriod budgetPeriodBO) {
        this.budgetPeriodBO = budgetPeriodBO;
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    @Override
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

    public BudgetPersonnelDetails getBudgetPersonnelDetails(int index) {
        while (getBudgetPersonnelDetailsList().size() <= index) {
            getBudgetPersonnelDetailsList().add(getNewBudgetPersonnelLineItem());
        }
        return getBudgetPersonnelDetailsList().get(index);
    }

    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new BudgetPersonnelDetails();
    }

    @Override
    public List<BudgetLineItemCalculatedAmount> getBudgetLineItemCalculatedAmounts() {
        return budgetLineItemCalculatedAmounts;
    }

    public void setBudgetLineItemCalculatedAmounts(List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts) {
        this.budgetLineItemCalculatedAmounts = budgetLineItemCalculatedAmounts;
    }

    @Override
    public List<BudgetLineItemCalculatedAmount> getBudgetCalculatedAmounts() {
        return getBudgetLineItemCalculatedAmounts();
    }

    public boolean isBudgetPersonnelLineItemDeleted() {
        return budgetPersonnelLineItemDeleted;
    }

    public void setBudgetPersonnelLineItemDeleted(boolean budgetPersonnelLineItemDeleted) {
        this.budgetPersonnelLineItemDeleted = budgetPersonnelLineItemDeleted;
    }

    @Override
    public List<BudgetRateAndBase> getBudgetRateAndBaseList() {
        return budgetRateAndBaseList;
    }

    public void setBudgetRateAndBaseList(List<BudgetRateAndBase> budgetRateAndBaseList) {
        this.budgetRateAndBaseList = budgetRateAndBaseList;
    }

    public Date getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(Date oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public Date getOldEndDate() {
        return oldEndDate;
    }

    public void setOldEndDate(Date oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

    @Override
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    @Override
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    public boolean isDisplayTotalDetail() {
        return displayTotalDetail;
    }

    public void setDisplayTotalDetail(boolean displayTotalDetail) {
        this.displayTotalDetail = displayTotalDetail;
    }

    public ScaleTwoDecimal getObjectTotal() {
        return objectTotal;
    }

    public void setObjectTotal(ScaleTwoDecimal objectTotal) {
        this.objectTotal = objectTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLineItemNumber() == null) ? 0 : getLineItemNumber().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BudgetLineItem other = (BudgetLineItem) obj;
        if (getLineItemNumber() == null) {
            if (other.getLineItemNumber() != null)
                return false;
        } else if (!getLineItemNumber().equals(other.getLineItemNumber()))
            return false;
        if (getCostElement() == null) {
            if (other.getCostElement() != null)
                return false;
        } else if (!getCostElement().equals(other.getCostElement()))
            return false;
        return true;
    }

    public AbstractBudgetCalculatedAmount getNewBudgetLineItemCalculatedAmount() {
        return new BudgetLineItemCalculatedAmount();
    }

    /**
     * Calculated comparable based on the cost element, then on the line item sequence.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(BudgetLineItem o) {
        int compare = this.getCostElementName().compareTo(o.getCostElementName());
        if (compare == 0) {
            compare = this.getLineItemNumber().compareTo(o.getLineItemNumber());
        }
        return compare;
    }

    public boolean isSubAwardLineItem() {
        return getBudgetSubAward() != null;
    }

    @Override
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

    @Override
    public ScaleTwoDecimal getCostSharingAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(costSharingAmount);
    }

    @Override
    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    @Override
    public ScaleTwoDecimal getLineItemCost() {
        return ScaleTwoDecimal.returnZeroIfNull(lineItemCost);
    }

    @Override
    public void setLineItemCost(ScaleTwoDecimal lineItemCost) {
        this.lineItemCost = lineItemCost;
    }

    @Override
    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(underrecoveryAmount);
    }

    @Override
    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    @Override
    public ScaleTwoDecimal getTotalCostSharingAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(totalCostSharingAmount);
    }

    @Override
    public void setTotalCostSharingAmount(ScaleTwoDecimal totalCostSharingAmount) {
        this.totalCostSharingAmount = totalCostSharingAmount;
    }

    @Override
    public void setSubmitCostSharingFlag(Boolean submitCostSharingFlag) {
        this.submitCostSharingFlag = submitCostSharingFlag;
    }

    @Override
    public Boolean getSubmitCostSharingFlag() {
        if (ObjectUtils.isNull(budgetPeriodBO)) {
            this.refreshReferenceObject("budgetPeriodBO");
        }
        return (getBudgetPeriodBO() != null && getBudgetPeriodBO().getBudget().getSubmitCostSharingFlag()) ? submitCostSharingFlag : false;
    }

    @Override
    public Boolean getFormulatedCostElementFlag() {
        return formulatedCostElementFlag == null ? Boolean.FALSE : formulatedCostElementFlag;
    }

    @Override
    public void setFormulatedCostElementFlag(Boolean formulatedCostElementFlag) {
        this.formulatedCostElementFlag = formulatedCostElementFlag;
    }

	public String getLineItemGroupDescription() {
		StringBuilder personDetailGroup = new StringBuilder();
		personDetailGroup.append(getCostElementBO().getDescription());
		if(StringUtils.isNoneBlank(getGroupName())) {
			personDetailGroup.append(" (");
			personDetailGroup.append(getGroupName());
			personDetailGroup.append(")");
		}
		return personDetailGroup.toString();
	}

	public Budget getBudget() {
		return budget;
	}
	
	public void setBudget(Budget budget) {
		this.budget = budget;
		if (budget != null) {
			budgetId = budget.getBudgetId();
		} else {
			budgetId = null;
		}
	}
	
	public boolean isPersonnelLineItem() {
		return getBudgetPersonnelDetailsList().size() > 0;
	}
	public BudgetSubAwards getBudgetSubAward() {
		return budgetSubAward;
	}
	public void setBudgetSubAward(BudgetSubAwards budgetSubAward) {
		this.budgetSubAward = budgetSubAward;
		if (budgetSubAward != null) {
			subAwardNumber = budgetSubAward.getSubAwardNumber();
			setBudget(budgetSubAward.getBudget());
		}
	}
	public DevelopmentProposal getHierarchyProposal() {
		return hierarchyProposal;
	}
	public void setHierarchyProposal(DevelopmentProposal hierarchyProposal) {
		this.hierarchyProposal = hierarchyProposal;
	}

}
