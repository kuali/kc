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
package org.kuali.kra.award.bo;

import java.util.Collection;

import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class Creates comment type based on the create comment method called.
 */
public class AwardCommentFactory {
    
    private static final boolean INCLUDE_IN_CHECKLIST = true;
    private static final boolean EXCLUDE_FROM_CHECKLIST = false;
    
    /**
     * This method creates a Cost Share Comment
     * @param award
     * @return
     */
    public AwardComment createCostShareComment(Award award) {
        return createAwardComment(award, Constants.COST_SHARE_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates F and A Rate Comment
     * @param award
     * @return
     */
    public AwardComment createFandaRateComment(Award award) {
        return createAwardComment(award, Constants.FANDA_RATE_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates F and A Rate Comment
     * @param award
     * @return
     */
    public AwardComment createPaymentAndInvoiceComment(Award award) {
        return createAwardComment(award, Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Benefits Rate Comment
     * @param award
     * @return
     */
    public AwardComment createBenefitsRateComment(Award award) {
        return createAwardComment(award, Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates PreAwardSponsorAuthoriztion comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardSponsorAuthorizationComment(Award award) {
        return createAwardComment(award, Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a PreAwardInstitutionalAuthorization Comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardInstitutionalAuthorizationComment(Award award) {
        return createAwardComment(award, Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }

    /**
     * This method returns a new AwardComment
     * @param award
     * @param commentTypeCode
     * @param checklistPrintFlag
     * @return
     */
    public  AwardComment createAwardComment(Award award, String commentTypeCode, boolean checklistPrintFlag) {
                AwardComment comment = new AwardComment();
                CommentType commentType = findCommentType(commentTypeCode);
                comment.setCommentType(commentType);
                comment.setCommentTypeCode(commentType.getCommentTypeCode());
                comment.setChecklistPrintFlag(checklistPrintFlag);
                comment.setComments("");
                return comment;
}

    /**
     * This method returns corresponding Comment Type
     * @param commentTypeCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public CommentType findCommentType(String commentTypeCode){
        BusinessObjectService costShareCommentType = getKraBusinessObjectService();
        Collection<CommentType> costShareCommentTypes = 
            (Collection<CommentType>) costShareCommentType.findAll(CommentType.class);
        CommentType returnVal = null;
        for(CommentType commentType : costShareCommentTypes){
            if (commentType.getCommentTypeCode().equals(commentTypeCode)){
                returnVal = commentType;
                break;
            }  
        }
        return returnVal;
    }
    
    /**
     * This method returns a business object service
     * @return
     */
    protected BusinessObjectService getKraBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }
    
    
}
