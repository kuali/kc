/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.document.authorizer;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * The Modify CommitteeBase Authorizer checks to see if the user has 
 * permission to modify a committee. Authorization depends upon whether
 * the committee is being created or modified.  For creation, the
 * user needs the ADD_COMMITTEE permission.  If the committee is being
 * modified, the user only needs to have the MODIFY_COMMITTEE permission 
 * for that committee.
 */
public abstract class ModifyCommitteeAuthorizerBase extends CommitteeAuthorizerBase {

    /**
     * @see org.kuali.kra.protocol.document.authorizer.CommitteeAuthorizerBase#isAuthorized(java.lang.String, org.kuali.kra.protocol.document.authorization.CommitteeTaskBase)
     */
    public boolean isAuthorized(String userId, CommitteeTaskBase task) {
        boolean hasPermission = true;
        CommitteeBase committee = task.getCommittee();
        if (committee.getId() == null) {
            
            // We have to consider the case when we are saving the committee for the first time.
            
// TODO *********commented the code below during IACUC refactoring*********             
//            hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_IACUC, PermissionConstants.ADD_IACUC_COMMITTEE);
            
            hasPermission = hasUnitPermission(userId, getModuleNamespaceCodeHook(), getPermissionNameForAddCommiteeHook());
        } 
        else {
            /*
             * After the initial save, the committee can only be modified has the required permission.
             */
            hasPermission = !committee.getCommitteeDocument().isViewOnly() &&
                            !isPessimisticLocked(committee.getCommitteeDocument()) &&

// TODO *********commented the code below during IACUC refactoring*********                             
//                            hasPermission(userId, committee, PermissionConstants.MODIFY_IACUC_COMMITTEE);
                            
                            hasPermission(userId, committee, getPermissionNameForModifyCommitteeHook());
        }

        // Verify that document is not locked
        if (isPessimisticLocked(committee.getCommitteeDocument())) {
            hasPermission = false;
        }

        return hasPermission;
    }

    protected abstract String getPermissionNameForModifyCommitteeHook();

    protected abstract String getModuleNamespaceCodeHook();

    protected abstract String getPermissionNameForAddCommiteeHook();
    
    

    private boolean isPessimisticLocked(Document document) {
        boolean isLocked = false;
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            // if lock is owned by current user, do not display message for it
            if (!lock.isOwnedByUser(GlobalVariables.getUserSession().getPerson())) {
                isLocked = true;
            }
        }
        return isLocked;
    }
}
