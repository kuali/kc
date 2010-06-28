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

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Defines the rule that a user cannot delete an Other Action if it is being used in a Minute entry.
 */
public class MeetingDeleteOtherRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<MeetingDeleteOtherEvent> {

    /**
     * Checks to see whether the Other Action being deleted is being used by any of the Minute entries.
     * 
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(MeetingDeleteOtherEvent event) {
        boolean rulePassed = true;
        ErrorReporter errorReporter = new ErrorReporter();
        
        int itemNumber = event.getOtherNumber();
        CommScheduleActItem deletedCommScheduleActItem = event.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems().get(itemNumber);
        List<CommitteeScheduleMinute> committeeScheduleMinutes = event.getMeetingHelper().getCommitteeSchedule().getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinute committeeScheduleMinute : committeeScheduleMinutes) {
            if (deletedCommScheduleActItem.equals(committeeScheduleMinute.getCommScheduleActItem())) {
                errorReporter.reportError("meetingHelper.newOtherAction.", KeyConstants.ERROR_CANNOT_DELETE_ACTION_ITEM_IN_USE);
                rulePassed = false;
            }
        }
        
        return rulePassed;
    }

}
