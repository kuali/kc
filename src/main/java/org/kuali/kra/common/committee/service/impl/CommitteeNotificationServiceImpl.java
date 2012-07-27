/*
 * Copyright 2006-2010 The Kuali Foundation
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
package org.kuali.kra.common.committee.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.committee.meeting.CommScheduleMinuteDoc;
import org.kuali.kra.common.committee.meeting.ScheduleAgenda;
import org.kuali.kra.common.committee.notification.AgendaCreatedNotificationRenderer;
import org.kuali.kra.common.committee.notification.CommitteeNotificationContext;
import org.kuali.kra.common.committee.notification.MinutesCreatedNotificationRenderer;
import org.kuali.kra.common.committee.service.CommonCommitteeNotificationService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;

/**
 * 
 * This class generates the notifications for committees.
 */
public class CommitteeNotificationServiceImpl implements CommonCommitteeNotificationService {

    private String committeeNotificationType;

    private KcNotificationService kcNotificationService;
    
    public String getCommitteeNotificationType() {
        return committeeNotificationType;
    }

    public void setCommitteeNotificationType(String committeeNotificationType) {
        this.committeeNotificationType = committeeNotificationType;
    }

    /**
     * This method generates Agenda Generated notifications for a committee.
     * @throws Exception 
     */
    public void generateNotification(String notificationType, ScheduleAgenda agenda) {
        
        if (StringUtils.equals(notificationType, Constants.COMMITTEE_AGENDA_NOTIFICATION)) {
            CommonCommitteeSchedule committeeSchedule = agenda.getCommitteeSchedule();
            AgendaCreatedNotificationRenderer renderer = new AgendaCreatedNotificationRenderer(agenda, "action taken");
            CommitteeNotificationContext context = new CommitteeNotificationContext(committeeSchedule, 
                                                    notificationType, "Agenda Generated Notification", renderer);
            kcNotificationService.sendNotification(context);
        } else {
            throw new IllegalArgumentException(committeeNotificationType);
        }
        
    }
    
    /**
     * This method generates Minutes Generated notifications for a committee.
     * @throws Exception 
     */
    public void generateNotification(String notificationType, CommScheduleMinuteDoc minuteDoc) {
        
        if (StringUtils.equals(notificationType, Constants.COMMITTEE_MINUTES_NOTIFICATION)) {
            CommonCommitteeSchedule committeeSchedule = minuteDoc.getCommitteeSchedule();
            MinutesCreatedNotificationRenderer renderer = new MinutesCreatedNotificationRenderer(minuteDoc, "action taken");
            CommitteeNotificationContext context = new CommitteeNotificationContext(committeeSchedule, 
                                                    notificationType, "Agenda Generated Notification", renderer);
            kcNotificationService.sendNotification(context);
        } else {
            throw new IllegalArgumentException(committeeNotificationType);
        }
        
    }
    
    /**
     * Populated by Spring Beans.
     * @param kcNotificationService
     */
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

}
