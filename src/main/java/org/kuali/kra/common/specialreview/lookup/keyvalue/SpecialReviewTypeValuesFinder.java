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
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Provides a value finder for module-specific configuration of Special Review Types.
 */
public abstract class SpecialReviewTypeValuesFinder extends KeyValuesBase {

    private static final String MODULE_CODE_NAME = "moduleCode";
    private static final String PERMISSION_NAME = "View Active Special Review Types";
    
    private KeyValuesService keyValuesService;
    private BusinessObjectService businessObjectService;
    private IdentityManagementService identityManagementService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<?> getKeyValues() {
        List<KeyLabelPair> keyValues = filterActiveSpecialReviewUsageTypes(createKeyValues());
        keyValues.add(0, new KeyLabelPair(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        
        return keyValues;
    }
    
    @SuppressWarnings("unchecked")
    private List<KeyLabelPair> createKeyValues() {
        Collection<SpecialReviewType> specialReviewTypes = getKeyValuesService().findAllOrderBy(SpecialReviewType.class, "sortId", true);
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        for (SpecialReviewType specialReviewType : specialReviewTypes) {
            keyValues.add(new KeyLabelPair(specialReviewType.getSpecialReviewTypeCode(), specialReviewType.getDescription()));                            
        }       
        return keyValues;
    }
    
    private List<KeyLabelPair> filterActiveSpecialReviewUsageTypes(List<KeyLabelPair> unfilteredKeyValues) {
        final List<KeyLabelPair> filteredKeyValues = new ArrayList<KeyLabelPair>();
        
        boolean canViewNonGlobalSpecialReviewTypes = getIdentityManagementService().hasPermission(
                GlobalVariables.getUserSession().getPrincipalId(), KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE , PERMISSION_NAME, new AttributeSet());
        
        Collection<SpecialReviewUsage> specialReviewUsages = getSpecialReviewUsages();
        for (KeyLabelPair item : unfilteredKeyValues) {
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
    
    public IdentityManagementService getIdentityManagementService() {
        if (identityManagementService == null) {
            identityManagementService = KraServiceLocator.getService(IdentityManagementService.class);
        }
        return identityManagementService;
    }
    
    public void setIdentityManagementService(IdentityManagementService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }

}