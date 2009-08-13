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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class AwardPaymentAndInvoicesAuditRule implements DocumentAuditRule {
   
    private static final int FIVE = 5;
    private static final int ZERO = 0;
    private static final String DOT = ".";
    private static final String PAYMENT_AND_INVOICES_AUDIT_ERRORS = "paymentAndInvoicesAuditErrors";
    private static final String PAYMENT_METHOD = "Payment Method";
    private static final String PAYMENT_BASIS = "Payment Basis";
    
    
    private List<AuditError> auditErrors;
    
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        auditErrors = new ArrayList<AuditError>();
        Award award = awardDocument.getAward();
          if(award.getMethodOfPaymentCode() == null){
                valid&=false;
                addErrorToAuditErrors(PAYMENT_METHOD);
            }
          if(award.getBasisOfPaymentCode() == null) {
              valid&=false;
              addErrorToAuditErrors(PAYMENT_BASIS);
          }
        reportAndCreateAuditCluster();
        return valid;
    }
    
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(String description) {
        String[] params = new String[FIVE];
        params[ZERO] = description;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
        sb.append(DOT);
        sb.append(Constants.PAYMENT_AND_INVOICES_PANEL_ANCHOR);
        auditErrors.add(new AuditError(Constants.PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY, 
                                        KeyConstants.ERROR_REQUIRED, 
                                        sb.toString(),
                                        params));   
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > ZERO) {
            GlobalVariables.getAuditErrorMap().put(PAYMENT_AND_INVOICES_AUDIT_ERRORS, new AuditCluster(Constants.PAYMENT_AND_INVOICES_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }

}
