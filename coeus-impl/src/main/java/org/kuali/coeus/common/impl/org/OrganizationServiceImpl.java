/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.common.framework.org.crrspndnt.OrganizationCorrespondent;
import org.kuali.kra.iacuc.bo.IacucOrganizationCorrespondent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the implementation of the OrganizationService.
 * It provides service methods related to organization object.
 */
@Component("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    private static final String ORGANIZATION_ID = "organizationId";

	@Autowired
	@Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public String getOrganizationName(String organizationId) {
        String organizationName = null;
        Organization organization = getOrganization(organizationId);
        if(organization != null) {
            organizationName = organization.getOrganizationName();
        }
        return organizationName;
    }

    @Override
    public Organization getOrganization(String organizationId) {
        Organization organization = null;
        if (StringUtils.isNotEmpty(organizationId)) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(ORGANIZATION_ID, organizationId);
            organization = (Organization) getBusinessObjectService().findByPrimaryKey(Organization.class, primaryKeys);
        }

        return organization;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrganizationCorrespondent> retrieveOrganizationCorrespondentsByOrganizationId(String organizationId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(ORGANIZATION_ID, organizationId);
        List<OrganizationCorrespondent> organizationCorrespondents = 
            (List<OrganizationCorrespondent>) getBusinessObjectService().findMatching(OrganizationCorrespondent.class, queryMap);
        return organizationCorrespondents;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<IacucOrganizationCorrespondent> retrieveIacucOrganizationCorrespondentsByOrganizationId(String organizationId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(ORGANIZATION_ID, organizationId);
        List<IacucOrganizationCorrespondent> organizationCorrespondents = 
            (List<IacucOrganizationCorrespondent>) getBusinessObjectService().findMatching(IacucOrganizationCorrespondent.class, queryMap);
        return organizationCorrespondents;
    }
    
    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */

    public BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
    }
    
}
