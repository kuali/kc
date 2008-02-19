/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;

/**
 * This class...
 */
public class NarrativeAuthZServiceImpl implements NarrativeAuthZService {
    private BusinessObjectService businessObjectService;
    private ProposalAuthorizationService proposalAuthorizationService;

    /**
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService#authorize(org.kuali.kra.proposaldevelopment.bo.Narrative)
     */
    public NarrativeRight authorize(Narrative narrative,String loggedInUserId) {
        Map narrativeRightQuery = new HashMap();
        narrativeRightQuery.put("proposalNumber", narrative.getProposalNumber());
        narrativeRightQuery.put("moduleNumber", narrative.getModuleNumber());
        narrativeRightQuery.put("userId", loggedInUserId);
        Collection<NarrativeUserRights> narrativeUserRights = getBusinessObjectService().findMatching(NarrativeUserRights.class, narrativeRightQuery);
        
        for (NarrativeUserRights narrativeUserRight : narrativeUserRights) {
            if(narrativeUserRight.getAccessType().equals(NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType()))
                return NarrativeRight.MODIFY_NARRATIVE_RIGHT;
            else if(narrativeUserRight.getAccessType().equals(NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType()))
                return NarrativeRight.VIEW_NARRATIVE_RIGHT;
            else if(narrativeUserRight.getAccessType().equals(NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType()))
                return NarrativeRight.NO_NARRATIVE_RIGHT;
            
        }
        return NarrativeRight.NO_NARRATIVE_RIGHT;
    }
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
    public NarrativeRight getDefaultNarrativeRight(String username, ProposalDevelopmentDocument doc) {
        NarrativeRight right;
        if (proposalAuthorizationService.hasPermission(username, doc, PermissionConstants.MODIFY_NARRATIVE)) {
            right = NarrativeRight.MODIFY_NARRATIVE_RIGHT;
        }
        else if (proposalAuthorizationService.hasPermission(username, doc, PermissionConstants.VIEW_NARRATIVE)) {
            right = NarrativeRight.VIEW_NARRATIVE_RIGHT;
        }
        else {
            right = NarrativeRight.NO_NARRATIVE_RIGHT;
        }
        return right;
    }
    
    /**
     * Set the Proposal Authorization Service.  Injected by the Spring Framework.
     * @param proposalAuthorizationService the Proposal Authorization Service
     */
    public void setProposalAuthorizationService(ProposalAuthorizationService proposalAuthorizationService) {
        this.proposalAuthorizationService = proposalAuthorizationService;
    }
}
