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
