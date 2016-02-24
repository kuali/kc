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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

/**
 * This class carries the min capitalization data
 */
public class MinimumCapitalizationInfo {
    private String requirementDriver;
    private double amount;
    private double federalMinimum;
    private double instituteMinimum;
    /**
     * Constructs a EquipmentCapitalizationMinimumLoader.java.
     * @param requirementDriver
     * @param amount
     */
    public MinimumCapitalizationInfo(String requirementDriver, double amount, double federalMinimum, double instituteMinimum) {
        this.requirementDriver = requirementDriver;
        this.amount = amount;
        this.federalMinimum = federalMinimum;
        this.instituteMinimum = instituteMinimum;
    }
    /**
     * Gets the requirementDriver attribute. 
     * @return Returns the requirementDriver.
     */
    public String getRequirementDriver() {
        return requirementDriver;
    }
    /**
     * Gets the amount attribute. 
     * @return Returns the amount.
     */
    public double getAmount() {
        return amount;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = PRIME * result + (int) (temp ^ (temp >>> 32));
        result = PRIME * result + ((requirementDriver == null) ? 0 : requirementDriver.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MinimumCapitalizationInfo)) {
            return false;
        }
        MinimumCapitalizationInfo other = (MinimumCapitalizationInfo) obj;
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (requirementDriver == null) {
            if (other.requirementDriver != null) {
                return false;
            }
        } else if (!requirementDriver.equals(other.requirementDriver)) {
            return false;
        }
        return true;
    }
    
    public double getFederalMinimum() {
        return federalMinimum;
    }
    public void setFederalMinimum(double federalMinimum) {
        this.federalMinimum = federalMinimum;
    }
    public double getInstituteMinimum() {
        return instituteMinimum;
    }
    public void setInstituteMinimum(double instituteMinimum) {
        this.instituteMinimum = instituteMinimum;
    }

}
