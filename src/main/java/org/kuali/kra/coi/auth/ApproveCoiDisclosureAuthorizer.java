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
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.krad.util.ObjectUtils;

public class ApproveCoiDisclosureAuthorizer extends CoiDisclosureAuthorizer {

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        return hasPermission(userId, task.getCoiDisclosure(), PermissionConstants.APPROVE_COI_DISCLOSURE) && isSubmitted(task);
    }

    protected boolean isSubmitted(CoiDisclosureTask task) {
        CoiDisclosure disclosure = task.getCoiDisclosure();
        
        if (disclosure.getCoiDisclosureDocument().getDocumentHeader().hasWorkflowDocument() &&
            disclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isEnroute()) {
            return true;
        }
        return false;
    }
}
