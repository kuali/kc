/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.acknowledgement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.web.CommitteeScheduleWebTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class IrbAcknowledgementWebTest  extends CommitteeScheduleWebTestBase {
    // TODO : this is in general working.  need to add to passsuite after all the detail of 'IRB acknowledgement'
    // panel is done.

    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeHelper.scheduleData.recurrenceType";
    public static final String WEEKLY = "WEEKLY";
    public static final String  SCHEDULEDATA_WEEKLYSCHEDULE_SCHEDULEENDDATE = "committeeHelper.scheduleData.weeklySchedule.scheduleEndDate";
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";

    private static final String PERSON_LOOKUP = "org.kuali.kra.bo.KcPerson";
    private static final String PERSON_ID_FIELD = "personId";
    private static final String ROLODEX_LOOKUP ="org.kuali.kra.bo.NonOrganizationalRolodex";
    private static final String ROLODEX_ID_FIELD = "rolodexId";
    private static final String EMPLOYEE_ID = "10000000001";
    private static final String NON_EMPLOYEE_ID = "1727";
    private static final String ADD_MEMBER_BUTTON = "methodToCall.addCommitteeMembership";
    private static final String ACK_COMMENT = "acknowledgement comment";
   
   // private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0";
    private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[";

    //private HtmlPage membersPage;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private String scheduleDate;
    private String committeeId;
    private String scheduleId;
    private int scheduleNumber;
    private List<String> protocolNumbers;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        //this.membersPage = getMembersPage();
        protocolNumbers = new ArrayList<String>();
       //committeeId = "testcommittee";
        committeeId = getNextCommitteeID();
        if (isMonday(new Date())) {
            scheduleNumber = 2;
        } else {
            scheduleNumber = 1;
        }

        //Date scheduleDate = new Date(dateFormat.parse("10/12/2009").getTime());
        
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
        //this.membersPage = null;
    }
    
    protected String getLoginUserName() {
        // need quickstart for committee & protocol
        return "quickstart";
    }
    
    /**
     * This method is to test the Irb Acknowledgement.
     * Also set up protocol submission that submit to this committee
     * @throws Exception
     */
    @Test
    public void testIrbAcknowledgement() throws Exception {
 
        HtmlPage membersPage = getMemberPage();              
        String docNbr = this.getDocNbr(membersPage);
        membersPage = docSearch(docNbr);
//        committeeId = StringUtils.substringBetween(membersPage.asText(),"* Committee ID: ", " * Committee Name: ");
        setFieldValue(membersPage, COMMITTEE_ID_ID, committeeId);
        membersPage = clickMembersHyperlink(membersPage);
        
        // set up schedule page
        HtmlPage schedulePage = getWeeklySchedule(clickScheduleHyperlink(membersPage));
        schedulePage = clickOn(schedulePage, "save");
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage, "Document was successfully saved.");

              
        // Submit it
        HtmlPage portalPage = clickOn(schedulePage, "submit");
        assertFalse(hasError(portalPage));
        
        // set up protocol submitted
        HtmlPage protocolActionPage = getProtocolSubmission();
//        protocolActionPage = clickOn(protocolActionPage, "tab-SummaryHistoryPrint:SubmissionDetails-imageToggle");;
//        protocolActionPage = clickOn(protocolActionPage, "tab-:SubmissionDetails-imageToggle");;
//        com.gargoylesoftware.htmlunit.ScriptException: ReferenceError: "toggleTab" is not defined.

        assertProtocolSubmiissionDetails(protocolActionPage);

    }

    private void  assertProtocolSubmiissionDetails(HtmlPage page)  throws Exception {
        // this tab is not open, so asText can't see it.
        String pageXml = page.asXml();
        int idx1 = pageXml.indexOf("Submission Details");
        assertTrue(idx1 > 0);
        idx1 = pageXml.indexOf("Committee Id:", idx1);
        assertTrue(idx1 > 0);
        int idx2 = pageXml.indexOf(committeeId,idx1);        
        assertTrue(idx2 > 0);
        
        idx1 = pageXml.indexOf("Committee Name:", idx2);
        assertTrue(idx1 > 0);
        idx2 = pageXml.indexOf("Committee Test",idx1);        
        assertTrue(idx2 > 0);

        idx1 = pageXml.indexOf("Scheduled Date:", idx2);
        assertTrue(idx1 > 0);
        idx2 = pageXml.indexOf(scheduleDate,idx1);        
        assertTrue(idx2 > 0);

        idx1 = pageXml.indexOf("Submission Type:", idx2);
        assertTrue(idx1 > 0);
        idx2 = pageXml.indexOf("FYI",idx1);        
        assertTrue(idx2 > 0);

        idx1 = pageXml.indexOf("Review Type:", idx2);
        assertTrue(idx1 > 0);
        idx2 = pageXml.indexOf("FYI",idx1);        
        assertTrue(idx2 > 0);

        idx1 = pageXml.indexOf("Submission Status:", idx2);
        assertTrue(idx1 > 0);
        idx2 = pageXml.indexOf("IRB Acknowledgement",idx1);        
        assertTrue(idx2 > 0);


    }


    /*HtmlPage> innerPages
     * set up committee members
     */
    private HtmlPage getMemberPage() throws Exception {
        HtmlPage membersPage = getMembersPage();
        membersPage = getEmployee(membersPage);
        // add Philip Berg
        membersPage = addMember(membersPage);
        String endDt = dateFormat.format(DateUtils.addDays(new Date(), 60));

        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].membershipTypeCode", "1");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termStartDate", "01/01/2009");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termEndDate", endDt);
        
        membersPage = addMemberRole(membersPage, 0, "2", "01/01/2009", endDt);
        membersPage = addMemberRole(membersPage, 0, "1", "01/01/2009", "01/10/2009");
        membersPage = addMemberRole(membersPage, 0, "1", "02/01/2009", "02/10/2009");
        membersPage = addMemberExpertise(membersPage, "01.0101", 0);

        
        // add Paulin Ho
        membersPage = getNonEmployee(membersPage);
        membersPage = addMember(membersPage);
        
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].membershipTypeCode", "1");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].termStartDate", "01/01/2009");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].termEndDate", endDt);
        
        membersPage = addMemberRole(membersPage, 1, "7", "01/01/2009", endDt);
        membersPage = addMemberRole(membersPage, 1, "2", "01/01/2009", "01/10/2009");
        membersPage = addMemberRole(membersPage, 1, "14", "02/01/2009", "02/10/2009");
        membersPage = addMemberExpertise(membersPage, "01.0101", 1);

        /*
         * Verify that we can save the document without getting any errors.
         */
        membersPage = clickOn(membersPage, "save");
        assertFalse(hasError(membersPage));
        assertContains(membersPage, "Document was successfully saved.");
        return membersPage;

    }
    
    
            
    /*
     * set up weekly schedule for this committee
     */
    private HtmlPage getWeeklySchedule(HtmlPage page) throws Exception {
        
        HtmlPage schedulePage =  page; 
        Date dt = new Date();
        //scheduleDate = formatDate(dt);
        scheduleDate = dateFormat.format(dt);
        setFields(schedulePage, scheduleDate);
        String endDate = formatDate(DateUtils.addDays(new Date(), 7));
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, WEEKLY);
        setFieldValue(schedulePage, SCHEDULEDATA_WEEKLYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));

        Date monday = null;
        for(int i=1;i<=7; i++) {
            // skip if current date is monday
            monday = DateUtils.addDays(new Date(), i);
            if (isMonday(monday))
                break;
        }
        
        if (isMonday(new Date())) {
            scheduleNumber = 2;
        } else {
            scheduleNumber = 1;
        }
        assertRecord(pageAfterAdd, monday);
        scheduleDate = dateFormat.format(monday);
        return pageAfterAdd;
    }

    /*
     * create protocol submission which will be submitted to the committee/schedule just created
     */
    private HtmlPage getProtocolSubmission() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPageForMeeting();
        String docNbr = this.getDocNbr(protocolPage);
        protocolNumbers.add(StringUtils.substringBetween(protocolPage.asText(), "Protocol #: ", " Protocol Status:"));
        // js is not working well, when committeeid is selected, the schedule is is not generated by dwr/ajax, so
        // set js disabled, and use refresh button to get schedule list.
        webClient.setJavaScriptEnabled(false);

        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);

        // TODO: do I need to expand the inner panel first?
        
        // Make a selection in the Submission Type box and the Submission Review Type box
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.submissionTypeCode", "100");
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.protocolReviewTypeCode", "7");
  
        // temp test check
        HtmlElement element = getElement(protocolActionsPage, "actionHelper.protocolSubmitAction.committeeId");
        HtmlSelect selectField = (HtmlSelect) element;
        List options = selectField.getOptions();
        HtmlOption option = (HtmlOption)options.get(options.size() - 1);
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.committeeId", committeeId);
        
        protocolActionsPage = clickOnByName(protocolActionsPage, "methodToCall.refreshPage", true);
        element = getElement(protocolActionsPage, "actionHelper.protocolSubmitAction.scheduleId");
        selectField = (HtmlSelect) element;
        options = selectField.getOptions();
        option = (HtmlOption)options.get(options.size() - 1);
        if (StringUtils.isBlank(scheduleId)) {
            scheduleId = option.getAttribute("value");
        }
     //   int optionSize = selectField.getOptionSize();
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.scheduleId", scheduleId);

        // Click Submit
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.submitForReview.anchor:SubmitforReview");
        
        // Verify that no errors occurred
        assertNotNull(resultPage);
        assertFalse(hasError(resultPage));
        assertDoesNotContain(resultPage, "Submit For Review");
        
        
//        // submit reviewers
//        setFieldValue(resultPage,"actionHelper.protocolAssignReviewersBean.reviewer[0].checked", "on");
//        setFieldValue(resultPage,"actionHelper.protocolAssignReviewersBean.reviewer[0].reviewerTypeCode", "1");
//        setFieldValue(resultPage,"actionHelper.protocolAssignReviewersBean.reviewer[1].checked", "on");
//        setFieldValue(resultPage,"actionHelper.protocolAssignReviewersBean.reviewer[1].reviewerTypeCode", "2");
//        resultPage = clickOn(resultPage, "methodToCall.assignReviewers.anchor:AssignReviewers");
        // Did not open the tab with following click.  asText still does not show
//        resultPage = clickOn(resultPage, "tab-SummaryHistoryPrint:SubmissionDetails-imageToggle");
//        resultPage = clickOn(resultPage, "tab-:SubmissionDetails-imageToggle");
        
        // after submit for review, it returned to portal page.  so has to do doc search to reload it.
        
        resultPage = docSearch(docNbr);
        protocolActionsPage = clickOnTab(resultPage, PROTOCOL_ACTIONS_LINK_NAME);

        // withdraw, so 'notify irb' action will appear
        protocolActionsPage = clickOnByName(protocolActionsPage, "methodToCall.withdrawProtocol.anchor:WithdrawProtocol", true);
        
        // Verify that no errors occurred
        assertNotNull(protocolActionsPage);
        assertFalse(hasError(protocolActionsPage));

        // after withdraw, the new version protocol will be display at proocol page, so navigate to 'actions' tab
        protocolActionsPage = clickOnTab(resultPage, PROTOCOL_ACTIONS_LINK_NAME);
        assertNotNull(protocolActionsPage);
        assertFalse(hasError(protocolActionsPage));

        // the process is not very clean yet, so need to reload
        protocolActionsPage = clickOnByName(protocolActionsPage, "methodToCall.reload", true);
        // Verify that no errors occurred
        assertNotNull(protocolActionsPage);
        assertFalse(hasError(protocolActionsPage));
        
        // doing notify
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.submissionQualifierTypeCode", "8");
        setFieldValue(protocolActionsPage, "actionHelper.protocolNotifyIrbBean.committeeId", committeeId);
        protocolActionsPage = clickOnByName(protocolActionsPage, "methodToCall.notifyIrbProtocol.anchor:NotifyIRB", true);
        // Verify that no errors occurred
        assertNotNull(protocolActionsPage);
        assertFalse(hasError(protocolActionsPage));
        
        // reload again incase there is lingering bad data
        protocolActionsPage = clickOnByName(protocolActionsPage, "methodToCall.reload", true);
        // Verify that no errors occurred
        assertNotNull(protocolActionsPage);
        assertFalse(hasError(protocolActionsPage));

        // now the 'irb acknowledgement' should appear
        setFieldValue(protocolActionsPage, "actionHelper.irbAcknowledgementBean.comments", ACK_COMMENT);
        resultPage = clickOnByName(protocolActionsPage, "methodToCall.irbAcknowledgement.anchor", true);
        assertNotNull(resultPage);
        assertFalse(hasError(resultPage));

        webClient.setJavaScriptEnabled(true);
        return resultPage;
    }
        
    /*
     * create protocol with required fields.  
     */
    protected HtmlPage getProtocolSavedRequiredFieldsPageForMeeting() throws Exception {
    // need several protocol in the same test.  The original method only create one, so change it here
        HtmlPage protocolPage = buildProtocolDocumentPage();
        setProtocolRequiredFields(protocolPage);
        protocolPage = lookupProtocolAuditRequiredResearchArea(protocolPage);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        return protocolPage;
    }


    /**
     * This method is to look up an employee
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage getEmployee(HtmlPage membersPage) throws Exception {
        return lookup(membersPage , PERSON_LOOKUP, PERSON_ID_FIELD, EMPLOYEE_ID);
    }

    /**
     * This method is to look up a non-employee
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage getNonEmployee(HtmlPage membersPage) throws Exception {
        return lookup(membersPage , ROLODEX_LOOKUP, ROLODEX_ID_FIELD, NON_EMPLOYEE_ID);
    }

    /**
     * This method adds a member to the page
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage addMember(HtmlPage page) throws Exception {
        return clickOn(getElementByName(page, ADD_MEMBER_BUTTON, true));
    }

    /**
     * This method adds a role to a member
     * @param membersPage
     * @param memberIndex
     * @param membershipRoleCode
     * @param startDate
     * @param endDate
     * @return membersPage
     * @throws Exception
     */
    private HtmlPage addMemberRole(HtmlPage membersPage, int memberIndex, String membershipRoleCode, String startDate, String endDate) throws Exception {
        setFieldValue(membersPage, "committeeHelper.newCommitteeMembershipRoles[" + memberIndex + "].membershipRoleCode", membershipRoleCode);
        setFieldValue(membersPage, "committeeHelper.newCommitteeMembershipRoles[" + memberIndex + "].startDate" ,startDate);
        setFieldValue(membersPage, "committeeHelper.newCommitteeMembershipRoles[" + memberIndex + "].endDate", endDate);
        return clickOn(getElementByName(membersPage, ADD_ROLE_BUTTON+memberIndex+"].line"+memberIndex, true));
    }
    
    /**
     * This method add a expertise to a member
     * @param membersPage
     * @param researchAreaCode
     * @return membersPage
     * @throws Exception
     */
    private HtmlPage addMemberExpertise(HtmlPage membersPage, String researchAreaCode, int memberIndex) throws Exception {
        return multiLookup(membersPage, "memberIndex"+memberIndex, "researchAreaCode", researchAreaCode);
    }
}
