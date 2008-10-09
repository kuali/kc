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

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

@Entity
@Table(name="BUDGET_MODULAR")
public class BudgetModular extends KraPersistableBusinessObjectBase {
    @Id
	@Column(name="BUDGET_PERIOD_NUMBER")
	private Long budgetPeriodId;
    
    @Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Column(name="VERSION_NUMBER")
	private Integer budgetVersionNumber;
    
    @Column(name="BUDGET_PERIOD")
	private Integer budgetPeriod;
    
    @Column(name="DIRECT_COST_LESS_CONSOR_FNA")
	private BudgetDecimal directCostLessConsortiumFna;
    
    @Column(name="CONSORTIUM_FNA")
	private BudgetDecimal consortiumFna;
    
    @Column(name="TOTAL_DIRECT_COST")
	private BudgetDecimal totalDirectCost;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetModularIdc.class, mappedBy="budgetModular")
	private List<BudgetModularIdc> budgetModularIdcs;
    
    // Derived properties
    @Transient
    private BudgetDecimal totalRequestedCost;
    
    @Transient
    private BudgetDecimal totalFnaRequested;
    
    public BudgetModular() {
        super();
        budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        directCostLessConsortiumFna = new BudgetDecimal(0);
        consortiumFna = new BudgetDecimal(0);
        totalDirectCost = new BudgetDecimal(0);
    }
    
    public BudgetModular(String proposalNumber, Integer budgetVersionNumber, Integer budgetPeriod) {
        this();
        this.setProposalNumber(proposalNumber);
        this.setBudgetVersionNumber(budgetVersionNumber);
        this.setBudgetPeriod(budgetPeriod);
    }
    
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public BudgetDecimal getConsortiumFna() {
        return consortiumFna;
    }

    public void setConsortiumFna(BudgetDecimal consortiumFna) {
        this.consortiumFna = consortiumFna;
    }

    public BudgetDecimal getDirectCostLessConsortiumFna() {
        return directCostLessConsortiumFna;
    }

    public void setDirectCostLessConsortiumFna(BudgetDecimal directCostLessConsortiumFna) {
        this.directCostLessConsortiumFna = directCostLessConsortiumFna;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }
    
    public BudgetDecimal getTotalFnaRequested() {
        return totalFnaRequested;
    }

    public void setTotalFnaRequested(BudgetDecimal totalFnaRequested) {
        this.totalFnaRequested = totalFnaRequested;
    }

    public BudgetDecimal getTotalRequestedCost() {
        return totalRequestedCost;
    }

    public void setTotalRequestedCost(BudgetDecimal totalRequestedCost) {
        this.totalRequestedCost = totalRequestedCost;
    }

    public List<BudgetModularIdc> getBudgetModularIdcs() {
        return budgetModularIdcs;
    }

    public void setBudgetModularIdcs(List<BudgetModularIdc> budgetModularIdcs) {
        this.budgetModularIdcs = budgetModularIdcs;
    }
    
    public BudgetModularIdc getBudgetModularIdc(int index) {
        while (getBudgetModularIdcs().size() <= index) {
            getBudgetModularIdcs().add(new BudgetModularIdc());
        }
        return (BudgetModularIdc) getBudgetModularIdcs().get(index);
    }
    
    public void calculateAllTotals() {
        this.calculateTotalDirectCost();
        this.calculateTotalFnaRequested();
        this.calculateTotalRequestedCost();
    }
    
    public void calculateTotalDirectCost() {
        BudgetDecimal totalDirectCost = new BudgetDecimal(0);
        if (this.getDirectCostLessConsortiumFna() != null) {
            totalDirectCost = totalDirectCost.add(this.getDirectCostLessConsortiumFna());
        } if (this.getConsortiumFna() != null) {
            totalDirectCost = totalDirectCost.add(this.getConsortiumFna());
        }
        this.setTotalDirectCost(totalDirectCost);
    }
    
    public void calculateTotalFnaRequested() {
        BudgetDecimal fnaRequested = new BudgetDecimal(0);
        for (BudgetModularIdc budgetModularIdc: this.getBudgetModularIdcs()) {
            budgetModularIdc.calculateFundsRequested();
            if (budgetModularIdc.getFundsRequested() != null) {
                fnaRequested = fnaRequested.add(budgetModularIdc.getFundsRequested());
            }
        }
        this.setTotalFnaRequested(fnaRequested);
    }
    
    public void calculateTotalRequestedCost() {
        BudgetDecimal requestedCost = new BudgetDecimal(0);
        if (this.getTotalDirectCost() != null) {
            requestedCost = requestedCost.add(this.getTotalDirectCost());
        }
        if (this.getTotalFnaRequested() != null) {
            requestedCost = requestedCost.add(this.getTotalFnaRequested());
        }
        this.setTotalRequestedCost(requestedCost);
    }
    
    public void addNewBudgetModularIdc(BudgetModularIdc budgetModularIdc) {
        int hack = 0;
        budgetModularIdc.setProposalNumber(this.getProposalNumber());
        budgetModularIdc.setBudgetVersionNumber(this.getBudgetVersionNumber());
        budgetModularIdc.setBudgetPeriod(this.getBudgetPeriod());
        
        /*if List <budgetModularIdc> contains the budgetModularIdc being passed with same rate and description, then add its idcBase to that budgetModularIdc.
         * otherwise add it to the list.
         */
        for (BudgetModularIdc testBudgetModularIdc: this.getBudgetModularIdcs()){
                 if(testBudgetModularIdc.getIdcRate().equals(budgetModularIdc.getIdcRate()) &&
                        testBudgetModularIdc.getDescription().equals(budgetModularIdc.getDescription())){
                    testBudgetModularIdc.setIdcBase(testBudgetModularIdc.getIdcBase().add(budgetModularIdc.getIdcBase()));
                    testBudgetModularIdc.setFundsRequested(testBudgetModularIdc.getFundsRequested().add(budgetModularIdc.getFundsRequested()));
                    hack = 1;
                    return;
                }
            }
         this.getBudgetModularIdcs().add(budgetModularIdc);
    
         
        }
    

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("proposalNumber", this.proposalNumber);
        map.put("budgetVersionNumber", this.budgetVersionNumber);
        map.put("budgetPeriod", this.budgetPeriod);
        return map;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

}

