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
