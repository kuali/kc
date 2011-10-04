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
package org.kuali.kra.committee.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.ScheduleStatus;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.web.struts.form.schedule.*;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.DefaultScheduleSequence;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.TrimDatesScheduleSequenceDecorator;
import org.kuali.kra.scheduling.sequence.WeekScheduleSequenceDecorator;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Committee Service implementation.
 */
@Transactional
public class CommitteeScheduleServiceImpl implements CommitteeScheduleService {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleServiceImpl.class);
    
    private static final String COLON = ":";
    
    private static final String DESCRIPTION = "description";
    
    private static final String SCHEDULED = "Scheduled";
    
    private static final String PROTOCOL_ID_FIELD = "PROTOCOL_ID_FK";
    private static final String SCHEDULE_ID_FIELD = "SCHEDULE_ID_FK";
    private static final String ENTRY_NUMBER_FIELD = "ENTRY_NUMBER";
    private static final String SUBMISSION_ID_FIELD = "SUBMISSION_ID_FK";
    private static final String COMM_SCHEDULE_MINUTES_ID_PROPERTY = "commScheduleMinutesId";
    private BusinessObjectService businessObjectService;

    private ScheduleService scheduleService;   
    private ReviewCommentsService reviewCommentsService;

    
    /**
     * Set the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }    
    
    /**
     * Set the Schedule Service.
     * @param scheduleService
     */
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    /**
     * 
     * Set the ReviewComments Service.
     * @param reviewCommentsService
     */
    public void setReviewCommentsService(ReviewCommentsService reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }
    /**
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#isCommitteeScheduleDeletable(org.kuali.kra.committee.bo.CommitteeSchedule)
     */
    public Boolean isCommitteeScheduleDeletable(CommitteeSchedule committeeSchedule){
        
        boolean retVal = false;
        
        retVal = !isProtocolAssignedToScheduleDate(committeeSchedule);

        return retVal;
    }
       
    /**
     * Helper method to check if Protocol is assigned to CommitteeSchedule.
     * @param committeeSchedule
     * @return
     */
    protected Boolean isProtocolAssignedToScheduleDate(CommitteeSchedule committeeSchedule){
        boolean retVal = true;
        List<Protocol> list = committeeSchedule.getProtocols();
        if(null == list || list.size() == 0)
            retVal = false;
        return retVal;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#addSchedule(org.kuali.kra.committee.web.struts.form.schedule.ScheduleData, org.kuali.kra.committee.bo.Committee)
     */
    public void addSchedule(ScheduleData scheduleData, Committee committee) throws ParseException {
        
        Time24HrFmt time = getTime24hFmt(scheduleData.getScheduleStartDate(), scheduleData.getTime().findMinutes());
        java.sql.Date dt = scheduleData.getScheduleStartDate();       
        List<java.util.Date>dates = scheduleData.getScheduleDates(scheduleService, dt, time);
        List<java.sql.Date> skippedDates = new ArrayList<java.sql.Date>();
        scheduleData.setDatesInConflict(skippedDates);
        addScheduleDatesToCommittee(dates, committee, scheduleData.getPlace(),skippedDates);
    }
    
    /**
     * Helper method to convert date and minutes into Time24HrFmt object.
     * @param date
     * @param min
     * @return
     * @throws ParseException
     */
    protected Time24HrFmt getTime24hFmt(Date date, int min) throws ParseException{
        Date dt  = DateUtils.round(date, Calendar.DAY_OF_MONTH);            
        dt = DateUtils.addMinutes(dt, min);
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        StringBuffer sb = new StringBuffer();
        String str = sb.append(cl.get(Calendar.HOUR_OF_DAY)).append(COLON).append(cl.get(Calendar.MINUTE)).toString();       
        return new Time24HrFmt(str); 
    }
  
    /**
     * Helper method to add schedule to list.
     * @param dates
     * @param committee
     * @param location
     * @param skippedDates
     */
    protected void addScheduleDatesToCommittee(List<Date> dates, Committee committee, String location, List<java.sql.Date> skippedDates){
        for(Date date: dates) {
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            
            if(!isDateAvailable(committee.getCommitteeSchedules(), sqldate)){
                skippedDates.add(sqldate);
                continue;
            }
          
            CommitteeSchedule committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setScheduledDate(sqldate);
            committeeSchedule.setPlace(location);
            committeeSchedule.setTime(new Timestamp(date.getTime()));

            int daysToAdd = committee.getAdvancedSubmissionDaysRequired();
            java.sql.Date sqlDate = calculateAdvancedSubmissionDays(date, daysToAdd);
            committeeSchedule.setProtocolSubDeadline(sqlDate);

            committeeSchedule.setCommittee(committee);

            ScheduleStatus defaultStatus = getDefaultScheduleStatus();
            committeeSchedule.setScheduleStatusCode(defaultStatus.getScheduleStatusCode());
            committeeSchedule.setScheduleStatus(defaultStatus);
                         
            committee.getCommitteeSchedules().add(committeeSchedule);            
        }
    }
    
    /**
     * Helper method to test if date is available (non conflicting).
     * @param committeeSchedules
     * @param date
     * @return
     */
    protected Boolean isDateAvailable(List<CommitteeSchedule> committeeSchedules, java.sql.Date date) {
        boolean retVal = true;
        for (CommitteeSchedule committeeSchedule : committeeSchedules) {
            Date scheduledDate = committeeSchedule.getScheduledDate();
            if ((scheduledDate != null) && DateUtils.isSameDay(scheduledDate, date)) {
                retVal = false;
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Helper method to calculate advanced submission days.
     * @param startDate
     * @param days
     * @return
     */
    protected java.sql.Date calculateAdvancedSubmissionDays(Date startDate, Integer days){
        Date deadlineDate = DateUtils.addDays(startDate, -days);
        return new java.sql.Date(deadlineDate.getTime());
    }
    
    /**
     * Helper method to retrieve default ScheduleStatus object.
     * @return
     */
    @SuppressWarnings("unchecked")
    protected ScheduleStatus getDefaultScheduleStatus(){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(DESCRIPTION, SCHEDULED);
        List<ScheduleStatus> scheduleStatuses = (List<ScheduleStatus>)businessObjectService.findMatching(ScheduleStatus.class, fieldValues);
        return scheduleStatuses.get(0);
    }
    
    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#getMinutesByProtocol(java.lang.Long)
     */
    public List<CommitteeScheduleMinute> getMinutesByProtocol(Long protocolId){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_ID_FIELD, protocolId);
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>)businessObjectService.findMatchingOrderBy(CommitteeScheduleMinute.class, fieldValues, ENTRY_NUMBER_FIELD, true);
        return minutes;
    }
    
    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#getMinutesBySchedule(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<CommitteeScheduleMinute> getMinutesBySchedule(Long scheduleId){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<CommitteeScheduleMinute> permittedMinutes = new ArrayList<CommitteeScheduleMinute>();
        fieldValues.put(SCHEDULE_ID_FIELD, scheduleId);
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>)businessObjectService.findMatchingOrderBy(CommitteeScheduleMinute.class, fieldValues, ENTRY_NUMBER_FIELD, true);
        for (CommitteeScheduleMinute minute : minutes) {
                if(reviewCommentsService.getReviewerCommentsView(minute)){
                    permittedMinutes.add(minute);
                }
               
        }
        
        return permittedMinutes;
    }
      
    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#getCommitteeScheduleMinute(java.lang.Long)
     */
    public CommitteeScheduleMinute getCommitteeScheduleMinute(Long committeeScheduleId){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(COMM_SCHEDULE_MINUTES_ID_PROPERTY, committeeScheduleId);
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>)businessObjectService.findMatching(CommitteeScheduleMinute.class, fieldValues);
        if(minutes.size() == 1){
            return minutes.get(0);
        }else{
            return null;
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeScheduleService#getMinutesByProtocolSubmission(java.lang.Long)
     */
    public List<CommitteeScheduleMinute> getMinutesByProtocolSubmission(Long submissionID){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<CommitteeScheduleMinute> permittedMinutes = new ArrayList<CommitteeScheduleMinute>();
        fieldValues.put(SUBMISSION_ID_FIELD, submissionID);
        List<CommitteeScheduleMinute> minutes = (List<CommitteeScheduleMinute>)businessObjectService.findMatchingOrderBy(CommitteeScheduleMinute.class, fieldValues, ENTRY_NUMBER_FIELD, true);
        for (CommitteeScheduleMinute minute : minutes) {      
          if(reviewCommentsService.getReviewerCommentsView(minute)){
              permittedMinutes.add(minute);
          }
        }
        return permittedMinutes;
    }
}
