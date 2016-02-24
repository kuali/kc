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
package org.kuali.kra.irb.permission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.permission.PermissionsHelperBase;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The PermissionsHelper is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class PermissionsHelper extends PermissionsHelperBase {


    private static final long serialVersionUID = 7892561010219047907L;

    public PermissionsHelper(ProtocolForm form) {
        super(form, RoleConstants.PROTOCOL_ROLE_TYPE);
        this.form = form;
    }

    @Override
    protected void initExcludedRolesHook() {
        if (excludeRoles == null) {
            excludeRoles = new HashSet<String>();
        }
        excludeRoles.add(RoleConstants.IRB_PROTOCOL_ONLINE_REVIEWER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER_SCHEDULED_DATE);
        excludeRoles.add(RoleConstants.PROTOCOL_APPROVER);
        excludeRoles.add(RoleConstants.ACTIVE_COMMITTEE_MEMBER_ON_PROTOCOL);
    }

    @Override
    protected void buildDisplayNameMap() {
        if (displayNameMap == null) {
            displayNameMap = new HashMap<String, String>();
            displayNameMap.put(RoleConstants.PROTOCOL_AGGREGATOR, AGGREGATOR_NAME);
            displayNameMap.put(RoleConstants.PROTOCOL_VIEWER, VIEWER_NAME);
            displayNameMap.put(RoleConstants.PROTOCOL_UNASSIGNED, UNASSIGNED_NAME);
        }
    }

    @Override
    public boolean canModifyPermissions() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, (Protocol)getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.PROTOCOL_AGGREGATOR) ||
        StringUtils.equals(roleName, RoleConstants.PROTOCOL_VIEWER);
    }   
}
