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

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.committee.service.CommitteeScheduleAttendanceService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public abstract class CommitteeDecisionRuleBase extends KcUnitTestBase {
    private CommitteeDecision committeeDecision;
    protected CommitteeDecision buildValidCommitteeDecision() {
        committeeDecision = new CommitteeDecision(null);
        //committeeDecision.setAbstainCount(new Integer(0));
        committeeDecision.setMotion(MotionValuesFinder.APPROVE);
        committeeDecision.setNoCount(new Integer(0));
        //committeeDecision.setProtocolId(protocol.getProtocolId());
        committeeDecision.setVotingComments("just some dumb comments");
        committeeDecision.setYesCount(new Integer(2));
        committeeDecision.getAbstainers().add(getBasicAbstainer());
        committeeDecision.getRecused().add(getBasicRescuser());
        return committeeDecision;
    }
    
    protected CommitteePerson getBasicAbstainer() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(1));
        return person;
    }
    
    protected CommitteePerson getBasicRescuser() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(2));
        return person;
    }
    
    protected CommitteePerson getBasicPerson() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(3));
        return person;
    }
    
    public class MockCommitteeScheduleAttendanceService implements CommitteeScheduleAttendanceService {

        public Set<String> getActualVotingMembersPresent(String committeeId, String scheduleId) {
            return new HashSet<String>(committeeDecision.getTotalVoteCount());
        }

        public Set<String> getVotingMembersPresent(String committeeId, String scheduleId) {
            return new HashSet<String>(committeeDecision.getTotalVoteCount());
         }

        public int getActualVotingMembersCount(String committeeId, String scheduleId) {
            return committeeDecision.getTotalVoteCount();
        }
        
    }
}
