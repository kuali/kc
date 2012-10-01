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

import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.meeting.CommScheduleMinuteDocBase;
import org.kuali.kra.common.committee.meeting.CommonMeetingService;
import org.kuali.kra.common.committee.meeting.MeetingActionsActionBase;
import org.kuali.kra.common.committee.meeting.ScheduleAgendaBase;
import org.kuali.kra.common.committee.print.service.CommitteePrintingServiceBase;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteePrintingService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;

public class IacucMeetingActionsAction extends MeetingActionsActionBase {

    @Override
    protected ScheduleAgendaBase getNewScheduleAgendaInstanceHook() {
        return new IacucScheduleAgenda();
    }

    @Override
    protected String getProtocolCorrespondenceAgendaTypeCodeHook() {
        return IacucProtocolCorrespondenceType.AGENDA;
    }

    @Override
    protected CommScheduleMinuteDocBase getNewCommScheduleMinuteDocInstanceHook() {
        return new IacucCommScheduleMinuteDoc();
    }

    @Override
    protected String getProtocolCorrespondenceMinutesTypeCodeHook() {
        return IacucProtocolCorrespondenceType.MINUTES;
    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondenceInstanceHook(String protocolActionTypeCode) {
        return new IacucProtocolActionsCorrespondence(protocolActionTypeCode);
    }

    @Override
    protected ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return KraServiceLocator.getService(IacucProtocolActionCorrespondenceGenerationService.class);
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
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KraServiceLocator.getService(IacucCommitteePrintingService.class);
    }

}
