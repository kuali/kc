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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.kim.bo.entity.dto.KimEntityAffiliationInfo;
import org.kuali.rice.kim.bo.entity.dto.KimEntityInfo;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class MultiCampusIdentityServiceImpl implements MultiCampusIdentityService {
    
    private BusinessObjectService businessObjectService;
    private IdentityService identityService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.service.MultiCampusIdentityService#getMultiCampusPrincipalName(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String getMultiCampusPrincipalName(String principalName, String campusCode) {
        String result = principalName;
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("multiCampusPrincipalName", principalName);
        Collection<KcPersonExtendedAttributes> multiCampusPersons = businessObjectService.findMatching(KcPersonExtendedAttributes.class, fieldValues);
        for (KcPersonExtendedAttributes multiCampusPerson : multiCampusPersons) {
            String principalId = multiCampusPerson.getPersonId();
            if (hasCampusAffiliation(principalId, campusCode)) {
                result = getIdentityService().getPrincipal(principalId).getPrincipalName();
                break;
            }
        }
        
        return result;
    }
    
    private boolean hasCampusAffiliation(String principalId, String campusCode) {
        boolean hasCampusAffiliation = false;
        
        KimEntityInfo entityInfo = getIdentityService().getEntityInfoByPrincipalId(principalId);
        for (KimEntityAffiliationInfo affiliation : entityInfo.getAffiliations()) {
            if (StringUtils.equals(campusCode, affiliation.getCampusCode())) {
                hasCampusAffiliation = true;
                break;
            }
        }
        
        return hasCampusAffiliation;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public IdentityService getIdentityService() {
        if (identityService == null) {
            identityService = KIMServiceLocator.getIdentityService();
        }
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
}