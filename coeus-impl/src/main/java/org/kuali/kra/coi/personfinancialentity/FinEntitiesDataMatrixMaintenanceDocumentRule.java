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
 * This class implements business rule for data matrix maintenance
 */
public class FinEntitiesDataMatrixMaintenanceDocumentRule   extends KcMaintenanceDocumentRuleBase {

    private static final String COLUMN_SORT_ID_FIELD_NAME = "columnSortId";
    private static final String GROUP_ID_FIELD_NAME = "dataGroupId";
    
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
        
        FinEntitiesDataMatrix newFinEntitiesDataMatrix = (FinEntitiesDataMatrix) document.getNewMaintainableObject().getDataObject();
        
        isValid &= checkSortIdUniqueness(newFinEntitiesDataMatrix);
        isValid &= checkGroupIdExist(newFinEntitiesDataMatrix);
        
        return isValid;
    }

    
    /*
     * validate uniqueness of column sort id
     */
    @SuppressWarnings("unchecked")
    private boolean checkSortIdUniqueness(FinEntitiesDataMatrix newFinEntitiesDataMatrix) {
        boolean isValid = true;
        
        Integer coloumnSortId = newFinEntitiesDataMatrix.getColumnSortId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(COLUMN_SORT_ID_FIELD_NAME, coloumnSortId);
        Collection<FinEntitiesDataMatrix> matchingDataMatrixs = getBusinessObjectService().findMatching(FinEntitiesDataMatrix.class, fieldValues);
        
        for (FinEntitiesDataMatrix dataMatrix : matchingDataMatrixs) {
            if (!ObjectUtils.equals(dataMatrix.getColumnName(), newFinEntitiesDataMatrix.getColumnName())
                    && ObjectUtils.equals(dataMatrix.getDataGroupId(), newFinEntitiesDataMatrix.getDataGroupId())) {
                isValid = false;
                putFieldError(COLUMN_SORT_ID_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                    new String[] {"Column Sort Id"});
                break;
            }
        }
        
        return isValid;
    }
       
    /*
     * validate group id exist, ie, data group exist
     */
    @SuppressWarnings("unchecked")
    private boolean checkGroupIdExist(FinEntitiesDataMatrix newFinEntitiesDataMatrix) {
        boolean isValid = true;

        Integer groupId = newFinEntitiesDataMatrix.getDataGroupId();

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(GROUP_ID_FIELD_NAME, groupId);

        if (getBusinessObjectService().countMatching(FinEntitiesDataGroup.class, fieldValues) == 0) {
            isValid = false;
            putFieldError(GROUP_ID_FIELD_NAME, KeyConstants.ERROR_DATA_GROUP_NOT_EXIST, new String[] { groupId.toString() });
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
