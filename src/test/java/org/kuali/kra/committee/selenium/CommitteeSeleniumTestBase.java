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
package org.kuali.kra.committee.selenium;

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class CommitteeSeleniumTestBase extends KcSeleniumTestBase {

    protected String getNextCommitteeID() {
        // TODO : this will cause duplicate committeeID if more than 2 test classes call this.
        // use timestamp for now.  Can use oracle sequence number too ?
            //nextCommitteeId++;
            return (new Long(new java.util.Date().getTime())).toString();
            //return nextCommitteeId.toString();
        }


    protected void lookupResearchArea(String researchAreaCode) {
        selenium.click("methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).((``)).(:;committeeResearchAreas;:).((%true%)).((~~)).anchorAreaofResearch");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@name='researchAreaCode']", researchAreaCode);
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//input[@type='checkbox']");
        selenium.click("methodToCall.prepareToReturnSelectedResults.(::;true;::)");
        selenium.waitForPageToLoad("30000");

    }
    
    protected void lookupKcPerson(String personId) {
        selenium.click("methodToCall.performLookup.(!!org.kuali.kra.bo.KcPerson!!).(((personId:committeeHelper.newCommitteeMembership.personId,fullName:committeeHelper.newCommitteeMembership.personName))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).(::::;;::::).anchor");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@id='personId']", personId);
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//table[@id='row']/tbody/tr[1]/td[1]/a");
        selenium.waitForPageToLoad("30000");
    }

}
