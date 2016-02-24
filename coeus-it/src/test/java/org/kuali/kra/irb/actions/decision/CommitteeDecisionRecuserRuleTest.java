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

import static org.junit.Assert.*;
public class CommitteeDecisionRecuserRuleTest extends CommitteeDecisionRuleBase {
    
    private CommitteeDecisionRecuserRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new CommitteeDecisionRecuserRule();
    }

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
