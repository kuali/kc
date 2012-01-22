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
package org.kuali.kra.irb.actions.print;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.KraAuthorizationService;

/**
 * This class...
 */
public class ProtocolPrintPermissionUtils extends PermissionsHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6233936470002193650L;
    private Protocol protocol;

    public ProtocolPrintPermissionUtils() {
        super();
     }    
    public ProtocolPrintPermissionUtils(String roleType) {
        super(roleType);
     }    

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#canModifyPermissions()
     */
    @Override
    public boolean canModifyPermissions() {
        return false;
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#getPersonsInRole(java.lang.String)
     */
    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        return kraAuthorizationService.getPersonsInRole(getProtocol(), roleName);
    }

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#isStandardRoleName(java.lang.String)
     */
    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.PROTOCOL_AGGREGATOR) ||
                StringUtils.equals(roleName, RoleConstants.PROTOCOL_VIEWER);
    }

    /**
     * Sets the protocol attribute value.
     * @param protocol The protocol to set.
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the protocol attribute. 
     * @return Returns the protocol.
     */
    public Protocol getProtocol() {
        return protocol;
    }

}
