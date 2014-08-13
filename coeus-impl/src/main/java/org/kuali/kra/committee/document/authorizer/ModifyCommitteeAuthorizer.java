/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

    @Override
    protected String getPermissionNameForAddCommiteeHook() {
        return PermissionConstants.ADD_COMMITTEE;
    }
    
}
