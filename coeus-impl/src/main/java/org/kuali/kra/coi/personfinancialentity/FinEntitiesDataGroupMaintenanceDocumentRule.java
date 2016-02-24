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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class implements business rule for data group maintenance
 */
public class FinEntitiesDataGroupMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {

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
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
       


}
