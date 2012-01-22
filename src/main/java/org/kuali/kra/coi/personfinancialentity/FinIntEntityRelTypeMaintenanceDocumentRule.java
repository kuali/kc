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
 * This class implements the business rule for entity relation type code
 */
public class FinIntEntityRelTypeMaintenanceDocumentRule  extends KraMaintenanceDocumentRuleBase {

    private static final String REL_TYPE_SORT_ID_FIELD_NAME = "sortId";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    
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
        
        FinIntEntityRelType newFinIntEntityRelType = (FinIntEntityRelType) document.getNewMaintainableObject().getDataObject();
        
        isValid &= checkSortIdUniqueness(newFinIntEntityRelType);
        isValid &= checkDescriptionUniqueness(newFinIntEntityRelType);
        
        return isValid;
    }

    /*
     * validate that sort id is unique
     */
    @SuppressWarnings("unchecked")
    private boolean checkSortIdUniqueness(FinIntEntityRelType newFinIntEntityRelType) {
        boolean isValid = true;
        
        Integer groupSortId = newFinIntEntityRelType.getSortId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(REL_TYPE_SORT_ID_FIELD_NAME, groupSortId);
        Collection<FinIntEntityRelType> matchingRelTypes = getBusinessObjectService().findMatching(FinIntEntityRelType.class, fieldValues);
        
        for (FinIntEntityRelType relType : matchingRelTypes) {
            if (!ObjectUtils.equals(relType.getRelationshipTypeCode(), newFinIntEntityRelType.getRelationshipTypeCode())) {
                isValid = false;
                putFieldError(REL_TYPE_SORT_ID_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                    new String[] {"Sort Id"});
                break;
            }
        }
        
        return isValid;
    }
       
    /*
     * validate code description is unique
     */
    @SuppressWarnings("unchecked")
    private boolean checkDescriptionUniqueness(FinIntEntityRelType newFinIntEntityRelType) {
        boolean isValid = true;
        
        String description = newFinIntEntityRelType.getDescription();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(DESCRIPTION_FIELD_NAME, description);
        Collection<FinIntEntityRelType> matchingRelTypes = getBusinessObjectService().findMatching(FinIntEntityRelType.class, fieldValues);
        
        for (FinIntEntityRelType relType : matchingRelTypes) {
            if (!ObjectUtils.equals(relType.getRelationshipTypeCode(), newFinIntEntityRelType.getRelationshipTypeCode())) {
                isValid = false;
                putFieldError(DESCRIPTION_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                    new String[] {"Description"});
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
