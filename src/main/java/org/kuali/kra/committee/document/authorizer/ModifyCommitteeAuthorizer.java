/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Modify Committee Authorizer checks to see if the user has 
 * permission to modify a committee. Authorization depends upon whether
 * the committee is being created or modified.  For creation, the
 * user needs the ADD_COMMITTEE permission.  If the committee is being
 * modified, the user only needs to have the MODIFY_COMMITTEE permission 
 * for that committee.
 */
public class ModifyCommitteeAuthorizer extends CommitteeAuthorizer {

    /**
     * @see org.kuali.kra.irb.document.authorizer.CommitteeAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.document.authorization.CommitteeTask)
     */
    public boolean isAuthorized(String username, CommitteeTask task) {
        boolean hasPermission = true;
        Committee committee = task.getCommittee();
        Long committeeId = committee.getId();
        if (committeeId == null) {
            
            // We have to consider the case when we are saving the committee for the first time.
            
            hasPermission = hasUnitPermission(username, PermissionConstants.ADD_COMMITTEE);
        } 
        else {
            /*
             * After the initial save, the committee can only be modified has the required permission.
             */
            hasPermission = !committee.getCommitteeDocument().isViewOnly() &&
                            hasPermission(username, committee, PermissionConstants.MODIFY_COMMITTEE);
        }
        return hasPermission;
    }
}
