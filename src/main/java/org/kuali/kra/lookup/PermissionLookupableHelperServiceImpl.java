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
package org.kuali.kra.lookup;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Overrides the normal {@link org.kuali.rice.kim.lookup.PermissionLookupableHelperServiceImpl} to modify lookup parameters for multicampus mode.
 */
public class PermissionLookupableHelperServiceImpl extends org.kuali.rice.kim.lookup.PermissionLookupableHelperServiceImpl {

    private static final long serialVersionUID = -7752147555985433570L;

    private static final String PRINCIPAL_NAME_FIELD = "assignedToPrincipal.principalName";
    
    private MultiCampusIdentityService multiCampusIdentityService;
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        boolean multiCampusEnabled = getParameterService().getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        if (multiCampusEnabled) {
            if (StringUtils.isNotBlank(fieldValues.get(PRINCIPAL_NAME_FIELD))) {
                String principalName = fieldValues.get(PRINCIPAL_NAME_FIELD);
                String campusCode = (String) GlobalVariables.getUserSession().retrieveObject(Constants.USER_CAMPUS_CODE_KEY);
                String multiCampusPrincipalName = getMultiCampusIdentityService().getMultiCampusPrincipalName(principalName, campusCode);
                fieldValues.put(PRINCIPAL_NAME_FIELD, multiCampusPrincipalName);
            }
        }
        
        return super.getSearchResults(fieldValues);
    }

    public MultiCampusIdentityService getMultiCampusIdentityService() {
        if (multiCampusIdentityService == null) {
            multiCampusIdentityService = KraServiceLocator.getService(MultiCampusIdentityService.class);
        }
        return multiCampusIdentityService;
    }

    public void setMultiCampusIdentityService(MultiCampusIdentityService multiCampusIdentityService) {
        this.multiCampusIdentityService = multiCampusIdentityService;
    }
    
}
