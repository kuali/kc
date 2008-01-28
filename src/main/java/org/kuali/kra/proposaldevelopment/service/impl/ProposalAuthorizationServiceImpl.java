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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Proposal Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalAuthorizationServiceImpl implements ProposalAuthorizationService {
    
    private static final String PROPOSAL_KEY = "kra.proposal";
    
    private UnitAuthorizationService unitAuthorizationService;
    private PersonService kimPersonService;
    private QualifiedRoleService kimQualifiedRoleService;

    /**
     * Set the Unit Authorization Service.  Injected by Spring.
     * @param unitAuthorizationService the Unit Authorization Service
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    /**
     * Set the KIM Person Service.  Injected by Spring.
     * @param personService the KIM Person Service
     */
    public void setKimPersonService(PersonService personService) {
        this.kimPersonService = personService;
    }
    
    /**
     * Set the KIM Qualified Role Service.  Injected by Spring.
     * @param qualifiedRoleService the KIM Qualified Role Service
     */
    public void setKimQualifiedRoleService(QualifiedRoleService qualifiedRoleService) {
        this.kimQualifiedRoleService = qualifiedRoleService;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#getUserNames(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public List<String> getUserNames(ProposalDevelopmentDocument doc, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber().toString());
        return kimQualifiedRoleService.getPersonUsernames(roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#addRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void addRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber().toString());
        kimPersonService.addQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#removeRole(java.lang.String, java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void removeRole(String username, String roleName, ProposalDevelopmentDocument doc) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber().toString());
        kimPersonService.removeQualifiedRole(username, roleName, qualifiedRoleAttributes);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasPermission(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.lang.String)
     */
    public boolean hasPermission(UniversalUser user, ProposalDevelopmentDocument doc, String permissionName) {
        String userId = user.getPersonUserIdentifier();
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(PROPOSAL_KEY, doc.getProposalNumber().toString());
        boolean userHasPermission = kimPersonService.hasQualifiedPermission(userId, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
        if (!userHasPermission) {
            String unitNumber = doc.getOwnedByUnitNumber();
            userHasPermission = unitAuthorizationService.hasPermission(user, unitNumber, permissionName);
        
        }
        return userHasPermission;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService#hasPermission(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.Narrative, java.lang.String)
     */
    public boolean hasPermission(UniversalUser user, ProposalDevelopmentDocument doc, Narrative narrative, String permissionName) {
        // TODO Auto-generated method stub
        return false;
    }
}
