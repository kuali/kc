/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.meeting.CommitteeScheduleMinute;

public class ReviewComments {
    
    private CommitteeScheduleMinute newComment = new CommitteeScheduleMinute();
    
    private List<CommitteeScheduleMinute> comments = new ArrayList<CommitteeScheduleMinute>();
    
    public List<CommitteeScheduleMinute> getComments() {
        return comments;
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
    
    public void addNewComment() {
        comments.add(newComment);
        newComment = new CommitteeScheduleMinute();
    }

    public void deleteComment(int index) {
        if (index >= 0 && index < comments.size()) {
            comments.remove(index);
        }
    }

    public void moveUp(int index) {
        if (index > 0) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index - 1, minute);
        }
    }

    public void moveDown(int index) {
        if (index < comments.size() - 1) {
            CommitteeScheduleMinute minute = comments.remove(index);
            comments.add(index + 1, minute);
        }
    }
}
