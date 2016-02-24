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
package org.kuali.kra.irb.actions.print;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;

import java.util.ArrayList;
import java.util.List;


public class ProtocolPrintPermissionUtils extends PermissionsHelperBase {


    private static final long serialVersionUID = -6233936470002193650L;
    private Protocol protocol;

    public ProtocolPrintPermissionUtils() {
        super();
     }    
    public ProtocolPrintPermissionUtils(String roleType) {
        super(roleType);
     }    

    @Override
    public boolean canModifyPermissions() {
        return false;
    }

    @Override
    protected List<KcPerson> getPersonsInRole(String roleName) {
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        List<String> users = kraAuthorizationService.getPrincipalsInRole(roleName, getProtocol());

        final List<KcPerson> persons = new ArrayList<KcPerson>();
        for(String userId : users) {
            KcPerson person = kcPersonService.getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        return persons;
    }

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
