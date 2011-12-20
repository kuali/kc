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
package org.kuali.kra.personmasschange.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;

public class PersonMassChangeHelper implements Serializable {
    
    private PersonMassChangeForm form;
    
    private List<Award> awardChangeCandidates;
    private List<InstitutionalProposal> institutionalProposalChangeCandidates;
    private List<DevelopmentProposal> proposalDevelopmentChangeCandidates;
    private List<ProposalLog> proposalLogChangeCandidates;
    private List<SubAward> subawardChangeCandidates;
    private List<Negotiation> negotiationChangeCandidates;
    private List<Committee> committeeChangeCandidates;
    private List<Protocol> protocolChangeCandidates;
    private List<CommitteeSchedule> scheduleChangeCandidates;
    private List<Unit> unitChangeCandidates;
    
    public PersonMassChangeHelper(PersonMassChangeForm form) {
        this.form = form;
        
        awardChangeCandidates = new ArrayList<Award>();
        institutionalProposalChangeCandidates = new ArrayList<InstitutionalProposal>();
        proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
        proposalLogChangeCandidates = new ArrayList<ProposalLog>();
        subawardChangeCandidates = new ArrayList<SubAward>();
        negotiationChangeCandidates = new ArrayList<Negotiation>();
        committeeChangeCandidates = new ArrayList<Committee>();
        protocolChangeCandidates = new ArrayList<Protocol>();
        scheduleChangeCandidates = new ArrayList<CommitteeSchedule>();
        unitChangeCandidates = new ArrayList<Unit>();
    }
    
    public String getReplaceeName() {
        return form.getDocument().getPersonMassChange().getReplaceeFullName();
    }
    
    public String getReplacerName() {
        return form.getDocument().getPersonMassChange().getReplacerFullName();
    }

    public List<Award> getAwardChangeCandidates() {
        return awardChangeCandidates;
    }

    public void setAwardChangeCandidates(List<Award> awardChangeCandidates) {
        this.awardChangeCandidates = awardChangeCandidates;
    }

    public List<InstitutionalProposal> getInstitutionalProposalChangeCandidates() {
        return institutionalProposalChangeCandidates;
    }

    public void setInstitutionalProposalChangeCandidates(List<InstitutionalProposal> institutionalProposalChangeCandidates) {
        this.institutionalProposalChangeCandidates = institutionalProposalChangeCandidates;
    }

    public List<DevelopmentProposal> getProposalDevelopmentChangeCandidates() {
        return proposalDevelopmentChangeCandidates;
    }

    public void setProposalDevelopmentChangeCandidates(List<DevelopmentProposal> proposalDevelopmentChangeCandidates) {
        this.proposalDevelopmentChangeCandidates = proposalDevelopmentChangeCandidates;
    }

    public List<ProposalLog> getProposalLogChangeCandidates() {
        return proposalLogChangeCandidates;
    }

    public void setProposalLogChangeCandidates(List<ProposalLog> proposalLogChangeCandidates) {
        this.proposalLogChangeCandidates = proposalLogChangeCandidates;
    }

    public List<SubAward> getSubawardChangeCandidates() {
        return subawardChangeCandidates;
    }

    public void setSubawardChangeCandidates(List<SubAward> subawardChangeCandidates) {
        this.subawardChangeCandidates = subawardChangeCandidates;
    }

    public List<Negotiation> getNegotiationChangeCandidates() {
        return negotiationChangeCandidates;
    }

    public void setNegotiationChangeCandidates(List<Negotiation> negotiationChangeCandidates) {
        this.negotiationChangeCandidates = negotiationChangeCandidates;
    }

    public List<Committee> getCommitteeChangeCandidates() {
        return committeeChangeCandidates;
    }

    public void setCommitteeChangeCandidates(List<Committee> committeeChangeCandidates) {
        this.committeeChangeCandidates = committeeChangeCandidates;
    }

    public List<Protocol> getProtocolChangeCandidates() {
        return protocolChangeCandidates;
    }

    public void setProtocolChangeCandidates(List<Protocol> protocolChangeCandidates) {
        this.protocolChangeCandidates = protocolChangeCandidates;
    }

    public List<CommitteeSchedule> getScheduleChangeCandidates() {
        return scheduleChangeCandidates;
    }

    public void setScheduleChangeCandidates(List<CommitteeSchedule> scheduleChangeCandidates) {
        this.scheduleChangeCandidates = scheduleChangeCandidates;
    }

    public List<Unit> getUnitChangeCandidates() {
        return unitChangeCandidates;
    }

    public void setUnitChangeCandidates(List<Unit> unitChangeCandidates) {
        this.unitChangeCandidates = unitChangeCandidates;
    }

}