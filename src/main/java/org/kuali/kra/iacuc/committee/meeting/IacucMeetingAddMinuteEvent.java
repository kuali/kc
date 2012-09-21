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
package org.kuali.kra.iacuc.committee.meeting;

import org.kuali.kra.common.committee.meeting.MeetingAddMinuteEvent;
import org.kuali.kra.common.committee.meeting.MeetingAddMinuteRule;
import org.kuali.kra.iacuc.committee.document.IacucCommitteeDocument;
import org.kuali.rice.krad.document.Document;

public class IacucMeetingAddMinuteEvent extends MeetingAddMinuteEvent {

    public IacucMeetingAddMinuteEvent(String errorPathPrefix, IacucCommitteeDocument document, IacucMeetingHelper meetingHelper,
            org.kuali.kra.common.committee.meeting.MeetingEventBase.ErrorType type) {
        super(errorPathPrefix, document, meetingHelper, type);
    }
    
    public IacucMeetingAddMinuteEvent(String errorPathPrefix, Document document, IacucMeetingHelper meetingHelper,
            org.kuali.kra.common.committee.meeting.MeetingEventBase.ErrorType type) {
        this(errorPathPrefix, (IacucCommitteeDocument) document, meetingHelper, type);
    }

    @Override
    protected MeetingAddMinuteRule getMeetingAddMinuteRuleInstanceHook() {
        return new IacucMeetingAddMinuteRule();
    }

}
