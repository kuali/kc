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
package org.kuali.kra.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class CreateCoiDisclosureAuthorizer extends TaskAuthorizerImpl {

    private CoiDisclosureService coiDisclosureService;
    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public boolean isAuthorized(String userId, Task task) {
        // TODO : do we let coi admin create coi disclosure if admin is not a reporter ?
        return coiDisclosureService.isReporter() ||
               hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.MAINTAIN_COI_DISCLOSURE);
    }

    public void setCoiDisclosureService(CoiDisclosureService coiDisclosureService) {
        this.coiDisclosureService = coiDisclosureService;
    }

}
