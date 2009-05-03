/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorization;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.rice.shim.DocumentInitiationAuthorizationException;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentPresentationControllerBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeDocumentPresentationController extends TransactionalDocumentPresentationControllerBase {

    @Override
    public boolean canInitiate(String documentTypeName) {
        //super.canInitiate(documentTypeName, user);
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        if (!canCreateCommittee(user)) {
            throw new DocumentInitiationAuthorizationException(KeyConstants.ERROR_AUTHORIZATION_DOCUMENT_INITIATION, 
                                                               new String[] { user.getPersonUserIdentifier(), documentTypeName });
        }
        return true;
    }
    /**
     * Does the user have permission to create a committee?
     * @param user the user
     * @return true if the user can create a committee; otherwise false
     */
    private boolean canCreateCommittee(UniversalUser user) {
        String username = user.getPersonUserIdentifier();
        ApplicationTask task = new ApplicationTask(TaskName.ADD_COMMITTEE);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }

}
