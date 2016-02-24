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
public class CommitteeDecisionAbstainerRuleTest extends CommitteeDecisionRuleBase {
    
    private CommitteeDecisionAbstainerRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new CommitteeDecisionAbstainerRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }
    
    /**
     * Tests a valid new abstainer.
     */
    @Test
    public void testValidNewAbstainer() throws Exception {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, getBasicPerson(), null, null, false);
        assertTrue(rule.proccessCommitteeDecisionAbstainerRule(null, decision));
    }
    
    /**
     * Tests invalid new abstainer which has no membership ID
     */
    @Test
    public void testInvalidNewAbstainerNoMembershipId() throws Exception {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, new CommitteePerson(), null, null, false);
        assertFalse(rule.proccessCommitteeDecisionAbstainerRule(null, decision));
    }
    
    /**
     * Tests an invalid new abstainer which is already in the abstainer list.
     */
    @Test
    public void testInvalidNewAbstainerInAbstainerList() throws Exception {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, getBasicAbstainer(), null, null, false);
        assertFalse(rule.proccessCommitteeDecisionAbstainerRule(null, decision));
    }
    
    /**
     * Tests an invalid new abstainer which is already in the recused list.
     */
    @Test
    public void testInvalidNewAbstainerInRecusedList() throws Exception {
        CommitteeDecision decision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE, YES_COUNT, NO_COUNT, getBasicRecused(), null, null, false);
        assertFalse(rule.proccessCommitteeDecisionAbstainerRule(null, decision));
    }
    
}
