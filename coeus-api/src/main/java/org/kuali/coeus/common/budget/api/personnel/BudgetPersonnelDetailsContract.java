package org.kuali.coeus.common.budget.api.personnel;

import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;
import java.util.List;

public interface BudgetPersonnelDetailsContract extends IdentifiableBudget {

    Long getBudgetPersonnelLineItemId();

    Long getBudgetLineItemId();
    
    Integer getLineItemNumber();
    
    Integer getBudgetPeriod();
    
    Boolean getOnOffCampusFlag();
    
    Date getEndDate();
    
    Date getStartDate();
    
    String getBudgetJustification();
    
    ScaleTwoDecimal getCostSharingAmount();
    
    String getLineItemDescription();
    
    Boolean getApplyInRateFlag();
    
    Integer getPersonNumber();
    
    ScaleTwoDecimal getCostSharingPercent();
    
    String getJobCode();
    
    ScaleTwoDecimal getPercentCharged();
    
    ScaleTwoDecimal getPercentEffort();
    
    String getPeriodTypeCode();
    
    String getPersonId();
    
    ScaleTwoDecimal getSalaryRequested();
    
    Integer getSequenceNumber();
    
    Integer getPersonSequenceNumber();
    
    ScaleTwoDecimal getUnderrecoveryAmount();
    
    Boolean getSubmitCostSharingFlag();

    BudgetPersonContract getBudgetPerson();

    List<? extends BudgetPersonnelCalculatedAmountContract> getBudgetPersonnelCalculatedAmounts();
}
