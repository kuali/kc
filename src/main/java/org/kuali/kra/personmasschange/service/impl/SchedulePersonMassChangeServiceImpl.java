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
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.SchedulePersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Schedules.
 */
public class SchedulePersonMassChangeServiceImpl implements SchedulePersonMassChangeService {

    private static final String SCHEDULE_FIELD = "document.personMassChange.schedulePersonMassChange.";
    
    private static final String COMMITTEE_ID = "committeeId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String ATTENDEES = "attendees";
    
    private static final String SCHEDULE_ATTENDEES_FIELD = SCHEDULE_FIELD + ATTENDEES;
    
    private static final String SCHEDULE = "schedule";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    
    @Override
    public List<CommitteeSchedule> getScheduleChangeCandidates(PersonMassChange personMassChange) {
        Set<CommitteeSchedule> scheduleChangeCandidates = new HashSet<CommitteeSchedule>();
        
        List<CommitteeSchedule> schedules = new ArrayList<CommitteeSchedule>();
        if (personMassChange.getSchedulePersonMassChange().isAttendees()) {
            schedules.addAll(getSchedules(personMassChange));
        }

        for (CommitteeSchedule schedule : schedules) {
            if (isScheduleAttendeesChangeCandidate(personMassChange, schedule)) {
                scheduleChangeCandidates.add(schedule);
            }
        }
        
        return new ArrayList<CommitteeSchedule>(scheduleChangeCandidates);
    }
    
    private List<CommitteeSchedule> getSchedules(PersonMassChange personMassChange) {
        List<CommitteeSchedule> scheduleChangeCandidates = new ArrayList<CommitteeSchedule>();
        
        Collection<Committee> committees = getBusinessObjectService().findAll(Committee.class);

        if (personMassChange.isChangeAllSequences()) {
            for (Committee committee : committees) {
                scheduleChangeCandidates.addAll(committee.getCommitteeSchedules());
            }
        } else {
            scheduleChangeCandidates.addAll(getLatestSchedules(committees));
        }
        
        return scheduleChangeCandidates;
    }
    
    private List<CommitteeSchedule> getLatestSchedules(Collection<Committee> committees) {
        List<CommitteeSchedule> latestSchedules = new ArrayList<CommitteeSchedule>();
        
        List<Committee> latestCommittees = new ArrayList<Committee>();
        for (String uniqueCommitteeId : getUniqueCommitteeIds(committees)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(COMMITTEE_ID, uniqueCommitteeId);
            Collection<Committee> uniqueCommittees = getBusinessObjectService().findMatchingOrderBy(Committee.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueCommittees.isEmpty()) {
                latestCommittees.add((Committee) CollectionUtils.get(uniqueCommittees, 0));
            }
        }
        
        for (Committee latestCommittee : latestCommittees) {
            latestSchedules.addAll(latestCommittee.getCommitteeSchedules());
        }
        
        return latestSchedules;
    }
    
    private Set<String> getUniqueCommitteeIds(Collection<Committee> committees) {
        Set<String> uniqueCommitteeIds = new HashSet<String>();
        
        for (Committee committee : committees) {
            uniqueCommitteeIds.add(committee.getCommitteeId());
        }
        
        return uniqueCommitteeIds;
        
    }
    
    private boolean isScheduleAttendeesChangeCandidate(PersonMassChange personMassChange, CommitteeSchedule schedule) {
        boolean isScheduleAttendeesChangeCandidate = false;
        
        if (personMassChange.getSchedulePersonMassChange().isAttendees()) {
            for (CommitteeScheduleAttendance scheduleAttendance : schedule.getCommitteeScheduleAttendances()) {
                if (isPersonIdMassChange(personMassChange, scheduleAttendance) || isRolodexIdMassChange(personMassChange, scheduleAttendance)) {
                    isScheduleAttendeesChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isScheduleAttendeesChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<CommitteeSchedule> scheduleChangeCandidates) {
        for (CommitteeSchedule scheduleChangeCandidate : scheduleChangeCandidates) {            
            if (scheduleChangeCandidate.getCommittee().getCommitteeDocument().getPessimisticLocks().isEmpty()) {
                if (personMassChange.getSchedulePersonMassChange().isAttendees()) {
                    performScheduleAttendeesPersonMassChange(personMassChange, scheduleChangeCandidate);
                }
            } else {
                if (personMassChange.getSchedulePersonMassChange().isAttendees()) {
                    String scheduleId = scheduleChangeCandidate.getScheduleId();
                    errorReporter.reportWarning(SCHEDULE_ATTENDEES_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, SCHEDULE, scheduleId);
                }
            }
        }
    }
    
    private void performScheduleAttendeesPersonMassChange(PersonMassChange personMassChange, CommitteeSchedule scheduleChangeCandidate) {
        for (CommitteeScheduleAttendance scheduleAttendance : scheduleChangeCandidate.getCommitteeScheduleAttendances()) {
            String replaceePersonId = personMassChange.getReplaceePersonId();
            String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
            String personId = scheduleAttendance.getPersonId();
            String alternateFor = scheduleAttendance.getAlternateFor();
            
            if (personId != null && (StringUtils.equals(replaceePersonId, personId) || StringUtils.equals(replaceeRolodexId, personId))) {
                performScheduleAttendeesPersonIdPersonMassChange(personMassChange, scheduleAttendance);
            }
            if (alternateFor != null && (StringUtils.equals(replaceePersonId, alternateFor) || StringUtils.equals(replaceeRolodexId, alternateFor))) {
                performScheduleAttendeesAlternateForPersonMassChange(personMassChange, scheduleAttendance);
            }
            
            getBusinessObjectService().save(scheduleAttendance);
        }
    }
    
    private void performScheduleAttendeesPersonIdPersonMassChange(PersonMassChange personMassChange, CommitteeScheduleAttendance scheduleAttendance) {
        if (personMassChange.getReplacerPersonId() != null) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            scheduleAttendance.setPersonId(person.getPersonId());
            scheduleAttendance.setNonEmployeeFlag(false);
            scheduleAttendance.setPersonName(person.getFullName());
        } else if (personMassChange.getReplacerRolodexId() != null) {
            Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
            scheduleAttendance.setPersonId(String.valueOf(rolodex.getRolodexId()));
            scheduleAttendance.setNonEmployeeFlag(true);
            scheduleAttendance.setPersonName(rolodex.getFullName());
        }
    }
    
    private void performScheduleAttendeesAlternateForPersonMassChange(PersonMassChange personMassChange, CommitteeScheduleAttendance scheduleAttendance) {
        if (personMassChange.getReplacerPersonId() != null) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            scheduleAttendance.setAlternateFor(person.getPersonId());
        } else if (personMassChange.getReplacerRolodexId() != null) {
            Rolodex rolodex = getRolodexService().getRolodex(Integer.parseInt(personMassChange.getReplacerRolodexId()));
            scheduleAttendance.setAlternateFor(String.valueOf(rolodex.getRolodexId()));
        }
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, CommitteeScheduleAttendance scheduleAttendance) {
        boolean isPersonIdMassChange = false;
        
        String replaceePersonId = personMassChange.getReplaceePersonId();
        if (replaceePersonId != null) {
            isPersonIdMassChange |= StringUtils.equals(replaceePersonId, scheduleAttendance.getPersonId());
            isPersonIdMassChange |= StringUtils.equals(replaceePersonId, scheduleAttendance.getAlternateFor());
        }
        
        return isPersonIdMassChange;
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, CommitteeScheduleAttendance scheduleAttendance) {
        boolean isRolodexIdMassChange = false;
        
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        if (replaceeRolodexId != null) {
            isRolodexIdMassChange |= StringUtils.equals(replaceeRolodexId, scheduleAttendance.getPersonId());
            isRolodexIdMassChange |= StringUtils.equals(replaceeRolodexId, scheduleAttendance.getAlternateFor());
        }
        
        return isRolodexIdMassChange;
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