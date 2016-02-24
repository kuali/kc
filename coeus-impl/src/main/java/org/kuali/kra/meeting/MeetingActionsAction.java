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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.committee.service.ScheduleCorrespondencePrint;
import org.kuali.coeus.common.questionnaire.framework.print.CorrespondencePrintingService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.correspondence.AbstractProtocolActionsCorrespondence;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.grantexemption.GrantExemptionCorrespondence;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.withdraw.WithdrawCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MeetingActionsAction extends MeetingActionsActionBase {
    
    private static final String AGENDA_TYPE = "9";
    private static final String MEETING_MINUTE_TYPE = "10";

    private static final List GENERIC_TYPE_CORRESPONDENCE;
    static {
        final List correspondenceTypes = new ArrayList();
        correspondenceTypes.add(ProtocolCorrespondenceType.ABANDON_NOTICE);
        correspondenceTypes.add(ProtocolCorrespondenceType.APPROVAL_LETTER);
        correspondenceTypes.add(ProtocolCorrespondenceType.CLOSURE_NOTICE);
        correspondenceTypes.add(ProtocolCorrespondenceType.EXPEDITED_APPROVAL_LETTER);
        correspondenceTypes.add(ProtocolCorrespondenceType.NOTICE_OF_DEFERRAL);
        correspondenceTypes.add(ProtocolCorrespondenceType.SMR_LETTER);
        correspondenceTypes.add(ProtocolCorrespondenceType.SRR_LETTER);
        correspondenceTypes.add(ProtocolCorrespondenceType.SUSPENSION_NOTICE);
        correspondenceTypes.add(ProtocolCorrespondenceType.TERMINATION_NOTICE);
        GENERIC_TYPE_CORRESPONDENCE = correspondenceTypes;
    }

    private static final Map<String, String> CORR_TYPE_TO_ACTION_TYPE_MAP;

    static {
        CORR_TYPE_TO_ACTION_TYPE_MAP = new HashMap<String, String>();
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.ABANDON_NOTICE, ProtocolActionType.ABANDON_PROTOCOL);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.APPROVAL_LETTER,ProtocolActionType.APPROVED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.CLOSURE_NOTICE,ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.EXPEDITED_APPROVAL_LETTER,ProtocolActionType.EXPEDITE_APPROVAL);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.NOTICE_OF_DEFERRAL,ProtocolActionType.DEFERRED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.SMR_LETTER,ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.SRR_LETTER,ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.SUSPENSION_NOTICE,ProtocolActionType.SUSPENDED);
        CORR_TYPE_TO_ACTION_TYPE_MAP.put(ProtocolCorrespondenceType.TERMINATION_NOTICE,ProtocolActionType.TERMINATED);
    }
    
    protected CommitteePrintingService getCommitteePrintingService() {
        return KcServiceLocator.getService(CommitteePrintingService.class);
    }
  
    public ActionForward regenerateCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        MeetingHelper meetingHelper = (MeetingHelper) ((MeetingForm) form).getMeetingHelper();
        meetingHelper.setRegeneratedCorrespondences((List)new ArrayList<ProtocolCorrespondence>());
        for (org.kuali.kra.protocol.correspondence.ProtocolCorrespondence protocolCorrespondence : meetingHelper.getCorrespondences()) {
            if (protocolCorrespondence.isRegenerateFlag()) {
                Protocol protocol = (Protocol) protocolCorrespondence.getProtocol();
                AttachmentDataSource dataSource = generateCorrespondenceDocumentAndAttach(protocol, protocolCorrespondence.getProtoCorrespTypeCode());
                PrintableAttachment source = new PrintableAttachment();

                if (dataSource != null) {
                    protocolCorrespondence.setCorrespondence(dataSource.getData());
                    protocolCorrespondence.setFinalFlag(false);
                    protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
                    protocolCorrespondence.setCreateTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                }

                meetingHelper.getRegeneratedCorrespondences().add(protocolCorrespondence);
            }
        }
        getBusinessObjectService().save(meetingHelper.getRegeneratedCorrespondences());
        return mapping.findForward("correspondence");
    }

    protected AttachmentDataSource generateCorrespondenceDocumentAndAttach(Protocol protocol, String correspondenceType) throws PrintingException {
        AbstractProtocolActionsCorrespondence correspondence = null;
        if (StringUtils.equals(ProtocolCorrespondenceType.WITHDRAWAL_NOTICE, correspondenceType)) {
            correspondence = new WithdrawCorrespondence();
        } else if (GENERIC_TYPE_CORRESPONDENCE.contains(correspondenceType)) {
            correspondence = new ProtocolGenericCorrespondence(CORR_TYPE_TO_ACTION_TYPE_MAP.get(correspondenceType));
        } else if (StringUtils.equals(ProtocolCorrespondenceType.GRANT_EXEMPTION_NOTICE, correspondenceType)) {
            correspondence = new GrantExemptionCorrespondence();
        }
        correspondence.setProtocol(protocol);
        return getProtocolActionCorrespondenceGenerationService().reGenerateCorrespondenceDocument(correspondence);
    }

    protected ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return KcServiceLocator.getService(ProtocolActionCorrespondenceGenerationService.class);
    }

    @Override
    protected ScheduleAgendaBase getNewScheduleAgendaInstanceHook() {
        return new ScheduleAgenda();
    }

    @Override
    protected String getProtocolCorrespondenceAgendaTypeCodeHook() {
        return AGENDA_TYPE;
    }

    @Override
    protected CommScheduleMinuteDocBase getNewCommScheduleMinuteDocInstanceHook() {
        return new CommScheduleMinuteDoc();
    }

    @Override
    protected String getProtocolCorrespondenceMinutesTypeCodeHook() {
        return MEETING_MINUTE_TYPE;
    }

    @Override
    protected Class<? extends CommitteeScheduleBase> getCommitteeScheduleBOClass() {
        return CommitteeSchedule.class;
    }

    @Override
    protected String getActionIdHook() {
        return "protocolProtocolActions";
    }

    @Override
    protected CommonMeetingService getMeetingService() {
        return KcServiceLocator.getService(MeetingService.class);
    }

    @Override
    protected org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService<?> getReviewerCommentsService() {
        return KcServiceLocator.getService(ReviewCommentsService.class);
    }

    @Override
    protected CorrespondencePrintingService getCorrespondencePrintingService() {
        return KcServiceLocator.getService(ScheduleCorrespondencePrint.class);
    }

    @Override
    protected MeetingControllerService getMeetingControllerService() {
        return KcServiceLocator.getService("meetingControllerService");
    }
}
