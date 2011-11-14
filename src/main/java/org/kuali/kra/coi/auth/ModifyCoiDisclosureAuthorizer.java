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
package org.kuali.kra.coi.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * 
 * This class is to check if user has permission to modify coi disclosure
 */
public class ModifyCoiDisclosureAuthorizer extends CoiDisclosureAuthorizer {

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        // TODO : need to verify whether after 'certify' , is it still 'saved' status or not ?
        boolean hasPermission = true;
        CoiDisclosure coiDisclosure = task.getCoiDisclosure();
        if (coiDisclosure == null || coiDisclosure.getCoiDisclosureId() == null) {
            
            // We have to consider the case when we are saving the coi disclosure for the first time.
            
           // hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.MAINTAIN_COI_DISCLOSURE);
            hasPermission = getCoiDisclosureService().isReporter() 
                    || hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.MAINTAIN_COI_DISCLOSURE);
        } 
        else {
            /*
             * After the initial save, the protocol can only be modified has the required permission.
             */
            hasPermission = !coiDisclosure.getCoiDisclosureDocument().isViewOnly() && 
                            !isPessimisticLocked(coiDisclosure.getCoiDisclosureDocument()) &&
                            !kraWorkflowService.isInWorkflow(coiDisclosure.getCoiDisclosureDocument()) &&
                            (hasPermission(userId, coiDisclosure, PermissionConstants.MAINTAIN_COI_DISCLOSURE)
                                    || StringUtils.equals(userId, coiDisclosure.getPersonId()));

        }
        return hasPermission;
    }
    

}
