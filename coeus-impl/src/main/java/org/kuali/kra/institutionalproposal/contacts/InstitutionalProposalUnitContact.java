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
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.unit.UnitContactType;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalUnitContact extends InstitutionalProposalContact {


    private static final long serialVersionUID = -4018882949510183728L;
    
    public  static final String OSP_ADMINISTRATOR = "OSP_ADMINISTRATOR";
    private static final String UNIT_ADMINISTRATOR_TYPE_CODE = "UNIT_ADMINISTRATOR_TYPE_CODE";
    private static final String CONTACT_TYPE_CODE = "CONTACT_TYPE_CODE";
    private UnitContactType unitContactType;
    private UnitAdministratorType unitAdministratorType;
    private String unitAdministratorTypeCode;
    
    

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
    

    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
    }
    

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

    @Override
    protected Class<?extends ContactRole> getContactRoleType() {
        return getUnitContactType() == UnitContactType.ADMINISTRATOR ? UnitAdministratorType.class : ContactType.class;
    }
    
    protected String getContactRoleTypeIdentifier() {
        return  getUnitContactType() == UnitContactType.ADMINISTRATOR ? UNIT_ADMINISTRATOR_TYPE_CODE : CONTACT_TYPE_CODE;
    }
    @Override
    protected Map<String, Object> getContactRoleIdentifierMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(getContactRoleTypeIdentifier(), getRoleCode());
        return map;
     }

}
