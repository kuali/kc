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


import org.kuali.kra.award.AwardForm;

import java.io.Serializable;

/**
 * This class supports the AwardForm class
 */
public class ApprovedEquipmentBean extends SpecialApprovalBean implements Serializable {
    private EquipmentCapitalizationMinimumLoader capitalizationMinimumLoader;
    private AwardApprovedEquipment newAwardApprovedEquipment;
    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    public ApprovedEquipmentBean(AwardForm form) {
        this(form, new EquipmentCapitalizationMinimumLoader());
    }
    
    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    ApprovedEquipmentBean(AwardForm form, EquipmentCapitalizationMinimumLoader capitalizationMinimumLoader) {
        super(form);
        this.capitalizationMinimumLoader = capitalizationMinimumLoader;
        init();
    }
    
    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     */
    public boolean addApprovedEquipmentItem() {
        AddAwardApprovedEquipmentRuleEvent event = generateAddEvent();
        boolean success = new AwardApprovedEquipmentRuleImpl().processAddAwardApprovedEquipmentBusinessRules(event);
        if(success){
            getAward().add(getNewAwardApprovedEquipment());
            init();
        }
        return success;
    }

    /**
     * This method delets a selected equipment item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deleteApprovedEquipmentItem(int deletedItemIndex) {
        removeCollectionItem(getAward().getApprovedEquipmentItems(), deletedItemIndex);
    }
    

    public Object getData() {
        return getNewAwardApprovedEquipment();
    }
    
    /**
     * Gets the newAwardApprovedEquipment attribute. 
     * @return Returns the newAwardApprovedEquipment.
     */
    public AwardApprovedEquipment getNewAwardApprovedEquipment() {
        return newAwardApprovedEquipment;
    }

    /**
     * Sets the newAwardApprovedEquipment attribute value.
     * @param newAwardApprovedEquipment The newAwardApprovedEquipment to set.
     */
    void setNewAwardApprovedEquipment(AwardApprovedEquipment newAwardApprovedEquipment) {
        this.newAwardApprovedEquipment = newAwardApprovedEquipment;
    }
    
    /**
     * This method generates an Add Event
     * @return
     */
    AddAwardApprovedEquipmentRuleEvent generateAddEvent() {        
        AddAwardApprovedEquipmentRuleEvent event = new AddAwardApprovedEquipmentRuleEvent(
                                                            "newAwardApprovedEquipment",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            getNewAwardApprovedEquipment(),
                                                            capitalizationMinimumLoader.getMinimumCapitalization());
        return event;
    }

    /**
     * Initialize subform
     */
    private void init() {
        newAwardApprovedEquipment = new AwardApprovedEquipment(); 
    }
}
