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
