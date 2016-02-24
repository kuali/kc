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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.document.authorizer.CommitteeAuthorizerBase;

public abstract class ViewScheduleAuthorizerBase extends CommitteeAuthorizerBase {

    @Override
    public boolean isAuthorized(String username, CommitteeTaskBase task) {
        boolean hasPermission = true;
        CommitteeBase committee = task.getCommittee();
        if (task instanceof CommitteeScheduleTaskBase) {
//            hasPermission = hasPermission(username,((CommitteeScheduleTaskBase)task).getCommitteeSchedule(),PermissionConstants.VIEW_IACUC_SCHEDULE);
            hasPermission = hasPermission(username,((CommitteeScheduleTaskBase)task).getCommitteeSchedule(), getPermissionNameForViewScheduleHook());
            // now check if this schedule is flagged as available by admin
            hasPermission = hasPermission && ((CommitteeScheduleTaskBase)task).getCommitteeSchedule().isAvailableToReviewers();
        } else {
            hasPermission = hasPermission(username, committee, getPermissionNameForViewScheduleHook());
        }        
        return hasPermission;
    }

    protected abstract String getPermissionNameForViewScheduleHook();

    /**
     * Does the given user has the permission for this committee?
     * @param username the unique username of the user
     * @param committee the committee
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, CommitteeScheduleBase committeeSchedule, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, committeeSchedule, permissionName);
    }
    

}
