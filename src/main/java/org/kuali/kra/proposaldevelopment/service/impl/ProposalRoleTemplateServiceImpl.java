/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.UnitAclEntry;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.PersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The Proposal Role Template Service Implementation.
 */
public class ProposalRoleTemplateServiceImpl implements ProposalRoleTemplateService {
    
    private static final String PROPOSAL_ROLE_TYPE = "P";
    
    private KraAuthorizationService kraAuthorizationService;
    private BusinessObjectService businessObjectService;
    private PersonService personService;
    
    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the proposal authorization service
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Set the Business Object Service.  Injected by the Spring Framework.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Person Service.  Injected by the Spring Framework.
     * @param personService the person service
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService#addUsers(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void addUsers(ProposalDevelopmentDocument doc) {
        String creatorUsername = getCreator(doc);
        
        Collection<UnitAclEntry> proposalRoleTemplates = getRoleTemplates(doc.getOwnedByUnitNumber());
        for (UnitAclEntry proposalRoleTemplate : proposalRoleTemplates) {
            String personId = proposalRoleTemplate.getPersonId();
            String username = personService.getPerson(personId).getUserName();
            if (username != null && !StringUtils.equals(username, creatorUsername)) {
                kraAuthorizationService.addRole(username, proposalRoleTemplate.getRoleName(), doc);
            }
        }
    }
    
    /**
     * Gets the creator of the proposal.  Actually, I'm being sneaky.  The addUsers method is only
     * used when the proposal is being created.  Therefore, the current user corresponds to the
     * creator of the proposal.
     * @param doc the proposal development document
     * @return the creator's username
     */
    public String getCreator(ProposalDevelopmentDocument doc) {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
    }
    
    /**
     * Get the role templates for the proposal.  The retrieved role templates correspond
     * to the proposal's lead unit.
     * @param unitNumber the lead unit of the proposal
     * @return the collection of role templates
     */
    private Collection<UnitAclEntry> getRoleTemplates(String unitNumber) {
        Collection<UnitAclEntry> list = new ArrayList<UnitAclEntry>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("unitNumber", unitNumber);
        fieldValues.put("active", true);
        Collection<UnitAclEntry> aclList = businessObjectService.findMatching(UnitAclEntry.class, fieldValues);
        for (UnitAclEntry aclEntry : aclList) {
            String roleTypeCode = aclEntry.getRole().getRoleTypeCode();
            if (StringUtils.equals(roleTypeCode, PROPOSAL_ROLE_TYPE)) {
                list.add(aclEntry);
            }
        }
        return list;
    }

}
