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
package org.kuali.kra.meeting;

import org.kuali.coeus.common.committee.impl.meeting.MeetingFormBase;

/**
 * 
 * This class is a form for Meeting management,
 */
public class MeetingForm extends MeetingFormBase {
    

    private static final long serialVersionUID = -8524010367028447411L;

    @Override
    protected MeetingHelper getNewMeetingHelperInstanceHook(MeetingFormBase meetingForm) {
        return new MeetingHelper((MeetingForm) meetingForm);
    }
}
