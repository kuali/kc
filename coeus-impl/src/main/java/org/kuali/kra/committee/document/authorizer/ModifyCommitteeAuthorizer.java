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
package org.kuali.kra.committee.document.authorizer;

import org.kuali.coeus.common.committee.impl.document.authorizer.ModifyCommitteeAuthorizerBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Modify Committee Authorizer checks to see if the user has 
 * permission to modify a committee. Authorization depends upon whether
 * the committee is being created or modified.  For creation, the
 * user needs the ADD_COMMITTEE permission.  If the committee is being
 * modified, the user only needs to have the MODIFY_COMMITTEE permission 
 * for that committee.
 */
public class ModifyCommitteeAuthorizer extends ModifyCommitteeAuthorizerBase {

    @Override
    protected String getPermissionNameForModifyCommitteeHook() {
        return PermissionConstants.MODIFY_COMMITTEE;
    }

    @Override
    protected String getModuleNamespaceCodeHook() {
        return Constants.MODULE_NAMESPACE_IRB;
    }

    @Override
    protected String getPermissionNameForAddCommiteeHook() {
        return PermissionConstants.ADD_COMMITTEE;
    }
    
}
