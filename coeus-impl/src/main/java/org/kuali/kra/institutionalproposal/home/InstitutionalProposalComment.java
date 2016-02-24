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
package org.kuali.kra.institutionalproposal.home;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;

public class InstitutionalProposalComment extends InstitutionalProposalAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalCommentsId;

    private Long proposalId;

    private String commentTypeCode;

    private String comments;

    private CommentType commentType;

    public InstitutionalProposalComment() {
        super();
    }

    public InstitutionalProposalComment(String commentTypeCode) {
        this();
        setCommentTypeCode(commentTypeCode);
        setComments(StringUtils.EMPTY);
    }

    public Long getProposalCommentsId() {
        return proposalCommentsId;
    }

    public void setProposalCommentsId(Long proposalCommentsId) {
        this.proposalCommentsId = proposalCommentsId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public String getCommentTypeCode() {
        return commentTypeCode;
    }

    public void setCommentTypeCode(String commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commentType == null) ? 0 : commentType.hashCode());
        result = prime * result + ((commentTypeCode == null) ? 0 : commentTypeCode.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((proposalCommentsId == null) ? 0 : proposalCommentsId.hashCode());
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        InstitutionalProposalComment other = (InstitutionalProposalComment) obj;
        if (commentType == null) {
            if (other.commentType != null) return false;
        } else if (!commentType.equals(other.commentType)) return false;
        if (commentTypeCode == null) {
            if (other.commentTypeCode != null) return false;
        } else if (!commentTypeCode.equals(other.commentTypeCode)) return false;
        if (comments == null) {
            if (other.comments != null) return false;
        } else if (!comments.equals(other.comments)) return false;
        if (proposalCommentsId == null) {
            if (other.proposalCommentsId != null) return false;
        } else if (!proposalCommentsId.equals(other.proposalCommentsId)) return false;
        if (proposalId == null) {
            if (other.proposalId != null) return false;
        } else if (!proposalId.equals(other.proposalId)) return false;
        return true;
    }
}
