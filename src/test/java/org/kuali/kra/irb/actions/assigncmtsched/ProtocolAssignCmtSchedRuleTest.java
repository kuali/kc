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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the business rules for Assigning a protocol to a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolAssignCmtSchedRuleTest extends ProtocolRuleTestBase {

    private ProtocolAssignCmtSchedRule rule = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolAssignCmtSchedRule();
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
        
        ProtocolAssignCmtSchedBean actionBean = new ProtocolAssignCmtSchedBean(null);
        actionBean.setCommitteeId("1");
        
        assertTrue(rule.processAssignToCommitteeSchedule(protocolDocument, actionBean));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }

    /**
     * Test an invalid assignment, no committee selected.
     * @throws Exception
     */
    @Test
    public void testNoCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolAssignCmtSchedBean actionBean = new ProtocolAssignCmtSchedBean(null);
        actionBean.setCommitteeId("");
        
        assertFalse(rule.processAssignToCommitteeSchedule(protocolDocument, actionBean));
        assertError(Constants.PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
    }
}
