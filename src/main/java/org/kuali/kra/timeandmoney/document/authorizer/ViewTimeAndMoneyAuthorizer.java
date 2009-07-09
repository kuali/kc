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
package org.kuali.kra.timeandmoney.document.authorizer;

import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.timeandmoney.document.authorization.TimeAndMoneyTask;

/**
 * The View Time and Money Authorizer determines if a user has the right
 * to view a specific Time and Money document.
 */
public class ViewTimeAndMoneyAuthorizer extends TimeAndMoneyAuthorizer {

    /**
     * @see org.kuali.kra.irb.TimeAndMoneyAuthorizer.authorizer.AwardAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.irb.document.authorization.AwardTask)
     */
    public boolean isAuthorized(String username, TimeAndMoneyTask task) {
        return hasPermission(username, task.getTimeAndMoneyDocument(), AwardPermissionConstants.VIEW_AWARD.getAwardPermission());
    }
}
