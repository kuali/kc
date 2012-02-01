/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational CommCommitteey License, Version 2.0 (the "License");
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
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.CommitteePersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Committees.
 */
public class CommitteePersonMassChangeServiceImpl implements CommitteePersonMassChangeService {
    
    private static final String COMMITTEE_FIELD = "document.personMassChange.committeePersonMassChange.";

    private static final String COMMITTEE_ID = "committeeId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String MEMBER = "member";
    
    private static final String COMMITTEE_MEMBER_FIELD = COMMITTEE_FIELD + MEMBER;
    
    private static final String COMMITTEE = "committee";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    
    @Override
    public List<Committee> getCommitteeChangeCandidates(PersonMassChange personMassChange) {
        Set<Committee> committeeChangeCandidates = new HashSet<Committee>();
        
        List<Committee> committees = new ArrayList<Committee>();
        if (personMassChange.getCommitteePersonMassChange().isMember()) {
            committees.addAll(getCommittees(personMassChange));
        }

        for (Committee committee : committees) {
            if (isCommitteeMemberChangeCandidate(personMassChange, committee)) {
                committeeChangeCandidates.add(committee);
            }
        }
        
        return new ArrayList<Committee>(committeeChangeCandidates);
    }
    
    private List<Committee> getCommittees(PersonMassChange personMassChange) {
        List<Committee> committeeChangeCandidates = new ArrayList<Committee>();
        
        Collection<Committee> committees = getBusinessObjectService().findAll(Committee.class);

        if (personMassChange.isChangeAllSequences()) {
            committeeChangeCandidates.addAll(committees);
        } else {
            committeeChangeCandidates.addAll(getLatestCommittees(committees));
        }
        
        return committeeChangeCandidates;
    }
    
    private List<Committee> getLatestCommittees(Collection<Committee> committees) {
        List<Committee> latestCommittees = new ArrayList<Committee>();
        
        for (String uniqueCommitteeId : getUniqueCommitteeIds(committees)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(COMMITTEE_ID, uniqueCommitteeId);
            Collection<Committee> uniqueCommittees = getBusinessObjectService().findMatchingOrderBy(Committee.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueCommittees.isEmpty()) {
                latestCommittees.add((Committee) CollectionUtils.get(uniqueCommittees, 0));
            }
        }
        
        return latestCommittees;
    }
    
    private Set<String> getUniqueCommitteeIds(Collection<Committee> committees) {
        Set<String> uniqueCommitteeIds = new HashSet<String>();
        
        for (Committee committee : committees) {
            uniqueCommitteeIds.add(committee.getCommitteeId());
        }
        
        return uniqueCommitteeIds;
        
    }
    
    private boolean isCommitteeMemberChangeCandidate(PersonMassChange personMassChange, Committee committee) {
        boolean isCommitteeMemberChangeCandidate = false;
        
        if (personMassChange.getCommitteePersonMassChange().isMember()) {
            for (CommitteeMembership committeeMembership : committee.getCommitteeMemberships()) {
                if (isPersonIdMassChange(personMassChange, committeeMembership) || isRolodexIdMassChange(personMassChange, committeeMembership)) {
                    isCommitteeMemberChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isCommitteeMemberChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Committee> committeeChangeCandidates) {
        for (Committee committeeChangeCandidate : committeeChangeCandidates) {
            if (committeeChangeCandidate.getCommitteeDocument().getPessimisticLocks().isEmpty()) {
                if (personMassChange.getCommitteePersonMassChange().isMember()) {
                    performCommitteeMemberPersonMassChange(personMassChange, committeeChangeCandidate);
                }
            } else {
                if (personMassChange.getCommitteePersonMassChange().isMember()) {
                    String committeeName = committeeChangeCandidate.getCommitteeName();
                    errorReporter.reportWarning(COMMITTEE_MEMBER_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, COMMITTEE, committeeName);
                }
            }
        }
    }
    
    private void performCommitteeMemberPersonMassChange(PersonMassChange personMassChange, Committee committeeChangeCandidate) {
        for (CommitteeMembership committeeMembership : committeeChangeCandidate.getCommitteeMemberships()) {
            if (personMassChange.getReplacerPersonId() != null) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
                committeeMembership.setPersonId(person.getPersonId());
                committeeMembership.setRolodexId(null);
                committeeMembership.setPersonName(person.getFullName());
            } else if (personMassChange.getReplacerRolodexId() != null) {
                Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
                committeeMembership.setRolodexId(rolodex.getRolodexId());
                committeeMembership.setPersonId(null);
                committeeMembership.setPersonName(rolodex.getFullName());
            }
            
            getBusinessObjectService().save(committeeMembership);
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, CommitteeMembership committeeMembership) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, committeeMembership.getPersonId());
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, CommitteeMembership committeeMembership) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(committeeMembership.getRolodexId()));
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