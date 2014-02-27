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
package org.kuali.kra.irb.actions.print;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.KcPersonService;

import java.util.ArrayList;
import java.util.List;

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
     * @see org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase#canModifyPermissions()
     */
    @Override
    public boolean canModifyPermissions() {
        return false;
    }

    /**
     * @see org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase#getPersonsInRole(java.lang.String)
     */
    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        List<String> users = kraAuthorizationService.getPrincipalsInRole(getProtocol(), roleName);

        final List<KcPerson> persons = new ArrayList<KcPerson>();
        for(String userId : users) {
            KcPerson person = kcPersonService.getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        return persons;
    }

    /**
     * @see org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase#isStandardRoleName(java.lang.String)
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
