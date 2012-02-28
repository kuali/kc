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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.ProposalComment;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepadBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLogUtils;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteAddEvent;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteEventBase.ErrorType;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcAttachmentService;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.bo.Attachment;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.NoteType;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class...
 */
public class InstitutionalProposalHomeAction extends InstitutionalProposalAction {
    private static final Log LOG = LogFactory.getLog(InstitutionalProposalHomeAction.class);
    private static final String VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";

    private InstitutionalProposalNotepadBean institutionalProposalNotepadBean;
    private KcAttachmentService kcAttachmentService;
    private ParameterService parameterService;

    /**
     * Constructs a InstitutionalProposalHomeAction.java.
     */
    public InstitutionalProposalHomeAction() {
        institutionalProposalNotepadBean = new InstitutionalProposalNotepadBean();
    }

    /**
     * This method is used to add a new Award Cost Share.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        if (applyRules(new InstitutionalProposalNoteAddEvent(Constants.EMPTY_STRING, ((InstitutionalProposalForm) form)
                .getDocument(), institutionalProposalForm.getInstitutionalProposalNotepadBean()
                .getNewInstitutionalProposalNotepad(), ErrorType.HARDERROR))) {
            InstitutionalProposalNotepad notepad = institutionalProposalForm.getInstitutionalProposalNotepadBean().getNewInstitutionalProposalNotepad();
            BusinessObjectEntry businessObjectEntry = (BusinessObjectEntry) KRADServiceLocatorWeb.getDataDictionaryService().getDataDictionary().getBusinessObjectEntry("org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad");
            if (institutionalProposalForm.getAttachmentFile() != null) {
                if (!businessObjectEntry.isBoNotesEnabled()) {
                    throw new RuntimeException("to add attachments, the DD file for InstitutionalProposalNotepad must be configured for boNotesEnabled=true");
                }
                else {
                    // FIXME: Rice 2.0 upgrade.  since this is a new bo, there is no objid generated yet.
                    // so noteservice.getByRemoteObjectId should not work.  there is no other option to get this list, and it seemed that there should be no list.
                    /*
                   List<Note> boNotes = notepad.getBoNotes();
                    if (!boNotes.isEmpty()) {
                        throw new IllegalStateException("IP already has an attachment for the note"); 
                    }
                    */
                    
                    FormFile attachmentFile = institutionalProposalForm.getAttachmentFile();
                    
                    if (attachmentFile == null) {
                        GlobalVariables.getMessageMap().putError(
                                String.format("%s.%s",
                                        KRADConstants.NEW_DOCUMENT_NOTE_PROPERTY_NAME,
                                        KRADConstants.NOTE_ATTACHMENT_FILE_PROPERTY_NAME),
                                RiceKeyConstants.ERROR_UPLOADFILE_NULL);
                    }
                    
                    KcAttachmentService attachmentService = getKcAttachmentService();
                    // Checking attachment file name for invalid characters.
                    String invalidCharacters = attachmentService.getInvalidCharacters(attachmentFile.getFileName());
                    if (ObjectUtils.isNotNull(invalidCharacters)) {
                        String parameter = getParameterService().
                            getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);    
                        
                        if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                            GlobalVariables.getMessageMap().putError(Constants.INVALID_FILE_NAME_ERROR_TAB, KeyConstants.INVALID_FILE_NAME, 
                                                                    attachmentFile.getFileName(), invalidCharacters);
                        } else {
                            GlobalVariables.getMessageMap().putWarning(Constants.INVALID_FILE_NAME_ERROR_TAB, KeyConstants.INVALID_FILE_NAME, 
                                                                    attachmentFile.getFileName(), invalidCharacters);
                        }
                    }
                    
                    
                    Note newNote = new Note();
                    newNote.setNoteText("Default text, will never be shown to user.");
                    newNote.setNoteTypeCode(NoteType.BUSINESS_OBJECT.getCode());
                    newNote.setNotePostedTimestampToCurrent();
                    Attachment attachment = null;
                    if (attachmentFile != null && !StringUtils.isBlank(attachmentFile.getFileName())) {
                        if (attachmentFile.getFileSize() == 0) {
                            GlobalVariables.getMessageMap().putError(
                                    String.format("%s.%s",
                                            KRADConstants.NEW_DOCUMENT_NOTE_PROPERTY_NAME,
                                            KRADConstants.NOTE_ATTACHMENT_FILE_PROPERTY_NAME),
                                    RiceKeyConstants.ERROR_UPLOADFILE_EMPTY,
                                    attachmentFile.getFileName());
                        }
                        else {
                            String attachmentType = null;
                            attachment = getAttachmentService().createAttachment(notepad, attachmentFile.getFileName(), attachmentFile.getContentType(), attachmentFile.getFileSize(), attachmentFile.getInputStream(), attachmentType);
                        }
                        // create a new note from the data passed in
                        Note tmpNote = getNoteService().createNote(newNote, notepad, GlobalVariables.getUserSession().getPrincipalId());
                        tmpNote.addAttachment(attachment);
                        notepad.getAttachments().add(tmpNote);
                    }
                }
            }
            institutionalProposalNotepadBean.addNote(((InstitutionalProposalForm) form).getInstitutionalProposalNotepadBean());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to update notedPad values
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward updateNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is for selecting all keywords if javascript is disabled on a browser. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return Basic ActionForward
     * @throws Exception
     */
    public ActionForward selectAllScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        List<InstitutionalProposalScienceKeyword> keywords = institutionalProposalDocument.getInstitutionalProposal().getKeywords();
        for (InstitutionalProposalScienceKeyword institutionalProposalScienceKeyword : keywords) {
            institutionalProposalScienceKeyword.setSelectKeyword(true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to delete selected keywords from the keywords list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        KeywordsService keywordsService = KraServiceLocator.getService(KeywordsService.class);
        keywordsService.deleteKeyword(institutionalProposalDocument.getInstitutionalProposal()); 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the totals on Financial panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This takes care of populating the ScienceKeywords in keywords list after the selected Keywords returns from <code>multilookup</code>
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, 
     *                                                                          org.apache.struts.action.ActionForm, 
     *                                                                          javax.servlet.http.HttpServletRequest, 
     *                                                                          javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
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

    /**
     * 
     * This method...
     * @return
     */
    @SuppressWarnings("unchecked")
    protected KeywordsService getKeywordService(){
        return KraServiceLocator.getService(KeywordsService.class);
    }

    /**
     * 
     * Clears the Mailing Name and Address selected from Delivery info panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
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
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposal institutionalProposal = institutionalProposalDocument.getInstitutionalProposal();
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
        InstitutionalProposalComment ipCmt = new InstitutionalProposalComment(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE);
        ipCmt.setCommentType(ct);
        ipCmt.setComments(proposalLog.getComments());
        ipCmt.setProposalNumber(proposalLog.getProposalNumber()); 
        ipCmt.setProposalId(ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().getProposalId());
        ipCmt.setInstitutionalProposal(ipForm.getInstitutionalProposalDocument().getInstitutionalProposal());
        ipCmt.getInstitutionalProposal().setProposalNumber(proposalLog.getProposalNumber());
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().add(ipCmt);
        ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().setProposalNumber(proposalLog.getProposalNumber());
        if (proposalLog != null) {
            ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().doProposalLogDataFeed(proposalLog);
            // getProposalLogService().promoteProposalLog(proposalLog.getProposalNumber());
        }
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
            //  ipForm.getInstitutionalProposalDocument().getInstitutionalProposal().doProposalLogDataFeed(proposalLog);
            getProposalLogService().promoteProposalLog(proposalLog.getProposalNumber());
            this.getNegotationService().promoteProposalLogNegotiation(proposalLog.getProposalNumber(), ip.getProposalNumber());
        }
        ip.setSponsorNihMultiplePi(getSponsorService().isSponsorNihMultiplePi(ip));
        return forward;
    }   
    
    private NegotiationService getNegotationService() {
        return KraServiceLocator.getService(NegotiationService.class);
    }

    private ProposalLog retrieveProposalLog(String proposalNumber) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        return (ProposalLog) getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
    }
    
    private InstitutionalProposal findPendingVersion(String proposalNumber) {
        return getInstitutionalProposalVersioningService().getPendingInstitutionalProposalVersion(proposalNumber);
    }

    private ActionForward createAndSaveNewVersion(HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm,
            InstitutionalProposalDocument institutionalProposalDocument, InstitutionalProposal institutionalProposal) throws VersionException, 
                                                                                                         WorkflowException, 
                                                                                                         IOException {
        InstitutionalProposal newVersion = getVersioningService().createNewVersion(institutionalProposal);
        newVersion.setProposalSequenceStatus(VersionStatus.PENDING.toString());
        newVersion.setAwardFundingProposals(transferFundingProposals(institutionalProposal, newVersion));
        InstitutionalProposalDocument newInstitutionalProposalDocument = 
            (InstitutionalProposalDocument) getDocumentService().getNewDocument(InstitutionalProposalDocument.class);
        newInstitutionalProposalDocument.getDocumentHeader().setDocumentDescription(institutionalProposalDocument.getDocumentHeader().getDocumentDescription());
        newInstitutionalProposalDocument.setInstitutionalProposal(newVersion);
        getDocumentService().saveDocument(newInstitutionalProposalDocument);
        //getVersionHistoryService().createVersionHistory(newVersion, VersionStatus.PENDING, GlobalVariables.getUserSession().getPrincipalName());
        reinitializeForm(institutionalProposalForm, newInstitutionalProposalDocument);
        response.sendRedirect(makeDocumentOpenUrl(newInstitutionalProposalDocument));
        return null;
    }
 
    /**
     * This method prepares the AwardForm with the document found via the Award lookup
     * Because the helper beans may have preserved a different AwardForm, we need to reset these too
     * @param awardForm
     * @param document
     */
    private void reinitializeForm(InstitutionalProposalForm institutionalProposalForm, InstitutionalProposalDocument document) throws WorkflowException {
        institutionalProposalForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        institutionalProposalForm.setDocument(document);
        // document.setDocumentSaveAfterAwardLookupEditOrVersion(true);
        institutionalProposalForm.initialize();
    }

    private String makeDocumentOpenUrl(InstitutionalProposalDocument newInstitutionalProposalDocument) {
        String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
        String url = String.format("%s/DocHandler.do?command=displayDocSearchView&docId=%s", 
                workflowUrl, newInstitutionalProposalDocument.getDocumentNumber());
        return url;
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
            response.sendRedirect(makeDocumentOpenUrl(institutionalProposalForm.getInstitutionalProposalDocument()));
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
    
    protected VersioningService getVersioningService() {
        return KraServiceLocator.getService(VersioningService.class);
    }
    
    protected InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KraServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }
    
    protected ProposalLogService getProposalLogService() {
        return KraServiceLocator.getService(ProposalLogService.class);
    }
    
    /**
     * This method gets the attachment service
     * @return
     */
    protected KcAttachmentService getKcAttachmentService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KraServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }
    /**
     * Gets the parameter service.
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#getParameterService()
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null ) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);             
        }
        return this.parameterService;
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

    /*
     * copy AwardFundingProposal objects from old IP to new one, deactivating old ones in the process.
     */
    private ArrayList<AwardFundingProposal> transferFundingProposals(InstitutionalProposal oldIP, InstitutionalProposal newIP) {
        ArrayList<AwardFundingProposal> newFundingProposals = new ArrayList<AwardFundingProposal>();
        for (AwardFundingProposal afpp:oldIP.getAwardFundingProposals()) {
            newFundingProposals.add(new AwardFundingProposal(afpp.getAward(), newIP));
            afpp.setActive(false);
        }
        return newFundingProposals;
    }
}