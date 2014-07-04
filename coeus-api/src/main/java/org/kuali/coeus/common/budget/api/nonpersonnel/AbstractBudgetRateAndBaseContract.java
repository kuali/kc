package org.kuali.coeus.common.budget.api.nonpersonnel;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface AbstractBudgetRateAndBaseContract extends IdentifiableBudget {
    
    Integer getBudgetPeriod();

    Integer getLineItemNumber();

    String getRateClassCode();

    Integer getRateNumber();

    String getRateTypeCode();

    ScaleTwoDecimal getAppliedRate();

    ScaleTwoDecimal getBaseCostSharing();

    ScaleTwoDecimal getCalculatedCost();

    ScaleTwoDecimal getCalculatedCostSharing();

    Date getEndDate();

    Boolean getOnOffCampusFlag();

    Date getStartDate();

    RateClassContract getRateClass();
}
