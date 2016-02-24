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
 * This class implements the business rule for entity relation type code
 */
public class FinIntEntityRelTypeMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {

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
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
       

}
