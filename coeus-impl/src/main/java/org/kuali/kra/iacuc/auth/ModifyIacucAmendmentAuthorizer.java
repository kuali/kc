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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;

public abstract class ModifyIacucAmendmentAuthorizer extends ModifyIacucProtocolAuthorizer {
    
    private String moduleTypeCode;
    private ProtocolAmendRenewService protocolAmendRenewService;
    
    protected ModifyIacucAmendmentAuthorizer(String moduleTypeCode) {
        this.moduleTypeCode = moduleTypeCode;
    }

    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        ProtocolBase protocol = task.getProtocol();
        boolean hasPermission = super.isAuthorized(userId, task);

        if (hasPermission && protocol.isRenewalWithoutAmendment()) {
            hasPermission = IacucProtocolModule.ADD_MODIFY_ATTACHMENTS.equals(moduleTypeCode) || canModifyModule(protocol, moduleTypeCode);
        } else if (hasPermission && isAmendmentOrRenewal(protocol)) {
            hasPermission = canModifyModule(protocol, moduleTypeCode);
        }
        
        if (hasPermission && protocol.isCorrectionMode()) {
            hasPermission = canCorrectModule(protocol, moduleTypeCode);
        }
        
        return hasPermission;
    }
    
    private boolean canCorrectModule(ProtocolBase protocol, String moduleTypeCode) {
        List<String> availableModules = new ArrayList<String>();
        
        try {
            availableModules = protocolAmendRenewService.getAvailableModules(protocol.getProtocolNumber());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        
        return availableModules.contains(moduleTypeCode);
    }
    
    /**
     * For amendment (or a renewal with an amendment), the user can only modify certain modules (parts) of the protocol. Determine
     * if a certain module can be modified or not.
     * 
     * @param protocol the amendment protocol
     * @param moduleTypeCode the module type code
     * @return true if the module can be modified; otherwise false
     */
    private boolean canModifyModule(ProtocolBase protocol, String moduleTypeCode) {
        return protocol.getProtocolAmendRenewal().hasModule(moduleTypeCode);
    }

    public void setProtocolAmendRenewService(ProtocolAmendRenewService protocolAmendRenewService) {
        this.protocolAmendRenewService = protocolAmendRenewService;
    }    

}
