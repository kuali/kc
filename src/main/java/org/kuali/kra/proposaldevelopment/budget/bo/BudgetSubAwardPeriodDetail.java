/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.budget.bo;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.DeepCopyIgnore;

public class BudgetSubAwardPeriodDetail extends BudgetAssociate {

    private static final long serialVersionUID = 2327612798304765405L;
    
    @DeepCopyIgnore
    private Long budgetSubAwardDetailId;
    
    private Integer subAwardNumber;
    
    private Long budgetId;

    private Integer budgetPeriod;
    
    private BudgetDecimal directCost = BudgetDecimal.ZERO;
    
    private BudgetDecimal indirectCost = BudgetDecimal.ZERO;
    
    private BudgetDecimal costShare = BudgetDecimal.ZERO;
    
    private BudgetDecimal totalCost = BudgetDecimal.ZERO;
    
    private transient boolean amountsModified = false;

    public BudgetSubAwardPeriodDetail() {
        
    }
    
    public BudgetSubAwardPeriodDetail(BudgetSubAwards subAward, BudgetPeriod period) {
        this.subAwardNumber = subAward.getSubAwardNumber();
        this.budgetId = period.getBudgetId();
        this.budgetPeriod = period.getBudgetPeriod();
    }
    
    public Long getBudgetSubAwardDetailId() {
        return budgetSubAwardDetailId;
    }

    public void setBudgetSubAwardDetailId(Long budgetSubAwardDetailId) {
        this.budgetSubAwardDetailId = budgetSubAwardDetailId;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public BudgetDecimal getDirectCost() {
        return directCost;
    }

    public void setDirectCost(BudgetDecimal directCost) {
        if (!ObjectUtils.equals(this.directCost, directCost)) {
            amountsModified = true;
        }
        this.directCost = directCost;
    }

    public BudgetDecimal getIndirectCost() {
        return indirectCost;
    }

    public void setIndirectCost(BudgetDecimal indirectCost) {
        if (!ObjectUtils.equals(this.indirectCost, indirectCost)) {
            amountsModified = true;
        }        
        this.indirectCost = indirectCost;
    }

    public BudgetDecimal getCostShare() {
        return costShare;
    }

    public void setCostShare(BudgetDecimal costShare) {
        if (!ObjectUtils.equals(this.costShare, costShare)) {
            amountsModified = true;
        }        
        this.costShare = costShare;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public BudgetDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }
    
    public void computeTotal() {
        BudgetDecimal total = getDirectCost() == null ? BudgetDecimal.ZERO : getDirectCost();
        total = total.add(getIndirectCost() == null ? BudgetDecimal.ZERO : getIndirectCost());
        total = total.add(getCostShare() == null ? BudgetDecimal.ZERO : getCostShare());
        setTotalCost(total);
    }

    public boolean isAmountsModified() {
        return amountsModified;
    }

    public void setAmountsModified(boolean amountsModified) {
        this.amountsModified = amountsModified;
    }
}
