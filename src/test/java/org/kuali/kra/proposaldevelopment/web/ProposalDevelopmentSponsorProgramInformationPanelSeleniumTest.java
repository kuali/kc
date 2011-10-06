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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests tests the Sponsor & Program Information tab in the Proposal page of a Development Proposal.
 */
public class ProposalDevelopmentSponsorProgramInformationPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PRIME_SPONSOR_NAME_DIV = "div[id='primeSponsorName.div']";
    
    private static final String LIST_PREFIX = "document.developmentProposalList[0].";

    private static final String DEADLINE_DATE_ID = "deadlineDate";
    private static final String DEADLINE_TYPE_ID = "deadlineType";
    private static final String PRIME_SPONSOR_CODE_ID_ID = "primeSponsorCode";
    private static final String NSF_CODE_ID = "nsfCode";
    private static final String AGENCY_DIVISION_CODE_ID = "agencyDivisionCode";
    private static final String PROGRAM_ANNOUNCEMENT_TITLE_ID = "programAnnouncementTitle";
    private static final String NOTICE_OF_OPPORTUNITY_CODE_ID = "noticeOfOpportunityCode";
    private static final String CFDA_NUMBER_ID = "cfdaNumber";
    private static final String PROGRAM_ANNOUNCEMENT_NUMBER_ID = "programAnnouncementNumber";
    private static final String SPONSOR_PROPOSAL_NUMBER_ID = "sponsorProposalNumber";
    private static final String SUBCONTRACTS_ID = "subcontracts";
    private static final String AGENCY_PROGRAM_CODE_ID = "agencyProgramCode";
    
    private static final String LIST_DEADLINE_DATE_ID = LIST_PREFIX + DEADLINE_DATE_ID;
    private static final String LIST_DEADLINE_TYPE_ID = LIST_PREFIX + DEADLINE_TYPE_ID;
    private static final String LIST_PRIME_SPONSOR_CODE_ID = LIST_PREFIX + PRIME_SPONSOR_CODE_ID_ID;
    private static final String LIST_NSF_CODE_ID = LIST_PREFIX + NSF_CODE_ID;
    private static final String LIST_AGENCY_DIVISION_CODE_ID = LIST_PREFIX + AGENCY_DIVISION_CODE_ID;
    private static final String LIST_PROGRAM_ANNOUNCEMENT_TITLE_ID = LIST_PREFIX + PROGRAM_ANNOUNCEMENT_TITLE_ID;
    private static final String LIST_NOTICE_OF_OPPORTUNITY_CODE_ID = LIST_PREFIX + NOTICE_OF_OPPORTUNITY_CODE_ID;
    private static final String LIST_CFDA_NUMBER_ID = LIST_PREFIX + CFDA_NUMBER_ID;
    private static final String LIST_PROGRAM_ANNOUNCEMENT_NUMBER_ID = LIST_PREFIX + PROGRAM_ANNOUNCEMENT_NUMBER_ID;
    private static final String LIST_SPONSOR_PROPOSAL_NUMBER_ID = LIST_PREFIX + SPONSOR_PROPOSAL_NUMBER_ID;
    private static final String LIST_SUBCONTRACTS_ID = LIST_PREFIX + SUBCONTRACTS_ID;
    private static final String LIST_AGENCY_PROGRAM_CODE_ID = LIST_PREFIX + AGENCY_PROGRAM_CODE_ID;

    private static final String DEADLINE_DATE = "08/21/2008";
    private static final String PRIME_SPONSOR_CODE = "000659";
    private static final String DEADLINE_TYPE = "Postmark";
    private static final String NSF_CODE = "Law - Non-Science and Engineering Fields: J.02";
    private static final String AGENCY_DIVISION_CODE = "123";
    private static final String PROGRAM_ANNOUNCEMENT_TITLE = "we want to give you money";
    private static final String NOTICE_OF_OPPORTUNITY = "Unsolicited";
    private static final String CDFA_NUMBER = "12.456";
    private static final String PROGRAM_ANNOUNCEMENT_NUMBER = "Program #122";
    private static final String SPONSOR_PROPOSAL_NUMBER = "234567";
    private static final String SUBCONTRACTS = "on";
    private static final String AGENCY_PROGRAM_CODE = "456";
    private static final String PRIME_SPONSOR_NAME = "National Energy Technology Laboratory";
    
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
     * Test the addition of sponsor and program information.
     *
     * @throws Exception
     */
    @Test
    public void testAddSponsorProgramInformation() throws Exception {
        helper.createProposalDevelopment();

        helper.set(LIST_DEADLINE_DATE_ID, DEADLINE_DATE);
        helper.set(LIST_DEADLINE_TYPE_ID, DEADLINE_TYPE);
        helper.set(LIST_PRIME_SPONSOR_CODE_ID, PRIME_SPONSOR_CODE);
        helper.set(LIST_NSF_CODE_ID, NSF_CODE);
        helper.set(LIST_AGENCY_DIVISION_CODE_ID, AGENCY_DIVISION_CODE);
        helper.set(LIST_PROGRAM_ANNOUNCEMENT_TITLE_ID, PROGRAM_ANNOUNCEMENT_TITLE);
        helper.set(LIST_NOTICE_OF_OPPORTUNITY_CODE_ID, NOTICE_OF_OPPORTUNITY);
        helper.set(LIST_CFDA_NUMBER_ID, CDFA_NUMBER);
        helper.set(LIST_PROGRAM_ANNOUNCEMENT_NUMBER_ID, PROGRAM_ANNOUNCEMENT_NUMBER);
        helper.set(LIST_SPONSOR_PROPOSAL_NUMBER_ID, SPONSOR_PROPOSAL_NUMBER);
        helper.set(LIST_SUBCONTRACTS_ID, SUBCONTRACTS);
        helper.set(LIST_AGENCY_PROGRAM_CODE_ID, AGENCY_PROGRAM_CODE);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertElementContains(LIST_DEADLINE_DATE_ID, DEADLINE_DATE);
        helper.assertElementContains(LIST_DEADLINE_TYPE_ID, DEADLINE_TYPE);
        helper.assertElementContains(LIST_PRIME_SPONSOR_CODE_ID, PRIME_SPONSOR_CODE);
        helper.assertSelectorContains(PRIME_SPONSOR_NAME_DIV, PRIME_SPONSOR_NAME);
        helper.assertElementContains(LIST_NSF_CODE_ID, NSF_CODE);
        helper.assertElementContains(LIST_AGENCY_DIVISION_CODE_ID, AGENCY_DIVISION_CODE);
        helper.assertElementContains(LIST_PROGRAM_ANNOUNCEMENT_TITLE_ID, PROGRAM_ANNOUNCEMENT_TITLE);
        helper.assertElementContains(LIST_NOTICE_OF_OPPORTUNITY_CODE_ID, NOTICE_OF_OPPORTUNITY);
        helper.assertElementContains(LIST_CFDA_NUMBER_ID, CDFA_NUMBER);
        helper.assertElementContains(LIST_PROGRAM_ANNOUNCEMENT_NUMBER_ID, PROGRAM_ANNOUNCEMENT_NUMBER);
        helper.assertElementContains(LIST_SPONSOR_PROPOSAL_NUMBER_ID, SPONSOR_PROPOSAL_NUMBER);
        helper.assertElementContains(LIST_SUBCONTRACTS_ID, SUBCONTRACTS);
        helper.assertElementContains(LIST_AGENCY_PROGRAM_CODE_ID, AGENCY_PROGRAM_CODE);
    }

    /**
     * Test that the prime sponsor name from one document does not display on a brand new document (see KRACOEUS-212).
     *
     * @throws Exception
     */
    @Test
    public void testAddSponsorProgramInformationOnNewDocument() throws Exception {
        helper.createProposalDevelopment();

        helper.set(LIST_PRIME_SPONSOR_CODE_ID, PRIME_SPONSOR_CODE);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(LIST_PRIME_SPONSOR_CODE_ID, PRIME_SPONSOR_CODE);
        helper.assertSelectorContains(PRIME_SPONSOR_NAME_DIV, PRIME_SPONSOR_NAME);

        helper.createProposalDevelopment();

        helper.assertElementContains(LIST_PRIME_SPONSOR_CODE_ID, Constants.EMPTY_STRING);

        helper.assertSelectorContains(PRIME_SPONSOR_NAME_DIV, Constants.EMPTY_STRING);
    }

}