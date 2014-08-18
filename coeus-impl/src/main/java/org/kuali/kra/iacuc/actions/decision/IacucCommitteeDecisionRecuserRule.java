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
package org.kuali.kra.iacuc.actions.decision;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionVoterRuleBase;
import org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionRecuserRule;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class handles the rules for the recuser side of the committee decision.
 */
public class IacucCommitteeDecisionRecuserRule extends CommitteeDecisionVoterRuleBase<IacucCommitteePerson> implements ExecuteCommitteeDecisionRecuserRule<IacucCommitteeDecision> {
    
    @Override
    public boolean proccessCommitteeDecisionRecuserRule(ProtocolDocumentBase document, IacucCommitteeDecision committeeDecision) {
        boolean retVal = true; 
        
        if(!processVoter(committeeDecision.getNewRecused(), committeeDecision.getAbstainers(), committeeDecision.getRecused())) {
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + ".newRecused.membershipId", 
                    KeyConstants.ERROR_PROTOCOL_RECORD_COMMITTEE_ABSTAIN_RECUSED_ALREADY_EXISTS);
            retVal = false;
        }
        return retVal;
    }
}
