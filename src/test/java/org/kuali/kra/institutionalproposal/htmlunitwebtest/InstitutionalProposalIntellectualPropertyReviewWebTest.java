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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerTestUnitTestData(
        @UnitTestData(order = { 
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
        sqlStatements = {
                @UnitTestSql("delete from IP_REVIEW_ACTIVITY_TYPE"),
                @UnitTestSql("delete from IP_REVIEW_REQ_TYPE"),
                @UnitTestSql("delete from IP_REVIEW_RESULT_TYPE")
                }, 
        sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_IP_REVIEW_ACTIVITY_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_IP_REVIEW_REQ_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_IP_REVIEW_RESULT_TYPE.sql", delimiter = ";")
                })
        )
public class InstitutionalProposalIntellectualPropertyReviewWebTest extends InstitutionalProposalWebTestBase {
    
    private static final String IP_REVIEW_TAB_NAME = "intellectualPropertyReview.x";
    private static final String EDIT_IP_REVIEW_BUTTON = "methodToCall.editIntellectualPropertyReview";
    
    /**
     * The set up method calls the parent super method and gets the 
     * Institutional Proposal Home page after that.
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.InstitutionalProposalWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    /**
     * This method calls parent tear down method and than sets institutionalproposalHomePage to null
     * @see org.kuali.kra.institutionalproposal.htmlunitwebtest.institutionalproposalWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Tests creating a new maintenance document to enter the initial IP record.
     * @throws Exception
     */
    @Test
    public void testCreateAndEditIpReviewRecord() throws Exception {
        
        // Add the record
        HtmlPage ipReviewPage = getIpReviewPage();
        String docNumber = getDocNbr(ipReviewPage);
        HtmlElement ipReviewBtn = getElementByName(ipReviewPage, EDIT_IP_REVIEW_BUTTON, true);
        HtmlPage ipReviewMaintenancePage = clickOn(ipReviewBtn);
        assertTrue("Kuali :: Institutional Proposal Review".equals(ipReviewMaintenancePage.getTitleText()));
        setFieldValue(ipReviewMaintenancePage, "document.documentHeader.documentDescription", "New Record");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.reviewSubmissionDate", "07/07/2009");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.reviewReceiveDate", "07/08/2009");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.ipReviewer", "000000008");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.ipReviewRequirementTypeCode", "1");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.reviewResultCode", "1");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.generalComments", "My General Comment");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.reviewerComments", "My Reviewer Comment");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.activityNumber", "1");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.ipReviewActivityTypeCode", "1");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.activityDate", "07/09/2009");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.comments", "comment");
        HtmlElement addBtn = getElementByName(ipReviewMaintenancePage, 
                "methodToCall.addLine.ipReviewActivities", true);
        ipReviewMaintenancePage = clickOn(addBtn);
        //ipReviewMaintenancePage = addIpReviewActivity(ipReviewMaintenancePage, "1", "My First Review Activity Comment");
        //ipReviewMaintenancePage = addIpReviewActivity(ipReviewMaintenancePage, "2", "My Second Review Activity Comment");
        HtmlElement blanketApproveBtn = getElementByName(ipReviewMaintenancePage, "methodToCall.blanketApprove", true);
        clickOn(blanketApproveBtn);
        
        // Verify the record
        HtmlPage proposalHomePage = docSearch(docNumber);
        ipReviewPage = clickOnTab(proposalHomePage, IP_REVIEW_TAB_NAME);
        assertContains(ipReviewPage, "Document was successfully saved");
        assertContains(ipReviewPage, "07/07/2009");
        assertContains(ipReviewPage, "Expedited");
        assertContains(ipReviewPage, "07/08/2009");
        assertContains(ipReviewPage, "QQQ");
        assertContains(ipReviewPage, "Joe Tester");
        assertContains(ipReviewPage, "My General Comment");
        assertContains(ipReviewPage, "My Reviewer Comment");
        //assertContains(ipReviewPage, "Conference Call");
        //assertContains(ipReviewPage, "07/09/2009");
        //assertContains(ipReviewPage, "My First Review Activity Comment");
        //assertContains(ipReviewPage, "My Second Review Activity Comment");
        
        // Edit the record
        ipReviewBtn = getElementByName(ipReviewPage, EDIT_IP_REVIEW_BUTTON, true);
        ipReviewMaintenancePage = clickOn(ipReviewBtn);
        assertTrue("Kuali :: Institutional Proposal Review".equals(ipReviewMaintenancePage.getTitleText()));
        setFieldValue(ipReviewMaintenancePage, "document.documentHeader.documentDescription", "Edit Record");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.generalComments", "General Comment 2");
        //HtmlElement deleteBtn = getElementByName(ipReviewPage, EDIT_IP_REVIEW_BUTTON, true);
        //ipReviewMaintenancePage = clickOn(deleteBtn);
        blanketApproveBtn = getElementByName(ipReviewMaintenancePage, "methodToCall.blanketApprove", true);
        clickOn(blanketApproveBtn);
        
        // Verify the edit
        proposalHomePage = docSearch(docNumber);
        ipReviewPage = clickOnTab(proposalHomePage, IP_REVIEW_TAB_NAME);
        assertContains(ipReviewPage, "Document was successfully saved");
        assertContains(ipReviewPage, "General Comment 2");
        assertDoesNotContain(ipReviewPage, "My First Review Activity Comment");
    }
    
    private HtmlPage getIpReviewPage() throws Exception {
        HtmlPage proposalHomePage = getProposalHomePage();
        HtmlPage ipReviewPage = clickOnTab(proposalHomePage, IP_REVIEW_TAB_NAME);
        return ipReviewPage;
    }
    
    private HtmlPage addIpReviewActivity(HtmlPage ipReviewMaintenancePage, String activityNumber, String comment) throws IOException {
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.activityNumber", activityNumber);
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.ipReviewActivityTypeCode", "1");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.activityDate", "07/09/2009");
        setFieldValue(ipReviewMaintenancePage, "document.newMaintainableObject.add.ipReviewActivities.comments", comment);
        HtmlElement addBtn = getElementByName(ipReviewMaintenancePage, 
                "methodToCall.addLine.ipReviewActivities.(!!org.kuali.kra.institutionalproposal.ipreview.InstitutionalProposalIpReviewActivity!!)", true);
        return clickOn(addBtn);
    }

}
