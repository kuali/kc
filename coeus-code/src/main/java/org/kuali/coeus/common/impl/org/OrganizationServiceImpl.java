/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.common.framework.org.crrspndnt.OrganizationCorrespondent;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
