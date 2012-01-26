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
package org.kuali.kra.meeting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.committee.service.CommitteeNotificationService;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class MeetingActionsAction extends MeetingAction {

    private static final String AGENDA_TYPE = "9";
    private static final String MEETING_MINUTE_TYPE = "10";

    /**
     * 
     * This method is to generate Meeting Agenda.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generateAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();

        List<Printable> printableArtifactList = getPrintableArtifacts(meetingHelper, AGENDA_TYPE);
        if (printableArtifactList.get(0).getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.scheduleAgenda",
                    KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        } else {
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
                setFileDataProperties(dataStream, meetingHelper.getCommitteeSchedule().getId(), "Agenda");
                ScheduleAgenda agenda = new ScheduleAgenda();
                agenda.setAgendaName("Agenda For Schedule #  " + (meetingHelper.getCommitteeSchedule().getId()) + " Version "
                        + (meetingHelper.getScheduleAgendas().size() + 1));
                agenda.setAgendaNumber(meetingHelper.getScheduleAgendas().size() + 1);
                saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), agenda, dataStream.getContent());
                meetingHelper.setAgendaGenerationDate(new Date(agenda.getCreateTimestamp().getTime()));
                meetingHelper.getScheduleAgendas().add(agenda);
                meetingHelper.setViewId("viewAgenda" + meetingHelper.getScheduleAgendas().size());
            }
        }
        return actionForward;
    }

    /*
     * get the printable and add to printable list. 
     */
    private List<Printable> getPrintableArtifacts(MeetingHelper meetingHelper, String protoCorrespTypeCode) {
//        meetingHelper.getCommitteeSchedule().getCommittee().getCommitteeDocument()
        AbstractPrint printable = (AbstractPrint)getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.SCHEDULE_TEMPLATE);
        Committee committee = meetingHelper.getCommitteeSchedule().getCommittee();
        printable.setPrintableBusinessObject(committee);
        
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("committeeId", committee.getCommitteeId());
        reportParameters.put("scheduleId", meetingHelper.getCommitteeSchedule().getScheduleId());
        reportParameters.put("protoCorrespTypeCode", protoCorrespTypeCode);
        ((AbstractPrint) printable).setReportParameters(reportParameters);
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        return printableArtifactList;
    }
    
    /*
     * set up a few file data properties 
     */
    private void setFileDataProperties(AttachmentDataSource dataStream, Long scheduleId, String type) {
        String fileName = type + "-For-Schedule-" + scheduleId + Constants.PDF_FILE_EXTENSION;
        try {
            dataStream.setFileName(URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            dataStream.setFileName(fileName);
        }
        dataStream.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
       
    }
    
    /**
     * 
     * This method is to generate Meeting Minute document.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generateMinutes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        List<Printable> printableArtifactList = getPrintableArtifacts(meetingHelper, MEETING_MINUTE_TYPE);
        if (printableArtifactList.get(0).getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.meetingMinute",
                    KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        } else {
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
                setFileDataProperties(dataStream, meetingHelper.getCommitteeSchedule().getId(), "Minute");
                CommScheduleMinuteDoc minuteDoc = new CommScheduleMinuteDoc();
                minuteDoc.setMinuteName("Minute For Schedule #  " + (meetingHelper.getCommitteeSchedule().getId()) + " Version "
                        + (meetingHelper.getMinuteDocs().size() + 1));
                minuteDoc.setMinuteNumber(meetingHelper.getMinuteDocs().size() + 1);
                saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), minuteDoc, dataStream.getContent());
                meetingHelper.getMinuteDocs().add(minuteDoc);
                meetingHelper.setViewId("viewMinute" + meetingHelper.getMinuteDocs().size());
            }
        }
        return actionForward;
    }

    private void saveGeneratedDoc (Long scheduleId, GeneratedMeetingDoc generatedMeetingDoc, byte[] pdfData)  {
        generatedMeetingDoc.setScheduleIdFk(scheduleId);
        generatedMeetingDoc.setCreateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        generatedMeetingDoc.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        generatedMeetingDoc.setPdfStore(pdfData);
        getBusinessObjectService().save(generatedMeetingDoc);
    }
    
    private CommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }


    /**
     * 
     * This method is to view the selected agenda.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
       // final int selection = this.getSelectedLine(request);
        final int selection =  Integer.parseInt(request.getParameter("line"));
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        source.setContent(meetingHelper.getScheduleAgendas().get(selection).getPdfStore());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("ScheduleAgenda" + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }

    /**
     * 
     * This method is UI hook to send notification message for selected agenda/meeting minutes
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendAgendaNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        final int selection = getSelectedLine(request);
        ScheduleAgenda agenda = ((MeetingForm)form).getMeetingHelper().getScheduleAgendas().get(selection);
        getCommitteeNotificationService().generateNotification(Constants.COMMITTEE_AGENDA_NOTIFICATION, agenda);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to view the selected meeting minute document.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
      //  final int selection = this.getSelectedLine(request);
        final int selection =  Integer.parseInt(request.getParameter("line"));
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        source.setContent(meetingHelper.getMinuteDocs().get(selection).getPdfStore());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("MeetingMinute" + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }
    
    /**
     * 
     * This method is to send notification that meeting minutes have been generated.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendMinutesNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
      //  final int selection = this.getSelectedLine(request);
        final int selection = getSelectedLine(request);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        CommScheduleMinuteDoc minuteDoc = meetingHelper.getMinuteDocs().get(selection);
        getCommitteeNotificationService().generateNotification(Constants.COMMITTEE_MINUTES_NOTIFICATION, minuteDoc);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to view the selected correspondence.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        source.setContent(meetingHelper.getCorrespondences().get(selection).getCorrespondence());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("Correspondence-" + meetingHelper.getCorrespondences().get(selection).getProtocolCorrespondenceType().getDescription() + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }

    /*
     * concrete class for AttachmentDataSource.
     * This is a similar class from printingserviceimpl
     * TODO : maybe should create a public class for this ?
     */
    private class PrintableAttachment extends AttachmentDataSource {
        private byte[] streamData;

        public byte[] getContent() {
            return streamData;
        }

        public void setContent(byte[] streamData) {
            this.streamData = streamData;
        }
    }

    /**
     * 
     * This method is to print Roster and/or future scheduling.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printRosterFutureSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CommitteeDocument document = 
            ((CommitteeDocument) getDocumentService().getByDocumentHeaderId(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentNumber()));
        Boolean printRooster = ((MeetingForm) form).getMeetingHelper().getPrintRooster();
        Boolean printFutureScheduledMeeting = ((MeetingForm) form).getMeetingHelper().getPrintFutureScheduledMeeting();
        

        if (applyRules(new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, document, printRooster, printFutureScheduledMeeting, true))) {
            AbstractPrint printable;
            List<Printable> printableArtifactList = new ArrayList<Printable>();
            if (printRooster) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER);
                printable.setPrintableBusinessObject(document.getCommittee());
                document.getCommittee().setPrintRooster(printRooster);
                printableArtifactList.add(printable);
                ((MeetingForm) form).getMeetingHelper().setPrintRooster(false);
            }
            if (printFutureScheduledMeeting) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS);
                printable.setPrintableBusinessObject(document.getCommittee());
                printableArtifactList.add(printable);
                document.getCommittee().setPrintRooster(printFutureScheduledMeeting);
                ((MeetingForm) form).getMeetingHelper().setPrintFutureScheduledMeeting(false);
            }
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null) {
                PrintingUtils.streamToResponse(dataStream, response);
                actionForward = null;
            }
        }
        return actionForward;
    }

    private CommitteeNotificationService getCommitteeNotificationService() {
        return KraServiceLocator.getService(CommitteeNotificationService.class);
    }

    private DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
    
}
