package org.kuali.kra.irb.selenium;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.springframework.util.StringUtils;


public class ProtocolSubmitTest extends ProtocolRequiredFieldsTestBase {
    private Committee selectedCommittee;
    private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    @Before
    public void setUp() throws Exception {
        super.setUp();
        getCommittee();
    }

	@Test
	public void testProtocolSubmit() throws Exception {
 //       selenium.setSpeed("1000");
        selenium.open("/kc-dev/portal.do");
        selenium.click("link=Researcher");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Create Protocol");
        selenium.waitForPageToLoad("30000");
        selenium.selectFrame("iframeportlet");
        setupRequiredFields("Submit test", "10000000002", "000001");
        selenium.waitForPageToLoad("30000");
		selenium.click("methodToCall.headerTab.headerDispatch.save.navigateTo.protocolActions");
		selenium.waitForPageToLoad("60000");
     //   selenium.setSpeed("2000");
		selenium.click("tab-RequestanAction-imageToggle");
		selenium.click("tab-:SubmitforReview-imageToggle");
		selenium.select("actionHelper.protocolSubmitAction.submissionTypeCode", "label=Initial Protocol Application for Approval");
		selenium.select("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "label=Full");
//		selenium.select("actionHelper.protocolSubmitAction.committeeId", "label=test");
//		selenium.select("actionHelper.protocolSubmitAction.scheduleId", "label=02-06-2012, [no location], 12:00 PM");
//		selenium.select("actionHelper.protocolSubmitAction.scheduleId", "label=01-06-2012, [no location], 12:00 PM");
// need to have committee set up before this test.
		selenium.select("actionHelper.protocolSubmitAction.committeeId", "label=" + selectedCommittee.getCommitteeName());
		selenium.click("//option[@value='"+selectedCommittee.getCommitteeId()+"']");
 //       selenium.fireEvent("actionHelper.protocolSubmitAction.committeeId", "change");
       // selenium.runScript("$j('#actionHelper.protocolSubmitAction.committeeId').trigger('change')");
        selenium.waitForCondition("selenium.browserbot.getCurrentWindow().document.getElementById('actionHelper.protocolSubmitAction.scheduleId')", "10000");

		selenium.select("actionHelper.protocolSubmitAction.scheduleId", "label="+dateFormat.format(selectedCommittee.getCommitteeSchedules().get(0).getScheduledDate())+", [no location], 12:00 PM");
        // onchange may not work for all browser, then use runscript to force it.
		//  selenium.fireEvent("actionHelper.protocolSubmitAction.scheduleId", "change");
		// still encounter problem occasionally with runscript
        selenium.runScript("$j('#actionHelper.protocolSubmitAction.scheduleId').trigger('change')");
        // following is working
     //   selenium.waitForCondition("selenium.browserbot.getCurrentWindow().document.getElementsByName('actionHelper.protocolSubmitAction.reviewer[0].reviewerTypeCode')", "10000");
        selenium.waitForCondition("selenium.isTextPresent('Nicholas Majors')", "10000");
		selenium.select("actionHelper.protocolSubmitAction.reviewer[0].reviewerTypeCode", "label=primary");
		selenium.select("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "label=Expedited");
 //       selenium.fireEvent("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "change");
        selenium.runScript("$j('#actionHelper.protocolSubmitAction.protocolReviewTypeCode').trigger('change')");
        Assert.assertTrue(selenium.isTextPresent("Clinical studies of drugs and medical devices only when condition (a) or (b) is met."));
		selenium.select("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "label=Exempt");
        //selenium.fireEvent("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "change");
        selenium.runScript("$j('#actionHelper.protocolSubmitAction.protocolReviewTypeCode').trigger('change')");
        Assert.assertTrue(selenium.isTextPresent("Educational Research Conducted in Educational Settings - Research conducted in established"));
		selenium.select("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "label=Expedited");
        //selenium.fireEvent("actionHelper.protocolSubmitAction.protocolReviewTypeCode", "change");
        selenium.runScript("$j('#actionHelper.protocolSubmitAction.protocolReviewTypeCode').trigger('change')");
        Assert.assertTrue(selenium.isTextPresent("Clinical studies of drugs and medical devices only when condition (a) or (b) is met."));
		selenium.click("actionHelper.protocolSubmitAction.expeditedReviewCheckList[0].checked");
		selenium.click("methodToCall.submitForReview.anchor:SubmitforReview");
		selenium.waitForPageToLoad("30000");
	}

	private void getCommittee() {
	    for (Committee committee : (List<Committee>)getBusinessObjectService().findAll(Committee.class)) {
	        if (StringUtils.endsWithIgnoreCase(committee.getCommitteeName(), "- Complete Test")) {
	            if (selectedCommittee == null || selectedCommittee.getId().intValue() < committee.getId().intValue()) {
	                selectedCommittee = committee;
	            }
	            
	        }
	    }
	}
}
