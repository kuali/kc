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
package org.kuali.kra.irb.actions.decision;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import static org.junit.Assert.*;
public class CommitteeDecisionRuleTest extends CommitteeDecisionRuleBase {
    
    private CommitteeDecisionRule rule;

    @Before
    public void setUp() throws Exception {

        rule = new CommitteeDecisionRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        
    }
    
    /**
     * Test a valid approve.
     * @throws Exception
     */
    @Test
    public void testValidApprove() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, null, null, protocolDocument.getProtocol(), false);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, NO_COUNT));
        assertTrue(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test an invalid approve with no Yes count.
     * @throws Exception
     */
    @Test
    public void testInvalidApprove() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, null, NO_COUNT, null, null, protocolDocument.getProtocol(), false);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(null, NO_COUNT));
        assertFalse(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test a valid disapprove with more no votes than yes votes.
     * @throws Exception
     */
    @Test
    public void testValidDisapprove() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.DISAPPROVE, YES_COUNT, null, null, null, protocolDocument.getProtocol(), true);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, 5));
        assertTrue(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test an invalid disapprove with no No count.
     * @throws Exception
     */
    @Test
    public void testInvalidDisapprove() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.DISAPPROVE, YES_COUNT, null, null, null, protocolDocument.getProtocol(), false);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, null));
        assertFalse(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test a valid SMR with a single comment.
     * @throws Exception
     */
    @Test
    public void testValidSMR() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS, YES_COUNT, NO_COUNT, null, null, protocolDocument.getProtocol(), true);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, NO_COUNT));
        assertTrue(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test an invalid SMR with no comment.
     * @throws Exception
     */
    @Test
    public void testInvalidSMR() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS, YES_COUNT, NO_COUNT, null, null, protocolDocument.getProtocol(), false);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, NO_COUNT));
        assertFalse(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test a valid SRR with a single comment.
     * @throws Exception
     */
    @Test
    public void testValidSRR() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED, YES_COUNT, NO_COUNT, null, null, protocolDocument.getProtocol(), true);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, NO_COUNT));
        assertTrue(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }
    
    /**
     * Test an invalid SRR with no comment.
     * @throws Exception
     */
    @Test
    public void testInvalidSRR() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED, YES_COUNT, NO_COUNT, null, null, protocolDocument.getProtocol(), false);
        rule.setAttendanceService(getMockCommitteeScheduleAttendanceService(YES_COUNT, NO_COUNT));
        assertFalse(rule.proccessCommitteeDecisionRule(protocolDocument, decision));
    }

}
