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

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase;

import java.util.HashMap;
import java.util.Map;

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
        return "iacucProtocolProtocol";
    }

    @Override
    protected CommonMeetingService getMeetingService() {
        return KcServiceLocator.getService(IacucMeetingService.class);
    }

    @Override
    protected ReviewCommentsService<?> getReviewerCommentsService() {
        return KcServiceLocator.getService(IacucReviewCommentsService.class);
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

    @Override
    protected MeetingControllerService getMeetingControllerService() {
        return KcServiceLocator.getService("iacucMeetingControllerService");
    }

    @Override
    protected String getDocumentNumber(ProtocolSubmissionLiteBase protocolSubmission) {
        Map<String, String> criteria = new HashMap<>();
        criteria.put(PROTOCOL_ID, protocolSubmission.getProtocolId().toString());
        IacucProtocol protocol = getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, criteria);
        return protocol.getProtocolDocument().getDocumentNumber();
    }
}
