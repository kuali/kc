/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

/**
 * The AwardApprovedEquipmentRuleImpl
 */
public class AwardApprovedEquipmentRuleImpl extends KcTransactionalDocumentRuleBase
                                            implements AwardApprovedEquipmentRule {
    
    private static final String EQUIPMENT_AMOUNT_PROPERTY = "approvedEquipmentBean.newAwardApprovedEquipment.amount";
    private static final String EQUIPMENT_ITEM_PROPERTY = "approvedEquipmentBean.newAwardApprovedEquipment.item";
    private static final String AMOUNT_ERROR_PARM = "Amount (Amount)";
    private static final String ITEM_ERROR_PARM = "Item (Item)";

    @Override
    public boolean processAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent event) {
        return processCommonValidations(event);        
    }
    /**
     * 
     * This method processes new AwardApprovedEquipment rules
     * 
     * @param event
     * @return
     */
    public boolean processAddAwardApprovedEquipmentBusinessRules(AddAwardApprovedEquipmentRuleEvent event) {
        return areRequiredFieldsComplete(event.getEquipmentItemForValidation()) && processCommonValidations(event);        
    }
    
    private boolean processCommonValidations(AwardApprovedEquipmentRuleEvent event) {
        AwardApprovedEquipment equipmentItem = event.getEquipmentItemForValidation();
        boolean valid = isAmountValid(event.getErrorPathPrefix(), equipmentItem, event.getMinimumCapitalization());
        List<AwardApprovedEquipment> items = event.getAward().getApprovedEquipmentItems();
        valid &= isUnique(items, equipmentItem);
        
        return valid;
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
            if(!hasDuplicateErrorBeenReported()) {
                reportError(APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, 
                        KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE, ITEM_ERROR_PARM);
            }
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
     * @param errorPath
     * @param equipmentItem
     * @param minimumCapitalization
     * @return
     */
    boolean isAmountValid(String errorPath, AwardApprovedEquipment equipmentItem, MinimumCapitalizationInfo minimumCapitalization) {
        ScaleTwoDecimal amount = equipmentItem.getAmount();
        boolean amountValid = amount != null && amount.doubleValue() >= minimumCapitalization.getAmount();
        if(!amountValid) {
            reportSoftError(APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID, 
                                String.format("%-12.2f", minimumCapitalization.getAmount()).trim(), 
                                                minimumCapitalization.getRequirementDriver());
        } else if (minimumCapitalization.getFederalMinimum() <= amount.doubleValue() 
                && minimumCapitalization.getInstituteMinimum() > amount.doubleValue()) {
            reportSoftError(APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID, 
                    String.format("%-12.2f", minimumCapitalization.getInstituteMinimum()).trim(), 
                                     "Institution");            
            amountValid = false;
        }  else if (minimumCapitalization.getFederalMinimum() > amount.doubleValue() 
                && minimumCapitalization.getInstituteMinimum() <= amount.doubleValue()) {
            reportSoftError(APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_AMOUNT_VALID, 
                    String.format("%-12.2f", minimumCapitalization.getFederalMinimum()).trim(), 
                                     "Federal");
            amountValid = false;            
        }
        return true;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_AWARD_APPROVED_EQUIPMENT_ITEM_NOT_UNIQUE);
    }
}
