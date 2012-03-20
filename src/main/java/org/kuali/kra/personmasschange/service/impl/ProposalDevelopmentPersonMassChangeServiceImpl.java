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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProposalDevelopmentPersonMassChangeService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Proposal Developments.
 * 
 * Person roles that might be replaced are: Investigator, Mailing Information, Key Study Person.
 */
public class ProposalDevelopmentPersonMassChangeServiceImpl implements ProposalDevelopmentPersonMassChangeService {

    private static final String PMC_LOCKED_FIELD = "personMassChangeDocumentLocked";
    
    private static final String DEVELOPMENT_PROPOSAL = "development proposal";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
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
            proposalDevelopmentChangeCandidate.getProposalDocument().refreshPessimisticLocks();
            if (!proposalDevelopmentChangeCandidate.getProposalDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(proposalDevelopmentChangeCandidate);
            }
        }
        
        return proposalDevelopmentChangeCandidates;
    }
    
    private List<DevelopmentProposal> getDevelopmentProposals() {
        return new ArrayList<DevelopmentProposal>(getBusinessObjectService().findAll(DevelopmentProposal.class));
    }
    
    private boolean isProposalDevelopmentChangeCandidate(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        boolean isProposalDevelopmentChangeCandidate = false;
        
        List<ProposalPerson> persons = developmentProposal.getProposalPersons();
        Integer mailingInformationId = developmentProposal.getMailingAddressId();
        
        String[] investigatorRoles = { ProposalPersonRole.PRINCIPAL_INVESTIGATOR, ProposalPersonRole.CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { ProposalPersonRole.KEY_PERSON };
        
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            isProposalDevelopmentChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isMailingInformation()) {
            isProposalDevelopmentChangeCandidate |= isMailingInformationChangeCandidate(personMassChange, mailingInformationId);
        }
        if (personMassChange.getProposalDevelopmentPersonMassChange().isKeyStudyPerson()) {
            isProposalDevelopmentChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        
        return isProposalDevelopmentChangeCandidate;
    }
    
    private boolean isPersonChangeCandidate(PersonMassChange personMassChange, List<ProposalPerson> persons, String... personRoles) {
        boolean isPersonChangeCandidate = false;
        
        for (ProposalPerson person : persons) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    isPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isPersonChangeCandidate;
    }
    
    private boolean isPersonInRole(ProposalPerson proposalPerson, String... personRoles) {
        boolean isPersonInRole = false;
        
        for (String personRole : personRoles) {
            if (StringUtils.equals(proposalPerson.getProposalPersonRoleId(), personRole)) {
                isPersonInRole = true;
                break;
            }
        }
        
        return isPersonInRole;
    }
    
    private boolean isMailingInformationChangeCandidate(PersonMassChange personMassChange, Integer mailingInformationId) {
        return isRolodexIdMassChange(personMassChange, mailingInformationId);
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<DevelopmentProposal> proposalDevelopmentChangeCandidates) {
        for (DevelopmentProposal proposalDevelopmentChangeCandidate : proposalDevelopmentChangeCandidates) {
            proposalDevelopmentChangeCandidate.getProposalDocument().refreshPessimisticLocks();
            if (proposalDevelopmentChangeCandidate.getProposalDocument().getPessimisticLocks().isEmpty()) {
                performProposalInvestigatorPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalMailingInfoPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
                performProposalKeyStudyPersonPersonMassChange(personMassChange, proposalDevelopmentChangeCandidate);
            }
        }        
    }
    
    private void performProposalInvestigatorPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal) {
        if (personMassChange.getProposalDevelopmentPersonMassChange().isInvestigator()) {
            String[] personRoles = { ProposalPersonRole.PRINCIPAL_INVESTIGATOR, ProposalPersonRole.CO_INVESTIGATOR };
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
            String[] personRoles = { ProposalPersonRole.KEY_PERSON };
            performPersonPersonMassChange(personMassChange, developmentProposal, personRoles);
        }
    }
    
    private void performPersonPersonMassChange(PersonMassChange personMassChange, DevelopmentProposal developmentProposal, String... personRoles) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (isPersonInRole(person, personRoles)) {
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
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && replaceePersonId.equals(personId);
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, Integer rolodexId) {
        Integer replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && replaceeRolodexId.equals(rolodexId);
    }
    
    private void reportSoftError(DevelopmentProposal developmentProposalChangeCandidate) {
        String proposalNumber = developmentProposalChangeCandidate.getProposalNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, DEVELOPMENT_PROPOSAL, proposalNumber);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public PersonEditableService getPersonEditableService() {
        return personEditableService;
    }

    public void setPersonEditableService(PersonEditableService personEditableService) {
        this.personEditableService = personEditableService;
    }

}