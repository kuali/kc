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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class defines an AwardContact
 */
public abstract class AwardContact extends AwardAssociate {
    private static final String ROLODEX_ID_FIELD_NAME = "rolodexId";
    private static final String PERSON_ID_FIELD_NAME = "personId";

    private static final long serialVersionUID = 4386300861743037298L;
    
    /**
     * These field are OJB hacks. Because anonymous access wouldn't work for more than one field, the Award,
     * we need to provide real fields to be persisted
     */
    protected String personId;
    
    @AwardSyncable  protected Integer rolodexId;
    @AwardSyncable protected String roleCode;
    
    private Long awardContactId;    
    private ContactRole contactRole;
    private String fullName;
    private Person person;
    private NonOrganizationalRolodex rolodex;
    
    /**
     * Constructor
     */
    public AwardContact() {
        
    }
    
    /**
     * 
     * Convenience constructor
     * @param rolodex
     * @param contactType
     */
    AwardContact(NonOrganizationalRolodex rolodex, ContactRole contactRole) {
        this();
        setRolodex(rolodex);
        setContactRole(contactRole);
    }

    /**
     * 
     * Convenience constructor
     * @param person
     * @param contactCategory
     */
    AwardContact(Person person, ContactRole role) {
        this();
        setPerson(person);
        setContactRole(role);
    }

    /**
     * This has been modified from the Eclipse generated method
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwardContact)) {
            return false;
        }
        AwardContact other = (AwardContact) obj;
        if (getContactRole() == null) {
            if (other.getContactRole() != null) {
                return false;
            }
        } else if (!getContactRole().equals(other.getContactRole())) {
            return false;
        }
        if (getContact() == null) {
            if (other.getContact() != null) {
                return false;
            }
        } else {
            if(getContact().getFullName() == null) {
                if (other.getContact().getFullName() != null) {
                    return false;
                }
            } else {
                return getContact().getFullName().equalsIgnoreCase(other.getContact().getFullName());
            }
        }
        return true;
    }

    /**
     * Gets the awardContactId attribute. 
     * @return Returns the awardContactId.
     */
    public Long getAwardContactId() {
        return awardContactId;
    }

    /**
     * Gets the contact attribute. 
     * @return Returns the contact.
     */
    public Contactable getContact() {
        Contactable contact = person != null ? person : (rolodex != null ? rolodex : null);
        if(contact == null) {
            if(personId != null) {
                refreshPerson();
                contact = person;
            } else if(rolodexId != null) {
                refreshRolodex();
                contact = rolodex;
            }
        }        
        return contact; 
    }

    /**
     * @return
     */
    public String getContactOrganizationName() {
        Contactable contact = getContact();
        return contact != null ? contact.getContactOrganizationName() : null;
    }

    /**
     * @return
     */
    public String getGenericId() {
        return rolodexId != null ? rolodexId.toString() : personId;
    }
    
    /**
     * @return
     */
    public String getOrganizationIdentifier() {
        return getContact() != null ? getContact().getOrganizationIdentifier() : null;
    }
    
    /**
     * Gets the contactRole attribute. 
     * @return Returns the contactRole.
     */
    public ContactRole getContactRole() {
        return contactRole;
    }
    
    /**
     * @return
     */
    public String getContactRoleCode() {
        return roleCode;
    }

    /**
     * @return
     */
    public String getEmailAddress() {
        return getContact() != null ? getContact().getEmailAddress() : null;
    }

    /**
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public Person getPerson() {
        if(person == null && personId != null) {
            refreshPerson();
        }
        return person;
    }
    
    /**
     * Gets the personId attribute. 
     * @return Returns the personId.
     */
    public String getPersonId() {
        return personId;
    }
    
    public String getPhoneNumber() {
        return getContact() != null ? getContact().getPhoneNumber() : null;
    }

    /**
     * Gets the roleCode attribute. 
     * @return Returns the roleCode.
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Gets the rolodex attribute. 
     * @return Returns the rolodex.
     */
    public NonOrganizationalRolodex getRolodex() {
        return rolodex;
    }
    
    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * This has been modified from the Eclipse generated method
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((getContactRole() == null) ? 0 : getContactRole().hashCode());
        return PRIME * result + ((getContact() != null && getContact().getFullName() != null) ? getContact().getFullName().hashCode() : 0);
    }

    /**
     * This method determines if contact is an employee
     * @return
     */
    public boolean isEmployee() {
        return getContact() != null && (getContact() instanceof Person);
    }
       
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardContactId = null;
    }
    
    /**
     * Sets the awardContactId attribute value.
     * @param awardContactId The awardContactId to set.
     */
    public void setAwardContactId(Long awardContactid) {
        this.awardContactId = awardContactid;
    }
    
    /**
     * Sets the contactRole attribute value.
     * @param contactRole The contactRole to set.
     */
    public void setContactRole(ContactRole contactRole) {
        this.contactRole = contactRole;
        this.roleCode =  contactRole != null ? contactRole.getRoleCode() : null; 
    }
    
    public void setContactRoleCode(String roleCode) {
        this.roleCode = roleCode;
        refreshContactRole(); 
    }
    
    public void setEmailAddress(String emailAddress) {
        // no-op
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    /**
     * Sets the person attribute value.
     * @param person The person to set.
     */
    public void setPerson(Person person) {
        if(person != null && person.getPersonId() == null) {
            person = null;
        }
        
        this.person = person;
        this.rolodex = null;
        this.rolodexId = null;        
        if(person != null) {
            this.fullName =  person.getFullName();
            this.personId = person.getPersonId();
        } else {
            this.fullName =  null;
            this.personId = null;
        }
    }
    
    /**
     * Sets the personId attribute value.
     * @param personId The personId to set.
     */
    public void setPersonId(String personId) {
        this.personId = personId;
        refreshPerson();
    }
    
    public void setPhoneNumber(String phoneNumber) {
        // no-op
    }

    /**
     * Sets the roleCode attribute value.
     * @param roleCode The roleCode to set.
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        refreshContactRole();
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRolodex(NonOrganizationalRolodex rolodex) {
        if(rolodex != null && rolodex.getRolodexId() == null) {
            rolodex = null;
        }
        
        this.person = null;
        this.personId = null;
        this.rolodex = rolodex;
        if(rolodex != null) {
            this.fullName =  rolodex.getFullName();
            this.rolodexId = rolodex.getRolodexId();
        } else {
            this.fullName =  null;
            this.rolodexId = null;
        }
    }
    
    /**
     * Sets the rolodexId attribute value.
     * @param rolodexId The rolodexId to set.
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
        refreshRolodex();
    }

    public void setUnitName(String unitName) {
        // no-op
    }
    
    /**
     * This method looks up BOS
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * This method specifies the actual class implementing ContactRole
     * @return
     */
    protected abstract Class<?extends ContactRole> getContactRoleType();
    
    /**
     * This method specifies the identifier of the actual type implementing ContactRole
     * @return
     */
    protected abstract String getContactRoleTypeIdentifier();
    
    /**
     * @see org.kuali.kra.award.contacts.AwardContact#refreshContactRole()
     */
    protected ContactRole refreshContactRole() {
        ContactRole role;
        if(roleCode != null) {
            role = (ContactRole) getBusinessObjectService().findByPrimaryKey(getContactRoleType(), 
                                                                             getIdentifierMap(getContactRoleTypeIdentifier(), roleCode));
        } else {
            role = null;
        }
        setContactRole(role);
        return role;
    }
    
    @Override    
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> map = super.toStringMapper();
        map.put("awardContactId", awardContactId);    
        map.put("fullName", fullName);
        map.put("person", person);
        map.put("rolodex", rolodex);
        return map;
    }

    /**
     * Build an identifier map for the BOS lookup
     * @param identifierField
     * @param identifierValue
     * @return
     */
    protected Map<String, Object> getIdentifierMap(String identifierField, Object identifierValue) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(identifierField, identifierValue);
        return map;
    }
    
    /*
     * Refreshes the person from the personId
     */
    protected void refreshPerson() {
        if(personId != null) {
            if(this.person == null || !personId.equals(this.person.getPersonId())) {
                setPerson((Person) getBusinessObjectService().findByPrimaryKey(Person.class, getIdentifierMap(PERSON_ID_FIELD_NAME, personId)));
            }
        }
    }
    
    /*
     * Refreshes the rolodex from the rolodexId
     */
    protected void refreshRolodex() {
        NonOrganizationalRolodex rolodex;
        if(rolodexId != null) {
            rolodex = (NonOrganizationalRolodex) getBusinessObjectService().findByPrimaryKey(NonOrganizationalRolodex.class, 
                                                                                                getIdentifierMap(ROLODEX_ID_FIELD_NAME, rolodexId));
        } else {
            rolodex = null;
        }
        setRolodex(rolodex);
    }
}