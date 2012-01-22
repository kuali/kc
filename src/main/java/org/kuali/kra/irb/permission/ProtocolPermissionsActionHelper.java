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
package org.kuali.kra.irb.permission;

import org.kuali.kra.common.permissions.web.struts.action.PermissionsActionHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.krad.document.Document;

/**
 * The Protocol Permissions Action Helper performs all of the presentation logic
 * for the Permissions tab web page.  The ProtocolPermissionsAction delegates all
 * of the work to this helper.
 */
class ProtocolPermissionsActionHelper extends PermissionsActionHelperBase {

    /**
     * Constructs a ProtocolPermissionsActionHelper.
     * @param parentAction the parent Action instance that will delegate to this helper
     */
    public ProtocolPermissionsActionHelper(ProtocolPermissionsAction parentAction) {
        super(parentAction);
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsActionHelperBase#addUserToRoleInDatabase(org.kuali.core.document.Document, java.lang.String, java.lang.String)
     */
    @Override
    protected void addUserToRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        getKraAuthorizationService().addRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    /**
     * @see org.kuali.kra.common.permissions.web.struts.action.PermissionsActionHelperBase#removeRoleFromUserInDatabase(org.kuali.core.document.Document, java.lang.String, java.lang.String)
     */
    @Override
    protected void removeUserFromRoleInDatabase(Document document, String userId, String roleName) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        getKraAuthorizationService().removeRole(userId, roleName, protocolDocument.getProtocol());
    }
    
    /**
     * Get the Protocol Authorization Service.
     * @return the Protocol Authorization Service
     */
    private KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
}
