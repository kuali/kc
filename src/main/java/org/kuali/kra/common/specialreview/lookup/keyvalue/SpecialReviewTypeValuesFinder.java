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
package org.kuali.kra.common.specialreview.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.SpecialReviewUsage;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Provides a value finder for module-specific configuration of Special Review Types.
 */
public abstract class SpecialReviewTypeValuesFinder extends KeyValuesBase {

    private static final String MODULE_CODE_NAME = "moduleCode";
    private static final String PERMISSION_NAME = "View Active Special Review Types";
    
    private KeyValuesService keyValuesService;
    private BusinessObjectService businessObjectService;
    private IdentityService identityManagementService;
    private PermissionService permissionService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = filterActiveSpecialReviewUsageTypes(createKeyValues());
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        
        return keyValues;
    }
    
    @SuppressWarnings("unchecked")
    private List<KeyValue> createKeyValues() {
        Collection<SpecialReviewType> specialReviewTypes = getKeyValuesService().findAllOrderBy(SpecialReviewType.class, "sortId", true);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (SpecialReviewType specialReviewType : specialReviewTypes) {
            keyValues.add(new ConcreteKeyValue(specialReviewType.getSpecialReviewTypeCode(), specialReviewType.getDescription()));                            
        }       
        return keyValues;
    }
    
    private List<KeyValue> filterActiveSpecialReviewUsageTypes(List<KeyValue> unfilteredKeyValues) {
        final List<KeyValue> filteredKeyValues = new ArrayList<KeyValue>();
        
        boolean canViewNonGlobalSpecialReviewTypes = getPermissionService().hasPermission(
                GlobalVariables.getUserSession().getPrincipalId(), KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE , PERMISSION_NAME);
        
        Collection<SpecialReviewUsage> specialReviewUsages = getSpecialReviewUsages();
        for (KeyValue item : unfilteredKeyValues) {
            SpecialReviewUsage itemSpecialReviewUsage = null;
            for (SpecialReviewUsage specialReviewUsage : specialReviewUsages) {
                if (StringUtils.equals(specialReviewUsage.getSpecialReviewTypeCode(), String.valueOf(item.getKey()))) {
                    itemSpecialReviewUsage = specialReviewUsage;
                    break;
                }
            }
            if (itemSpecialReviewUsage != null && itemSpecialReviewUsage.isActive()) {
                if (itemSpecialReviewUsage.isGlobal() || canViewNonGlobalSpecialReviewTypes) {
                    filteredKeyValues.add(item);
                }
            }
        }
        
        return filteredKeyValues;
    }
    
    /**
     * Gets the Coeus Module code for the module using this value finder.
     * @return the Coeus Module code
     */
    public abstract String getModuleCode();
    
    @SuppressWarnings("unchecked")
    private Collection<SpecialReviewUsage> getSpecialReviewUsages() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE_NAME, getModuleCode());
        
        return getBusinessObjectService().findMatching(SpecialReviewUsage.class, fieldValues);
    }
    
    public KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = (KeyValuesService) KraServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
    
    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public IdentityService getIdentityService() {
        if (identityManagementService == null) {
            identityManagementService = KraServiceLocator.getService(IdentityService.class);
        }
        return identityManagementService;
    }
    
    public void setIdentityManagementService(IdentityService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }
    
    public PermissionService getPermissionService() {
        if (permissionService == null) {
            permissionService = KimApiServiceLocator.getPermissionService();
        }
        return permissionService;
    }
    
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}