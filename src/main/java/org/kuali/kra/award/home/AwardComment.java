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
package org.kuali.kra.award.home;

import java.util.Calendar;
import java.util.Date;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.bo.CommentType;

/**
 * This class is business object representation of an Award Comment
 */
public class AwardComment extends AwardAssociate implements Comparable<AwardComment> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3611932717292205490L;

    private Long awardCommentId;

    /*
     * These fields will sync when the containing class syncs.
     */
    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    private String commentTypeCode;

    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    private Boolean checklistPrintFlag;

    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    @AwardSyncableProperty
    private String comments;

    private Long awardId;

    private CommentType commentType;

    private String updateTimestampDateString;

    /**
     * 
     * Constructs a AwardComment.java.
     */
    public AwardComment() {
        super();
    }

    public AwardComment(CommentType commentType, String comments) {
        this();
        setCommentType(commentType);
        setComments(comments);
        setChecklistPrintFlag(Boolean.FALSE);
    }

    /**
     * This method...
     * @return
     */
    public Long getAwardCommentId() {
        return awardCommentId;
    }

    /**
     * This method...
     * @param awardCommentId
     */
    public void setAwardCommentId(Long awardCommentId) {
        this.awardCommentId = awardCommentId;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardCommentId == null) ? 0 : awardCommentId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AwardComment other = (AwardComment) obj;
        if (!awardCommentId.equals(other.awardCommentId)) return false;
        return true;
    }

    public boolean sameText(AwardComment compare) {
        if (this.getComments() == compare.getComments()) {
            return true;
        }
        if (!this.isEntered() && !compare.isEntered()) {
            return true;
        }
        if (!this.isEntered() || !compare.isEntered()) {
            return false;
        }
        return comments.equals(compare.getComments());
    }

    /**
     * Gets the commentTypeCode attribute. 
     * @return Returns the commentTypeCode.
     */
    public String getCommentTypeCode() {
        return commentTypeCode;
    }

    /**
     * Sets the commentTypeCode attribute value.
     * @param commentTypeCode The commentTypeCode to set.
     */
    public void setCommentTypeCode(String commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    /**
     * Gets the checklistPrintFlag attribute. 
     * @return Returns the checklistPrintFlag.
     */
    public Boolean getChecklistPrintFlag() {
        return checklistPrintFlag;
    }

    /**
     * Sets the checklistPrintFlag attribute value.
     * @param checklistPrintFlag The checklistPrintFlag to set.
     */
    public void setChecklistPrintFlag(Boolean checklistPrintFlag) {
        this.checklistPrintFlag = checklistPrintFlag;
    }

    /**
     * Gets the comments attribute. 
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments attribute value.
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the commentType attribute. 
     * @return Returns the commentType.
     */
    public CommentType getCommentType() {
        return commentType;
    }

    /**
     * Sets the commentType attribute value.
     * @param commentType The commentType to set.
     */
    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
        this.commentTypeCode = commentType != null ? commentType.getCommentTypeCode() : null;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    /**
     * Gets the updateTimestampDateString attribute. 
     * @return Returns the updateTimestampDateString.
     */
    public String getUpdateTimestampDateString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) getUpdateTimestamp());
        return Integer.toString(cal.get(Calendar.MONTH)) + "/" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(cal.get(Calendar.YEAR));
    }

    public void resetPersistenceState() {
        awardCommentId = null;
        versionNumber = null;
    }

    public int compareTo(AwardComment awardCommentArg) {
        return awardCommentArg.getUpdateTimestamp().compareTo(this.getUpdateTimestamp());
    }

    public void disableComment() {
        setVersionNumber(new Long(-1));
    }

    public boolean isDisabled() {
        return new Long(-1).equals(getVersionNumber());
    }

    public boolean isEntered() {
        return comments != null && comments.length() > 0;
    }
}
