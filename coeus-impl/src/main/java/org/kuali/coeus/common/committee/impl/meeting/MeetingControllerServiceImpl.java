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
package org.kuali.coeus.common.committee.impl.meeting;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class MeetingControllerServiceImpl implements MeetingControllerService {
    private static final String LINE_NUMBER = "lineNum";
    private static final String READ_ONLY = "readOnly";
    private static final String TRUE_STRING = "true";
    private static final String ID = "id";

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    private Class<? extends CommitteeScheduleBase> scheduleBusinessObjectClass;

    private ReviewCommentsService reviewCommentsService;

    private CommonMeetingService meetingService;

    @Override
    public void populateSchedule(MeetingFormBase form, HttpServletRequest request, String scheduleId) {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(ID, scheduleId);
        List<CommitteeScheduleMinuteBase<?, ?>> permittedMinutes = new ArrayList<>();
        CommitteeScheduleBase commSchedule = businessObjectService.findByPrimaryKey(getScheduleBusinessObjectClass(), fieldValues);
        List<CommitteeScheduleMinuteBase> minutes = commSchedule.getCommitteeScheduleMinutes();

        // use the entry type comparator to sort the minutes
        Collections.sort(minutes, CommitteeScheduleMinuteBase.entryTypeComparator);

        for (CommitteeScheduleMinuteBase minute : minutes) {
            if (getReviewCommentsService().getReviewerCommentsView(minute)) {
                permittedMinutes.add(minute);
            }
        }
        commSchedule.setCommitteeScheduleMinutes(permittedMinutes);
        form.setReadOnly(TRUE_STRING.equals(request.getParameter(READ_ONLY)));
        form.getMeetingHelper().setCommitteeSchedule(commSchedule);
        if (!form.getMeetingHelper().hasViewModifySchedulePermission()) {
            // same exception as of checkauthorization of kualiaction
            throw new AuthorizationException(getGlobalVariableService().getUserSession().getPerson().getPrincipalName(), "start",
                    form.getClass().getSimpleName());
        }

        getMeetingService().populateFormHelper(form.getMeetingHelper(), commSchedule,
                getScheduleLineNumber(request, commSchedule));
    }


    /**
     * This is a utility method to figure out the order of the selected schedule in schedule collections. This is primarily for
     * creating meeting tab title.
     */
    private static int getScheduleLineNumber(HttpServletRequest request, CommitteeScheduleBase<?, ?, ?, ?> commSchedule) {
        int lineNumber = 0;
        if (StringUtils.isNotBlank(request.getParameter(LINE_NUMBER))) {
            lineNumber = Integer.parseInt(request.getParameter(LINE_NUMBER));
        } else {
            for (CommitteeScheduleBase<?, ?, ?, ?> schedule : commSchedule.getParentCommittee().getCommitteeSchedules()) {
                lineNumber++;
                if (schedule.getId().equals(commSchedule.getId())) {
                    break;
                }
            }
        }
        return lineNumber;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public Class<? extends CommitteeScheduleBase> getScheduleBusinessObjectClass() {
        return scheduleBusinessObjectClass;
    }

    @Required
    public void setScheduleBusinessObjectClass(Class<? extends CommitteeScheduleBase> scheduleBusinessObjectClass) {
        this.scheduleBusinessObjectClass = scheduleBusinessObjectClass;
    }

    public ReviewCommentsService getReviewCommentsService() {
        return reviewCommentsService;
    }

    @Required
    public void setReviewCommentsService(ReviewCommentsService reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

    public CommonMeetingService getMeetingService() {
        return meetingService;
    }

    @Required
    public void setMeetingService(CommonMeetingService meetingService) {
        this.meetingService = meetingService;
    }
}
