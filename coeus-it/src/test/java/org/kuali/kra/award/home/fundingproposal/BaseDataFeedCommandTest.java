/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.home.fundingproposal;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public abstract class BaseDataFeedCommandTest extends KcIntegrationTestBase {
    Award award;
    InstitutionalProposal proposal;
    
    @Before
    public void setUp() throws Exception {
        award = new Award();
        proposal = new InstitutionalProposal();
        proposal.setProposalNumber("1234");
    }
    
    @After
    public void tearDown() throws Exception {
        award = null;
        proposal = null;
    }
}
