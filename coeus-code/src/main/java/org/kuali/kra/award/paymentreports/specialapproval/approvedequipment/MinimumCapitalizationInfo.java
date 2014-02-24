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