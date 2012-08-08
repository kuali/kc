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
package org.kuali.kra.irb.actions.decision;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;

public class CommitteeDecisionRuleTest extends CommitteeDecisionRuleBase {
    
    private CommitteeDecisionRule rule;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new CommitteeDecisionRule();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        rule = null;
        
        super.tearDown();
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