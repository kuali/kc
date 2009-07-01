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
package org.kuali.kra.s2s.generator.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.budget.BudgetDecimal;

public class CompensationInfo {

    private BudgetDecimal calendarMonths;
    private BudgetDecimal academicMonths;
    private BudgetDecimal summerMonths;
    private BudgetDecimal requestedSalary;
    private BudgetDecimal fringe;
    private BudgetDecimal fundsRequested;
    private BudgetDecimal baseSalary;
    private BudgetDecimal costSharingAmount;
    private BudgetDecimal fringeCostSharing;
    private BudgetDecimal nonFundsRequested;


    public CompensationInfo() {
    }

    /**
     * Getter for property calendarMonths.
     * 
     * @return Value of property calendarMonths.
     */
    public BudgetDecimal getCalendarMonths() {
        return calendarMonths;
    }

    /**
     * Setter for property calendarMonths.
     * 
     * @param calendarMonths New value of property calendarMonths.
     */
    public void setCalendarMonths(BudgetDecimal calendarMonths) {
        this.calendarMonths = calendarMonths;
    }

    /**
     * Getter for property academicMonths.
     * 
     * @return Value of property academicMonths.
     */
    public BudgetDecimal getAcademicMonths() {
        return academicMonths;
    }

    /**
     * Setter for property academicMonths.
     * 
     * @param academicMonths New value of property academicMonths.
     */
    public void setAcademicMonths(BudgetDecimal academicMonths) {
        this.academicMonths = academicMonths;
    }

    /**
     * Getter for property summerMonths.
     * 
     * @return Value of property summerMonths.
     */
    public BudgetDecimal getSummerMonths() {
        return summerMonths;
    }

    /**
     * Setter for property summerMonths.
     * 
     * @param summerMonths New value of property summerMonths.
     */
    public void setSummerMonths(BudgetDecimal summerMonths) {
        this.summerMonths = summerMonths;
    }

    /**
     * Getter for property requestedSalary.
     * 
     * @return Value of property requestedSalary.
     */
    public BudgetDecimal getRequestedSalary() {
        return requestedSalary;
    }

    /**
     * Setter for property requestedSalary.
     * 
     * @param requestedSalary New value of property requestedSalary.
     */
    public void setRequestedSalary(BudgetDecimal requestedSalary) {
        this.requestedSalary = requestedSalary;
    }

    /**
     * Getter for property fringe.
     * 
     * @return Value of property fringe.
     */
    public BudgetDecimal getFringe() {
        return fringe;
    }

    /**
     * Setter for property fringe.
     * 
     * @param fringe New value of property fringe.
     */
    public void setFringe(BudgetDecimal fringe) {
        this.fringe = fringe;
    }

    /**
     * Getter for property fundsRequested.
     * 
     * @return Value of property fundsRequested.
     */
    public BudgetDecimal getFundsRequested() {
        return fundsRequested;
    }

    /**
     * Setter for property fundsRequested.
     * 
     * @param fundsRequested New value of property fundsRequested.
     */
    public void setFundsRequested(BudgetDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    /**
     * Getter for property baseSalary.
     * 
     * @return Value of property baseSalary.
     */
    public BudgetDecimal getBaseSalary() {
        return baseSalary;
    }

    /**
     * Setter for property baseSalary.
     * 
     * @param baseSalary New value of property baseSalary.
     */
    public void setBaseSalary(BudgetDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    // start add costSaring for fedNonFedBudget repport
    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public BudgetDecimal getNonFundsRequested() {
        return nonFundsRequested;
    }

    public void setNonFundsRequested(BudgetDecimal nonFundsRequested) {
        this.nonFundsRequested = nonFundsRequested;
    }

    public BudgetDecimal getFringeCostSharing() {
        return fringeCostSharing;
    }

    public void setFringeCostSharing(BudgetDecimal fringeCostSharing) {
        this.fringeCostSharing = fringeCostSharing;
    }


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("calendarMonths", getCalendarMonths());
        hashMap.put("academicMonths", getAcademicMonths());
        hashMap.put("summerMonths", getSummerMonths());
        hashMap.put("requestedSalary", getRequestedSalary());
        hashMap.put("fringe", getFringe());
        hashMap.put("fundsRequested", getFundsRequested());
        hashMap.put("baseSalary", getBaseSalary());
        hashMap.put("costSharingAmount", getCostSharingAmount());
        hashMap.put("fringeCostSharing", getFringeCostSharing());
        hashMap.put("nonFundsRequested", getNonFundsRequested());
        return hashMap;
    }
}
