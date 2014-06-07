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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.framework.persistence.BooleanNFConverter;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.util.ObjectUtils;

@Entity
@Table(name = "BUDGET_DETAILS")
public class BudgetLineItem extends BudgetLineItemBase implements HierarchyMaintainable, Comparable<BudgetLineItem> {

    @Transient
    private ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;

    @DeepCopyIgnore
    @Column(name = "BUDGET_DETAILS_ID")
    @Id
    private Long budgetLineItemId; 

    @Column(name = "BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId; 

    @Column(name = "LINE_ITEM_NUMBER")
    private Integer lineItemNumber; 

    @Column(name = "BUDGET_ID")
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

    @Column(name = "COST_SHARING_AMOUNT")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false)
    private List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false)
    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;

    @Transient
    private boolean budgetPersonnelLineItemDeleted;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false)
    private List<BudgetRateAndBase> budgetRateAndBaseList;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID", insertable = false, updatable = false)
    private List<BudgetFormulatedCostDetail> budgetFormulatedCosts; 

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "BUDGET_CATEGORY_CODE", referencedColumnName = "BUDGET_CATEGORY_CODE", insertable = false, updatable = false)
    private BudgetCategory budgetCategory; 

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "COST_ELEMENT", referencedColumnName = "COST_ELEMENT", insertable = false, updatable = false)
    private CostElement costElementBO; 

    //ignore the budget period bo during deep copy as any link up the budget object graph
    //will cause generateAllPeriods to consume large amounts of memory
    @DeepCopyIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "BUDGET_PERIOD", referencedColumnName = "BUDGET_PERIOD", insertable = false, updatable = false)
    private BudgetPeriod budgetPeriodBO; 

    @Transient
    private Date oldStartDate;

    @Transient
    private Date oldEndDate;

    @Column(name = "SUBAWARD_NUMBER")
    private Integer subAwardNumber;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @Transient
    private transient boolean displayTotalDetail;

    @Transient
    private transient ScaleTwoDecimal objectTotal;

    public BudgetLineItem() {
        super();
        budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
        budgetRateAndBaseList = new ArrayList<BudgetRateAndBase>();
        displayTotalDetail = false;
        objectTotal = new ScaleTwoDecimal(0);
        budgetFormulatedCosts = new ArrayList<BudgetFormulatedCostDetail>();
    }

    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    public Integer getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(Integer lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public Boolean getApplyInRateFlag() {
        return applyInRateFlag;
    }

    public void setApplyInRateFlag(Boolean applyInRateFlag) {
        this.applyInRateFlag = applyInRateFlag;
    }

    public Integer getBasedOnLineItem() {
        return basedOnLineItem;
    }

    public void setBasedOnLineItem(Integer basedOnLineItem) {
        this.basedOnLineItem = basedOnLineItem;
    }

    public String getBudgetCategoryCode() {
        return budgetCategoryCode;
    }

    public void setBudgetCategoryCode(String budgetCategoryCode) {
        this.budgetCategoryCode = budgetCategoryCode;
    }

    public String getBudgetJustification() {
        return budgetJustification;
    }

    public void setBudgetJustification(String budgetJustification) {
        this.budgetJustification = budgetJustification;
    }

    public String getCostElement() {
        return costElement;
    }

    public void setCostElement(String costElement) {
        this.costElement = costElement;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLineItemDescription() {
        return lineItemDescription;
    }

    public void setLineItemDescription(String lineItemDescription) {
        this.lineItemDescription = lineItemDescription;
    }

    public Integer getLineItemSequence() {
        return lineItemSequence;
    }

    public void setLineItemSequence(Integer lineItemSequence) {
        this.lineItemSequence = lineItemSequence;
    }

    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<BudgetFormulatedCostDetail> getBudgetFormulatedCosts() {
        return budgetFormulatedCosts;
    }

    public void setBudgetFormulatedCosts(List<BudgetFormulatedCostDetail> budgetFormulatedCosts) {
        this.budgetFormulatedCosts = budgetFormulatedCosts;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public CostElement getCostElementBO() {
        return costElementBO;
    }

    public void setCostElementBO(CostElement costElementBO) {
        this.costElementBO = costElementBO;
    }

    public BudgetPeriod getBudgetPeriodBO() {
        return budgetPeriodBO;
    }

    public void setBudgetPeriodBO(BudgetPeriod budgetPeriodBO) {
        this.budgetPeriodBO = budgetPeriodBO;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

    /**
     * Gets BudgetPersonnelDetails from BudgetPersonnelDetails list at index.
     * 
     * @param index
     * @return BudgetPersonnelDetails at index
     */
    public BudgetPersonnelDetails getBudgetPersonnelDetails(int index) {
        while (getBudgetPersonnelDetailsList().size() <= index) {
            getBudgetPersonnelDetailsList().add(getNewBudgetPersonnelLineItem());
        }
        return getBudgetPersonnelDetailsList().get(index);
    }

    /**
     * 
     * This method is to create new BudgetpersonnelDetails object
     * @return
     */
    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new BudgetPersonnelDetails();
    }

    /**
     * Gets the budgetLineItemCalculatedAmounts attribute. 
     * @return Returns the budgetLineItemCalculatedAmounts.
     */
    public List<BudgetLineItemCalculatedAmount> getBudgetLineItemCalculatedAmounts() {
        return budgetLineItemCalculatedAmounts;
    }

    /**
     * Sets the budgetLineItemCalculatedAmounts attribute value.
     * @param budgetLineItemCalculatedAmounts The budgetLineItemCalculatedAmounts to set.
     */
    public void setBudgetLineItemCalculatedAmounts(List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts) {
        this.budgetLineItemCalculatedAmounts = budgetLineItemCalculatedAmounts;
    }

    public List getBudgetCalculatedAmounts() {
        return getBudgetLineItemCalculatedAmounts();
    }

    /**
     * Gets the budgetPersonnelLineItemDeleted attribute. 
     * @return Returns the budgetPersonnelLineItemDeleted.
     */
    public boolean isBudgetPersonnelLineItemDeleted() {
        return budgetPersonnelLineItemDeleted;
    }

    /**
     * Sets the budgetPersonnelLineItemDeleted attribute value.
     * @param budgetPersonnelLineItemDeleted The budgetPersonnelLineItemDeleted to set.
     */
    public void setBudgetPersonnelLineItemDeleted(boolean budgetPersonnelLineItemDeleted) {
        this.budgetPersonnelLineItemDeleted = budgetPersonnelLineItemDeleted;
    }

    /**
     * Gets the budgetRateAndBaseList attribute. 
     * @return Returns the budgetRateAndBaseList.
     */
    public List<BudgetRateAndBase> getBudgetRateAndBaseList() {
        return budgetRateAndBaseList;
    }

    /**
     * Sets the budgetRateAndBaseList attribute value.
     * @param budgetRateAndBaseList The budgetRateAndBaseList to set.
     */
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

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
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
        return subAwardNumber != null;
    }

    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

    public ScaleTwoDecimal getCostSharingAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(costSharingAmount);
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public ScaleTwoDecimal getLineItemCost() {
        return ScaleTwoDecimal.returnZeroIfNull(lineItemCost);
    }

    public void setLineItemCost(ScaleTwoDecimal lineItemCost) {
        this.lineItemCost = lineItemCost;
    }

    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(underrecoveryAmount);
    }

    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    public ScaleTwoDecimal getTotalCostSharingAmount() {
        return ScaleTwoDecimal.returnZeroIfNull(totalCostSharingAmount);
    }

    public void setTotalCostSharingAmount(ScaleTwoDecimal totalCostSharingAmount) {
        this.totalCostSharingAmount = totalCostSharingAmount;
    }

    public void setSubmitCostSharingFlag(Boolean submitCostSharingFlag) {
        this.submitCostSharingFlag = submitCostSharingFlag;
    }

    public Boolean getSubmitCostSharingFlag() {
        if (ObjectUtils.isNull(budgetPeriodBO)) {
            this.refreshReferenceObject("budgetPeriodBO");
        }
        return (getBudgetPeriodBO() != null && getBudgetPeriodBO().getBudget().getSubmitCostSharingFlag()) ? submitCostSharingFlag : false;
    }

    public Boolean getFormulatedCostElementFlag() {
        return formulatedCostElementFlag == null ? Boolean.FALSE : formulatedCostElementFlag;
    }

    public void setFormulatedCostElementFlag(Boolean formulatedCostElementFlag) {
        this.formulatedCostElementFlag = formulatedCostElementFlag;
    }
}
