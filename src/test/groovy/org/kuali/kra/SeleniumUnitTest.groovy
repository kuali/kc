package org.kuali.kra

import org.junit.Assert
import org.junit.Test

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase

class SeleniumUnitTest extends KcSeleniumTestBase {

    @Test
    void testSeleniumUnit() throws Exception {
        selenium.open("/kc-dev/portal.jsp")
        selenium.click("link=Create Proposal")
        selenium.waitForPageToLoad("60000")
        selenium.type("//input[@id='document.documentHeader.documentDescription']", "ProposalDevelopmentDocumentTest")
        selenium.type("//input[@id='document.developmentProposalList[0].sponsorCode']", "005770")
        selenium.type("//textarea[@id='document.developmentProposalList[0].title']", "project title")
        selenium.type("//input[@id='document.developmentProposalList[0].requestedStartDateInitial']", String.format('%tD', new Date()))
        selenium.type("//input[@id='document.developmentProposalList[0].requestedEndDateInitial']", String.format('%tD', new Date()))
        selenium.select("//select[@id='document.developmentProposalList[0].proposalTypeCode']", "label=New")
        selenium.select("//select[@id='document.developmentProposalList[0].ownedByUnitNumber']", "label=000001 - University")
        selenium.select("//select[@id='document.developmentProposalList[0].activityTypeCode']", "label=Research")
        selenium.click("methodToCall.save")
        selenium.waitForPageToLoad("60000")
        selenium.click("//img[@alt='doc search']")
        selenium.waitForPageToLoad("60000")
        selenium.click("//input[@name='methodToCall.search' and @value='search']")
        selenium.waitForPageToLoad("60000")
        selenium.selectFrame("iframeportlet")
        Assert.assertTrue(selenium.isTextPresent("Proposal Development Document - ProposalDevelopmentDocumentTest"))
    }
    
}