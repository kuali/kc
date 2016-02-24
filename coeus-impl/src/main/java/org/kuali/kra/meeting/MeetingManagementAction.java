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

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;

public class MeetingManagementAction extends MeetingManagementActionBase {

    @Override
    protected MeetingAddMinuteEventBase getNewMeetingAddMinuteEventInstanceHook(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type) {
        return new MeetingAddMinuteEvent(errorPathPrefix, (CommitteeDocument) document, (MeetingHelper) meetingHelper, type);
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new CommScheduleActItem();
    }

    @Override
    protected String getCommitteeScheduleActionIdHook() {
        return "committeeSchedule";
    }

    @Override
    protected String getCommitteeCommitteeActionIdHook() {
        return "committeeCommittee";
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new CommitteeScheduleAttachments();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return CommitteeScheduleService.class;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class<? extends CommitteeScheduleBase> getCommitteeScheduleBOClass() {
        return CommitteeSchedule.class;
    }

    @Override
    protected String getActionIdHook() {
        return "protocolProtocolActions";
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CommonMeetingService getMeetingService() {
        return KcServiceLocator.getService(MeetingService.class);
    }

    @Override
    protected org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService<?> getReviewerCommentsService() {
        return KcServiceLocator.getService(ReviewCommentsService.class);
    }

    @Override
    protected MeetingControllerService getMeetingControllerService() {
        return KcServiceLocator.getService("meetingControllerService");
    }
}
