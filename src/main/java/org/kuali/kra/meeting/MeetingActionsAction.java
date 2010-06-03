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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;

public class MeetingActionsAction extends MeetingAction {
    
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ActionForward forward = super.execute(mapping, form, request, response);
//        String command = request.getParameter("command");
//        if (StringUtils.isNotBlank(command) && "viewAgenda".equals(command)) {
//            forward = viewAgenda(mapping, form, request, response);
//        }
//        return forward;
//    }


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
        ActionForward  actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
       
        // TODO : Following commented out lines is intend to work with offshore's print implementation
        AbstractPrint printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.MEETING_AGENDA);
        printable.setDocument(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getCommittee().getCommitteeDocument());
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        if (printable.getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.scheduleAgenda", KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        } else {
        AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
        if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
            String fileName = "Agenda For Schedule # " + meetingHelper.getCommitteeSchedule().getId() + Constants.PDF_FILE_EXTENSION;
            try {
                dataStream.setFileName(URLEncoder.encode(fileName,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                dataStream.setFileName(fileName);
            }
            dataStream.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

            // use js to start 'view' document
//            streamToResponse(dataStream, response);
//            actionForward = null;
        ScheduleAgenda agenda = new ScheduleAgenda();
        agenda.setAgendaName("Agenda For Schedule #  "+ (meetingHelper.getCommitteeSchedule().getId()) + " Version " + (meetingHelper.getScheduleAgendas().size()+1));
        agenda.setAgendaNumber(meetingHelper.getScheduleAgendas().size()+1);
//        agenda.setPdfStore(dataStream.getContent());
        // TODO : this is temporary until offshorteam complete the pdf generation
        // getFileTemp should be replaced by real content
//        saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), agenda, getFileTemp());
        saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), agenda, dataStream.getContent());
        meetingHelper.setAgendaGenerationDate(new Date(agenda.getCreateTimestamp().getTime()));
        meetingHelper.getScheduleAgendas().add(agenda);
        meetingHelper.setViewId("viewAgenda"+meetingHelper.getScheduleAgendas().size());
        }
    }
        return actionForward;
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
        // TODO : Following commented out lines is intend to work with offshore's print implementation
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        AbstractPrint printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.MEETING_MINUTES);
        printable.setDocument(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getCommittee().getCommitteeDocument());
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        if (printable.getXSLTemplates().isEmpty()) {
            GlobalVariables.getMessageMap().putError("meetingHelper.meetingMinute", KeyConstants.ERROR_PROTO_CORRESPONDENCE_TEMPL_NOT_SET);
        }
        else {
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null && dataStream.getContent().length > 0) {
                String fileName = "Agenda For Schedule # " + meetingHelper.getCommitteeSchedule().getId()
                        + Constants.PDF_FILE_EXTENSION;
                try {
                    dataStream.setFileName(URLEncoder.encode(fileName, "UTF-8"));
                }
                catch (UnsupportedEncodingException e) {
                    dataStream.setFileName(fileName);
                }
                dataStream.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);

                // use js to start 'view' document
                // streamToResponse(dataStream, response);
                // actionForward = null;
                CommScheduleMinuteDoc minuteDoc = new CommScheduleMinuteDoc();
                minuteDoc.setMinuteName("Minute For Schedule #  " + (meetingHelper.getCommitteeSchedule().getId()) + " Version "
                        + (meetingHelper.getMinuteDocs().size() + 1));
                minuteDoc.setMinuteNumber(meetingHelper.getMinuteDocs().size() + 1);
                saveGeneratedDoc(meetingHelper.getCommitteeSchedule().getId(), minuteDoc, dataStream.getContent());
                meetingHelper.getMinuteDocs().add(minuteDoc);
                meetingHelper.setViewId("viewMinute" + meetingHelper.getMinuteDocs().size());
                // viewGeneratedMinute(mapping, form, request, response, meetingHelper.getMinuteDocs().size()-1);
                // PrintableAttachment source = new PrintableAttachment();
                // source.setContent(minuteDoc.getPdfStore());
                // source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
                // streamToResponse(source, response);
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
    
    /*
     * TODO : a temp methods to set up a pdf file before generate is working
     * remove this when generate pdf file is working
     */
    private byte[] getFileTemp() {
        try {
            File file = new File("src/main/webapp/static/printing/data/KCTestPrintableTestData.pdf");
            InputStream inStream = new FileInputStream(file);
            //BufferedInputStream bis = new BufferedInputStream(inStream);
            //return new byte[bis.available()];
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
                for (int readNum; (readNum = inStream.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    System.out.println("read " + readNum + " bytes,");
                }
                System.out.println("bos size " + bos.size());

            return bos.toByteArray();

        }
        catch (Exception e) {
            return null;
        }
    }

    private CommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }

    /*
     * This method is copied from KratrasactionalDocumentBase.   
     */
    private void streamToResponse(AttachmentDataSource attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils
                    .saveMimeOutputStreamAsFile(response, attachmentDataSource
                            .getContentType(), baos, attachmentDataSource
                            .getFileName());

        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                // LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
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
        
        final int selection = this.getSelectedLine(request);
       // final int selection =  Integer.parseInt(request.getParameter("line"));
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        source.setContent(meetingHelper.getScheduleAgendas().get(selection).getPdfStore());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("ScheduleAgenda ");
        streamToResponse(source, response);
        
        return null;
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
        
        final int selection = this.getSelectedLine(request);
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        PrintableAttachment source = new PrintableAttachment();
        source.setContent(meetingHelper.getMinuteDocs().get(selection).getPdfStore());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("MeetingMinute ");
        streamToResponse(source, response);
        
        return null;
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
        source.setFileName("Correspondence-" + meetingHelper.getCorrespondences().get(selection).getProtocolCorrespondenceType().getDescription());
        streamToResponse(source, response);
        
        return null;
    }

    
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
        

        // TODO : This rule is shared with committee action print.  error message path may be different, so need to refactor rule a little.
        if (applyRules(new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, document, printRooster, printFutureScheduledMeeting, true))) {
            AbstractPrint printable;
            List<Printable> printableArtifactList = new ArrayList<Printable>();
            if (printRooster) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER);
                printable.setDocument(document);
                printableArtifactList.add(printable);
            }
            if (printFutureScheduledMeeting) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS);
                printable.setDocument(document);
                printableArtifactList.add(printable);
            }
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null) {
                streamToResponse(dataStream, response);
                actionForward = null;
            }
        }
        return actionForward;
    }

    private DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
    private PrintingService getPrintingService() {
        return KraServiceLocator.getService(PrintingService.class);
    }
    
}
