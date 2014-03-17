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
package org.kuali.kra.timeandmoney.document.authorizer;

import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.timeandmoney.document.authorization.TimeAndMoneyTask;

/**
 * The Modify Time and Money Permissions Authorizer checks to see if the user has 
 * permission to maintain Time and Money access, i.e. assign Users to Time and Money Roles.
 */
public class ModifyTimeAndMoneyPermissionsAuthorizer extends TimeAndMoneyAuthorizer {

    @Override
    public boolean isAuthorized(String userId, TimeAndMoneyTask task) {
        return hasPermission(userId, task.getTimeAndMoneyDocument(), AwardPermissionConstants.MODIFY_AWARD.getAwardPermission());
    }
}
