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

import org.kuali.kra.meeting.CommitteeScheduleMinute;

/**
 * 
 * This class encapsulates a collection of meeting minutes as reviewer comments.
 */
public class ReviewComments implements Serializable {
    
    private static final long serialVersionUID = 1234567890;
    
    private CommitteeScheduleMinute newComment = new CommitteeScheduleMinute();
    
    private List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
    private List<CommitteeScheduleMinute> commentsToDelete = new ArrayList<CommitteeScheduleMinute>();
    
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

    public void setNewComment(CommitteeScheduleMinute newComment) {
        this.newComment = newComment;
    }
    
    /**
     * 
     * This method adds a new comment to the collection of comments.
     */
    public void addNewComment() {
        comments.add(newComment);
        newComment = new CommitteeScheduleMinute();
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
     * 
     * This method changes the position of one element in the collection by one value.
     * @param index the integer value of the position in the collection to act upon.
     */
    public void moveUp(int index) {
        if (index > 0) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index - 1, minute);
        }
    }
    
    /**
     * 
     * This method changes the position of one element in the collection by one value.
     * @param index the integer value of the position in the collection to act upon.
     */
    public void moveDown(int index) {
        if (index < comments.size() - 1) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index + 1, minute);
        }
    }
}
