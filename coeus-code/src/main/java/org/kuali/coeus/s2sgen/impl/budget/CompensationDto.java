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


public class CompensationDto {

    private ScaleTwoDecimal calendarMonths = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal academicMonths = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal summerMonths = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal requestedSalary = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal fringe = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal fundsRequested = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal baseSalary = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal fringeCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal nonFundsRequested = ScaleTwoDecimal.ZERO;

    /**
     * Getter for property calendarMonths.
     * 
     * @return Value of property calendarMonths.
     */
    public ScaleTwoDecimal getCalendarMonths() {
        return calendarMonths;
    }

    /**
     * Setter for property calendarMonths.
     * 
     * @param calendarMonths New value of property calendarMonths.
     */
    public void setCalendarMonths(ScaleTwoDecimal calendarMonths) {
        this.calendarMonths = calendarMonths;
    }

    /**
     * Getter for property academicMonths.
     * 
     * @return Value of property academicMonths.
     */
    public ScaleTwoDecimal getAcademicMonths() {
        return academicMonths;
    }

    /**
     * Setter for property academicMonths.
     * 
     * @param academicMonths New value of property academicMonths.
     */
    public void setAcademicMonths(ScaleTwoDecimal academicMonths) {
        this.academicMonths = academicMonths;
    }

    /**
     * Getter for property summerMonths.
     * 
     * @return Value of property summerMonths.
     */
    public ScaleTwoDecimal getSummerMonths() {
        return summerMonths;
    }

    /**
     * Setter for property summerMonths.
     * 
     * @param summerMonths New value of property summerMonths.
     */
    public void setSummerMonths(ScaleTwoDecimal summerMonths) {
        this.summerMonths = summerMonths;
    }

    /**
     * Getter for property requestedSalary.
     * 
     * @return Value of property requestedSalary.
     */
    public ScaleTwoDecimal getRequestedSalary() {
        return requestedSalary;
    }

    /**
     * Setter for property requestedSalary.
     * 
     * @param requestedSalary New value of property requestedSalary.
     */
    public void setRequestedSalary(ScaleTwoDecimal requestedSalary) {
        this.requestedSalary = requestedSalary;
    }

    /**
     * Getter for property fringe.
     * 
     * @return Value of property fringe.
     */
    public ScaleTwoDecimal getFringe() {
        return fringe;
    }

    /**
     * Setter for property fringe.
     * 
     * @param fringe New value of property fringe.
     */
    public void setFringe(ScaleTwoDecimal fringe) {
        this.fringe = fringe;
    }

    /**
     * Getter for property fundsRequested.
     * 
     * @return Value of property fundsRequested.
     */
    public ScaleTwoDecimal getFundsRequested() {
        return fundsRequested;
    }

    /**
     * Setter for property fundsRequested.
     * 
     * @param fundsRequested New value of property fundsRequested.
     */
    public void setFundsRequested(ScaleTwoDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    /**
     * Getter for property baseSalary.
     * 
     * @return Value of property baseSalary.
     */
    public ScaleTwoDecimal getBaseSalary() {
        return baseSalary;
    }

    /**
     * Setter for property baseSalary.
     * 
     * @param baseSalary New value of property baseSalary.
     */
    public void setBaseSalary(ScaleTwoDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    // start add costSaring for fedNonFedBudget repport
    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public ScaleTwoDecimal getNonFundsRequested() {
        return nonFundsRequested;
    }

    public void setNonFundsRequested(ScaleTwoDecimal nonFundsRequested) {
        this.nonFundsRequested = nonFundsRequested;
    }

    public ScaleTwoDecimal getFringeCostSharing() {
        return fringeCostSharing;
    }

    public void setFringeCostSharing(ScaleTwoDecimal fringeCostSharing) {
        this.fringeCostSharing = fringeCostSharing;
    }
}
