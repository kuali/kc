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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizerBase;

import java.util.List;

/**
 * Base class for Narrative Authorizers.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class NarrativeAuthorizer extends TaskAuthorizerBase {
    
    private KcAuthorizationService kraAuthorizationService;
    private boolean requiresWritableDoc = false;
    
    public boolean isAuthorized(String userId, Task task) {
        NarrativeTask narrativeTask = (NarrativeTask)task;
        if (isRequiresWritableDoc() && narrativeTask.getDocument().isViewOnly()) {
            return false;
        } else {
            return isAuthorized(userId, narrativeTask);
        }
    }
    
    public abstract boolean isAuthorized(String userId, NarrativeTask task);

    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the Kra Authorization Service
     */
    public final void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param userId the unique username of the user
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasProposalPermission(String userId, ProposalDevelopmentDocument doc, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, doc, permissionName);
    }
    
    /**
     * Does the user have the given narrative right for the given narrative?
     * @param userId the username of the user
     * @param narrative the narrative
     * @param narrativeRight the narrative right we are looking for
     * @return true if the user has the narrative right for the narrative
     */
    protected final boolean hasNarrativeRight(String userId, Narrative narrative, NarrativeRight narrativeRight) {
        List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
        for (NarrativeUserRights userRights : userRightsList) {
            if (StringUtils.equals(userId, userRights.getUserId())) {
                if (StringUtils.equals(userRights.getAccessType(), narrativeRight.getAccessType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRequiresWritableDoc() {
        return requiresWritableDoc;
    }

    public void setRequiresWritableDoc(boolean requiresWritableDoc) {
        this.requiresWritableDoc = requiresWritableDoc;
    }
}
