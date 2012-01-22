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
package org.kuali.kra.committee.rule.event;

import org.kuali.kra.committee.rules.CommitteeActionPrintCommitteeDocumentRule;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.rice.krad.document.Document;

public class CommitteeActionPrintCommitteeDocumentEvent extends CommitteeActionsEventBase<CommitteeActionPrintCommitteeDocumentRule> {
    private static final String MSG = "print committee document";
    
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;
    private boolean onMeetingAction;
    
    public CommitteeActionPrintCommitteeDocumentEvent(String errorPathPrefix, Document document, Boolean printRooster, Boolean printFutureScheduledMeeting) {
        super(MSG + getDocumentId(document), errorPathPrefix, document);
        setPrintRooster(printRooster);
        setPrintFutureScheduledMeeting(printFutureScheduledMeeting);
        setOnMeetingAction(false);
    }

    public CommitteeActionPrintCommitteeDocumentEvent(String errorPathPrefix, Document document, Boolean printRooster, Boolean printFutureScheduledMeeting, boolean onMeetingAction) {
        super(MSG + getDocumentId(document), errorPathPrefix, document);
        setPrintRooster(printRooster);
        setPrintFutureScheduledMeeting(printFutureScheduledMeeting);
        setOnMeetingAction(onMeetingAction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BusinessRuleInterface getRule() {
        return new CommitteeActionPrintCommitteeDocumentRule();
    }

    public Boolean getPrintRooster() {
        return printRooster;
    }

    public void setPrintRooster(Boolean printRooster) {
        this.printRooster = printRooster;
    }

    public Boolean getPrintFutureScheduledMeeting() {
        return printFutureScheduledMeeting;
    }

    public void setPrintFutureScheduledMeeting(Boolean printFutureScheduledMeeting) {
        this.printFutureScheduledMeeting = printFutureScheduledMeeting;
    }

    public boolean isOnMeetingAction() {
        return onMeetingAction;
    }

    public void setOnMeetingAction(boolean onMeetingAction) {
        this.onMeetingAction = onMeetingAction;
    }

}
