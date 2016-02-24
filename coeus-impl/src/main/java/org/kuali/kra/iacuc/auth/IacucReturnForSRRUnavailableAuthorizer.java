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

import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.infrastructure.PermissionConstants;


/**
 * Is the user allowed to return protocols for major revisions and the action is currently not available?
 */
public class IacucReturnForSRRUnavailableAuthorizer extends IacucProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {        
        IacucProtocolAction lastAction = (IacucProtocolAction) task.getProtocol().getLastProtocolAction();
        IacucProtocolSubmission lastSubmission = (IacucProtocolSubmission) task.getProtocol().getProtocolSubmission();
        
        return (!canPerform(lastAction, lastSubmission)) && hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
    }
    
    private boolean canPerform(IacucProtocolAction lastAction, IacucProtocolSubmission lastSubmission) {
        return canPerformSRR(lastAction, lastSubmission);
    }
    
}
