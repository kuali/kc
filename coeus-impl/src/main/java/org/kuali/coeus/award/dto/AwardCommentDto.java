package org.kuali.coeus.award.dto;
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

public class AwardCommentDto {

    private Long awardCommentId;
    private String commentTypeCode;
    private Boolean checklistPrintFlag;
    private String comments;

    public Long getAwardCommentId() {
        return awardCommentId;
    }

    public void setAwardCommentId(Long awardCommentId) {
        this.awardCommentId = awardCommentId;
    }

    public String getCommentTypeCode() {
        return commentTypeCode;
    }

    public void setCommentTypeCode(String commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    public Boolean getChecklistPrintFlag() {
        return checklistPrintFlag;
    }

    public void setChecklistPrintFlag(Boolean checklistPrintFlag) {
        this.checklistPrintFlag = checklistPrintFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
