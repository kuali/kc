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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommitteeDecisionRuleTest {
    
    CommitteeDecisionRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new CommitteeDecisionRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }
    
    @Test
    public void testProccessCommitteeDecisionRule0() {
        CommitteeDecision decision = buildValidCommitteeDecision();
        assertTrue(rule.proccessCommitteeDecisionRule(null, decision));
    }

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
    }
    
    private CommitteeDecision buildValidCommitteeDecision() {
        CommitteeDecision committeeDecision = new CommitteeDecision();
        committeeDecision.setAbstainCount(new Integer(0));
        committeeDecision.setMotion(MotionValuesFinder.APPROVE);
        committeeDecision.setNoCount(new Integer(0));
        //committeeDecision.setProtocolId(protocol.getProtocolId());
        committeeDecision.setVotingComments("just some dumb comments");
        committeeDecision.setYesCount(new Integer(2));
        committeeDecision.getAbstainers().add(getBasicAbstainer());
        committeeDecision.getRecused().add(getBasicRescuser());
        return committeeDecision;
    }
    
    private CommitteePerson getBasicAbstainer() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(1));
        return person;
    }
    
    private CommitteePerson getBasicRescuser() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(2));
        return person;
    }
    
    private CommitteePerson getBasicPerson() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(3));
        return person;
    }

}
