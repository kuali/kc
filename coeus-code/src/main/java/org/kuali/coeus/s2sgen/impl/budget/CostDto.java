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


public class CostDto {

    private int budgetPeriod;
    private ScaleTwoDecimal cost;
    private String category;
    private String categoryType;
    private String description;
    private int quantity;
    private ScaleTwoDecimal costSharing;

    /**
     * Getter for property budgetPeriod
     * 
     * @return Value of property budgetPEriod.
     */
    public int getBudgetPeriod() {
        return budgetPeriod;
    }

    /**
     * Setter for property budgetPeriod
     * 
     * @param budgetPeriod New value of property budgetPeriod.
     */
    public void setBudgetPeriod(int budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }


    /**
     * Getter for property cost
     * 
     * @return Value of property cost.
     */
    public ScaleTwoDecimal getCost() {
        return cost;
    }

    /**
     * Setter for property cost
     * 
     * @param cost New value of property cost.
     */
    public void setCost(ScaleTwoDecimal cost) {
        this.cost = cost;
    }

    /**
     * Getter for property category
     * 
     * @return Value of property category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for property category
     * 
     * @param category New value of property category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for property categoryType
     * 
     * @return Value of property categoryType.
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * Setter for property categoryType
     * 
     * @param categoryType New value of property categoryType.
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * Getter for property description
     * 
     * @return Value of property description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property description
     * 
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property quantity
     * 
     * @return Value of property quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for property quantity
     * 
     * @param quantity New value of property quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // start add costSaring for fedNonFedBudget repport
    public ScaleTwoDecimal getCostSharing() {
        return costSharing==null? ScaleTwoDecimal.ZERO:costSharing;
    }

    public void setCostSharing(ScaleTwoDecimal costSharing) {
        this.costSharing = costSharing;
    }
}
