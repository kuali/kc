/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.service.UnitAuthorizationService;

public class CertifyProposalPersonAuthorizer extends ProposalAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {
        boolean isAuthorized = true;
        ProposalDevelopmentDocument doc = task.getDocument();
        String unitNumber = doc.getDevelopmentProposal().getOwnedByUnitNumber();
        
        if (unitNumber != null) {
            UnitAuthorizationService auth = KraServiceLocator.getService(UnitAuthorizationService.class);
            isAuthorized = auth.hasPermission(userId, unitNumber, "KC-UNT", PermissionConstants.MAINTAIN_PERSONNEL_CERTIFICATION)
                    || hasProposalPermission(userId, task.getDocument(), PermissionConstants.MAINTAIN_PERSONNEL_CERTIFICATION)
                    || getKraAuthorizationService().hasPermission(userId, task.getDocument(), "KC-UNT", PermissionConstants.MAINTAIN_PERSONNEL_CERTIFICATION);
        }
        return isAuthorized;
    }

}
