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

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.rules.MaintenanceDocumentRule;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.GlobalVariables;

public class SubAwardInvoiceMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
    private DictionaryValidationService dictionaryValidationService;
    private ErrorReporter errorReporter;
    private SubAwardService subAwardService;
    
    public SubAwardInvoiceMaintenanceDocumentRule() {
        dictionaryValidationService = KraServiceLocator.getService(DictionaryValidationService.class);
        errorReporter = new ErrorReporter();
        subAwardService = KraServiceLocator.getService(SubAwardService.class);
    }
    
    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean valid = super.isDocumentValidForSave(document);

        SubAwardAmountReleased invoice = (SubAwardAmountReleased) document.getNoteTarget();
        GlobalVariables.getMessageMap().addToErrorPath("document.newMaintainableObject");
        valid &= dictionaryValidationService.isBusinessObjectValid(invoice);
        if (invoice.getStartDate() != null && invoice.getEndDate() != null 
                && invoice.getEndDate().before(invoice.getStartDate())) {
            valid = false;
            errorReporter.reportError("startDate", KeyConstants.SUBAWARD_ERROR_END_DATE_GREATER_THAN_START);
        }
        if (invoice.getAmountReleased() != null) {
            if (invoice.getAmountReleased().isZero()) {
                valid = false;
                errorReporter.reportError("amountReleased", KeyConstants.ERROR_SUBAWARD_INVOICE_AMOUNT_RELEASED_ZERO);
            }
            SubAward subAward = invoice.getSubAward();
            if (subAward.getSubAwardAmountReleasedList().contains(invoice)) {
                subAward.getSubAwardAmountReleasedList().remove(invoice);
            } 
            subAward.getSubAwardAmountReleasedList().add(invoice);
            subAwardService.getAmountInfo(subAward);          
            if (invoice.getSubAward().getTotalAvailableAmount().isNegative()) {
                valid = false;
                errorReporter.reportError("amountReleased", KeyConstants.ERROR_SUBAWARD_AMOUNT_RELEASED_GREATER_OBLIGATED_AMOUNT);
            }
                        
        }
                
        GlobalVariables.getMessageMap().clearErrorPath();
        return valid;
    }
    
    /**
     * The Subaward Invoice maint doc saves the BO on every save so the primary key check will always fail so we disable it here by
     * always returning success.
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#primaryKeyCheck(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean primaryKeyCheck(MaintenanceDocument document) {
        return true;
    }
}
