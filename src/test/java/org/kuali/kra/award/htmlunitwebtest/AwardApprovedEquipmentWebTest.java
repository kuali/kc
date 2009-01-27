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
package org.kuali.kra.award.htmlunitwebtest;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class tests the ApprovedEquipment panel
 */
public class AwardApprovedEquipmentWebTest extends AwardPaymentsAndTermsWebTest {
    private static final String UNIQUE_ERROR_MSG = "Approved Equipment Vendor, Model and Item must be unique";
    private static final String INSTITUTIONAL_CONTEXT = "Institution";
    private static final double MINIMUM_CAPITALIZATION_AMOUNT = 50.00;
    private static final String AMOUNT_ERROR_CONTEXT = "Amount";
    private static final String ITEM_ERROR_CONTEXT = "Item";    
    private static final String REQUIRED_FIELD_MSG = "%s is a required field.";
    private static final String INVALID_AMOUNT_MSG = "Approved Equipment Amount is less than $%s %s capitalization minimum";
    private static final String BUTTON_NAME_PREFIX = "methodToCall.";
    private static final String ADD_BUTTON_CONTEXT = BUTTON_NAME_PREFIX + "addApprovedEquipmentItem";     
    private static final String NEW_FIELD_PREFIX = "approvedEquipmentFormHelper.newAwardApprovedEquipment.";
    private static final String NEW_ITEM_FIELD = NEW_FIELD_PREFIX + "item";
    private static final String NEW_VENDOR_FIELD = NEW_FIELD_PREFIX + "vendor";
    private static final String NEW_MODEL_FIELD = NEW_FIELD_PREFIX + "model";
    private static final String NEW_AMOUNT_FIELD = NEW_FIELD_PREFIX + "amount";
    private static final String AMOUNT1 = "1000.0";
    private static final String AMOUNT2 = "2000.0";
    private static final String INVALID_AMOUNT = "1.00";
    private static final String MODEL1 = "100z";
    private static final String VENDOR1 = "FooVendor";
    private static final String ITEM1 = "Foo";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_NoErrors() throws Exception{
        populateAddRowFields(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        assertDoesNotContain(addNewEquipmentItem(), ERRORS_FOUND_ON_PAGE);
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_ItemRequiredError() throws Exception{
        populateAddRowFields(null, VENDOR1, MODEL1, AMOUNT1);
        HtmlPage pageAfterAdd = addNewEquipmentItem();
        assertContains(pageAfterAdd, ERRORS_FOUND_ON_PAGE);
        assertContains(pageAfterAdd, String.format(REQUIRED_FIELD_MSG, ITEM_ERROR_CONTEXT));
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_AmountRequiredError() throws Exception{
        populateAddRowFields(ITEM1, VENDOR1, MODEL1, null);
        HtmlPage pageAfterAdd = addNewEquipmentItem();
        assertContains(pageAfterAdd, ERRORS_FOUND_ON_PAGE);
        assertContains(pageAfterAdd, String.format(REQUIRED_FIELD_MSG, AMOUNT_ERROR_CONTEXT));
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_AmountValidError() throws Exception{
        populateAddRowFields(ITEM1, VENDOR1, MODEL1, INVALID_AMOUNT);
        HtmlPage pageAfterAdd = addNewEquipmentItem();
        assertContains(pageAfterAdd, SOFT_ERRORS_FOUND_ON_PAGE);        
        String errorMsg = String.format(INVALID_AMOUNT_MSG, 
                                        getFormattedAmount(MINIMUM_CAPITALIZATION_AMOUNT), 
                                        INSTITUTIONAL_CONTEXT);
        assertContains(pageAfterAdd, errorMsg);
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_DuplicateError() throws Exception{
        populateAddRowFields(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        HtmlPage pageAfterAdd = addNewEquipmentItem();
        
        populateAddRowFields(ITEM1, VENDOR1, MODEL1, AMOUNT2);
        pageAfterAdd = addNewEquipmentItem();
System.out.println(pageAfterAdd.asXml());                
//        assertContains(pageAfterAdd, UNIQUE_ERROR_MSG);
    }

    private HtmlPage addNewEquipmentItem() throws IOException {
        HtmlForm form = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String addButtonName = getImageTagName(paymentReportsAndTermsPage, ADD_BUTTON_CONTEXT);        
        HtmlImageInput addButton = (HtmlImageInput) form.getInputByName(addButtonName);
        HtmlPage pageAfterAdd = (HtmlPage) addButton.click();
        return pageAfterAdd;
    }
    
    private String getFormattedAmount(double amount) {
        return String.format("%12.2f", amount).trim();
    }
    
    private void populateAddRowFields(String item, String vendor, String model, String amount) {
        setFieldValue(NEW_ITEM_FIELD, item);
        setFieldValue(NEW_VENDOR_FIELD, vendor);
        setFieldValue(NEW_MODEL_FIELD, model);
        setFieldValue(NEW_AMOUNT_FIELD, amount);
    }
    
    private void setFieldValue(String fieldName, String fieldValue) {
        if(fieldValue != null) {
            setFieldValue(paymentReportsAndTermsPage, fieldName, fieldValue);
        }
    }
}