/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.baseline;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.kuali.kra.award.htmlunitwebtest.AwardHomeWebTest;
import org.kuali.kra.budget.web.BudgetSummaryWebTest;
import org.kuali.kra.committee.web.CommitteeWebTest;
import org.kuali.kra.irb.web.ProtocolRequiredFieldsWebTest;
import org.kuali.kra.test.OjbRepositoryMappingTest;

/**
 * This class defines a test suite that should be executed before committing any code
 * 
 * Note: Since BudgetSummaryWebTest extends from ProposalDevelopmentWebTestBase and causes the creaion of a ProposalDevelopmentDocument,
 * it satisfies both document creation requirements; that is the creation of a Proposal document and Budget document 
 * 
 */
@RunWith(Suite.class)
@SuiteClasses(  {
                AwardHomeWebTest.class,
                BudgetSummaryWebTest.class,
                CommitteeWebTest.class,
                OjbRepositoryMappingTest.class,
                ProtocolRequiredFieldsWebTest.class
                }
             )
public class PrecommitTestSuite {

}
