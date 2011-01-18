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
package org.kuali.kra;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class SeleniumUnitTest extends KcSeleniumTestBase {

    @Test
    public void testSeleniumUnit() throws Exception {
        selenium.open("/kc-dev/portal.jsp");
        selenium.click("link=Create Proposal");
        selenium.waitForPageToLoad("60000");
        selenium.type("//input[@id='document.documentHeader.documentDescription']", "ProposalDevelopmentDocumentTest");
        selenium.type("//input[@id='document.developmentProposalList[0].sponsorCode']", "005770");
        selenium.type("//textarea[@id='document.developmentProposalList[0].title']", "project title");
        selenium.type("//input[@id='document.developmentProposalList[0].requestedStartDateInitial']", String.format("%tD", new Date()));
        selenium.type("//input[@id='document.developmentProposalList[0].requestedEndDateInitial']", String.format("%tD", new Date()));
        selenium.select("//select[@id='document.developmentProposalList[0].proposalTypeCode']", "label=New");
        selenium.select("//select[@id='document.developmentProposalList[0].ownedByUnitNumber']", "label=000001 - University");
        selenium.select("//select[@id='document.developmentProposalList[0].activityTypeCode']", "label=Instruction");
        selenium.click("methodToCall.save");
        selenium.waitForPageToLoad("60000");
        selenium.click("//img[@alt='doc search']");
        selenium.waitForPageToLoad("60000");
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("60000");
        selenium.selectFrame("iframeportlet");
        Assert.assertTrue(selenium.isTextPresent("Proposal Development Document - ProposalDevelopmentDocumentTest"));
    }

}