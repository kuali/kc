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

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ReviewCommentsBeanBase  implements Serializable {



    private static final long serialVersionUID = 994429171132365761L;

    private String errorPropertyKey;
    
    private CommitteeScheduleMinuteBase newReviewComment;
    private List<CommitteeScheduleMinuteBase> reviewComments;
    private List<CommitteeScheduleMinuteBase> deletedReviewComments;
    // flag to hide reviewer name for this bean.
    private boolean hideReviewerName;

    public ReviewCommentsBeanBase(String errorPropertyKey) {
        this.errorPropertyKey = errorPropertyKey + ".reviewCommentsBean";
        
        this.newReviewComment = getNewCommitteeScheduleMinuteInstanceHook();
        this.newReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        this.reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
        this.deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
    }

    protected abstract CommitteeScheduleMinuteBase getNewCommitteeScheduleMinuteInstanceHook();

    public String getErrorPropertyName() {
        return errorPropertyKey;
    }
    
    public CommitteeScheduleMinuteBase getNewReviewComment() {
        return newReviewComment;
    }
    
    public void setNewReviewComment(CommitteeScheduleMinuteBase newReviewComment) {
        this.newReviewComment = newReviewComment;
    }
    
    public List<CommitteeScheduleMinuteBase> getReviewComments() {
        return reviewComments;
    }
    
    public void setReviewComments(List<CommitteeScheduleMinuteBase> reviewComments) {
        this.reviewComments = reviewComments;         
        this.setHideReviewerName(getReviewCommentsService().setHideReviewerName(this.reviewComments));
    }
    
    public List<CommitteeScheduleMinuteBase> getDeletedReviewComments() {
        return deletedReviewComments;
    }
    
    public void setDeletedReviewComments(List<CommitteeScheduleMinuteBase> deletedReviewComments) {
        this.deletedReviewComments = deletedReviewComments;
    }

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }

    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }
    
    private ReviewCommentsService getReviewCommentsService() {
        return KcServiceLocator.getService(getReviewCommentsServiceClassHook());
    }

    protected abstract Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook();


}
