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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collections;
import java.util.List;

public class NarrativeAuthZServiceImpl implements NarrativeAuthZService {
    private BusinessObjectService businessObjectService;
    private SystemAuthorizationService systemAuthorizationService;
    private KraAuthorizationService kraAuthorizationService;

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     * 
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService#getDefaultNarrativeRight(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public NarrativeRight getDefaultNarrativeRight(String userId, ProposalDevelopmentDocument doc) {
        NarrativeRight right;
        if (kraAuthorizationService.hasPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
            right = NarrativeRight.MODIFY_NARRATIVE_RIGHT;
        }
        else if (kraAuthorizationService.hasPermission(userId, doc, PermissionConstants.VIEW_NARRATIVE)) {
            right = NarrativeRight.VIEW_NARRATIVE_RIGHT;
        }
        else {
            right = NarrativeRight.NO_NARRATIVE_RIGHT;
        }
        return right;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService#getDefaultNarrativeRight(java.lang.String)
     */
    public NarrativeRight getDefaultNarrativeRight(String roleName) {
        return this.getDefaultNarrativeRight(Collections.singletonList(roleName));
    }
    
    protected NarrativeRight getDefaultNarrativeRight(List<String> roleNames) {
        List<String> matchingRoleNames = systemAuthorizationService.getRoleNamesForPermission(PermissionConstants.MODIFY_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        for(String role : roleNames) {
            if(matchingRoleNames.contains(role)) {
                return NarrativeRight.MODIFY_NARRATIVE_RIGHT;
            }
        }
        matchingRoleNames.clear();
        matchingRoleNames = systemAuthorizationService.getRoleNamesForPermission(PermissionConstants.VIEW_NARRATIVE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
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
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the Proposal Authorization Service
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
}
