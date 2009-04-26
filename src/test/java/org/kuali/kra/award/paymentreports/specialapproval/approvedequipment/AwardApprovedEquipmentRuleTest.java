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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.rules.SoftError;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests AwardApprovedEquipmentRuleImpl behavior
 */
public class AwardApprovedEquipmentRuleTest {
    private static final double ONE_PENNY = 0.01;
    private static final double AMOUNT1 = 200.00;
    private static final double AMOUNT2 = 100.00;
    private static final String AMOUNT2_TEXT = "100.00";
    private static final String WIDGET1 = "Widget1";
    private static final String WIDGET2 = "Widget2";
    private static final String MODEL = "Model 1001";
    private static final String VENDOR = "WidgetVendor";
    private AwardApprovedEquipmentRuleImpl approvedEquipmentRule;
    private Award award;
    private MinimumCapitalizationInfo minimumCapitalizationInfo;
    
    @Before
    public void setUp() throws Exception {
        approvedEquipmentRule = prepareTestReadyAwardApprovedEquipmentRule();
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        String requirement = EquipmentCapitalizationMinimumLoader.INSTITUTION_REQUIREMENT;
        minimumCapitalizationInfo = new MinimumCapitalizationInfo(requirement, AMOUNT2);
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @After
    public void tearDown() throws Exception {
        minimumCapitalizationInfo = null;
        award = null;
        approvedEquipmentRule = null;
    }
    
    @Test
    public void testRequiredFieldPresent() {
        AwardApprovedEquipment equipmentItem = createEquipmentItem(VENDOR, MODEL, WIDGET1, AMOUNT1);
        Assert.assertTrue(approvedEquipmentRule.areRequiredFieldsComplete(equipmentItem));
        
        equipmentItem.setVendor(null);
        Assert.assertTrue(approvedEquipmentRule.areRequiredFieldsComplete(equipmentItem));
        
        equipmentItem.setModel(null);
        Assert.assertTrue(approvedEquipmentRule.areRequiredFieldsComplete(equipmentItem));
        
        equipmentItem.setItem(null);
        Assert.assertFalse(approvedEquipmentRule.areRequiredFieldsComplete(equipmentItem));
        
        equipmentItem.setItem(WIDGET1);
        equipmentItem.setAmount(null);
        Assert.assertFalse(approvedEquipmentRule.areRequiredFieldsComplete(equipmentItem));
    }
    
    @Test
    public void testValidatingAmount() {
        AwardApprovedEquipment equipmentItem = createEquipmentItem(VENDOR, MODEL, WIDGET1, -1.00);
        checkValidAmount(equipmentItem, 1);
        
        equipmentItem.setAmount(0.00);
        checkValidAmount(equipmentItem, 1);
        
        equipmentItem.setAmount(AMOUNT2 - ONE_PENNY);
        checkValidAmount(equipmentItem, 1);
        
        equipmentItem.setAmount(AMOUNT2);
        checkValidAmount(equipmentItem, 0);
        
        equipmentItem.setAmount(null);
        checkValidAmount(equipmentItem, 1);
    }
    
    @Test
    public void testValidatingAmount_MinimumCapititalizationAmount() {
        AwardApprovedEquipment equipmentItem = createEquipmentItem(VENDOR, MODEL, WIDGET1, 1.00);
        approvedEquipmentRule.isAmountValid(AwardApprovedEquipmentRule.APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        List<SoftError> errors = new ArrayList<SoftError>(approvedEquipmentRule.getSoftErrors()
                                                                    .get(AwardApprovedEquipmentRule.APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY));
        Assert.assertEquals(AMOUNT2_TEXT, errors.get(0).getErrorParms()[0]);
    }
    
    @Test
    public void testIsUnique() {
        AwardApprovedEquipment equipmentItem1 = createEquipmentItem(VENDOR, MODEL, WIDGET1, AMOUNT1);
        AwardApprovedEquipment equipmentItem2 = createEquipmentItem(VENDOR, MODEL, WIDGET2, AMOUNT2);
        
        
        checkAddingNewItemToEmptyList(equipmentItem1);
        award.add(equipmentItem1);
        checkExistingEntriesDoesntTriggerErrorOnSave();
        addApprovedEquipmentToAward(equipmentItem1, equipmentItem2);
        checkAddingDuplicateToCollection();
        checkEditingItemResultingInDuplicate(equipmentItem2);
    }

    private void addApprovedEquipmentToAward(AwardApprovedEquipment equipmentItem1, 
                                                AwardApprovedEquipment equipmentItem2) {
        equipmentItem1.setApprovedEquipmentId(1L);
        award.add(equipmentItem1);
        equipmentItem2.setApprovedEquipmentId(2L);
        award.add(equipmentItem2);
    }

    private void checkAddingDuplicateToCollection() {
        AwardApprovedEquipment item1Duplicate = createEquipmentItem(VENDOR, MODEL, WIDGET1, AMOUNT1);
        Assert.assertFalse(approvedEquipmentRule.isUnique(award.getApprovedEquipmentItems(), item1Duplicate));
    }
    
    private void checkAddingNewItemToEmptyList(AwardApprovedEquipment equipmentItem1) {
        Assert.assertTrue(approvedEquipmentRule.isUnique(new ArrayList<AwardApprovedEquipment>(), 
                                                            equipmentItem1));
    }

    private void checkExistingEntriesDoesntTriggerErrorOnSave() {
        List<AwardApprovedEquipment> items = award.getApprovedEquipmentItems();
        for(AwardApprovedEquipment item : items) {
            Assert.assertTrue(approvedEquipmentRule.isUnique(items, item));
        }
    }
    
    private void checkValidAmount(AwardApprovedEquipment equipmentItem, int expectedErrorCount) {
        approvedEquipmentRule.isAmountValid(AwardApprovedEquipmentRule.APPROVED_EQUIPMENT_ITEMS_LIST_ERROR_KEY, equipmentItem, minimumCapitalizationInfo);
        Assert.assertEquals(expectedErrorCount, approvedEquipmentRule.getSoftErrors().size());
        approvedEquipmentRule.getSoftErrors().clear();
    }
    
    private void checkEditingItemResultingInDuplicate(AwardApprovedEquipment equipmentItem2) {
        equipmentItem2.setItem(WIDGET1);
        Assert.assertFalse(approvedEquipmentRule.isUnique(award.getApprovedEquipmentItems(), equipmentItem2));
    }
    
    private AwardApprovedEquipment createEquipmentItem(String vendor, String model, 
                                                        String item, double value) {
        return new AwardApprovedEquipment(vendor, model, item, value);
    }
    
    private AwardApprovedEquipmentRuleImpl prepareTestReadyAwardApprovedEquipmentRule() {
        AwardApprovedEquipmentRuleImpl approvedEquipmentRule = new AwardApprovedEquipmentRuleImpl() {
            private Map<String, Collection<SoftError>> softErrors = new HashMap<String, Collection<SoftError>>();
            @Override
            public Map<String, Collection<SoftError>> getSoftErrors() { 
                return softErrors; 
            }
            @Override
            protected void reportSoftError(String propertyName, String errorKey, String... errorParams) {
                addSoftError(propertyName, errorKey, errorParams);
            }
            private void addSoftError(String propertyName, String errorKey, String[] errorParams) {
                Collection<SoftError> errorsForProperty = softErrors.get(propertyName);
                if(errorsForProperty == null) {
                    errorsForProperty = new HashSet<SoftError>();
                }
                errorsForProperty.add(new SoftError(errorKey, errorParams));
                softErrors.put(propertyName, errorsForProperty);
            }
        };
        return approvedEquipmentRule;
    }
}
