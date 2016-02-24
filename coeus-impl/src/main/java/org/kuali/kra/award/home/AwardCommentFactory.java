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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;

/**
 * This class Creates comment type based on the create comment method called.
 */
public class AwardCommentFactory {
    
    /**
     * This method creates a Cost Share Comment
     * @param award
     * @return
     */
    public AwardComment createCostShareComment() {
        return createAwardComment(Constants.COST_SHARE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates F and A Rate Comment
     * @param award
     * @return
     */
    public AwardComment createFandaRateComment() {
        return createAwardComment(Constants.FANDA_RATE_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_INCLUDE_IN_CHECKLIST);
    }
    
    /**
     * This method creates PreAwardSponsorAuthoriztion comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardSponsorAuthorizationComment() {
        return createAwardComment(Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a PreAwardInstitutionalAuthorization Comment
     * @param award
     * @return
     */
    public AwardComment createPreAwardInstitutionalAuthorizationComment() {
        return createAwardComment(Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates a Special Review Comment
     * @param award
     * @return
     */
    public AwardComment createSpecialReviewComment() {
        return createAwardComment(Constants.SPECIAL_REVIEW_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST);
    }
    
    /**
     * This method creates Proposal Comment
     * @param award
     * @return
     */
    public AwardComment createProposalComment() {
        return createAwardComment(Constants.PROPOSAL_COMMENT_TYPE_CODE, Constants.AWARD_COMMENT_EXCLUDE_FROM_CHECKLIST);
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
        return (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
    }
    
    
}
