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

import java.sql.Date;
import java.util.List;

import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import javax.persistence.Transient;

public abstract class BudgetLineItemBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 8356817148151906918L;

    @Transient
    private ScaleTwoDecimal directCost;

    @Transient
    private ScaleTwoDecimal indirectCost;

    @Transient
    private transient BudgetService budgetService;

    public abstract String getGroupName();

    public String getCostElementName() {
        if (getCostElementBO() != null) {
            return getCostElementBO().getDescription();
        } else {
            return "";
        }
    }

    public abstract void setGroupName(String groupName);

    public abstract List getBudgetCalculatedAmounts();

    public ScaleTwoDecimal getDirectCost() {
        return ScaleTwoDecimal.returnZeroIfNull(directCost);
    }

    public void setDirectCost(ScaleTwoDecimal directCost) {
        this.directCost = directCost;
    }

    public ScaleTwoDecimal getIndirectCost() {
        return ScaleTwoDecimal.returnZeroIfNull(indirectCost);
    }

    public void setIndirectCost(ScaleTwoDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }

    public abstract Integer getBudgetPeriod();

    public abstract void setBudgetPeriod(Integer budgetPeriod);

    public abstract Integer getLineItemNumber();

    public abstract void setLineItemNumber(Integer lineItemNumber);

    public abstract Boolean getApplyInRateFlag();

    public abstract void setApplyInRateFlag(Boolean applyInRateFlag);

    public abstract Integer getBasedOnLineItem();

    public abstract void setBasedOnLineItem(Integer basedOnLineItem);

    public abstract String getBudgetCategoryCode();

    public abstract void setBudgetCategoryCode(String budgetCategoryCode) ;

    public abstract String getBudgetJustification();

    public abstract void setBudgetJustification(String budgetJustification);

    public abstract String getCostElement();

    public abstract void setCostElement(String costElement);

    public abstract ScaleTwoDecimal getCostSharingAmount();

    public abstract void setCostSharingAmount(ScaleTwoDecimal costSharingAmount);

    public abstract Date getEndDate();

    public abstract void setEndDate(Date endDate);

    public abstract ScaleTwoDecimal getLineItemCost();

    public abstract void setLineItemCost(ScaleTwoDecimal lineItemCost);

    public abstract String getLineItemDescription();

    public abstract void setLineItemDescription(String lineItemDescription);

    public abstract Integer getLineItemSequence();

    public abstract void setLineItemSequence(Integer lineItemSequence);

    public abstract Boolean getOnOffCampusFlag();

    public abstract void setOnOffCampusFlag(Boolean onOffCampusFlag);

    public abstract Integer getQuantity();

    public abstract void setQuantity(Integer quantity);

    public abstract Date getStartDate();

    public abstract void setStartDate(Date startDate);

    public abstract ScaleTwoDecimal getUnderrecoveryAmount();

    public abstract void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount);

    public abstract BudgetCategory getBudgetCategory();

    public abstract void setBudgetCategory(BudgetCategory budgetCategory);

    public abstract CostElement getCostElementBO();

    public abstract void setCostElementBO(CostElement costElementBO);

    public abstract ScaleTwoDecimal getTotalCostSharingAmount();

    public abstract void setTotalCostSharingAmount(ScaleTwoDecimal totalCostSharingAmount);

    public boolean isValidToApplyInRate() {
        return getBudgetService().ValidInflationCeRate(this);
    }

    public abstract Long getBudgetLineItemId();

    public abstract void setBudgetLineItemId(Long budgetLineItemId);

    public abstract void setSubmitCostSharingFlag(Boolean submitCostSharingFlag);

    public abstract Boolean getSubmitCostSharingFlag();
    /**
     * Gets the budgetPeriodBO attribute. 
     * @return Returns the budgetPeriodBO.
     */
    public abstract BudgetPeriod getBudgetPeriodBO();

    public abstract void setBudgetPeriodBO(BudgetPeriod budgetPeriodBO);

    public abstract Boolean getFormulatedCostElementFlag();

    public abstract void setFormulatedCostElementFlag(Boolean formulatedCostElementFlag);

    public abstract List<BudgetFormulatedCostDetail> getBudgetFormulatedCosts();

    public abstract void setBudgetFormulatedCosts(List<BudgetFormulatedCostDetail> budgetFormulatedCosts);

    public abstract Long getBudgetId();

    public abstract void setBudgetId(Long budgetId);

    public abstract Long getBudgetPeriodId();

    public abstract void setBudgetPeriodId(Long budgetPeriodId);

    public BudgetService getBudgetService() {
        if (budgetService == null) {
            budgetService = KcServiceLocator.getService(BudgetService.class);
        }
        return budgetService;
    }
}
