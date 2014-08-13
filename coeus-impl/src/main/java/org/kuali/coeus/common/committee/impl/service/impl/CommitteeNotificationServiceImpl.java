/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommScheduleMinuteDocBase;
import org.kuali.coeus.common.committee.impl.meeting.ScheduleAgendaBase;
import org.kuali.coeus.common.committee.impl.notification.AgendaCreatedNotificationRenderer;
import org.kuali.coeus.common.committee.impl.notification.CommitteeNotificationContext;
import org.kuali.coeus.common.committee.impl.notification.MinutesCreatedNotificationRenderer;
import org.kuali.coeus.common.committee.impl.service.CommonCommitteeNotificationService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
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
    public void generateNotification(String notificationType, ScheduleAgendaBase agenda) {
        
        if (StringUtils.equals(notificationType, Constants.COMMITTEE_AGENDA_NOTIFICATION)) {
            CommitteeScheduleBase committeeSchedule = agenda.getCommitteeSchedule();
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
    public void generateNotification(String notificationType, CommScheduleMinuteDocBase minuteDoc) {
        
        if (StringUtils.equals(notificationType, Constants.COMMITTEE_MINUTES_NOTIFICATION)) {
            CommitteeScheduleBase committeeSchedule = minuteDoc.getCommitteeSchedule();
            MinutesCreatedNotificationRenderer renderer = new MinutesCreatedNotificationRenderer(minuteDoc, "action taken");
            CommitteeNotificationContext context = new CommitteeNotificationContext(committeeSchedule, 
                                                    notificationType, "Minutes Generated Notification", renderer);
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
