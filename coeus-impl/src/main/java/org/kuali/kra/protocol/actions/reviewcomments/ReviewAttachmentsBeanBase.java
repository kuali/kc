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
package org.kuali.kra.protocol.actions.reviewcomments;

import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ReviewAttachmentsBeanBase<PRA extends ProtocolReviewAttachmentBase> implements Serializable {



    private static final long serialVersionUID = -376105485699731967L;
    
    private String errorPropertyKey;
    private PRA newReviewAttachment;
    private List<PRA> reviewAttachments;
    private List<PRA> deletedReviewAttachments;
    // flag to hide reviewer name for this bean.
    private boolean hideReviewerName;

    public ReviewAttachmentsBeanBase(String errorPropertyKey) {
        this.errorPropertyKey = errorPropertyKey + ".reviewAttachmentsBean";        
        this.newReviewAttachment = getNewProtocolReviewAttachmentInstanceHook();
        this.reviewAttachments = new ArrayList<PRA>();
        this.deletedReviewAttachments = new ArrayList<PRA>();
    }

    protected abstract PRA getNewProtocolReviewAttachmentInstanceHook();
    
    public String getErrorPropertyName() {
        return errorPropertyKey;
    }
    

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }

    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }
    
    public PRA getNewReviewAttachment() {
        return newReviewAttachment;
    }

    public void setNewReviewAttachment(PRA newReviewAttachment) {
        this.newReviewAttachment = newReviewAttachment;
    }

    public List<PRA> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<PRA> reviewAttachments) {
        this.reviewAttachments = reviewAttachments;
    }

    public List<PRA> getDeletedReviewAttachments() {
        return deletedReviewAttachments;
    }

    public void setDeletedReviewAttachments(List<PRA> deletedReviewAttachments) {
        this.deletedReviewAttachments = deletedReviewAttachments;
    }
}
