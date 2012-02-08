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
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProtocolPersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.PersonEditableService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Protocols.
 */
public class ProtocolPersonMassChangeServiceImpl implements ProtocolPersonMassChangeService {

    private static final String PROTOCOL_FIELD = "document.personMassChange.protocolPersonMassChange.";

    private static final String PROTOCOL_ID = "protocolId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String INVESTIGATOR = "investigator";
    private static final String KEY_STUDY_PERSON = "keyStudyPerson";
    private static final String CORRESPONDENTS = "correspondents";
    private static final String REVIEWER = "reviewer";
    
    private static final String PROTOCOL_INVESTIGATOR_FIELD = PROTOCOL_FIELD + INVESTIGATOR;
    private static final String PROTOCOL_KEY_STUDY_PERSON_FIELD = PROTOCOL_FIELD + KEY_STUDY_PERSON;
    private static final String PROTOCOL_CORRESPONDENTS_FIELD = PROTOCOL_FIELD + CORRESPONDENTS;
    private static final String PROTOCOL_REVIEWER_FIELD = PROTOCOL_FIELD + REVIEWER;
    
    private static final String PROTOCOL = "protocol";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private PersonEditableService personEditableService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    
    @Override
    public List<Protocol> getProtocolChangeCandidates(PersonMassChange personMassChange) {
        Set<Protocol> protocolChangeCandidates = new HashSet<Protocol>();
        
        List<Protocol> protocols = new ArrayList<Protocol>();
        if (personMassChange.getProtocolPersonMassChange().isInvestigator() || personMassChange.getProtocolPersonMassChange().isKeyStudyPerson() 
                || personMassChange.getProtocolPersonMassChange().isCorrespondents() || personMassChange.getProtocolPersonMassChange().isReviewer()) {
            protocols.addAll(getProtocols(personMassChange));
        }

        for (Protocol protocol : protocols) {
            if (isProtocolChangeCandidate(personMassChange, protocol)) {
                protocolChangeCandidates.add(protocol);
            }
        }
        
        return new ArrayList<Protocol>(protocolChangeCandidates);
    }
    
    private List<Protocol> getProtocols(PersonMassChange personMassChange) {
        List<Protocol> protocolChangeCandidates = new ArrayList<Protocol>();
        
        Collection<Protocol> protocols = getBusinessObjectService().findAll(Protocol.class);

        if (personMassChange.isChangeAllSequences()) {
            protocolChangeCandidates.addAll(protocols);
        } else {
            protocolChangeCandidates.addAll(getLatestProtocols(protocols));
        }
        
        return protocolChangeCandidates;
    }
    
    private List<Protocol> getLatestProtocols(Collection<Protocol> protocols) {
        List<Protocol> latestProtocols = new ArrayList<Protocol>();
        
        for (String uniqueProtocolId : getUniqueProtocolIds(protocols)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PROTOCOL_ID, uniqueProtocolId);
            Collection<Protocol> uniqueProtocols = getBusinessObjectService().findMatchingOrderBy(Protocol.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueProtocols.isEmpty()) {
                latestProtocols.add((Protocol) CollectionUtils.get(uniqueProtocols, 0));
            }
        }
        
        return latestProtocols;
    }
    
    private Set<String> getUniqueProtocolIds(Collection<Protocol> protocols) {
        Set<String> uniqueProtocolIds = new HashSet<String>();
        
        for (Protocol protocol : protocols) {
            uniqueProtocolIds.add(String.valueOf(protocol.getProtocolId()));
        }
        
        return uniqueProtocolIds;
        
    }
    
    private boolean isProtocolChangeCandidate(PersonMassChange personMassChange, Protocol protocol) {
        boolean isProtocolChangeCandidate = false;
        
        List<ProtocolPerson> protocolPersons = protocol.getProtocolPersons();
        List<ProtocolOnlineReview> protocolOnlineReviews = protocol.getProtocolOnlineReviews();
        
        String[] investigatorRoles = { ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, ProtocolPersonRole.ROLE_CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { ProtocolPersonRole.ROLE_STUDY_PERSONNEL };
        String[] correspondentsRoles = { ProtocolPersonRole.ROLE_CORRESPONDENT_CRC, ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR };
        
        if (personMassChange.getProtocolPersonMassChange().isInvestigator()) {
            isProtocolChangeCandidate |= isProtocolPersonChangeCandidate(personMassChange, protocolPersons, investigatorRoles);
        }
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            isProtocolChangeCandidate |= isProtocolPersonChangeCandidate(personMassChange, protocolPersons, keyStudyPersonRoles);
        }
        if (personMassChange.getProtocolPersonMassChange().isCorrespondents()) {
            isProtocolChangeCandidate |= isProtocolPersonChangeCandidate(personMassChange, protocolPersons, correspondentsRoles);
        }
        if (personMassChange.getProtocolPersonMassChange().isReviewer()) {
            isProtocolChangeCandidate |= isProtocolReviewerChangeCandidate(personMassChange, protocolOnlineReviews);
        }
        
        return isProtocolChangeCandidate;
    }
    
    private boolean isProtocolPersonChangeCandidate(PersonMassChange personMassChange, List<ProtocolPerson> protocolPersons, String... protocolPersonRoles) {
        boolean isProtocolPersonChangeCandidate = false;
        
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if (isProtocolPersonInRole(protocolPerson, protocolPersonRoles)) {
                if (isPersonIdMassChange(personMassChange, protocolPerson) || isRolodexIdMassChange(personMassChange, protocolPerson)) {
                    isProtocolPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isProtocolPersonChangeCandidate;
    }
    
    private boolean isProtocolPersonInRole(ProtocolPerson protocolPerson, String... protocolPersonRoles) {
        boolean isProtocolPersonInRole = false;
        
        for (String protocolPersonRole : protocolPersonRoles) {
            if (StringUtils.equals(protocolPerson.getProtocolPersonRoleId(), protocolPersonRole)) {
                isProtocolPersonInRole = true;
                break;
            }
        }
        
        return isProtocolPersonInRole;
    }
    
    private boolean isProtocolReviewerChangeCandidate(PersonMassChange personMassChange, List<ProtocolOnlineReview> protocolOnlineReviews) {
        boolean isProtocolReviewerChangeCandidate = false;
        
        for (ProtocolOnlineReview protocolOnlineReview : protocolOnlineReviews) {
            ProtocolReviewer protocolReviewer = protocolOnlineReview.getProtocolReviewer();
            if (isPersonIdMassChange(personMassChange, protocolReviewer) || isRolodexIdMassChange(personMassChange, protocolReviewer)) {
                isProtocolReviewerChangeCandidate = true;
                break;
            }
        }
        
        return isProtocolReviewerChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Protocol> protocolChangeCandidates) {
        for (Protocol protocolChangeCandidate : protocolChangeCandidates) {
            if (protocolChangeCandidate.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                performProtocolInvestigatorPersonMassChange(personMassChange, protocolChangeCandidate);
                performProtocolKeyStudyPersonPersonMassChange(personMassChange, protocolChangeCandidate);
                performProtocolCorrespondentsPersonMassChange(personMassChange, protocolChangeCandidate);
                performProtocolReviewerPersonMassChange(personMassChange, protocolChangeCandidate);
            } else {
                if (personMassChange.getProtocolPersonMassChange().isInvestigator()) {
                    reportWarning(PROTOCOL_INVESTIGATOR_FIELD, protocolChangeCandidate);
                } else if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
                    reportWarning(PROTOCOL_KEY_STUDY_PERSON_FIELD, protocolChangeCandidate);
                } else if (personMassChange.getProtocolPersonMassChange().isCorrespondents()) {
                    reportWarning(PROTOCOL_CORRESPONDENTS_FIELD, protocolChangeCandidate);
                } else if (personMassChange.getProtocolPersonMassChange().isReviewer()) {
                    reportWarning(PROTOCOL_REVIEWER_FIELD, protocolChangeCandidate);
                }
            }
        }
    }
    
    private void reportWarning(String propertyName, Protocol protocolChangeCandidate) {
        String protocolNumber = protocolChangeCandidate.getProtocolNumber();
        errorReporter.reportWarning(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, PROTOCOL, protocolNumber);
    }
    
    private void performProtocolInvestigatorPersonMassChange(PersonMassChange personMassChange, Protocol protocolChangeCandidate) {
        if (personMassChange.getProtocolPersonMassChange().isInvestigator()) {
            String[] protocolPersonRoles = { ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, ProtocolPersonRole.ROLE_CO_INVESTIGATOR };
            performProtocolPersonPersonMassChange(personMassChange, protocolChangeCandidate, protocolPersonRoles);
        }
    }
    
    private void performProtocolKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, Protocol protocolChangeCandidate) {
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            String[] protocolPersonRoles = { ProtocolPersonRole.ROLE_STUDY_PERSONNEL };
            performProtocolPersonPersonMassChange(personMassChange, protocolChangeCandidate, protocolPersonRoles);
        }
    }
    
    private void performProtocolCorrespondentsPersonMassChange(PersonMassChange personMassChange, Protocol protocolChangeCandidate) {
        if (personMassChange.getProtocolPersonMassChange().isCorrespondents()) {
            String[] protocolPersonRoles = { ProtocolPersonRole.ROLE_CORRESPONDENT_CRC, ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR };
            performProtocolPersonPersonMassChange(personMassChange, protocolChangeCandidate, protocolPersonRoles);
        }
    }

    private void performProtocolPersonPersonMassChange(PersonMassChange personMassChange, Protocol protocolChangeCandidate, String... protocolPersonRoles) {
        for (ProtocolPerson protocolPerson : protocolChangeCandidate.getProtocolPersons()) {
            if (isProtocolPersonInRole(protocolPerson, protocolPersonRoles)) {
                if (personMassChange.getReplacerPersonId() != null) {
                    getPersonEditableService().populateContactFieldsFromPersonId(protocolPerson);
                    getProtocolPersonTrainingService().setTrainedFlag(protocolPerson);
                    protocolPerson.setRolodexId(null);
                    
                    for (ProtocolUnit protocolUnit : protocolPerson.getProtocolUnits()) {
                        protocolUnit.setPersonId(personMassChange.getReplacerPersonId());
                    }
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    getPersonEditableService().populateContactFieldsFromRolodexId(protocolPerson);
                    getProtocolPersonTrainingService().setTrainedFlag(protocolPerson);
                    protocolPerson.setPersonId(null);
                    
                    for (ProtocolUnit protocolUnit : protocolPerson.getProtocolUnits()) {
                        protocolUnit.setPersonId(null);
                    }
                }

                getBusinessObjectService().save(protocolPerson);
            }
        }
    }

    private void performProtocolReviewerPersonMassChange(PersonMassChange personMassChange, Protocol protocolChangeCandidate) {
        if (personMassChange.getProtocolPersonMassChange().isReviewer()) {
            for (ProtocolOnlineReview protocolOnlineReview : protocolChangeCandidate.getProtocolOnlineReviews()) {
                ProtocolReviewer protocolReviewer = protocolOnlineReview.getProtocolReviewer();
                if (personMassChange.getReplacerPersonId() != null) {
                    KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                    protocolReviewer.setPersonId(person.getPersonId());
                    protocolReviewer.setRolodexId(null);
                    protocolReviewer.setNonEmployeeFlag(false);
                } else if (personMassChange.getReplacerRolodexId() != null) {
                    Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
                    protocolReviewer.setRolodexId(rolodex.getRolodexId());
                    protocolReviewer.setPersonId(null);
                    protocolReviewer.setNonEmployeeFlag(true);
                }
            
                getBusinessObjectService().save(protocolReviewer);
            }
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, ProtocolPerson protocolPerson) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, protocolPerson.getPersonId());
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, ProtocolPerson protocolPerson) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(protocolPerson.getRolodexId()));
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, ProtocolReviewer protocolReviewer) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, protocolReviewer.getPersonId());
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, ProtocolReviewer protocolReviewer) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(protocolReviewer.getRolodexId()));
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

    public ProtocolPersonTrainingService getProtocolPersonTrainingService() {
        return protocolPersonTrainingService;
    }

    public void setProtocolPersonTrainingService(ProtocolPersonTrainingService protocolPersonTrainingService) {
        this.protocolPersonTrainingService = protocolPersonTrainingService;
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