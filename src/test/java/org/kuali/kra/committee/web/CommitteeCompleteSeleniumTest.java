package org.kuali.kra.committee.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the basic submission of a Committee.
 */
public class CommitteeCompleteSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PERSON_ID_TAG = "committeeHelper.newCommitteeMembership.personId";
    private static final String ROLODEX_ID_TAG = "committeeHelper.newCommitteeMembership.rolodexId";
    private static final String RESEARCH_AREAS_TAG = "committeeResearchAreas";
    
    private static final String PERSON_ID_ID = "personId";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String MEMBERSHIP_TYPE_CODE_ID = "document.committeeList[0].committeeMemberships[%d].membershipTypeCode";
    private static final String TERM_START_DATE_ID = "document.committeeList[0].committeeMemberships[%d].termStartDate";
    private static final String TERM_END_DATE_ID = "document.committeeList[0].committeeMemberships[%d].termEndDate";
    private static final String MEMBERSHIP_ROLE_CODE_ID = "committeeHelper.newCommitteeMembershipRoles[%d].membershipRoleCode";
    private static final String START_DATE_ID = "committeeHelper.newCommitteeMembershipRoles[%d].startDate";
    private static final String END_DATE_ID = "committeeHelper.newCommitteeMembershipRoles[%d].endDate";
    private static final String RESEARCH_AREA_CODE_ID = "researchAreaCode";
    private static final String SCHEDULE_START_DATE_ID = "committeeHelper.scheduleData.scheduleStartDate";
    private static final String RECURRENCE_TYPE_ID = "committeeHelper.scheduleData.recurrenceType";
    private static final String SCHEDULE_END_DATE_ID = "committeeHelper.scheduleData.monthlySchedule.scheduleEndDate";
    
    private static final String VOTING_CHAIR_MEMBERSHIP_TYPE = "Voting member";
    private static final String NICHOLAS_MAJORS_PERSON_ID = "10000000004";
    private static final String NICHOLAS_MAJORS_NAME = "Nicholas Majors";
    private static final String CHAIR_MEMBERSHIP_ROLE = "Chair";
    private static final String PERSONAL_CULINARY_SERVICES_OTHER_RESEARCH_AREA_CODE = "12.99";
    private static final String PERSONAL_CULINARY_SERVICES_OTHER_NAME = "12.99 Personal and Culinary Services, Other";
    private static final String ALLYSON_CATE_PERSON_ID = "10000000008";
    private static final String ALLYSON_CATE_NAME = "Allyson Cate";
    private static final String IRB_ADMINISTRATOR_MEMBERSHIP_ROLE = "IRB Administrator";
    private static final String AGRICULTURAL_PRODUCTION_OPERATIONS_RESEARCH_AREA_CODE = "01.03";
    private static final String AGRICULTURAL_PRODUCTION_OPERATIONS_NAME = "01.03 Agricultural Production Operations";
    private static final String LEE_VAN_LENTEN_PERSON_ID = "328";
    private static final String LEE_VAN_LENTEN_NAME = "Van Lenten, Lee";
    private static final String ALTERNATE_MEMBERSHIP_ROLE = "Alternate";
    private static final String GENERAL_EDUCATION_RESEARCH_AREA_CODE = "13.01";
    private static final String GENERAL_EDUCATION_NAME = "13.01 Education, General";
    private static final String RECURRENCE_TYPE = "MONTHLY";
    
    private static final String ADD_MEMBERSHIP_BUTTON = "methodToCall.addCommitteeMembership";
    private static final String ADD_MEMBERSHIP_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[%d].line%d";
    private static final String ADD_EVENT_BUTTON = "methodToCall.addEvent";
    private static final String SHOW_ALL_MEMBERS_BUTTON = "methodToCall.showAllMembers";
    
    private String startDate;
    private String endDate;
    private String firstScheduleDate;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private CommitteeSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = CommitteeSeleniumHelper.instance(driver);
        setupDates();
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test the basic submission of a committee.
     */
    @Test
    public void testCommitteeComplete() throws Exception {
        helper.createCommittee();

        addMembers();
        
        addSchedules();
        
        helper.routeDocument();
        
        assertMembers();
        assertSchedule();
    }

    /**
     * Adds members to a committee.
     */
    private void addMembers() {
        helper.clickCommitteeMembersPage();
        
        addMember(0, true, NICHOLAS_MAJORS_PERSON_ID, CHAIR_MEMBERSHIP_ROLE, PERSONAL_CULINARY_SERVICES_OTHER_RESEARCH_AREA_CODE);
        helper.clickCollapseAll();
        addMember(1, true, ALLYSON_CATE_PERSON_ID, IRB_ADMINISTRATOR_MEMBERSHIP_ROLE, AGRICULTURAL_PRODUCTION_OPERATIONS_RESEARCH_AREA_CODE);        
        helper.clickCollapseAll();
        addMember(2, false, LEE_VAN_LENTEN_PERSON_ID, ALTERNATE_MEMBERSHIP_ROLE, GENERAL_EDUCATION_RESEARCH_AREA_CODE);
    }
    
    /**
     * Adds a member to a committee.
     * 
     * @param index the index of the member
     * @param isEmployee whether or not the member is an employee
     * @param personId the id of the member
     * @param role the role of the member
     * @param researchAreaCode the research area code of the member
     */
    private void addMember(int index, boolean isEmployee, String personId, String role, String researchAreaCode) {
        if (isEmployee) {
            helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, personId);
        } else {
            helper.lookup(ROLODEX_ID_TAG, ROLODEX_ID_ID, personId);
        }
        
        helper.click(ADD_MEMBERSHIP_BUTTON);

        helper.openTab(index);
        helper.openTab(index + 1);
        helper.set(String.format(MEMBERSHIP_TYPE_CODE_ID, index), VOTING_CHAIR_MEMBERSHIP_TYPE);
        helper.set(String.format(TERM_START_DATE_ID, index), startDate);
        helper.set(String.format(TERM_END_DATE_ID, index), endDate);
        
        helper.openTab(index + 3);
        helper.set(String.format(MEMBERSHIP_ROLE_CODE_ID, index), role);
        helper.set(String.format(START_DATE_ID, index), startDate);
        helper.set(String.format(END_DATE_ID, index), endDate);
        helper.click(String.format(ADD_MEMBERSHIP_ROLE_BUTTON, index, index));

        helper.openTab(index + 4);
        helper.multiLookup(RESEARCH_AREAS_TAG, RESEARCH_AREA_CODE_ID, researchAreaCode);
        
        helper.saveDocument();
        helper.assertSave();
    }
    
    /**
     * Adds schedules to a committee.
     */
    private void addSchedules() {
        helper.clickCommitteeSchedulePage();
        
        helper.set(SCHEDULE_START_DATE_ID, startDate);
        helper.set(RECURRENCE_TYPE_ID, RECURRENCE_TYPE);
        helper.set(SCHEDULE_END_DATE_ID, endDate);
        helper.click(ADD_EVENT_BUTTON);
        helper.saveDocument();
        helper.assertSave();
    }
    
    /**
     * Asserts that the members have been properly added.
     */
    private void assertMembers() {
        helper.clickCommitteeMembersPage();
        
        helper.click(SHOW_ALL_MEMBERS_BUTTON);
        helper.clickExpandAll();
        
        assertMember(NICHOLAS_MAJORS_NAME, CHAIR_MEMBERSHIP_ROLE, PERSONAL_CULINARY_SERVICES_OTHER_NAME);
        assertMember(ALLYSON_CATE_NAME, IRB_ADMINISTRATOR_MEMBERSHIP_ROLE, AGRICULTURAL_PRODUCTION_OPERATIONS_NAME);
        assertMember(LEE_VAN_LENTEN_NAME, ALTERNATE_MEMBERSHIP_ROLE, GENERAL_EDUCATION_NAME);
    }
    
    /**
     * Asserts that a member has been properly added.
     *
     * @param name the name of the member
     * @param role the role of the member
     * @param researchArea the research area of the member
     */
    private void assertMember(String name, String role, String researchArea) {
        helper.assertPageContains(name);
        helper.assertPageContains(role);
        helper.assertPageContains(researchArea);
    }
    
    /**
     * Asserts that the schedule has been properly added.
     */
    private void assertSchedule() {
        helper.clickCommitteeSchedulePage();
        
        helper.assertPageContains(firstScheduleDate);
    }
    
    /**
     * Sets up the dates for the schedule.
     */
    private void setupDates() {
        Calendar cl = new GregorianCalendar();
        cl.setTime(new Date());        
        cl.get(Calendar.DAY_OF_MONTH);
        int month = cl.get(Calendar.MONTH);
        int year = cl.get(Calendar.YEAR);
        if (cl.get(Calendar.DAY_OF_MONTH) <= 6) {
            startDate = dateFormat.format(DateUtils.addDays(new Date(), 6));
            endDate = dateFormat.format(DateUtils.addDays(new Date(), 371));
        } else {
            startDate = dateFormat.format(new Date());
            endDate = dateFormat.format(DateUtils.addDays(new Date(), 365));
        }
        if (month == 11) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 2;
        }
        if (month < 10) {
            firstScheduleDate = "0" + month + "/06/" + year;
        } else {
            firstScheduleDate =  month + "/06/" + year;
        }
    }
    
}