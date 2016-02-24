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
package org.kuali.coeus.propdev.impl.print;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;


public class CompensationInfo {

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
