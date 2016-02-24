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

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class ModifyIacucProtocolAuthorizer  extends IacucProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        boolean hasPermission = true;
        IacucProtocol protocol = task.getProtocol();
        Long protocolId = protocol.getProtocolId();
        if (protocolId == null) {
            
            // We have to consider the case when we are saving the protocol for the first time.
            
            hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_IACUC, PermissionConstants.CREATE_IACUC_PROTOCOL);
        } 
        else {
            /*
             * After the initial save, the protocol can only be modified has the required permission.
             */
            hasPermission = !protocol.getIacucProtocolDocument().isViewOnly() && 
                            !isPessimisticLocked(protocol.getIacucProtocolDocument()) &&
                            (!kraWorkflowService.isInWorkflow(protocol.getIacucProtocolDocument()) || protocol.isCorrectionMode()) &&
                            hasPermission(userId, protocol, PermissionConstants.MODIFY_IACUC_PROTOCOL);
    
        }
        return hasPermission;
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
