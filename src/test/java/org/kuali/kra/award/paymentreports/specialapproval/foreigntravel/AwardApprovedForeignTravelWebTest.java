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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

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
public class AwardApprovedForeignTravelWebTest extends AwardPaymentsAndTermsWebTest {
    private static final String UNIQUE_ERROR_MSG = "A row duplicates another";
    private static final String TRAVELER_NAME_ERROR_CONTEXT = "Traveler Name";    
    private static final String DESTINATION_ERROR_CONTEXT = "Destination";
    private static final String START_DATE_ERROR_CONTEXT = "Start Date";
    private static final String END_DATE_ERROR_CONTEXT = "End Date";
    private static final String AMOUNT_ERROR_CONTEXT = "Amount";
    private static final String INVALID_FIELD_MSG = "Please enter a valid %s";
    private static final String DD_REQUIRED_FIELD_MSG = "%s (%s) is a required field.";
    private static final String ADD_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "addApprovedForeignTravel";     
    private static final String DELETE_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "deleteApprovedForeignTravelTrip";
    private static final String NEW_FIELD_PREFIX = "approvedForeignTravelBean.newApprovedForeignTravel.";
    
    private static final String TRAVELER_NAME_FIELD_NAME = "travelerName";
    private static final String DESTINATION_FIELD_NAME = "destination";
    private static final String START_DATE_FIELD_NAME = "startDate";
    private static final String END_DATE_FIELD_NAME = "endDate";
    private static final String AMOUNT_FIELD_NAME = "amount";
    
    private static final String NEW_TRAVELER_FIELD = NEW_FIELD_PREFIX + TRAVELER_NAME_FIELD_NAME;
    private static final String NEW_DESTINATION_FIELD = NEW_FIELD_PREFIX + DESTINATION_FIELD_NAME;
    private static final String NEW_START_DATE_FIELD = NEW_FIELD_PREFIX + START_DATE_FIELD_NAME;
    private static final String NEW_END_DATE_FIELD = NEW_FIELD_PREFIX + END_DATE_FIELD_NAME;
    private static final String NEW_AMOUNT_FIELD = NEW_FIELD_PREFIX + AMOUNT_FIELD_NAME;
    private static final String EXISTING_ROW_FIELD_NAME = "document.awardList[0].approvedForeignTravelTrips[%d].%s";
    
    private static final String INVALID_AMOUNT = "-1.00";

    private static final String PERSON_ID_FIELD = "personId";
    private static final String TRAVELER1 = "Joe Tester";    
    private static final String TRAVELER1_ID_VALUE = "000000008";
    private static final String DESTINATION1 = "Tokyo, Japan";
    private static final String START_DATE1 = "05/01/2009";
    private static final String END_DATE1 = "05/10/2009";
    private static final String AMOUNT1 = "3000.00";
    
    private static final String TRAVELER2 = "Lora OConnor";
    private static final String TRAVELER2_ID_VALUE = "000000004";
    private static final String DESTINATION2 = "Berlin, Germany";
    private static final String START_DATE2 = "06/01/2009";
    private static final String END_DATE2 = "06/15/2009";
    private static final String AMOUNT2 = "4000.00";
    
    private static final String UNFORMATTED_TOTAL = "7000.00";
    
//    private static final String FORMATTED_TOTAL = "$7,000.00";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testAddingApprovedForeignTravel_NoErrors() throws Exception {
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_TravelerRequiredError() throws Exception{
        addNewForeignTravel(null, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        checkAddingApprovedForeignTravel_CausesRequiredError(TRAVELER_NAME_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_AmountRequiredError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, null);
        checkAddingApprovedForeignTravel_CausesRequiredError(AMOUNT_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_AmountValidError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, INVALID_AMOUNT);
        checkAddingApprovedForeignTravel_CausesRequiredError(AMOUNT_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_SameTravelerDestinationStatDateCausesDuplicateError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE2, AMOUNT2);
        assertContains(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_DifferentTravelers_NoDuplicateError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER2, DESTINATION1, START_DATE1, END_DATE2, AMOUNT2);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_DifferentDestination_NoDuplicateError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER1, DESTINATION2, START_DATE1, END_DATE2, AMOUNT2);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testAddingApprovedForeignTravel_DifferentStartDate_NoDuplicateError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE2, END_DATE1, AMOUNT1);
        assertDoesNotContain(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testEditingAwardApprovedForeignTravel_CausesDuplicateError() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER1, DESTINATION2, START_DATE1, END_DATE2, AMOUNT2);
        save();        
        editExistingRow(1, DESTINATION_FIELD_NAME, DESTINATION1);
        save();
        assertContains(paymentReportsAndTermsPage, UNIQUE_ERROR_MSG);
    }
    
    @Test
    public void testEditingAwardApprovedForeignTravel_DestinationEditMakesRequiredError() throws Exception{
        checkEditApprovedForeignTravel_CausesRequiredError(DESTINATION_ERROR_CONTEXT, DESTINATION_FIELD_NAME, DESTINATION_ERROR_CONTEXT);
    }
    
    @Test
    public void testEditingAwardApprovedForeignTravel_StartDateEditMakesRequiredError() throws Exception{
        checkEditApprovedForeignTravel_CausesRequiredError(START_DATE_ERROR_CONTEXT, START_DATE_FIELD_NAME, START_DATE_ERROR_CONTEXT);
    }
    
    @Test
    public void testEditingAwardApprovedForeignTravel_EndDateEditDoesNotMakeRequiredError() throws Exception{
        checkEditApprovedForeignTravel_DoesNotCauseRequiredError(END_DATE_ERROR_CONTEXT, END_DATE_FIELD_NAME, END_DATE_ERROR_CONTEXT);
    }
    
    @Test
    public void testEditingAwardApprovedForeignTravel_AmountEditRequiredMakesError() throws Exception{
        checkEditApprovedForeignTravel_CausesRequiredError(AMOUNT_ERROR_CONTEXT, AMOUNT_FIELD_NAME, AMOUNT_ERROR_CONTEXT);
    }
    
    @Test
    public void testAddingAwardApprovedForeignTravel_MostRecentFirst() throws Exception {
        addNewForeignTravel(TRAVELER2, DESTINATION2, START_DATE2, END_DATE2, AMOUNT2);
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        save();
        assertEquals(START_DATE2, getFieldValue(paymentReportsAndTermsPage, String.format(EXISTING_ROW_FIELD_NAME, 0, START_DATE_FIELD_NAME)));
        assertEquals(START_DATE1, getFieldValue(paymentReportsAndTermsPage, String.format(EXISTING_ROW_FIELD_NAME, 1, START_DATE_FIELD_NAME)));
    }
    
    @Test
    public void testAddingAwardApprovedForeignTravel_TotalAmountShown() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER2, DESTINATION2, START_DATE2, END_DATE2, AMOUNT2);
//        This assertion should be true, but isn't. JSTL fmt:formatNumber works when live under Jetty, but doesn't seem to work under HtmlUnit 
//        assertContains(paymentReportsAndTermsPage, FORMATTED_TOTAL);
        assertContains(paymentReportsAndTermsPage, UNFORMATTED_TOTAL);
    }

    @Test
    public void testDeletingAwardApprovedForeignTravel_UnsavedItem() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        deleteRow(0);
        checkForErrorMessages();
    }
    
    @Test
    public void testDeletingAwardApprovedForeignTravel_FirstSavedItem() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        addNewForeignTravel(TRAVELER2, DESTINATION2, START_DATE2, END_DATE2, AMOUNT2);
        save();
        deleteRow(0);
        checkForErrorMessages();
    }
    
    @Test
    public void testDeletingApprovedForeignTravel_SavedItem() throws Exception{
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        save();
        deleteRow(0);
        checkForErrorMessages();
        save();
        reload();
        assertDoesNotContain(paymentReportsAndTermsPage, TRAVELER1);
    }

    private void reload() throws IOException {
        paymentReportsAndTermsPage = reload(paymentReportsAndTermsPage);
    }

    private void save() throws IOException {
        paymentReportsAndTermsPage = save(paymentReportsAndTermsPage);
    }
    
    private void editExistingRow(int rowNum, String fieldName, String value) throws IOException {
        if(TRAVELER_NAME_FIELD_NAME.equals(fieldName)) {
            if(value != null) {
                String lookupValue = TRAVELER1.equals(value) ? TRAVELER1_ID_VALUE : TRAVELER2_ID_VALUE;
                paymentReportsAndTermsPage = lookup(paymentReportsAndTermsPage, String.format("document.awardList[0].approvedForeignTravelTrips[%d].travelerId", rowNum), PERSON_ID_FIELD, lookupValue);            
            }
        } else {
            setFieldValue(String.format(EXISTING_ROW_FIELD_NAME, rowNum, fieldName), value);
        }
    }
    
    private void checkAddingApprovedForeignTravel_CausesRequiredError(String errorContext) throws Exception {
        assertContains(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage, String.format(INVALID_FIELD_MSG, errorContext));
    }
    
    private void checkEditApprovedForeignTravel_CausesRequiredError(String fieldLabel, String fieldName, String errorMessageContext) throws IOException {
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        save();
        editExistingRow(0, fieldName, EMPTY_STRING);
        save();
        assertContains(paymentReportsAndTermsPage, String.format(DD_REQUIRED_FIELD_MSG, fieldLabel, fieldLabel));
    }

    private void checkEditApprovedForeignTravel_DoesNotCauseRequiredError(String fieldLabel, String fieldName, String errorMessageContext) throws IOException {
        addNewForeignTravel(TRAVELER1, DESTINATION1, START_DATE1, END_DATE1, AMOUNT1);
        save();
        editExistingRow(0, fieldName, EMPTY_STRING);
        save();
        assertDoesNotContain(paymentReportsAndTermsPage, String.format(DD_REQUIRED_FIELD_MSG, fieldLabel, fieldLabel));
    }
    
    private void checkForErrorMessages() {
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
        assertDoesNotContain(paymentReportsAndTermsPage, EXCEPTION_OR_SYSTEM_ERROR);
    }
    
    private void addNewForeignTravel(String travelerName, String destination, String startDate, String endDate, String amount) throws IOException {
        if(travelerName != null) {
            String lookupValue = TRAVELER1.equals(travelerName) ? TRAVELER1_ID_VALUE : TRAVELER2_ID_VALUE;
            paymentReportsAndTermsPage = lookup(paymentReportsAndTermsPage, "approvedForeignTravelBean.newApprovedForeignTravel.travelerId", PERSON_ID_FIELD, lookupValue);            
        }
        populateAddRowFields(destination, startDate, endDate, amount);
        HtmlForm form = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String addButtonName = getImageTagName(paymentReportsAndTermsPage, ADD_BUTTON_CONTEXT);        
        HtmlImageInput addButton = (HtmlImageInput) form.getInputByName(addButtonName);
        paymentReportsAndTermsPage = (HtmlPage) addButton.click();
    }
    
    private void deleteRow(int rowNum) throws IOException {
        paymentReportsAndTermsPage = pressAButton(paymentReportsAndTermsPage, getDeleteButtonMethodToCallName(rowNum));
    }

    private String getDeleteButtonMethodToCallName(int rowNum) {
        return String.format(DELETE_BUTTON_CONTEXT + ".line%d", rowNum);
    }
    
    private void populateAddRowFields(String destination, String startDate, String endDate, String amount) {
        setFieldValue(NEW_DESTINATION_FIELD, destination);
        setFieldValue(NEW_START_DATE_FIELD, startDate);
        setFieldValue(NEW_END_DATE_FIELD, endDate);
        setFieldValue(NEW_AMOUNT_FIELD, amount);
    }
    
    private void setFieldValue(String fieldName, String fieldValue) {
        if(fieldValue != null) {
            setFieldValue(paymentReportsAndTermsPage, fieldName, fieldValue);
        }
    }
}