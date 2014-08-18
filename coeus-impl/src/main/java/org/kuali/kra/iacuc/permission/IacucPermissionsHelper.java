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
package org.kuali.kra.iacuc.permission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.permission.PermissionsHelperBase;

import java.util.HashMap;
import java.util.HashSet;


/**
 * The PermissionsHelperBase is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class IacucPermissionsHelper extends PermissionsHelperBase {
    

    private static final long serialVersionUID = 3601806102887965826L;


    /**
     * Constructs a PermissionsHelperBase.
     * @param form the form
     */
    public IacucPermissionsHelper(IacucProtocolForm form) {
        super(form, RoleConstants.IACUC_ROLE_TYPE);
        this.form = form;
    }   

    /**
     * Build the mapping of role names to display.
     */
    @Override
    protected void buildDisplayNameMap() {
        if (displayNameMap == null) {
            displayNameMap = new HashMap<String, String>();
            displayNameMap.put(RoleConstants.IACUC_PROTOCOL_AGGREGATOR, AGGREGATOR_NAME);
            displayNameMap.put(RoleConstants.IACUC_PROTOCOL_VIEWER, VIEWER_NAME);
            displayNameMap.put(RoleConstants.IACUC_PROTOCOL_UNASSIGNED, UNASSIGNED_NAME);

        }
    }
    

    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.IACUC_PROTOCOL_AGGREGATOR) ||
               StringUtils.equals(roleName, RoleConstants.IACUC_PROTOCOL_VIEWER);

    }
    

    @Override
    public boolean canModifyPermissions() {              
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_ROLES, (IacucProtocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }

    @Override
    protected void initExcludedRolesHook() {
        if (excludeRoles == null) {
            excludeRoles = new HashSet<String>();
        }
        
        excludeRoles.add(RoleConstants.IACUC_PROTOCOL_APPROVER);
        excludeRoles.add(RoleConstants.IACUC_PROTOCOL_UNASSIGNED);
        excludeRoles.add(RoleConstants.MODIFY_IACUC_PROTOCOLS);
        excludeRoles.add(RoleConstants.ACTIVE_IACUC_COMMITTEE_MEMBER);
        excludeRoles.add(RoleConstants.ACTIVE_IACUC_COMMITTEE_MEMBER_ON_PROTOCOL);
        excludeRoles.add(RoleConstants.ACTIVE_IACUC_COMMITTEE_MEMBER_ON_SCHEDULED_DATE);
        excludeRoles.add(RoleConstants.IACUC_PROTOCOL_CREATOR);
        excludeRoles.add(RoleConstants.IACUC_PROTOCOL_DELETER);        
    }

}
