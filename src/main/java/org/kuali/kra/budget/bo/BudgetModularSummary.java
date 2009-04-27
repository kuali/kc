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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;

public class BudgetModularSummary implements Serializable {
    
    private BudgetDecimal directCostLessConsortiumFna;
    private BudgetDecimal consortiumFna;
    private BudgetDecimal totalDirectCost;
    private BudgetDecimal totalFnaRequested;
    private BudgetDecimal totalRequestedCost;
    
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
    
}
