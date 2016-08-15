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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.bo.ScheduleStatus;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.*;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.scheduling.ScheduleService;
import org.kuali.coeus.sys.framework.scheduling.seq.DefaultScheduleSequence;
import org.kuali.coeus.sys.framework.scheduling.seq.ScheduleSequence;
import org.kuali.coeus.sys.framework.scheduling.seq.TrimDatesScheduleSequenceDecorator;
import org.kuali.coeus.sys.framework.scheduling.seq.WeekScheduleSequenceDecorator;
import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;


/**
 * The CommitteeBase Service implementation.
 */
@Transactional
public abstract class CommitteeScheduleServiceImplBase<CS extends CommitteeScheduleBase<CS, CMT, ?, CSM>, 
                                                   CMT extends CommitteeBase<CMT,?,CS>,
                                                   CSM extends CommitteeScheduleMinuteBase<CSM, CS>>

                                                   implements CommitteeScheduleServiceBase<CS, CMT, CSM> {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleServiceImplBase.class);
    
    private static final String COLON = ":";
    
    private static final String DESCRIPTION = "description";
    
    private static final String SCHEDULED = "Scheduled";

    private static final String PROTOCOL_ID_FIELD = "protocolIdFk";
    private static final String ENTRY_NUMBER_FIELD = "entryNumber";
    private BusinessObjectService businessObjectService;

    private ScheduleService scheduleService;   


    private ReviewCommentsService<?> reviewCommentsService;

    
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
   
    public void setReviewCommentsService(ReviewCommentsService<?> reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }
    
    @Override
    public Boolean isCommitteeScheduleDeletable(CS committeeSchedule){
        
        boolean retVal = false;
        
        retVal = !isProtocolAssignedToScheduleDate(committeeSchedule);

        return retVal;
    }
       
    /**
     * Helper method to check if ProtocolBase is assigned to CommitteeScheduleBase.
     * @param committeeSchedule
     * @return
     */
    protected Boolean isProtocolAssignedToScheduleDate(CS committeeSchedule){
        boolean retVal = true;
        List<ProtocolBase> list = committeeSchedule.getProtocols();
        if(null == list || list.size() == 0)
            retVal = false;
        return retVal;
    }

    @Override
    public void addSchedule(ScheduleData scheduleData, CMT committee) throws ParseException {
        
        List<Date> dates = null;
        Date dtEnd = null;
        int frequency = 0;
        int day = 0;
        CronSpecialChars[] weekdays = null;
        CronSpecialChars weekOfMonth = null;
        CronSpecialChars dayOfWeek = null;
        CronSpecialChars month = null;
        
        Time24HrFmt time = getTime24hFmt(scheduleData.getScheduleStartDate(), scheduleData.getTime().findMinutes());
        Date dt = scheduleData.getScheduleStartDate();       
        
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());        
        switch (key) {
            case NEVER :
                dates = scheduleService.getScheduledDates(dt, dt, time, null);
                break;
            case DAILY : 
                DailyScheduleDetails.optionValues dailyoption = DailyScheduleDetails.optionValues.valueOf(scheduleData.getDailySchedule().getDayOption());
                switch (dailyoption) {
                    case XDAY: 
                        dtEnd = scheduleData.getDailySchedule().getScheduleEndDate();
                        day = scheduleData.getDailySchedule().getDay();
                        dates = scheduleService.getIntervalInDaysScheduledDates(dt, dtEnd, time, day);
                        break;
                    case WEEKDAY : 
                        dtEnd = scheduleData.getDailySchedule().getScheduleEndDate();                        
                        weekdays = ScheduleData.convertToWeekdays(scheduleData.getDailySchedule().getDaysOfWeek());
                        ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),1,weekdays.length);
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
                        break;
                }
                break;
            case WEEKLY :
                dtEnd = scheduleData.getWeeklySchedule().getScheduleEndDate();
                if(CollectionUtils.isNotEmpty(scheduleData.getWeeklySchedule().getDaysOfWeek())) {
                    weekdays = ScheduleData.convertToWeekdays(scheduleData.getWeeklySchedule().getDaysOfWeek().toArray(new String[scheduleData.getWeeklySchedule().getDaysOfWeek().size()]));
                }
                
                ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),scheduleData.getWeeklySchedule().getWeek(),weekdays.length);
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
                break;
            case MONTHLY :
                MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(scheduleData.getMonthlySchedule().getMonthOption());
                switch(monthOption) {
                    case XDAYANDXMONTH :
                        dtEnd = scheduleData.getMonthlySchedule().getScheduleEndDate();
                        day = scheduleData.getMonthlySchedule().getDay();
                        frequency = scheduleData.getMonthlySchedule().getOption1Month();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, frequency, null);
                        break;
                    case XDAYOFWEEKANDXMONTH :
                        dtEnd = scheduleData.getMonthlySchedule().getScheduleEndDate();
                        weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getMonthlySchedule().getSelectedMonthsWeek());
                        dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getMonthlySchedule().getSelectedDayOfWeek());
                        frequency = scheduleData.getMonthlySchedule().getOption2Month();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, dayOfWeek, weekOfMonth, frequency, null);
                        break;
                }
                break;
            case YEARLY : 
                YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(scheduleData.getYearlySchedule().getYearOption());
                switch(yearOption) {
                    case XDAY :
                        dtEnd = scheduleData.getYearlySchedule().getScheduleEndDate();
                        month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption1Month());
                        day = scheduleData.getYearlySchedule().getDay();
                        frequency = scheduleData.getYearlySchedule().getOption1Year();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, month, day, frequency, null);
                        break;
                    case CMPLX:
                        dtEnd = scheduleData.getYearlySchedule().getScheduleEndDate();
                        weekOfMonth = ScheduleData.getWeekOfMonth(scheduleData.getYearlySchedule().getSelectedMonthsWeek());
                        dayOfWeek = ScheduleData.getDayOfWeek(scheduleData.getYearlySchedule().getSelectedDayOfWeek());
                        month = ScheduleData.getMonthOfWeek(scheduleData.getYearlySchedule().getSelectedOption2Month());
                        frequency = scheduleData.getYearlySchedule().getOption2Year();
                        dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekOfMonth, dayOfWeek, month, frequency, null);
                        break;
                }
                break;            
        }
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
    protected void addScheduleDatesToCommittee(List<Date> dates, CMT committee, String location, List<java.sql.Date> skippedDates){
        for(Date date: dates) {
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            
            if(!isDateAvailable(committee.getCommitteeSchedules(), sqldate)){
                skippedDates.add(sqldate);
                continue;
            }

            CS committeeSchedule = getNewCommiteeScheduleInstanceHook();
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
    
    protected abstract CS getNewCommiteeScheduleInstanceHook();
    
    /**
     * Helper method to test if date is available (non conflicting).
     * @param committeeSchedules
     * @param date
     * @return
     */
    protected Boolean isDateAvailable(List<CS> committeeSchedules, java.sql.Date date) {
        boolean retVal = true;
        for (CS committeeSchedule : committeeSchedules) {
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
    protected ScheduleStatus getDefaultScheduleStatus(){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(DESCRIPTION, SCHEDULED);
        List<ScheduleStatus> scheduleStatuses = (List<ScheduleStatus>)businessObjectService.findMatching(ScheduleStatus.class, fieldValues);
        return scheduleStatuses.get(0);
    }
    
    @Override
    public List<CSM> getMinutesByProtocol(Long protocolId){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PROTOCOL_ID_FIELD, protocolId);
        List<CSM> minutes = (List<CSM>) businessObjectService.findMatchingOrderBy(getCommitteeScheduleMinuteBOClassHook(), fieldValues, ENTRY_NUMBER_FIELD, true);
        List<CSM> permittedMinutes = new ArrayList<CSM>();
        for (CSM minute : minutes) {
            if(reviewCommentsService.getReviewerCommentsView(minute)){
                permittedMinutes.add(minute);
            }
        }
        return permittedMinutes;
    }
    
    protected abstract Class<CSM> getCommitteeScheduleMinuteBOClassHook();


    /**
     * This method will downloadAttachment  to CommitteeScheduleAttachmentsBase.
     * @param committeScheduleAttachments
     * @return
     */
    @Override
    public void downloadAttachment(KcPersistableBusinessObjectBase attachmentDataSource, HttpServletResponse response) throws Exception {

    	CommitteeScheduleAttachmentsBase committeScheduleAttachments = getNewCommitteeScheduleAttachmentsInstanceHook();
        byte[] data = null;
        String contentType = null;
        String fileName = null;
        if(attachmentDataSource.getClass().isInstance(committeScheduleAttachments)){
            committeScheduleAttachments =(CommitteeScheduleAttachmentsBase)attachmentDataSource;
            data = committeScheduleAttachments.getData();
            contentType = committeScheduleAttachments.getContentType();
            fileName = committeScheduleAttachments.getFileName();
        }
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(data.length);
            baos.write(data);
            WebUtils.saveMimeOutputStreamAsFile(response, contentType, baos, fileName);
        } finally {
        	try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
               
            }
        }
    }

    protected abstract CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook();
}
