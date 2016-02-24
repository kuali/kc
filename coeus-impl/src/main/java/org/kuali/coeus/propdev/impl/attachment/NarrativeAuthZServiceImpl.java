/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
