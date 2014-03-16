/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.personmasschange.service.impl;

import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.*;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.subaward.bo.SubAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Defines the service for performing a Person Mass Change.
 */
public class PersonMassChangeServiceImpl implements PersonMassChangeService {
    
	@Autowired
	@Qualifier("awardPersonMassChangeService")
    private AwardPersonMassChangeService awardPersonMassChangeService;

    private IacucProtocolPersonMassChangeService iacucProtocolPersonMassChangeService;
    
	@Autowired
	@Qualifier("institutionalProposalPersonMassChangeService")
    private InstitutionalProposalPersonMassChangeService institutionalProposalPersonMassChangeService;

	private ProposalDevelopmentPersonMassChangeService proposalDevelopmentPersonMassChangeService;
	
	@Autowired
	@Qualifier("proposalLogPersonMassChangeService")
    private ProposalLogPersonMassChangeService proposalLogPersonMassChangeService;
	
	@Autowired
	@Qualifier("subawardPersonMassChangeService")
    private SubawardPersonMassChangeService subawardPersonMassChangeService;
	
	@Autowired
	@Qualifier("negotiationPersonMassChangeService")
    private NegotiationPersonMassChangeService negotiationPersonMassChangeService;

    private ProtocolPersonMassChangeService protocolPersonMassChangeService;
	
    @Autowired
	@Qualifier("unitAdministratorPersonMassChangeService")
    private UnitAdministratorPersonMassChangeService unitAdministratorPersonMassChangeService;

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange) {
        List<Award> awardChangeCandidates = getAwardPersonMassChangeService().getAwardChangeCandidates(personMassChange);
        getAwardPersonMassChangeService().performPersonMassChange(personMassChange, awardChangeCandidates);
        
        List<IacucProtocol> iacucProtocolChangeCandidates = getIacucProtocolPersonMassChangeService().getIacucProtocolChangeCandidates(personMassChange);
        getIacucProtocolPersonMassChangeService().performPersonMassChange(personMassChange, iacucProtocolChangeCandidates);
        
        List<InstitutionalProposal> institutionalProposalChangeCandidates 
            = getInstitutionalProposalPersonMassChangeService().getInstitutionalProposalChangeCandidates(personMassChange);
        getInstitutionalProposalPersonMassChangeService().performPersonMassChange(personMassChange, institutionalProposalChangeCandidates);
        
        List<DevelopmentProposal> proposalDevelopmentChangeCandidates 
            = getProposalDevelopmentPersonMassChangeService().getProposalDevelopmentChangeCandidates(personMassChange);
        getProposalDevelopmentPersonMassChangeService().performPersonMassChange(personMassChange, proposalDevelopmentChangeCandidates);
        
        List<ProposalLog> proposalLogChangeCandidates = getProposalLogPersonMassChangeService().getProposalLogChangeCandidates(personMassChange);
        getProposalLogPersonMassChangeService().performPersonMassChange(personMassChange, proposalLogChangeCandidates);
        
        List<SubAward> subawardChangeCandidates = getSubawardPersonMassChangeService().getSubawardChangeCandidates(personMassChange);
        getSubawardPersonMassChangeService().performPersonMassChange(personMassChange, subawardChangeCandidates);
        
        List<Negotiation> negotiationChangeCandidates = getNegotiationPersonMassChangeService().getNegotiationChangeCandidates(personMassChange);
        getNegotiationPersonMassChangeService().performPersonMassChange(personMassChange, negotiationChangeCandidates);

        List<Protocol> protocolChangeCandidates = getProtocolPersonMassChangeService().getProtocolChangeCandidates(personMassChange);
        getProtocolPersonMassChangeService().performPersonMassChange(personMassChange, protocolChangeCandidates);

        List<UnitAdministrator> unitAdministratorChangeCandidates 
            = getUnitAdministratorPersonMassChangeService().getUnitAdministratorChangeCandidates(personMassChange);
        getUnitAdministratorPersonMassChangeService().performPersonMassChange(personMassChange, unitAdministratorChangeCandidates);
    }

    public AwardPersonMassChangeService getAwardPersonMassChangeService() {
        return awardPersonMassChangeService;
    }

    public void setAwardPersonMassChangeService(AwardPersonMassChangeService awardPersonMassChangeService) {
        this.awardPersonMassChangeService = awardPersonMassChangeService;
    }
    
    public IacucProtocolPersonMassChangeService getIacucProtocolPersonMassChangeService() {
        return iacucProtocolPersonMassChangeService;
    }

    public void setIacucProtocolPersonMassChangeService(IacucProtocolPersonMassChangeService iacucProtocolPersonMassChangeService) {
        this.iacucProtocolPersonMassChangeService = iacucProtocolPersonMassChangeService;
    }

    public InstitutionalProposalPersonMassChangeService getInstitutionalProposalPersonMassChangeService() {
        return institutionalProposalPersonMassChangeService;
    }

    public void setInstitutionalProposalPersonMassChangeService(InstitutionalProposalPersonMassChangeService institutionalProposalPersonMassChangeService) {
        this.institutionalProposalPersonMassChangeService = institutionalProposalPersonMassChangeService;
    }

    public ProposalDevelopmentPersonMassChangeService getProposalDevelopmentPersonMassChangeService() {
        return proposalDevelopmentPersonMassChangeService;
    }

    public void setProposalDevelopmentPersonMassChangeService(ProposalDevelopmentPersonMassChangeService proposalDevelopmentPersonMassChangeService) {
        this.proposalDevelopmentPersonMassChangeService = proposalDevelopmentPersonMassChangeService;
    }

    public ProposalLogPersonMassChangeService getProposalLogPersonMassChangeService() {
        return proposalLogPersonMassChangeService;
    }

    public void setProposalLogPersonMassChangeService(ProposalLogPersonMassChangeService proposalLogPersonMassChangeService) {
        this.proposalLogPersonMassChangeService = proposalLogPersonMassChangeService;
    }

    public SubawardPersonMassChangeService getSubawardPersonMassChangeService() {
        return subawardPersonMassChangeService;
    }

    public void setSubawardPersonMassChangeService(SubawardPersonMassChangeService subawardPersonMassChangeService) {
        this.subawardPersonMassChangeService = subawardPersonMassChangeService;
    }

    public NegotiationPersonMassChangeService getNegotiationPersonMassChangeService() {
        return negotiationPersonMassChangeService;
    }

    public void setNegotiationPersonMassChangeService(NegotiationPersonMassChangeService negotiationPersonMassChangeService) {
        this.negotiationPersonMassChangeService = negotiationPersonMassChangeService;
    }

    public ProtocolPersonMassChangeService getProtocolPersonMassChangeService() {
        return protocolPersonMassChangeService;
    }

    public void setProtocolPersonMassChangeService(ProtocolPersonMassChangeService protocolPersonMassChangeService) {
        this.protocolPersonMassChangeService = protocolPersonMassChangeService;
    }

    public UnitAdministratorPersonMassChangeService getUnitAdministratorPersonMassChangeService() {
        return unitAdministratorPersonMassChangeService;
    }

    public void setUnitAdministratorPersonMassChangeService(UnitAdministratorPersonMassChangeService unitAdministratorPersonMassChangeService) {
        this.unitAdministratorPersonMassChangeService = unitAdministratorPersonMassChangeService;
    }

}