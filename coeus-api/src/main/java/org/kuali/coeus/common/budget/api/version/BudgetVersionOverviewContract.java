package org.kuali.coeus.common.budget.api.version;

import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.api.model.DocumentNumbered;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface BudgetVersionOverviewContract extends DocumentNumbered {

    Integer getBudgetVersionNumber();

    Long getBudgetId();

    ScaleTwoDecimal getCostSharingAmount();

    Date getEndDate();

    Date getStartDate();

    Boolean isFinalVersionFlag();

    String getOhRateTypeCode();

    ScaleTwoDecimal getResidualFunds();

    ScaleTwoDecimal getTotalCost();

    ScaleTwoDecimal getTotalDirectCost();

    ScaleTwoDecimal getTotalIndirectCost();

    ScaleTwoDecimal getTotalCostLimit();

    ScaleTwoDecimal getTotalDirectCostLimit();

    ScaleTwoDecimal getUnderrecoveryAmount();

    String getComments();

    Boolean getModularBudgetFlag();

    String getOnOffCampusFlag();

    Boolean getSubmitCostSharingFlag();

    RateClassContract getUrRateClass();

    RateClassContract getRateClass();
}
