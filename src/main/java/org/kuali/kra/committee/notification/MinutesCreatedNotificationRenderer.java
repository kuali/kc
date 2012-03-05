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
package org.kuali.kra.committee.notification;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.irb.notification.IRBReplacementParameters;
import org.kuali.kra.meeting.CommScheduleMinuteDoc;
import org.kuali.kra.meeting.ScheduleAgenda;

/**
 * Renders additional fields for the Agenda Created notification.
 */
public class MinutesCreatedNotificationRenderer extends CommitteeNotificationRenderer {

    private static final long serialVersionUID = -6019679826378390076L;
    
    private String actionTaken;
    private CommScheduleMinuteDoc commScheduleMinuteDoc;

    /**
     * Constructs an Minutes Created notification renderer.
     * 
     * @param protocol
     * @param actionTaken
     */
    public MinutesCreatedNotificationRenderer(CommScheduleMinuteDoc minuteDoc, String actionTaken) {
        super(minuteDoc.getCommitteeSchedule().getCommittee());
        this.commScheduleMinuteDoc = minuteDoc;
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public CommScheduleMinuteDoc getCommScheduleMinuteDoc() {
        return commScheduleMinuteDoc;
    }
    
    public void setCommScheduleMinuteDoc(CommScheduleMinuteDoc commScheduleMinuteDoc) {
        this.commScheduleMinuteDoc = commScheduleMinuteDoc;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationContext#replaceContextVariables(java.lang.String)
     */
    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put(CommitteeReplacementParameters.LAST_ACTION_DATE, commScheduleMinuteDoc.getCommitteeSchedule().getScheduledDate().toString());
        params.put(CommitteeReplacementParameters.ACTION_TAKEN, actionTaken);
        params.put(CommitteeReplacementParameters.OBJECT_INDEX, new Integer(commScheduleMinuteDoc.getMinuteNumber().intValue() - 1).toString());
        return params;
    }    

}