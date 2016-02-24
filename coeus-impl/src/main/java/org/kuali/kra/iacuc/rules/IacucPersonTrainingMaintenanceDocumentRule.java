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
package org.kuali.kra.iacuc.rules;

import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Overrides the custom save and approve methods of the maintenance document processing to check uniqueness constraints.
 */
public class IacucPersonTrainingMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    private static final String PERSON_TRAINING_FIELD_NAME = "personTrainingId";
    
    private transient BusinessObjectService businessObjectService;

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkIacucPersonTraining(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkIacucPersonTraining(document);
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return checkIacucPersonTraining(document);
    }

    @Override 
    public boolean isDocumentValidForSave(MaintenanceDocument document) {
        return checkIacucPersonTraining(document);
    }

    /**
     * Validates iacuc person training.
     *
     * @param document the maintenance document to check
     * @return true if all uniqueness constraints succeed, false otherwise
     */
    private boolean checkIacucPersonTraining(MaintenanceDocument document) {
        boolean isValid = true;
        
        IacucPersonTraining newIacucPersonTraining = (IacucPersonTraining) document.getNewMaintainableObject().getDataObject();
        
        isValid &= isPersonTrainingValid(newIacucPersonTraining);
        
        return isValid;
    }

    /**
     * Validates person id and person training match.
     * 
     * @param newIacucPersonTraining the new person training
     * @return 
     */
    @SuppressWarnings("deprecation")
    private boolean isPersonTrainingValid(IacucPersonTraining newIacucPersonTraining) {
        boolean isValid = true;
        Integer personTrainingId = newIacucPersonTraining.getPersonTrainingId();
        if(ObjectUtils.isNotNull(personTrainingId)) {
            Map<String, Integer> fieldValues = new HashMap<String, Integer>();
            fieldValues.put(PERSON_TRAINING_FIELD_NAME, personTrainingId);
            PersonTraining personTraining = getBusinessObjectService().findByPrimaryKey(PersonTraining.class, fieldValues);
            if(ObjectUtils.isNull(personTraining)) {
                isValid = false;
                putFieldError(PERSON_TRAINING_FIELD_NAME, KeyConstants.ERROR_IACUC_VALIDATION_INVALID_PERSON_TRAINING); 
            }else {
                newIacucPersonTraining.setPersonId(personTraining.getPersonId());
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
