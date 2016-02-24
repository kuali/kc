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
package org.kuali.kra.iacuc.auth;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * Is the user allowed to disapprove protocols and the action is currently not available?
 */
public class DisapproveIacucProtocolUnavailableAuthorizer extends IacucProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {        
        ProtocolActionBase lastAction = task.getProtocol().getLastProtocolAction();
        ProtocolSubmissionBase lastSubmission = task.getProtocol().getProtocolSubmission();
        
        return (!canPerform(lastAction, lastSubmission)) && hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
    }
    
    private boolean canPerform(ProtocolActionBase lastAction, ProtocolSubmissionBase lastSubmission) {
        boolean canPerform = false;
        
        if (lastAction != null && lastSubmission != null) {
            canPerform = IacucProtocolActionType.RECORD_COMMITTEE_DECISION.equals(lastAction.getProtocolActionTypeCode())
                      && CommitteeDecisionMotionType.DISAPPROVE.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
        }
        
        return canPerform;
    }
    
    
}
