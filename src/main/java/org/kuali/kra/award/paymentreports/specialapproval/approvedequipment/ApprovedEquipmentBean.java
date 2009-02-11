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

import java.util.List;

import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardApprovedEquipment;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class supports the AwardForm class
 */
public class ApprovedEquipmentBean {
    private AwardDocument document;
    private KualiRuleService ruleService;
    private AwardApprovedEquipment newAwardApprovedEquipment;
    private Award award;
    private EquipmentCapitalizationMinimumLoader capitalizationMinimumLoader;
    
    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    public ApprovedEquipmentBean(AwardDocument document, Award award) {
        this(document, award, new EquipmentCapitalizationMinimumLoader());
    }
    
    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    ApprovedEquipmentBean(AwardDocument document, Award award, EquipmentCapitalizationMinimumLoader capitalizationMinimumLoader) {
        this.document = document;
        this.award = award;
        this.capitalizationMinimumLoader = capitalizationMinimumLoader;
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardApprovedEquipment = new AwardApprovedEquipment(); 
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
    public void setNewAwardApprovedEquipment(AwardApprovedEquipment newAwardApprovedEquipment) {
        this.newAwardApprovedEquipment = newAwardApprovedEquipment;
    }

    /**
     * @return
     */
    public AwardDocument getAwardDocument() {
        return document;
    }
    
    /**
     * @return
     */
    public Award getAward() {
        return award;
    }
    
    /**
     * @return
     */
    public Object getData() {
        return getNewAwardApprovedEquipment();
    }
    
    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     */
    public boolean addApprovedEquipmentItem() {
        AwardApprovedEquipmentRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
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
        List<AwardApprovedEquipment> items = getAward().getApprovedEquipmentItems();
        if(deletedItemIndex >= 1 || deletedItemIndex <= items.size()) {
            items.remove(deletedItemIndex-1);
        }        
    }
    
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService("kualiRuleService"); 
        }
        return ruleService;
    }
    
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    AwardApprovedEquipmentRuleEvent generateAddEvent() {        
        AwardApprovedEquipmentRuleEvent event = new AwardApprovedEquipmentRuleEvent(
                                                            "newAwardApprovedEquipment",
                                                            getAwardDocument(),
                                                            getNewAwardApprovedEquipment(),
                                                            capitalizationMinimumLoader.getMinimumCapitalization());
        return event;
    }
}
