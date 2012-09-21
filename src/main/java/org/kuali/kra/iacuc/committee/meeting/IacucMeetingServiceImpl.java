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

import org.kuali.kra.common.committee.meeting.CommScheduleMinuteDoc;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.common.committee.meeting.MeetingServiceImpl;
import org.kuali.kra.common.committee.meeting.OtherPresentBean;
import org.kuali.kra.common.committee.meeting.ProtocolContingency;
import org.kuali.kra.common.committee.meeting.ScheduleAgenda;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;

public class IacucMeetingServiceImpl extends MeetingServiceImpl<IacucCommitteeSchedule, IacucCommitteeScheduleMinute, IacucCommittee> implements IacucMeetingService {

    @Override
    protected Class<? extends ScheduleAgenda> getScheduleAgendaBOClassHook() {
        return IacucScheduleAgenda.class;
    }

    @Override
    protected Class<? extends CommScheduleMinuteDoc> getCommScheduleMinuteDocBOClassHook() {
        return IacucCommScheduleMinuteDoc.class;
    }

    @Override
    protected Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook() {
        return IacucProtocolCorrespondence.class;
    }

    @Override
    protected Class<? extends ProtocolContingency> getProtocolContingencyBOClassHook() {
        return IacucProtocolContingency.class;
    }

    @Override
    protected OtherPresentBean getNewOtherPresentBeanInstanceHook() {
        return new IacucOtherPresentBean();
    }

    @Override
    protected IacucCommitteeScheduleMinute getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleAttendance getNewCommitteeScheduleAttendanceInstanceHook() {
        return new IacucCommitteeScheduleAttendance();
    }

}
