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
