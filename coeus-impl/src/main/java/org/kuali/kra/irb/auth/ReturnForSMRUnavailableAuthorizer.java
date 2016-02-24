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
package org.kuali.kra.irb.auth;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * Is the user allowed to return protocols for specific minor revisions and the action is currently not available?
 */
public class ReturnForSMRUnavailableAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        ProtocolAction lastAction = task.getProtocol().getLastProtocolAction();
        ProtocolSubmission lastSubmission = task.getProtocol().getProtocolSubmission();
        
        return !canPerform(lastAction, lastSubmission) && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }
    
    private boolean canPerform(ProtocolAction lastAction, ProtocolSubmission lastSubmission) {
        boolean canPerform = false;
        
        if (lastAction != null && lastSubmission != null) {
            
            boolean traditionalSubPerform  = ProtocolActionType.RECORD_COMMITTEE_DECISION.equals(lastAction.getProtocolActionTypeCode())
            && CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
            
            boolean exemptExpeditePerform = false;
            if (lastSubmission.getProtocolReviewType() != null){
                exemptExpeditePerform =  canPerformActionOnExpeditedOrExempt(lastSubmission, lastAction);
            }
            
            
            canPerform = traditionalSubPerform || exemptExpeditePerform;
        }
        
        return canPerform;
    }
    
}
