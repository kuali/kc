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

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.htmlunitwebtest.AwardPaymentsAndTermsWebTest;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class tests the ApprovedEquipment panel
 */
public class AwardApprovedEquipmentWebTest extends AwardPaymentsAndTermsWebTest {
    private static final String UNFORMATTED_TOTAL = "3000.00";
    private static final String UNIQUE_ERROR_MSG = "Approved Equipment Vendor, Model and Item must be unique";
    private static final String INSTITUTIONAL_CONTEXT = "Institution";
    private static final double MINIMUM_CAPITALIZATION_AMOUNT = 50.00;
    private static final String AMOUNT_ERROR_CONTEXT = "Amount (Amount)";
    private static final String ITEM_ERROR_CONTEXT = "Item (Item)";    
    private static final String REQUIRED_FIELD_MSG = "%s is a required field.";
    private static final String INVALID_AMOUNT_MSG = "Approved Equipment Amount is less than $%s %s capitalization minimum";
    private static final String ADD_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "addApprovedEquipmentItem";     
    private static final String DELETE_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "deleteApprovedEquipmentItem";
    private static final String NEW_FIELD_PREFIX = "approvedEquipmentBean.newAwardApprovedEquipment.";
    
    private static final String AMOUNT_FIELD_NAME = "amount";
    private static final String ITEM_FIELD_NAME = "item";
    private static final String MODEL_FIELD_NAME = "model";
    private static final String VENDOR_FIELD_NAME = "vendor";
    
    private static final String NEW_ITEM_FIELD = NEW_FIELD_PREFIX + ITEM_FIELD_NAME;
    private static final String NEW_VENDOR_FIELD = NEW_FIELD_PREFIX + VENDOR_FIELD_NAME;
    private static final String NEW_MODEL_FIELD = NEW_FIELD_PREFIX + MODEL_FIELD_NAME;
    private static final String NEW_AMOUNT_FIELD = NEW_FIELD_PREFIX + AMOUNT_FIELD_NAME;
    private static final String EXISTING_ROW_FIELD_NAME = "document.awardList[0].approvedEquipmentItems[%d].%s";
    
    private static final String INVALID_AMOUNT = "1.00";

    private static final String ITEM1 = "Foo";
    private static final String MODEL1 = "100z";
    private static final String VENDOR1 = "FooVendor";
    private static final String AMOUNT1 = "1000.0";
    private static final String FORMATTED_AMOUNT1 = "1,000.00";
    
    private static final String ITEM2 = "Bar";
    private static final String MODEL2 = "200z";
    private static final String VENDOR2 = "BarVendor";
    private static final String AMOUNT2 = "2000.0";
    private static final String FORMATTED_AMOUNT2 = "2,000.00";
    
//    private static final String FORMATTED_TOTAL = "$3,000.00";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_NoErrors() throws Exception {
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_ItemRequiredError() throws Exception{
        addNewEquipmentItem(null, VENDOR1, MODEL1, AMOUNT1);
        checkAddingApprovedEquipment_CausesRequiredError(ITEM_ERROR_CONTEXT);
    }
    
    @Test
    public void testAwardApprovedEquipmentPanelAdd_AmountRequiredError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, null);
        checkAddingApprovedEquipment_CausesRequiredError(AMOUNT_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_AmountValidError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, INVALID_AMOUNT);
        checkForSoftError();
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_SameItemVendorModelCausesDuplicateError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT2);
        assertContains(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_DifferentItems_NoDuplicateError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM2, VENDOR1, MODEL1, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_DifferentVendors_NoDuplicateError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM1, VENDOR2, MODEL1, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_DifferentModels_NoDuplicateError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL2, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testEditingAwardApprovedEquipment_CausesDuplicateError() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM2, VENDOR1, MODEL1, AMOUNT2);
        saveWithErrorCheck();
        editExistingRow(0, ITEM_FIELD_NAME, ITEM1);
        saveWithoutErrorCheck();
        assertErrorTextContainedWithinSection(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG, "tab-SpecialApproval:ApprovedEquipment-div");
    }
    
    @Test
    public void testAddingAwardApprovedEquipmentPanel_ItemEditMakesRequiredError() throws Exception{
        checkEditApprovedEquipment_CausesRequiredError(ITEM_FIELD_NAME, ITEM_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingAwardApprovedEquipmentPanel_AmountEditRequiredMakesError() throws Exception{
        checkEditApprovedEquipment_CausesRequiredError(AMOUNT_FIELD_NAME, AMOUNT_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_MostRecentFirst() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM2, VENDOR2, MODEL2, AMOUNT2);
        assertEquals(FORMATTED_AMOUNT2, getFieldValue(paymentReportsAndTermsPage, String.format(EXISTING_ROW_FIELD_NAME, 0, AMOUNT_FIELD_NAME)));
        assertEquals(FORMATTED_AMOUNT1, getFieldValue(paymentReportsAndTermsPage, String.format(EXISTING_ROW_FIELD_NAME, 1, AMOUNT_FIELD_NAME)));
    }
    
    @Test
    public void testAddingAwardApprovedEquipment_TotalAmountShown() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM2, VENDOR2, MODEL2, AMOUNT2);
//        This assertion should be true, but isn't. JSTL fmt:formatNumber works when live under Jetty, but doesn't seem to work under HtmlUnit 
//        assertContains(paymentReportsAndTermsPage, FORMATTED_TOTAL);
        assertContains(paymentReportsAndTermsPage, UNFORMATTED_TOTAL);
    }

    @Test
    public void testDeletingAwardApprovedEquipment_UnsavedItem() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        deleteRow(0);
        checkForErrorMessages();
    }

    private void checkForErrorMessages() {
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
        assertDoesNotContain(paymentReportsAndTermsPage, EXCEPTION_OR_SYSTEM_ERROR);
    }
    
    @Test
    public void testDeletingAwardApprovedEquipment_FirstSavedItem() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        addNewEquipmentItem(ITEM2, VENDOR2, MODEL2, AMOUNT2);
        saveWithErrorCheck();
        deleteRow(1);
        checkForErrorMessages();
    }
    
    @Test
    public void testDeletingAwardApprovedEquipment_SavedItem() throws Exception{
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        saveWithErrorCheck();
        deleteRow(0);
        saveWithErrorCheck();
        reload();
        assertDoesNotContain(paymentReportsAndTermsPage, VENDOR1);
    }

    private void reload() throws IOException {
        paymentReportsAndTermsPage = reload(paymentReportsAndTermsPage);
        checkForErrorMessages();
    }

    private void saveWithoutErrorCheck() throws IOException {
        paymentReportsAndTermsPage = save(paymentReportsAndTermsPage);
    }
    
    private void saveWithErrorCheck() throws IOException {
        saveWithoutErrorCheck();
        checkForErrorMessages();
    }
    
    private void editExistingRow(int rowNum, String fieldName, String value) {
        setFieldValue(String.format(EXISTING_ROW_FIELD_NAME, rowNum, fieldName), value);
    }
    
    private void checkForSoftError() {
        assertContains(paymentReportsAndTermsPage, SOFT_ERRORS_FOUND_ON_PAGE);        
        String errorMsg = String.format(INVALID_AMOUNT_MSG, 
                                        getFormattedAmount(MINIMUM_CAPITALIZATION_AMOUNT), 
                                        INSTITUTIONAL_CONTEXT);
        assertContains(paymentReportsAndTermsPage, errorMsg);
    }
    
    private void checkAddingApprovedEquipment_CausesRequiredError(String errorContext) throws Exception {
        assertContains(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage, String.format(REQUIRED_FIELD_MSG, errorContext));
    }
    
    private void checkEditApprovedEquipment_CausesRequiredError(String fieldName, String errorMessageContext) throws IOException {
        addNewEquipmentItem(ITEM1, VENDOR1, MODEL1, AMOUNT1);
        editExistingRow(0, fieldName, EMPTY_STRING);
        saveWithoutErrorCheck();
        assertContains(paymentReportsAndTermsPage, String.format(REQUIRED_FIELD_MSG, errorMessageContext));
    }
    
    private void addNewEquipmentItem(String item, String vendor, String model, String amount) throws IOException {
        populateAddRowFields(item, vendor, model, amount);
        
        HtmlForm form = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String addButtonName = getImageTagName(paymentReportsAndTermsPage, ADD_BUTTON_CONTEXT);        
        HtmlImageInput addButton = (HtmlImageInput) form.getInputByName(addButtonName);
        paymentReportsAndTermsPage = (HtmlPage) addButton.click();
    }
    
    private void deleteRow(int rowNum) throws IOException {
        paymentReportsAndTermsPage = pressAButton(paymentReportsAndTermsPage, getButtonMethodCToCallName(rowNum));
        checkForErrorMessages();
    }

    private String getButtonMethodCToCallName(int rowNum) {
        return String.format(DELETE_BUTTON_CONTEXT + ".line%d", rowNum);
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