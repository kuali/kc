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
