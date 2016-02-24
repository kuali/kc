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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.*;

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
        approvedEquipmentRule.setErrorReporter(new ErrorReporterImpl());
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        String requirement = EquipmentCapitalizationMinimumLoader.INSTITUTION_REQUIREMENT;
        minimumCapitalizationInfo = new MinimumCapitalizationInfo(requirement, AMOUNT2, AMOUNT1, AMOUNT2);
        GlobalVariables.setMessageMap(new MessageMap());
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
        checkValidAmount(equipmentItem, 1);
        
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
