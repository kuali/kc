package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPersonnelDetails extends BudgetLineItemBase {
    private Integer personNumber;
	private BudgetDecimal costSharingPercent;
	private String jobCode;
	private BudgetDecimal percentCharged;
	private BudgetDecimal percentEffort;
	private String periodType;
	private String personId;
	private BudgetDecimal salaryRequested;
	private Integer sequenceNumber;
	private List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts;

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public BudgetDecimal getCostSharingPercent() {
		return costSharingPercent;
	}

	public void setCostSharingPercent(BudgetDecimal costSharingPercent) {
		this.costSharingPercent = costSharingPercent;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public BudgetDecimal getPercentCharged() {
		return percentCharged;
	}

	public void setPercentCharged(BudgetDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}

	public BudgetDecimal getPercentEffort() {
		return percentEffort;
	}

	public void setPercentEffort(BudgetDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public BudgetDecimal getSalaryRequested() {
		return salaryRequested;
	}

	public void setSalaryRequested(BudgetDecimal salaryRequested) {
		this.salaryRequested = salaryRequested;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("personNumber", getPersonNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applyInRateFlag", getApplyInRateFlag());
		hashMap.put("budgetJustification", getBudgetJustification());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("costSharingPercent", getCostSharingPercent());
		hashMap.put("endDate", getEndDate());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("lineItemDescription", getLineItemDescription());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("percentCharged", getPercentCharged());
		hashMap.put("percentEffort", getPercentEffort());
		hashMap.put("periodType", getPeriodType());
		hashMap.put("personId", getPersonId());
		hashMap.put("salaryRequested", getSalaryRequested());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    /**
     * Gets the budgetPersonnelCalculatedAmounts attribute. 
     * @return Returns the budgetPersonnelCalculatedAmounts.
     */
    public List<BudgetPersonnelCalculatedAmount> getBudgetPersonnelCalculatedAmounts() {
        return budgetPersonnelCalculatedAmounts;
    }

    /**
     * Sets the budgetPersonnelCalculatedAmounts attribute value.
     * @param budgetPersonnelCalculatedAmounts The budgetPersonnelCalculatedAmounts to set.
     */
    public void setBudgetPersonnelCalculatedAmounts(List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts) {
        this.budgetPersonnelCalculatedAmounts = budgetPersonnelCalculatedAmounts;
    }
}
