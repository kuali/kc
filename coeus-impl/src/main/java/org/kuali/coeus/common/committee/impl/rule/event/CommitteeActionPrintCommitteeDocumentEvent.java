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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.rules.CommitteeActionPrintCommitteeDocumentRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

public class CommitteeActionPrintCommitteeDocumentEvent extends CommitteeActionsEventBase<CommitteeActionPrintCommitteeDocumentRule> {
    private static final String MSG = "print committee document";
    
    private Boolean printRooster;
    private Boolean printFutureScheduledMeeting;
    private boolean onMeetingAction;
    private Boolean printCorrespondence;
    
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
    public KcBusinessRule getRule() {
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

    public void setPrintCorrespondence(Boolean printCorrespondence) {
        this.printCorrespondence = printCorrespondence;
    }
    
    public Boolean getPrintCorrespondence() {
        return printCorrespondence;
    }

}
