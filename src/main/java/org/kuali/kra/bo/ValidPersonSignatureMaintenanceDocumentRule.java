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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;

public class ValidPersonSignatureMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    private static final String PERSON_SIGNATURE_FILE_INVALID_ERROR_KEY = "error.invalid.personSignature.invalid.fileName";

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */

    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }


    /**
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }

    /**
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return isPersonSignatureValidForSave(document);
    }

    public boolean isPersonSignatureValidForSave(MaintenanceDocument document) {
        boolean result = super.isDocumentValidForSave(document);
        result &= isSignatureValid(document);
        return result;
    }
    
    private boolean isSignatureValid(MaintenanceDocument document) {
        boolean isSignatureValid = true;
        PersonSignature personSignature = (PersonSignature)document.getNewMaintainableObject().getDataObject();
        
        if(personSignature.getTemplateFile() == null && personSignature.getFileName() == null) {
            ErrorReporter errorReporter = new ErrorReporter();
            errorReporter.reportError("document.newMaintainableObject.templateFile", 
                    PERSON_SIGNATURE_FILE_INVALID_ERROR_KEY,
                    new String[]{});
            isSignatureValid = false;
        }
        return isSignatureValid;
    }
    
}
