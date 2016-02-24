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
package org.kuali.kra.committee.rules;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class contains the document rules of the Action Print section.
 */
public class CommitteeActionPrintCommitteeDocumentRule extends KcTransactionalDocumentRuleBase
                                                       implements KcBusinessRule<CommitteeActionPrintCommitteeDocumentEvent> {
    private static final String PRINT_TYPE_FIELD = "committeeHelper.reportType";
    private static final String MEETING_PRINT_TYPE_FIELD = "meetingHelper.reportType";

/**
 * 
 * This method processes the rules of the CommitteeActionPrintCommitteeDocumentEvent.
 * 
 * @param event to be validated against the rules.
 * @return true if validation passed the rules, false otherwise.
 */
    public boolean processRules(CommitteeActionPrintCommitteeDocumentEvent event) {
        boolean rulePassed = true;
        
        if (!event.getPrintRooster() && !event.getPrintFutureScheduledMeeting()) {
            if (event.isOnMeetingAction()) {
                reportError(MEETING_PRINT_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_PRINT_REPORT_NOT_SPECIFIED);
            } else {
                reportError(PRINT_TYPE_FIELD, KeyConstants.ERROR_COMMITTEE_ACTION_PRINT_REPORT_NOT_SPECIFIED);
            }
            rulePassed = false;
        }
        
        return rulePassed;
    }

}
