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
 * This class is meeting generated agenda doc.
 */
public class ScheduleAgenda extends GeneratedMeetingDoc {

    private static final long serialVersionUID = -3448403457020324952L;

    private Long scheduleAgendaId;

    private Integer agendaNumber;

    private String agendaName;

    public ScheduleAgenda() {
    }

    public Long getScheduleAgendaId() {
        return scheduleAgendaId;
    }

    public void setScheduleAgendaId(Long scheduleAgendaId) {
        this.scheduleAgendaId = scheduleAgendaId;
    }

    public Integer getAgendaNumber() {
        return agendaNumber;
    }

    public void setAgendaNumber(Integer agendaNumber) {
        this.agendaNumber = agendaNumber;
    }

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }
}
