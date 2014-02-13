/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi;

import org.apache.commons.lang.ObjectUtils;
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
 * This class implements the business rule for COI note type code
 */
public class CoiNoteTypeMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {

    private static final String COI_NOTE_TYPE_SORT_ID_FIELD_NAME = "sortId";
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
        
        CoiNoteType newCoiNoteType = (CoiNoteType) document.getNewMaintainableObject().getDataObject();
        
        isValid &= checkSortIdUniqueness(newCoiNoteType);
        isValid &= checkDescriptionUniqueness(newCoiNoteType);
        
        return isValid;
    }

    /*
     * validate that sort id is unique
     */
    @SuppressWarnings("unchecked")
    private boolean checkSortIdUniqueness(CoiNoteType newCoiNoteType) {
        boolean isValid = true;
        
        Integer groupSortId = newCoiNoteType.getSortId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(COI_NOTE_TYPE_SORT_ID_FIELD_NAME, groupSortId);
        Collection<CoiNoteType> matchingRelTypes = getBusinessObjectService().findMatching(CoiNoteType.class, fieldValues);
        
        for (CoiNoteType noteType : matchingRelTypes) {
            if (!ObjectUtils.equals(noteType.getNoteTypeCode(), newCoiNoteType.getNoteTypeCode())) {
                isValid = false;
                putFieldError(COI_NOTE_TYPE_SORT_ID_FIELD_NAME, KeyConstants.ERROR_DUPLICATE_PROPERTY, 
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
    private boolean checkDescriptionUniqueness(CoiNoteType newFinIntEntityRelType) {
        boolean isValid = true;
        
        String description = newFinIntEntityRelType.getDescription();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(DESCRIPTION_FIELD_NAME, description);
        Collection<CoiNoteType> matchingRelTypes = getBusinessObjectService().findMatching(CoiNoteType.class, fieldValues);
        
        for (CoiNoteType noteType : matchingRelTypes) {
            if (!ObjectUtils.equals(noteType.getNoteTypeCode(), newFinIntEntityRelType.getNoteTypeCode())) {
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
