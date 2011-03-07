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
package org.kuali.kra.award.web;

import org.junit.Test;
import org.kuali.kra.infrastructure.TestUtilities;

public class AwardCompleteSeleniumTest extends AwardSeleniumTestBase {
    
    private static final String GRADUATE_STUDENT_COUNT_ID = "customDataHelper.customDataValues[3].value";
    private static final String BILLING_ELEMENT_ID = "customDataHelper.customDataValues[0].value";
    
    @Test
    public void testAwardComplete() {
        createAward();
        
        openTab(10);
        lookupSponsorTemplate("1");
        click("methodToCall.processAnswer.button0");
        click("methodToCall.processAnswer.button0");
        
        addContacts();
        
        addCustomData();
        
        submit();
    }
    
    private void addContacts() {
        clickAwardContactsPage();

        openTab(0);
        lookupEmployeeContact("10000000004", "Principal Investigator");
        click("methodToCall.addProjectPerson");

        set("document.awardList[0].projectPersons[0].creditSplits[0].credit", "100");
        set("document.awardList[0].projectPersons[0].creditSplits[1].credit", "100");
        set("document.awardList[0].projectPersons[0].creditSplits[2].credit", "100");
        set("document.awardList[0].projectPersons[0].creditSplits[3].credit", "100");
        set("document.awardList[0].projectPersons[0].units[0].creditSplits[0].credit", "100");
        set("document.awardList[0].projectPersons[0].units[0].creditSplits[1].credit", "100");
        set("document.awardList[0].projectPersons[0].units[0].creditSplits[2].credit", "100");
        set("document.awardList[0].projectPersons[0].units[0].creditSplits[3].credit", "100");
    }
    
    private void addCustomData() {
        clickAwardCustomDataPage();
        
        openTab(0);
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab(2);
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    private void submit() {
        clickAwardActionsPage();
        
        routeDocument();
        assertRoute();
    }

}
