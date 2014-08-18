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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.List;

/**
 * Defines the rule that a user cannot delete an Other Action if it is being used in a Minute entry.
 */
public class MeetingDeleteOtherRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<MeetingDeleteOtherEvent> {

    /**
     * Checks to see whether the Other Action being deleted is being used by any of the Minute entries.
     * 
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(MeetingDeleteOtherEvent event) {
        boolean rulePassed = true;
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        
        int itemNumber = event.getOtherNumber();
        CommScheduleActItemBase deletedCommScheduleActItem = event.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems().get(itemNumber);
        List<? extends CommitteeScheduleMinuteBase> committeeScheduleMinutes = event.getMeetingHelper().getCommitteeSchedule().getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinuteBase committeeScheduleMinute : committeeScheduleMinutes) {
            if (deletedCommScheduleActItem.equals(committeeScheduleMinute.getCommScheduleActItem())) {
                errorReporter.reportError("meetingHelper.newOtherAction.", KeyConstants.ERROR_CANNOT_DELETE_ACTION_ITEM_IN_USE);
                rulePassed = false;
            }
        }
        
        return rulePassed;
    }

}
