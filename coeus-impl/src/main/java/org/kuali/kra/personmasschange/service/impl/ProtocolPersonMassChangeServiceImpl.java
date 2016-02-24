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
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonRole;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.ProtocolPersonMassChangeService;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.util.*;

/**
 * Defines the service for performing a Person Mass Change on Protocols.
 * 
 * Person roles that might be replaced are: Investigator, Key Study Person, Correspondents, Reviewer.
 */
public class ProtocolPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements ProtocolPersonMassChangeService {

    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private static final String PROTOCOL = "protocol";
    private static final String IRB_WARNINGS = "irbWarnings";
    
    private PersonEditableService personEditableService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;    
    @Override
    public List<Protocol> getProtocolChangeCandidates(PersonMassChange personMassChange) {
        List<Protocol> protocolChangeCandidates = new ArrayList<Protocol>();
        
        List<Protocol> protocols = new ArrayList<Protocol>();
        if (personMassChange.getProtocolPersonMassChange().requiresChange()) {
            protocols.addAll(getProtocols(personMassChange));
        }

        for (Protocol protocol : protocols) {
            if (isProtocolChangeCandidate(personMassChange, protocol)) {
                protocolChangeCandidates.add(protocol);
            }
        }
        
        for (Protocol protocolChangeCandidate : protocolChangeCandidates) {
            if (!protocolChangeCandidate.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(protocolChangeCandidate);
            }
        }
        
        return protocolChangeCandidates;
    }
    
    private List<Protocol> getProtocols(PersonMassChange personMassChange) {
        List<Protocol> protocols = new ArrayList<Protocol>();
        
        Collection<Protocol> allProtocols = getBusinessObjectService().findAll(Protocol.class);

        if (personMassChange.isChangeAllSequences()) {
            protocols.addAll(allProtocols);
        } else {
            protocols.addAll(getLatestProtocols(allProtocols));
        }
        
        return protocols;
    }
    
    private List<Protocol> getLatestProtocols(Collection<Protocol> protocols) {
        List<Protocol> latestProtocols = new ArrayList<Protocol>();
        
        for (String uniqueProtocolNumber : getUniqueProtocolNumbers(protocols)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PROTOCOL_NUMBER, uniqueProtocolNumber);
            Collection<Protocol> uniqueProtocols = getBusinessObjectService().findMatchingOrderBy(Protocol.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueProtocols.isEmpty()) {
                latestProtocols.add((Protocol) CollectionUtils.get(uniqueProtocols, 0));
            }
        }
        
        return latestProtocols;
    }
    
    private Set<String> getUniqueProtocolNumbers(Collection<Protocol> protocols) {
        Set<String> uniqueProtocolIds = new HashSet<String>();
        
        for (Protocol protocol : protocols) {
            uniqueProtocolIds.add(protocol.getProtocolNumber());
        }
        
        return uniqueProtocolIds;
    }
    
    private boolean isProtocolChangeCandidate(PersonMassChange personMassChange, Protocol protocol) {
        boolean isProtocolChangeCandidate = false;
        boolean hasErrors = false;
        
        List<ProtocolPerson> persons = (List) protocol.getProtocolPersons();
        List<ProtocolOnlineReview> onlineReviews = (List) protocol.getProtocolOnlineReviews();
        
        String[] investigatorRoles = { ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, ProtocolPersonRole.ROLE_CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { ProtocolPersonRole.ROLE_STUDY_PERSONNEL };
        String[] correspondentsRoles = { ProtocolPersonRole.ROLE_CORRESPONDENT_CRC, ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR };
        
        if (personMassChange.getProtocolPersonMassChange().isInvestigator()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        if (personMassChange.getProtocolPersonMassChange().isCorrespondents()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, correspondentsRoles);
        }
        //if the protocol is a change candidate based on the investigators and key persons,
        //then make sure the replacer user doesn't already exist.
        if (isProtocolChangeCandidate) {
            hasErrors |= !isReplacerValidPersonChangeCandidate(personMassChange, persons);
        }        
        if (personMassChange.getProtocolPersonMassChange().isReviewer()) {
            isProtocolChangeCandidate |= isReviewerChangeCandidate(personMassChange, onlineReviews);
        }
        
        return isProtocolChangeCandidate && !hasErrors;
    }
    
    private boolean isReviewerChangeCandidate(PersonMassChange personMassChange, List<ProtocolOnlineReview> onlineReviews) {
        boolean isReviewerChangeCandidate = false;
        
        for (ProtocolOnlineReview onlineReview : onlineReviews) {
            ProtocolReviewer reviewer = (ProtocolReviewer) onlineReview.getProtocolReviewer();
            if (isPersonIdMassChange(personMassChange, reviewer.getPersonId()) || isRolodexIdMassChange(personMassChange, reviewer.getRolodexId())) {
                isReviewerChangeCandidate = true;
                break;
            }
        }
        
        return isReviewerChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Protocol> protocolChangeCandidates) {
        for (Protocol protocolChangeCandidate : protocolChangeCandidates) {
            if (protocolChangeCandidate.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                performInvestigatorPersonMassChange(personMassChange, protocolChangeCandidate);
                performKeyStudyPersonPersonMassChange(personMassChange, protocolChangeCandidate);
                performCorrespondentsPersonMassChange(personMassChange, protocolChangeCandidate);
                performReviewerPersonMassChange(personMassChange, protocolChangeCandidate);
            }
        }
    }
    
    private void performInvestigatorPersonMassChange(PersonMassChange personMassChange, Protocol protocol) {
        if (personMassChange.getProtocolPersonMassChange().isInvestigator()) {
            String[] personRoles = { ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, ProtocolPersonRole.ROLE_CO_INVESTIGATOR };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }
    
    private void performKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, Protocol protocol) {
        if (personMassChange.getProtocolPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { ProtocolPersonRole.ROLE_STUDY_PERSONNEL };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }
    
    private void performCorrespondentsPersonMassChange(PersonMassChange personMassChange, Protocol protocol) {
        if (personMassChange.getProtocolPersonMassChange().isCorrespondents()) {
            String[] personRoles = { ProtocolPersonRole.ROLE_CORRESPONDENT_CRC, ProtocolPersonRole.ROLE_CORRESPONDENT_ADMINISTRATOR };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }

    private void performPersonPersonMassChange(PersonMassChange personMassChange, Protocol protocol, String... personRoles) {
        for (ProtocolPersonBase person : protocol.getProtocolPersons()) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                        person.setPersonId(personMassChange.getReplacerPersonId());
                        person.setRolodexId(null);
                        person.setPersonName(kcPerson.getFullName());
                        getPersonEditableService().populateContactFieldsFromPersonId(person);
                        getProtocolPersonTrainingService().setTrainedFlag(person);
                        
                        for (ProtocolUnitBase unit : person.getProtocolUnits()) {
                            unit.setPersonId(personMassChange.getReplacerPersonId());
                        }
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                        person.setPersonId(null);
                        person.setRolodexId(rolodex.getRolodexId());
                        person.setPersonName(rolodex.getFullName());
                        getPersonEditableService().populateContactFieldsFromRolodexId(person);
                        getProtocolPersonTrainingService().setTrainedFlag(person);
                        
                        for (ProtocolUnitBase unit : person.getProtocolUnits()) {
                            unit.setPersonId(null);
                        }
                    }
    
                    getBusinessObjectService().save(person);
                }
            }
        }
    }

    private void performReviewerPersonMassChange(PersonMassChange personMassChange, Protocol protocol) {
        if (personMassChange.getProtocolPersonMassChange().isReviewer()) {
            for (ProtocolOnlineReviewBase onlineReview : protocol.getProtocolOnlineReviews()) {
                ProtocolReviewer reviewer = (ProtocolReviewer) onlineReview.getProtocolReviewer();
                if (isPersonIdMassChange(personMassChange, reviewer.getPersonId()) || isRolodexIdMassChange(personMassChange, reviewer.getRolodexId())) {
                    if (personMassChange.getReplacerPersonId() != null) {
                        KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                        reviewer.setPersonId(kcPerson.getPersonId());
                        reviewer.setRolodexId(null);
                        reviewer.setNonEmployeeFlag(false);
                    } else if (personMassChange.getReplacerRolodexId() != null) {
                        RolodexContract rolodex = getRolodexService().getRolodex(personMassChange.getReplacerRolodexId());
                        reviewer.setRolodexId(rolodex.getRolodexId());
                        reviewer.setPersonId(null);
                        reviewer.setNonEmployeeFlag(true);
                    }
                
                    getBusinessObjectService().save(reviewer);
                }
            }
        }
    }
    
    private void reportSoftError(Protocol protocol) {
        String protocolNumber = protocol.getProtocolNumber();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, PROTOCOL, protocolNumber);
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
    
    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((Protocol) parent).getProtocolNumber();
    }

    @Override
    protected String getDocumentName() {
        return PROTOCOL;
    }

    @Override
    protected String getWarningKey() {
        return IRB_WARNINGS;
    }

}
