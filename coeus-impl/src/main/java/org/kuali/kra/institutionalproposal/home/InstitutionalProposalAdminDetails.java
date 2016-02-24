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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Date;

public class InstitutionalProposalAdminDetails extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String devProposalNumber;

    private String instProposalNumber;

    private Integer instPropSequenceNumber;

    private Date dateSubmittedByDept;

    private Date dateReturnedToDept;

    private Date dateApprovedByOsp;

    private Date dateSubmittedToAgency;

    private Date instPropCreateDate;

    private String instPropCreateUser;

    private String signedBy;

    private boolean submissionType;

    private InstitutionalProposal institutionalProposal;

    public InstitutionalProposalAdminDetails() {
    }

    public String getDevProposalNumber() {
        return devProposalNumber;
    }

    public void setDevProposalNumber(String devProposalNumber) {
        this.devProposalNumber = devProposalNumber;
    }

    public String getInstProposalNumber() {
        return instProposalNumber;
    }

    public void setInstProposalNumber(String instProposalNumber) {
        this.instProposalNumber = instProposalNumber;
    }

    public Integer getInstPropSequenceNumber() {
        return instPropSequenceNumber;
    }

    public void setInstPropSequenceNumber(Integer instPropSequenceNumber) {
        this.instPropSequenceNumber = instPropSequenceNumber;
    }

    public Date getDateSubmittedByDept() {
        return dateSubmittedByDept;
    }

    public void setDateSubmittedByDept(Date dateSubmittedByDept) {
        this.dateSubmittedByDept = dateSubmittedByDept;
    }

    public Date getDateReturnedToDept() {
        return dateReturnedToDept;
    }

    public void setDateReturnedToDept(Date dateReturnedToDept) {
        this.dateReturnedToDept = dateReturnedToDept;
    }

    public Date getDateApprovedByOsp() {
        return dateApprovedByOsp;
    }

    public void setDateApprovedByOsp(Date dateApprovedByOsp) {
        this.dateApprovedByOsp = dateApprovedByOsp;
    }

    public Date getDateSubmittedToAgency() {
        return dateSubmittedToAgency;
    }

    public void setDateSubmittedToAgency(Date dateSubmittedToAgency) {
        this.dateSubmittedToAgency = dateSubmittedToAgency;
    }

    public Date getInstPropCreateDate() {
        return instPropCreateDate;
    }

    public void setInstPropCreateDate(Date instPropCreateDate) {
        this.instPropCreateDate = instPropCreateDate;
    }

    public String getInstPropCreateUser() {
        return instPropCreateUser;
    }

    public void setInstPropCreateUser(String instPropCreateUser) {
        this.instPropCreateUser = instPropCreateUser;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public boolean getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(boolean submissionType) {
        this.submissionType = submissionType;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }
}
