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
package org.kuali.kra.irb.actions.assignagenda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the business rules for Assigning a protocol to a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_schedule_status.sql", delimiter = ";")
}))
public class ProtocolAssignToAgendaRuleTest extends ProtocolRuleTestBase {

    private ProtocolAssignToAgendaRule rule = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolAssignToAgendaRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid assignment.
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testHasCommittee() throws Exception {
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolAssignToAgendaBean actionBean = new ProtocolAssignToAgendaBean(null);
        actionBean.setCommitteeId("1");
        
        assertTrue(rule.processAssignToAgendaRule(protocolDocument, actionBean));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }

    /**
     * Test an invalid assignment, no committee selected.
     * @throws Exception
     */
    @Test
    public void testNoCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolAssignToAgendaBean actionBean = new ProtocolAssignToAgendaBean(null);
        actionBean.setCommitteeId("");
        
        assertFalse(rule.processAssignToAgendaRule(protocolDocument, actionBean));
        assertError(Constants.PROTOCOL_ASSIGN_TO_AGENDA_PROPERTY_KEY + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
    }
}
