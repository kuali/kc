/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionMaintenanceRule extends KraMaintenanceDocumentRuleBase {

    @Override
    protected boolean dataDictionaryValidate(MaintenanceDocument document) {
        // TODO Auto-generated method stub
        //return super.dataDictionaryValidate(document);
        LOG.debug("MaintenanceDocument validation beginning");

        // explicitly put the errorPath that the dictionaryValidationService requires
        GlobalVariables.getErrorMap().addToErrorPath("document.newMaintainableObject.businessObject");

        // document must have a newMaintainable object
        Maintainable newMaintainable = document.getNewMaintainableObject();
        if (newMaintainable == null) {
            GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject.businessObject");
            throw new ValidationException("Maintainable object from Maintenance Document '" + document.getDocumentTitle() + "' is null, unable to proceed.");
        }

        // document's newMaintainable must contain an object (ie, not null)
        PersistableBusinessObject businessObject = newMaintainable.getBusinessObject();
        if (businessObject == null) {
            GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject.");
            throw new ValidationException("Maintainable's component business object is null.");
        }
        
        // run required check from maintenance data dictionary
        maintDocDictionaryService.validateMaintenanceRequiredFields(document);
        
        //check for duplicate entries in collections if necessary
        maintDocDictionaryService.validateMaintainableCollectionsForDuplicateEntries(document);

        // run the DD DictionaryValidation (non-recursive)
        dictionaryValidationService.validateBusinessObject(businessObject,false);

        // do default (ie, mandatory) existence checks
        dictionaryValidationService.validateDefaultExistenceChecks(businessObject);

        // do apc checks
        dictionaryValidationService.validateApcRules(businessObject);
        

        // explicitly remove the errorPath we've added
        GlobalVariables.getErrorMap().removeFromErrorPath("document.newMaintainableObject");

        LOG.debug("MaintenanceDocument validation ending");
        return true;
    }

}
