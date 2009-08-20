/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepadBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This class...
 */
public class InstitutionalProposalHomeAction extends InstitutionalProposalAction {
    
    private static final String VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";

    private InstitutionalProposalNotepadBean institutionalProposalNotepadBean;
    
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
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        institutionalProposalNotepadBean.addNote(((InstitutionalProposalForm) form).getInstitutionalProposalNotepadBean());
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
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        propMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        propMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        InstitutionalProposal prop = propMultiLookupForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        getKeywordService().addKeywords(prop, propMultiLookupForm);
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
        
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setAddressLine1("");
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setAddressLine2("");
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setAddressLine3("");
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setCity("");
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setOrganization("");
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getRolodex().setState("");
        
        }
      return mapping.findForward("basic");
    }
    
    /**
     * This method is used to handle the edit button action on an ACTIVE Award. If no Pending version exists for the same
     * awardNumber, a new Award version is created. If a Pending version exists, the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending version AwardDocument to be opened. Answering No 
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
        if(pendingProposal != null) {
            Object question = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            forward = question == null ? showPromptForEditingPendingVersion(mapping, institutionalProposalForm, request, response) :
                                         processPromptForEditingPendingVersionResponse(mapping, request, response, institutionalProposalForm, pendingProposal);
        } else {
            forward = createAndSaveNewAwardVersion(response, institutionalProposalForm, institutionalProposalDocument, institutionalProposal);
        }
        return forward;
    }
    
    private InstitutionalProposal findPendingVersion(String proposalNumber) {
        return getInstitutionalProposalVersioningService().getPendingInstitutionalProposalVersion(proposalNumber);
    }

    private ActionForward createAndSaveNewAwardVersion(HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm,
            InstitutionalProposalDocument institutionalProposalDocument, InstitutionalProposal institutionalProposal) throws VersionException, 
                                                                                                         WorkflowException, 
                                                                                                         IOException {
        InstitutionalProposal newVersion = getVersioningService().createNewVersion(institutionalProposal);
        newVersion.setProposalSequenceStatus(VersionStatus.PENDING.toString());
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
        //document.setDocumentSaveAfterAwardLookupEditOrVersion(true);
        institutionalProposalForm.initialize();
    }
    
    private String makeDocumentOpenUrl(InstitutionalProposalDocument newInstitutionalProposalDocument) {
        String workflowUrl = getKualiConfigurationService().getPropertyString(KNSConstants.WORKFLOW_URL_KEY);
        String url = String.format("%s/DocHandler.do?command=displayDocSearchView&docId=%s", 
                workflowUrl, newInstitutionalProposalDocument.getDocumentNumber());
        return url;
    }
    
    private ActionForward showPromptForEditingPendingVersion(ActionMapping mapping, InstitutionalProposalForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.performQuestionWithoutInput(mapping, form, request, response, "EDIT_OR_VERSION_QUESTION_ID", getResources(
                request).getMessage(VERSION_EDITPENDING_PROMPT_KEY), KNSConstants.CONFIRMATION_QUESTION,
                KNSConstants.MAPPING_CANCEL, "");
    }

    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, InstitutionalProposalForm institutionalProposalForm, InstitutionalProposal institutionalProposal) throws WorkflowException, IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
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
    
}
