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

import org.kuali.kra.common.committee.bo.Committee;
import org.kuali.kra.common.committee.bo.CommitteeSchedule;
import org.kuali.kra.common.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.common.committee.document.authorization.CommitteeTask;
import org.kuali.kra.common.committee.meeting.CommScheduleActItem;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttachments;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinute;
import org.kuali.kra.common.committee.meeting.MeetingForm;
import org.kuali.kra.common.committee.meeting.MeetingHelper;
import org.kuali.kra.common.committee.meeting.OtherPresentBean;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.authorization.IacucCommitteeScheduleTask;
import org.kuali.kra.infrastructure.TaskGroupName;

public class IacucMeetingHelper extends MeetingHelper {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5657448017371644177L;

    public IacucMeetingHelper(MeetingForm form) {
        super((IacucMeetingForm)form);
    }

    @Override
    protected CommitteeScheduleAttachments getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

    @Override
    protected OtherPresentBean getNewOtherPresentBeanInstanceHook() {
        return new IacucOtherPresentBean();
    }

    @Override
    protected CommScheduleActItem getNewCommScheduleActItemInstanceHook() {
        return new IacucCommScheduleActItem();
    }

    @Override
    protected CommitteeScheduleMinute<?, ?> getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

    @Override
    protected CommitteeSchedule<?, ?, ?, ?> getNewCommitteeScheduleInstanceHook() {
        return new IacucCommitteeSchedule();
    }

    @Override
    protected CommitteeTask getNewCommitteeTaskInstanceHook(String taskName, Committee committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTask<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeScheduleTask getNewCommitteeScheduleTaskInstanceHook(String taskName, Committee committee, CommitteeSchedule committeeSchedule) {
        return new IacucCommitteeScheduleTask(taskName, (IacucCommittee) committee, (IacucCommitteeSchedule) committeeSchedule);
    }

}
