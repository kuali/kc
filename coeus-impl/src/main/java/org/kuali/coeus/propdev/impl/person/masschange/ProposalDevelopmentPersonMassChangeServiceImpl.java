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
package org.kuali.coeus.propdev.impl.person.masschange;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.coeus.propdev.impl.person.masschange.ProposalDevelopmentPersonMassChangeService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.kra.personmasschange.service.impl.MassPersonChangeServiceBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the service for performing a Person Mass Change on Proposal Developments.
 * 
 * Person roles that might be replaced are: Investigator, Mailing Information, Key Study Person.
 */
@Component("proposalDevelopmentPersonMassChangeService")
public class ProposalDevelopmentPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements ProposalDevelopmentPersonMassChangeService {

    private static final String DEVELOPMENT_PROPOSAL = "development proposal";
    private static final String DEVPROP_WARNINGS = "devPropWarnings";

    @Autowired
    @Qualifier("personEditableService")
    private PersonEditableService personEditableService;
    
    @Override
    public List<DevelopmentProposal> getProposalDevelopmentChangeCandidates(PersonMassChange personMassChange) {
        List<DevelopmentProposal> proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
        
        List<DevelopmentProposal> developmentProposals = new ArrayList<DevelopmentProposal>();
        if (personMassChange.getProposalDevelopmentPersonMassChange().requiresChange()) {
            developmentProposals.addAll(getDevelopmentProposals());
        }

        for (DevelopmentProposal developmentProposal : developmentProposals) {
            if (isProposalDevelopmentChangeCandidate(personMassChange, developmentProposal)) {
                proposalDevelopmentChangeCandidates.add(developmentProposal);
            }
        }
        
        for (DevelopmentProposal proposalDevelopmentChangeCandidate : proposalDevelopmentChangeCandidates) {
            if (!proposalDevelopmentChangeCandidate.getProposalDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(proposalDevelopmentChangeCandidate);
            }
        }
        
        return proposalDevelopmentChangeCandidates;
    }
    
    private List<DevelopmentProposal> getDevelopmentProposals() {
        return new ArrayList<DevelopmentProposal>(getDataObjectService().findAll(DevelopmentProposal.class).getResults());
    }
    
    private boolean isProposalDevelopmentChangeCandidate(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        boolean isProposalDevelopmentChangeCandidate = false;
        boolean hasErrors = false;
        
        List<ProposalPerson> persons = developmentProposal.getProposalPersons();
        Integer mailingInformationId = developmentProposal.getMailingAddressId();
        
        String[] investigatorRoles = { PropAwardPersonRole.PRINCIPAL_INVESTIGATOR, PropAwardPersonRole.CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { PropAwardPersonRole.KEY_PERSON };
        
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            isProposalDevelopmentChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isKeyStudyPerson()) {
            isProposalDevelopmentChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        //if the award is a change candidate based on the investigators and key persons,
        //then make sure the replacer user doesn't already exist.
        if (isProposalDevelopmentChangeCandidate) {
            hasErrors |= !isReplacerValidPersonChangeCandidate(personMassChange, persons);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
            isProposalDevelopmentChangeCandidate |= isMailingInformationChangeCandidate(personMassChange, mailingInformationId);
        }        
        
        return isProposalDevelopmentChangeCandidate && !hasErrors;
    }
    
    private boolean isMailingInformationChangeCandidate(PersonMassChange personMassChange, Integer mailingInformationId) {
        return isRolodexIdMassChange(personMassChange, mailingInformationId);
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<DevelopmentProposal> proposalDevelopmentChangeCandidates) {
        for (DevelopmentProposal proposalDevelopmentChangeCandidate : proposalDevelopmentChangeCandidates) {
            if (proposalDevelopmentChangeCandidate.getProposalDocument().getPessimisticLocks().isEmpty()) {
                performProposalInvestigatorPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalMailingInfoPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalKeyStudyPersonPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
            }
        }        
    }
    
    private void performProposalInvestigatorPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            String[] personRoles = { PropAwardPersonRole.PRINCIPAL_INVESTIGATOR, PropAwardPersonRole.CO_INVESTIGATOR };
            performPersonPersonMassChange(personMassChange, developmentProposal, personRoles);
        }
    }
    
    private void performProposalMailingInfoPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
            developmentProposal.setMailingAddressId(personMassChange.getReplacerRolodexId());
            
            getBusinessObjectService().save(developmentProposal);
        }
    }
    
    private void performProposalKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { PropAwardPersonRole.KEY_PERSON };
            performPersonPersonMassChange(personMassChange, developmentProposal, personRoles);
        }
    }
    
    private void performPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal, String... personRoles) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        person.setPersonId(personMassChange.getReplacerPersonId());
                        person.setRolodexId(null);
                        getPersonEditableService().populateContactFieldsFromPersonId(person);
                        
                        for (ProposalPersonBiography biography : developmentProposal.getPropPersonBios()) {
                            if (ObjectUtils.equals(biography.getProposalPersonNumber(), person.getProposalPersonNumber())) {
                                biography.setPersonId(personMassChange.getReplacerPersonId());
                                biography.setRolodexId(null);
                                
                                getBusinessObjectService().save(biography);
                            }
                        }
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        person.setPersonId(null);
                        person.setRolodexId(personMassChange.getReplacerRolodexId());
                        getPersonEditableService().populateContactFieldsFromRolodexId(person);
                        
                        for (ProposalPersonBiography biography : developmentProposal.getPropPersonBios()) {
                            if (ObjectUtils.equals(biography.getProposalPersonNumber(), person.getProposalPersonNumber())) {
                                biography.setPersonId(null);
                                biography.setRolodexId(personMassChange.getReplacerRolodexId());
                            
                                getBusinessObjectService().save(biography);
                            }
                        }
                    }
    
                    getBusinessObjectService().save(person);
                }
            }
        }
    }
    
    private void reportSoftError(DevelopmentProposal developmentProposalChangeCandidate) {
        String proposalNumber = developmentProposalChangeCandidate.getProposalNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, DEVELOPMENT_PROPOSAL, proposalNumber);
    }
    
    public PersonEditableService getPersonEditableService() {
        return personEditableService;
    }

    public void setPersonEditableService(PersonEditableService personEditableService) {
        this.personEditableService = personEditableService;
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((DevelopmentProposal) parent).getProposalNumber();
    }

    @Override
    protected String getDocumentName() {
        return DEVELOPMENT_PROPOSAL;
    }

    @Override
    protected String getWarningKey() {
        return DEVPROP_WARNINGS;
    }

}
