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
