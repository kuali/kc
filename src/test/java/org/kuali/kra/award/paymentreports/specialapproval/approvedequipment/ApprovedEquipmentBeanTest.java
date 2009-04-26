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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.kuali.kra.award.bo.Award;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This test class will not be executable until we fix the Rice code where 
 * the Document and Form class constructors invoke Spring services. Geez! Have these
 * guys never read anything about unit testing?
 * 
 */     
//@RunWith(JMock.class)
public class ApprovedEquipmentBeanTest {
    private static final String MODEL_NAME = "Model";
    private static final String VENDOR_NAME = "Vendor";
    private static final String ITEM_NAME = "Item";
    private static final double INSTITUTION_MINIMUM_AMOUNT = 50.00;
    private static final double FEDERAL_MINIMUM_AMOUNT = 100.00;
    
    private Mockery context;
    private KualiRuleService ruleService;
    private Award award;
    private SpecialApprovalBean bean;
    private AwardApprovedEquipment equipmentItem;
   
    @Test
    public void testDummy() {
        
    }
//    @Before
//    public void setUp() throws Exception {
//        context = new JUnit4Mockery();
//        ruleService = getRuleService();
//        award = new Award();
//        AwardForm form = new AwardForm();
//        bean = new ApprovedEquipmentBean(form, getCapitalizationMinimumLoader());
//        equipmentItem = new AwardApprovedEquipment();        
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//        equipmentItem = null;
//        bean = null;
//        award = null;
//        ruleService = null;
//        context = null;
//    }
//    
//    @Test
//    public void testAddingNewEquipmentItem() throws Exception {
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        
//        equipmentItem.setItem(ITEM_NAME);
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        Assert.assertEquals(0, award.getApprovedEquipmentItems().size());
//        
//        equipmentItem.setVendor(VENDOR_NAME);
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        
//        Assert.assertEquals(0, award.getApprovedEquipmentItems().size());
//        equipmentItem.setModel(MODEL_NAME);
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        
//        equipmentItem.setAmount(-INSTITUTION_MINIMUM_AMOUNT);
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        
//        equipmentItem.setAmount(0.00);
//        checkAddingEquipmentItem(bean, equipmentItem, false);
//        
//        Assert.assertEquals(0, award.getApprovedEquipmentItems().size());
//        
//        equipmentItem.setAmount(INSTITUTION_MINIMUM_AMOUNT);
//        checkAddingEquipmentItem(bean, equipmentItem, true);
//        Assert.assertEquals(1, award.getApprovedEquipmentItems().size());
//        
//        equipmentItem.setAmount(FEDERAL_MINIMUM_AMOUNT);
//        checkAddingEquipmentItem(bean, equipmentItem, true);
//        Assert.assertEquals(2, award.getApprovedEquipmentItems().size());
//    }
//
//    @Test
//    public void testDeletingEquipmentItem() throws Exception {
//        final int NUMBER_OF_ITEMS = 5;
//        for (int i = 1; i <= NUMBER_OF_ITEMS; i++) {
//            award.add(new AwardApprovedEquipment(VENDOR_NAME + i, MODEL_NAME + i, ITEM_NAME + i, INSTITUTION_MINIMUM_AMOUNT));
//        }
//        List<AwardApprovedEquipment> originalList = new ArrayList<AwardApprovedEquipment>(award.getApprovedEquipmentItems());
//        Assert.assertEquals(NUMBER_OF_ITEMS, award.getApprovedEquipmentItems().size());
//        
//        bean.deleteApprovedEquipmentItem(1);
//        
//        Assert.assertEquals(NUMBER_OF_ITEMS - 1, award.getApprovedEquipmentItems().size());
//        Assert.assertFalse(award.getApprovedEquipmentItems().contains(originalList.get(0)));
//    }
    
    private void checkAddingEquipmentItem(ApprovedEquipmentBean bean, AwardApprovedEquipment item, boolean expectedOutcome) {
        bean.setNewAwardApprovedEquipment(item);
        bean.setRuleService(ruleService);
        setExpectation(ruleService, bean.generateAddEvent(), expectedOutcome);
        bean.addApprovedEquipmentItem();
        context.assertIsSatisfied();
    }
    
    private KualiRuleService getRuleService() {
        return context.mock(KualiRuleService.class);
    }
    
    private void setExpectation(final KualiRuleService ruleService, final KualiDocumentEvent event, final boolean outcome) {
        context.checking(new Expectations() {{
            one(ruleService).applyRules(event); 
            will(returnValue(outcome));
            }});
    }
    
    private EquipmentCapitalizationMinimumLoader getCapitalizationMinimumLoader() {
        EquipmentCapitalizationMinimumLoader loader = new EquipmentCapitalizationMinimumLoader() {

            @Override
            protected double getFederalCapitalizationMinimum() {
                return FEDERAL_MINIMUM_AMOUNT;
            }

            @Override
            protected double getInstitutionCapitalizationMinimum() {
                return INSTITUTION_MINIMUM_AMOUNT;
            }
            
        };
        return loader;
    }
}
