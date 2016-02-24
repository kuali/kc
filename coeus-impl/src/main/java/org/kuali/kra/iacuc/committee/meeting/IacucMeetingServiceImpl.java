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
package org.kuali.kra.iacuc.committee.meeting;

import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;

public class IacucMeetingServiceImpl extends MeetingServiceImplBase<IacucCommitteeSchedule, IacucCommitteeScheduleMinute, IacucCommittee> implements IacucMeetingService {

    @Override
    protected Class<? extends ScheduleAgendaBase> getScheduleAgendaBOClassHook() {
        return IacucScheduleAgenda.class;
    }

    @Override
    protected Class<? extends CommScheduleMinuteDocBase> getCommScheduleMinuteDocBOClassHook() {
        return IacucCommScheduleMinuteDoc.class;
    }

    @Override
    protected Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
        return IacucProtocolCorrespondence.class;
    }

    @Override
    protected Class<? extends ProtocolContingencyBase> getProtocolContingencyBOClassHook() {
        return IacucProtocolContingency.class;
    }

    @Override
    protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
        return new IacucOtherPresentBean();
    }

    @Override
    protected IacucCommitteeScheduleMinute getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleAttendanceBase getNewCommitteeScheduleAttendanceInstanceHook() {
        return new IacucCommitteeScheduleAttendance();
    }

}
