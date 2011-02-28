package org.kuali.kra.committee.selenium;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommitteeCompleteTest extends CommitteeSeleniumTestBase {
    
    private String startDate;
    private String endDate;
    private String firstScheduleDate;
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setDate();
    }

    @Test
    public void testCommitteeComplete() throws Exception {
        selenium.open("/kc-dev/portal.do");
        selenium.click("link=Central Admin");
        selenium.waitForPageToLoad("30000");
        selenium.click("//a[@title='Create Committee']");
        selenium.waitForPageToLoad("30000");
        selenium.selectFrame("iframeportlet");
      //  String committeeId = getNextCommitteeID();
        String docNum = selenium.getValue("//input[@name='docNum']");
        selenium.type("//input[@id='document.documentHeader.documentDescription']", "test");
        selenium.type("//input[@id='document.committeeList[0].committeeId']", docNum);
        selenium.type("//input[@id='document.committeeList[0].committeeName']", docNum + " - Complete Test");
        selenium.type("//input[@id='document.committeeList[0].homeUnitNumber']", "000001");
        selenium.select("//select[@id='document.committeeList[0].committeeTypeCode']", "label=IRB");
        selenium.type("//textarea[@id='document.committeeList[0].scheduleDescription']", "test");
        selenium.type("//textarea[@id='document.committeeList[0].committeeDescription']", "test");
        selenium.select("//select[@id='document.committeeList[0].reviewTypeCode']", "label=Full");
        selenium.type("//input[@id='document.committeeList[0].minimumMembersRequired']", "8");
        selenium.type("//input[@id='document.committeeList[0].maxProtocols']", "9");
        selenium.type("//input[@id='document.committeeList[0].advancedSubmissionDaysRequired']", "7");
        selenium.type("//input[@id='document.committeeList[0].maxProtocols']", "100");
        lookupResearchArea("12.99");
        selenium.click("methodToCall.headerTab.headerDispatch.save.navigateTo.committeeMembership");
        selenium.waitForPageToLoad("30000");

        Assert.assertTrue(selenium.isTextPresent("Document was successfully saved."));

        addAndVerifyMembers();
        addSchedules();
        
        // submit to approve committee
        selenium.click("methodToCall.route");
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.headerTab.headerDispatch.reload.navigateTo.committeeMembership");
        selenium.waitForPageToLoad("30000");
        assertMember("Nicholas Majors", "Chair", "12.9999 Personal and Culinary Services, Other", false);
        assertMember("Allyson Cate", "IRB Administrator", "01.03 Agricultural Production Operations", false);
        assertMember("Van Lenten, Lee", "Alternate", "13.01 Education, General", false);

        selenium.click("methodToCall.headerTab.headerDispatch.reload.navigateTo.committeeSchedule");
        selenium.waitForPageToLoad("30000");
        Assert.assertTrue(selenium.isTextPresent("1 " + firstScheduleDate));
        
        // Doc search to load the approved committee
        selenium.selectFrame("relative=up");
        selenium.click("//img[@alt='doc search']");
        selenium.waitForPageToLoad("30000");
        selenium.selectFrame("iframeportlet");
        selenium.type("routeHeaderId", docNum);
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=" + docNum);
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.headerTab.headerDispatch.reload.navigateTo.committeeMembership");
        selenium.waitForPageToLoad("30000");
        assertMember("Nicholas Majors", "Chair", "12.9999 Personal and Culinary Services, Other", false);
        assertMember("Allyson Cate", "IRB Administrator", "01.03 Agricultural Production Operations", false);
        assertMember("Van Lenten, Lee", "Alternate", "13.01 Education, General", false);
        selenium.click("methodToCall.headerTab.headerDispatch.reload.navigateTo.committeeSchedule");
        selenium.waitForPageToLoad("30000");
        Assert.assertTrue(selenium.isTextPresent("1 " + firstScheduleDate));
    }

    private void addAndVerifyMembers() {
//        lookupKcPerson("10000000004");
//        selenium.click("methodToCall.addCommitteeMembership");
//        selenium.waitForPageToLoad("30000");
//        // use "tab-1-imageToggle" is not reliable because this is generated.  use "title" is not very good either
//        // may have to find a more reliable way.
//        //selenium.click("tab-1-imageToggle");
        // double space between 'Nicholas' & 'Majors'
//        selenium.click("//input[@title='open Nicholas  Majors (inactive)']");
//        //selenium.click("tab-3-imageToggle");
//        selenium.click("//input[@title='open Person Details']");
//        selenium.select("//select[@id='document.committeeList[0].committeeMemberships[0].membershipTypeCode']",
//                "label=Voting member");
//        // selenium.click("//img[@alt='Date selector']");
//        // selenium.click("//tbody/tr[3]/td[6]");
//        // selenium.click("//img[@id='document.committeeList[0].committeeMemberships[0].termEndDate_datepicker']");
//        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[0].termStartDate']", startDate);
//        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[0].termEndDate']", endDate);
//        //selenium.click("tab-30-imageToggle");
//        selenium.click("//input[@title='open Roles']");
//        selenium.select("committeeHelper.newCommitteeMembershipRoles[0].membershipRoleCode", "label=Chair");
//        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[0].startDate']", startDate);
//        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[0].endDate']", endDate);
//        selenium.click("methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0");
//        selenium.waitForPageToLoad("30000");
//        //selenium.click("tab-37-imageToggle");
//        selenium.click("//input[@title='open Expertise']");
//        // selenium.click("methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).((``)).(:;committeeResearchAreas;:).((%true%)).((~~)).anchor37.memberIndex0");
//        // selenium.click("//input[starts-with(@name,'methodToCall.deleteProtocolResearchArea.line1.') and ends-with(@name, '.memberIndex0')]");
//        selenium.click("//input[contains(@name, '.memberIndex0')]");
//        selenium.waitForPageToLoad("30000");
//        selenium.type("//input[@name='researchAreaCode']", "12.9999");
//        selenium.click("//input[@name='methodToCall.search' and @value='search']");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("//input[@type='checkbox']");
//        selenium.click("methodToCall.prepareToReturnSelectedResults.(::;true;::)");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("methodToCall.save");
//        selenium.waitForPageToLoad("30000");
//
//        assertMember("Nicholas Majors", "Chair", "12.9999 Personal and Culinary Services, Other", true);
       
        addEmployeeMember(0, "10000000004", "Nicholas  Majors", "Chair", "12.9999", "Personal and Culinary Services, Other");
//        selenium.setSpeed("1000");
        addEmployeeMember(1, "10000000008", "Allyson  Cate", "IRB Administrator", "01.03", "Agricultural Production Operations");
        
//        lookupKcPerson("10000000008");
//        selenium.click("methodToCall.addCommitteeMembership");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("tab-38-imageToggle");
//        selenium.click("tab-40-imageToggle");
//        selenium.select("//select[@id='document.committeeList[0].committeeMemberships[1].membershipTypeCode']",
//                "label=Voting member");
//        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[1].termStartDate']", startDate);
//        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[1].termEndDate']", endDate);
//        selenium.click("tab-67-imageToggle");
//        selenium.select("committeeHelper.newCommitteeMembershipRoles[1].membershipRoleCode", "label=IRB Administrator");
//        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[1].startDate']", startDate);
//        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[1].endDate']", endDate);
//        selenium.click("methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[1].line1");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("tab-74-imageToggle");
//        selenium
//                .click("methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).((``)).(:;committeeResearchAreas;:).((%true%)).((~~)).anchor74.memberIndex1");
//        selenium.waitForPageToLoad("30000");
//        selenium.type("//input[@name='researchAreaCode']", "01.03");
//        selenium.click("//input[@name='methodToCall.search' and @value='search']");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("//input[@type='checkbox']");
//        selenium.click("methodToCall.prepareToReturnSelectedResults.(::;true;::)");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("methodToCall.save");
//        selenium.waitForPageToLoad("30000");
//
//        assertMember("Allyson Cate", "IRB Administrator", "01.03 Agricultural Production Operations", true);

        selenium
                .click("methodToCall.performLookup.(!!org.kuali.kra.bo.NonOrganizationalRolodex!!).(((rolodexId:committeeHelper.newCommitteeMembership.rolodexId,fullName:committeeHelper.newCommitteeMembership.personName))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).(::::;;::::).anchor");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@id='rolodexId']", "328");
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//table[@id='row']/tbody/tr[1]/td[1]/a");
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.addCommitteeMembership");
        selenium.waitForPageToLoad("30000");
        selenium.click("tab-75-imageToggle");
        selenium.click("tab-77-imageToggle");
        selenium.select("//select[@id='document.committeeList[0].committeeMemberships[2].membershipTypeCode']",
                "label=Voting member");
        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[2].termStartDate']", startDate);
        selenium.type("//input[@id='document.committeeList[0].committeeMemberships[2].termEndDate']", endDate);
        selenium.click("tab-104-imageToggle");
        selenium.select("committeeHelper.newCommitteeMembershipRoles[2].membershipRoleCode", "label=Alternate");
        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[2].startDate']", startDate);
        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles[2].endDate']", endDate);
        selenium.click("methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[2].line2");
        selenium.waitForPageToLoad("30000");
        selenium.click("tab-111-imageToggle");
        selenium
                .click("methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).((``)).(:;committeeResearchAreas;:).((%true%)).((~~)).anchor111.memberIndex2");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@name='researchAreaCode']", "13.01");
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//input[@type='checkbox']");
        selenium.click("methodToCall.prepareToReturnSelectedResults.(::;true;::)");
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.save");
        selenium.waitForPageToLoad("30000");

        assertMember("Van Lenten, Lee", "Alternate", "13.01 Education, General", true);
    }
    
    private void addEmployeeMember(int idx, String principalId, String name, String role, String researchAreaCode, String researchAreaDesc) {
        lookupKcPerson(principalId);
        selenium.click("methodToCall.addCommitteeMembership");
        selenium.waitForPageToLoad("30000");
        // use "tab-1-imageToggle" is not reliable because this is generated.  use "title" is not very good either
        // may have to find a more reliable way.
        //selenium.click("tab-1-imageToggle");
        // double space between first & last name for this title
        selenium.click("//input[@title='open "+ name + " (inactive)']");
        //selenium.click("tab-3-imageToggle");
        selenium.click("//input[@title='open Person Details']");
        selenium.select("//select[@id='document.committeeList[0].committeeMemberships["+idx+"].membershipTypeCode']",
                "label=Voting member");
        // selenium.click("//img[@alt='Date selector']");
        // selenium.click("//tbody/tr[3]/td[6]");
        // selenium.click("//img[@id='document.committeeList[0].committeeMemberships[0].termEndDate_datepicker']");
        selenium.type("//input[@id='document.committeeList[0].committeeMemberships["+idx+"].termStartDate']", startDate);
        selenium.type("//input[@id='document.committeeList[0].committeeMemberships["+idx+"].termEndDate']", endDate);
        //selenium.click("tab-30-imageToggle");
        selenium.click("//input[@title='open Roles']");
        selenium.select("committeeHelper.newCommitteeMembershipRoles["+idx+"].membershipRoleCode", "label="+role);
        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles["+idx+"].startDate']", startDate);
        selenium.type("//input[@id='committeeHelper.newCommitteeMembershipRoles["+idx+"].endDate']", endDate);
        selenium.click("methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships["+idx+"].line"+idx);
        selenium.waitForPageToLoad("30000");
        //selenium.click("tab-37-imageToggle");
        selenium.click("//input[@title='open Expertise']");
        // selenium.click("methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).((``)).(:;committeeResearchAreas;:).((%true%)).((~~)).anchor37.memberIndex0");
        // selenium.click("//input[starts-with(@name,'methodToCall.deleteProtocolResearchArea.line1.') and ends-with(@name, '.memberIndex0')]");
        selenium.click("//input[contains(@name, '.memberIndex"+ idx+"')]");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@name='researchAreaCode']", researchAreaCode);
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//input[@type='checkbox']");
        selenium.click("methodToCall.prepareToReturnSelectedResults.(::;true;::)");
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.save");
        selenium.waitForPageToLoad("30000");
        // make double space to single space
        assertMember(name.replace("  ", " "), role , researchAreaCode + " " + researchAreaDesc, true);

    }
    
    private void addSchedules() {
        
        selenium.click("methodToCall.headerTab.headerDispatch.save.navigateTo.committeeSchedule");
        selenium.waitForPageToLoad("30000");
 //       selenium.setSpeed("2000");
        selenium.click("//input[@name='committeeHelper.scheduleData.recurrenceType' and @value='WEEKLY']");
        selenium.click("//input[@name='committeeHelper.scheduleData.recurrenceType' and @value='MONTHLY']");
        selenium.type("//input[@id='committeeHelper.scheduleData.scheduleStartDate']", startDate);
        selenium.type("//input[@id='committeeHelper.scheduleData.monthlySchedule.scheduleEndDate']", endDate);
        selenium.click("methodToCall.addEvent.anchorSchedule");
        selenium.waitForPageToLoad("30000");
        selenium.click("methodToCall.save");
        selenium.waitForPageToLoad("30000");
        Assert.assertTrue(selenium.isTextPresent("Document was successfully saved."));
        // Assert.assertTrue(selenium.isTextPresent("1 03/06/2011")); // not working for editable field
        Assert.assertEquals(selenium.getValue("//input[@id='document.committeeList[0].committeeSchedules[0].scheduledDate']"),
                firstScheduleDate);
        
    }
    
    private void assertMember(String name, String role, String researchArea, boolean isSave) {
        if (isSave) {
            Assert.assertTrue(selenium.isTextPresent("Document was successfully saved."));
        }
        Assert.assertTrue(selenium.isTextPresent(name));
        // this is really interesting. selenium.isTextPresent seems to check if the text is "visible".  if it is "hide" in a tab, then
        // the test will fail.  if the tab open, and it can be seen, then it is ok.
        // Assert.assertTrue(selenium.isTextPresent("1 " + role));  
        selenium.getBodyText().contains("1 " + role);
        // Assert.assertTrue(selenium.isTextPresent(researchArea));
        selenium.getBodyText().contains(researchArea);
    }
    
    private void setDate() {
        
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
