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
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.AwardPersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Awards.
 */
public class AwardPersonMassChangeServiceImpl implements AwardPersonMassChangeService {
    
    private static final String AWARD_FIELD = "document.personMassChange.awardPersonMassChange.";
    
    private static final String AWARD_ID = "awardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String INVESTIGATOR = "investigator";
    private static final String KEY_STUDY_PERSON = "keyStudyPerson";
    private static final String CONTACT_PERSON = "contactPerson";
    private static final String FOREIGN_TRIP = "foreignTrip";
    private static final String UNIT_ADMINISTRATOR = "unitAdministrator";
    
    private static final String AWARD_INVESTIGATOR_FIELD = AWARD_FIELD + INVESTIGATOR;
    private static final String AWARD_KEY_STUDY_PERSON_FIELD = AWARD_FIELD + KEY_STUDY_PERSON;
    private static final String AWARD_CONTACT_PERSON_FIELD = AWARD_FIELD + CONTACT_PERSON;
    private static final String AWARD_FOREIGN_TRIP_FIELD = AWARD_FIELD + FOREIGN_TRIP;
    private static final String AWARD_UNIT_ADMINISTRATOR_FIELD = AWARD_FIELD + UNIT_ADMINISTRATOR;
    
    private static final String AWARD = "award";
    
    private final ErrorReporter errorReporter = new ErrorReporter();

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    
    @Override
    public List<Award> getAwardChangeCandidates(PersonMassChange personMassChange) {
        List<Award> awardChangeCandidates = new ArrayList<Award>();
        
        List<Award> awards = new ArrayList<Award>();
        if (personMassChange.getAwardPersonMassChange().isInvestigator() || personMassChange.getAwardPersonMassChange().isKeyStudyPerson() 
                || personMassChange.getAwardPersonMassChange().isContactPerson() || personMassChange.getAwardPersonMassChange().isForeignTrip() 
                || personMassChange.getAwardPersonMassChange().isUnitAdministrator()) {
            awards.addAll(getAwards(personMassChange));
        }

        for (Award award : awards) {
            if (isAwardChangeCandidate(personMassChange, award)) {
                awardChangeCandidates.add(award);
            }
        }
        
        return awardChangeCandidates;
    }
    
    private List<Award> getAwards(PersonMassChange personMassChange) {
        List<Award> awardChangeCandidates = new ArrayList<Award>();
        
        Collection<Award> awards = getBusinessObjectService().findAll(Award.class);

        if (personMassChange.isChangeAllSequences()) {
            awardChangeCandidates.addAll(awards);
        } else {
            awardChangeCandidates.addAll(getLatestAwards(awards));
        }
        
        return awardChangeCandidates;
    }
    
    private List<Award> getLatestAwards(Collection<Award> awards) {
        List<Award> latestAwards = new ArrayList<Award>();
        
        for (String uniqueAwardId : getUniqueAwardIds(awards)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(AWARD_ID, uniqueAwardId);
            Collection<Award> uniqueAwards = getBusinessObjectService().findMatchingOrderBy(Award.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueAwards.isEmpty()) {
                latestAwards.add((Award) CollectionUtils.get(uniqueAwards, 0));
            }
        }
        
        return latestAwards;
    }
    
    private Set<String> getUniqueAwardIds(Collection<Award> awards) {
        Set<String> uniqueAwardIds = new HashSet<String>();
        
        for (Award award : awards) {
            uniqueAwardIds.add(String.valueOf(award.getAwardId()));
        }
        
        return uniqueAwardIds;
    }
    
    private boolean isAwardChangeCandidate(PersonMassChange personMassChange, Award award) {
        boolean isAwardChangeCandidate = false;
        
        List<AwardPerson> awardPersons = award.getProjectPersons();
        List<AwardSponsorContact> awardSponsorContacts = award.getSponsorContacts();
        List<AwardApprovedForeignTravel> awardApprovedForeignTravels = award.getApprovedForeignTravelTrips();
        List<AwardUnitContact> awardUnitContacts = award.getAwardUnitContacts();
        
        String[] investigatorRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
        String[] keyStudyPersonRoles = { ContactRole.KEY_PERSON_CODE };
        
        if (personMassChange.getAwardPersonMassChange().isInvestigator()) {
            isAwardChangeCandidate |= isAwardPersonChangeCandidate(personMassChange, awardPersons, investigatorRoles);
        }
        if (personMassChange.getAwardPersonMassChange().isKeyStudyPerson()) {
            isAwardChangeCandidate |= isAwardPersonChangeCandidate(personMassChange, awardPersons, keyStudyPersonRoles);
        }
        if (personMassChange.getAwardPersonMassChange().isContactPerson()) {
            isAwardChangeCandidate |= isAwardContactPersonChangeCandidate(personMassChange, awardSponsorContacts);
        }
        if (personMassChange.getAwardPersonMassChange().isForeignTrip()) {
            isAwardChangeCandidate |= isAwardForeignTripChangeCandidate(personMassChange, awardApprovedForeignTravels);
        }
        if (personMassChange.getAwardPersonMassChange().isUnitAdministrator()) {
            isAwardChangeCandidate |= isAwardUnitAdministratorChangeCandidate(personMassChange, awardUnitContacts);
        }
        
        return isAwardChangeCandidate;
    }
    
    private boolean isAwardPersonChangeCandidate(PersonMassChange personMassChange, List<AwardPerson> awardPersons, String... personRoles) {
        boolean isAwardPersonChangeCandidate = false;
        
        for (AwardPerson awardPerson : awardPersons) {
            if (isAwardPersonInRole(awardPerson, personRoles)) {
                if (isPersonIdMassChange(personMassChange, awardPerson.getPersonId()) 
                        || isRolodexIdMassChange(personMassChange, awardPerson.getRolodexId())) {
                    isAwardPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isAwardPersonChangeCandidate;
    }
    
    private boolean isAwardPersonInRole(AwardPerson awardPerson, String... personRoles) {
        boolean isAwardPersonInRole = false;
        
        for (String awardPersonRole : personRoles) {
            if (StringUtils.equals(awardPerson.getRoleCode(), awardPersonRole)) {
                isAwardPersonInRole = true;
                break;
            }
        }
        
        return isAwardPersonInRole;
    }
    
    private boolean isAwardContactPersonChangeCandidate(PersonMassChange personMassChange, List<AwardSponsorContact> awardSponsorContacts) {
        boolean isAwardContactPersonChangeCandidate = false;
        
        for (AwardSponsorContact awardSponsorContact : awardSponsorContacts) {
            if (isRolodexIdMassChange(personMassChange, awardSponsorContact.getRolodexId())) {
                isAwardContactPersonChangeCandidate = true;
                break;
            }
        }
        
        return isAwardContactPersonChangeCandidate;
    }
    
    private boolean isAwardForeignTripChangeCandidate(PersonMassChange personMassChange, List<AwardApprovedForeignTravel> awardApprovedForeignTravels) {
        boolean isAwardForeignTripChangeCandidate = false;
        
        for (AwardApprovedForeignTravel awardApprovedForeignTravel : awardApprovedForeignTravels) {
            if (isPersonIdMassChange(personMassChange, awardApprovedForeignTravel.getPersonId()) 
                    || isRolodexIdMassChange(personMassChange, awardApprovedForeignTravel.getRolodexId())) {
                isAwardForeignTripChangeCandidate = true;
                break;
            }
        }
        
        return isAwardForeignTripChangeCandidate;
    }
    
    private boolean isAwardUnitAdministratorChangeCandidate(PersonMassChange personMassChange, List<AwardUnitContact> awardUnitContacts) {
        boolean isAwardUnitAdministratorChangeCandidate = false;
        
        for (AwardUnitContact awardUnitContact : awardUnitContacts) {
            if (isPersonIdMassChange(personMassChange, awardUnitContact.getPersonId())) {
                isAwardUnitAdministratorChangeCandidate = true;
                break;
            }
        }
        
        return isAwardUnitAdministratorChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Award> awardChangeCandidates) {
        for (Award awardChangeCandidate : awardChangeCandidates) {
            awardChangeCandidate.getAwardDocument().refreshPessimisticLocks();
            if (awardChangeCandidate.getAwardDocument().getPessimisticLocks().isEmpty()) {
                performAwardInvestigatorPersonMassChange(personMassChange, awardChangeCandidate);
                performAwardKeyStudyPersonPersonMassChange(personMassChange, awardChangeCandidate);
                performAwardContactPersonPersonMassChange(personMassChange, awardChangeCandidate);
                performAwardForeignTripPersonMassChange(personMassChange, awardChangeCandidate);
                performAwardUnitAdministratorPersonMassChange(personMassChange, awardChangeCandidate);
            } else {
                if (personMassChange.getAwardPersonMassChange().isInvestigator()) {
                    reportWarning(AWARD_INVESTIGATOR_FIELD, awardChangeCandidate);
                } else if (personMassChange.getAwardPersonMassChange().isKeyStudyPerson()) {
                    reportWarning(AWARD_KEY_STUDY_PERSON_FIELD, awardChangeCandidate);
                } else if (personMassChange.getAwardPersonMassChange().isContactPerson()) {
                    reportWarning(AWARD_CONTACT_PERSON_FIELD, awardChangeCandidate);
                } else if (personMassChange.getAwardPersonMassChange().isForeignTrip()) {
                    reportWarning(AWARD_FOREIGN_TRIP_FIELD, awardChangeCandidate);
                } else if (personMassChange.getAwardPersonMassChange().isUnitAdministrator()) {
                    reportWarning(AWARD_UNIT_ADMINISTRATOR_FIELD, awardChangeCandidate);
                }
            }
        }
    }
    
    private void reportWarning(String propertyName, Award award) {
        String awardNumber = award.getAwardNumber();
        errorReporter.reportWarning(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, AWARD, awardNumber);
    }
    
    private void performAwardInvestigatorPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isInvestigator()) {
            String[] personRoles = { ContactRole.PI_CODE, ContactRole.COI_CODE };
            performAwardPersonPersonMassChange(personMassChange, award, personRoles);
        }
    }
    
    private void performAwardKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { ContactRole.KEY_PERSON_CODE };
            performAwardPersonPersonMassChange(personMassChange, award, personRoles);
        }
    }
    
    private void performAwardPersonPersonMassChange(PersonMassChange personMassChange, Award award, String... personRoles) {
        for (AwardPerson awardPerson : award.getProjectPersons()) {
            if (isAwardPersonInRole(awardPerson, personRoles)) {
                if (personMassChange.getReplacerPersonId() != null) {
                    KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    awardPerson.setPersonId(person.getPersonId());
                    awardPerson.setFullName(person.getFullName());
                    awardPerson.setRolodexId(null);
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
                    awardPerson.setPersonId(null);
                    awardPerson.setRolodexId(rolodex.getRolodexId());
                    awardPerson.setFullName(rolodex.getFullName());
                }

                getBusinessObjectService().save(awardPerson);
            }
        }
    }
    
    private void performAwardContactPersonPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isContactPerson()) {
            for (AwardSponsorContact awardSponsorContact : award.getSponsorContacts()) {
                awardSponsorContact.setRolodexId(Integer.parseInt(personMassChange.getReplacerRolodexId()));

                getBusinessObjectService().save(awardSponsorContact);
            }
        }
    }
    
    private void performAwardForeignTripPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isForeignTrip()) {
            for (AwardApprovedForeignTravel awardApprovedForeignTravel : award.getApprovedForeignTravelTrips()) {
                if (personMassChange.getReplacerPersonId() != null) {
                    KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    awardApprovedForeignTravel.setPersonId(person.getPersonId());
                    awardApprovedForeignTravel.setTravelerName(person.getFullName());
                    awardApprovedForeignTravel.setRolodexId(null);
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
                    awardApprovedForeignTravel.setPersonId(null);
                    awardApprovedForeignTravel.setRolodexId(rolodex.getRolodexId());
                    awardApprovedForeignTravel.setTravelerName(rolodex.getFullName());
                }

                getBusinessObjectService().save(awardApprovedForeignTravel);
            }
        }
    }
    
    private void performAwardUnitAdministratorPersonMassChange(PersonMassChange personMassChange, Award award) {
        if (personMassChange.getAwardPersonMassChange().isUnitAdministrator()) {
            for (AwardUnitContact awardUnitContact : award.getAwardUnitContacts()) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                awardUnitContact.setPersonId(person.getPersonId());
                awardUnitContact.setFullName(person.getFullName());

                getBusinessObjectService().save(awardUnitContact);
            }
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, personId);
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, Integer rolodexId) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(rolodexId));
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