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
package org.kuali.kra.institutionalproposal;

import org.kuali.kra.bo.CommentType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class ProposalComment extends InstitutionalProposalAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalCommentsId;

    private Long proposalId;

    private String commentTypeCode;

    private String comments;

    private CommentType commentType;

    private InstitutionalProposal proposal;

    public ProposalComment() {
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

    public InstitutionalProposal getProposal() {
        return proposal;
    }

    public void setProposal(InstitutionalProposal proposal) {
        this.proposal = proposal;
    }
}
