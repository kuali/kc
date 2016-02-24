/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.framework.compliance.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * Provides a value finder for module-specific configuration of Special Review Types.
 */
public abstract class SpecialReviewTypeValuesFinder extends UifKeyValuesFinderBase {

    private static final String MODULE_CODE_NAME = "moduleCode";
    private static final String PERMISSION_NAME = "View Active Special Review Types";
    
    private KeyValuesService keyValuesService;
    private BusinessObjectService businessObjectService;
    private IdentityService identityManagementService;
    private PermissionService permissionService;
    
    @Override
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
            keyValuesService = (KeyValuesService) KcServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }
    
    public void setKeyValuesService(KeyValuesService keyValuesService) {
        this.keyValuesService = keyValuesService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public IdentityService getIdentityService() {
        if (identityManagementService == null) {
            identityManagementService = KcServiceLocator.getService(IdentityService.class);
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
