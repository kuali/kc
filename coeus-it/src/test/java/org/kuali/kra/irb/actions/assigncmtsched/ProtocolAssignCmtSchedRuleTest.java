/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import org.kuali.rice.krad.util.GlobalVariables;
import static org.junit.Assert.*;
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
        assertEquals(GlobalVariables.getMessageMap().getErrorMessages().size(), 0);
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
