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
package org.kuali.kra.institutionalproposal.proposaladmindetails;

import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

public class ProposalAdminDetails extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
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

    public ProposalAdminDetails() {
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

    /**
     * Gets the proposalAdminDetailId attribute. 
     * @return Returns the proposalAdminDetailId.
     */
    public Long getProposalAdminDetailId() {
        return proposalAdminDetailId;
    }

    /**
     * Sets the proposalAdminDetailId attribute value.
     * @param proposalAdminDetailId The proposalAdminDetailId to set.
     */
    public void setProposalAdminDetailId(Long proposalAdminDetailId) {
        this.proposalAdminDetailId = proposalAdminDetailId;
    }

    /**
     * Gets the instProposalId attribute. 
     * @return Returns the instProposalId.
     */
    public Long getInstProposalId() {
        return instProposalId;
    }

    /**
     * Sets the instProposalId attribute value.
     * @param instProposalId The instProposalId to set.
     */
    public void setInstProposalId(Long instProposalId) {
        this.instProposalId = instProposalId;
    }

    /**
     * Gets the developmentProposal attribute. 
     * @return Returns the developmentProposal.
     */
    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    /**
     * Sets the developmentProposal attribute value.
     * @param developmentProposal The developmentProposal to set.
     */
    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }

    /**
     * Gets the institutionalProposal attribute. 
     * @return Returns the institutionalProposal.
     */
    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    /**
     * Sets the institutionalProposal attribute value.
     * @param institutionalProposal The institutionalProposal to set.
     */
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    /**
     * Gets the devProposalNumber attribute. 
     * @return Returns the devProposalNumber.
     */
    public String getDevProposalNumber() {
        return devProposalNumber;
    }

    /**
     * Sets the devProposalNumber attribute value.
     * @param devProposalNumber The devProposalNumber to set.
     */
    public void setDevProposalNumber(String devProposalNumber) {
        this.devProposalNumber = devProposalNumber;
    }
}
