/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;

import java.util.ArrayList;
import java.util.List;

public class BudgetModular extends BudgetAssociate {

    private Long budgetPeriodId;

    private Integer budgetPeriod;

    private ScaleTwoDecimal directCostLessConsortiumFna;

    private ScaleTwoDecimal consortiumFna;

    private ScaleTwoDecimal totalDirectCost;

    // Derived properties 
    private ScaleTwoDecimal totalRequestedCost;

    private ScaleTwoDecimal totalFnaRequested;

    private List<BudgetModularIdc> budgetModularIdcs;

    public BudgetModular() {
        super();
        budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        directCostLessConsortiumFna = new ScaleTwoDecimal(0);
        consortiumFna = new ScaleTwoDecimal(0);
        totalDirectCost = new ScaleTwoDecimal(0);
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

    public ScaleTwoDecimal getConsortiumFna() {
        return consortiumFna;
    }

    public void setConsortiumFna(ScaleTwoDecimal consortiumFna) {
        this.consortiumFna = consortiumFna;
    }

    public ScaleTwoDecimal getDirectCostLessConsortiumFna() {
        return directCostLessConsortiumFna;
    }

    public void setDirectCostLessConsortiumFna(ScaleTwoDecimal directCostLessConsortiumFna) {
        this.directCostLessConsortiumFna = directCostLessConsortiumFna;
    }

    public ScaleTwoDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(ScaleTwoDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public ScaleTwoDecimal getTotalFnaRequested() {
        return totalFnaRequested;
    }

    public void setTotalFnaRequested(ScaleTwoDecimal totalFnaRequested) {
        this.totalFnaRequested = totalFnaRequested;
    }

    public ScaleTwoDecimal getTotalRequestedCost() {
        return totalRequestedCost;
    }

    public void setTotalRequestedCost(ScaleTwoDecimal totalRequestedCost) {
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
        ScaleTwoDecimal totalDirectCost = new ScaleTwoDecimal(0);
        if (this.getDirectCostLessConsortiumFna() != null) {
            totalDirectCost = totalDirectCost.add(this.getDirectCostLessConsortiumFna());
        }
        if (this.getConsortiumFna() != null) {
            totalDirectCost = totalDirectCost.add(this.getConsortiumFna());
        }
        this.setTotalDirectCost(totalDirectCost);
    }

    public void calculateTotalFnaRequested() {
        ScaleTwoDecimal fnaRequested = new ScaleTwoDecimal(0);
        for (BudgetModularIdc budgetModularIdc : this.getBudgetModularIdcs()) {
            budgetModularIdc.calculateFundsRequested();
            if (budgetModularIdc.getFundsRequested() != null) {
                fnaRequested = fnaRequested.add(budgetModularIdc.getFundsRequested());
            }
        }
        this.setTotalFnaRequested(fnaRequested);
    }

    public void calculateTotalRequestedCost() {
        ScaleTwoDecimal requestedCost = new ScaleTwoDecimal(0);
        if (this.getTotalDirectCost() != null) {
            requestedCost = requestedCost.add(this.getTotalDirectCost());
        }
        if (this.getTotalFnaRequested() != null) {
            requestedCost = requestedCost.add(this.getTotalFnaRequested());
        }
        this.setTotalRequestedCost(requestedCost);
    }

    public void addNewBudgetModularIdc(BudgetModularIdc budgetModularIdc) {
        budgetModularIdc.setBudgetId(this.getBudgetId());
        budgetModularIdc.setBudgetPeriod(this.getBudgetPeriod());
        /*if List <budgetModularIdc> contains the budgetModularIdc being passed with same rate and description, then add its idcBase to that budgetModularIdc.
         * otherwise add it to the list.
         */
        for (BudgetModularIdc testBudgetModularIdc : this.getBudgetModularIdcs()) {
            if (testBudgetModularIdc.getIdcRate().equals(budgetModularIdc.getIdcRate()) && testBudgetModularIdc.getDescription().equals(budgetModularIdc.getDescription())) {
                if (testBudgetModularIdc.getIdcBase() == null) {
                    testBudgetModularIdc.setIdcBase(budgetModularIdc.getIdcBase());
                } else {
                    testBudgetModularIdc.setIdcBase(testBudgetModularIdc.getIdcBase().add(budgetModularIdc.getIdcBase()));
                }
                if (testBudgetModularIdc.getFundsRequested() == null) {
                    testBudgetModularIdc.setFundsRequested(budgetModularIdc.getFundsRequested());
                } else {
                    testBudgetModularIdc.setFundsRequested(testBudgetModularIdc.getFundsRequested().add(budgetModularIdc.getFundsRequested()));
                }
                return;
            }
        }
        this.getBudgetModularIdcs().add(budgetModularIdc);
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}
