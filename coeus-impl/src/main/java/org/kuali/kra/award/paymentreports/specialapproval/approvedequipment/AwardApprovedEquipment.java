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

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * This class handles the Award Special Approval for Approved Equipment
 */
public class AwardApprovedEquipment extends AwardAssociate implements ValuableItem {

    private static final long serialVersionUID = 1039155193608738040L;

    private Long approvedEquipmentId;

    private String item;

    private String vendor;

    private String model;

    private ScaleTwoDecimal amount;


    public AwardApprovedEquipment() {
    }


    public AwardApprovedEquipment(String vendor, String model, String item, double amount) {
        this();
        this.vendor = vendor;
        this.model = model;
        this.item = item;
        this.amount = new ScaleTwoDecimal(amount);
    }

    /**
     * Gets the approvedEquipmentId attribute. 
     * @return Returns the approvedEquipmentId.
     */
    public Long getApprovedEquipmentId() {
        return approvedEquipmentId;
    }

    /**
     * Gets the item attribute. 
     * @return Returns the item.
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the vendor attribute. 
     * @return Returns the vendor.
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Gets the model attribute. 
     * @return Returns the model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the amount attribute. 
     * @return Returns the amount.
     */
    public ScaleTwoDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the approvedEquipmentId attribute value.
     * @param approvedEquipmentId The approvedEquipmentId to set.
     */
    public void setApprovedEquipmentId(final Long approvedEquipmentId) {
        this.approvedEquipmentId = approvedEquipmentId;
    }

    /**
     * Sets the item attribute value.
     * @param item The item to set.
     */
    public void setItem(final String item) {
        this.item = item;
    }

    /**
     * Sets the vendor attribute value.
     * @param vendor The vendor to set.
     */
    public void setVendor(final String vendor) {
        this.vendor = vendor;
    }

    /**
     * Sets the model attribute value.
     * @param model The model to set.
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * Sets the amount attribute value.
     * @param amount The amount to set.
     */
    public void setAmount(final ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    /**
     * Convenience method
     * @param amount
     */
    public void setAmount(final double amount) {
        this.amount = new ScaleTwoDecimal(amount);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + ((item == null) ? 0 : item.hashCode());
        result = PRIME * result + ((model == null) ? 0 : model.hashCode());
        result = PRIME * result + ((vendor == null) ? 0 : vendor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AwardApprovedEquipment)) {
            return false;
        }
        AwardApprovedEquipment other = (AwardApprovedEquipment) obj;
        if (item == null) {
            if (other.item != null) {
                return false;
            }
        } else if (!item.equals(other.item)) {
            return false;
        }
        if (model == null) {
            if (other.model != null) {
                return false;
            }
        } else if (!model.equals(other.model)) {
            return false;
        }
        if (vendor == null) {
            if (other.vendor != null) {
                return false;
            }
        } else if (!vendor.equals(other.vendor)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * Convenience method to check equiality of another AwardApprovedEquipment
     * @param anotherEquipmentItem
     * @return
     */
    public boolean equals(AwardApprovedEquipment anotherEquipmentItem) {
        if (this == anotherEquipmentItem) {
            return true;
        }
        if (anotherEquipmentItem == null) {
            return false;
        }
        if (item == null) {
            if (anotherEquipmentItem.item != null) {
                return false;
            }
        } else if (!item.equals(anotherEquipmentItem.item)) {
            return false;
        }
        if (model == null) {
            if (anotherEquipmentItem.model != null) {
                return false;
            }
        } else if (!model.equals(anotherEquipmentItem.model)) {
            return false;
        }
        if (vendor == null) {
            if (anotherEquipmentItem.vendor != null) {
                return false;
            }
        } else if (!vendor.equals(anotherEquipmentItem.vendor)) {
            return false;
        }
        return true;
    }

    @Override
    public void resetPersistenceState() {
        this.approvedEquipmentId = null;
    }
}
