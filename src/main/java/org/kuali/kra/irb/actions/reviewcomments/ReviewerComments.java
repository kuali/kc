/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.reviewcomments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class encapsulates a collection of meeting minutes as reviewer comments.
 */
public class ReviewerComments implements Serializable {
    
    private static final long serialVersionUID = 1234567890;
    
    private CommitteeScheduleMinute newComment = new CommitteeScheduleMinute();
    
    private List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
    private List<CommitteeScheduleMinute> commentsToDelete = new ArrayList<CommitteeScheduleMinute>();
    private Long protocolId;
    
    public List<CommitteeScheduleMinute> getComments() {
        return comments;
    }
    
    public List<CommitteeScheduleMinute> getCommentsToDelete() {
        return commentsToDelete;
    }
    
    /**
     *     
     * This method should be called after the collection of comments to delete have been removed from the DB.
     */
    public void resetComentsToDelete() {
        commentsToDelete = new ArrayList<CommitteeScheduleMinute>();
    }

    public void setComments(List<CommitteeScheduleMinute> comments) {
        this.comments = comments;
    }

    public CommitteeScheduleMinute getNewComment() {
        return newComment;
    }
    
    /**
     * This method adds a new comment to the collection of comments.
     */
    public void addNewComment(Protocol protocol) {
        newComment.setProtocol(protocol);
        newComment.setProtocolIdFk(protocol.getProtocolId());
        newComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newComment.setCreateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        newComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalId());
        newComment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalId());
        comments.add(newComment);
        newComment = new CommitteeScheduleMinute();
    }
    
    /**
     * This method adds a new comment to the collection of comments.
     */
    public void addNewComment(Protocol protocol, ProtocolOnlineReview protocolOnlineReview) {
        newComment.setProtocolOnlineReviewIdFk(protocolOnlineReview.getProtocolOnlineReviewId());
        newComment.setProtocolReviewer(protocolOnlineReview.getProtocolReviewer());
        newComment.setProtocolReviewerIdFk(protocolOnlineReview.getProtocolReviewerId());
        addNewComment( protocol );
    }
    
    
    /**
     * 
     * This method deletes a comment from the collection of comments.
     * @param index the integer index of position in the collection of comments to delete/
     */
    public void deleteComment(int index) {
        if (index >= 0 && index < comments.size()) {
            commentsToDelete.add(comments.get(index));
            comments.remove(index);
        }
    }
    
    /**
     * This method deletes all comments.
     * 
     */
    
    public void deleteAllComments() {
        for (int i = 0; i<comments.size();i++) {
            deleteComment(i);
        }
    }
    
    /**
     * 
     * This method changes the position of one element in the collection by one value.
     * @param index the integer value of the position in the collection to act upon.
     * @todo this isn't perfect yet, need to skip the hideen ones, and then go over the next hidden hidden one, if the first one was hidden.
     */
    public void moveUp(int index, int startIndex) {
        if (index > 0) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index - 1, minute);
            int indexJustAbove = index - 2;
            if (indexJustAbove > 0) {
                CommitteeScheduleMinute minuteJustAbove = comments.get(indexJustAbove);
                
                /**
                 * reasons to move an additional line:
                 * 1) the line just below is hidden
                 * 2) the first line moved over was hidden, the previous line was hidden, the next line is shown
                 */
                CommitteeScheduleMinute minuteFirst;
                try{
                    minuteFirst = comments.get(startIndex);
                } catch (ArrayIndexOutOfBoundsException ai) {
                    //I am trying to move the first one
                    minuteFirst = minute;
                }
                CommitteeScheduleMinute minutePrevious;
                try{
                    minutePrevious = comments.get(index + 1);
                } catch (IndexOutOfBoundsException i) {
                    //I am trying to move the first one
                    minutePrevious = minute;
                }
                if (!this.getProtocolId().equals(minuteJustAbove.getProtocolId())
                        || (!this.getProtocolId().equals(minuteFirst.getProtocolId())
                                && !this.getProtocolId().equals(minutePrevious.getProtocolId())
                                && this.getProtocolId().equals(minuteJustAbove.getProtocolId()))) {
                  //the comment just above is "hidden", so move this comment up another space
                    moveUp(index - 1, startIndex);
                }
                
                /*
                if (!this.getProtocolId().equals(minuteJustAbove.getProtocolId())) {
                    //the comment just above is "hidden", so move this comment up another space
                    moveUp(index - 1, startIndex);
                }*/
            }
        }
    }
    
    /**
     * 
     * This method changes the position of one element in the collection by one value.
     * @param index the integer value of the position in the collection to act upon.
     * @todo this isn't perfect yet, need to skip the hideen ones, and then go over the next hidden hidden one, if the first one was hidden.
     */
    public void moveDown(int index, int startIndex) {
        if (index < comments.size() - 1) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index + 1, minute);
            int indexJustBelow = index + 2;
            if (indexJustBelow < comments.size() - 1) {
                CommitteeScheduleMinute minuteJustBelow = comments.get(indexJustBelow);
                /**
                 * reasons to move an additional line:
                 * 1) the line just below is hidden
                 * 2) the first line moved over was hidden, the previous line was hidden, the next line is shown
                 */
                CommitteeScheduleMinute minuteFirst;
                try{
                    minuteFirst = comments.get(startIndex);
                } catch (ArrayIndexOutOfBoundsException ai) {
                    //I am trying to move the first one
                    minuteFirst = minute;
                }
                CommitteeScheduleMinute minutePrevious;
                try{
                    minutePrevious = comments.get(index - 1);
                } catch (ArrayIndexOutOfBoundsException ai) {
                    //I am trying to move the first one
                    minutePrevious = minute;
                }
                if (!this.getProtocolId().equals(minuteJustBelow.getProtocolId())
                        || (!this.getProtocolId().equals(minuteFirst.getProtocolId())
                                && !this.getProtocolId().equals(minutePrevious.getProtocolId())
                                && this.getProtocolId().equals(minuteJustBelow.getProtocolId()))) {
                    //the comment just below is "hidden", so move this comment down another space
                    moveDown(index + 1, startIndex);
                }
            }
        }
    }
    
    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }
    
    public Long getProtocolId() {
        return this.protocolId;
    }
}
