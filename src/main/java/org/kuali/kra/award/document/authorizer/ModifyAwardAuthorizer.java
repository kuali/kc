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
package org.kuali.kra.award.document.authorizer;

import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.AwardPermissionConstants;

/**
 * The Modify Award Authorizer checks to see if the user has 
 * permission to modify a award. Authorization depends upon whether
 * the award is being created or modified.  For creation, the
 * user needs the CREATE_AWARD permission.  If the award is being
 * modified, the user only needs to have the MODIFY_AWARD permission 
 * for that award.
 */
public class ModifyAwardAuthorizer extends AwardAuthorizer {

    /**
     * @see org.kuali.kra.irb.document.authorizer.AwardAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.document.authorization.AwardTask)
     */
    public boolean isAuthorized(String username, AwardTask task) {
        
        Award award = task.getAward();
        return award.isNew() ? canUserCreateAward(username, award) : canUserModifyAward(username, award);
    }
    
    /**
     * 
     * We have to consider the case when we are saving the award for the first time.
     * 
     * @param username
     * @param award
     * @return
     */
    protected boolean canUserCreateAward(String username, Award award){
        return hasUnitPermission(username, AwardPermissionConstants.CREATE_AWARD.getAwardPermission());
    }
    
    /**
     * 
     * After the initial save, the award can only be modified has the required permission.
     * 
     * @param username
     * @param award
     * @return
     */
    protected boolean canUserModifyAward(String username, Award award){
        return hasPermission(username, award, AwardPermissionConstants.MODIFY_AWARD.getAwardPermission());
    }
    
    
}
