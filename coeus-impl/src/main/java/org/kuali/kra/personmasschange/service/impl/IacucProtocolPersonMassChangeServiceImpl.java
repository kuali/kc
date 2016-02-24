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
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonRole;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.IacucProtocolPersonMassChangeService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.util.*;

/**
 * Defines the service for performing a Person Mass Change on IACUC Protocols.
 */
public class IacucProtocolPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements IacucProtocolPersonMassChangeService {
    
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private static final String PROTOCOL = "protocol";
    private static final String IACUC_WARNINGS = "iacucWarnings";
    
    private PersonEditableService personEditableService;
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    
    @Override
    public List<IacucProtocol> getIacucProtocolChangeCandidates(PersonMassChange personMassChange) {
        List<IacucProtocol> protocolChangeCandidates = new ArrayList<IacucProtocol>();
        
        List<IacucProtocol> protocols = new ArrayList<IacucProtocol>();
        if (personMassChange.getIacucProtocolPersonMassChange().requiresChange()) {
            protocols.addAll(getProtocols(personMassChange));
        }

        for (IacucProtocol protocol : protocols) {
            if (isProtocolChangeCandidate(personMassChange, protocol)) {
                protocolChangeCandidates.add(protocol);
            }
        }
        
        for (IacucProtocol protocolChangeCandidate : protocolChangeCandidates) {
            if (!protocolChangeCandidate.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(protocolChangeCandidate);
            }
        }
        
        return protocolChangeCandidates;
    }
    
    private List<IacucProtocol> getProtocols(PersonMassChange personMassChange) {
        List<IacucProtocol> protocols = new ArrayList<IacucProtocol>();
        
        Collection<IacucProtocol> allProtocols = getBusinessObjectService().findAll(IacucProtocol.class);

        if (personMassChange.isChangeAllSequences()) {
            protocols.addAll(allProtocols);
        } else {
            protocols.addAll(getLatestProtocols(allProtocols));
        }
        
        return protocols;
    }
    
    private List<IacucProtocol> getLatestProtocols(Collection<IacucProtocol> protocols) {
        List<IacucProtocol> latestProtocols = new ArrayList<IacucProtocol>();
        
        for (String uniqueProtocolNumber : getUniqueProtocolNumbers(protocols)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PROTOCOL_NUMBER, uniqueProtocolNumber);
            Collection<IacucProtocol> uniqueProtocols = getBusinessObjectService().findMatchingOrderBy(IacucProtocol.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueProtocols.isEmpty()) {
                latestProtocols.add((IacucProtocol) CollectionUtils.get(uniqueProtocols, 0));
            }
        }
        
        return latestProtocols;
    }
    
    private Set<String> getUniqueProtocolNumbers(Collection<IacucProtocol> protocols) {
        Set<String> uniqueProtocolIds = new HashSet<String>();
        
        for (IacucProtocol protocol : protocols) {
            uniqueProtocolIds.add(protocol.getProtocolNumber());
        }
        
        return uniqueProtocolIds;
    }
    
    private boolean isProtocolChangeCandidate(PersonMassChange personMassChange, IacucProtocol protocol) {
        boolean isProtocolChangeCandidate = false;
        boolean hasErrors = false;
        
        List<ProtocolPersonBase> persons = protocol.getProtocolPersons();
        List<ProtocolOnlineReviewBase> onlineReviews = protocol.getProtocolOnlineReviews();
        
        String[] investigatorRoles = { IacucProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, IacucProtocolPersonRole.ROLE_CO_INVESTIGATOR };
        String[] keyStudyPersonRoles = { IacucProtocolPersonRole.ROLE_STUDY_PERSONNEL };
        String[] correspondentsRoles = { IacucProtocolPersonRole.ROLE_CORRESPONDENTS };
        
        if (personMassChange.getIacucProtocolPersonMassChange().isInvestigator()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, investigatorRoles);
        }
        if (personMassChange.getIacucProtocolPersonMassChange().isKeyStudyPerson()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, keyStudyPersonRoles);
        }
        if (personMassChange.getIacucProtocolPersonMassChange().isCorrespondents()) {
            isProtocolChangeCandidate |= isPersonChangeCandidate(personMassChange, persons, correspondentsRoles);
        }
        //if the protocol is a change candidate based on the investigators and key persons,
        //then make sure the replacer user doesn't already exist.
        if (isProtocolChangeCandidate) {
            hasErrors |= !isReplacerValidPersonChangeCandidate(personMassChange, persons);
        }          
        if (personMassChange.getIacucProtocolPersonMassChange().isReviewer()) {
            isProtocolChangeCandidate |= isReviewerChangeCandidate(personMassChange, onlineReviews);
        }
        
        return isProtocolChangeCandidate && !hasErrors;
    }
    
    private boolean isReviewerChangeCandidate(PersonMassChange personMassChange, List<ProtocolOnlineReviewBase> onlineReviews) {
        boolean isReviewerChangeCandidate = false;
        
        for (ProtocolOnlineReviewBase onlineReview : onlineReviews) {
            ProtocolReviewer reviewer = onlineReview.getProtocolReviewer();
            if (isPersonIdMassChange(personMassChange, reviewer.getPersonId()) || isRolodexIdMassChange(personMassChange, reviewer.getRolodexId())) {
                isReviewerChangeCandidate = true;
                break;
            }
        }
        
        return isReviewerChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<IacucProtocol> protocolChangeCandidates) {
        for (IacucProtocol protocolChangeCandidate : protocolChangeCandidates) {
            if (protocolChangeCandidate.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                performInvestigatorPersonMassChange(personMassChange, protocolChangeCandidate);
                performKeyStudyPersonPersonMassChange(personMassChange, protocolChangeCandidate);
                performCorrespondentsPersonMassChange(personMassChange, protocolChangeCandidate);
                performReviewerPersonMassChange(personMassChange, protocolChangeCandidate);
            }
        }
    }
    
    private void performInvestigatorPersonMassChange(PersonMassChange personMassChange, IacucProtocol protocol) {
        if (personMassChange.getIacucProtocolPersonMassChange().isInvestigator()) {
            String[] personRoles = { IacucProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR, IacucProtocolPersonRole.ROLE_CO_INVESTIGATOR };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }
    
    private void performKeyStudyPersonPersonMassChange(PersonMassChange personMassChange, IacucProtocol protocol) {
        if (personMassChange.getIacucProtocolPersonMassChange().isKeyStudyPerson()) {
            String[] personRoles = { IacucProtocolPersonRole.ROLE_STUDY_PERSONNEL };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }
    
    private void performCorrespondentsPersonMassChange(PersonMassChange personMassChange, IacucProtocol protocol) {
        if (personMassChange.getIacucProtocolPersonMassChange().isCorrespondents()) {
            String[] personRoles = { IacucProtocolPersonRole.ROLE_CORRESPONDENTS };
            performPersonPersonMassChange(personMassChange, protocol, personRoles);
        }
    }

    private void performPersonPersonMassChange(PersonMassChange personMassChange, IacucProtocol protocol, String... personRoles) {
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

    private void performReviewerPersonMassChange(PersonMassChange personMassChange, IacucProtocol protocol) {
        if (personMassChange.getIacucProtocolPersonMassChange().isReviewer()) {
            for (ProtocolOnlineReviewBase onlineReview : protocol.getProtocolOnlineReviews()) {
                ProtocolReviewer reviewer = onlineReview.getProtocolReviewer();
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
    
    private void reportSoftError(IacucProtocol protocol) {
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
        return ((IacucProtocol) parent).getProtocolNumber();
    }

    @Override
    protected String getDocumentName() {
        return PROTOCOL;
    }

    @Override
    protected String getWarningKey() {
        return IACUC_WARNINGS;
    }
    
}
