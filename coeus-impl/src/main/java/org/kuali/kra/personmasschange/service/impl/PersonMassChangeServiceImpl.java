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
package org.kuali.kra.personmasschange.service.impl;

import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.propdev.impl.person.masschange.ProposalDevelopmentPersonMassChangeService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.*;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
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
