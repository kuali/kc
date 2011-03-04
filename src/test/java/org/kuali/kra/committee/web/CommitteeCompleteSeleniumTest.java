package org.kuali.kra.committee.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

public class CommitteeCompleteSeleniumTest extends CommitteeSeleniumTestBase {
    
    private static final String AGRICULTURAL_PRODUCTION_OPERATIONS_RESEARCH_AREA_CODE = "01.03";
    private static final String PERSONAL_CULINARY_SERVICES_OTHER_RESEARCH_AREA_CODE = "12.99";
    private static final String GENERAL_EDUCATION_RESEARCH_AREA_CODE = "13.01";
    
    private String startDate;
    private String endDate;
    private String firstScheduleDate;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setupDates();
    }

    @Test
    public void testCommitteeComplete() throws Exception {
        createCommittee();

        addMembers();
        
        addSchedules();
        
        routeDocument();
        
        assertMembers();
        assertSchedule();
        
        String documentNumber = getDocumentNumber();
        docSearch(documentNumber);
        
        assertMembers();
        assertSchedule();
    }

    private void addMembers() {
        clickCommitteeMembersPage();
        
        addMember(0, true, "10000000004", "Chair", PERSONAL_CULINARY_SERVICES_OTHER_RESEARCH_AREA_CODE);
        addMember(1, true, "10000000008", "IRB Administrator", AGRICULTURAL_PRODUCTION_OPERATIONS_RESEARCH_AREA_CODE);        
        addMember(2, false, "328", "Alternate", GENERAL_EDUCATION_RESEARCH_AREA_CODE);
    }
    
    private void addMember(int index, boolean isEmployee, String personId, String role, String researchAreaCode) {
        if (isEmployee) {
            lookupEmployeeCommitteeMember(personId);
        } else {
            lookupNonEmployeeCommitteeMember(personId);
        }
        
        click("methodToCall.addCommitteeMembership");
        
        openTab(0);
        
        openTab(1);
        set("document.committeeList[0].committeeMemberships[" + index + "].membershipTypeCode", "Voting member");
        set("document.committeeList[0].committeeMemberships[" + index + "].termStartDate", startDate);
        set("document.committeeList[0].committeeMemberships[" + index + "].termEndDate", endDate);
        
        openTab(3);
        set("committeeHelper.newCommitteeMembershipRoles[" + index + "].membershipRoleCode", role);
        set("committeeHelper.newCommitteeMembershipRoles[" + index + "].startDate", startDate);
        set("committeeHelper.newCommitteeMembershipRoles[" + index + "].endDate", endDate);
        click("methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[" + index + "].line" + index);

        openTab(4);
        lookupResearchArea(researchAreaCode);
        
        saveDocument();
        assertSave();
    }
    
    private void addSchedules() {
        clickCommitteeSchedulePage();
        
        set("committeeHelper.scheduleData.scheduleStartDate", startDate);
        set("committeeHelper.scheduleData.recurrenceType", "MONTHLY");
        set("committeeHelper.scheduleData.monthlySchedule.scheduleEndDate", endDate);
        click("methodToCall.addEvent");
        saveDocument();
        assertSave();
    }
    
    private void assertMembers() {
        clickCommitteeMembersPage();
        
        click("methodToCall.showAllMembers");
        clickExpandAll();
        
        assertMember("Nicholas  Majors", "Chair", "12.99 Personal and Culinary Services, Other");
        assertMember("Allyson  Cate", "IRB Administrator", "01.03 Agricultural Production Operations");
        assertMember("Van Lenten, Lee", "Alternate", "13.01 Education, General");
    }
    
    private void assertMember(String name, String role, String researchArea) {
        assertPageContains(name);
        assertPageContains(role);
        assertPageContains(researchArea);
    }
    
    private void assertSchedule() {
        clickCommitteeSchedulePage();
        
        assertPageContains(firstScheduleDate);
    }
    
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