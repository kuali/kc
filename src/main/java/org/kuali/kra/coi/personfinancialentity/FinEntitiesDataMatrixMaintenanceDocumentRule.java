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
 * This class implements business rule for data matrix maintenance
 */
public class FinEntitiesDataMatrixMaintenanceDocumentRule   extends KraMaintenanceDocumentRuleBase {

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
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
       

}
