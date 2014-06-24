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
package org.kuali.coeus.common.impl.multicampus;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.multicampus.MultiCampusIdentityService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliationContract;
import org.kuali.rice.kim.api.identity.entity.EntityContract;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component("multiCampusIdentityService")
public class MultiCampusIdentityServiceImpl implements MultiCampusIdentityService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Override
    public String getMultiCampusPrincipalName(String principalName, String campusCode) {
        String result = principalName;
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("multiCampusPrincipalName", principalName);
        Collection<KcPersonExtendedAttributes> multiCampusPersons = getBusinessObjectService().findMatching(KcPersonExtendedAttributes.class, fieldValues);
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
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
}