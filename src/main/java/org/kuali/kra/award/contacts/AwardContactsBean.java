/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This is the base class for handling AwardContacts
 */
public abstract class AwardContactsBean {
    private static final String PERSON_IDENTIFIER_FIELD = "personId";
    private static final String ROLODEX_IDENTIFIER_FIELD = "rolodexId";
    
    protected List<ContactRole> contactRoles;
    protected AwardContact newAwardContact;
    protected AwardForm awardForm;
    
    protected Person person;
    protected NonOrganizationalRolodex rolodex;
    
    private BusinessObjectService businessObjectService;
    private String personId;

    private Integer rolodexId;
    
    public AwardContactsBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        init();
    }
    
    public abstract List<ContactRole> getContactRoles();
    
    public String getContactRoleCode() {
        return newAwardContact.getContactRole().getRoleCode();
    }
    
    public AwardContact getNewAwardContact() {
        return newAwardContact;
    }
    
    /**
     * Gets the personId attribute. 
     * @return Returns the personId.
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setContactRoleCode(String contactRoleCode) {
        ContactRole matchingRole = findMatchingContactRole(getContactRoles(), contactRoleCode);
        newAwardContact.setContactRole(matchingRole);  
    }

    /**
     * @param personId
     */
    public void setPersonId(String personId) {
        this.personId = personId;
        this.person = personId != null ? (Person) findContact(PERSON_IDENTIFIER_FIELD, Person.class, personId) : null;
        setContactPerson(this.person);
    }

    /**
     * @param rolodexId
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
        this.rolodex = rolodexId != null ? (NonOrganizationalRolodex) findContact(ROLODEX_IDENTIFIER_FIELD, NonOrganizationalRolodex.class, rolodexId) : null;
        setContactRolodex(this.rolodex);
    }
    
    protected Object findContact(String identifierField, @SuppressWarnings("unchecked") Class contactClass, Object contactIdentifier) {
        Map<String, Object> identifierMap = new HashMap<String, Object>();
        identifierMap.put(identifierField, contactIdentifier);
        return getBusinessObjectService().findByPrimaryKey(contactClass, identifierMap);
    }
    
    /**
     * This method finds a matching AwardContactRole in the specified collection for the specified role code
     * @param roles
     * @param contactRoleCode
     * @return The matching AwardContactRole; may be null
     */
    protected ContactRole findMatchingContactRole(Collection<ContactRole> roles, String contactRoleCode) {
        ContactRole matchingRole = null;
        for(ContactRole role: roles) {
            if(role.getRoleCode().equals(contactRoleCode)) {
                matchingRole = role;
                break;
            }
        }
        return matchingRole;
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        if(businessObjectService == null) {
            businessObjectService = (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class); 
        }
        return businessObjectService;
    }
    
    protected abstract void init();

    protected Award getAward() {
        return getDocument().getAward();
    }
    
    protected AwardDocument getDocument() {
        return awardForm.getAwardDocument();
    }
    
    private void setContactPerson(Person person) {
        this.person = person;
        newAwardContact.setPerson(person);
    }

    private void setContactRolodex(NonOrganizationalRolodex rolodex) {
        this.rolodex = rolodex;
        newAwardContact.setRolodex(rolodex);
    }
}
