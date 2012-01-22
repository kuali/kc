/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.home;

import org.apache.commons.lang.StringUtils;
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
