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
public class CommitteeDecisionRecuserRule extends CommitteeDecisionVoterRuleBase implements ExecuteCommitteeDecisionRecuserRule<CommitteeDecision> {
    
    @Override
    public boolean proccessCommitteeDecisionRecuserRule(ProtocolDocumentBase document, CommitteeDecision committeeDecision) {
        boolean retVal = true; 
        
        if(!processVoter(committeeDecision.getNewRecused(), committeeDecision.getAbstainers(), committeeDecision.getRecused())) {
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + ".newRecused.membershipId", 
                    KeyConstants.ERROR_PROTOCOL_RECORD_COMMITTEE_ABSTAIN_RECUSED_ALREADY_EXISTS);
            retVal = false;
        }
        return retVal;
    }
}
