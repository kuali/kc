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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ValidAwardBasisPayment;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class AwardPaymentAndInvoicesAuditRule implements DocumentAuditRule {
   
    private static final String DOT = ".";
    private static final String PAYMENTS_AND_INVOICES_AUDIT_ERRORS = "paymentAndInvoicesAuditErrors";
    private static final String PAYMENT_METHOD = "Payment Method";
    private static final String PAYMENT_BASIS = "Payment Basis";
   
    private static final String BASIS_OF_PAYMENT_AUDIT_KEY = "awardBasisOfPaymentCode";
    private static final String METHOD_OF_PAYMENT_AUDIT_KEY = "awardMethofOfPaymentCode";
    
    private static final String PAYMENTS_INVOICES_URL=Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE+"."+Constants.PAYMENT_AND_INVOICES_PANEL_ANCHOR;
    
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        Award award = awardDocument.getAward();
          if(award.getMethodOfPaymentCode() == null){
                valid&=false;
                addErrorToAuditErrors(auditErrors, METHOD_OF_PAYMENT_AUDIT_KEY, PAYMENT_METHOD);
            }
          if(award.getBasisOfPaymentCode() == null) {
              valid&=false;
              addErrorToAuditErrors(auditErrors, BASIS_OF_PAYMENT_AUDIT_KEY, PAYMENT_BASIS);
          }
          valid &= checkAwardBasisOfPayment(awardDocument, auditErrors);
          valid &= checkAwardBasisAndMethodOfPayment( awardDocument, auditErrors );
        reportAndCreateAuditCluster(auditErrors);
        return valid;
    }
    
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(List<AuditError> auditErrors, String relAuditErrorKey, String description) {
        String[] params = new String[5];
        params[0] = description;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
        sb.append(DOT);
        sb.append(Constants.PAYMENT_AND_INVOICES_PANEL_ANCHOR);
        if( relAuditErrorKey != null ) {
            sb.append(DOT);
            sb.append( relAuditErrorKey );
        }
            
        auditErrors.add(new AuditError(Constants.PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY, 
                                        KeyConstants.ERROR_REQUIRED, 
                                        sb.toString(),
                                        params));   
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster( List<AuditError> auditErrors ) {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(PAYMENTS_AND_INVOICES_AUDIT_ERRORS, new AuditCluster(Constants.PAYMENT_AND_INVOICES_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }

    protected boolean checkAwardBasisOfPayment( AwardDocument document, List<AuditError> errors ) {
        boolean valid = false;
        if( document.getAward().getAwardTypeCode() != null && document.getAward().getBasisOfPaymentCode()!=null) {
        
           List<ValidAwardBasisPayment> basisPayments = new ArrayList<ValidAwardBasisPayment>(getAwardPaymentAndInvoicesService().getValidAwardBasisPaymentsByAwardTypeCode(document.getAward().getAwardTypeCode()));
           for( ValidAwardBasisPayment basisPayment : basisPayments )
               if( StringUtils.equals( basisPayment.getBasisOfPaymentCode(), document.getAward().getBasisOfPaymentCode() ) ) valid = true;
               
           document.getAward().refreshReferenceObject("awardType");
           if( !valid ) //todo lookup basis of payment description to use instead of code.
               errors.add(new AuditError(Constants.PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY,
                       KeyConstants.ERROR_AWARD_INVALID_BASIS_OF_PAYMENT_FOR_AWARD_TYPE,
                       PAYMENTS_INVOICES_URL,
                       new String[] { document.getAward().getBasisOfPaymentCode(), document.getAward().getAwardType().getDescription()}));   
        }    
        else {
            valid = true;
        }
       return valid;
    }
    
    protected boolean checkAwardBasisAndMethodOfPayment( AwardDocument document, List<AuditError> errors ) {
        boolean valid = false;
        if( document.getAward().getAwardTypeCode() != null && document.getAward().getBasisOfPaymentCode()!=null) {
        
           List<ValidBasisMethodPayment> basisMethodPayments = new ArrayList<ValidBasisMethodPayment>(getAwardPaymentAndInvoicesService().getValidBasisMethodPaymentByBasisCode(document.getAward().getBasisOfPaymentCode()));
           for( ValidBasisMethodPayment basisMethodPayment : basisMethodPayments )
               if( StringUtils.equals( basisMethodPayment.getMethodOfPaymentCode(), document.getAward().getMethodOfPaymentCode() ) ) valid = true;
               
           
           if( !valid ) //todo lookup basis of payment description to use instead of code.
               errors.add(new AuditError(Constants.PAYMENT_AND_INVOICES_AUDIT_RULES_ERROR_KEY,
                       KeyConstants.ERROR_AWARD_INVALID_BASIS_AND_METHOD_OF_PAYMENT,
                       PAYMENTS_INVOICES_URL,
                       new String[] { }));   
        }    
        else {
            valid = true;
        }
       return valid;
    }
    
    
    
    protected AwardPaymentAndInvoicesService getAwardPaymentAndInvoicesService() {
        return KraServiceLocator.getService(AwardPaymentAndInvoicesService.class);
    }
   

}



