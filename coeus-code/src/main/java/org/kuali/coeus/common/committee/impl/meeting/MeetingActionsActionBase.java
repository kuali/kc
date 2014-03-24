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
package org.kuali.coeus.common.committee.impl.meeting;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.committee.impl.print.ScheduleTemplatePrintBase;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.coeus.common.committee.impl.service.CommonCommitteeNotificationService;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.printing.CorrespondencePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.meeting.CommScheduleMinuteDoc;
import org.kuali.kra.meeting.ScheduleAgenda;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MeetingActionsActionBase extends MeetingActionBase {

    
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
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();

        List<Printable> printableArtifactList = getPrintableArtifacts(meetingHelper, getProtocolCorrespondenceAgendaTypeCodeHook());
        if (printableArtifactList.get(0).getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.scheduleAgenda",
                    KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        } else {
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
                setFileDataProperties(dataStream, meetingHelper.getCommitteeSchedule().getId(), "Agenda");

                ScheduleAgendaBase agenda = getNewScheduleAgendaInstanceHook();
                int agendaNumber = meetingHelper.getScheduleAgendas().size() + 1;
                agenda.setAgendaName("Agenda For Schedule #  " + (meetingHelper.getCommitteeSchedule().getId()) + " Version " + agendaNumber);
                agenda.setAgendaNumber(agendaNumber);
                saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), agenda, dataStream.getContent());
                meetingHelper.setAgendaGenerationDate(new Date(agenda.getCreateTimestamp().getTime()));
                meetingHelper.getScheduleAgendas().add(agenda);
                //meetingHelper.setViewId("viewAgenda" + meetingHelper.getScheduleAgendas().size());
            }
        }
        return actionForward;
    }

    protected abstract ScheduleAgendaBase getNewScheduleAgendaInstanceHook();

    protected abstract String getProtocolCorrespondenceAgendaTypeCodeHook();

    
    
    /*
     * get the printable and add to printable list. 
     */
    private List<Printable> getPrintableArtifacts(MeetingHelperBase meetingHelper, String protocolCorrespondenceTypeCode) {
        CommitteeBase committee = meetingHelper.getCommitteeSchedule().getParentCommittee();
        AbstractPrint printable = (AbstractPrint)getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.SCHEDULE_TEMPLATE, committee.getCommitteeId());    
        printable.setPrintableBusinessObject(meetingHelper.getCommitteeSchedule());        
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("committeeId", committee.getCommitteeId());
        reportParameters.put("scheduleId", meetingHelper.getCommitteeSchedule().getScheduleId());
        //reportParameters.put("protoCorrespTypeCode", protoCorrespTypeCode);
        reportParameters.put(ScheduleTemplatePrintBase.REPORT_PARAMETER_KEY, protocolCorrespondenceTypeCode);
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
    public ActionForward generateMinutes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();

        List<Printable> printableArtifactList = getPrintableArtifacts(meetingHelper, getProtocolCorrespondenceMinutesTypeCodeHook());
        if (printableArtifactList.get(0).getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.meetingMinute",
                    KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        } else {
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
                setFileDataProperties(dataStream, meetingHelper.getCommitteeSchedule().getId(), "Minute");

                CommScheduleMinuteDocBase minuteDoc = getNewCommScheduleMinuteDocInstanceHook();
                minuteDoc.setMinuteName("Minute For Schedule #  " + (meetingHelper.getCommitteeSchedule().getId()) + " Version "
                        + (meetingHelper.getMinuteDocs().size() + 1));
                minuteDoc.setMinuteNumber(meetingHelper.getMinuteDocs().size() + 1);
                saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), minuteDoc, dataStream.getContent());
                meetingHelper.getMinuteDocs().add(minuteDoc);
                //meetingHelper.setViewId("viewMinute" + meetingHelper.getMinuteDocs().size());
            }
        }
        return actionForward;
    }

    protected abstract CommScheduleMinuteDocBase getNewCommScheduleMinuteDocInstanceHook();

    protected abstract String getProtocolCorrespondenceMinutesTypeCodeHook();
    

    private void saveGeneratedDoc (Long scheduleId, GeneratedMeetingDocBase generatedMeetingDoc, byte[] pdfData)  {
        generatedMeetingDoc.setScheduleIdFk(scheduleId);
        generatedMeetingDoc.setCreateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        generatedMeetingDoc.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        generatedMeetingDoc.setPdfStore(pdfData);
        getBusinessObjectService().save(generatedMeetingDoc);
    }
    
    
    protected abstract CommitteePrintingServiceBase getCommitteePrintingService();


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
    public ActionForward viewAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int selection = this.getSelectedLine(request);
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        String scheduleId = request.getParameter("scheduleId");
        if (scheduleId != null) {
            String selectedLine = request.getParameter("line");
            selection = Integer.parseInt(selectedLine);
            Long schedule_id = Long.parseLong(scheduleId);
            List<ScheduleAgenda> scheduleAgendas = getAgendaDoc(schedule_id);
            source.setContent(scheduleAgendas.get(selection).getPdfStore());
        }
        else {
            source.setContent(meetingHelper.getScheduleAgendas().get(selection).getPdfStore());
        }
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("ScheduleAgendaBase" + Constants.PDF_FILE_EXTENSION);
        if (source.getContent() != null) {
            PrintingUtils.streamToResponse(source, response);
            return null;
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Retrieve the agenda documents for the selected committee schedule.
     * 
     */
    private List<ScheduleAgenda> getAgendaDoc(Long scheduleId) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("scheduleIdFk", scheduleId);
        return (List<ScheduleAgenda>) KcServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(ScheduleAgenda.class, fieldValues, "createTimestamp", true);
        
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
        ScheduleAgendaBase agenda = ((MeetingFormBase)form).getMeetingHelper().getScheduleAgendas().get(selection);
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

        int selection = this.getSelectedLine(request);

        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        String scheduleId = request.getParameter("scheduleId");
        if (scheduleId != null) {
            String selectedLine = request.getParameter("line");
            selection = Integer.parseInt(selectedLine);
            Long schedule_Id = Long.parseLong(scheduleId);
            List<CommScheduleMinuteDoc> commScheduleMinutes = getMinuteDoc(schedule_Id);
            source.setContent(commScheduleMinutes.get(selection).getPdfStore());
        } else {
            source.setContent(meetingHelper.getMinuteDocs().get(selection).getPdfStore());
        }
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("MeetingMinute" + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }
    /*
     * This method is get the meeting minute documents of the selected committee schedule
     */
    private List<CommScheduleMinuteDoc> getMinuteDoc(Long scheduleId) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put("scheduleIdFk", scheduleId);
        return (List<CommScheduleMinuteDoc>) KcServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(CommScheduleMinuteDoc.class, fieldValues, "createTimestamp", true);
        
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
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        CommScheduleMinuteDocBase minuteDoc = meetingHelper.getMinuteDocs().get(selection);
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
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
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
    public class PrintableAttachment extends AttachmentDataSource {

        private static final long serialVersionUID = -8537865725808758230L;
        
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
        MeetingFormBase committeeForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = committeeForm.getMeetingHelper();
        CommitteeScheduleBase committeeSchedule = meetingHelper.getCommitteeSchedule();
        CommitteeDocumentBase document = 
            ((CommitteeDocumentBase) getDocumentService().getByDocumentHeaderId(committeeSchedule.getParentCommittee().getCommitteeDocument().getDocumentNumber()));
        Boolean printRooster = meetingHelper.getPrintRooster();
        Boolean printFutureScheduledMeeting = meetingHelper.getPrintFutureScheduledMeeting();
        List<Printable> correspondencePrintables = getCorrespondencePrintingService().getCorrespondencePrintable(committeeSchedule, meetingHelper.getCorrespondencesToPrint());
        Boolean printCorrespondences = !correspondencePrintables.isEmpty();
        CommitteeActionPrintCommitteeDocumentEvent event = new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, document, printRooster, printFutureScheduledMeeting, true);
        event.setPrintCorrespondence(printCorrespondences);
        if (applyRules(event)) {
            AbstractPrint printable;
            List<Printable> printableArtifactList = new ArrayList<Printable>();
            if (printRooster) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER, document.getCommittee().getCommitteeId());
                printable.setPrintableBusinessObject(document.getCommittee());
                document.getCommittee().setPrintRooster(printRooster);
                printableArtifactList.add(printable);
                meetingHelper.setPrintRooster(false);
            }
            if (printFutureScheduledMeeting) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS, document.getCommittee().getCommitteeId());
                printable.setPrintableBusinessObject(document.getCommittee());
                printableArtifactList.add(printable);
                document.getCommittee().setPrintRooster(printFutureScheduledMeeting);
                meetingHelper.setPrintFutureScheduledMeeting(false);
            }
            
            printableArtifactList.addAll(correspondencePrintables);
            
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null) {
                PrintingUtils.streamToResponse(dataStream, response);
                actionForward = null;
            }
        }
        return actionForward;
    }
    
    protected abstract CorrespondencePrintingService getCorrespondencePrintingService();

    private CommonCommitteeNotificationService getCommitteeNotificationService() {
        return KcServiceLocator.getService(CommonCommitteeNotificationService.class);
    }

    private DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }
   
    
    public abstract ActionForward regenerateCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;
   
    public ActionForward viewGeneratedCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        ProtocolCorrespondence correspondence = meetingHelper.getRegeneratedCorrespondences().get(selection);
            
        source.setContent(correspondence.getCorrespondence());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("Correspondence-" + correspondence.getProtocolCorrespondenceType().getDescription() + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }
    public ActionForward saveCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return correspondenceAction(mapping, form, true);
    }

    public ActionForward closeCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return correspondenceAction(mapping, form, false);
    }

    private ActionForward correspondenceAction(ActionMapping mapping, ActionForm form, boolean saveAction) {
        MeetingFormBase meetingForm = ((MeetingFormBase) form);
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        List<ProtocolCorrespondence> correspondences = meetingHelper.getRegeneratedCorrespondences();
        if (saveAction) {
            for (ProtocolCorrespondence correspondence : correspondences) {
                if (correspondence.getFinalFlag()) {
                    correspondence.setFinalFlagTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());

                }
            }
            getBusinessObjectService().save(correspondences);
        }
        // reset the regenerate check box
        for (ProtocolCorrespondence protocolCorrespondence : meetingHelper.getCorrespondences()) {
            protocolCorrespondence.setRegenerateFlag(false);
        }
        
        return mapping.findForward("actions");
   
    }

}
