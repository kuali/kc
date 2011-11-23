/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;

/**
 * 
 * This class maintains the authorization for maintaining report tracking on awards.
 */
public class MaintainAwardReportTrackingAuthorizer extends AwardAuthorizer {

    @Override
    public boolean isAuthorized(String userId, AwardTask task) {
        return hasUnitPermission(userId, Constants.MODULE_NAMESPACE_AWARD, AwardPermissionConstants.MODIFY_AWARD_REPORT_TRACKING.getAwardPermission());
    }

}
