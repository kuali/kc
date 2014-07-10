/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;


public class IndirectCostDetailsDto {


    private String costType;
    private ScaleTwoDecimal rate;
    private ScaleTwoDecimal base;
    private ScaleTwoDecimal funds;
    private ScaleTwoDecimal baseCostSharing;
    private ScaleTwoDecimal costSharing;

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
    public ScaleTwoDecimal getRate() {
        return rate;
    }

    /**
     * Setter for property rate.
     * 
     * @param rate New value of property rate.
     */
    public void setRate(ScaleTwoDecimal rate) {
        this.rate = rate;
    }

    /**
     * Getter for property base.
     * 
     * @return Value of property base.
     */
    public ScaleTwoDecimal getBase() {
        return base;
    }

    /**
     * Setter for property base.
     * 
     * @param base New value of property base.
     */
    public void setBase(ScaleTwoDecimal base) {
        this.base = base;
    }

    /**
     * Getter for property funds.
     * 
     * @return Value of property funds.
     */
    public ScaleTwoDecimal getFunds() {
        return funds;
    }

    /**
     * Setter for property funds.
     * 
     * @param funds New value of property funds.
     */
    public void setFunds(ScaleTwoDecimal funds) {
        this.funds = funds;
    }

    // start add costSaring for fedNonFedBudget repport

    public ScaleTwoDecimal getBaseCostSharing() {
        return baseCostSharing==null? ScaleTwoDecimal.ZERO:baseCostSharing;
    }

    public void setBaseCostSharing(ScaleTwoDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
    }

    public ScaleTwoDecimal getCostSharing() {
        return costSharing==null? ScaleTwoDecimal.ZERO:costSharing;
    }

    public void setCostSharing(ScaleTwoDecimal costSharing) {
        this.costSharing = costSharing;
    }
}
