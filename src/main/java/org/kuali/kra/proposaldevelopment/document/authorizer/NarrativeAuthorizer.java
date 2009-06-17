/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KraAuthorizationService;

/**
 * Base class for Narrative Authorizers.
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class NarrativeAuthorizer extends TaskAuthorizerImpl {
    
    private KraAuthorizationService kraAuthorizationService;
    

    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the Kra Authorization Service
     */
    public final void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param username the unique username of the user
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasProposalPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        return kraAuthorizationService.hasPermission(username, doc, permissionName);
    }
    
    /**
     * Does the user have the given narrative right for the given narrative?
     * @param username the username of the user
     * @param narrative the narrative
     * @param narrativeRight the narrative right we are looking for
     * @return true if the user has the narrative right for the narrative
     */
    protected final boolean hasNarrativeRight(String username, Narrative narrative, NarrativeRight narrativeRight) {
        List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
        for (NarrativeUserRights userRights : userRightsList) {
            String personUserName = personService.getPersonUserName(userRights.getUserId());
            if (StringUtils.equals(username, personUserName)) {
                if (StringUtils.equals(userRights.getAccessType(), narrativeRight.getAccessType())) {
                    return true;
                }
            }
        }
        return false;
    }
}
