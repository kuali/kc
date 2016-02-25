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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.GlobalVariables;

public class SubAwardInvoiceMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    private DictionaryValidationService dictionaryValidationService;
    private ErrorReporter errorReporter;
    private SubAwardService subAwardService;
    
    public SubAwardInvoiceMaintenanceDocumentRule() {
        dictionaryValidationService = KcServiceLocator.getService(DictionaryValidationService.class);
        errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        subAwardService = KcServiceLocator.getService(SubAwardService.class);
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
            subAwardService.calculateAmountInfo(subAward);
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
