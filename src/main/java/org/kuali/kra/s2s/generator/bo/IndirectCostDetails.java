/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.budget.BudgetDecimal;

public class IndirectCostDetails {


    private String costType;
    private BudgetDecimal rate;
    private BudgetDecimal base;
    private BudgetDecimal funds;
    private BudgetDecimal baseCostSharing;
    private BudgetDecimal costSharing;

    public IndirectCostDetails() {
    }

    /**
     * Getter for property costType.
     * 
     * @return Value of property costType.
     */
    public String getCostType() {
        return costType;
    }

    /**
     * Setter for property costType.
     * 
     * @param costType New value of property costType.
     */
    public void setCostType(String costType) {
        this.costType = costType;
    }

    /**
     * Getter for property rate.
     * 
     * @return Value of property rate.
     */
    public BudgetDecimal getRate() {
        return rate;
    }

    /**
     * Setter for property rate.
     * 
     * @param rate New value of property rate.
     */
    public void setRate(BudgetDecimal rate) {
        this.rate = rate;
    }

    /**
     * Getter for property base.
     * 
     * @return Value of property base.
     */
    public BudgetDecimal getBase() {
        return base;
    }

    /**
     * Setter for property base.
     * 
     * @param base New value of property base.
     */
    public void setBase(BudgetDecimal base) {
        this.base = base;
    }

    /**
     * Getter for property funds.
     * 
     * @return Value of property funds.
     */
    public BudgetDecimal getFunds() {
        return funds;
    }

    /**
     * Setter for property funds.
     * 
     * @param funds New value of property funds.
     */
    public void setFunds(BudgetDecimal funds) {
        this.funds = funds;
    }

    // start add costSaring for fedNonFedBudget repport

    public BudgetDecimal getBaseCostSharing() {
        return baseCostSharing==null?BudgetDecimal.ZERO:baseCostSharing;
    }

    public void setBaseCostSharing(BudgetDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
    }

    public BudgetDecimal getCostSharing() {
        return costSharing==null?BudgetDecimal.ZERO:costSharing;
    }

    public void setCostSharing(BudgetDecimal costSharing) {
        this.costSharing = costSharing;
    }


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("costType", getCostType());
        hashMap.put("rate", getRate());
        hashMap.put("base", getBase());
        hashMap.put("funds", getFunds());
        hashMap.put("baseCostSharing", getBaseCostSharing());
        hashMap.put("costSharing", getCostSharing());

        return hashMap;
    }
}
