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
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliationContract;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;

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
        
        EntityContract entityInfo = getIdentityService().getEntityByPrincipalId(principalId);
        for (EntityAffiliationContract affiliation : entityInfo.getAffiliations()) {
            if (StringUtils.equals(campusCode, affiliation.getCampusCode())) {
                hasCampusAffiliation = true;
                break;
            }
        }
        
        return hasCampusAffiliation;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KRADServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public IdentityService getIdentityService() {
        if (identityService == null) {
            identityService = KimApiServiceLocator.getIdentityService();
        }
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
}