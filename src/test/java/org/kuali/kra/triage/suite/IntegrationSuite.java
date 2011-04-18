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
package org.kuali.kra.triage.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.kuali.kra.SeleniumUnitTest;
import org.kuali.kra.award.web.AwardCompleteSeleniumTest;
import org.kuali.kra.committee.web.CommitteeCompleteSeleniumTest;
import org.kuali.kra.institutionalproposal.web.InstitutionalProposalCompleteSeleniumTest;
import org.kuali.kra.irb.web.ProtocolCompleteSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentCompleteSeleniumTest;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentDeleteProposalTest;

@RunWith(Suite.class)
@SuiteClasses(  {
    SeleniumUnitTest.class,
    AwardCompleteSeleniumTest.class,
    CommitteeCompleteSeleniumTest.class,
    InstitutionalProposalCompleteSeleniumTest.class,
    ProposalDevelopmentCompleteSeleniumTest.class,
    ProtocolCompleteSeleniumTest.class,
    ProposalDevelopmentDeleteProposalTest.class
})
public class IntegrationSuite {}