/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class ProtocolSpecialReviewWebTest extends ProtocolWebTestBase {

    private static final String TABLE_ID = "specialReviewTableId";
    
    private class Review {
        String type;
        String approvalStatus;
        String protocolNumber;
        String applicationDate;
        String approvalDate;
        String expirationDate;
        String exemptNumber;
        String comments;
        
        public Review(String type, String approvalStatus, String protocolNumber, String applicationDate, String approvalDate,
                String expirationDate, String exemptNumber, String comments) {
            this.type = type;
            this.approvalStatus = approvalStatus;
            this.protocolNumber = protocolNumber;
            this.applicationDate = applicationDate;
            this.approvalDate = approvalDate;
            this.expirationDate = expirationDate;
            this.exemptNumber = exemptNumber;
            this.comments = comments;
        }

        public boolean equals(Object obj) {
            return true;
        }
    }

    /**
     * Verify that we can successfully add a special review and then verify that
     * it is indeed saved to the database.
     */
    @Test
    public void testAddSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        setSpecialReviewFields(specialReviewPage);
        specialReviewPage = clickOn(specialReviewPage, "methodToCall.addSpecialReview.anchorSpecialReview");
        assertTrue(!hasError(specialReviewPage));
        
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(new Review("Human Subjects", "Pending", "33", "01/01/2009", "02/01/2009", "03/01/2009", "1", "this is a test"));
        checkTable(specialReviewPage, reviews, 2);
        
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        checkTable(specialReviewPage, reviews, 2);
    }
    
    /**
     * Verify that we can delete a special review entry and that the
     * change is reflected in the database.
     */
    @Test
    public void testDeleteSpecialReview() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        setSpecialReviewFields(specialReviewPage);
        specialReviewPage = clickOn(specialReviewPage, "methodToCall.addSpecialReview.anchorSpecialReview");
        assertTrue(!hasError(specialReviewPage));
        
        protocolPage = saveAndSearchDoc(specialReviewPage);
        specialReviewPage = clickOnTab(protocolPage, SPECIAL_REVIEW_LINK_NAME);
        
        specialReviewPage = clickOn(specialReviewPage, "methodToCall.deleteSpecialReview.line0.anchor0");
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
        specialReviewPage = clickOn(specialReviewPage, "methodToCall.addSpecialReview.anchorSpecialReview");
        assertTrue(hasError(specialReviewPage));
        assertTrue(this.getErrors(specialReviewPage, "tab-SpecialReview-div").size() == 2);
    }
    
    private void setSpecialReviewFields(HtmlPage page) {
        setFieldValue(page, "specialReviewHelper.newSpecialReview.specialReviewCode", "1");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.approvalTypeCode", "1");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.protocolNumber", "33");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.applicationDate", "01/01/2009");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.approvalDate", "02/01/2009");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.expirationDate", "03/01/2009");
        setFieldValue(page, "specialReviewHelper.newExemptionTypeCodes", "1");
        setFieldValue(page, "specialReviewHelper.newSpecialReview.comments", "this is a test");
    }
    
    private void checkTable(HtmlPage page, List<Review> reviews, int startRow) {
        HtmlTable userTable = getTable(page, TABLE_ID);
        int rowCount = userTable.getRowCount();
        assertTrue(rowCount == (startRow + reviews.size()));
        
        for (int rowIndex = startRow; rowIndex < rowCount; rowIndex++) {
            HtmlTableRow row = userTable.getRow(rowIndex);
            int index = rowIndex - startRow;
            Review review = reviews.get(index);
            
            assertCellValue(row, 0, Integer.toString(index + 1));
            assertCellValue(row, 1, review.type);
            assertCellValue(row, 2, review.approvalStatus);
            assertCellValue(row, 3, review.protocolNumber);
            assertCellValue(row, 4, review.applicationDate);
            assertCellValue(row, 5, review.approvalDate);
            assertCellValue(row, 6, review.expirationDate);
            assertSelectedValue(row, 7, review.exemptNumber);
            assertCellValue(row, 8, review.comments);
        }
    }
        
    private void assertCellValue(HtmlTableRow row, int cellIndex, String expectedValue) {
        HtmlTableCell cell = row.getCell(cellIndex);
        String value = cell.asText().trim();
        assertTrue("Expected: " + expectedValue + "; Found: " + value, StringUtils.equals(value, expectedValue));
    }
    
    private void assertSelectedValue(HtmlTableRow row, int cellIndex, String expectedValue) {
        HtmlTableCell cell = row.getCell(cellIndex);
        HtmlElement element = getElementByValue(cell, expectedValue);
        String selected = element.getAttributeValue("selected");
        assertTrue(expectedValue + " was not selected", selected != null && selected.equals("selected"));
    }
    
    @SuppressWarnings("unchecked")
    private HtmlElement getElementByValue(HtmlElement element, String value) {
        Iterator<HtmlElement> iterator = element.getAllHtmlChildElements();
        while (iterator.hasNext()) {
            HtmlElement e = iterator.next();
            String elementValue = e.getAttributeValue("value");
            if (elementValue.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
