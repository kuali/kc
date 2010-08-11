/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.kra.HtmlUnitUtil;
import org.kuali.kra.infrastructure.Constants;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class ProtocolSpecialReviewWebTest extends ProtocolWebTestBase {

    private static final String TABLE_ID = "specialReviewTableId";
    
    private static final String SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_CODE = "1";
    private static final String SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_NAME = "Human Subjects";
    
    private static final String APPROVAL_TYPE_APPROVED_CODE = "2";
    private static final String APPROVAL_TYPE_APPROVED_NAME = "Approved";
    private static final String APPROVAL_TYPE_EXEMPT_CODE = "4";
    private static final String APPROVAL_TYPE_EXEMPT_NAME = "Exempt";
    
    private static final String PROTOCOL_NUMBER = "0906000001";
    private static final String APPLICATION_DATE = "01/01/2009";
    private static final String APPROVAL_DATE = "02/01/2009";
    private static final String EXPIRATION_DATE = "03/01/2009";
    private static final String COMMENTS = "This is a test";
    
    private static final String EXEMPTION_TYPE_E1_CODE = "1";
    private static final String EXEMPTION_TYPE_E1_NAME = "E1";
    
    private static final String SPECIAL_REVIEW_CODE_FIELD = "specialReviewHelper.newSpecialReview.specialReviewCode";
    private static final String APPROVAL_TYPE_CODE_FIELD = "specialReviewHelper.newSpecialReview.approvalTypeCode";
    private static final String PROTOCOL_NUMBER_FIELD = "specialReviewHelper.newSpecialReview.protocolNumber";
    private static final String APPLICATION_DATE_FIELD = "specialReviewHelper.newSpecialReview.applicationDate";
    private static final String APPROVAL_DATE_FIELD = "specialReviewHelper.newSpecialReview.approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "specialReviewHelper.newSpecialReview.expirationDate";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "specialReviewHelper.newSpecialReview.exemptionTypeCodes";
    private static final String COMMENTS_FIELD = "specialReviewHelper.newSpecialReview.comments";
    
    private static final String METHODTOCALL_ADDSPECIALREVIEW = "methodToCall.addSpecialReview.anchorSpecialReview";
    private static final String METHODCOCALL_DELETESPECIALREVIEW = "methodToCall.deleteSpecialReview.line0.anchor0";
    
    private class Review {
        String type;
        String approvalStatus;
        String protocolNumber;
        String applicationDate;
        String approvalDate;
        String expirationDate;
        List<String> exemptionTypeCodes;
        String comments;
        
        public Review(String type, String approvalStatus, String protocolNumber, String applicationDate, String approvalDate, String expirationDate, 
                      List<String> exemptionTypeCodes, String comments) {
            this.type = type;
            this.approvalStatus = approvalStatus;
            this.protocolNumber = protocolNumber;
            this.applicationDate = applicationDate;
            this.approvalDate = approvalDate;
            this.expirationDate = expirationDate;
            this.exemptionTypeCodes = exemptionTypeCodes;
            this.comments = comments;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
        
        @Override
        public int hashCode() {
            return 1;
        }
    }

    /**
     * Verify that we can successfully add an approved special review and then verify that it is indeed saved to the database.
     */
    @Test
    public void testAddApprovedSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        setApprovedSpecialReviewFields(specialReviewPage);
        specialReviewPage = clickOn(specialReviewPage, METHODTOCALL_ADDSPECIALREVIEW);
        assertTrue(!hasError(specialReviewPage));
        
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(
                new Review(SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_NAME, APPROVAL_TYPE_APPROVED_NAME, PROTOCOL_NUMBER, APPLICATION_DATE, APPROVAL_DATE, 
                           EXPIRATION_DATE, Collections.<String>emptyList(), COMMENTS));
        checkTable(specialReviewPage, reviews, 3);
        
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        checkTable(specialReviewPage, reviews, 3);
    }
    
    /**
     * Verify that we can successfully add an exempt special review and then verify that it is indeed saved to the database.
     */
    @Test
    public void testAddExemptSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        setExemptSpecialReviewFields(specialReviewPage);
        specialReviewPage = clickOn(specialReviewPage, METHODTOCALL_ADDSPECIALREVIEW);
        assertTrue(!hasError(specialReviewPage));
        
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(
                new Review(SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_NAME, APPROVAL_TYPE_EXEMPT_NAME, Constants.EMPTY_STRING, APPLICATION_DATE, Constants.EMPTY_STRING, 
                           EXPIRATION_DATE, Collections.singletonList(EXEMPTION_TYPE_E1_NAME), COMMENTS));
        checkTable(specialReviewPage, reviews, 3);
        
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        checkTable(specialReviewPage, reviews, 3);
    }
    
    /**
     * Verify that we can delete a special review entry and that the
     * change is reflected in the database.
     */
    @Test
    public void testDeleteSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        setApprovedSpecialReviewFields(specialReviewPage);
        specialReviewPage = clickOn(specialReviewPage, METHODTOCALL_ADDSPECIALREVIEW);
        assertTrue(!hasError(specialReviewPage));
        
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        specialReviewPage = clickOn(specialReviewPage, METHODCOCALL_DELETESPECIALREVIEW);
        List<Review> reviews = new ArrayList<Review>();
        checkTable(specialReviewPage, reviews, 2);
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        checkTable(specialReviewPage, reviews, 2);
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        checkTable(specialReviewPage, reviews, 2);
    }
    
    /**
     * Try adding a special review without setting the type and approval status.  We
     * should get back two errors.
     */
    @Test
    public void testAddErrorSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        specialReviewPage = clickOn(specialReviewPage, METHODTOCALL_ADDSPECIALREVIEW);
        assertTrue(hasError(specialReviewPage));
        assertTrue(this.getErrors(specialReviewPage, "tab-SpecialReview-div").size() == 2);
    }
    
    private void setApprovedSpecialReviewFields(HtmlPage page) {
        setFieldValue(page, SPECIAL_REVIEW_CODE_FIELD, SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_CODE);
        setFieldValue(page, APPROVAL_TYPE_CODE_FIELD, APPROVAL_TYPE_APPROVED_CODE);
        setFieldValue(page, PROTOCOL_NUMBER_FIELD, PROTOCOL_NUMBER);
        setFieldValue(page, APPLICATION_DATE_FIELD, APPLICATION_DATE);
        setFieldValue(page, APPROVAL_DATE_FIELD, APPROVAL_DATE);
        setFieldValue(page, EXPIRATION_DATE_FIELD, EXPIRATION_DATE);
        setFieldValue(page, COMMENTS_FIELD, COMMENTS);
    }
    
    private void setExemptSpecialReviewFields(HtmlPage page) {
        setFieldValue(page, SPECIAL_REVIEW_CODE_FIELD, SPECIAL_REVIEW_TYPE_HUMAN_SUBJECTS_CODE);
        setFieldValue(page, APPROVAL_TYPE_CODE_FIELD, APPROVAL_TYPE_EXEMPT_CODE);
        setFieldValue(page, APPLICATION_DATE_FIELD, APPLICATION_DATE);
        setFieldValue(page, EXPIRATION_DATE_FIELD, EXPIRATION_DATE);
        setFieldValue(page, EXEMPTION_TYPE_CODE_FIELD, EXEMPTION_TYPE_E1_CODE);
        setFieldValue(page, COMMENTS_FIELD, COMMENTS);
    }
    
    private void checkTable(HtmlPage page, List<Review> reviews, int startRow) {
        HtmlTable userTable = getTable(page, TABLE_ID);
        int rowCount = userTable.getRowCount();
        
        if (reviews.isEmpty()) {
            // The row count includes just the 0-based start row.
            // There is an adjustment of + 1 to startRow since there are no reviews.
            String message = "rowcount: " + rowCount + ", startRow: " + (startRow + 1); 
            assertTrue(message, rowCount == startRow + 1);
        } else {
            // The row count includes the 0-based start row and the two rows for each review.
            // There is no adjustment to startRow since it should start on the first line of the first review.
            String message = "rowcount: " + rowCount + ", reviews.size() :" + reviews.size() + ", startRow: " + (startRow + 1); 
            assertTrue(message, rowCount == startRow + (reviews.size() * 2));
        
            // Check each two successive rows for correct values.
            int reviewsIndex = 0;
            for (int rowIndex = startRow; rowIndex < rowCount; rowIndex++) {
                HtmlTableRow row = userTable.getRow(rowIndex);
                HtmlTableRow row2 = userTable.getRow(++rowIndex);
                Review review = reviews.get(reviewsIndex);
                
                assertCellValue(row, 0, Integer.toString(reviewsIndex + 1));
                assertCellValue(row, 1, review.type);
                assertCellValue(row, 2, review.approvalStatus);
                assertCellValue(row, 3, review.protocolNumber);
                assertCellValue(row, 4, review.applicationDate);
                assertCellValue(row, 5, review.approvalDate);
                assertCellValue(row, 6, review.expirationDate);
                assertSelectedValues(row, 7, review.exemptionTypeCodes);
                assertCellValue(row2, 1, review.comments);
                reviewsIndex++;
            }
        }
    }
        
    private void assertCellValue(HtmlTableRow row, int cellIndex, String expectedValue) {
        HtmlTableCell cell = row.getCell(cellIndex);
        String value = cell.asText().trim();
        assertTrue("Expected: " + expectedValue + "; Found: " + value, StringUtils.equals(value, expectedValue));
    }
    
    private void assertSelectedValues(HtmlTableRow row, int cellIndex, List<String> expectedValues) {
        HtmlTableCell cell = row.getCell(cellIndex);
        HtmlSelect element 
            = (HtmlSelect) HtmlUnitUtil.getElementByName((HtmlPage) cell.getPage(), "document.protocolList[0].specialReview[0].exemptionTypeCodes");
        List<HtmlOption> selectedOptions = element.getSelectedOptions();
        for (String expectedValue : expectedValues) {
            assertSelected(selectedOptions, expectedValue);
        }
        
    }

    private void assertSelected(List<HtmlOption> selectedOptions, String expectedValue) {
        boolean found = false;
        
        for (HtmlOption selectedOption : selectedOptions) {
            String elementValue = selectedOption.asText();
            if (elementValue.equals(expectedValue)) {
                found = true;
                break;
            }
        }
        
        assertTrue(expectedValue + " was not selected", found);
    }
}
