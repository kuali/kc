/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
