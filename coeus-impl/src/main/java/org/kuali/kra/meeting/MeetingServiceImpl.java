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
package org.kuali.kra.meeting;

import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MeetingServiceImpl extends MeetingServiceImplBase<CommitteeSchedule, CommitteeScheduleMinute, Committee> implements MeetingService {

    @Override
    protected Class<? extends ScheduleAgendaBase> getScheduleAgendaBOClassHook() {
        return ScheduleAgenda.class;
    }

    @Override
    protected Class<? extends CommScheduleMinuteDocBase> getCommScheduleMinuteDocBOClassHook() {
        return CommScheduleMinuteDoc.class;
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.correspondence.ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
        return ProtocolCorrespondence.class;
    }

    @Override
    protected Class<? extends ProtocolContingencyBase> getProtocolContingencyBOClassHook() {
        return ProtocolContingency.class;
    }

    @Override
    protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
        return new OtherPresentBean();
    }

    @Override
    protected CommitteeScheduleMinute getNewCommitteeScheduleMinuteInstanceHook() {
        return new CommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleAttendanceBase getNewCommitteeScheduleAttendanceInstanceHook() {
        return new CommitteeScheduleAttendance();
    }
}
