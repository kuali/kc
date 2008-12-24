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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.OrganizationService;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitService;

/**
 * This class...
 */
public class OrganizationServiceImpl implements OrganizationService {
    private BusinessObjectService businessObjectService;

    public String getOrganizationName(String organizationId) {
        String organizationName = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(organizationId)) {
            primaryKeys.put("organizationId", organizationId);
            Organization organization = (Organization) businessObjectService.findByPrimaryKey(Organization.class, primaryKeys);
            if (organization != null) {
                organizationName = organization.getOrganizationName();
            }
        }

        System.out.println(organizationName);
        return organizationName;
    }

    /**
     * @see org.kuali.kra.service.OrganizationService#getPersonOrganization()
     */
    public Organization getPersonOrganization() {
        String organizationId = Constants.DEFAULT_PROTOCOL_ORGANIZATION_ID;
        Person person = getPerson();
        String currentUnit = person.getHomeUnit();
        do {
            if(!StringUtils.isEmpty(currentUnit)) {
                Unit unit = getUnit(currentUnit);
                if(!StringUtils.isEmpty(unit.getOrganizationId())) {
                    organizationId = unit.getOrganizationId();
                    break;
                }
                currentUnit = unit.getParentUnitNumber();
            }
        } while(currentUnit != null);

        return getOrganization(organizationId);
    }
    
    /**
     * Get the person from the database.  This is the user in the current session.
     * @return person
     */
    private Person getPerson() {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        return personService.getPersonByName(username);
    }
    
    /**
     * Get the unit from the database based on unit number.  
     * @return unit
     */
    private Unit getUnit(String unitNumber) {
        UnitService unitService = KraServiceLocator.getService(UnitService.class);
        return unitService.getUnit(unitNumber);
    }

    /**
     * This method is to get Organization for a given organization id
     * @param organizationId
     * @return Organization
     */
    private Organization getOrganization(String organizationId) {
        Organization organization = null;

        if (StringUtils.isNotEmpty(organizationId)) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("organizationId", organizationId);
            organization = (Organization) businessObjectService.findByPrimaryKey(Organization.class, primaryKeys);
        }

        return organization;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
