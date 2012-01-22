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
package org.kuali.kra.coi.personfinancialentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class implements business rule for data group maintenance
 */
public class FinEntitiesDataGroupMaintenanceDocumentRule  extends KraMaintenanceDocumentRuleBase {

    private static final String GROUP_SORT_ID_FIELD_NAME = "dataGroupSortId";
    private static final String GROUP_NAME_FIELD_NAME = "dataGroupName";
    
    private transient BusinessObjectService businessObjectService;

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkUniqueness(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkUniqueness(document);
    }
    

    private boolean checkUniqueness(MaintenanceDocument document) {
        boolean isValid = true;
        
        FinEntitiesDataGroup newFinEntitiesDataGroup = (FinEntitiesDataGroup) document.getNewMaintainableObject().getDataObject();
        
        isValid &= checkSortIdUniqueness(newFinEntitiesDataGroup);
        isValid &= checkGroupNameUniqueness(newFinEntitiesDataGroup);
        
        return isValid;
    }

    
    /*
     * validate uniqueness of group sort id
     */
    @SuppressWarnings("unchecked")
    private boolean checkSortIdUniqueness(FinEntitiesDataGroup newFinEntitiesDataGroup) {
        boolean isValid = true;
        
        Integer groupSortId = newFinEntitiesDataGroup.getDataGroupSortId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(GROUP_SORT_ID_FIELD_NAME, groupSortId);
        Collection<FinEntitiesDataGroup> matchingDataGroups = getBusinessObjectService().findMatching(FinEntitiesDataGroup.class, fieldValues);
        
        for (FinEntitiesDataGroup dataGroup : matchingDataGroups) {
            if (!ObjectUtils.equals(dataGroup.getDataGroupId(), newFinEntitiesDataGroup.getDataGroupId())) {
                isValid = false;
                putFieldError(GROUP_SORT_ID_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                    new String[] {"Group Sort Id"});
                break;
            }
        }
        
        return isValid;
    }
       
    
    /*
     * validate uniqueness of group name
     */
    @SuppressWarnings("unchecked")
    private boolean checkGroupNameUniqueness(FinEntitiesDataGroup newFinEntitiesDataGroup) {
        boolean isValid = true;
        
        String groupName = newFinEntitiesDataGroup.getDataGroupName();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(GROUP_NAME_FIELD_NAME, groupName);
        Collection<FinEntitiesDataGroup> matchingDataGroups = getBusinessObjectService().findMatching(FinEntitiesDataGroup.class, fieldValues);
        
        for (FinEntitiesDataGroup dataGroup : matchingDataGroups) {
            if (!ObjectUtils.equals(dataGroup.getDataGroupId(), newFinEntitiesDataGroup.getDataGroupId())) {
                isValid = false;
                putFieldError(GROUP_NAME_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                    new String[] {"Group Name"});
                break;
            }
        }
        
        return isValid;
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
       


}
