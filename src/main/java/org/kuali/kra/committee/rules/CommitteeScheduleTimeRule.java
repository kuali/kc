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
package org.kuali.kra.committee.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleTimeEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class CommitteeScheduleTimeRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<CommitteeScheduleTimeEvent> {

    public static final String MSG1 = "hh:mm";

    public static final String MSG2 = "hh as 1-12 & mm as 0-59";

    public static final String MSG3 = "hh as 1-12";

    public static final String MSG4 = "mm as 0-59";

    public static final String COLON = ":";

    public static final String ID1 = "document.committeeList[0].committeeSchedules[%1$s].viewTime.time";

    public static final String ID2 = "committeeHelper.scheduleData.time.time";

    /**
     * @see org.kuali.kra.committee.rule.FilterCommitteeScheduleRule#processRules(org.kuali.kra.committee.rule.event.FilterCommitteeScheduleEvent)
     */
    public boolean processRules(CommitteeScheduleTimeEvent event) {

        boolean rulePassed = true;

        if(null != event.getScheduleData()) {
            rulePassed = processTime(event.getScheduleData().getTime().getTime(), ID2);
        }
        if(null != event.getCommitteeSchedules()) {
            rulePassed = processCommitteeSchedules(event.getCommitteeSchedules());
        }
        return rulePassed;
    }

    private boolean processCommitteeSchedules(List<CommitteeSchedule> committeeSchedules) {

        boolean rulePassed = true;
        int count = 0;

        for (CommitteeSchedule cs : committeeSchedules) {

            String time = cs.getViewTime().getTime();

            rulePassed &= processTime(time, String.format(ID1, count++));

        }
        return rulePassed;
    }

    // this method has been made public so its logic can be reused by MeetingSaveRule validation, this 
    // is a temporary solution, sometime in the future the two classes: MeetingSaveRule and CommitteeScheduleTimeRule
    // should be re-factored as siblings with the parent class containing the common time validation logic.
    public boolean processTime(String time, String id) {
    
        if (StringUtils.isBlank(time)) {               
            createRequiredFieldErrorReport(id);
            return false;
        }

        String[] result = time.split(COLON);
        if (result.length != 2) {               
            createFormattingErrorReport(id, time, MSG1);
            return false;
        }

        Integer hrs;
        Integer mins;

        try {
            hrs = new Integer(result[0]);
            mins = new Integer(result[1]);

            if (!(hrs >= 1 && hrs <= 12)) { 
                createFormattingErrorReport(id, time, MSG3);
                return false;
            }


            if (!(mins >= 0 && mins <= 59)) {
                createFormattingErrorReport(id, time, MSG4);
                return false;
            }
        }
        catch (NumberFormatException e) {
            createFormattingErrorReport(id, time, MSG2);
            return false;
        }
        
        return true;
    }

    private void createFormattingErrorReport(String id, String data, String msg) {
        reportError(id, KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME_FORMATTING, data, msg);
    }
    
    private void createRequiredFieldErrorReport(String id) {
        reportError(id, KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME_BLANK);
    }

}
