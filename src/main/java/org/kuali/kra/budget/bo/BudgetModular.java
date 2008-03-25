/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetModular extends KraPersistableBusinessObjectBase {
    
    private String proposalNumber;
    private Integer budgetVersionNumber;
    private Integer budgetPeriod;
    private BudgetDecimal directCostLessConsortiumFna;
    private BudgetDecimal consortiumFna;
    private BudgetDecimal totalDirectCost;
    
    private List<BudgetModularIdc> budgetModularIdcs;
    
    public BudgetModular() {
        super();
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
    
    public List<BudgetModularIdc> getBudgetModularIdcs() {
        return budgetModularIdcs;
    }

    public void setBudgetModularIdcs(List<BudgetModularIdc> budgetModularIdcs) {
        this.budgetModularIdcs = budgetModularIdcs;
    }
    
    public void calculateTotalDirectCost() {
        BudgetDecimal totalDirectCost = new BudgetDecimal(0);
        if (this.getDirectCostLessConsortiumFna() != null) {
            totalDirectCost.add(this.getDirectCostLessConsortiumFna());
        } if (this.getConsortiumFna() != null) {
            totalDirectCost.add(this.getConsortiumFna());
        }
        this.setTotalDirectCost(totalDirectCost);
    }
    
    public void addNewBudgetModularIdc(BudgetModularIdc budgetModularIdc) {
        budgetModularIdc.setProposalNumber(this.getProposalNumber());
        budgetModularIdc.setBudgetVersionNumber(this.getBudgetVersionNumber());
        budgetModularIdc.setBudgetPeriod(this.getBudgetPeriod());
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

}
