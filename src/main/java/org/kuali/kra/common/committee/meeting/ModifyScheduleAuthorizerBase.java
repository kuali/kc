/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.meeting;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.document.authorizer.CommitteeAuthorizerBase;

public abstract class ModifyScheduleAuthorizerBase extends CommitteeAuthorizerBase {

    /**
     * @see org.kuali.kra.protocol.document.authorizer.CommitteeAuthorizerBase#isAuthorized(java.lang.String, org.kuali.kra.protocol.document.authorization.CommitteeTaskBase)
     */
    public boolean isAuthorized(String username, CommitteeTaskBase task) {
        boolean hasPermission = true;
        CommitteeBase committee = task.getCommittee();
        
// TODO *********commented the code below during IACUC refactoring*********         
//        hasPermission = hasPermission(username, committee, PermissionConstants.MODIFY_IACUC_SCHEDULE);
        
        hasPermission = hasPermission(username, committee, getModfifySchedulePermissionNameHook());
        return hasPermission;
    }

    protected abstract String getModfifySchedulePermissionNameHook();

}
