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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleService;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

/**
 * This class defines an AwardContact
 */
public abstract class AwardContact extends AwardAssociate {

    private static final String ROLODEX_ID_FIELD_NAME = "rolodexId";


    private static final long serialVersionUID = 4386300861743037298L;

    /**
     * These field are OJB hacks. Because anonymous access wouldn't work for more than one field, the Award,
     * we need to provide real fields to be persisted
     */
    @AwardSyncableProperty(key = true)
    protected String personId;

    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    protected Integer rolodexId;

    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    protected String roleCode;

    private Long awardContactId;

    protected ContactRole contactRole;

    @AwardSyncableProperty
    private String fullName;

    private KcPerson person;

    private NonOrganizationalRolodex rolodex;

    private transient KcPersonService kcPersonService;

    @Transient
    private transient PropAwardPersonRoleService propAwardPersonRoleService;

    public AwardContact() {
    }

    AwardContact(NonOrganizationalRolodex rolodex, ContactRole contactRole) {
        this();
        setRolodex(rolodex);
        setContactRole(contactRole);
    }

    AwardContact(KcPerson person, ContactRole role) {
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
            if (getContact().getFullName() == null) {
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
        if (contact == null) {
            if (personId != null) {
                refreshPerson();
                contact = person;
            } else if (rolodexId != null) {
                refreshRolodex();
                contact = rolodex;
            }
        }
        return contact;
    }


    public String getContactOrganizationName() {
        Contactable contact = getContact();
        return contact != null ? contact.getContactOrganizationName() : null;
    }


    public String getGenericId() {
        return rolodexId != null ? rolodexId.toString() : personId;
    }


    public String getOrganizationIdentifier() {
        return getContact() != null ? getContact().getOrganizationIdentifier() : null;
    }

    /**
     * Gets the contactRole attribute. 
     * @return Returns the contactRole.
     */
    public ContactRole getContactRole() {
    	return getRole();
    }


    public String getContactRoleCode() {
        return roleCode;
    }


    public String getEmailAddress() {
        return getContact() != null ? getContact().getEmailAddress() : null;
    }


    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public KcPerson getPerson() {
        if (person == null && personId != null) {
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
        if (rolodex == null && rolodexId != null) {
             refreshRolodex();
         }
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
        return getContact() != null && (getContact() instanceof KcPerson);
    }

    @Override
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
        this.roleCode = contactRole != null ? contactRole.getRoleCode() : null;
    }

    public void setContactRoleCode(String roleCode) {
        this.roleCode = roleCode;
        refreshContactRole();
    }

    public void setEmailAddress(String emailAddress) {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Sets the person attribute value.
     * @param person The person to set.
     */
    public void setPerson(KcPerson person) {
        if (person != null && person.getPersonId() == null) {
            person = null;
        }
        this.person = person;
        if (person != null) {
            this.rolodex = null;
            this.rolodexId = null;
            this.fullName = person.getFullName();
            this.personId = person.getPersonId();
        } else {
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
        if (rolodex != null && rolodex.getRolodexId() == null) {
            rolodex = null;
        }
        this.rolodex = rolodex;
        if (rolodex != null) {
            this.person = null;
            this.personId = null;
            this.fullName = rolodex.getFullName();
            this.rolodexId = rolodex.getRolodexId();
        } else {
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
    }

    /**
     * This method looks up BOS
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KcServiceLocator.getService(BusinessObjectService.class);
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

    /**
     * This method specifies the actual class implementing ContactRole
     * @return
     */
    protected abstract Class<? extends ContactRole> getContactRoleType();

    protected abstract Map<String, Object> getContactRoleIdentifierMap();

    protected ContactRole refreshContactRole() {
        ContactRole role = null;
        if (roleCode != null) {
        	List<ContactRole> contactRoles = (List)getBusinessObjectService().findMatching(getContactRoleType(), getContactRoleIdentifierMap());
        	if(!contactRoles.isEmpty()) {
                role = (ContactRole)contactRoles.get(0); 
        	}
        }
        setContactRole(role);
        return role;
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
        if (personId != null) {
            if (this.person == null || !personId.equals(this.person.getPersonId())) {
                setPerson(getKcPersonService().getKcPersonByPersonId(personId));
            }
        }
    }

    /*
     * Refreshes the rolodex from the rolodexId
     */
    protected void refreshRolodex() {
        NonOrganizationalRolodex rolodex;
        if (rolodexId != null) {
            rolodex = (NonOrganizationalRolodex) getBusinessObjectService().findByPrimaryKey(NonOrganizationalRolodex.class, getIdentifierMap(ROLODEX_ID_FIELD_NAME, rolodexId));
        } else {
            rolodex = null;
        }
        setRolodex(rolodex);
    }
    
    public ContactRole getRole() {
    	return refreshContactRole();
    }
    
	protected PropAwardPersonRoleService getPropAwardPersonRoleService() {
		if (propAwardPersonRoleService == null) {
			propAwardPersonRoleService = KcServiceLocator.getService(PropAwardPersonRoleService.class);
		}
		return propAwardPersonRoleService;
	}

	public void setPropAwardPersonRoleService(
			PropAwardPersonRoleService propAwardPersonRoleService) {
		this.propAwardPersonRoleService = propAwardPersonRoleService;
	}

}
