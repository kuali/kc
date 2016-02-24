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
package org.kuali.coeus.common.committee.impl.document.authorizer;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizerBase;

/**
 * A CommitteeBase Authorizer determines if a user can perform
 * a given task on a committee.  
 */
public abstract class CommitteeAuthorizerBase extends TaskAuthorizerBase {
    
    protected KcAuthorizationService kraAuthorizationService;
    
    @Override
    public final boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (CommitteeTaskBase) task);
    }

    /**
     * Is the user authorized to execute the given committee task?
     * @param userId the user's unique username
     * @param task the committee task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, CommitteeTaskBase task);
    
    /**
     * Set the Kra Authorization Service.  Usually injected by the Spring Framework.
     * @param kraAuthorizationService
     */
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this committee?
     * @param userId the unique username of the user
     * @param committee the committee
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, CommitteeBase committee, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, committee, permissionName);
    }
}
