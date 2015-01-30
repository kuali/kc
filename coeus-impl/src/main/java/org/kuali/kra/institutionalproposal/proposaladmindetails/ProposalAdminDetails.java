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
package org.kuali.kra.institutionalproposal.proposaladmindetails;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;

import java.sql.Date;

public class ProposalAdminDetails extends KcPersistableBusinessObjectBase implements ProposalAdminDetailsContract {


    private static final long serialVersionUID = -277575593517810685L;

    private Long proposalAdminDetailId;

    private String devProposalNumber;

    private Long instProposalId;

    private Date dateSubmittedByDept;

    private Date dateReturnedToDept;

    private Date dateApprovedByOsp;

    private Date dateSubmittedToAgency;

    private Date instPropCreateDate;

    private String instPropCreateUser;

    private String signedBy;

    private boolean submissionType;

    private DevelopmentProposal developmentProposal;

    private InstitutionalProposal institutionalProposal;

    @Override
    public Date getDateSubmittedByDept() {
        return dateSubmittedByDept;
    }

    public void setDateSubmittedByDept(Date dateSubmittedByDept) {
        this.dateSubmittedByDept = dateSubmittedByDept;
    }

    @Override
    public Date getDateReturnedToDept() {
        return dateReturnedToDept;
    }

    public void setDateReturnedToDept(Date dateReturnedToDept) {
        this.dateReturnedToDept = dateReturnedToDept;
    }

    @Override
    public Date getDateApprovedByOsp() {
        return dateApprovedByOsp;
    }

    public void setDateApprovedByOsp(Date dateApprovedByOsp) {
        this.dateApprovedByOsp = dateApprovedByOsp;
    }

    @Override
    public Date getDateSubmittedToAgency() {
        return dateSubmittedToAgency;
    }

    public void setDateSubmittedToAgency(Date dateSubmittedToAgency) {
        this.dateSubmittedToAgency = dateSubmittedToAgency;
    }

    @Override
    public Date getInstPropCreateDate() {
        return instPropCreateDate;
    }

    public void setInstPropCreateDate(Date instPropCreateDate) {
        this.instPropCreateDate = instPropCreateDate;
    }

    @Override
    public String getInstPropCreateUser() {
        return instPropCreateUser;
    }

    public void setInstPropCreateUser(String instPropCreateUser) {
        this.instPropCreateUser = instPropCreateUser;
    }

    @Override
    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    @Override
    public boolean getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(boolean submissionType) {
        this.submissionType = submissionType;
    }

    @Override
    public Long getProposalAdminDetailId() {
        return proposalAdminDetailId;
    }


    public void setProposalAdminDetailId(Long proposalAdminDetailId) {
        this.proposalAdminDetailId = proposalAdminDetailId;
    }

    @Override
    public String getDevProposalNumber() {
        return devProposalNumber;
    }


    public void setDevProposalNumber(String devProposalNumber) {
        this.devProposalNumber = devProposalNumber;
    }


    @Override
    public Long getInstProposalId() {
        return instProposalId;
    }

    public void setInstProposalId(Long instProposalId) {
        this.instProposalId = instProposalId;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }
}
