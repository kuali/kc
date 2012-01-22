/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.Constants.PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PERSON_EDITABLE_FIELD_EXISTS;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Business rules class for the <code>{@link PersonEditableFieldMaintenanceDocument}</code>. When a <code>{@link PersonEditableField}</code> BO is created,
 * we need to check to see if it already exists or not. If it already exists, don't create a new one. Only change an existing one.
 * 
 */
public class PersonEditableFieldRule extends MaintenanceDocumentRuleBase {
    
        /**
         * Constructs a PersonEditableFieldRule.java.
         */
        public PersonEditableFieldRule() {
            super();
        }
        
        /**
         * Looks at existing persisted <code>{@link PersonEditableField}</code> BO's to see if this one already exists.
         * 
         * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
         */ 
        protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
            boolean retval = true;
            if (document.isNew()) {
                PersonEditableField newField = (PersonEditableField) getNewBo();
                
                for (PersonEditableField existingField : (Collection<PersonEditableField>) getBusinessObjectService().findAll(PersonEditableField.class)) {
                    if(StringUtils.equals(existingField.getFieldName(), newField.getFieldName()) && StringUtils.equals(existingField.getModuleCode(), newField.getModuleCode())) {
                        GlobalVariables.getMessageMap().putError(PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY, ERROR_PERSON_EDITABLE_FIELD_EXISTS, existingField.getFieldName(), existingField.getCoeusModule().getDescription());
                        retval = false;
                        break;
                    }
                }
            } else if (document.isEdit()) {
                PersonEditableField newField = (PersonEditableField) getNewBo();
                
                for (PersonEditableField existingField : (Collection<PersonEditableField>) getBusinessObjectService().findAll(PersonEditableField.class)) {
                    if(!ObjectUtils.equalByKeys(newField, existingField) && StringUtils.equals(existingField.getFieldName(), newField.getFieldName()) && StringUtils.equals(existingField.getModuleCode(), newField.getModuleCode())) {
                        GlobalVariables.getMessageMap().putError(PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY, ERROR_PERSON_EDITABLE_FIELD_EXISTS, existingField.getFieldName(), existingField.getCoeusModule().getDescription());
                        retval = false;
                        break;
                    }
                }
            } 
            
            return retval;
        }
        
        /**
         * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
         */
        protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
            return true;
        }
        
        /**
         * Read Only Access to <code>{@link BusinessObjectService}</code>
         * 
         * @return BusinessObjectService instance
         */
        public BusinessObjectService getBusinessObjectService() {
            return getService(BusinessObjectService.class);
        }
}
