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

import org.kuali.kra.award.home.Award;
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
    
    public static final String MODE_AWARD = "AWD";
    public static final String MODE_PROPOSAL_LOG = "PL";
    public static final String MODE_INSTITUTIONAL_PROPOSAL = "IP";
    public static final String MODE_SUBAWARD = "SAWD";
    public static final String MODE_NONE = "NONE";
    private static final String EMPTY_STRING = "";
    
    
    private String title;
    private String leadUnit;
    private String piEmployee;
    private String piNonEmployee;
    private String adminPerson;
    private String sponsor;
    private String primeSponsor;
    private String sponsorAward;
    private String subAwardOrganization;
    private String mode;
    
    public NegotiationAssociatedDetailBean() {
        this.title = EMPTY_STRING;
        this.leadUnit = EMPTY_STRING;
        this.piEmployee = EMPTY_STRING;
        this.piNonEmployee = EMPTY_STRING;
        this.adminPerson = EMPTY_STRING;
        this.sponsor = EMPTY_STRING;
        this.primeSponsor = EMPTY_STRING;
        this.sponsorAward = EMPTY_STRING;
        this.subAwardOrganization = EMPTY_STRING;
        this.mode = MODE_NONE;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param award
     */
    public NegotiationAssociatedDetailBean(Award award) {
        this();
        if (award != null) {
            this.title = award.getTitle();
            this.leadUnit = award.getLeadUnit() == null ? EMPTY_STRING : award.getLeadUnit().getUnitName();
            this.piEmployee = award.getPrincipalInvestigatorName();
            this.piNonEmployee = EMPTY_STRING;
            this.adminPerson = EMPTY_STRING;
            this.sponsor = award.getSponsor() == null ? EMPTY_STRING : award.getSponsor().getSponsorName();
            this.primeSponsor = award.getPrimeSponsor() == null ? EMPTY_STRING : award.getPrimeSponsor().getSponsorName();
            this.sponsorAward = award.getSponsorAwardNumber();
            this.subAwardOrganization = EMPTY_STRING;
        }
        this.mode = MODE_AWARD;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param proposalLog
     */
    public NegotiationAssociatedDetailBean(ProposalLog proposalLog) {
        this();
        if (proposalLog != null) {
            this.title = proposalLog.getTitle();
            this.leadUnit = proposalLog.getUnit() == null ? EMPTY_STRING : proposalLog.getUnit().getUnitName();
            this.piEmployee = proposalLog.getPiName();
            this.piNonEmployee = EMPTY_STRING;
            this.adminPerson = EMPTY_STRING;
            this.sponsor = proposalLog.getSponsorName();
            this.primeSponsor = EMPTY_STRING;
            this.sponsorAward = EMPTY_STRING;
            this.subAwardOrganization = EMPTY_STRING;
        }
        this.mode = MODE_PROPOSAL_LOG;
        
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param institutionalProposal
     */
    public NegotiationAssociatedDetailBean(InstitutionalProposal institutionalProposal) {
        this();
        if (institutionalProposal != null) {
            this.title = institutionalProposal.getTitle();
            this.leadUnit = institutionalProposal.getLeadUnit() == null ? EMPTY_STRING : institutionalProposal.getLeadUnit().getUnitName();
            this.piEmployee = institutionalProposal.getPrincipalInvestigator() == null ? EMPTY_STRING : institutionalProposal.getPrincipalInvestigator().getFullName();
            this.piNonEmployee = EMPTY_STRING;
            this.adminPerson = EMPTY_STRING;
            this.sponsor = institutionalProposal.getSponsorName();
            this.primeSponsor = institutionalProposal.getPrimeSponsor() == null ? EMPTY_STRING : institutionalProposal.getPrimeSponsor().getSponsorName();
            this.sponsorAward = EMPTY_STRING;
            this.subAwardOrganization = EMPTY_STRING;
        }
        this.mode = MODE_INSTITUTIONAL_PROPOSAL;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param unAssociatedDetail
     */
    public NegotiationAssociatedDetailBean(NegotiationUnassociatedDetail unAssociatedDetail) {
        this();
        if (unAssociatedDetail != null) {
            this.title = unAssociatedDetail.getTitle();
            this.leadUnit = unAssociatedDetail.getLeadUnit() == null ? EMPTY_STRING : unAssociatedDetail.getLeadUnit().getUnitName();
            this.piEmployee = unAssociatedDetail.getPIEmployee() == null ? EMPTY_STRING : unAssociatedDetail.getPIEmployee().getFullName();
            this.piNonEmployee = unAssociatedDetail.getPINonEmployee() == null ? EMPTY_STRING : unAssociatedDetail.getPINonEmployee().getFullName();
            this.adminPerson = unAssociatedDetail.getContactAdmin() == null ? EMPTY_STRING : unAssociatedDetail.getContactAdmin().getFullName();
            this.sponsor = unAssociatedDetail.getSponsor() == null ? EMPTY_STRING : unAssociatedDetail.getSponsor().getSponsorName();
            this.primeSponsor = unAssociatedDetail.getPrimeSponsor() == null ? EMPTY_STRING : unAssociatedDetail.getPrimeSponsor().getSponsorName();
            this.sponsorAward = unAssociatedDetail.getSponsorAwardNumber();
            this.subAwardOrganization = unAssociatedDetail.getSubAwardOrganization() == null ? EMPTY_STRING 
                    : unAssociatedDetail.getSubAwardOrganization().getOrganizationName();
        }
        this.mode = MODE_NONE;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}