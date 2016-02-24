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
