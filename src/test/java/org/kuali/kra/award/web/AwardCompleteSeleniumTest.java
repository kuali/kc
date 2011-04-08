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
    
    private static final String TEMPLATE_CODE_TAG = "document.award.templateCode";
    private static final String PERSON_ID_TAG = "projectPersonnelBean.personId";
    
    private static final String TEMPLATE_CODE_ID = "templateCode";
    private static final String PERSON_ID_ID = "personId";
    private static final String CONTACT_ROLE_CODE_ID = "projectPersonnelBean.contactRoleCode";
    private static final String CREDIT_SPLITS_ID = "document.awardList[0].projectPersons[0].creditSplits[%d].credit";
    private static final String UNIT_CREDIT_SPLITS_ID = "document.awardList[0].projectPersons[0].units[0].creditSplits[%d].credit";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customDataHelper.customDataValues[3].value";
    private static final String BILLING_ELEMENT_ID = "customDataHelper.customDataValues[0].value";
    
    private static final String TEST_SPONSOR_TEMPLATE_CODE = "1";
    private static final String NICHOLAS_MAJORS_PERSON_ID = "10000000004";
    private static final String PI_CONTACT_ROLE = "Principal Investigator";
    private static final String TOTAL_CREDIT_SPLIT = "100";
    
    private static final String YES_BUTTON = "methodToCall.processAnswer.button0";
    private static final String ADD_PERSON_BUTTON = "methodToCall.addProjectPerson";
    
    @Test
    public void testAwardComplete() {
        createAward();
        
        openTab("Sponsor Template");
        lookup(TEMPLATE_CODE_TAG, TEMPLATE_CODE_ID, TEST_SPONSOR_TEMPLATE_CODE);
        click(YES_BUTTON);
        click(YES_BUTTON);
        
        addContacts();
        
        addCustomData();
        
        submit();
    }
    
    private void addContacts() {
        clickAwardContactsPage();

        openTab("Key Personnel and Credit Split");
        lookup(PERSON_ID_TAG, PERSON_ID_ID, NICHOLAS_MAJORS_PERSON_ID);
        set(CONTACT_ROLE_CODE_ID, PI_CONTACT_ROLE);
        click(ADD_PERSON_BUTTON);

        for (int i = 0; i < 4; i++) {
            set(String.format(CREDIT_SPLITS_ID, i), TOTAL_CREDIT_SPLIT);
            set(String.format(UNIT_CREDIT_SPLITS_ID, i), TOTAL_CREDIT_SPLIT);
        }
    }
    
    private void addCustomData() {
        clickAwardCustomDataPage();
        
        openTab("Personnel Items for Review");
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    private void submit() {
        clickAwardActionsPage();
        
        routeDocument();
        assertRoute();
    }

}
