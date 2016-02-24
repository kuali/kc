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
package org.kuali.coeus.propdev.impl.editable;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.attr.PersonEditableField;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.Collection;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.PERSON_EDITABLE_FIELD_NAME_PROPERTY_KEY;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PERSON_EDITABLE_FIELD_EXISTS;

/**
 * Business rules class for the <code>{@link PersonEditableFieldMaintenanceDocument}</code>. When a <code>{@link PersonEditableField}</code> BO is created,
 * we need to check to see if it already exists or not. If it already exists, don't create a new one. Only change an existing one.
 * 
 */
public class PersonEditableFieldRule extends KcMaintenanceDocumentRuleBase {
    
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
        public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
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
        public BusinessObjectService getBusinessObjectService() {return getService(BusinessObjectService.class);}
}
