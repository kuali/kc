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

import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class SeleniumUnitTest extends KcSeleniumTestBase {

    @Test
    public void testSeleniumUnit() throws Exception {
        clickResearcherTab();
        
        click("Create Proposal");
        
        set("document.documentHeader.documentDescription", "ProposalDevelopmentDocumentTest");
        set("document.developmentProposalList[0].sponsorCode", "005770");
        set("document.developmentProposalList[0].title", "project title");
        set("document.developmentProposalList[0].requestedStartDateInitial", String.format("%tD", new Date()));
        set("document.developmentProposalList[0].requestedEndDateInitial", String.format("%tD", new Date()));
        set("document.developmentProposalList[0].proposalTypeCode", "New");
        set("document.developmentProposalList[0].ownedByUnitNumber", "000001 - University");
        set("document.developmentProposalList[0].activityTypeCode", "Instruction");
        
        closeAndSearchDocument();
    }

}