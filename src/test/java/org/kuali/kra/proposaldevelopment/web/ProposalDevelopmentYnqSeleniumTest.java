/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Questions page of a Development Proposal.
 */
public class ProposalDevelopmentYnqSeleniumTest extends KcSeleniumTestBase {
    
    private static final String GRANTS_GOV_AGENCY_SPECIFIC_QUESTIONS_TAB_ID = "Grants gov Agency Specific Questions";
    private static final String PROPOSAL_QUESTIONS_TAB_ID = "Proposal Questions";
    
    private static final String LIST_PREFIX = "document.developmentProposalList[0].";
    
    private static final String YNQS_ID = LIST_PREFIX + "proposalYnq[%d].answer";

    private static final String YES_RADIO_FIELD_VALUE = "Y";
    
    private static final String NSF_BEGINNING_INVESTIGATOR_QUESTION = "NSF Beginning Investigator";
    private static final String ERROR_REVIEW_DATE = "Review Date is required";
    private static final String ERROR_EXPLANATION = "Explanation is required";
    
    private ProposalDevelopmentSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test the Question page with no errors.
     */
    @Test
    public void testProposalYnqPageNoErrors() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentQuestionsPage();
        
        helper.assertPageContains(NSF_BEGINNING_INVESTIGATOR_QUESTION);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
    }

    /**
     * Test the Question page with errors.
     */
    @Test
    public void testProposalYnqPageErrors() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentQuestionsPage();

        helper.openTab(GRANTS_GOV_AGENCY_SPECIFIC_QUESTIONS_TAB_ID);
        helper.set(String.format(YNQS_ID, 20), YES_RADIO_FIELD_VALUE);
        
        helper.openTab(PROPOSAL_QUESTIONS_TAB_ID);
        helper.set(String.format(YNQS_ID, 0), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 1), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 2), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 3), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 4), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 5), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 6), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 7), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 8), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 9), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 10), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 11), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 12), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 13), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 14), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 15), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 16), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 17), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 18), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 19), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 21), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 22), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 23), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 24), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 25), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 26), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 27), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 28), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 29), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 30), YES_RADIO_FIELD_VALUE);
        helper.set(String.format(YNQS_ID, 31), YES_RADIO_FIELD_VALUE);
        
        helper.saveDocument();
        helper.assertPageErrors();
        helper.assertPageContains(ERROR_REVIEW_DATE);
        helper.assertPageContains(ERROR_EXPLANATION);
    }

}