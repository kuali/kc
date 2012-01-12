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
package org.kuali.kra.committee.web.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.committee.rule.event.CommitteeActionViewBatchCorrespondenceEvent;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;



/**
 * The CommitteeActionsAction corresponds to the Actions tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeActionsAction extends CommitteeAction {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeActionsAction.class);

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
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
        String committeeId = committeeDocument.getCommittee().getCommitteeId();
        String batchCorrespondenceTypeCode = committeeForm.getCommitteeHelper().getGenerateBatchCorrespondenceTypeCode();
        Date startDate = committeeForm.getCommitteeHelper().getGenerateStartDate();
        Date endDate = committeeForm.getCommitteeHelper().getGenerateEndDate();
        
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        if (isAuthorized(task)) {
            if (applyRules(new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                    batchCorrespondenceTypeCode, startDate, endDate, committeeId))) {
                committeeForm.getCommitteeHelper().getGenerateBatchCorrespondence().clear();
                committeeForm.getCommitteeHelper().getGenerateBatchCorrespondence().add(
                        getCommitteeBatchCorrespondenceService().generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate));
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
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

        CommitteeForm committeeForm = (CommitteeForm) form;
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
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        List<CommitteeBatchCorrespondence> committeeBatchCorrespondences = committeeForm.getCommitteeHelper()
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
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        List<CommitteeBatchCorrespondence> committeeBatchCorrespondences = committeeForm.getCommitteeHelper()
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
    private ActionForward viewBatchCorrespondence(ActionMapping mapping, CommitteeForm committeeForm, 
            List<CommitteeBatchCorrespondence> committeeBatchCorrespondences, boolean viewBatch, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        List<String> bookmarksList = new ArrayList<String>();
        List<byte[]> pdfBaosList = new ArrayList<byte[]>();

        if (applyRules(new CommitteeActionViewBatchCorrespondenceEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), committeeBatchCorrespondences, viewBatch))) {
            for (CommitteeBatchCorrespondence committeeBatchCorrespondence : committeeBatchCorrespondences) {
                for (CommitteeBatchCorrespondenceDetail committeeBatchCorrespondenceDetail : committeeBatchCorrespondence
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
        ((CommitteeForm)form).getCommitteeHelper().prepareView();
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
     * This method is perform the action - Print Committee Document.
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
        CommitteeForm committeeForm = (CommitteeForm) form;
        CommitteeDocument committeeDocument = committeeForm.getCommitteeDocument();
        Boolean printRooster = committeeForm.getCommitteeHelper().getPrintRooster();
        Boolean printFutureScheduledMeeting = committeeForm.getCommitteeHelper().getPrintFutureScheduledMeeting();
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        if (isAuthorized(task)) {
            if (applyRules(new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                    printRooster, printFutureScheduledMeeting))) {
                AbstractPrint printable;
                List<Printable> printableArtifactList = new ArrayList<Printable>();
                if (printRooster) {
                    printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER);
                    printable.setPrintableBusinessObject(committeeForm.getCommitteeDocument().getCommittee());
                    committeeForm.getCommitteeDocument().getCommittee().setPrintRooster(true);
                    printableArtifactList.add(printable);
                }
                if (printFutureScheduledMeeting) {
                    printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS);
                    printable.setPrintableBusinessObject(committeeForm.getCommitteeDocument().getCommittee());
                    committeeForm.getCommitteeDocument().getCommittee().setPrintRooster(false);
                    printableArtifactList.add(printable);
                }
                AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
                if (dataStream.getContent() != null) {
                    streamToResponse(dataStream, response);
                    actionForward = RESPONSE_ALREADY_HANDLED;
                }
            }
        }

        return actionForward;
    }
    
    private CommitteeBatchCorrespondenceService getCommitteeBatchCorrespondenceService() {
        return KraServiceLocator.getService(CommitteeBatchCorrespondenceService.class);
    }
    
    private CommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }

    private CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KraServiceLocator.getService(CommitteeBatchCorrespondenceDao.class);
    }
    
}
