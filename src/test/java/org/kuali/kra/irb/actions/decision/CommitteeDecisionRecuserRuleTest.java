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

public class CommitteeDecisionRecuserRuleTest extends CommitteeDecisionRuleBase {
    
    private CommitteeDecisionRecuserRule rule;

    @Override
    @Before
    public void setUp() throws Exception {
        rule = new CommitteeDecisionRecuserRule();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        rule = null;
    }
    
    /**
     * Tests a valid new recused.
     */
    @Test
    public void testValidNewRecused() {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, null, getBasicPerson(), null, false);
        assertTrue(rule.proccessCommitteeDecisionRecuserRule(null, decision));
    }
    
   /**
    * Tests invalid new recused which has no membership ID
    */
    @Test
    public void testInvalidNewRecusedNoMembershipId() {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, null, new CommitteePerson(), null, false);
        assertFalse(rule.proccessCommitteeDecisionRecuserRule(null, decision));
    }
    
    /**
     * Tests an invalid new recused which is already in the recused list.
     */
    @Test
    public void testInvalidNewRecusedInRecusedList() {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, null, getBasicRecused(), null, false);
        assertFalse(rule.proccessCommitteeDecisionRecuserRule(null, decision));
    }
    
    /**
     * Tests an invalid new recused which is already in the abstainer list.
     */
    @Test
    public void testInvalidNewRecusedInAbstainerList() {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, null, getBasicAbstainer(), null, false);
        assertFalse(rule.proccessCommitteeDecisionRecuserRule(null, decision));
    }

}