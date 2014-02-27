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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.rice.kns.rule.PromptBeforeValidation;
import org.kuali.rice.kns.rules.PromptBeforeValidationBase;
import org.kuali.rice.krad.document.Document;

import java.util.Calendar;

/**
 * If a newly created Permanent proposal log has the same PI as one or more Temporary logs,
 * check if the user wants to merge one of the Temporary logs to the Permanent log.
 * This prompt will occur upon submission of the Permanent log.
 */
public class SubAwardInvoicePromptBeforeValidation extends PromptBeforeValidationBase implements PromptBeforeValidation {
    
    protected static final String EFFECTIVE_DATE_PROMPT_ID = "effectiveDate";
    protected static final String EFFECTIVE_DATE_PROMPT = "Invoice Effective Date is more than 30 days in the past or more than 30 days in the future. Do you wish to continue?";
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean doPrompts(Document document) {
        SubAwardAmountReleased invoice = (SubAwardAmountReleased) document.getNoteTarget();
        if (invoice.getEffectiveDate() != null) {
            Calendar beforeEffective = Calendar.getInstance();
            beforeEffective.add(Calendar.DAY_OF_MONTH, -30);
            Calendar afterEffective = Calendar.getInstance();
            afterEffective.add(Calendar.DAY_OF_MONTH, 30);
            Calendar effectiveDate = Calendar.getInstance();
            effectiveDate.setTime(invoice.getEffectiveDate());
            
            if (effectiveDate.before(beforeEffective) || effectiveDate.after(afterEffective)) {
                if (!askOrAnalyzeYesNoQuestion(EFFECTIVE_DATE_PROMPT_ID, EFFECTIVE_DATE_PROMPT)) {
                    abortRulesCheck();
                    return false;
                } else {
                    return true;
                }
            } 
        }
        return true;
    }
}
