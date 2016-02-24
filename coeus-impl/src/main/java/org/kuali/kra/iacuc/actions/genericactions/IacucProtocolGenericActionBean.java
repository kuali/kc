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
package org.kuali.kra.iacuc.actions.genericactions;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewAttachmentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsBean;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;

import java.sql.Date;

/**
 * This class is really just a "form" for generic actions.
 */
public class IacucProtocolGenericActionBean extends IacucProtocolActionBean implements ProtocolGenericActionBean {

    private static final long serialVersionUID = 1098390205989217539L;
    
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    
    private String errorPropertyKey;
    
    private ReviewCommentsBeanBase reviewCommentsBean;
    private ReviewAttachmentsBeanBase reviewAttachmentsBean;
    
    /**
     * Constructs a ProtocolGenericActionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolGenericActionBean(IacucActionHelper actionHelper, String errorPropertyKey) {
        super(actionHelper);
        
        this.errorPropertyKey = errorPropertyKey;
        reviewCommentsBean = new IacucReviewCommentsBean(errorPropertyKey);       
        reviewAttachmentsBean = new IacucReviewAttachmentsBean(errorPropertyKey);
    }

    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Date getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
    public String getErrorPropertyKey() {
        return errorPropertyKey;
    }

    public ReviewCommentsBeanBase getReviewCommentsBean() {
        return reviewCommentsBean;
    }

    public ReviewAttachmentsBeanBase getReviewAttachmentsBean() {
        return reviewAttachmentsBean;
    }

    public void setReviewAttachmentsBean(ReviewAttachmentsBeanBase reviewAttachmentsBean) {
        this.reviewAttachmentsBean = reviewAttachmentsBean;
    }
    
}
