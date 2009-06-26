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


import java.io.Serializable;

import org.kuali.kra.award.AwardForm;

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
    
    /**
     * @return
     */
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
