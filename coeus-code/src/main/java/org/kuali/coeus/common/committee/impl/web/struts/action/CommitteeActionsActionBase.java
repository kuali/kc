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
package org.kuali.coeus.common.committee.impl.web.struts.action;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceDetailBase;
import org.kuali.coeus.common.committee.impl.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionViewBatchCorrespondenceEvent;
import org.kuali.coeus.common.committee.impl.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.questionnaire.framework.print.CorrespondencePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



/**
 * The CommitteeActionsActionBase corresponds to the Actions tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public abstract class CommitteeActionsActionBase extends CommitteeActionBase {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeActionsActionBase.class);

    // signifies that a response has already be handled therefore forwarding to obtain a response is not needed. 
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    
    /**
     * This method is perform the action - Generate Batch Correspondence.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generateBatchCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeDocumentBase committeeDocument = committeeForm.getCommitteeDocument();
        String committeeId = committeeDocument.getCommittee().getCommitteeId();
        String batchCorrespondenceTypeCode = committeeForm.getCommitteeHelper().getGenerateBatchCorrespondenceTypeCode();
        Date startDate = committeeForm.getCommitteeHelper().getGenerateStartDate();
        Date endDate = committeeForm.getCommitteeHelper().getGenerateEndDate();

        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        if (isAuthorized(task)) {

            if (applyRules(getNewCommitteeActionGenerateBatchCorrespondenceEventInstanceHook(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                    batchCorrespondenceTypeCode, startDate, endDate, committeeId))) {
                committeeForm.getCommitteeHelper().getGenerateBatchCorrespondence().clear();
                committeeForm.getCommitteeHelper().getGenerateBatchCorrespondence().add(
                        getCommitteeBatchCorrespondenceService().generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate));
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);
    
    
    protected abstract CommitteeActionGenerateBatchCorrespondenceEventBase getNewCommitteeActionGenerateBatchCorrespondenceEventInstanceHook(String errorPathPrefix,
            org.kuali.rice.krad.document.Document document, String batchCorrespondenceTypeCode, Date startDate, Date endDate,
            String committeeId);

    /**
     * This method is perform the action - Filter Batch Correspondence History.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
    public ActionForward filterBatchCorrespondenceHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        String batchCorrespondenceTypeCode = committeeForm.getCommitteeHelper().getHistoryBatchCorrespondenceTypeCode();
        Date startDate = committeeForm.getCommitteeHelper().getHistoryStartDate();
        Date endDate = committeeForm.getCommitteeHelper().getHistoryEndDate();
        
        committeeForm.getCommitteeHelper().resetBatchCorrespondenceHistory(committeeForm);
        if (applyRules(new CommitteeActionFilterBatchCorrespondenceHistoryEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                batchCorrespondenceTypeCode, startDate, endDate))) {
            committeeForm.getCommitteeHelper().setBatchCorrespondenceHistory(getCommitteeBatchCorrespondenceDao()
                    .getCommitteeBatchCorrespondence(batchCorrespondenceTypeCode, startDate, endDate));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method returns the selected batch correspondence that just have been generated documents for viewing.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewBatchCorrespondenceGenerated(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences = committeeForm.getCommitteeHelper()
                .getGenerateBatchCorrespondence();
        
        return viewBatchCorrespondence(mapping, committeeForm, committeeBatchCorrespondences, true, response);
    }
        
    /**
     * 
     * This method returns the selected batch correspondence history documents for viewing.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewBatchCorrespondenceHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences = committeeForm.getCommitteeHelper()
                .getBatchCorrespondenceHistory();
        
        return viewBatchCorrespondence(mapping, committeeForm, committeeBatchCorrespondences, false, response);
    }
        
    /**
     * 
     * This method returns the selected batch correspondence documents for viewing.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    private ActionForward viewBatchCorrespondence(ActionMapping mapping, CommitteeFormBase committeeForm, 
            List<CommitteeBatchCorrespondenceBase> committeeBatchCorrespondences, boolean viewBatch, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        List<String> bookmarksList = new ArrayList<String>();
        List<byte[]> pdfBaosList = new ArrayList<byte[]>();

        if (applyRules(new CommitteeActionViewBatchCorrespondenceEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), committeeBatchCorrespondences, viewBatch))) {
            for (CommitteeBatchCorrespondenceBase committeeBatchCorrespondence : committeeBatchCorrespondences) {
                for (CommitteeBatchCorrespondenceDetailBase committeeBatchCorrespondenceDetail : committeeBatchCorrespondence
                        .getCommitteeBatchCorrespondenceDetails()) {
                    if (committeeBatchCorrespondenceDetail.getSelected()) {
                        bookmarksList.add("Protocol " + committeeBatchCorrespondenceDetail.getProtocolCorrespondence().getProtocolNumber() + ": "
                                + committeeBatchCorrespondenceDetail.getProtocolAction().getComments());
                        pdfBaosList.add(committeeBatchCorrespondenceDetail.getProtocolCorrespondence().getCorrespondence());
                    }
                }
            }

            byte[] mergedPdfBytes = mergePdfBytes(pdfBaosList, bookmarksList);
            
            // If there is a stylesheet issue, the pdf bytes will be null. To avoid an exception
            // initialize to an empty array before sending the content back
            if (mergedPdfBytes == null) {
                mergedPdfBytes = new byte[0];
            }
    
            this.streamToResponse(mergedPdfBytes, "correspondence.pdf", 
                    Constants.PDF_REPORT_CONTENT_TYPE, response);

            actionForward = RESPONSE_ALREADY_HANDLED;
        }
        
        return actionForward;
    }

    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ((CommitteeFormBase)form).getCommitteeHelper().prepareView();
        return actionForward;
    }

    /**
     * This method merged the pdf bytes without creating page numbers and dates.
     * 
     * (This is a slimed down version of MergePdfBytes() in PrintingServiceImpl.java)
     * 
     * @param pdfBytesList
     *            List containing the PDF data bytes
     * @param bookmarksList
     *            List of bookmarks corresponding to the PDF bytes.
     * @return
     * @throws PrintingException
     */
    private byte[] mergePdfBytes(List<byte[]> pdfBytesList, List<String> bookmarksList) throws PrintingException {
        Document document = null;
        PdfWriter writer = null;
        ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
        for (int count = 0; count < pdfBytesList.size(); count++) {
            PdfReader reader;
            try {
                reader = new PdfReader(pdfBytesList.get(count));
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
                break;
//              throw new PrintingException(e.getMessage(), e);
            }
            int nop;
            if (reader == null) {
                LOG.debug("Empty PDF bytes found for " + bookmarksList.get(count));
                continue;
            } else {
                nop = reader.getNumberOfPages();
            }

            if (count == 0) {
                document = nop > 0 ? new com.lowagie.text.Document(reader.getPageSizeWithRotation(1)) : new com.lowagie.text.Document();
                try {
                    writer = PdfWriter.getInstance(document, mergedPdfReport);
                } catch (DocumentException e) {
                    LOG.error(e.getMessage(), e);
                    throw new PrintingException(e.getMessage(), e);
                }
                document.open();
            }
            PdfContentByte cb = writer.getDirectContent();
            int pageCount = 0;
            while (pageCount < nop) {
                document.setPageSize(reader.getPageSize(++pageCount));
                document.newPage();
                PdfImportedPage page = writer
                        .getImportedPage(reader, pageCount);

                cb.addTemplate(page, 1, 0, 0, 1, 0, 0);

                PdfOutline root = cb.getRootOutline();
                if (pageCount == 1) {
                    String pageName = bookmarksList.get(count);
                    cb.addOutline(new PdfOutline(root, new PdfDestination(PdfDestination.FITH), pageName), pageName);
                }
            }
        }
        
        if (document != null) {
            document.close();
            return mergedPdfReport.toByteArray();
        }
        
        return null;
    }

    /**
     * This method is perform the action - Print CommitteeBase Document.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
    public ActionForward printCommitteeDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        CommitteeDocumentBase committeeDocument = committeeForm.getCommitteeDocument();
        Boolean printRooster = committeeForm.getCommitteeHelper().getPrintRooster();
        Boolean printFutureScheduledMeeting = committeeForm.getCommitteeHelper().getPrintFutureScheduledMeeting();
        
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        if (isAuthorized(task)) {
            List<Printable> correspondencePrintables = getCorrespondencePrintingService().getCorrespondencePrintable(committeeDocument.getCommittee(), committeeForm.getCommitteeHelper().getCorrespondencesToPrint());
            Boolean printCorrespondence = !correspondencePrintables.isEmpty();
            CommitteeActionPrintCommitteeDocumentEvent event = new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                    printRooster, printFutureScheduledMeeting);
            event.setPrintCorrespondence(printCorrespondence);
            String committeeId = committeeForm.getCommitteeDocument().getCommittee().getCommitteeId();
            if (applyRules(event)) {
                AbstractPrint printable;
                List<Printable> printableArtifactList = new ArrayList<Printable>();
                if (printRooster) {
                    printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER, committeeId);
                    printable.setPrintableBusinessObject(committeeForm.getCommitteeDocument().getCommittee());
                    committeeForm.getCommitteeDocument().getCommittee().setPrintRooster(true);
                    printableArtifactList.add(printable);
                }
                if (printFutureScheduledMeeting) {
                    printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS, committeeId);
                    printable.setPrintableBusinessObject(committeeForm.getCommitteeDocument().getCommittee());
                    committeeForm.getCommitteeDocument().getCommittee().setPrintRooster(false);
                    printableArtifactList.add(printable);
                }
                printableArtifactList.addAll(correspondencePrintables);

                AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
                if (dataStream.getData() != null) {
                    streamToResponse(dataStream, response);
                    actionForward = RESPONSE_ALREADY_HANDLED;
                }
            }
        }

        return actionForward;
    }
    
    protected abstract CorrespondencePrintingService getCorrespondencePrintingService();
    
    protected abstract CommitteeBatchCorrespondenceServiceBase getCommitteeBatchCorrespondenceService();
    
    protected abstract CommitteePrintingServiceBase getCommitteePrintingService();

    protected abstract CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao();

    
}
