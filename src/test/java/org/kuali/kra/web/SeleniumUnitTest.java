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
package org.kuali.kra.web;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the basic functionality of the Selenium unit tests.
 */
public class SeleniumUnitTest extends KcSeleniumTestBase {
    
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
     * Test that Selenium is working by creating a Development Proposal document.
     */
    @Test
    public void testSeleniumUnit() throws Exception {
        helper.clickResearcherTab();
        
        helper.click("Create Proposal");
        
        helper.set("document.documentHeader.documentDescription", "ProposalDevelopmentDocumentTest");
        helper.set("document.developmentProposalList[0].sponsorCode", "005770");
        helper.set("document.developmentProposalList[0].title", "project title");
        helper.set("document.developmentProposalList[0].requestedStartDateInitial", String.format("%tD", new Date()));
        helper.set("document.developmentProposalList[0].requestedEndDateInitial", String.format("%tD", new Date()));
        helper.set("document.developmentProposalList[0].proposalTypeCode", "New");
        helper.set("document.developmentProposalList[0].ownedByUnitNumber", "000001 - University");
        helper.set("document.developmentProposalList[0].activityTypeCode", "Instruction");
        
        helper.closeAndSearchDocument();
    }

}