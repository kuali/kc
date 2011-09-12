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

import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;

/**
 * 
 * This class consolidates Award, Proposal Log, Institutional Proposal, and Sub Awards into one viewable bean for the Negotiation's page. 
 */
public class NegotiationAssociatedDetailBean {
    
    public static final String MODE_AWARD = "AWD";
    public static final String MODE_PROPOSAL_LOG = "PL";
    public static final String MODE_INSTITUTIONAL_PROPOSAL = "IP";
    public static final String MODE_SUBAWARD = "SAWD";
    public static final String MODE_NONE = "NONE";
    
    
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
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param award
     */
    public NegotiationAssociatedDetailBean(Award award) {
        this.title = award.getTitle();
        this.leadUnit = award.getLeadUnit() == null ? "" : award.getLeadUnit().getUnitName();
        this.piEmployee = award.getPrincipalInvestigatorName();
        this.piNonEmployee = "";
        this.adminPerson = "";
        this.sponsor = award.getSponsor() == null ? "" : award.getSponsor().getSponsorName();
        this.primeSponsor = award.getPrimeSponsor() == null ? "" : award.getPrimeSponsor().getSponsorName();
        this.sponsorAward = award.getSponsorAwardNumber();
        this.subAwardOrganization = "";
        this.mode = MODE_AWARD;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param proposalLog
     */
    public NegotiationAssociatedDetailBean(ProposalLog proposalLog) {
        this.title = proposalLog.getTitle();
        this.leadUnit = proposalLog.getUnit() == null ? "" : proposalLog.getUnit().getUnitName();
        this.piEmployee = proposalLog.getPiName();
        this.piNonEmployee = "";
        this.adminPerson = "";
        this.sponsor = proposalLog.getSponsorName();
        this.primeSponsor = "";
        this.sponsorAward = "";
        this.subAwardOrganization = "";
        this.mode = MODE_PROPOSAL_LOG;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param institutionalProposal
     */
    public NegotiationAssociatedDetailBean(InstitutionalProposal institutionalProposal) {
        this.title = institutionalProposal.getTitle();
        this.leadUnit = institutionalProposal.getLeadUnit() == null ? "" : institutionalProposal.getLeadUnit().getUnitName();
        this.piEmployee = institutionalProposal.getPrincipalInvestigator() == null ? "" : institutionalProposal.getPrincipalInvestigator().getFullName();
        this.piNonEmployee = "";
        this.adminPerson = "";
        this.sponsor = institutionalProposal.getSponsorName();
        this.primeSponsor = institutionalProposal.getPrimeSponsor() == null ? "" : institutionalProposal.getPrimeSponsor().getSponsorName();
        this.sponsorAward = "";
        this.subAwardOrganization = "";
        this.mode = MODE_INSTITUTIONAL_PROPOSAL;
    }
    
    /**
     * 
     * Constructs a NegotiationAssociatedDetailBean.java.
     * @param unAssociatedDetail
     */
    public NegotiationAssociatedDetailBean(NegotiationUnassociatedDetail unAssociatedDetail) {
        this.title = unAssociatedDetail.getTitle();
        this.leadUnit = unAssociatedDetail.getLeadUnit() == null ? "" : unAssociatedDetail.getLeadUnit().getUnitName();
        this.piEmployee = unAssociatedDetail.getPIEmployee() == null ? "" : unAssociatedDetail.getPIEmployee().getFullName();
        this.piNonEmployee = unAssociatedDetail.getPINonEmployee() == null ? "" : unAssociatedDetail.getPINonEmployee().getFullName();
        this.adminPerson = unAssociatedDetail.getContactAdmin() == null ? "" : unAssociatedDetail.getContactAdmin().getFullName();
        this.sponsor = unAssociatedDetail.getSponsor() == null ? "" : unAssociatedDetail.getSponsor().getSponsorName();
        this.primeSponsor = unAssociatedDetail.getPrimeSponsor() == null ? "" : unAssociatedDetail.getPrimeSponsor().getSponsorName();
        this.sponsorAward = unAssociatedDetail.getSponsorAwardNumber();
        this.subAwardOrganization = unAssociatedDetail.getSubAwardOrganization() == null ? "" 
                : unAssociatedDetail.getSubAwardOrganization().getOrganizationName();
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