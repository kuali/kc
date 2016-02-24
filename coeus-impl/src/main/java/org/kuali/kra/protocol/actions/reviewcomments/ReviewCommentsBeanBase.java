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
