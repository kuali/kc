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
