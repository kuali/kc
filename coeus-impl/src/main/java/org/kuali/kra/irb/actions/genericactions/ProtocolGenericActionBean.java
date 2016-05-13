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
package org.kuali.kra.irb.actions.genericactions;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;

import java.sql.Date;

/**
 * This class is really just a "form" for generic actions.
 */
public class ProtocolGenericActionBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean {

    private static final long serialVersionUID = 1098390205989217539L;
    
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    
    private String errorPropertyKey;
    
    private ReviewCommentsBean reviewCommentsBean;
    private ReviewAttachmentsBean reviewAttachmentsBean;

    public ProtocolGenericActionBean() {}

    /**
     * Constructs a ProtocolGenericActionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolGenericActionBean(ActionHelper actionHelper, String errorPropertyKey) {
        super(actionHelper);
        
        this.errorPropertyKey = errorPropertyKey;
        reviewCommentsBean = new ReviewCommentsBean(errorPropertyKey);
        reviewAttachmentsBean = new ReviewAttachmentsBean(errorPropertyKey);
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

    public ReviewCommentsBean getReviewCommentsBean() {
        return reviewCommentsBean;
    }

    public ReviewAttachmentsBean getReviewAttachmentsBean() {
        return reviewAttachmentsBean;
    }

    public void setReviewAttachmentsBean(ReviewAttachmentsBeanBase reviewAttachmentsBean) {
        this.reviewAttachmentsBean = (ReviewAttachmentsBean) reviewAttachmentsBean;
    }
    
}
