/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.committee.impl.notification;

import org.kuali.coeus.common.committee.impl.meeting.CommScheduleMinuteDocBase;

import java.util.Map;

/**
 * Renders additional fields for the Agenda Created notification.
 */
public class MinutesCreatedNotificationRenderer extends CommitteeNotificationRenderer {

    private static final long serialVersionUID = -6019679826378390076L;
    
    private String actionTaken;
    private CommScheduleMinuteDocBase commScheduleMinuteDoc;

    /**
     * Constructs an Minutes Created notification renderer.
     * 
     * @param protocol
     * @param actionTaken
     */
    public MinutesCreatedNotificationRenderer(CommScheduleMinuteDocBase minuteDoc, String actionTaken) {
        super(minuteDoc.getCommitteeSchedule().getParentCommittee());
        this.commScheduleMinuteDoc = minuteDoc;
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public CommScheduleMinuteDocBase getCommScheduleMinuteDoc() {
        return commScheduleMinuteDoc;
    }
    
    public void setCommScheduleMinuteDoc(CommScheduleMinuteDocBase commScheduleMinuteDoc) {
        this.commScheduleMinuteDoc = commScheduleMinuteDoc;
    }
    
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(CommitteeReplacementParameters.LAST_ACTION_DATE, commScheduleMinuteDoc.getCommitteeSchedule().getScheduledDate().toString());
        params.put(CommitteeReplacementParameters.ACTION_TAKEN, actionTaken);
        params.put(CommitteeReplacementParameters.OBJECT_INDEX, new Integer(commScheduleMinuteDoc.getMinuteNumber().intValue() - 1).toString());
        params.put(CommitteeReplacementParameters.SCHEDULE_ID,commScheduleMinuteDoc.getCommitteeSchedule().getScheduleId());
        return params;
    }    

}
