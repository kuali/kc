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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class InstitutionalProposalUnitContact extends InstitutionalProposalContact {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4018882949510183728L;
    
    public  static final String OSP_ADMINISTRATOR = "OSP_ADMINISTRATOR";
    private static final String UNIT_ADMINISTRATOR_TYPE_CODE = "UNIT_ADMINISTRATOR_TYPE_CODE";
    private static final String CONTACT_TYPE_CODE = "CONTACT_TYPE_CODE";
    private UnitContactType unitContactType;
    private UnitAdministratorType unitAdministratorType;
    private String unitAdministratorTypeCode;
    
    
    /**
     * Constructs a AwardUnitContact.java.
     */
    public InstitutionalProposalUnitContact() {
        super();
    }
    
    /**
     * Constructs a AwardUnitContact.java.
     * @param rolodex
     * @param role
     * @param unitContactType
     */
    public InstitutionalProposalUnitContact(NonOrganizationalRolodex rolodex, ContactRole role, UnitContactType unitContactType) {
        super(rolodex, role);
        this.unitContactType = unitContactType;
    }

    /**
     * Constructs a AwardUnitContact.java.
     * @param person
     * @param role
     * @param unitContactType
     */
    public InstitutionalProposalUnitContact(KcPerson person, ContactRole role, UnitContactType unitContactType) {
        super(person, role);
        this.unitContactType = unitContactType;
    }

    /**
     * Constructs a AwardUnitContact.java.
     * @param unitContactType
     */
    InstitutionalProposalUnitContact(UnitContactType unitContactType) {
        this.unitContactType = unitContactType;
    }
    
    @SuppressWarnings("unchecked")
    public String getUnitAdministratorUnitNumberByPersonId() {
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put("unitNumber", getInstitutionalProposal().getLeadUnitNumber());
            criteria.put("personId", getPerson().getPersonId());
            List<UnitAdministrator> results = new ArrayList<UnitAdministrator>(
                    getBusinessObjectService().findMatching(UnitAdministrator.class, criteria));
            if (results.size() == 0) {
                return getPerson().getUnit().getUnitNumber(); //display home unit number if they are not a default administrator.
            } else {
                return results.get(0).getUnitNumber(); //display Unit number of default unit administrator table.
            }
    }
    
    /**
     * This method...
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }
    
    /**
     * @return
     */
    public UnitContactType getUnitContactType() {
        return unitContactType;
    }
    
    

    /**
     * Gets the unitAdministratorType attribute. 
     * @return Returns the unitAdministratorType.
     */
    public UnitAdministratorType getUnitAdministratorType() {
        return unitAdministratorType;
    }

    /**
     * Sets the unitAdministratorType attribute value.
     * @param unitAdministratorType The unitAdministratorType to set.
     */
    public void setUnitAdministratorType(UnitAdministratorType unitAdministratorType) {
        this.unitAdministratorType = unitAdministratorType;
    }
    
    

    /**
     * Gets the unitAdministratorTypeCode attribute. 
     * @return Returns the unitAdministratorTypeCode.
     */
    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    /**
     * Sets the unitAdministratorTypeCode attribute value.
     * @param unitAdministratorTypeCode The unitAdministratorTypeCode to set.
     */
    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    /**
     * This method determines if unit contact is an OSP Admin
     * @return
     */
    public boolean isOspAdministrator() {
        boolean ospAdmin;
        if(getUnitContactType() == UnitContactType.ADMINISTRATOR && roleCode != null && getContactRole() == null) {
            ospAdmin = OSP_ADMINISTRATOR.equals(refreshContactRole().getRoleDescription());
        } else {
            ospAdmin = false;
        }
        return ospAdmin;
    }
    
    public void setUnitContactType(UnitContactType contactType) {
        this.unitContactType = contactType;
    }

    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleType()
     */
    @Override
    protected Class<?extends ContactRole> getContactRoleType() {
        return getUnitContactType() == UnitContactType.ADMINISTRATOR ? UnitAdministratorType.class : ContactType.class;
    }
    /**
     * @see org.kuali.kra.award.contacts.AwardContact#getContactRoleTypeIdentifier()
     */
    @Override
    protected String getContactRoleTypeIdentifier() {
        return  getUnitContactType() == UnitContactType.ADMINISTRATOR ? UNIT_ADMINISTRATOR_TYPE_CODE : CONTACT_TYPE_CODE;
    }
}
