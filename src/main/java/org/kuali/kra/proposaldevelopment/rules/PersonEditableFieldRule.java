/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.Collection;

import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.PersonEditableField;

import static org.kuali.core.util.GlobalVariables.getErrorMap;
import static org.kuali.core.util.ObjectUtils.equalByKeys;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PERSON_EDITABLE_FIELD_EXISTS;

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
         * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
         */ 
        protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
            boolean retval = true;
            if (document.isNew()) {
                PersonEditableField newField = (PersonEditableField) getNewBo();
                
                for (PersonEditableField existingField : (Collection<PersonEditableField>) getBusinessObjectService().findAll(PersonEditableField.class)) {
                    retval &= !equalByKeys(existingField, newField);
                }
            }
            
            if (!retval) {
                getErrorMap().putError(PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY, ERROR_PERSON_EDITABLE_FIELD_EXISTS); 
            }
            
            return retval;
        }
        
        /**
         * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
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
