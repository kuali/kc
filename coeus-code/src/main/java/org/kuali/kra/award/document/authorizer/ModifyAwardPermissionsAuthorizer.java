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
package org.kuali.kra.award.document.authorizer;

import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;

/**
 * The Modify Award Permissions Authorizer checks to see if the user has 
 * permission to maintain award access, i.e. assign Users to Award Roles.
 */
public class ModifyAwardPermissionsAuthorizer extends AwardAuthorizer {

    /**
     * 
     * @see org.kuali.kra.award.document.authorizer.AwardAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.award.document.authorization.AwardTask)
     */
    @Override
    public boolean isAuthorized(String userId, AwardTask task) {
        return !task.getAward().getAwardDocument().isViewOnly() &&
               hasPermission(userId, task.getAward(), AwardPermissionConstants.MODIFY_AWARD.getAwardPermission());
    }
}
