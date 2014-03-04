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
package org.kuali.kra.award.rule;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.InvInstructionsIndicatorConstants;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.award.rule.event.AwardCommentsRuleEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.Collection;


/**
 * This class implements rules for checking Award comments entered in text boxes.
 */
public class AwardCommentsRuleImpl extends KcTransactionalDocumentRuleBase implements AwardCommentsRule {
    private static final Integer MAX_COMMENT_LENGTH = 5000;
    private static String[] VALID_BASIS_METHOD_PAYMENT_FINDER_FIELDS = new String[] {"basisOfPaymentCode","methodOfPaymentCode"}; 
   
    public boolean processAwardCommentsBusinessRules(AwardCommentsRuleEvent awardCommentsRuleEvent) {
        boolean valid = true;

        // Out of the comment types defined in Constants, these are the ones we actually use
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, "awardPreAwardSponsorAuthorizationComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, "awardPreAwardInstitutionalAuthorizationComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.COST_SHARE_COMMENT_TYPE_CODE, "awardCostShareComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.FANDA_RATE_COMMENT_TYPE_CODE, "awardFandaRateComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, "awardBenefitsRateComment.comments");
        valid &= checkAwardComment(awardCommentsRuleEvent, Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE, "awardPaymentAndInvoiceRequirementsComments.comments");
        valid &= checkPaymentAndInvoiceCommentRule( awardCommentsRuleEvent, "awardPaymentAndInvoiceRequirementsComments.comments" );
        
        return valid;
    }

    /**
     * This method looks up the comment for a given type code in the AwardDocument and validates it.
     * @param awardCommentsRuleEvent
     * @param commentTypeCode
     * @param errorKey
     * @return
     */
    private boolean checkAwardComment(AwardCommentsRuleEvent awardCommentsRuleEvent, String commentTypeCode, String errorKey) {
        boolean valid = true;
        
        AwardDocument awardDocument = (AwardDocument)awardCommentsRuleEvent.getDocument();
        Award award = awardDocument.getAward();
        
        AwardComment awardComment = award.findCommentOfSpecifiedType(commentTypeCode);
        if (awardComment != null) {
            String commentString = awardComment.getComments();
            if (!isAwardCommentValid(commentString)) {
                String fullErrorPath = awardCommentsRuleEvent.getErrorPathPrefix() + "." + errorKey;
                reportError(fullErrorPath, KeyConstants.ERROR_MAXLENGTH, "Comment", MAX_COMMENT_LENGTH.toString());
                valid = false;
            }
        }
        
        return valid;
    }

    
    @SuppressWarnings("unchecked")
    private boolean checkPaymentAndInvoiceCommentRule( AwardCommentsRuleEvent awardCommentsRuleEvent, String errorKey ) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument)awardCommentsRuleEvent.getDocument();
        Award award = awardDocument.getAward();
        AwardComment awardComment = award.findCommentOfSpecifiedType(Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE);
        //lookup the ValidBasisMethodPayment object associated with the award.  If none is found for the basis and method we do not 
        //we do not do the check for the correct value in the invoice comment here as an error will be thrown in another business
        //rule regarding that case.
        Collection<ValidBasisMethodPayment> results = getBusinessObjectService().findMatching( ValidBasisMethodPayment.class, CollectionUtils.zipMap(VALID_BASIS_METHOD_PAYMENT_FINDER_FIELDS, new Object[]{award.getBasisOfPaymentCode(), award.getMethodOfPaymentCode()}));
        if( results.size() == 1 ) {
            ValidBasisMethodPayment vbpay = results.iterator().next();
            InvInstructionsIndicatorConstants indicator = vbpay.getInvInstructionsIndicatorConstant(); 
            if( indicator != null ) {
                switch (indicator) {
                    case Blank:     valid = awardComment==null || StringUtils.isEmpty(awardComment.getComments());
                                    if( !valid ) {
                                        String fullErrorPath = awardCommentsRuleEvent.getErrorPathPrefix() + "." + errorKey;
                                        reportError( fullErrorPath, KeyConstants.ERROR_PAYMENT_AND_INVOICE_COMMENT_NOT_ALLOWED );
                                    }
                                    break;
                    case Mandatory: valid = awardComment!=null && !StringUtils.isEmpty(awardComment.getComments());
                                    if( !valid ) {
                                        String fullErrorPath = awardCommentsRuleEvent.getErrorPathPrefix() + "." + errorKey;
                                        reportError( fullErrorPath, KeyConstants.ERROR_PAYMENT_AND_INVOICE_COMMENT_REQUIRED );
                                    }
                                    break;
                }
            }
        } else if ( results.size() > 1 ) {
            throw new IllegalStateException( String.format( "Found more than one ValidBasisMethodPayment records for basisOfPayment=%s and methodOfPayment=%s", award.getBasisOfPaymentCode(),award.getMethodOfPaymentCode() ));
        } 
        return valid;
    }
    
    private boolean isAwardCommentValid(String comment) {
        return comment==null || comment.length()<=MAX_COMMENT_LENGTH;
    }
    
  
    
}
