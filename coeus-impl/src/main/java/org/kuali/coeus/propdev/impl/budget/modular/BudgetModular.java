/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.budget.modular;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@Entity
@Table(name = "BUDGET_MODULAR")
public class BudgetModular extends KcPersistableBusinessObjectBase implements BudgetModularContract {

    @Column(name = "BUDGET_PERIOD_NUMBER", insertable = false, updatable = false)
    private Long budgetPeriodId;

    @Id
    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_PERIOD_NUMBER", referencedColumnName = "BUDGET_PERIOD_NUMBER", insertable = true, updatable = true)
    private BudgetPeriod budgetPeriodObj;

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

    @OneToMany(mappedBy="budgetModular", orphanRemoval = true, cascade = { CascadeType.ALL })
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

    public BudgetModular(BudgetPeriod budgetPeriod) {
        this();
        this.setBudgetPeriodObj(budgetPeriod);
        this.setBudgetId(budgetPeriod.getBudgetId());
        this.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
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

    public BudgetPeriod getBudgetPeriodObj() {
        return budgetPeriodObj;
    }

    public void setBudgetPeriodObj(BudgetPeriod budgetPeriodObj) {
        if (budgetPeriodObj != null) {
            setBudgetPeriodId(budgetPeriodObj.getBudgetPeriodId());
        } else {
            setBudgetPeriodId(null);
        }

        this.budgetPeriodObj = budgetPeriodObj;
    }
}
