/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.person.signature;

import org.kuali.coeus.common.framework.person.signature.PersonSignature;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidPersonSignatureMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    private static final String PERSON_SIGNATURE_FILE_INVALID_ERROR_KEY = "error.invalid.personSignature.invalid.fileName";
    private static final String PERSON_SIGNATURE_ID_INVALID_ERROR_KEY = "error.invalid.personSignature.invalid.personSignatureId";

    @Override

    @SuppressWarnings("deprecation")
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }

    @SuppressWarnings("deprecation")
    public boolean isPersonSignatureValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        result &= isSignatureValid(document);
        result &= isNotDuplicateSignatureId(document);
        return result;
    }
    
    @SuppressWarnings("deprecation")
    private boolean isSignatureValid(MaintenanceDocument document) {
        boolean isSignatureValid = true;
        PersonSignature personSignature = (PersonSignature)document.getNewMaintainableObject().getDataObject();
        
        if(personSignature.getTemplateFile() == null && personSignature.getFileName() == null) {
            ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
            errorReporter.reportError("document.newMaintainableObject.templateFile", 
                    PERSON_SIGNATURE_FILE_INVALID_ERROR_KEY,
                    new String[]{});
            isSignatureValid = false;
        }
        return isSignatureValid;
    }
    
    @SuppressWarnings("deprecation")
    private boolean isNotDuplicateSignatureId(MaintenanceDocument document) {
        boolean isValid = true;
        PersonSignature personSignature = (PersonSignature) document.getNewMaintainableObject().getDataObject();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personSignature.getPersonId());
        if(document.isNew()) {
            isValid = getBoService().countMatching(PersonSignature.class, fieldValues) == 0;
        }else if (document.isEdit()) {
            isValid = isRecordUpdateValid(fieldValues, personSignature);
        }
        if(!isValid) {
            ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
            errorReporter.reportError("document.newMaintainableObject.personId", 
                    PERSON_SIGNATURE_ID_INVALID_ERROR_KEY,
                    new String[]{});
        }
        return isValid;
    }

    @SuppressWarnings("deprecation")
    private boolean isRecordUpdateValid(Map<String, Object> fieldValues, PersonSignature mdocPersonSignature) {
        boolean isRecordUpdateValid = false;
        List<PersonSignature> authorizedSignatures = (List<PersonSignature>)getBoService().findMatching(PersonSignature.class, fieldValues);
        if(authorizedSignatures.isEmpty()) {
            isRecordUpdateValid = true;
        }else {
            PersonSignature dbPersonSignature = authorizedSignatures.get(0);
            Long dbPersonSignatureId = dbPersonSignature.getPersonSignatureId();
            if(mdocPersonSignature.getPersonSignatureId().equals(dbPersonSignatureId)) {
                isRecordUpdateValid = true;
            }
        }
        return isRecordUpdateValid;
    }
    
}
