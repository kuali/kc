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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the base class for handling Institutional Proposal Contacts
 */
public abstract class InstitutionalProposalContactsBean implements Serializable {


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
        newInstitutionalProposalContact.setInstitutionalProposal(getInstitutionalProposal());
        newInstitutionalProposalContact.setContactRoleCode(contactRoleCode);  
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


    protected BusinessObjectService getBusinessObjectService() {
        if(businessObjectService == null) {
            businessObjectService = (BusinessObjectService) KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
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

