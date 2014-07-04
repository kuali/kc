package org.kuali.coeus.common.budget.api.core;

import org.kuali.coeus.common.budget.api.distribution.BudgetCostShareContract;
import org.kuali.coeus.common.budget.api.distribution.BudgetUnrecoveredFandAContract;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonContract;
import org.kuali.coeus.common.budget.api.rate.BudgetLaRateContract;
import org.kuali.coeus.common.budget.api.rate.BudgetRateContract;
import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.sys.api.model.DocumentNumbered;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;
import java.util.List;

public interface BudgetContract extends IdentifiableBudget, DocumentNumbered {

    Integer getBudgetVersionNumber();

    ScaleTwoDecimal getCostSharingAmount();

    Date getEndDate();

    Date getStartDate();

    Boolean getFinalVersionFlag();

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

    String getBudgetJustification();

    String getBudgetAdjustmentDocumentNumber();

    List<? extends BudgetRateContract> getBudgetRates();
    
    List<? extends BudgetLaRateContract> getBudgetLaRates();
    
    List<? extends BudgetProjectIncomeContract> getBudgetProjectIncomes();
    
    List<? extends BudgetCostShareContract> getBudgetCostShares();
    
    List<? extends BudgetUnrecoveredFandAContract> getBudgetUnrecoveredFandAs();
    
    List<? extends BudgetPersonContract> getBudgetPersons();
    
    List<? extends BudgetSubAwardsContract> getBudgetSubAwards();

    List<? extends BudgetPeriodContract> getBudgetPeriods();
}
