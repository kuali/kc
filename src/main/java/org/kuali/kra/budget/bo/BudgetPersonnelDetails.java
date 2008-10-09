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

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

@IdClass(org.kuali.kra.budget.bo.id.BudgetPersonnelDetailsId.class)
@Entity
@Table(name="BUDGET_PERSONNEL_DETAILS")
public class BudgetPersonnelDetails extends BudgetLineItemBase {
    
    @Id
	@Column(name="PERSON_NUMBER")
	private Integer personNumber;
    
	@Column(name="JOB_CODE")
	private String jobCode;
	
	@Column(name="PERIOD_TYPE")
	private String periodTypeCode;
	
	@Column(name="PERSON_ID")
	private String personId;
	
	@Column(name="SEQUENCE_NUMBER")
	private Integer sequenceNumber;
	
	@Column(name="PERSON_SEQUENCE_NUMBER")
	private Integer personSequenceNumber;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable=false, updatable=false), @JoinColumn(name="VERSION_NUMBER", insertable=false, updatable=false), @JoinColumn(name="PERSON_SEQUENCE_NUMBER", insertable=false, updatable=false)})
	private BudgetPerson budgetPerson;
	
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount.class, mappedBy="budgetPersonnelDetails")
	private List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetPersonnelRateAndBase.class, mappedBy="budgetPersonnelDetails")
	private List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name="PERIOD_TYPE", insertable=false, updatable=false)
    private BudgetPeriodType budgetPeriodType;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="LINE_ITEM_NUMBER", insertable = false, updatable = false)})
    private BudgetLineItem budgetLineItem;
    
    @Transient
    private String effdtAfterStartdtMsg;
    
    @Transient
    private Boolean nonEmployeeFlag;
    
    @Transient
    private BudgetDecimal costSharingPercent=BudgetDecimal.ZERO;
    
    @Transient
    private BudgetDecimal salaryRequested=BudgetDecimal.ZERO;
    
    @Transient
    private BudgetDecimal percentCharged=BudgetDecimal.ZERO;
    
    @Transient
    private BudgetDecimal percentEffort=BudgetDecimal.ZERO;

    public BudgetPersonnelDetails(){
        budgetPersonnelCalculatedAmounts = new ArrayList<BudgetPersonnelCalculatedAmount>();
        budgetPersonnelRateAndBaseList = new ArrayList<BudgetPersonnelRateAndBase>();
    }
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
		return BudgetDecimal.returnZeroIfNull(percentCharged);
	}

	public void setPercentCharged(BudgetDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}

	public BudgetDecimal getPercentEffort() {
		return BudgetDecimal.returnZeroIfNull(percentEffort);
	}

	public void setPercentEffort(BudgetDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
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
	
	public BudgetLineItem getBudgetLineItem() {
        return budgetLineItem;
    }
    public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
        this.budgetLineItem = budgetLineItem;
    }
    
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("personNumber", getPersonNumber());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("percentCharged", getPercentCharged());
		hashMap.put("percentEffort", getPercentEffort());
		hashMap.put("periodType", getPeriodTypeCode());
		hashMap.put("personId", getPersonId());
		hashMap.put("salaryRequested", getSalaryRequested());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    /**
     * Gets the nonEmployeeFlag attribute. 
     * @return Returns the nonEmployeeFlag.
     */
    public Boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    /**
     * Sets the nonEmployeeFlag attribute value.
     * @param nonEmployeeFlag The nonEmployeeFlag to set.
     */
    public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    /**
     * Gets the personSequenceNumber attribute. 
     * @return Returns the personSequenceNumber.
     */
    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    /**
     * Sets the personSequenceNumber attribute value.
     * @param personSequenceNumber The personSequenceNumber to set.
     */
    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    /**
     * Gets the budgetPerson attribute. 
     * @return Returns the budgetPerson.
     */
    public BudgetPerson getBudgetPerson() {
        return budgetPerson;
    }

    /**
     * Sets the budgetPerson attribute value.
     * @param budgetPerson The budgetPerson to set.
     */
    public void setBudgetPerson(BudgetPerson budgetPerson) {
        this.budgetPerson = budgetPerson;
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
    @Override
    public List getBudgetCalculatedAmounts() {
        return getBudgetPersonnelCalculatedAmounts();
    }
    /**
     * Gets the budgetPersonnelRateAndBaseList attribute. 
     * @return Returns the budgetPersonnelRateAndBaseList.
     */
    public List<BudgetPersonnelRateAndBase> getBudgetPersonnelRateAndBaseList() {
        return budgetPersonnelRateAndBaseList;
    }
    /**
     * Sets the budgetPersonnelRateAndBaseList attribute value.
     * @param budgetPersonnelRateAndBaseList The budgetPersonnelRateAndBaseList to set.
     */
    public void setBudgetPersonnelRateAndBaseList(List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList) {
        this.budgetPersonnelRateAndBaseList = budgetPersonnelRateAndBaseList;
    }
    
    public String getEffdtAfterStartdtMsg() {
        if (budgetPerson == null) {
            this.refreshReferenceObject("budgetPerson");
        }
        if (budgetPerson.getEffectiveDate().after(getStartDate())) {
            return "Earning Period Start Date is before "+budgetPerson.getPersonName() +"'s Salary Effective Date. Salary is calculated based on Effective Date."; 
        }
        return "";
    }
    public void setEffdtAfterStartdtMsg(String effdtAfterStartdtMsg) {
        this.effdtAfterStartdtMsg = effdtAfterStartdtMsg;
    }
    public BudgetPeriodType getBudgetPeriodType() {
        return budgetPeriodType;
    }
    public void setBudgetPeriodType(BudgetPeriodType budgetPeriodType) {
        this.budgetPeriodType = budgetPeriodType;
    }
}

