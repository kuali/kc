/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.meeting.CommScheduleActItemBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.meeting.MeetingFormBase;
import org.kuali.kra.common.committee.meeting.MeetingHelperBase;
import org.kuali.kra.common.committee.meeting.OtherPresentBeanBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.authorization.IacucCommitteeScheduleTask;
import org.kuali.kra.infrastructure.TaskGroupName;

public class IacucMeetingHelper extends MeetingHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5657448017371644177L;

    public IacucMeetingHelper(MeetingFormBase form) {
        super((IacucMeetingForm)form);
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

    @Override
    protected OtherPresentBeanBase getNewOtherPresentBeanInstanceHook() {
        return new IacucOtherPresentBean();
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new IacucCommScheduleActItem();
    }

    @Override
    protected CommitteeScheduleMinuteBase<?, ?> getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

    @Override
    protected CommitteeScheduleBase<?, ?, ?, ?> getNewCommitteeScheduleInstanceHook() {
        return new IacucCommitteeSchedule();
    }

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule) {
        return new IacucCommitteeScheduleTask(taskName, (IacucCommittee) committee, (IacucCommitteeSchedule) committeeSchedule);
    }

}
