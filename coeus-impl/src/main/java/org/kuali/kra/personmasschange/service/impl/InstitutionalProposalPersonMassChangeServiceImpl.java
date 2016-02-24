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

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.InstitutionalProposalPersonMassChangeService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Defines the service for performing a Person Mass Change on Institutional Proposals.
 * 
 * Person roles that might be replaced are: Investigator, Unit Contact, Mailing Information, IP Reviewer.
 */
@Component("institutionalProposalPersonMassChangeService")
public class InstitutionalProposalPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements InstitutionalProposalPersonMassChangeService {
    
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";

    private static final String INSTITUTIONAL_PROPOSAL = "institutional proposal";
    private static final String INSTPROP_WARNINGS = "instPropWarnings";
    

    @Override
    public List<InstitutionalProposal> getInstitutionalProposalChangeCandidates(PersonMassChange personMassChange) {
        List<InstitutionalProposal> institutionalProposalChangeCandidates = new ArrayList<InstitutionalProposal>();
        
        List<InstitutionalProposal> institutionalProposals = new ArrayList<InstitutionalProposal>();
        if (personMassChange.getInstitutionalProposalPersonMassChange().requiresChange()) {
            institutionalProposals.addAll(getInstitutionalProposals(personMassChange));
        }
        
        for (InstitutionalProposal institutionalProposal : institutionalProposals) {
            if (isInstitutionalProposalChangeCandidate(personMassChange, institutionalProposal)) {
                institutionalProposalChangeCandidates.add(institutionalProposal);
            }
        }
        
        for (InstitutionalProposal institutionalProposalChangeCandidate : institutionalProposalChangeCandidates) {
            if (!institutionalProposalChangeCandidate.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(institutionalProposalChangeCandidate);
            }
        }
        
        return institutionalProposalChangeCandidates;
    }
    
    private List<InstitutionalProposal> getInstitutionalProposals(PersonMassChange personMassChange) {
        List<InstitutionalProposal> institutionalProposals = new ArrayList<InstitutionalProposal>();
        
        Collection<InstitutionalProposal> allInstitutionalProposals = getBusinessObjectService().findAll(InstitutionalProposal.class);

        if (personMassChange.isChangeAllSequences()) {
            institutionalProposals.addAll(allInstitutionalProposals);
        } else {
            institutionalProposals.addAll(getLatestInstitutionalProposals(allInstitutionalProposals));
        }
        
        return institutionalProposals;
    }
    
    private List<InstitutionalProposal> getLatestInstitutionalProposals(Collection<InstitutionalProposal> institutionalProposals) {
        List<InstitutionalProposal> latestInstitutionalProposals = new ArrayList<InstitutionalProposal>();
        
        for (String uniqueInstitutionalProposalNumber : getUniqueInstitutionalProposalNumbers(institutionalProposals)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PROPOSAL_NUMBER, uniqueInstitutionalProposalNumber);
            Collection<InstitutionalProposal> uniqueInstitutionalProposals 
                = getBusinessObjectService().findMatchingOrderBy(InstitutionalProposal.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueInstitutionalProposals.isEmpty()) {
                latestInstitutionalProposals.add((InstitutionalProposal) CollectionUtils.get(uniqueInstitutionalProposals, 0));
            }
        }
        
        return latestInstitutionalProposals;
    }
    
    private Set<String> getUniqueInstitutionalProposalNumbers(Collection<InstitutionalProposal> institutionalProposals) {
        Set<String> uniqueAwardIds = new HashSet<String>();
        
        for (InstitutionalProposal institutionalProposal : institutionalProposals) {
            uniqueAwardIds.add(institutionalProposal.getProposalNumber());
        }
        
        return uniqueAwardIds;
    }
    
    private boolean isInstitutionalProposalChangeCandidate(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        boolean isInstitutionalProposalChangeCandidate = false;
        boolean hasErrors = false;
        
        List<InstitutionalProposalPerson> persons = institutionalProposal.getProjectPersons();
        Integer mailingInformationId = institutionalProposal.getRolodexId();
        List<InstitutionalProposalUnitContact> unitContacts = institutionalProposal.getInstitutionalProposalUnitContacts();
        IntellectualPropertyReview intellectualPropertyReview = institutionalProposal.getProposalIpReviewJoin().getIntellectualPropertyReview();
        
        String[] investigatorRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
        String[] keyStudyPersonRoles = { ContactRole.KEY_PERSON_CODE };
        
        if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
            isInstitutionalProposalChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
            isInstitutionalProposalChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        //if the award is a change candidate based on the investigators and key persons,
        //then make sure the replacer user doesn't already exist.
        if (isInstitutionalProposalChangeCandidate) {
            hasErrors |= !isReplacerValidPersonChangeCandidate(personMassChange, persons);
        }        
        if (personMassChange.getInstitutionalProposalPersonMassChange().isMailingInformation()) {
            isInstitutionalProposalChangeCandidate |= isMailingInformationChangeCandidate(personMassChange, mailingInformationId);
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
            isInstitutionalProposalChangeCandidate |= isUnitContactChangeCandidate(personMassChange, unitContacts);
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isIpReviewer()) {
            isInstitutionalProposalChangeCandidate |= isIpReviewerChangeCandidate(personMassChange, intellectualPropertyReview);
        }

        return isInstitutionalProposalChangeCandidate && !hasErrors;
    }
        
    private boolean isMailingInformationChangeCandidate(PersonMassChange personMassChange, Integer mailingInformation) {
        return isRolodexIdMassChange(personMassChange, mailingInformation);
    }
    
    private boolean isUnitContactChangeCandidate(PersonMassChange personMassChange, List<InstitutionalProposalUnitContact> unitContacts) {
        boolean isUnitContactChangeCandidate = false;
        
        for (InstitutionalProposalUnitContact unitContact : unitContacts) {
            if (isPersonIdMassChange(personMassChange, unitContact.getPersonId())) {
                isUnitContactChangeCandidate = true;
                break;
            }
        }

        return isUnitContactChangeCandidate;
    }

    private boolean isIpReviewerChangeCandidate(PersonMassChange personMassChange, IntellectualPropertyReview intellectualPropertyReview) {
        boolean isIpReviewerChangeCandidate = false;

        if (intellectualPropertyReview != null) {
            isIpReviewerChangeCandidate = isPersonIdMassChange(personMassChange, intellectualPropertyReview.getIpReviewer());
        }
        
        return isIpReviewerChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<InstitutionalProposal> institutionalProposalChangeCandidates) {
        for (InstitutionalProposal institutionalProposalChangeCandidate : institutionalProposalChangeCandidates) {
            if (institutionalProposalChangeCandidate.getInstitutionalProposalDocument().getPessimisticLocks().isEmpty()) {
                performInvestigatorPersonMassChange(personMassChange, institutionalProposalChangeCandidate);
                performKeyStudyPersonPersonMassChange(personMassChange, institutionalProposalChangeCandidate);
                performMailingInformationPersonMassChange(personMassChange, institutionalProposalChangeCandidate);
                performUnitContactPersonMassChange(personMassChange, institutionalProposalChangeCandidate);
                performIpReviewerPersonMassChange(personMassChange, institutionalProposalChangeCandidate);
            }
        }
    }
    
    private void performInvestigatorPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
            String[] personRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
            performPersonPersonMassChange(personMassChange, institutionalProposal, personRoles);
        }
    }
    
    private void performKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { ContactRole.KEY_PERSON_CODE };
            performPersonPersonMassChange(personMassChange, institutionalProposal, personRoles);
        }
    }
    
    private void performPersonPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal, String... personRoles) {
        for (InstitutionalProposalPerson person : institutionalProposal.getProjectPersons()) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        person.setPersonId(personMassChange.getReplacerPersonId());
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        person.setRolodexId(personMassChange.getReplacerRolodexId());
                    }
    
                    getBusinessObjectService().save(person);
                }
            }
        }
    }

    private void performMailingInformationPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isMailingInformation()) {
            RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
            institutionalProposal.setRolodexId(rolodex.getRolodexId());
            getBusinessObjectService().save(institutionalProposal);
        }
    }
    
    private void performUnitContactPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
            for (InstitutionalProposalUnitContact unitContact : institutionalProposal.getInstitutionalProposalUnitContacts()) {
                if (isPersonIdMassChange(personMassChange, unitContact.getPersonId())) {
                    KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    unitContact.setPersonId(kcPerson.getPersonId());
                    unitContact.setFullName(kcPerson.getFullName());
    
                    getBusinessObjectService().save(unitContact);
                }
            }
        }
    }
    
    private void performIpReviewerPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isIpReviewer()) {
            IntellectualPropertyReview intellectualPropertyReview = institutionalProposal.getProposalIpReviewJoin().getIntellectualPropertyReview();
            if (intellectualPropertyReview != null) {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                intellectualPropertyReview.setIpReviewer(kcPerson.getPersonId());
                getBusinessObjectService().save(intellectualPropertyReview);
            }
        }
    }
    
    private void reportSoftError(InstitutionalProposal institutionalProposal) {
        String proposalNumber = institutionalProposal.getProposalNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, INSTITUTIONAL_PROPOSAL, proposalNumber);
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((InstitutionalProposal) parent).getProposalNumber();
    }

    @Override
    protected String getDocumentName() {
        return INSTITUTIONAL_PROPOSAL;
    }

    @Override
    protected String getWarningKey() {
        return INSTPROP_WARNINGS;
    }
    
}
