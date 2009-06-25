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
package org.kuali.kra.award.home;

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
    public AwardComment createCostShareComment() {
        return createAwardComment(Constants.COST_SHARE_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates F and A Rate Comment
     * @param award
     * @return
     */
    public AwardComment createFandaRateComment() {
        return createAwardComment(Constants.FANDA_RATE_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates F and A Rate Comment
     * @param award
     * @return
     */
    public AwardComment createPaymentAndInvoiceComment() {
        return createAwardComment(Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Benefits Rate Comment
     * @param award
     * @return
     */
    public AwardComment createBenefitsRateComment() {
        return createAwardComment(Constants.BENEFITS_RATES_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates PreAwardSponsorAuthoriztion comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardSponsorAuthorizationComment() {
        return createAwardComment(Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a PreAwardInstitutionalAuthorization Comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardInstitutionalAuthorizationComment() {
        return createAwardComment(Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a General Comment
     * @param award
     * @return
     */
    public AwardComment createGeneralComment() {
        return createAwardComment(Constants.GENERAL_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Fiscal Report Comment
     * @param award
     * @return
     */
    public AwardComment createFiscalReportComment() {
        return createAwardComment(Constants.FISCAL_REPORT_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Intellectual Property Comment
     * @param award
     * @return
     */
    public AwardComment createIntellecutalPropertyComment() {
        return createAwardComment(Constants.INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Procurement Comment
     * @param award
     * @return
     */
    public AwardComment createProcurementComment() {
        return createAwardComment(Constants.PROCUREMENT_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates Property comment
     * @param award
     * @return
     */
    public AwardComment createPropertyComment() {
        return createAwardComment(Constants.PROPERTY_COMMENT_TYPE_CODE, INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates a Special Rate Comment
     * @param award
     * @return
     */
    public AwardComment createSpecialRateComment() {
        return createAwardComment(Constants.SPECIAL_RATE_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a Special Review Comment
     * @param award
     * @return
     */
    public AwardComment createSpecialReviewComment() {
        return createAwardComment(Constants.SPECIAL_REVIEW_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates Proposal Summary Comment
     * @param award
     * @return
     */
    public AwardComment createProposalSummaryComment() {
        return createAwardComment(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates Proposal Comment
     * @param award
     * @return
     */
    public AwardComment createProposalComment() {
        return createAwardComment(Constants.PROPOSAL_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates Proposal IP Review Comment
     * @param award
     * @return
     */
    public AwardComment createProposalIPReviewComment() {
        return createAwardComment(Constants.PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE, EXCLUDE_FROM_CHECKLIST);
    }
    
    

    /**
     * This method returns a new AwardComment
     * @param commentTypeCode
     * @param checklistPrintFlag
     * @return
     */
    public  AwardComment createAwardComment(String commentTypeCode, boolean checklistPrintFlag) {
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
