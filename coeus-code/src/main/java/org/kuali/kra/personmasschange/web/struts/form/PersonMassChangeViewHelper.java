/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.*;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;

import java.util.ArrayList;
import java.util.List;

public class PersonMassChangeViewHelper extends PersonMassChangeHelperBase {

    private static final long serialVersionUID = -2286604379815618461L;

    private static final String EMPTY_CANDIDATES_MESSAGE = "No records need to be changed.";

    private PersonMassChangeForm form;
    
    private List<Award> awardChangeCandidates;
    private List<IacucProtocol> iacucProtocolChangeCandidates;
    private List<InstitutionalProposal> institutionalProposalChangeCandidates;
    private List<DevelopmentProposal> proposalDevelopmentChangeCandidates;
    private List<ProposalLog> proposalLogChangeCandidates;
    private List<SubAward> subawardChangeCandidates;
    private List<Negotiation> negotiationChangeCandidates;
    private List<Committee> committeeChangeCandidates;
    private List<Protocol> protocolChangeCandidates;
    private List<CommitteeSchedule> scheduleChangeCandidates;
    private List<UnitAdministrator> unitAdministratorChangeCandidates;
    
    private transient AwardPersonMassChangeService awardPersonMassChangeService;
    private transient IacucProtocolPersonMassChangeService iacucProtocolPersonMassChangeService;
    private transient InstitutionalProposalPersonMassChangeService institutionalProposalPersonMassChangeService;
    private transient ProposalDevelopmentPersonMassChangeService proposalDevelopmentPersonMassChangeService;
    private transient ProposalLogPersonMassChangeService proposalLogPersonMassChangeService;
    private transient SubawardPersonMassChangeService subawardPersonMassChangeService;
    private transient NegotiationPersonMassChangeService negotiationPersonMassChangeService;
    private transient ProtocolPersonMassChangeService protocolPersonMassChangeService;
    private transient UnitAdministratorPersonMassChangeService unitAdministratorPersonMassChangeService;
    
    public PersonMassChangeViewHelper(PersonMassChangeForm form) {
        this.form = form;
        
        awardChangeCandidates = new ArrayList<Award>();
        iacucProtocolChangeCandidates = new ArrayList<IacucProtocol>();
        institutionalProposalChangeCandidates = new ArrayList<InstitutionalProposal>();
        proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
        proposalLogChangeCandidates = new ArrayList<ProposalLog>();
        subawardChangeCandidates = new ArrayList<SubAward>();
        negotiationChangeCandidates = new ArrayList<Negotiation>();
        committeeChangeCandidates = new ArrayList<Committee>();
        protocolChangeCandidates = new ArrayList<Protocol>();
        scheduleChangeCandidates = new ArrayList<CommitteeSchedule>();
        unitAdministratorChangeCandidates = new ArrayList<UnitAdministrator>();
    }
    
    public String getEmptyCandidatesMessage() {
        return EMPTY_CANDIDATES_MESSAGE;
    }

    public List<Award> getAwardChangeCandidates() {
        return awardChangeCandidates;
    }

    public void setAwardChangeCandidates(List<Award> awardChangeCandidates) {
        this.awardChangeCandidates = awardChangeCandidates;
    }
    
    public List<IacucProtocol> getIacucProtocolChangeCandidates() {
        return iacucProtocolChangeCandidates;
    }

    public void setIacucProtocolChangeCandidates(List<IacucProtocol> iacucProtocolChangeCandidates) {
        this.iacucProtocolChangeCandidates = iacucProtocolChangeCandidates;
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

    public List<UnitAdministrator> getUnitAdministratorChangeCandidates() {
        return unitAdministratorChangeCandidates;
    }

    public void setUnitAdministratorChangeCandidates(List<UnitAdministrator> unitAdministratorChangeCandidates) {
        this.unitAdministratorChangeCandidates = unitAdministratorChangeCandidates;
    }
    
    /**
     * Prepares the fields to render the view.
     */
    public void prepareView() {
        PersonMassChange personMassChange = form.getPersonMassChangeDocument().getPersonMassChange();

        prepareReplaceeView(personMassChange, personMassChange.getReplaceePersonId(), personMassChange.getReplaceeRolodexId());
        prepareReplacerView(personMassChange, personMassChange.getReplacerPersonId(), personMassChange.getReplacerRolodexId());
        
        setAwardChangeCandidates(getAwardPersonMassChangeService().getAwardChangeCandidates(personMassChange));
        setIacucProtocolChangeCandidates(getIacucProtocolPersonMassChangeService().getIacucProtocolChangeCandidates(personMassChange));
        setInstitutionalProposalChangeCandidates(getInstitutionalProposalPersonMassChangeService().getInstitutionalProposalChangeCandidates(personMassChange));
        setProposalDevelopmentChangeCandidates(getProposalDevelopmentPersonMassChangeService().getProposalDevelopmentChangeCandidates(personMassChange));
        setProposalLogChangeCandidates(getProposalLogPersonMassChangeService().getProposalLogChangeCandidates(personMassChange));
        setSubawardChangeCandidates(getSubawardPersonMassChangeService().getSubawardChangeCandidates(personMassChange));
        setNegotiationChangeCandidates(getNegotiationPersonMassChangeService().getNegotiationChangeCandidates(personMassChange));
        setProtocolChangeCandidates(getProtocolPersonMassChangeService().getProtocolChangeCandidates(personMassChange));
        setUnitAdministratorChangeCandidates(getUnitAdministratorPersonMassChangeService().getUnitAdministratorChangeCandidates(personMassChange));
    }
    
    public AwardPersonMassChangeService getAwardPersonMassChangeService() {
        if (awardPersonMassChangeService == null) {
            awardPersonMassChangeService = KcServiceLocator.getService(AwardPersonMassChangeService.class);
        }
        return awardPersonMassChangeService;
    }

    public void setAwardPersonMassChangeService(AwardPersonMassChangeService awardPersonMassChangeService) {
        this.awardPersonMassChangeService = awardPersonMassChangeService;
    }
    
    public IacucProtocolPersonMassChangeService getIacucProtocolPersonMassChangeService() {
        if (iacucProtocolPersonMassChangeService == null) {
            iacucProtocolPersonMassChangeService = KcServiceLocator.getService(IacucProtocolPersonMassChangeService.class);
        }
        return iacucProtocolPersonMassChangeService;
    }

    public void setIacucProtocolPersonMassChangeService(IacucProtocolPersonMassChangeService iacucProtocolPersonMassChangeService) {
        this.iacucProtocolPersonMassChangeService = iacucProtocolPersonMassChangeService;
    }

    public InstitutionalProposalPersonMassChangeService getInstitutionalProposalPersonMassChangeService() {
        if (institutionalProposalPersonMassChangeService == null) {
            institutionalProposalPersonMassChangeService = KcServiceLocator.getService(InstitutionalProposalPersonMassChangeService.class);
        }
        return institutionalProposalPersonMassChangeService;
    }

    public void setInstitutionalProposalPersonMassChangeService(InstitutionalProposalPersonMassChangeService institutionalProposalPersonMassChangeService) {
        this.institutionalProposalPersonMassChangeService = institutionalProposalPersonMassChangeService;
    }

    public ProposalDevelopmentPersonMassChangeService getProposalDevelopmentPersonMassChangeService() {
        if (proposalDevelopmentPersonMassChangeService == null) {
            proposalDevelopmentPersonMassChangeService = KcServiceLocator.getService(ProposalDevelopmentPersonMassChangeService.class);
        }
        return proposalDevelopmentPersonMassChangeService;
    }

    public void setProposalDevelopmentPersonMassChangeService(ProposalDevelopmentPersonMassChangeService proposalDevelopmentPersonMassChangeService) {
        this.proposalDevelopmentPersonMassChangeService = proposalDevelopmentPersonMassChangeService;
    }

    public ProposalLogPersonMassChangeService getProposalLogPersonMassChangeService() {
        if (proposalLogPersonMassChangeService == null) {
            proposalLogPersonMassChangeService = KcServiceLocator.getService(ProposalLogPersonMassChangeService.class);
        }
        return proposalLogPersonMassChangeService;
    }

    public void setProposalLogPersonMassChangeService(ProposalLogPersonMassChangeService proposalLogPersonMassChangeService) {
        this.proposalLogPersonMassChangeService = proposalLogPersonMassChangeService;
    }

    public SubawardPersonMassChangeService getSubawardPersonMassChangeService() {
        if (subawardPersonMassChangeService == null) {
            subawardPersonMassChangeService = KcServiceLocator.getService(SubawardPersonMassChangeService.class);
        }
        return subawardPersonMassChangeService;
    }

    public void setSubawardPersonMassChangeService(SubawardPersonMassChangeService subawardPersonMassChangeService) {
        this.subawardPersonMassChangeService = subawardPersonMassChangeService;
    }

    public NegotiationPersonMassChangeService getNegotiationPersonMassChangeService() {
        if (negotiationPersonMassChangeService == null) {
            negotiationPersonMassChangeService = KcServiceLocator.getService(NegotiationPersonMassChangeService.class);
        }
        return negotiationPersonMassChangeService;
    }

    public void setNegotiationPersonMassChangeService(NegotiationPersonMassChangeService negotiationPersonMassChangeService) {
        this.negotiationPersonMassChangeService = negotiationPersonMassChangeService;
    }

    public ProtocolPersonMassChangeService getProtocolPersonMassChangeService() {
        if (protocolPersonMassChangeService == null) {
            protocolPersonMassChangeService = KcServiceLocator.getService(ProtocolPersonMassChangeService.class);
        }
        return protocolPersonMassChangeService;
    }

    public void setProtocolPersonMassChangeService(ProtocolPersonMassChangeService protocolPersonMassChangeService) {
        this.protocolPersonMassChangeService = protocolPersonMassChangeService;
    }

    public UnitAdministratorPersonMassChangeService getUnitAdministratorPersonMassChangeService() {
        if (unitAdministratorPersonMassChangeService == null) {
            unitAdministratorPersonMassChangeService = KcServiceLocator.getService(UnitAdministratorPersonMassChangeService.class);
        }
        return unitAdministratorPersonMassChangeService;
    }

    public void setUnitAdministratorPersonMassChangeService(UnitAdministratorPersonMassChangeService unitAdministratorPersonMassChangeService) {
        this.unitAdministratorPersonMassChangeService = unitAdministratorPersonMassChangeService;
    }

}