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


/**
 * 
 * This class is meeting generated agenda doc.
 */
public abstract class ScheduleAgendaBase extends GeneratedMeetingDocBase {

    private static final long serialVersionUID = -3448403457020324952L;

    private Long scheduleAgendaId;

    private Integer agendaNumber;

    private String agendaName;

    public ScheduleAgendaBase() {
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
