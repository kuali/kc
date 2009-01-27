package org.kuali.kra.award.rules;

/**
 * This class carries the min capitalization data
 */
public class MinimumCapitalizationInfo {
    private String requirementDriver;
    private double amount;
    /**
     * Constructs a EquipmentCapitalizationMinimumLoader.java.
     * @param requirementDriver
     * @param amount
     */
    public MinimumCapitalizationInfo(String requirementDriver, double amount) {
        this.requirementDriver = requirementDriver;
        this.amount = amount;
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
    
}