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
package org.kuali.kra.irb.onlinereview;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.meeting.CommitteeScheduleMinute;

public class OnlineReviewActionHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String newComment;
    private boolean finalFlag;
    private boolean privateCommentFlag;
    private String minuteEntry;
    
    /**
     * Gets the newComment attribute. 
     * @return Returns the newComment.
     */
    public String getNewComment() {
        return newComment;
    }
    /**
     * Sets the newComment attribute value.
     * @param newComment The newComment to set.
     */
    public void setNewComment(String newComment) {
        this.newComment = newComment;
    }
    /**
     * Gets the finalFlag attribute. 
     * @return Returns the finalFlag.
     */
    public boolean isFinalFlag() {
        return finalFlag;
    }
    /**
     * Sets the finalFlag attribute value.
     * @param finalFlag The finalFlag to set.
     */
    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }
    /**
     * Gets the privateCommentFlag attribute. 
     * @return Returns the privateCommentFlag.
     */
    public boolean isPrivateCommentFlag() {
        return privateCommentFlag;
    }
    /**
     * Sets the privateCommentFlag attribute value.
     * @param privateCommentFlag The privateCommentFlag to set.
     */
    public void setPrivateCommentFlag(boolean privateCommentFlag) {
        this.privateCommentFlag = privateCommentFlag;
    }
    /**
     * Gets the minuteEntry attribute. 
     * @return Returns the minuteEntry.
     */
    public String getMinuteEntry() {
        return minuteEntry;
    }
    /**
     * Sets the minuteEntry attribute value.
     * @param minuteEntry The minuteEntry to set.
     */
    public void setMinuteEntry(String minuteEntry) {
        this.minuteEntry = minuteEntry;
    }
    
    
}
