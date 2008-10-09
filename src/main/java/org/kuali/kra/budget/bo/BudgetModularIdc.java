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

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

@IdClass(org.kuali.kra.budget.bo.id.BudgetModularIdcId.class)
@Entity
@Table(name="BUDGET_MODULAR_IDC")
public class BudgetModularIdc extends KraPersistableBusinessObjectBase {
    
    @Id
	@Column(name="BUDGET_PERIOD_NUMBER")
	private Long budgetPeriodId;
    
    @Id
    @Column(name="RATE_NUMBER")
    private Integer rateNumber;
    
    @Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Column(name="VERSION_NUMBER")
	private Integer budgetVersionNumber;
    
    @Column(name="BUDGET_PERIOD")
	private Integer budgetPeriod;
    
    @Column(name="DESCRIPTION")
	private String description;
    
    @Column(name="IDC_RATE")
	private BudgetDecimal idcRate;
    
    @Column(name="IDC_BASE")
	private BudgetDecimal idcBase;
    
    @Column(name="FUNDS_REQUESTED")
	private BudgetDecimal fundsRequested;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="DESCRIPTION", insertable=false, updatable=false)
	private RateClass rateClass;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false)
    private BudgetModular budgetModular;
    
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BudgetDecimal getFundsRequested() {
        return fundsRequested;
    }

    public void setFundsRequested(BudgetDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    public BudgetDecimal getIdcBase() {
        return idcBase;
    }

    public void setIdcBase(BudgetDecimal idcBase) {
        this.idcBase = idcBase;
    }

    public BudgetDecimal getIdcRate() {
        return idcRate;
    }

    public void setIdcRate(BudgetDecimal idcRate) {
        this.idcRate = idcRate;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }
    
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public void calculateFundsRequested() {
        BudgetDecimal fundsRequested = new BudgetDecimal(0);
        if (this.getIdcBase() != null && this.getIdcRate() != null) {
            fundsRequested = this.getIdcBase().percentage(this.getIdcRate());
        }
        this.setFundsRequested(fundsRequested);
    }

    public BudgetModular getBudgetModular() {
        return budgetModular;
    }

    public void setBudgetModular(BudgetModular budgetModular) {
        this.budgetModular = budgetModular;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("proposalNumber", this.proposalNumber);
        map.put("budgetVersionNumber", this.budgetVersionNumber);
        map.put("budgetPeriod", this.budgetPeriod);
        map.put("rateNumber", this.rateNumber);
        return map;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

}

