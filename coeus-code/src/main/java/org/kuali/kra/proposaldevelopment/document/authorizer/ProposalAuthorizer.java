/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizerBase;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



/**
 * A Proposal Authorizer determines if a user can perform
 * a given task on a proposal.  
 */

@Component("parentProposalAuthorizer")
public abstract class ProposalAuthorizer extends TaskAuthorizerBase {
    
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;
    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;

    private boolean requiresWritableDoc = false;

    @Override
    public final boolean isAuthorized(String userId, Task task) {
        if (isRequiresWritableDoc() && ((ProposalTask)task).getDocument().isViewOnly() && task.getTaskName() != null && !StringUtils.equals(task.getTaskName(),"rejectProposal")) {
            return false;
        } else {
            return isAuthorized(userId, (ProposalTask) task);
        }
    }

    /**
     * Is the user authorized to execute the given proposal task?
     * @param userId the user's unique username
     * @param task the proposal task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, ProposalTask task);
    
    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kcAuthorizationService the Kra Authorization Service
     */
    public final void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }

    protected KcAuthorizationService getKcAuthorizationService (){return kcAuthorizationService;}
    /**
     * Does the given user has the permission for this proposal development document?
     * @param userId the unique username of the user
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasProposalPermission(String userId, ProposalDevelopmentDocument doc, String permissionName) {
        return getKcAuthorizationService().hasPermission(userId, doc, permissionName);
    }

    public boolean isRequiresWritableDoc() {
        return requiresWritableDoc;
    }

    public void setRequiresWritableDoc(boolean requiresWritableDoc) {
        this.requiresWritableDoc = requiresWritableDoc;
    }


}
