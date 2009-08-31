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
package org.kuali.kra.award.home;

import java.util.LinkedHashMap;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.bo.CommentType;

/**
 * This class is business object representation of an Award Comment
 */
public class AwardComment extends AwardAssociate {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3611932717292205490L;
    private Long awardCommentId;
    private Award award;
    
    @AwardSyncable private String commentTypeCode; 
    @AwardSyncable private Boolean checklistPrintFlag; 
    @AwardSyncable private String comments; 
    
    private CommentType commentType; 
    
    
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
     * This method...
     * @return
     */
    public Award getAward() {
        return award;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = super.toStringMapper();
        hashMap.put("awardCommentId", getAwardCommentId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        return hashMap;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((award == null) ? 0 : award.hashCode());
        result = prime * result + ((awardCommentId == null) ? 0 : awardCommentId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardComment other = (AwardComment) obj;
        if (award == null) {
            if (other.award != null)
                return false;
        }
        else if (!award.equals(other.award))
            return false;
        if (awardCommentId == null) {
            if (other.awardCommentId != null)
                return false;
        }
        else if (!awardCommentId.equals(other.awardCommentId))
            return false;
        return true;
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

    public void resetPersistenceState() {
        awardCommentId = null;
        versionNumber = null;
    }
}
