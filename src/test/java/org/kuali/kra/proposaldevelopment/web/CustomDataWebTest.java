/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CustomDataWebTest extends ProposalDevelopmentWebTestBase {


    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String CUSTOM_DATA_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.customData.x";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String LOCAL_REVIEW_DATE_ID = "customAttributeValues(id8)";
    private static final String TENURED_LABEL = "Tenured: ";
    private static final String GRADUATE_STUDENT_COUNT_LABEL = "Graduate Student Count: ";
    private static final String BILLING_ELEMENT_LABEL = "Billing Element: ";
    private static final String LOCAL_REVIEW_DATE_LABEL = "Local Review Date: ";

    @Test
    public void testCustomDataPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
        this.setDefaultRequiredFields(proposalPage);
        HtmlPage customDataPage = clickOn(proposalPage, CUSTOM_DATA_LINK_NAME);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_1);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_2);
        assertContains(customDataPage,TestUtilities.GROUP_NAME_3);
  
        setFieldValue(customDataPage, GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        // lookup 
        final HtmlPage SearchTenuredPage = clickOn(customDataPage, "Search Tenured");
        final HtmlPage personSearchResultsPage = clickOn(SearchTenuredPage, "methodToCall.search");
        HtmlAnchor hyperlink = getAnchor(personSearchResultsPage, "customAttributeValues(id5)="+TestUtilities.TENURED_VALUE);
        assertNotNull(hyperlink);
        final HtmlPage customDataPageWithTenured = clickOn(hyperlink);
        assertContains(customDataPageWithTenured,TENURED_LABEL+TestUtilities.TENURED_VALUE);
        assertContains(customDataPageWithTenured,GRADUATE_STUDENT_COUNT_LABEL+TestUtilities.GRADUATE_STUDENT_COUNT_VALUE); 
        
        // set values for a couple more fields and save
        setFieldValue(customDataPageWithTenured, BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
        setFieldValue(customDataPageWithTenured, LOCAL_REVIEW_DATE_ID, TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        HtmlPage savedCustomdataPage = clickOn(customDataPageWithTenured, "methodToCall.save", "Kuali :: Proposal Development Document");

        assertContains(savedCustomdataPage, "Document was successfully saved.");
        assertContains(savedCustomdataPage,TENURED_LABEL+TestUtilities.TENURED_VALUE);
        assertContains(savedCustomdataPage,GRADUATE_STUDENT_COUNT_LABEL+TestUtilities.GRADUATE_STUDENT_COUNT_VALUE); 
        assertContains(savedCustomdataPage,BILLING_ELEMENT_LABEL+TestUtilities.BILLING_ELEMENT_VALUE);
        assertContains(savedCustomdataPage,LOCAL_REVIEW_DATE_LABEL+TestUtilities.LOCAL_REVIEW_DATE_VALUE); 
       
        // verify DB
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        assertNotNull(doc);

        //verifySavedRequiredFields(doc, DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_OWNED_BY_UNIT, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "2007-08-14", "2007-08-21", DEFAULT_PROPOSAL_TYPE_CODE);
        assertEquals(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE, doc.getCustomAttributeDocuments("4").getCustomAttribute().getValue());
        assertEquals(TestUtilities.TENURED_VALUE, doc.getCustomAttributeDocuments("5").getCustomAttribute().getValue());
        assertEquals(TestUtilities.LOCAL_REVIEW_DATE_VALUE, doc.getCustomAttributeDocuments("8").getCustomAttribute().getValue());
        assertEquals(TestUtilities.BILLING_ELEMENT_VALUE, doc.getCustomAttributeDocuments("1").getCustomAttribute().getValue());


    }
}
