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
import org.kuali.kra.bo.Organization;
import org.kuali.kra.service.OrganizationService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class provides the implementation of the OrganizationService.
 * It provides service methods related to organization object.
 */
public class OrganizationServiceImpl implements OrganizationService {
    private BusinessObjectService businessObjectService;
    private static final String ORGANIZATION_ID = "organizationId";

    /**
     * @see org.kuali.kra.service.OrganizationService#getOrganizationName(java.lang.String)
     */
    public String getOrganizationName(String organizationId) {
        String organizationName = null;
        Organization organization = getOrganization(organizationId);
        if(organization != null) {
            organizationName = organization.getOrganizationName();
        }
        return organizationName;
    }

    /**
     * @see org.kuali.kra.service.OrganizationService#getOrganization(java.lang.String)
     */
    public Organization getOrganization(String organizationId) {
        Organization organization = null;
        if (StringUtils.isNotEmpty(organizationId)) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(ORGANIZATION_ID, organizationId);
            organization = (Organization) businessObjectService.findByPrimaryKey(Organization.class, primaryKeys);
        }

        return organization;
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
