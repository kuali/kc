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
package org.kuali.kra.meeting;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rules.CommitteeScheduleTimeRule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class implements business rule when saving committee schedule.
 */
public class MeetingSaveRule  extends ResearchDocumentRuleBase implements BusinessRuleInterface<MeetingSaveEvent> {
    
    private static final String MSG1 = "hh:mm";

    private static final String MSG2 = "hh as 1-12 & mm as 0-59";

    private static final String MSG3 = "hh as 1-12";

    private static final String MSG4 = "mm as 0-59";

    private static final String COLON = ":";

    private static final String ID1 = "meetingHelper.committeeSchedule.viewTime.time";
    private static final String VIEW_TIME = "viewTime";
    private static final String VIEW_START_TIME = "viewStartTime";
    private static final String VIEW_END_TIME = "viewEndTime";
    private ErrorReporter errorReporter;
    
    /**
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(MeetingSaveEvent event) {
        boolean rulePassed = true;
        rulePassed &= validateMeetingDetails(event.getMeetingHelper().getCommitteeSchedule());
        rulePassed &= validateDuplicateAlternateFor(event.getMeetingHelper().getMemberPresentBeans());
        return rulePassed;
    }

    /**
     * 
     * This method is to validate time/start time/end time to make sure format is ok and also end time >= start time
     * @param committeeSchedule
     * @return
     */
    private boolean validateMeetingDetails(CommitteeSchedule committeeSchedule) {

        boolean rulePassed = true;
        errorReporter = new ErrorReporter();
        String time = committeeSchedule.getViewStartTime().getTime();
        rulePassed &= processTime(time, ID1.replace(VIEW_TIME, VIEW_START_TIME));
        time = committeeSchedule.getViewEndTime().getTime();
        rulePassed &= processTime(time, ID1.replace(VIEW_TIME, VIEW_END_TIME));
        if (rulePassed) {
            rulePassed &= checkStartTimeBeforeEndTime(committeeSchedule.getViewStartTime(), committeeSchedule.getViewEndTime());
        }
        time = committeeSchedule.getViewTime().getTime();
        // reuse logic in CommitteeScheduleTimeRule
        rulePassed &= (new CommitteeScheduleTimeRule()).processTime(time, ID1);
        rulePassed &= checkDeadlineBeforeScheduleDate(committeeSchedule);
        return rulePassed;
    }

    /**
     * 
     * This method is to validate that same person is only selected in one alternate for
     * @param memberPresentBeans
     * @return
     */
    private boolean validateDuplicateAlternateFor(List<MemberPresentBean> memberPresentBeans) {

        boolean rulePassed = true;
        errorReporter = new ErrorReporter();
        int i = 0;
        for (MemberPresentBean memberPresentBean : memberPresentBeans) {
            if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())) {
                int j = 0;
                for (MemberPresentBean memberPresentBean1 : memberPresentBeans) {
                    if (j > i
                            && StringUtils.isNotBlank(memberPresentBean1.getAttendance().getAlternateFor())
                            && memberPresentBean.getAttendance().getAlternateFor().equals(
                                    memberPresentBean1.getAttendance().getAlternateFor())) {
                        errorReporter.reportError("meetingHelper.memberPresentBeans[" + i + "].attendance.alternateFor",
                                KeyConstants.ERROR_DUPLICATE_ALTERNATE_FOR);
                        rulePassed = false;
                    }
                    j++;
                }
            }
            i++;
        }

        return rulePassed;
    }

    /*
     * check 'time' format
     * This is copied from CommitteeScheduleTimeRule and with some modification.
     */
    private boolean processTime(String time, String id) {
        String prefix = "";
        if (id.contains(VIEW_START_TIME)) {
            prefix = "Start";
        }
        else if (id.contains(VIEW_END_TIME)) {
            prefix = "End";
        }
        boolean rulePassed = true;

        if (StringUtils.isBlank(time)) {
            rulePassed = createErrorReport(id, prefix, MSG1);
            return rulePassed;
        }

        String[] result = time.split(COLON);
        if (result.length != 2) {
            rulePassed = createErrorReport(id, prefix, MSG1);
            return rulePassed;
        }

        Integer hrs;
        Integer mins;

        try {
            hrs = new Integer(result[0]);
            mins = new Integer(result[1]);

            if (!(hrs >= 1 && hrs <= 12)) {
                rulePassed = createErrorReport(id, prefix, MSG3);
                return rulePassed;
            }

            if (!(mins >= 0 && mins <= 59)) {
                rulePassed = createErrorReport(id, prefix, MSG4);
                return rulePassed;
            }
        }
        catch (NumberFormatException e) {
            rulePassed = createErrorReport(id, prefix, MSG2);
        }
        return rulePassed;
    }

    /*
     * check end time >= start time
     */
    private boolean checkStartTimeBeforeEndTime(Time12HrFmt startTime, Time12HrFmt endTime) {

        boolean rulePassed = true;
        String[] startTimeSplit = startTime.getTime().split(COLON);
        String[] endTimeSplit = endTime.getTime().split(COLON);

        Integer startHrs;
        Integer startMins;
        Integer endHrs;
        Integer endMins;

        if ("PM".equals(startTime.getMeridiem()) && "AM".equals(endTime.getMeridiem())) {
            errorReporter.reportError(ID1.replace(VIEW_TIME, VIEW_END_TIME),
                    KeyConstants.ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME);
            rulePassed = false;
        } else if (startTime.getMeridiem().equals(endTime.getMeridiem())) {
            startHrs = new Integer(startTimeSplit[0]);
            startMins = new Integer(startTimeSplit[1]);
            endHrs = new Integer(endTimeSplit[0]);
            endMins = new Integer(endTimeSplit[1]);
            if ((startHrs != 12 && (startHrs > endHrs || endHrs == 12)) || (startHrs.equals(endHrs) && startMins > endMins)) {
                errorReporter.reportError(ID1.replace(VIEW_TIME, VIEW_END_TIME),
                        KeyConstants.ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME);
                rulePassed = false;
            }
        }
        return rulePassed;
    }

    /*
     * check if submitdeadline before scheduled date
     */
    private boolean checkDeadlineBeforeScheduleDate(CommitteeSchedule committeeSchedule) {
        boolean rulePassed = true;
        if (committeeSchedule.getProtocolSubDeadline() != null && (committeeSchedule.getScheduledDate().before(committeeSchedule.getProtocolSubDeadline()))) {
            reportError("meetingHelper.committeeSchedule.protocolSubDeadline", KeyConstants.ERROR_COMMITTEESCHEDULE_DEADLINE, committeeSchedule.getProtocolSubDeadline().toString(), committeeSchedule.getScheduledDate().toString());
            rulePassed = false;
        }
        return rulePassed;        
    }
    
    private boolean createErrorReport(String id, String prefix, String msg) {
        errorReporter.reportError(id, KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME, prefix, msg);
        return false;
    }

}
