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
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;

public class CommitteeDecisionRuleTest extends CommitteeDecisionRuleBase {
    
    CommitteeDecisionRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new CommitteeDecisionRule();
        rule.setAttendanceService(new MockCommitteeScheduleAttendanceService());
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }
    
    @Test
    public void testProccessCommitteeDecisionRule0() throws Exception {
        ProtocolDocument document = ProtocolFactory.createProtocolDocument();
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.getReviewComments().setProtocolId(document.getProtocol().getProtocolId());
        assertTrue(rule.proccessCommitteeDecisionRule(document, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule1() throws Exception {
        ProtocolDocument document = ProtocolFactory.createProtocolDocument();
        //more no votes than yes votes .... valid disapprove
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.getReviewComments().setProtocolId(document.getProtocol().getProtocolId());
        decision.setNoCount(5);
        decision.setMotion(MotionValuesFinder.DISAPPROVE);
        assertTrue(rule.proccessCommitteeDecisionRule(document, decision));
    }
    
    /**

    @Test
    public void testProccessCommitteeDecisionRule1() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewAbstainer(getBasicPerson());
        assertTrue(rule.proccessCommitteeDecisionRule(null, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule2() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewRecused(getBasicPerson());
        assertTrue(rule.proccessCommitteeDecisionRule(null, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule3() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewRecused(getBasicRescuser());
        assertFalse(rule.proccessCommitteeDecisionRule(null, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule4() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewRecused(getBasicAbstainer());
        assertFalse(rule.proccessCommitteeDecisionRule(null, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule5() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewAbstainer(getBasicAbstainer());
        assertFalse(rule.proccessCommitteeDecisionRule(null, decision));
    }
    
    @Test
    public void testProccessCommitteeDecisionRule6() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        decision.setNewAbstainer(getBasicRescuser());
        assertFalse(rule.proccessCommitteeDecisionRule(null, decision));
    }*/
}
