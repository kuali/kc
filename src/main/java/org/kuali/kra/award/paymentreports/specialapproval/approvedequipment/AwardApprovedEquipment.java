/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.paymentreports.specialapproval.approvedequipment;

import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class handles the Award Special Approval for Approved Equipment
 */
public class AwardApprovedEquipment extends AwardAssociate implements ValuableItem {
    private static final long serialVersionUID = 1039155193608738040L;
    
    private Long approvedEquipmentId;
    private String item;
    private String vendor;
    private String model;
    private KualiDecimal amount;
    
    /**
     * Constructs a AwardApprovedEquipment
     */
    public AwardApprovedEquipment() {
    }
    
    /**
     * Constructs a AwardApprovedEquipment
     */
    public AwardApprovedEquipment(String vendor, String model, String item, double amount) {
        this();
        this.vendor = vendor;
        this.model = model;
        this.item = item;
        this.amount = new KualiDecimal(amount);
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
    public KualiDecimal getAmount() {
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
    public void setAmount(final KualiDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * Convenience method
     * @param amount
     */
    public void setAmount(final double amount) {
        this.amount = new KualiDecimal(amount);
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
        if (this == anotherEquipmentItem) { return true; }
        if (anotherEquipmentItem == null) { return false; }
        
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
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.approvedEquipmentId = null;
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("approvedEquipmentId", approvedEquipmentId);
        map.put("amount", amount);
        map.put("item", item);
        map.put("model", model);
        map.put("vendor", vendor);
        return map;
    }
}