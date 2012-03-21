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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
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
 * Person roles that might be replaced are: Investigator, Unit Contact, Mailing Information, IP Reviewer.
 */
public class InstitutionalProposalPersonMassChangeServiceImpl implements InstitutionalProposalPersonMassChangeService {
    
    private static final String PMC_LOCKED_FIELD = "personMassChangeDocumentLocked";
    
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";

    private static final String INSTITUTIONAL_PROPOSAL = "institutional proposal";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;

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
            institutionalProposalChangeCandidate.getInstitutionalProposalDocument().refreshPessimisticLocks();
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
        
        List<InstitutionalProposalPerson> persons = institutionalProposal.getProjectPersons();
        Integer mailingInformationId = institutionalProposal.getRolodexId();
        List<InstitutionalProposalUnitContact> unitContacts = institutionalProposal.getInstitutionalProposalUnitContacts();
        IntellectualPropertyReview intellectualPropertyReview = institutionalProposal.getIntellectualPropertyReview();
        
        String[] investigatorRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
        String[] keyStudyPersonRoles = { ContactRole.KEY_PERSON_CODE };
        
        if (personMassChange.getInstitutionalProposalPersonMassChange().isInvestigator()) {
            isInstitutionalProposalChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getInstitutionalProposalPersonMassChange().isKeyStudyPerson()) {
            isInstitutionalProposalChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
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

        return isInstitutionalProposalChangeCandidate;
    }
    
    private boolean isPersonChangeCandidate(PersonMassChange personMassChange, List<InstitutionalProposalPerson> persons, String... personRoles) {
        boolean isPersonChangeCandidate = false;
        
        for (InstitutionalProposalPerson person : persons) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    isPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isPersonChangeCandidate;
    }
    
    private boolean isPersonInRole(InstitutionalProposalPerson person, String... personRoles) {
        boolean isPersonInRole = false;
        
        for (String personRole : personRoles) {
            if (StringUtils.equals(person.getRoleCode(), personRole)) {
                isPersonInRole = true;
                break;
            }
        }
        
        return isPersonInRole;
    }
    
    private boolean isMailingInformationChangeCandidate(PersonMassChange personMassChange, Integer mailingInformation) {
        return isRolodexIdMassChange(personMassChange, mailingInformation);
    }
    
    private boolean isUnitContactChangeCandidate(PersonMassChange personMassChange, List<InstitutionalProposalUnitContact> unitContacts) {
        boolean isUnitContactChangeCandidate = false;
        
        for (InstitutionalProposalUnitContact unitContact : unitContacts) {
            if (isPersonIdMassChange(personMassChange, unitContact.getPersonId()) || isRolodexIdMassChange(personMassChange, unitContact.getRolodexId())) {
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
            institutionalProposalChangeCandidate.getInstitutionalProposalDocument().refreshPessimisticLocks();
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
                if (personMassChange.getReplacerPersonId() != null) {
                    KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    person.setPersonId(kcPerson.getPersonId());
                    person.setFullName(kcPerson.getFullName());
                    person.setRolodexId(null);
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    Rolodex rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                    person.setPersonId(null);
                    person.setRolodexId(rolodex.getRolodexId());
                    person.setFullName(rolodex.getFullName());
                }

                getBusinessObjectService().save(person);
            }
        }
    }

    private void performMailingInformationPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isMailingInformation()) {
            Rolodex rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
            institutionalProposal.setRolodexId(rolodex.getRolodexId());
            getBusinessObjectService().save(institutionalProposal);
        }
    }
    
    private void performUnitContactPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isUnitContact()) {
            for (InstitutionalProposalUnitContact unitContact : institutionalProposal.getInstitutionalProposalUnitContacts()) {
                KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                unitContact.setPersonId(kcPerson.getPersonId());
                unitContact.setFullName(kcPerson.getFullName());

                getBusinessObjectService().save(unitContact);
            }
        }
    }
    
    private void performIpReviewerPersonMassChange(PersonMassChange personMassChange, InstitutionalProposal institutionalProposal) {
        if (personMassChange.getInstitutionalProposalPersonMassChange().isIpReviewer()) {
            KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            institutionalProposal.getIntellectualPropertyReview().setIpReviewer(kcPerson.getPersonId());
            getBusinessObjectService().save(institutionalProposal);
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
    
    private void reportSoftError(InstitutionalProposal institutionalProposal) {
        String proposalNumber = institutionalProposal.getProposalNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, INSTITUTIONAL_PROPOSAL, proposalNumber);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public RolodexService getRolodexService() {
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
    
}