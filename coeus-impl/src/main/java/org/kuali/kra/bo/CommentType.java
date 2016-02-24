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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is Business Object representation of a comment type.
 */
public class CommentType extends KcPersistableBusinessObjectBase {


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
