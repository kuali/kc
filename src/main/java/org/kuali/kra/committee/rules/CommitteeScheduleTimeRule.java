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
            rulePassed = processCommitteeSchedule(event.getCommitteeSchedules());
        }
        return rulePassed;
    }

    private boolean processCommitteeSchedule(List<CommitteeSchedule> committeeScheduleas) {

        boolean rulePassed = true;
        int count = 0;

        for (CommitteeSchedule cs : committeeScheduleas) {

            String time = cs.getViewTime().getTime();

            rulePassed = processTime(time, String.format(ID1, count++));

        }
        return rulePassed;
    }

    private boolean processTime(String time, String id) {
        boolean rulePassed = true;

        if (StringUtils.isBlank(time)) {               
            rulePassed = createErrorReport(id, MSG1);
            return rulePassed;
        }

        String[] result = time.split(COLON);
        if (result.length != 2) {               
            rulePassed = createErrorReport(id, MSG1);
            return rulePassed;
        }

        Integer hrs;
        Integer mins;

        try {
            hrs = new Integer(result[0]);
            mins = new Integer(result[1]);

            if (!(hrs >= 1 && hrs <= 12)) { 
                rulePassed = createErrorReport(id, MSG3);
                return rulePassed;
            }


            if (!(mins >= 0 && mins <= 59)) {
                rulePassed = createErrorReport(id, MSG4);
                return rulePassed;
            }
        }
        catch (NumberFormatException e) {
            rulePassed = createErrorReport(id, MSG2);           
        }
        return rulePassed;
    }

    private boolean createErrorReport(String id, String msg) {
        reportError(id, KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME, msg);
        return false;
    }

}
