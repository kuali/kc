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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("narrativeAuthZService")
public class NarrativeAuthZServiceImpl implements NarrativeAuthZService {

    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;
    
    @Override
    public NarrativeRight getDefaultNarrativeRight(String userId, ProposalDevelopmentDocument doc) {
        NarrativeRight right;
        if (getKcAuthorizationService().hasPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
            right = NarrativeRight.MODIFY_NARRATIVE_RIGHT;
        }
        else if (getKcAuthorizationService().hasPermission(userId, doc, PermissionConstants.VIEW_NARRATIVE)) {
            right = NarrativeRight.VIEW_NARRATIVE_RIGHT;
        }
        else {
            right = NarrativeRight.NO_NARRATIVE_RIGHT;
        }
        return right;
    }
    
    @Override
    public NarrativeRight getDefaultNarrativeRight(String roleName) {
        return this.getDefaultNarrativeRight(Collections.singletonList(roleName));
    }
    
    protected NarrativeRight getDefaultNarrativeRight(List<String> roleNames) {
        List<String> matchingRoleNames = getSystemAuthorizationService().getRoleNamesForPermission(PermissionConstants.MODIFY_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        for(String role : roleNames) {
            if(matchingRoleNames.contains(role)) {
                return NarrativeRight.MODIFY_NARRATIVE_RIGHT;
            }
        }
        matchingRoleNames.clear();
        matchingRoleNames = getSystemAuthorizationService().getRoleNamesForPermission(PermissionConstants.VIEW_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        for(String role : roleNames) {
            if(matchingRoleNames.contains(role)) {
                return NarrativeRight.VIEW_NARRATIVE_RIGHT;
            }
        }

        return NarrativeRight.NO_NARRATIVE_RIGHT;
    }
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param systemAuthorizationService the Proposal Authorization Service
     */
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }
    protected SystemAuthorizationService getSystemAuthorizationService (){return systemAuthorizationService;}
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the Proposal Authorization Service
     */
    public void setKcAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kcAuthorizationService = kraAuthorizationService;
    }
    protected KcAuthorizationService getKcAuthorizationService(){return kcAuthorizationService;}
}
