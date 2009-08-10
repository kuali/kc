/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.budget.modular;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;

public class BudgetModular extends BudgetAssociate {
    private Long budgetPeriodId;
    private Integer budgetPeriod;
    private BudgetDecimal directCostLessConsortiumFna;
    private BudgetDecimal consortiumFna;
    private BudgetDecimal totalDirectCost;
    
    // Derived properties
    private BudgetDecimal totalRequestedCost;
    private BudgetDecimal totalFnaRequested;
    
    private List<BudgetModularIdc> budgetModularIdcs;
    
    public BudgetModular() {
        super();
        budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        directCostLessConsortiumFna = new BudgetDecimal(0);
        consortiumFna = new BudgetDecimal(0);
        totalDirectCost = new BudgetDecimal(0);
    }
    
    public BudgetModular(Long budgetId, Integer budgetPeriod) {
        this();
        this.setBudgetId(budgetId);
//        this.setProposalNumber(proposalNumber);
//        this.setBudgetVersionNumber(budgetVersionNumber);
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

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
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
//        budgetModularIdc.setProposalNumber(this.getProposalNumber());
//        budgetModularIdc.setBudgetVersionNumber(this.getBudgetVersionNumber());
        budgetModularIdc.setBudgetId(budgetModularIdc.getBudgetId());
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
    

    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("budgetPeriod", this.budgetPeriod);
        return hashMap;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

}
