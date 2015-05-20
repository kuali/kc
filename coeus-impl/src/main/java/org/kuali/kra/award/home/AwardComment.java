/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.home;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.bo.CommentType;

import java.text.SimpleDateFormat;

/**
 * This class is business object representation of an Award Comment
 */
public class AwardComment extends AwardAssociate implements Comparable<AwardComment> {

    public static final String UPDATE_DATE_FORMAT = "M/dd/yyyy";
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


    public AwardComment() {
        super();
    }

    public AwardComment(CommentType commentType, String comments) {
        this();
        setCommentType(commentType);
        setComments(comments);
        setChecklistPrintFlag(Boolean.FALSE);
    }

    public Long getAwardCommentId() {
        return awardCommentId;
    }

    public void setAwardCommentId(Long awardCommentId) {
        this.awardCommentId = awardCommentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardCommentId == null) ? 0 : awardCommentId.hashCode());
        return result;
    }

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
        SimpleDateFormat dateFormat = new SimpleDateFormat(UPDATE_DATE_FORMAT);
        return dateFormat.format(getUpdateTimestamp());
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
