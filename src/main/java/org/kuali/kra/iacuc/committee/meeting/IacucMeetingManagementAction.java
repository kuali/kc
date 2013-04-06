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

import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.CommitteeDocumentBase;
import org.kuali.kra.common.committee.meeting.CommScheduleActItemBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.kra.common.committee.meeting.CommonMeetingService;
import org.kuali.kra.common.committee.meeting.MeetingAddMinuteEventBase;
import org.kuali.kra.common.committee.meeting.MeetingHelperBase;
import org.kuali.kra.common.committee.meeting.MeetingManagementActionBase;
import org.kuali.kra.common.committee.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.common.committee.service.CommitteeScheduleServiceBase;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;

public class IacucMeetingManagementAction extends MeetingManagementActionBase {

    @Override
    protected MeetingAddMinuteEventBase getNewMeetingAddMinuteEventInstanceHook(String  errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type) {
        return new IacucMeetingAddMinuteEvent(errorPathPrefix, (CommonCommitteeDocument) document, (IacucMeetingHelper) meetingHelper, type);
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new IacucCommScheduleActItem();
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new IacucCommitteeScheduleAttachments();
    }

    @Override
    protected Class<? extends CommitteeScheduleBase> getCommitteeScheduleBOClass() {
        return IacucCommitteeSchedule.class;
    }

    @Override
    protected String getActionIdHook() {
        return "iacucProtocolActions";
    }

    @Override
    protected CommonMeetingService getMeetingService() {
        return KraServiceLocator.getService(IacucMeetingService.class);
    }

    @Override
    protected ReviewCommentsService<?> getReviewerCommentsService() {
        return KraServiceLocator.getService(IacucReviewCommentsService.class);
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return IacucCommitteeScheduleService.class;
    }

    @Override
    protected String getCommitteeScheduleActionIdHook() {
        return "iacucCommitteeSchedule";
    }

    @Override
    protected String getCommitteeCommitteeActionIdHook() {
        return "iacucCommitteeCommittee";
    }

}
