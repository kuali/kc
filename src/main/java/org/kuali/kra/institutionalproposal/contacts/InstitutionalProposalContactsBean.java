/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.contacts;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This is the base class for handling Institutional Proposal Contacts
 */
public abstract class InstitutionalProposalContactsBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4211366507290652295L;
    private static final String PERSON_IDENTIFIER_FIELD = "personId";
    private static final String ROLODEX_IDENTIFIER_FIELD = "rolodexId";
    
    protected List<? extends ContactRole> contactRoles;
    protected InstitutionalProposalContact newInstitutionalProposalContact;
    protected InstitutionalProposalForm institutionalProposalForm;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;

    private String personId;
    private Integer rolodexId;
    
    public InstitutionalProposalContactsBean(InstitutionalProposalForm institutionalProposalForm) {
        this.institutionalProposalForm = institutionalProposalForm;
        init();
    }
    
    @SuppressWarnings("unchecked")
    public List<? extends ContactRole> getContactRoles() {
        if(contactRoles == null) {
            contactRoles = (List<? extends ContactRole>) getBusinessObjectService().findAll(getContactRoleType());
        }
        return contactRoles;
    }
    
    /**
     * Subclasses specify the contact role type
     * @return
     */
    protected abstract Class<? extends ContactRole> getContactRoleType();
    
    public String getContactRoleCode() {
        return newInstitutionalProposalContact.getContactRole() != null ? newInstitutionalProposalContact.getContactRole().getRoleCode() : null;
    }
    
    public InstitutionalProposalContact getNewInstitutionalProposalContact() {
        return newInstitutionalProposalContact;
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

    /**
     * @param contactRoleCode
     */
    public void setContactRoleCode(String contactRoleCode) {
        ContactRole matchingRole = findMatchingContactRole(getContactRoles(), contactRoleCode);
        newInstitutionalProposalContact.setContactRole(matchingRole);  
    }

    /**
     * @param personId
     */
    public void setPersonId(String personId) {
        this.personId = personId;
        KcPerson person = personId != null ? (KcPerson) findContact(PERSON_IDENTIFIER_FIELD, KcPerson.class, personId) : null;
        newInstitutionalProposalContact.setPerson(person);
    }

    /**
     * @param rolodexId
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
        NonOrganizationalRolodex rolodex = rolodexId != null 
                                               ? (NonOrganizationalRolodex) findContact(ROLODEX_IDENTIFIER_FIELD, NonOrganizationalRolodex.class, rolodexId) 
                                               : null;
                                               newInstitutionalProposalContact.setRolodex(rolodex);
    }
    
    protected Object findContact(String identifierField, @SuppressWarnings("unchecked") Class contactClass, Object contactIdentifier) {
        if (KcPerson.class.isAssignableFrom(contactClass)) {
            return getKcPersonService().getKcPersonByPersonId((String) contactIdentifier);
        }
        
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
    protected ContactRole findMatchingContactRole(Collection<? extends ContactRole> roles, String contactRoleCode) {
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
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    protected void init() {
        this.newInstitutionalProposalContact = createNewContact();
        this.personId = null;
        this.rolodexId = null;
    }
    
    protected abstract InstitutionalProposalContact createNewContact();

    protected InstitutionalProposal getInstitutionalProposal() {
        return getDocument().getInstitutionalProposal();
    }
    
    protected InstitutionalProposalDocument getDocument() {
        return institutionalProposalForm.getInstitutionalProposalDocument();
    }

    void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
}

