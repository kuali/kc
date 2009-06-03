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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class is an abstract class for AwardComment
 */
@SuppressWarnings("serial")
public abstract class AwardCommentBase extends KraPersistableBusinessObjectBase implements AwardSynchronizable{

    private String commentTypeCode; 
    private Boolean checklistPrintFlag; 
    private String comments; 
    
    private CommentType commentType; 
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getSyncBaseClass()
     */
    public Class getSyncBaseClass() {
        return AwardCommentBase.class;
    }

    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getAwardSyncClass()
     */
    public Class getAwardSyncClass() {
        return AwardComment.class;
    }
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("commentTypeCode", getCommentTypeCode());
        hashMap.put("checklistPrintFlag", getChecklistPrintFlag());
        hashMap.put("comments", getComments());
        return hashMap;
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
     * Gets the comment attribute. 
     * @return Returns the comment.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comment attribute value.
     * @param comment The comment to set.
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
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checklistPrintFlag == null) ? 0 : checklistPrintFlag.hashCode());
        result = prime * result + ((commentType == null) ? 0 : commentType.hashCode());
        result = prime * result + ((commentTypeCode == null) ? 0 : commentTypeCode.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardCommentBase other = (AwardCommentBase) obj;
        if (checklistPrintFlag == null) {
            if (other.checklistPrintFlag != null)
                return false;
        }
        else if (!checklistPrintFlag.equals(other.checklistPrintFlag))
            return false;
        if (commentType == null) {
            if (other.commentType != null)
                return false;
        }
        else if (!commentType.equals(other.commentType))
            return false;
        if (commentTypeCode == null) {
            if (other.commentTypeCode != null)
                return false;
        }
        else if (!commentTypeCode.equals(other.commentTypeCode))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        }
        else if (!comments.equals(other.comments))
            return false;
        return true;
    }

}
