/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.keyword.KeywordsService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.*;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogUtils;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteAddEvent;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteEventBase.ErrorType;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalNoteAttachmentService;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.bo.Attachment;
import org.kuali.rice.krad.exception.AuthenticationException;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalHomeAction extends InstitutionalProposalAction {
    private static final String VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";
    private static final String PENDING = "PENDING";

    private KcAttachmentService kcAttachmentService;
    private ParameterService parameterService;
    private InstitutionalProposalService institutionalProposalService;
    private InstitutionalProposalNoteAttachmentService institutionalProposalNoteAttachmentService;
    private KeywordsService keywordsService;
    private NegotiationService negotiationService;
    private VersioningService versioningService;
    private InstitutionalProposalVersioningService institutionalProposalVersioningService;
    private ProposalLogService proposalLogService;

    public InstitutionalProposalHomeAction() {
    }

    /**
     * This method is used to add a new Award Cost Share.
     */
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        ApplicationTask task = new ApplicationTask(TaskName.ADD_IACUC_PROTOCOL_NOTES);
        if (isAuthorized(task)) {
            if (applyRules(new InstitutionalProposalNoteAddEvent(Constants.EMPTY_STRING, ((InstitutionalProposalForm) form)
                    .getDocument(), institutionalProposalForm.getInstitutionalProposalNotepadBean()
                    .getNewInstitutionalProposalNotepad(), ErrorType.HARDERROR))) {
                return getInstitutionalProposalNoteAttachmentService().addNote(mapping, institutionalProposalForm);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to update notedPad values.
     */
    public ActionForward updateNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to delete an existing note/attachment.
     */
    public ActionForward deleteNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationTask task = new ApplicationTask(TaskName.ADD_IACUC_PROTOCOL_NOTES);
        if (isAuthorized(task)) {
            int selection = getLineToDelete(request);
            InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
            return getInstitutionalProposalNoteAttachmentService().deleteNote(mapping, institutionalProposalForm, selection);
        }
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is for selecting all keywords if javascript is disabled on a browser.
     */
    public ActionForward selectAllScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        List<InstitutionalProposalScienceKeyword> keywords = institutionalProposalDocument.getInstitutionalProposal().getKeywords();
        keywords.stream().forEach(institutionalProposalScienceKeyword -> institutionalProposalScienceKeyword.setSelectKeyword(true));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to delete selected keywords from the keywords list. 
     * It uses {@link KeywordsService} to process the request
     */
    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        getKeywordService().deleteKeyword(institutionalProposalDocument.getInstitutionalProposal());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the totals on Financial panel.
     */
    public ActionForward recalculateTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This takes care of populating the ScienceKeywords in keywords list after the selected Keywords returns from <code>multilookup</code>
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        InstitutionalProposalForm propMultiLookupForm = (InstitutionalProposalForm) form;
        String lookupResultsBOClassName = request.getParameter(KRADConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KRADConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        propMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        propMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        InstitutionalProposal prop = propMultiLookupForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        getKeywordService().addKeywords(prop, propMultiLookupForm);
       
        //load the rolodex if we have one in case we just searched for it
        if (prop.getRolodexId() != null) {
            prop.refreshReferenceObject("rolodex");
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    protected KeywordsService getKeywordService(){
        if (keywordsService == null){
            keywordsService = KcServiceLocator.getService(KeywordsService.class);
        }

        return keywordsService;
    }

    public void setKeywordsService(KeywordsService keywordsService) {
        this.keywordsService = keywordsService;
    }

    /**
     * 
     * Clears the Mailing Name and Address selected from Delivery info panel.
     */
    public ActionForward clearMailingNameAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        if (institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex() != null) {
        
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().setRolodexId(null);
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().setRolodex(null);
        }
        return mapping.findForward("basic");
    }

    /**
     * This method is used to handle the edit button action on an ACTIVE Institutional Proposal. If no Pending version exists for the same
     * proposalNumber, a new IP version is created. If a Pending version exists, the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending version InstitutionalProposalDocument to be opened. Answering No 
     * simply returns the user to the ACTIVE document screen
     */
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposal institutionalProposal = institutionalProposalDocument.getInstitutionalProposal();

        if (institutionalProposal.getProposalSequenceStatus().equalsIgnoreCase(PENDING)) {
            response.sendRedirect(buildForwardUrl(institutionalProposalForm.getDocId()));
            return null;
        }

        InstitutionalProposal pendingProposal = findPendingVersion(institutionalProposal.getProposalNumber());

        ActionForward forward;
        if (pendingProposal != null) {
            Object question = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            forward = question == null ? showPromptForEditingPendingVersion(mapping, institutionalProposalForm, request, response) :
                                         processPromptForEditingPendingVersionResponse(mapping, request, response, institutionalProposalForm, pendingProposal);
        } else {
            forward = createAndSaveNewVersion(response, institutionalProposalForm, institutionalProposalDocument, institutionalProposal);
        }
        return forward;
    }

    @Override
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);      
        InstitutionalProposalForm ipForm = (InstitutionalProposalForm) kualiDocumentFormBase;
        ProposalLog proposalLog = retrieveProposalLog(ipForm.getProposalNumber());
        
        // sets ip deadline date to prop log deadline date
        CommentType ct = new CommentType();
        ct.setCommentTypeCode(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE);
        ct.setDescription("description");
        ct.setChecklistFlag(false);
        ct.setTemplateFlag(false);
        ct.setAwardCommentScreenFlag(false);
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().setDeadlineDate(proposalLog.getDeadlineDate());
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().setDeadlineTime(proposalLog.getDeadlineTime());
        InstitutionalProposalComment ipCmt = new InstitutionalProposalComment(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE);
        ipCmt.setCommentType(ct);
        ipCmt.setComments(proposalLog.getComments());
        ipCmt.setProposalNumber(proposalLog.getProposalNumber()); 
        ipCmt.setProposalId(ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().getProposalId());
        ipCmt.setInstitutionalProposal(ipForm.getInstitutionalProposalDocument().getInstitutionalProposal());
        ipCmt.getInstitutionalProposal().setProposalNumber(proposalLog.getProposalNumber());
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().add(ipCmt);
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().setProposalNumber(proposalLog.getProposalNumber());
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().doProposalLogDataFeed(proposalLog);

    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InstitutionalProposalForm ipForm = (InstitutionalProposalForm) form;
        InstitutionalProposal ip = ipForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        if (!ip.getInstitutionalProposalScienceKeywords().isEmpty()) {
            ip.setScienceCodeIndicator("1");
        } else {
            ip.setScienceCodeIndicator("0");
        }
        ActionForward forward = super.save(mapping, form, request, response);
        ProposalLog proposalLog = retrieveProposalLog(ipForm.getProposalNumber());
        if (proposalLog != null && !proposalLog.getLogStatus().equals(ProposalLogUtils.getProposalLogSubmittedStatusCode())) {
            getProposalLogService().promoteProposalLog(proposalLog.getProposalNumber());
            this.getNegotationService().promoteProposalLogNegotiation(proposalLog.getProposalNumber(), ip.getProposalNumber());
        }
        ip.setSponsorNihMultiplePi(getSponsorHierarchyService().isSponsorableNihMultiplePi(ip));
        return forward;
    }   
    
    public NegotiationService getNegotationService() {
        if (negotiationService == null) {
            negotiationService = KcServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    private ProposalLog retrieveProposalLog(String proposalNumber) {
        Map<String, String> criteria = new HashMap<>();
        criteria.put("proposalNumber", proposalNumber);
        return getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
    }
    
    private InstitutionalProposal findPendingVersion(String proposalNumber) {
        return getInstitutionalProposalVersioningService().getPendingInstitutionalProposalVersion(proposalNumber);
    }

    private ActionForward createAndSaveNewVersion(HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm,
            InstitutionalProposalDocument institutionalProposalDocument, InstitutionalProposal institutionalProposal) throws VersionException, 
                                                                                                         WorkflowException, 
                                                                                                         IOException {

        InstitutionalProposalDocument newInstitutionalProposalDocument = getInstitutionalProposalService().createAndSaveNewVersion(institutionalProposal, institutionalProposalDocument);
        reinitializeForm(institutionalProposalForm, newInstitutionalProposalDocument);
       
        return new ActionRedirect(buildForwardUrl(newInstitutionalProposalDocument.getDocumentNumber()));
    }
    
    
    
    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }
        return institutionalProposalService;
    }
    
    
    /**
     * This method prepares the AwardForm with the document found via the Award lookup
     * Because the helper beans may have preserved a different AwardForm, we need to reset these too
     */
    private void reinitializeForm(InstitutionalProposalForm institutionalProposalForm, InstitutionalProposalDocument document) throws WorkflowException {
        institutionalProposalForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        institutionalProposalForm.setDocument(document);
        institutionalProposalForm.initialize();
    }

    private ActionForward showPromptForEditingPendingVersion(ActionMapping mapping, InstitutionalProposalForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.performQuestionWithoutInput(mapping, form, request, response, "EDIT_OR_VERSION_QUESTION_ID", getResources(
                request).getMessage(VERSION_EDITPENDING_PROMPT_KEY), KRADConstants.CONFIRMATION_QUESTION,
                KRADConstants.MAPPING_CANCEL, "");
    }

    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm, InstitutionalProposal institutionalProposal) throws WorkflowException, IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        if (ConfirmationQuestion.NO.equals(buttonClicked)) {
            forward = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            initializeFormWithInstutitionalProposal(institutionalProposalForm, institutionalProposal);
            response.sendRedirect(buildForwardUrl(institutionalProposalForm.getInstitutionalProposalDocument().getDocumentNumber()));
            forward = null;
        }
        return forward;
    }
    
    private void initializeFormWithInstutitionalProposal(InstitutionalProposalForm institutionalProposalForm, InstitutionalProposal institutionalProposal) throws WorkflowException {
        reinitializeForm(institutionalProposalForm, findDocumentForInstitutionalProposal(institutionalProposal));
    }

    private InstitutionalProposalDocument findDocumentForInstitutionalProposal(InstitutionalProposal institutionalProposal) throws WorkflowException {
        InstitutionalProposalDocument document = (InstitutionalProposalDocument) getDocumentService().getByDocumentHeaderId(
                institutionalProposal.getInstitutionalProposalDocument().getDocumentNumber());
        document.setInstitutionalProposal(institutionalProposal);
        return document;
    }

    @Override
    public ActionForward downloadBOAttachment(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        int attachmentIndex = selectedAttachmentIndex(request);
        InstitutionalProposalForm ipForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = ipForm.getInstitutionalProposalDocument();
        if (!WebUtils.canViewNoteAttachment(institutionalProposalDocument, null)) {
            throw new AuthenticationException("Unable to view attachment.");
        }
        List<InstitutionalProposalNotepad> notepads = institutionalProposalDocument.getInstitutionalProposal().getInstitutionalProposalNotepads();

        InstitutionalProposalNotepad noteParent = notepads.get(attachmentIndex);


        if (attachmentIndex >= 0) {
            Attachment attachment = noteParent.getAttachments().get(0).getAttachment();
            // since we're downloading a file, all of the editable properties from the previous request will continue to be editable.
            KualiDocumentFormBase documentForm = (KualiDocumentFormBase) form;
            documentForm.copyPopulateEditablePropertiesToActionEditableProperties();

            WebUtils.saveMimeInputStreamAsFile(response, attachment.getAttachmentMimeTypeCode(), attachment.getAttachmentContents(), attachment.getAttachmentFileName(), attachment.getAttachmentFileSize().intValue());
            return null;
        }

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }
    
    protected VersioningService getVersioningService() {
        if (versioningService == null) {
            versioningService = KcServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }
    
    protected InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        if (institutionalProposalVersioningService == null){
            institutionalProposalVersioningService = KcServiceLocator.getService(InstitutionalProposalVersioningService.class);
        }
        return institutionalProposalVersioningService;
    }

    public void setInstitutionalProposalVersioningService(InstitutionalProposalVersioningService institutionalProposalVersioningService) {
        this.institutionalProposalVersioningService = institutionalProposalVersioningService;
    }

    protected ProposalLogService getProposalLogService() {
        if (proposalLogService == null) {
            proposalLogService = KcServiceLocator.getService(ProposalLogService.class);
        }
        return proposalLogService;
    }
    
    public InstitutionalProposalNoteAttachmentService getInstitutionalProposalNoteAttachmentService() {
        if (institutionalProposalNoteAttachmentService == null) {
            institutionalProposalNoteAttachmentService = KcServiceLocator.getService(InstitutionalProposalNoteAttachmentService.class);
        }
        return institutionalProposalNoteAttachmentService;
    }

    protected KcAttachmentService getKcAttachmentService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null ) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}
