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
package org.kuali.kra.meeting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.web.CommitteeScheduleWebTestBase;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
//@PerSuiteUnitTestData(
//@UnitTestData(
//  sqlFiles = {
//          @UnitTestFile(filename = "classpath:sql/dml/load_MINUTE_ENTRY_TYPE.sql", delimiter = ";"),
//          @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_CONTINGENCY.sql", delimiter = ";"),
//          @UnitTestFile(filename = "classpath:sql/dml/load_SCHEDULE_ACT_ITEM_TYPE.sql", delimiter = ";")
//  }
//)
//)

public class MeetingWebTest extends CommitteeScheduleWebTestBase {

    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeHelper.scheduleData.recurrenceType";
    public static final String WEEKLY = "WEEKLY";
    public static final String  SCHEDULEDATA_WEEKLYSCHEDULE_SCHEDULEENDDATE = "committeeHelper.scheduleData.weeklySchedule.scheduleEndDate";
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";

    private static final String PERSON_LOOKUP = "org.kuali.kra.bo.Person";
    private static final String PERSON_ID_FIELD = "personId";
    private static final String ROLODEX_LOOKUP ="org.kuali.kra.bo.NonOrganizationalRolodex";
    private static final String ROLODEX_ID_FIELD = "rolodexId";
    private static final String EMPLOYEE_ID = "000000002";
    private static final String EMPLOYEE_ID_3 = "000000003";
    private static final String EMPLOYEE_NAME = "Philip Berg";
    private static final String NON_EMPLOYEE_ID = "1727";
    private static final String NON_EMPLOYEE_NAME = "Pauline Ho";
    private static final String ADD_MEMBER_BUTTON = "methodToCall.addCommitteeMembership";
    private static final String CLEAR_BUTTON = "methodToCall.clearCommitteeMembership";
    
   // private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0";
    private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[";

    private HtmlPage membersPage;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private String scheduleDate;

    /**
     * The set up method calls the parent super method and gets the 
     * committee page after saving initial required fields.
     * Then navigate to committee members page
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.membersPage = getMembersPage();
        //Date scheduleDate = new Date(dateFormat.parse("10/12/2009").getTime());
        
    }

    /**
     * This method calls parent tear down method and than sets membersPage to null
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.membersPage = null;
    }

//    /**
//     * This method is to test add an employee
//     * @throws Exception
//     */
//    @Test
//    public void testAddEmployee() throws Exception {
//        membersPage = getEmployee(membersPage);
//        assertTrue(membersPage.asText().contains(EMPLOYEE_NAME));
//        membersPage = addMember(membersPage);
//        assertFalse(membersPage.asText().contains(ERRORS_FOUND_ON_PAGE));
//    }
    
//    /**
//     * This method is to test add an non-employee
//     * @throws Exception
//     */
//    @Test
//    public void testAddNonEmployee() throws Exception {
//        membersPage = getNonEmployee(membersPage);
//        assertTrue(membersPage.asText().contains(NON_EMPLOYEE_NAME));
//        membersPage = addMember(membersPage);
//        assertFalse(membersPage.asText().contains(ERRORS_FOUND_ON_PAGE));
//    }
//    
//    /**
//     * This method is to test clear option.
//     * Select an employee and clear selected employee to search for a new person.
//     * @throws Exception
//     */
//    @Test
//    public void testClearEmployee() throws Exception {
//        membersPage = getEmployee(membersPage);
//        assertTrue(membersPage.asText().contains(EMPLOYEE_NAME));
//        membersPage = clickOn(getElementByName(membersPage, CLEAR_BUTTON, true));
//        assertFalse(membersPage.asText().contains(EMPLOYEE_NAME));
//    }
    
    /**
     * This method is to test the saving of a committee member.
     * Select an employee, add required data, and save document
     * @throws Exception
     */
    @Test
    public void testMeetingPage() throws Exception {
        
        getMemberPage();                
        String docNbr = this.getDocNbr(membersPage);
        membersPage = docSearch(docNbr);
        membersPage = clickMembersHyperlink(membersPage);
        
        // set up schedule page
        HtmlPage schedulePage = getWeeklySchedule(clickScheduleHyperlink(membersPage));
        schedulePage = clickOn(schedulePage, "save");
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage, "Document was successfully saved.");

              
        // Submit it
        schedulePage = clickOn(schedulePage, "submit");
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage, "Document was successfully submitted.");
        
        HtmlPage meetingPage = clickOn(schedulePage, "methodToCall.maintainSchedule.line0.anchor0");
        assertFalse(hasError(meetingPage));
        assertMeeting(meetingPage);

        meetingPage = testMeetingDetails(meetingPage);
        meetingPage = testOtherAction(meetingPage);
        meetingPage = testMinutes(meetingPage);
        meetingPage = testAttendancesMember(meetingPage);
        meetingPage = testAttendancesOther(meetingPage);
    }

    
    private void getMemberPage() throws Exception {
        membersPage = getEmployee(membersPage);
        // add Philip Berg
        membersPage = addMember(membersPage);
        
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].membershipTypeCode", "1");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termStartDate", "01/01/2009");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termEndDate", "12/31/2009");
        
        membersPage = addMemberRole(membersPage, 0, "2", "01/01/2009", "12/31/2009");
        membersPage = addMemberRole(membersPage, 0, "1", "01/01/2009", "01/10/2009");
        membersPage = addMemberRole(membersPage, 0, "1", "02/01/2009", "02/10/2009");
        membersPage = addMemberExpertise(membersPage, "01.0101", 0);

        
        // add Paulin Ho
        membersPage = getNonEmployee(membersPage);
        membersPage = addMember(membersPage);
        
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].membershipTypeCode", "1");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].termStartDate", "01/01/2009");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[1].termEndDate", "12/31/2009");
        
        membersPage = addMemberRole(membersPage, 1, "7", "01/01/2009", "12/31/2009");
        membersPage = addMemberRole(membersPage, 1, "2", "01/01/2009", "01/10/2009");
        membersPage = addMemberRole(membersPage, 1, "14", "02/01/2009", "02/10/2009");
        membersPage = addMemberExpertise(membersPage, "01.0101", 1);

        /*
         * Verify that we can save the document without getting any errors.
         */
        membersPage = clickOn(membersPage, "save");
        assertFalse(hasError(membersPage));
        assertContains(membersPage, "Document was successfully saved.");

    }
    
    private void assertMeeting(HtmlPage page)  throws Exception {
        assertTrue(page.asText().contains("Committee Test #1 Meeting "+scheduleDate));
        assertTrue(page.asText().contains("Place: Davis 103"));
        assertTrue(page.asText().contains("Max Protocols: 10"));
        assertTrue(page.asText().contains("Agenda Generation: Schedule Status Code: Scheduled Start Time: 12:00 checked AM unchecked PM End Time: 12:00 checked AM unchecked PM Comments: "));
        assertTrue(page.asText().contains("Scheduled Date: "+scheduleDate));
        assertTrue(page.asText().contains("Protocol Submitted Protocol Submitted Protocol No PI Protocol Title Submission Type Sub. Type Qualifier Sub. Review Type Submission Status Submission Date Actions "));
        assertTrue(page.asText().contains("Other Actions Other Actions * Type * Desctiption Actions "));
        assertTrue(page.asText().contains("Attendance Attendance Voting Members Present: 0 Person Name Alternate For Comments Actions Other Presents: 0 Add: Employee Search Non-employee Search Person Name Role Comments Actions Members Absent or Available: 2"));
        assertTrue(page.asText().contains("Name Actions 1 Ho, Pauline 2 Philip Berg "));
        assertTrue(page.asText().contains("Minutes Minutes * Entry Type Protocol Description Standard Review Comment Generate Attendance Private Final Actions "));
    }
    
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
        for(int i=0;i<7; i++) {
            monday = DateUtils.addDays(new Date(), i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);
        scheduleDate = dateFormat.format(monday);
        return pageAfterAdd;
    }

    private HtmlPage testMeetingDetails(HtmlPage page) throws Exception {
        
        setFieldValue(page, "meetingHelper.committeeSchedule.meetingDate", scheduleDate);
        setFieldValue(page, "meetingHelper.committeeSchedule.viewStartTime.time", "01:05");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewStartTime.meridiem", "PM");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewEndTime.time", "03:10");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewEndTime.meridiem", "PM");
        HtmlPage pageAfterSave = clickOn(page, "save");
        
        assertFalse(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("Meeting Date: "+scheduleDate));
        assertTrue(pageAfterSave.asText().contains("Agenda Generation: Schedule Status Code: Scheduled Start Time: 01:05 unchecked AM checked PM End Time: 03:10 unchecked AM checked PM Comments: "));

        return pageAfterSave;
    }

    private HtmlPage testOtherAction(HtmlPage page) throws Exception {
        
        setFieldValue(page, "meetingHelper.newOtherAction.scheduleActItemTypeCode", "1");
        setFieldValue(page, "meetingHelper.newOtherAction.itemDesctiption", "Other Action description 1");
        HtmlPage pageAfterAdd = clickOnByName(page,"methodToCall.addOtherAction.anchor", true);
        assertFalse(hasError(pageAfterAdd));
        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.scheduleActItemTypeCode", "2");
        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.itemDesctiption", "Other Action description 2");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addOtherAction.anchor", true);
        assertFalse(hasError(pageAfterAdd));

        HtmlPage pageAfterSave = clickOn(pageAfterAdd, "save");
        
        assertFalse(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 Other Business Other Action description 1"));
        assertTrue(pageAfterSave.asText().contains("2 New Member Consideration Other Action description 2"));
        
        // delete item 1
        HtmlPage pageAfterDelete = clickOnByName(pageAfterSave,"methodToCall.deleteOtherAction.line0", true);
        assertFalse(hasError(pageAfterDelete));
        pageAfterSave = clickOn(pageAfterDelete, "save");
        assertFalse(hasError(pageAfterSave));
        assertFalse(pageAfterSave.asText().contains("1 Other Business Other Action description 1"));
        assertTrue(pageAfterSave.asText().contains("1 New Member Consideration Other Action description 2"));

        return pageAfterSave;
    }
    
    private HtmlPage testMinutes(HtmlPage page) throws Exception {
        // TODO : need to add 'protocol, protocol contingency lookup & generate attendance
        setFieldValue(page, "meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode", "1");
        setFieldValue(page, "meetingHelper.newCommitteeScheduleMinute.minuteEntry", "Minutes description 1");
        HtmlPage pageAfterAdd = clickOnByName(page,"methodToCall.addCommitteeScheduleMinute", true);
        assertFalse(hasError(pageAfterAdd));
        setFieldValue(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode", "2");
        setFieldValue(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.minuteEntry", "Minutes description 2");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addCommitteeScheduleMinute", true);
        assertFalse(hasError(pageAfterAdd));

        HtmlPage pageAfterSave = clickOn(pageAfterAdd, "save");
        
        assertFalse(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 General Comments Minutes description 1"));
        assertTrue(pageAfterSave.asText().contains("2 Attendance Minutes description 2"));
        
        // delete item 1
        HtmlPage pageAfterDelete = clickOnByName(pageAfterSave,"methodToCall.deleteCommitteeScheduleMinute.line0", true);
        assertFalse(hasError(pageAfterDelete));
        pageAfterSave = clickOn(pageAfterDelete, "save");
        assertFalse(hasError(pageAfterSave));
        assertFalse(pageAfterSave.asText().contains("1 General Comments Minutes description 1"));
        assertTrue(pageAfterSave.asText().contains("1 Attendance Minutes description 2"));

        return pageAfterSave;
    }
    
    private HtmlPage testAttendancesMember(HtmlPage page) throws Exception {

        HtmlPage pageAfterAdd = clickOnByName(page,"methodToCall.presentVoting.line0", true);
        assertAttendancePanel(pageAfterAdd,1,0,1);
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.presentOther.line0", true);
        assertAttendancePanel(pageAfterAdd,1,1,0);

        HtmlPage pageAfterSave = clickOn(pageAfterAdd, "save");
                
        assertAttendancePanel(pageAfterSave,1,1,0);
       // delete other present
        HtmlPage pageAfterDelete = clickOnByName(pageAfterSave,"methodToCall.deleteOtherPresent.line0", true);
        assertAttendancePanel(pageAfterDelete,1,0,1);

        // mark member absent
        pageAfterDelete = clickOnByName(pageAfterDelete,"methodToCall.markAbsent.line0", true);
        assertAttendancePanel(pageAfterDelete,0,0,2);

        
        pageAfterSave = clickOn(pageAfterDelete, "save");
        assertAttendancePanel(pageAfterSave,0,0,2);

        return pageAfterSave;
    }

    private HtmlPage testAttendancesOther(HtmlPage page) throws Exception {

        HtmlPage pageAfterLookup = lookup(page , PERSON_LOOKUP, PERSON_ID_FIELD, EMPLOYEE_ID_3);;
        assertFalse(hasError(pageAfterLookup));
        HtmlPage pageAfterAdd = clickOnByName(pageAfterLookup,"methodToCall.addOtherPresent", true);
        assertAttendancePanel(pageAfterAdd,0,1,2);

        HtmlPage pageAfterSave = clickOn(pageAfterAdd, "save");
                
        assertAttendancePanel(pageAfterSave,0,1,2);
        
        // Pauline Ho is a member
        pageAfterLookup = lookup(pageAfterSave , ROLODEX_LOOKUP, ROLODEX_ID_FIELD, NON_EMPLOYEE_ID);
        assertFalse(hasError(pageAfterLookup));
        pageAfterAdd = clickOnByName(pageAfterLookup,"methodToCall.addOtherPresent", true);
        assertAttendancePanel(pageAfterAdd,0,2,1);

        pageAfterSave = clickOn(pageAfterAdd, "save");
                
        assertAttendancePanel(pageAfterSave,0,2,1);

        return pageAfterSave;
    }

    private void assertAttendancePanel (HtmlPage page, int memberPresent, int otherPresent, int memberAbsent) throws Exception {
        assertFalse(hasError(page));
        assertTrue(page.asText().contains("Attendance Attendance Voting Members Present: "+memberPresent));
        assertTrue(page.asText().contains("Other Presents: "+ otherPresent));
        assertTrue(page.asText().contains("Members Absent or Available: "+memberAbsent));

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
        //return multiLookup(membersPage, "committeeResearchAreas", "researchAreaCode", researchAreaCode);
        return multiLookup(membersPage, "memberIndex"+memberIndex, "researchAreaCode", researchAreaCode);
    }
}
