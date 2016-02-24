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

import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BudgetModularSummary implements Serializable {
    
    private ScaleTwoDecimal directCostLessConsortiumFna;
    private ScaleTwoDecimal consortiumFna;
    private ScaleTwoDecimal totalDirectCost;
    private ScaleTwoDecimal totalFnaRequested;
    private ScaleTwoDecimal totalRequestedCost;
    
    private List<BudgetModularIdc> budgetModularIdcs;
    
    public BudgetModularSummary() {
        super();
        budgetModularIdcs = new ArrayList<BudgetModularIdc>();
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
    
}
