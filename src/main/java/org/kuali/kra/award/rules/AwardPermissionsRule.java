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
package org.kuali.kra.award.rules;

import org.kuali.kra.common.permissions.rules.PermissionsRuleBase;
import org.kuali.kra.infrastructure.AwardRoleConstants;

/**
 * Business Rule to determine the legality of modifying the access
 * to a Protocol Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AwardPermissionsRule extends PermissionsRuleBase {
    
    /**
     * @see org.kuali.kra.common.permissions.rules.PermissionsRuleBase#getAdministratorRoleName()
     */
    @Override
    protected String getAdministratorRoleName() {
        return AwardRoleConstants.AWARD_AGGREGATOR.getAwardRole();
    }   
}
