/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions.decision;

import java.util.List;

/**
 * 
 * This class takes care of the basic functionality of verifying on meeting voter.
 */
public abstract class CommitteeDecisionVoterRuleBase<CP extends CommitteePersonBase> {
    
    /**
     * 
     * This method manages the business rules of a voter.
     * @param voter
     * @param abstainers
     * @param recused
     * @return
     */
    protected boolean processVoter(CP voter, List<CP> abstainers, List<CP> recused) {
        boolean retVal = true;
        if (voter.getMembershipId() == null 
                || !checkCommitteePerson(abstainers, voter)
                || !checkCommitteePerson(recused, voter)) {
            retVal = false;
        }
        return retVal;
    }
    
    protected boolean checkCommitteePerson(List<CP> people, CP committeePersonToCheck) {
        for (CP listPerson : people) {
            if (listPerson.getMembershipId().equals(committeePersonToCheck.getMembershipId())) {
                return false;
            }
        }
        return true;
    }
}
