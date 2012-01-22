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
package org.kuali.kra.bo;


/**
 * This class is Business Object representation of a comment type.
 */
public class CommentType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5649376154094364142L;

    public static final String SCREENFLAG_TRUE = "Y";

    private String commentTypeCode;

    private String description;

    private Boolean templateFlag;

    private Boolean checklistFlag;

    private Boolean awardCommentScreenFlag;

    public String getCommentTypeCode() {
        return commentTypeCode;
    }

    public void setCommentTypeCode(String commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    /**
     * This method returns the comment description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets comment description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the template flag
     * @return
     */
    public Boolean getTemplateFlag() {
        return templateFlag;
    }

    /**
     * This method sets the template flag
     * @param templateFlag
     */
    public void setTemplateFlag(Boolean templateFlag) {
        this.templateFlag = templateFlag;
    }

    /**
     * This method returns the checklist flag
     * @return
     */
    public Boolean getChecklistFlag() {
        return checklistFlag;
    }

    /**
     * This method sets the checklist flag
     * @param checklistFlag
     */
    public void setChecklistFlag(Boolean checklistFlag) {
        this.checklistFlag = checklistFlag;
    }

    /**
     * This method returns the Comment screen flag
     * @return
     */
    public Boolean getAwardCommentScreenFlag() {
        return awardCommentScreenFlag;
    }

    /**
     * This method sets the comment screen flag
     * @param awardCommentScreenFlag
     */
    public void setAwardCommentScreenFlag(Boolean awardCommentScreenFlag) {
        this.awardCommentScreenFlag = awardCommentScreenFlag;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((awardCommentScreenFlag == null) ? 0 : awardCommentScreenFlag.hashCode());
        result = prime * result + ((checklistFlag == null) ? 0 : checklistFlag.hashCode());
        result = prime * result + ((commentTypeCode == null) ? 0 : commentTypeCode.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((templateFlag == null) ? 0 : templateFlag.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CommentType other = (CommentType) obj;
        if (awardCommentScreenFlag == null) {
            if (other.awardCommentScreenFlag != null) return false;
        } else if (!awardCommentScreenFlag.equals(other.awardCommentScreenFlag)) return false;
        if (checklistFlag == null) {
            if (other.checklistFlag != null) return false;
        } else if (!checklistFlag.equals(other.checklistFlag)) return false;
        if (commentTypeCode == null) {
            if (other.commentTypeCode != null) return false;
        } else if (!commentTypeCode.equals(other.commentTypeCode)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (templateFlag == null) {
            if (other.templateFlag != null) return false;
        } else if (!templateFlag.equals(other.templateFlag)) return false;
        return true;
    }
}
