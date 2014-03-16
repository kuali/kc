/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.s2s.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "S2S_OPPORTUNITY")
public class S2sOpportunity extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "CFDA_NUMBER")
    private String cfdaNumber;

    @Column(name = "CLOSING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar closingDate;

    @Column(name = "COMPETETION_ID")
    private String competetionId;

    @Column(name = "INSTRUCTION_URL")
    private String instructionUrl;

    @Column(name = "OPENING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar openingDate;

    @Column(name = "OPPORTUNITY")
    @Lob
    private String opportunity;

    // opportunityId was changed to fundingOpportunityNumber in V2 
    @Column(name = "OPPORTUNITY_ID")
    private String opportunityId;

    // this is fundingOpportunityTitle in V2 
    @Column(name = "OPPORTUNITY_TITLE")
    private String opportunityTitle;

    @Column(name = "REVISION_CODE")
    private String revisionCode;

    @Column(name = "REVISION_OTHER_DESCRIPTION")
    private String revisionOtherDescription;

    @Column(name = "S2S_SUBMISSION_TYPE_CODE")
    private String s2sSubmissionTypeCode;

    @Column(name = "SCHEMA_URL")
    private String schemaUrl;

    @Column(name = "OFFERING_AGENCY")
    private String offeringAgency;

    @Column(name = "AGENCY_CONTACT_INFO")
    private String agencyContactInfo;

    @Column(name = "CFDA_DESCRIPTION")
    private String cfdaDescription;

    @Column(name = "MULTI_PROJECT")
    @Convert(converter = BooleanYNConverter.class)
    private boolean multiProject;

    @Column(name = "PROVIDER")
    private String providerCode;

    @OneToMany(targetEntity = S2sOppForms.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private List<S2sOppForms> s2sOppForms;

    @ManyToOne(targetEntity = S2sSubmissionType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_SUBMISSION_TYPE_CODE", referencedColumnName = "S2S_SUBMISSION_TYPE_CODE", insertable = false, updatable = false)
    private S2sSubmissionType s2sSubmissionType;

    @ManyToOne(targetEntity = S2sRevisionType.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "REVISION_CODE", referencedColumnName = "S2S_REVISION_TYPE_CODE", insertable = false, updatable = false)
    private S2sRevisionType s2sRevisionType;

    @ManyToOne(targetEntity = S2sProvider.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROVIDER", referencedColumnName = "CODE", insertable = false, updatable = false)
    private S2sProvider s2sProvider;

    @OneToOne(targetEntity = DevelopmentProposal.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal developmentProposal;

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    public Calendar getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Calendar closingDate) {
        this.closingDate = closingDate;
    }

    public String getCompetetionId() {
        return competetionId;
    }

    public void setCompetetionId(String competetionId) {
        this.competetionId = competetionId;
    }

    public String getInstructionUrl() {
        return instructionUrl;
    }

    public void setInstructionUrl(String instructionUrl) {
        this.instructionUrl = instructionUrl;
    }

    public Calendar getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Calendar openingDate) {
        this.openingDate = openingDate;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public String getRevisionCode() {
        return revisionCode;
    }

    public void setRevisionCode(String revisionCode) {
        this.revisionCode = revisionCode;
    }

    public String getRevisionOtherDescription() {
        return revisionOtherDescription;
    }

    public void setRevisionOtherDescription(String revisionOtherDescription) {
        this.revisionOtherDescription = revisionOtherDescription;
    }

    public String getS2sSubmissionTypeCode() {
        return s2sSubmissionTypeCode;
    }

    public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
        this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public List<S2sOppForms> getS2sOppForms() {
        return s2sOppForms;
    }

    public void setS2sOppForms(List<S2sOppForms> oppForms) {
        s2sOppForms = oppForms;
    }

    public S2sRevisionType getS2sRevisionType() {
        return s2sRevisionType;
    }

    public void setS2sRevisionType(S2sRevisionType revisionType) {
        s2sRevisionType = revisionType;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    public void setOpportunityTitle(String opportunityTitle) {
        this.opportunityTitle = opportunityTitle;
    }

    public S2sSubmissionType getS2sSubmissionType() {
        return s2sSubmissionType;
    }

    public void setS2sSubmissionType(S2sSubmissionType submissionType) {
        s2sSubmissionType = submissionType;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public S2sProvider getS2sProvider() {
        if (s2sProvider == null && providerCode != null) {
            this.refreshReferenceObject("s2sProvider");
        }
        return s2sProvider;
    }

    public void setS2sProvider(S2sProvider s2sProvider) {
        this.s2sProvider = s2sProvider;
    }

    public String getFundingOpportunityNumber() {
        return opportunityId;
    }

    public void setFundingOpportunityNumber(String fundingOpportunityNumber) {
        this.opportunityId = fundingOpportunityNumber;
    }

    public String getOfferingAgency() {
        return offeringAgency;
    }

    public void setOfferingAgency(String offeringAgency) {
        this.offeringAgency = offeringAgency;
    }

    public String getAgencyContactInfo() {
        return agencyContactInfo;
    }

    public void setAgencyContactInfo(String agencyContactInfo) {
        this.agencyContactInfo = agencyContactInfo;
    }

    public String getCfdaDescription() {
        return cfdaDescription;
    }

    public void setCfdaDescription(String cfdaDescription) {
        this.cfdaDescription = cfdaDescription;
    }

    public boolean isMultiProject() {
        return multiProject;
    }

    public void setMultiProject(boolean multiProject) {
        this.multiProject = multiProject;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }
}
