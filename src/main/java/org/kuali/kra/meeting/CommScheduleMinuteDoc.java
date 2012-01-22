/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.meeting;


/**
 * 
 * This class is for meeting generated minute doc.
 */
public class CommScheduleMinuteDoc extends GeneratedMeetingDoc {

    private static final long serialVersionUID = 2574809115702106379L;

    private Long commScheduleMinuteDocId;

    private Integer minuteNumber;

    private String minuteName;

    public CommScheduleMinuteDoc() {
    }

    public Long getCommScheduleMinuteDocId() {
        return commScheduleMinuteDocId;
    }

    public void setCommScheduleMinuteDocId(Long commScheduleMinuteDocId) {
        this.commScheduleMinuteDocId = commScheduleMinuteDocId;
    }

    public Integer getMinuteNumber() {
        return minuteNumber;
    }

    public void setMinuteNumber(Integer minuteNumber) {
        this.minuteNumber = minuteNumber;
    }

    public String getMinuteName() {
        return minuteName;
    }

    public void setMinuteName(String minuteName) {
        this.minuteName = minuteName;
    }
}
