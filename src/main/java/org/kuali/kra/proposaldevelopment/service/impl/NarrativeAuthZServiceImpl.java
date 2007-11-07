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
import org.kuali.kra.bo.RoleRight;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.RightConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;

/**
 * This class...
 */
public class NarrativeAuthZServiceImpl implements NarrativeAuthZService {
    private BusinessObjectService businessObjectService;

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
    public NarrativeRight getNarrativeRight(Integer roleId) {
        
        Map<String,Integer> proRoleMap = new HashMap<String,Integer>();
        proRoleMap.put("ROLE_ID", roleId);
        
        Collection<RoleRight> roleRights = getBusinessObjectService().findMatching(RoleRight.class, proRoleMap);
        NarrativeRight right = NarrativeRight.NO_NARRATIVE_RIGHT;
        for (RoleRight roleRight : roleRights) {
            String rightId = roleRight.getRightId();
            if (roleRight.getRightId().equals(RightConstants.MODIFY_NARRATIVE)) {
                right = NarrativeRight.MODIFY_NARRATIVE_RIGHT;
                break;
            }else if (roleRight.getRightId().equals(RightConstants.VIEW_NARRATIVE)) {
                right = NarrativeRight.VIEW_NARRATIVE_RIGHT;
            }
        }
        return right;
    }


}
