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
package org.kuali.kra.iacuc.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.permissions.web.bean.Role;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.permission.PermissionsHelper;


/**
 * The PermissionsHelper is used to manage the Permissions tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class IacucPermissionsHelper extends PermissionsHelper {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3601806102887965826L;


    private static final Log LOG = LogFactory.getLog(IacucPermissionsHelper.class);
    
    
    /**
     * Constructs a PermissionsHelper.
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
    

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#isStandardRoleName(java.lang.String)
     */
    @Override
    protected boolean isStandardRoleName(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.IACUC_PROTOCOL_AGGREGATOR) ||
               StringUtils.equals(roleName, RoleConstants.IACUC_PROTOCOL_VIEWER);

    }
    

    /**
     * @see org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase#canModifyPermissions()
     */
    @Override
    public boolean canModifyPermissions() {
        return true;
        /*
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        */
    }

    @Override
    protected void initExcludedRolesHook() {
        excludeRoles.add(RoleConstants.IACUC_PROTOCOL_APPROVER);
    }

}
