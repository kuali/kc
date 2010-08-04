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
package org.kuali.kra.meeting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.web.CommitteeScheduleWebTestBase;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

//@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
//        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_CONTINGENCY.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_SCHEDULE_ACT_ITEM_TYPE.sql", delimiter = ";") }))
/**
 * 
 * This class is for meeting web page test.
 */
public class MeetingWebTest extends CommitteeScheduleWebTestBase {
    // need to create committee and protocol, so CommitteeWebTestBase and ProtocolWebTestBase are needed
    // modified CommitteeWebTestBase to extend ProtocolWebTestBase

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
    private static final String EMPLOYEE_ID_3 = "10000000003";
    private static final String NON_EMPLOYEE_ID = "1727";
    private static final String ADD_MEMBER_BUTTON = "methodToCall.addCommitteeMembership";
    
   // private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0";
    private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[";

    //private HtmlPage membersPage;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
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
        committeeId = "testcommittee";
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
     * This method is to test the Meeting Page.
     * Need to set up a committee with members & schedules
     * Also set up protocol submission that submit to this committee
     * @throws Exception
     */
    @Test
    public void testMeetingPage() throws Exception {
 
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
        schedulePage = clickOn(schedulePage, "submit");
        assertFalse(hasError(schedulePage));
        assertContains(schedulePage, "Document was successfully submitted.");
        
        // set up 3 protocol submitted
        setProtocolSubmission();
        setProtocolSubmission();
        setProtocolSubmission();
        
        HtmlPage meetingPage = clickOn(schedulePage, "methodToCall.maintainSchedule.line"+(scheduleNumber-1)+".anchor0");
        assertFalse(hasError(meetingPage));
        assertMeeting(meetingPage);
        
        meetingPage = testMeetingDetails(meetingPage);
        meetingPage = testOtherAction(meetingPage);
        meetingPage = testMinutes(meetingPage);
        meetingPage = testAttendancesMember(meetingPage);
        meetingPage = testAttendancesOther(meetingPage);
        assertProtocolSubmitted(meetingPage);
    }

    @Test
    public void testMeetingActionPage() throws Exception {
//        transactionalLifecycle.stop();
        setProtocolCorrTemplate();
//        transactionalLifecycle.start();
       
        Map fieldValues = new HashMap();
        fieldValues.put("committeeId", committeeId);
        HtmlPage committeePage = docSearch(((List<Committee>)getBusinessObjectService().findMatching(Committee.class, fieldValues)).get(0).getCommitteeDocument().getDocumentNumber());
        HtmlPage schedulePage = clickScheduleHyperlink(committeePage);
        HtmlPage meetingPage = clickOn(schedulePage, "methodToCall.maintainSchedule.line"+(scheduleNumber-1)+".anchor0");
        meetingPage = clickOnTab(meetingPage, "actions");
        assertFalse(hasError(meetingPage));
        assertContains(meetingPage, "Agenda Generate Agenda Generate Agenda View Agenda Version Date Created Actions");
        assertContains(meetingPage, "Minutes Minutes Generate Minutes View Minutes Version Date Created Actions");
        assertContains(meetingPage, "Print Print Roster unchecked Future Scheduled Meetings unchecked");
        assertContains(meetingPage, "Correspondence Correspondence Protocol Number Correspondence Date Created Actions");
        // there is js to open doc page after this page load is complete, so need to disable js.  otherwise, htmlunit will get the doc page instead
        webClient.setJavaScriptEnabled(false);
        meetingPage = clickOn(meetingPage, "methodToCall.generateAgenda.anchor");
        // in meetingAgenda.tag, the date has been formatted to yyyy/mm/dd, but the html page is back with yyy-mm-dd hh:mm:ss
        assertContains(meetingPage, "Agenda Generate Agenda Generate Agenda View Agenda Version Date Created Actions 1 "+dateFormat1.format(new Date()));
        meetingPage = clickOn(meetingPage, "methodToCall.generateMinutes.anchorMinutes");
        // in meetingActionMinute.tag, the date has been formatted to yyyy/mm/dd, but the html page is back with yyy-mm-dd hh:mm:ss
        assertContains(meetingPage, "Minutes Minutes Generate Minutes View Minutes Version Date Created Actions 1 "+dateFormat1.format(new Date()));
        webClient.setJavaScriptEnabled(true);
   }

    /*
     * set up protocol corr template for testing
     */
    private void setProtocolCorrTemplate() {
        ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
        template.setFileName("test");
        template.setCorrespondenceTemplate(getFileTemp());
        template.setProtoCorrespTypeCode("9");
        template.setCommitteeId("DEFAULT");
        getBusinessObjectService().save(template);
        template.setProtoCorrespTypeCode("10");
        template.setProtoCorrespTemplId(null);
        getBusinessObjectService().save(template);

    }

    private byte[] getFileTemp() {
        try {
            File file = new File("src/test/resources/org/kuali/kra/printing/stylesheet/CorresReportAgenda.xsl");
            InputStream inStream = new FileInputStream(file);
            // BufferedInputStream bis = new BufferedInputStream(inStream);
            // return new byte[bis.available()];

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = inStream.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); // no doubt here is 0
                // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }

            return bos.toByteArray();

        }
        catch (Exception e) {
            return null;
        }
    }

    /*
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
     * validate data on meeting details
     */
    private void assertMeeting(HtmlPage page)  throws Exception {
        assertTrue(page.asText().contains("Committee Test #"+scheduleNumber+" Meeting "+scheduleDate));
        assertTrue(page.asText().contains("Place: Davis 103"));
        assertTrue(page.asText().contains("Max Protocols: 10"));
        assertTrue(page.asText().contains("Agenda Generation: Schedule Status Code: Scheduled Start Time: 12:00 checked AM unchecked PM End Time: 12:00 checked AM unchecked PM Comments: "));
        assertTrue(page.asText().contains("Scheduled Date: "+scheduleDate));
        assertTrue(page.asText().contains("Protocol Submitted Protocol Submitted Protocol No PI Protocol Title Submission Type Sub. Type Qualifier Sub. Review Type Submission Status Submission Date Actions "));
        assertTrue(page.asText().contains("Other Actions Other Actions * Type * Description Actions "));
        assertTrue(page.asText().contains("Attendance Attendance Voting Members Present: 0 Person Name Alternate For Comments Actions Other Presents: 0 Add: Employee Search Non-employee Search Person Name Role Comments Actions Members Absent or Available: 2"));
        assertTrue(page.asText().contains("Name Actions 1 Ho, Pauline 2 Joe Tester "));
        assertTrue(page.asText().contains("Minutes Minutes * Entry Type Generate Attendance Protocol Other Business Description Standard Review Comment Private Final Actions "));
    }
    
    /*
     * validate protocol submitted panel
     */
    private void  assertProtocolSubmitted(HtmlPage page)  throws Exception {
        // TODO : protocol test kew file is still not quite right yet.  After protocol submitted for review,
        // this protocol becomes 'final' and 'approved'.  So, it failed here 2/4/10
        assertTrue(page.asText().contains(protocolNumbers.get(0) +" Nicholas Majors New protocol test Initial Protocol Application for Approval Full Submitted to Committee"));
        assertTrue(page.asText().contains(protocolNumbers.get(1) +" Nicholas Majors New protocol test Initial Protocol Application for Approval Full Submitted to Committee"));
        assertTrue(page.asText().contains(protocolNumbers.get(2) +" Nicholas Majors New protocol test Initial Protocol Application for Approval Full Submitted to Committee"));
        String viewProtocolNumber = StringUtils.substringBetween(page.asText(), "Submission Date Actions 1 ", " Nicholas Majors New protocol test Initial Protocol ");
       // HtmlPage protocolActionPage = clickOnByName(page, "methodToCall.viewProtocolSubmission.line0", true);
        HtmlAnchor hyperlink = getAnchor(page, "meetingManagement.do?command=viewProtocolSubmission&line=0");
        assertNotNull(hyperlink);
        HtmlPage protocolActionPage = clickOn(hyperlink);
        
        assertFalse(hasError(protocolActionPage));
        assertTrue(protocolActionPage.asText().contains("Submitted to IRB "+dateFormat.format(new Date())));
        assertTrue(protocolActionPage.asText().contains("Protocol Number: "+viewProtocolNumber));
        //assertTrue(protocolActionPage.asText().contains("PI: Terry Durkin Status: Submitted to IRB Title: New protocol test"));
        //assertTrue(protocolActionPage.asText().contains("Status: ENROUTE Initiator: quickstart"));
        assertTrue(protocolActionPage.asText().contains("Nicholas Majors Principal Investigator 000001 : University "));

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
    private void setProtocolSubmission() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPageForMeeting();
        protocolNumbers.add(StringUtils.substringBetween(protocolPage.asText(), "Protocol #: ", " Protocol Status:"));
        // js is not working well, when committeeid is selected, the schedule is is not generated by dwr/ajax, so
        // set js disabled, and use refresh button to get schedule list.
        webClient.setJavaScriptEnabled(false);

        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);

        // TODO: do I need to expand the inner panel first?
        
        // Make a selection in the Submission Type box and the Submission Review Type box
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.submissionTypeCode", "100");
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.protocolReviewTypeCode", "1");
  
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
        webClient.setJavaScriptEnabled(true);
    }
        
    /*
     * create protocol with required fields.  
     */
    protected HtmlPage getProtocolSavedRequiredFieldsPageForMeeting() throws Exception {
    // need several protocol in the same test.  The original method only create one, so change it here
        HtmlPage protocolPage = buildProtocolDocumentPage();
        setProtocolRequiredFields(protocolPage);
        protocolPage = savePage(protocolPage);
        validateSavedPage(protocolPage);
        return protocolPage;
    }

    /*
     * validate meeting details
     */
    private HtmlPage testMeetingDetails(HtmlPage page) throws Exception {
        
        setFieldValue(page, "meetingHelper.committeeSchedule.meetingDate", scheduleDate);
        setFieldValue(page, "meetingHelper.committeeSchedule.viewStartTime.time", "01:65");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewStartTime.meridiem", "PM");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewEndTime.time", "03:10");
        setFieldValue(page, "meetingHelper.committeeSchedule.viewEndTime.meridiem", "PM");
        HtmlPage pageAfterSave = clickOn(page, "save");   
        // minute > 60
        assertTrue(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 error(s) found")); 

        
        setFieldValue(pageAfterSave, "meetingHelper.committeeSchedule.viewStartTime.time", "13:05");
        pageAfterSave = clickOn(pageAfterSave, "save");   
        // hour > 12
        assertTrue(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 error(s) found")); 
        
        setFieldValue(pageAfterSave, "meetingHelper.committeeSchedule.viewEndTime.meridiem", "AM");
        setFieldValue(pageAfterSave, "meetingHelper.committeeSchedule.viewStartTime.time", "01:05");
        pageAfterSave = clickOn(pageAfterSave, "save");   
        // start time > end time
        assertTrue(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 error(s) found")); 
  
        
        
        setFieldValue(pageAfterSave, "meetingHelper.committeeSchedule.viewEndTime.meridiem", "PM");
        pageAfterSave = clickOn(pageAfterSave, "save");
        
        assertFalse(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("Meeting Date: "+scheduleDate));
        assertTrue(pageAfterSave.asText().contains("Agenda Generation: Schedule Status Code: Scheduled Start Time: 01:05 unchecked AM checked PM End Time: 03:10 unchecked AM checked PM Comments: "));

        return pageAfterSave;
    }

    /*
     * validate other action panel page
     */
    private HtmlPage testOtherAction(HtmlPage page) throws Exception {
        
        HtmlPage pageAfterAdd = clickOnByName(page,"methodToCall.addOtherAction.anchor", true);
        assertTrue(hasError(pageAfterAdd));
        assertTrue(pageAfterAdd.asText().contains("2 error(s) found")); 

        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.scheduleActItemTypeCode", "1");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addOtherAction.anchor", true);
        assertTrue(hasError(pageAfterAdd));
        assertTrue(pageAfterAdd.asText().contains("1 error(s) found")); 
        
        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.itemDescription", "Other Action description 1");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addOtherAction.anchor", true);
        assertFalse(hasError(pageAfterAdd));
        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.scheduleActItemTypeCode", "2");
        setFieldValue(pageAfterAdd, "meetingHelper.newOtherAction.itemDescription", "Other Action description 2");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addOtherAction.anchor", true);
        assertFalse(hasError(pageAfterAdd));

        HtmlPage pageAfterSave = clickOn(pageAfterAdd, "save");
        
        assertFalse(hasError(pageAfterSave));
        assertTrue(pageAfterSave.asText().contains("1 Other Business Other Action description 1"));
        assertTrue(pageAfterSave.asText().contains("2 New Member Consideration Other Action description 2"));
        
        // delete item 1
        HtmlPage pageAfterDelete = clickOnByName(pageAfterSave,"methodToCall.deleteOtherAction.line0", true);
        pageAfterDelete = clickOnByName(pageAfterDelete,"methodToCall.processAnswer.button0",true);
        assertFalse(hasError(pageAfterDelete));
        pageAfterSave = clickOn(pageAfterDelete, "save");
        assertFalse(hasError(pageAfterSave));
        assertFalse(pageAfterSave.asText().contains("1 Other Business Other Action description 1"));
        assertTrue(pageAfterSave.asText().contains("1 New Member Consideration Other Action description 2"));

        return pageAfterSave;
    }
    
    /*
     * validate minutes panel
     */
    private HtmlPage testMinutes(HtmlPage page) throws Exception {
        // TODO : need to add 'protocol, protocol contingency lookup & generate attendance
        HtmlPage pageAfterAdd = clickOnByName(page,"methodToCall.addCommitteeScheduleMinute", true);
        assertTrue(hasError(pageAfterAdd));
        assertTrue(pageAfterAdd.asText().contains("1 error(s) found")); 
        
        setFieldValue(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode", "1");
        setFieldValue(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.minuteEntry", "Minutes description 1");
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addCommitteeScheduleMinute", true);
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
        //amswer the confirm question
        pageAfterDelete = clickOnByName(pageAfterDelete,"methodToCall.processAnswer.button0",true);
        
        
        assertFalse(hasError(pageAfterDelete));
        pageAfterSave = clickOn(pageAfterDelete, "save");
        assertFalse(hasError(pageAfterSave));
        assertFalse(pageAfterSave.asText().contains("1 General Comments Minutes description 1"));
        assertTrue(pageAfterSave.asText().contains("1 Attendance Minutes description 2"));

        setFieldValue(pageAfterSave, "meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode", "3");
        setFieldValue(pageAfterSave, "meetingHelper.newCommitteeScheduleMinute.minuteEntry", "Minutes description 1");
        pageAfterAdd = clickOnByName(pageAfterSave,"methodToCall.addCommitteeScheduleMinute", true);
        assertTrue(hasError(pageAfterAdd));
        assertTrue(pageAfterAdd.asText().contains("1 error(s) found")); 
        // TODO : how to figure out schedule id
        HtmlElement element = getElement(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.protocolIdFk");
        HtmlSelect selectField = (HtmlSelect) element;
        List options = selectField.getOptions();
        HtmlOption option = (HtmlOption)options.get(1);

        setFieldValue(pageAfterAdd, "meetingHelper.newCommitteeScheduleMinute.protocolIdFk", option.getAttribute("value"));
        pageAfterAdd = clickOnByName(pageAfterAdd,"methodToCall.addCommitteeScheduleMinute", true);
        assertFalse(hasError(pageAfterAdd));
        pageAfterSave = clickOn(pageAfterAdd, "save");
        assertFalse(hasError(pageAfterSave));

        
        return pageAfterSave;
    }
    
    /*
     * validate attendance member tabs
     */
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

    /*
     * validate other present tab
     */
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

    /*
     * utility methods to validate attendance data
     */
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
        return multiLookup(membersPage, "memberIndex"+memberIndex, "researchAreaCode", researchAreaCode);
    }
}