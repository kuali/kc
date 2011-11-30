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
package org.kuali.kra.negotiations.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.drools.core.util.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;

/**
 * 
 * This class consolidates Award, Proposal Log, Institutional Proposal, and Sub Awards into one viewable bean for the Negotiation's page. 
 */
public class NegotiationAssociatedDetailBean implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5451290930492370203L;
    
    private static final String EMPTY_STRING = "";
    
    private String associatedDocumentId;
    private String title;
    private String leadUnit;
    private String leadUnitNumber;
    private String piEmployee;
    private String piNonEmployee;
    private String adminPerson;
    private String sponsor;
    private String primeSponsor;
    private String sponsorAward;
    private String subAwardOrganization;
    private List<KcPerson> ospAdministrators;
    private String proposalType;
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param leadUnitNumber
     */
    public NegotiationAssociatedDetailBean(String leadUnitNumber) {
        this.associatedDocumentId = EMPTY_STRING;
        this.title = EMPTY_STRING;
        this.leadUnit = EMPTY_STRING;
        this.piEmployee = EMPTY_STRING;
        this.piNonEmployee = EMPTY_STRING;
        this.adminPerson = EMPTY_STRING;
        this.sponsor = EMPTY_STRING;
        this.primeSponsor = EMPTY_STRING;
        this.sponsorAward = EMPTY_STRING;
        this.subAwardOrganization = EMPTY_STRING;
        this.proposalType = EMPTY_STRING;
        this.leadUnitNumber = leadUnitNumber;
        this.ospAdministrators = new ArrayList<KcPerson>();
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param negotiable
     */
    public NegotiationAssociatedDetailBean(Negotiable negotiable) {
        this(negotiable != null ? negotiable.getLeadUnitNumber() : EMPTY_STRING);
        if (negotiable != null) {
            this.associatedDocumentId = negotiable.getAssociatedDocumentId();
            this.title = negotiable.getTitle();
            this.leadUnit = negotiable.getLeadUnitName();
            this.piEmployee = negotiable.getPiEmployeeName();
            this.piNonEmployee = negotiable.getPiNonEmployeeName();
            this.adminPerson = negotiable.getAdminPersonName();
            this.sponsor = negotiable.getSponsorName();
            this.primeSponsor = negotiable.getPrimeSponsorName();
            this.sponsorAward = negotiable.getSponsorAwardNumber();
            this.subAwardOrganization = negotiable.getSubAwardOrganizationName();
            this.proposalType = negotiable.getNegotiableProposalType() != null ? negotiable.getNegotiableProposalType().getDescription() : "";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeadUnit() {
        return leadUnit;
    }

    public void setLeadUnit(String leadUnit) {
        this.leadUnit = leadUnit;
    }

    public String getPiEmployee() {
        return piEmployee;
    }

    public void setPiEmployee(String piEmployee) {
        this.piEmployee = piEmployee;
    }

    public String getPiNonEmployee() {
        return piNonEmployee;
    }

    public void setPiNonEmployee(String piNonEmployee) {
        this.piNonEmployee = piNonEmployee;
    }

    public String getAdminPerson() {
        return adminPerson;
    }

    public void setAdminPerson(String adminPerson) {
        this.adminPerson = adminPerson;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getPrimeSponsor() {
        return primeSponsor;
    }

    public void setPrimeSponsor(String primeSponsor) {
        this.primeSponsor = primeSponsor;
    }

    public String getSponsorAward() {
        return sponsorAward;
    }

    public void setSponsorAward(String sponsorAward) {
        this.sponsorAward = sponsorAward;
    }

    public String getSubAwardOrganization() {
        return subAwardOrganization;
    }

    public void setSubAwardOrganization(String subAwardOrganization) {
        this.subAwardOrganization = subAwardOrganization;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }
    
    public boolean getDisplayOSPAdministrators() {
        return !StringUtils.isEmpty(this.getLeadUnitNumber());
    }

    public List<KcPerson> getOspAdministrators() {
        return ospAdministrators;
    }

    public void setOspAdministrators(List<KcPerson> ospAdministrators) {
        this.ospAdministrators = ospAdministrators;
    }

    public String getAssociatedDocumentId() {
        return associatedDocumentId;
    }

    public void setAssociatedDocumentId(String associatedDocumentId) {
        this.associatedDocumentId = associatedDocumentId;
    }

    public String getProposalType() {
        return proposalType;
    }

    public void setProposalType(String proposalType) {
        this.proposalType = proposalType;
    }
}