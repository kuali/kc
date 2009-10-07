/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.UnitAclLoadable;
import org.kuali.kra.bo.UnitAclEntry;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitAclLoadService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * @see org.kuali.kra.service.UnitAclLoadService
 */
public class UnitAclLoadServiceImpl implements UnitAclLoadService {

    private KraAuthorizationService kraAuthorizationService;
    private BusinessObjectService businessObjectService;
    private PersonService personService;

    /**
     * Set the Kra Authorization Service.  Injected by the Spring Framework.
     * @param kraAuthorizationService the kra authorization service
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
     * @see org.kuali.kra.service.UnitAclLoadService#loadUnitAcl(org.kuali.kra.document.ResearchDocumentBase)
     */
    public void loadUnitAcl(UnitAclLoadable unitAclLoadable) {
        String creatorUsername = getCreator();
        
        Collection<UnitAclEntry> kraRoleTemplates = getRoleTemplates(unitAclLoadable.getDocumentUnitNumber(), unitAclLoadable.getDocumentRoleTypeCode());
        for (UnitAclEntry kraRoleTemplate : kraRoleTemplates) {
            String personId = kraRoleTemplate.getPersonId();
            String username = personService.getPerson(personId).getUserName();
            if (username != null && !StringUtils.equals(username, creatorUsername)) {
                kraAuthorizationService.addRole(username, kraRoleTemplate.getRoleName(), unitAclLoadable);
            }
        }
    }
    
    /**
     * Gets the creator of the document.  Actually, I'm being sneaky.  The addUsers method is only
     * used when the document is being created.  Therefore, the current user corresponds to the
     * creator of the document.
     * @return the creator's username
     */
    public String getCreator() {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
    }

    /**
     * Get the role templates for the proposal.  The retrieved role templates correspond
     * to the proposal's lead unit.
     * @param unitNumber of the document
     * @return the collection of role templates
     */
    private Collection<UnitAclEntry> getRoleTemplates(String unitNumber, String documentTypeCode) {
        Collection<UnitAclEntry> list = new ArrayList<UnitAclEntry>();
        
        // get all acl for this unit
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("unitNumber", unitNumber);
        fieldValues.put("active", true);
        Collection<UnitAclEntry> aclList = businessObjectService.findMatching(UnitAclEntry.class, fieldValues);

        // filter out only acl for this document type
        for (UnitAclEntry aclEntry : aclList) {
            String roleTypeCode = aclEntry.getRole().getRoleTypeCode();
            if (StringUtils.equals(roleTypeCode, documentTypeCode)) {
                list.add(aclEntry);
            }
        }
        return list;
    }
}
