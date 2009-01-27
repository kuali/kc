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
package org.kuali.kra.award.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.AwardApprovedEquipment;
import org.kuali.kra.award.rule.AwardApprovedEquipmentRule;
import org.kuali.kra.award.rule.event.AwardApprovedEquipmentRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * The AwardApprovedEquipmentRuleImpl
 */
public class AwardApprovedEquipmentRuleImpl extends ResearchDocumentRuleBase 
                                            implements AwardApprovedEquipmentRule {
    
    private static final String EQUIPMENT_AMOUNT_PROPERTY = "approvedEquipmentFormHelper.newAwardApprovedEquipment.amount";
    private static final String EQUIPMENT_ITEM_PROPERTY = "approvedEquipmentFormHelper.newAwardApprovedEquipment.item";
    private static final String AMOUNT_ERROR_PARM = "Amount";
    private static final String ITEM_ERROR_PARM = "Item";

    public boolean processAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent event) {
        return processCommonValidations(event);        
    }
    
    public boolean processNewAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent event) {
        boolean commValidations = processCommonValidations(event);
        
        AwardApprovedEquipment equipmentItem = event.getEquipmentItemForValidation();
        boolean validRequiredFields = areRequiredFieldsComplete(equipmentItem);        
                
        return commValidations && validRequiredFields;
    }
    
    private boolean processCommonValidations(AwardApprovedEquipmentRuleEvent event) {
        AwardApprovedEquipment equipmentItem = event.getEquipmentItemForValidation();
        boolean validAmount = isAmountValid(equipmentItem, event.getMinimumCapitalization());
        List<AwardApprovedEquipment> items = event.getAwardDocument().getAward().getApprovedEquipmentItems();
        boolean validUnique = isUnique(items, equipmentItem);
        
        return validAmount && validUnique;
    }
    
    /**
     * An equipment item is unique if no other matching items are in the collection
     * To know if this is a new add or an edit of an existing equipment item, we check 
     * the identifier for nullity. If null, this is an add; otherwise, it's an update
     * If an update, then we expect to find one match in the collection (itself). If an add, 
     * we expect to find no matches in the collection 
     * @param equipmentItems
     * @param equipmentItem
     * @return
     */
    boolean isUnique(List<AwardApprovedEquipment> equipmentItems, AwardApprovedEquipment equipmentItem) {
        boolean duplicateFound = false;
        for(AwardApprovedEquipment listItem: equipmentItems) {
            duplicateFound = equipmentItem != listItem && listItem.equals(equipmentItem);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            reportError("document.awardList[0].item", 
                    KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE, ITEM_ERROR_PARM);
        }
        return !duplicateFound;
    }

    /**
     * Validate required fields present
     * @param equipmentItem
     * @return
     */
    boolean areRequiredFieldsComplete(AwardApprovedEquipment equipmentItem) {
        boolean itemValid = !StringUtils.isEmpty(equipmentItem.getItem());
        if(!itemValid) {
            reportError(EQUIPMENT_ITEM_PROPERTY, 
                            KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_REQUIRED, ITEM_ERROR_PARM);
        }
        
        boolean amountValid = equipmentItem.getAmount() != null;
        if(!amountValid) {
            reportError(EQUIPMENT_AMOUNT_PROPERTY, 
                            KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_REQUIRED, AMOUNT_ERROR_PARM);
        }
        
        return itemValid && amountValid;
    }
    
    /**
     * Validate required amount field
     * @param equipmentItem
     * @return Always returns true as this is a soft-error
     */
    boolean isAmountValid(AwardApprovedEquipment equipmentItem, MinimumCapitalizationInfo minimumCapitalization) {
        KualiDecimal amount = equipmentItem.getAmount();
        boolean amountValid = amount != null && amount.doubleValue() >= minimumCapitalization.getAmount();
        if(!amountValid) {
            reportSoftError(KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID, 
                                String.format("%-12.2f", minimumCapitalization.getAmount()).trim(), minimumCapitalization.getRequirementDriver());
        }
        return true;
    }
}