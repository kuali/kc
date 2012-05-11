/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.rules.MaintenanceDocumentRule;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.GlobalVariables;

public class SubAwardInvoiceMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {
    
    private DictionaryValidationService dictionaryValidationService;
    
    public SubAwardInvoiceMaintenanceDocumentRule() {
        dictionaryValidationService = KraServiceLocator.getService(DictionaryValidationService.class);
    }
    
    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean valid = super.isDocumentValidForSave(document);

        SubAwardAmountReleased invoice = (SubAwardAmountReleased) document.getNoteTarget();
        GlobalVariables.getMessageMap().addToErrorPath("document.newMaintainableObject");
        valid &= dictionaryValidationService.isBusinessObjectValid(invoice);
        GlobalVariables.getMessageMap().clearErrorPath();
        return valid;
    }

}
