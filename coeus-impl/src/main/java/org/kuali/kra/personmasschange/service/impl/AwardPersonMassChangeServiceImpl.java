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
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.AwardPersonMassChangeService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Defines the service for performing a Person Mass Change on Awards.
 * 
 * Person roles that might be replaced are: Investigator, Unit Contact, Sponsor Contact, Approved Foreign Travel.
 */
@Component("awardPersonMassChangeService")
public class AwardPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements AwardPersonMassChangeService {
    
    public static final String AWARD_WARNINGS = "awardWarnings";
    
    private static final String AWARD_NUMBER = "awardNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private static final String AWARD = "award";
    
    @Override
    public List<Award> getAwardChangeCandidates(PersonMassChange personMassChange) {
        List<Award> awardChangeCandidates = new ArrayList<Award>();
        
        List<Award> awards = new ArrayList<Award>();
        if (personMassChange.getAwardPersonMassChange().requiresChange()) {
            awards.addAll(getAwards(personMassChange));
        }

        for (Award award : awards) {
            if (isAwardChangeCandidate(personMassChange, award)) {
                awardChangeCandidates.add(award);
            }
        }
        
        for (Award awardChangeCandidate : awardChangeCandidates) {
            if (!awardChangeCandidate.getAwardDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(awardChangeCandidate);
            }
            
        }
        
        return awardChangeCandidates;
    }
    
    private List<Award> getAwards(PersonMassChange personMassChange) {
        List<Award> awards = new ArrayList<Award>();
        
        Collection<Award> allAwards = getBusinessObjectService().findAll(Award.class);

        if (personMassChange.isChangeAllSequences()) {
            awards.addAll(allAwards);
        } else {
            awards.addAll(getLatestAwards(allAwards));
        }
        
        return awards;
    }
    
    private List<Award> getLatestAwards(Collection<Award> awards) {
        List<Award> latestAwards = new ArrayList<Award>();
        
        for (String uniqueAwardNumber : getUniqueAwardNumbers(awards)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(AWARD_NUMBER, uniqueAwardNumber);
            Collection<Award> uniqueAwards = getBusinessObjectService().findMatchingOrderBy(Award.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueAwards.isEmpty()) {
                latestAwards.add((Award) CollectionUtils.get(uniqueAwards, 0));
            }
        }
        
        return latestAwards;
    }
    
    private Set<String> getUniqueAwardNumbers(Collection<Award> awards) {
        Set<String> uniqueAwardIds = new HashSet<String>();
        
        for (Award award : awards) {
            uniqueAwardIds.add(award.getAwardNumber());
        }
        
        return uniqueAwardIds;
    }
    
    private boolean isAwardChangeCandidate(PersonMassChange personMassChange, Award award) {
        boolean isAwardChangeCandidate = false;
        boolean hasErrors = false;
        
        List<AwardPerson> persons = award.getProjectPersons();
        List<AwardSponsorContact> sponsorContacts = award.getSponsorContacts();
        List<AwardApprovedForeignTravel> approvedForeignTravels = award.getApprovedForeignTravelTrips();
        List<AwardUnitContact> unitContacts = award.getAwardUnitContacts();
        
        String[] investigatorRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
        String[] keyStudyPersonRoles = { ContactRole.KEY_PERSON_CODE };
        
        if (personMassChange.getAwardPersonMassChange().isInvestigator()) {
            isAwardChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getAwardPersonMassChange().isKeyStudyPerson()) {
            isAwardChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        //if the award is a change candidate based on the investigators and key persons,
        //then make sure the replacer user doesn't already exist.
        if (isAwardChangeCandidate) {
            hasErrors |= !isReplacerValidPersonChangeCandidate(personMassChange, persons);
        }
        if (personMassChange.getAwardPersonMassChange().isUnitContact()) {
            isAwardChangeCandidate |= isUnitContactChangeCandidate(personMassChange, unitContacts);
        }
        if (personMassChange.getAwardPersonMassChange().isSponsorContact()) {
            isAwardChangeCandidate |= isSponsorContactChangeCandidate(personMassChange, sponsorContacts);
        }
        if (personMassChange.getAwardPersonMassChange().isApprovedForeignTravel()) {
            isAwardChangeCandidate |= isApprovedForeignTravelChangeCandidate(personMassChange, approvedForeignTravels);
        }

        return isAwardChangeCandidate && !hasErrors;
    }
    


    private boolean isUnitContactChangeCandidate(PersonMassChange personMassChange, List<AwardUnitContact> unitContacts) {
        boolean isUnitContactChangeCandidate = false;
        
        for (AwardUnitContact unitContact : unitContacts) {
            if (isPersonIdMassChange(personMassChange, unitContact.getPersonId())) {
                isUnitContactChangeCandidate = true;
                break;
            }
        }
        
        return isUnitContactChangeCandidate;
    }
    
    private boolean isSponsorContactChangeCandidate(PersonMassChange personMassChange, List<AwardSponsorContact> sponsorContacts) {
        boolean isSponsorContactChangeCandidate = false;
        
        for (AwardSponsorContact sponsorContact : sponsorContacts) {
            if (isRolodexIdMassChange(personMassChange, sponsorContact.getRolodexId())) {
                isSponsorContactChangeCandidate = true;
                break;
            }
        }
        
        return isSponsorContactChangeCandidate;
    }
    
    private boolean isApprovedForeignTravelChangeCandidate(PersonMassChange personMassChange, List<AwardApprovedForeignTravel> approvedForeignTravels) {
        boolean isApprovedForeignTravelChangeCandidate = false;
        
        for (AwardApprovedForeignTravel approvedForeignTravel : approvedForeignTravels) {
            if (isPersonIdMassChange(personMassChange, approvedForeignTravel.getPersonId()) 
                    || isRolodexIdMassChange(personMassChange, approvedForeignTravel.getRolodexId())) {
                isApprovedForeignTravelChangeCandidate = true;
                break;
            }
        }
        
        return isApprovedForeignTravelChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Award> awardChangeCandidates) {
        for (Award awardChangeCandidate : awardChangeCandidates) {
            if (awardChangeCandidate.getAwardDocument().getPessimisticLocks().isEmpty()) {
                performInvestigatorPersonMassChange(personMassChange, awardChangeCandidate);
                performKeyStudyPersonPersonMassChange(personMassChange, awardChangeCandidate);
                performUnitContactPersonMassChange(personMassChange, awardChangeCandidate);
                performSponsorContactPersonMassChange(personMassChange, awardChangeCandidate);
                performApprovedForeignTravelPersonMassChange(personMassChange, awardChangeCandidate);
            }
        }
    }
    
    private void performInvestigatorPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isInvestigator()) {
            String[] personRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
            performPersonPersonMassChange(personMassChange, award, personRoles);
        }
    }
    
    private void performKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { ContactRole.KEY_PERSON_CODE };
            performPersonPersonMassChange(personMassChange, award, personRoles);
        }
    }
    
    private void performPersonPersonMassChange(PersonMassChange personMassChange, Award award, String... personRoles) {
        for (AwardPerson person : award.getProjectPersons()) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                        person.setPersonId(kcPerson.getPersonId());
                        person.setRolodexId(null);
                        person.setFullName(kcPerson.getFullName());
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                        person.setPersonId(null);
                        person.setRolodexId(rolodex.getRolodexId());
                        person.setFullName(rolodex.getFullName());
                    }
                    
    
                    getBusinessObjectService().save(person);
                }
            }
        }
    }

    private void performUnitContactPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isUnitContact()) {
            for (AwardUnitContact unitContact : award.getAwardUnitContacts()) {
                if (isPersonIdMassChange(personMassChange, unitContact.getPersonId())) {
                    KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    unitContact.setPersonId(kcPerson.getPersonId());
                    unitContact.setFullName(kcPerson.getFullName());
    
                    getBusinessObjectService().save(unitContact);
                }
            }
        }
    }
    
    private void performSponsorContactPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isSponsorContact()) {
            for (AwardSponsorContact sponsorContact : award.getSponsorContacts()) {
                if (isRolodexIdMassChange(personMassChange, sponsorContact.getRolodexId())) {
                    sponsorContact.setRolodexId(personMassChange.getReplacerRolodexId());
    
                    getBusinessObjectService().save(sponsorContact);
                }
            }
        }
    }
    
    private void performApprovedForeignTravelPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isApprovedForeignTravel()) {
            for (AwardApprovedForeignTravel approvedForeignTravel : award.getApprovedForeignTravelTrips()) {
                if (isPersonIdMassChange(personMassChange, approvedForeignTravel.getPersonId()) 
                        || isRolodexIdMassChange(personMassChange, approvedForeignTravel.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                        approvedForeignTravel.setPersonId(kcPerson.getPersonId());
                        approvedForeignTravel.setTravelerName(kcPerson.getFullName());
                        approvedForeignTravel.setRolodexId(null);
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                        approvedForeignTravel.setPersonId(null);
                        approvedForeignTravel.setRolodexId(rolodex.getRolodexId());
                        approvedForeignTravel.setTravelerName(rolodex.getFullName());
                    }
    
                    getBusinessObjectService().save(approvedForeignTravel);
                }
            }
        }
    }
    
    private void reportSoftError(Award award) {
        String awardNumber = award.getAwardNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, AWARD, awardNumber);
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((Award) parent).getAwardNumber();
    }

    @Override
    protected String getDocumentName() {
        return AWARD;
    }

    @Override
    protected String getWarningKey() {
        return AWARD_WARNINGS;
    }

}
