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
package org.kuali.coeus.propdev.impl.budget.modular;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kuali.coeus.propdev.api.budget.modular.BudgetModularContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@Entity
@Table(name = "BUDGET_MODULAR")
public class BudgetModular extends KcPersistableBusinessObjectBase implements BudgetModularContract {

    @Id
    @Column(name = "BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Column(name = "DIRECT_COST_LESS_CONSOR_FNA")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal directCostLessConsortiumFna;

    @Column(name = "CONSORTIUM_FNA")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal consortiumFna;

    @Column(name = "TOTAL_DIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalDirectCost;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_PERIOD_NUMBER", referencedColumnName = "BUDGET_PERIOD_NUMBER")
    private List<BudgetModularIdc> budgetModularIdcs;

    // Derived properties
    @Transient
    private ScaleTwoDecimal totalRequestedCost;

    @Transient
    private ScaleTwoDecimal totalFnaRequested;

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

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    @Override
    public ScaleTwoDecimal getConsortiumFna() {
        return consortiumFna;
    }

    public void setConsortiumFna(ScaleTwoDecimal consortiumFna) {
        this.consortiumFna = consortiumFna;
    }

    @Override
    public ScaleTwoDecimal getDirectCostLessConsortiumFna() {
        return directCostLessConsortiumFna;
    }

    public void setDirectCostLessConsortiumFna(ScaleTwoDecimal directCostLessConsortiumFna) {
        this.directCostLessConsortiumFna = directCostLessConsortiumFna;
    }

    @Override
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

    @Override
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

    @Override
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}
