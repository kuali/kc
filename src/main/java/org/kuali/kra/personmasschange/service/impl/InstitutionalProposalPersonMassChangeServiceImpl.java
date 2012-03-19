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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.InstitutionalProposalPersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Institutional Proposals.
 * 
 * Person roles that might be replaced are: Investigator, Unit Administrator, Mailing Information, IP Reviewer
 */
public class InstitutionalProposalPersonMassChangeServiceImpl implements InstitutionalProposalPersonMassChangeService {
    
    private static final String PMC_LOCKED_FIELD = "personMassChangeDocumentLocked";

    protected static final String PERSON_ID = "personId";
    protected static final String ROLODEX_ID = "rolodexId";
    protected static final String IP_REVIEWER = "ipReviewer";
    protected static final String INSTITUTIONAL_PROPOSAL = "institutionalProposal";
    
    protected final ErrorReporter errorReporter = new ErrorReporter();
    
    protected BusinessObjectService boService;
    protected KcPersonService kcPersonService;
    protected RolodexService rolodexService;

    @Override
    public List<InstitutionalProposal> getInstitutionalProposalChangeCandidates(PersonMassChange personMassChange) {
        Set<InstitutionalProposal> proposalChangeCandidates = new HashSet<InstitutionalProposal>();
        if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
            List<InstitutionalProposalPerson> proposalPeople = getInvestigatorsToReplace(personMassChange);
            for (InstitutionalProposalPerson proposalPerson : proposalPeople) {
                if (personMassChange.isChangeAllSequences() || proposalPerson.getInstitutionalProposal().isActiveVersion()) {
                    proposalChangeCandidates.add(proposalPerson.getInstitutionalProposal());
                }
            }
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
            List<InstitutionalProposalPerson> proposalPeople = getKeyStudyPersonsToReplace(personMassChange);
            for (InstitutionalProposalPerson proposalPerson : proposalPeople) {
                if (personMassChange.isChangeAllSequences() || proposalPerson.getInstitutionalProposal().isActiveVersion()) {
                    proposalChangeCandidates.add(proposalPerson.getInstitutionalProposal());
                }
            }
        }

        if (personMassChange.getInstitutionalProposalPersonMassChange().isMailingInformation()) {
            List<InstitutionalProposal> mailingInfoProposals = getMailingInfoProposalsToReplace(personMassChange);
            for (InstitutionalProposal proposal : mailingInfoProposals) {
                if (personMassChange.isChangeAllSequences() || proposal.isActiveVersion()) {
                    proposalChangeCandidates.add(proposal);
                }
            }
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
            List<InstitutionalProposalUnitContact> unitContacts = getUnitContactsToReplace(personMassChange);
            for (InstitutionalProposalUnitContact unitContact : unitContacts) {
                if (personMassChange.isChangeAllSequences() || unitContact.getInstitutionalProposal().isActiveVersion()) {
                    proposalChangeCandidates.add(unitContact.getInstitutionalProposal());
                }
            }
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isIpReviewer()) {
            List<IntellectualPropertyReview> ipReviews = getIpReviewersToReplace(personMassChange);
            for (IntellectualPropertyReview ipReview : ipReviews) {
                if (personMassChange.isChangeAllSequences() || ipReview.getInstitutionalProposal().isActiveVersion()) {
                    proposalChangeCandidates.add(ipReview.getInstitutionalProposal());
                }
            }
        }
        
        for (InstitutionalProposal proposalChangeCandidate : proposalChangeCandidates) {
            proposalChangeCandidate.getInstitutionalProposalDocument().refreshPessimisticLocks();
            if (!proposalChangeCandidate.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(proposalChangeCandidate);
            }
        }
        
        return new ArrayList<InstitutionalProposal>(proposalChangeCandidates);
    }

    @Override
    // There's really no good way to do this prettily...
    public void performPersonMassChange(PersonMassChange personMassChange, List<InstitutionalProposal> institutionalProposalChangeCandidates) {
        if (!StringUtils.isBlank(personMassChange.getReplacerPersonId())) {
            KcPerson replacerPerson = kcPersonService.getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            for (InstitutionalProposal institutionalProposal : institutionalProposalChangeCandidates) {
                institutionalProposal.getInstitutionalProposalDocument().refreshPessimisticLocks();
                if (institutionalProposal.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
                        replaceInvestigatorWithPerson(personMassChange, institutionalProposal, replacerPerson);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
                        replaceKeyStudyPersonWithPerson(personMassChange, institutionalProposal, replacerPerson);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
                        replaceUnitContactWithPerson(personMassChange, institutionalProposal, replacerPerson);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isIpReviewer()) {
                        replaceIpReviewerWithPerson(personMassChange, institutionalProposal, replacerPerson);
                    }
                }
            }
        } else {
            NonOrganizationalRolodex replacerRolodex = (NonOrganizationalRolodex) rolodexService.getRolodex(
                    Integer.parseInt(personMassChange.getReplacerRolodexId()));
            for (InstitutionalProposal institutionalProposal : institutionalProposalChangeCandidates) {
                institutionalProposal.getInstitutionalProposalDocument().refreshPessimisticLocks();
                if (institutionalProposal.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
                        replaceInvestigatorWithRolodex(personMassChange, institutionalProposal, replacerRolodex);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
                        replaceKeyStudyPersonWithRolodex(personMassChange, institutionalProposal, replacerRolodex);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
                        replaceUnitContactWithRolodex(personMassChange, institutionalProposal, replacerRolodex);
                    }
                    if (personMassChange.getInstitutionalProposalPersonMassChange().isMailingInformation()) {
                        replaceMailingInfoWithRolodex(personMassChange, institutionalProposal, replacerRolodex);
                    }
                }
            }
        }
    }
    
    protected List<InstitutionalProposalPerson> getInvestigatorsToReplace(PersonMassChange personMassChange) {
        List<InstitutionalProposalPerson> proposalPeople = new ArrayList<InstitutionalProposalPerson>();
        Map<String, String> personCriteria = new HashMap<String, String>();
        if (!StringUtils.isBlank(personMassChange.getReplaceePersonId())) {
            personCriteria.put(PERSON_ID, personMassChange.getReplaceePersonId());
        } else if (!(StringUtils.isBlank(personMassChange.getReplaceeRolodexId()))) {
            personCriteria.put(ROLODEX_ID, personMassChange.getReplaceeRolodexId());
        } else {
            return proposalPeople;
        }
        proposalPeople = (List<InstitutionalProposalPerson>) boService.findMatching(InstitutionalProposalPerson.class, personCriteria);
        List<InstitutionalProposalPerson> investigators = new ArrayList<InstitutionalProposalPerson>();
        for (InstitutionalProposalPerson proposalPerson : proposalPeople) {
            if (proposalPerson.isCoInvestigator() || proposalPerson.isPrincipalInvestigator()) {
                investigators.add(proposalPerson);
            }
        }
        return investigators;
    }
    
    protected List<InstitutionalProposalPerson> getKeyStudyPersonsToReplace(PersonMassChange personMassChange) {
        List<InstitutionalProposalPerson> proposalPeople = new ArrayList<InstitutionalProposalPerson>();
        Map<String, String> personCriteria = new HashMap<String, String>();
        if (!StringUtils.isBlank(personMassChange.getReplaceePersonId())) {
            personCriteria.put(PERSON_ID, personMassChange.getReplaceePersonId());
        } else if (!(StringUtils.isBlank(personMassChange.getReplaceeRolodexId()))) {
            personCriteria.put(ROLODEX_ID, personMassChange.getReplaceeRolodexId());
        } else {
            return proposalPeople;
        }
        proposalPeople = (List<InstitutionalProposalPerson>) boService.findMatching(InstitutionalProposalPerson.class, personCriteria);
        List<InstitutionalProposalPerson> keyPersons = new ArrayList<InstitutionalProposalPerson>();
        for (InstitutionalProposalPerson proposalPerson : proposalPeople) {
            if (proposalPerson.isKeyPerson()) {
                keyPersons.add(proposalPerson);
            }
        }
        return keyPersons;
    }
    
    // Mailing Information
    protected List<InstitutionalProposal> getMailingInfoProposalsToReplace(PersonMassChange personMassChange) {
        Map<String, String> mailingInfoCriteria = new HashMap<String, String>();
        List<InstitutionalProposal> proposals = new ArrayList<InstitutionalProposal>();
        if (!StringUtils.isBlank(personMassChange.getReplaceeRolodexId())) {
            mailingInfoCriteria.put(ROLODEX_ID, personMassChange.getReplaceeRolodexId());
        } else {
            return proposals;
        }
        proposals = (List<InstitutionalProposal>) boService.findMatching(InstitutionalProposal.class, mailingInfoCriteria);
        return proposals;
    }
    
    // Unit Contact
    protected List<InstitutionalProposalUnitContact> getUnitContactsToReplace(PersonMassChange personMassChange) {
        List<InstitutionalProposalUnitContact> unitContacts = new ArrayList<InstitutionalProposalUnitContact>();
        Map<String, String> unitContactCriteria = new HashMap<String, String>();
        if (!StringUtils.isBlank(personMassChange.getReplaceePersonId())) {
            unitContactCriteria.put(PERSON_ID, personMassChange.getReplaceePersonId());
        } else if (!(StringUtils.isBlank(personMassChange.getReplaceeRolodexId()))) {
            unitContactCriteria.put(ROLODEX_ID, personMassChange.getReplaceeRolodexId());
        } else {
            return unitContacts;
        }
        unitContacts = (List<InstitutionalProposalUnitContact>) boService.findMatching(InstitutionalProposalUnitContact.class, unitContactCriteria);
        return unitContacts;
    }
    
    // IP Reviewer
    protected List<IntellectualPropertyReview> getIpReviewersToReplace(PersonMassChange personMassChange) {
        List<IntellectualPropertyReview> ipReviews = new ArrayList<IntellectualPropertyReview>();
        Map<String, String> ipReviewCriteria = new HashMap<String, String>();
        if (!StringUtils.isBlank(personMassChange.getReplaceePersonId())) {
            ipReviewCriteria.put(IP_REVIEWER, personMassChange.getReplaceePersonId());
        } else {
            return ipReviews;
        }
        ipReviews = (List<IntellectualPropertyReview>) boService.findMatching(IntellectualPropertyReview.class, ipReviewCriteria);
        return ipReviews;
    }
    
    protected void replaceInvestigatorWithPerson(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, KcPerson replacerPerson) {
        for (InstitutionalProposalPerson projectPerson : institutionalProposal.getProjectPersons()) {
            if (projectPerson.isPrincipalInvestigator() || projectPerson.isCoInvestigator()) {
                if (!StringUtils.isBlank(projectPerson.getPersonId()) && projectPerson.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    projectPerson.setPerson(replacerPerson);
                    boService.save(projectPerson);
                } else if (projectPerson.getRolodexId() != null && projectPerson.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    projectPerson.setPerson(replacerPerson);
                    boService.save(projectPerson);
                }
            }
        }
    }
    
    protected void replaceInvestigatorWithRolodex(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, NonOrganizationalRolodex replacerRolodex) {
        for (InstitutionalProposalPerson projectPerson : institutionalProposal.getProjectPersons()) {
            if (projectPerson.isPrincipalInvestigator() || projectPerson.isCoInvestigator()) {
                if (!StringUtils.isBlank(projectPerson.getPersonId()) && projectPerson.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    projectPerson.setRolodex(replacerRolodex);
                    boService.save(projectPerson);
                } else if (projectPerson.getRolodexId() != null && projectPerson.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    projectPerson.setRolodex(replacerRolodex);
                    boService.save(projectPerson);
                }
            }
        }
    }
    
    protected void replaceKeyStudyPersonWithPerson(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, KcPerson replacerPerson) {
        for (InstitutionalProposalPerson projectPerson : institutionalProposal.getProjectPersons()) {
            if (projectPerson.isPrincipalInvestigator() || projectPerson.isCoInvestigator()) {
                if (!StringUtils.isBlank(projectPerson.getPersonId()) && projectPerson.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    projectPerson.setPerson(replacerPerson);
                    boService.save(projectPerson);
                } else if (projectPerson.getRolodexId() != null && projectPerson.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    projectPerson.setPerson(replacerPerson);
                    boService.save(projectPerson);
                }
            }
        }
    }
    
    protected void replaceKeyStudyPersonWithRolodex(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, NonOrganizationalRolodex replacerRolodex) {
        for (InstitutionalProposalPerson projectPerson : institutionalProposal.getProjectPersons()) {
            if (projectPerson.isKeyPerson()) {
                if (!StringUtils.isBlank(projectPerson.getPersonId()) && projectPerson.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    projectPerson.setRolodex(replacerRolodex);
                    boService.save(projectPerson);
                } else if (projectPerson.getRolodexId() != null && projectPerson.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    projectPerson.setRolodex(replacerRolodex);
                    boService.save(projectPerson);
                }
            }
        }
    }

    protected void replaceMailingInfoWithRolodex(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, NonOrganizationalRolodex replacerRolodex) {
        Integer mailingInfoId = institutionalProposal.getRolodexId();
        if (mailingInfoId != null && mailingInfoId.equals(replacerRolodex.getRolodexId())) {
            institutionalProposal.setRolodexId(replacerRolodex.getRolodexId());
            institutionalProposal.setRolodex(replacerRolodex);
            boService.save(institutionalProposal);
        }
    }
    
    protected void replaceUnitContactWithPerson(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, KcPerson replacerPerson) {
        for (InstitutionalProposalUnitContact unitContact : institutionalProposal.getInstitutionalProposalUnitContacts()) {
            if (unitContact.getUnitAdministratorType() != null) {
                if (!StringUtils.isBlank(unitContact.getPersonId()) && unitContact.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    unitContact.setPerson(replacerPerson);
                    boService.save(unitContact);
                } else if (unitContact.getRolodexId() != null && unitContact.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    unitContact.setPerson(replacerPerson);
                    boService.save(unitContact);
                }
            }
        }
    }
    
    protected void replaceUnitContactWithRolodex(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, NonOrganizationalRolodex replacerRolodex) {
        for (InstitutionalProposalUnitContact unitContact : institutionalProposal.getInstitutionalProposalUnitContacts()) {
            if (unitContact.getUnitAdministratorType() != null) {
                if (!StringUtils.isBlank(unitContact.getPersonId()) && unitContact.getPersonId().equals(personMassChange.getReplaceePersonId())) {
                    unitContact.setRolodex(replacerRolodex);
                    boService.save(unitContact);
                } else if (unitContact.getRolodexId() != null && unitContact.getRolodexId().equals(personMassChange.getReplaceeRolodexId())) {
                    unitContact.setRolodex(replacerRolodex);
                    boService.save(unitContact);
                }
            }
        }
    }
    
    protected void replaceIpReviewerWithPerson(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, KcPerson replacerPerson) {
        String ipReviewer = institutionalProposal.getIntellectualPropertyReview().getIpReviewer();
        if (!StringUtils.isBlank(ipReviewer) && ipReviewer.equals(replacerPerson.getPersonId())) {
            institutionalProposal.getIntellectualPropertyReview().setIpReviewer(replacerPerson.getPersonId());
            boService.save(institutionalProposal);
        }
    }
    
    private void reportSoftError(InstitutionalProposal institutionalProposal) {
        String proposalNumber = institutionalProposal.getProposalNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, INSTITUTIONAL_PROPOSAL, proposalNumber);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.boService = businessObjectService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
    
}