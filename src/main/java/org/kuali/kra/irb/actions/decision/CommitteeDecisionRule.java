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

import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class runs the rules needed for committee decision recording.
 */
public class CommitteeDecisionRule extends ResearchDocumentRuleBase implements ExecuteCommitteeDecisionRule {

    /**
     * 
     * @see org.kuali.kra.irb.actions.decision.ExecuteCommitteeDecisionRule#proccessCommitteeDecisionRule(org.kuali.kra.irb.ProtocolDocument, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionRule(ProtocolDocument document, CommitteeDecision committeeDecision) {
        boolean abstaineeResults = proccessCommitteeDecisionRuleForNewAbstainee(committeeDecision);
        boolean recusedResults = proccessCommitteeDecisionRuleNewRecused(committeeDecision);
        return abstaineeResults && recusedResults;
    }
    
    private boolean proccessCommitteeDecisionRuleForNewAbstainee(CommitteeDecision committeeDecision) {
        boolean retVal = true;
        if (!checkCommitteePerson(committeeDecision.getAbstainers(), committeeDecision.getNewAbstainer())
                || !checkCommitteePerson(committeeDecision.getRecused(), committeeDecision.getNewAbstainer())) {
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_RECORD_COMMITTEE_KEY + ".newAbstainer.membershipId", 
                    KeyConstants.ERROR_PROTOCOL_RECORED_COMMITTEE_ABSTAIN_RECUSED_ALREADY_EXISTS);
            retVal = false;
        }
        return retVal;
    }
    
    private boolean proccessCommitteeDecisionRuleNewRecused(CommitteeDecision committeeDecision) {
        boolean retVal = true;        
        if (!checkCommitteePerson(committeeDecision.getAbstainers(), committeeDecision.getNewRecused())
                || !checkCommitteePerson(committeeDecision.getRecused(), committeeDecision.getNewRecused())) {
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_RECORD_COMMITTEE_KEY + ".newRecused.membershipId", 
                    KeyConstants.ERROR_PROTOCOL_RECORED_COMMITTEE_ABSTAIN_RECUSED_ALREADY_EXISTS);
            retVal = false;
        }
        return retVal;
    }
    
    private boolean checkCommitteePerson(List<CommitteePerson> people, CommitteePerson committeePersonToCheck) {
        for (CommitteePerson listPerson : people) {
            if (listPerson.getMembershipId().equals(committeePersonToCheck.getMembershipId())) {
                return false;
            }
        }
        return true;
    }

}
