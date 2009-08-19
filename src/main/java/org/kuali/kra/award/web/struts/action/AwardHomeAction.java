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
package org.kuali.kra.award.web.struts.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;


/**
 * 
 * This class represents the Struts Action for Award page(AwardHome.jsp) 
 */
public class AwardHomeAction extends AwardAction { 
    
    private static final String AWARD_VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    private ApprovedSubawardActionHelper approvedSubawardActionHelper;
    
    public AwardHomeAction(){
        approvedSubawardActionHelper = new ApprovedSubawardActionHelper();
    }

    public ActionForward addFundingProposal(ActionMapping mapping, ActionForm form, 
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getFundingProposalBean().addFundingProposal();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to add a new Award Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addApprovedSubaward(ActionMapping mapping, ActionForm form, 
                                                HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        approvedSubawardActionHelper.addApprovedSubaward(((AwardForm) form).getApprovedSubawardFormHelper());        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>AwardApprovedSubaward</code> to
     * <code>Award</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param awardApprovedSubaward
     * @return
     */
    boolean addApprovedSubawardToAward(Award award, AwardApprovedSubaward awardApprovedSubaward){
        return award.getAwardApprovedSubawards().add(awardApprovedSubaward);
    }
    
    /**
     * This method is used to delete an Award Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteApprovedSubaward(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request,
                                                         HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int delApprovedSubaward = getLineToDelete(request);
        awardDocument.getAward().getAwardApprovedSubawards().remove(delApprovedSubaward);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
     
    }
    
    /**
     * 
     * This method is a convenience method for deleting an <code>AwardApprovedSubaward</code> from
     * <code>Award</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteApprovedSubawardFromAward(Award award, int lineToDelete){
        award.getAwardApprovedSubawards().remove(lineToDelete);
        return true;
    }
    
    /**
     * 
     * This method opens a document in read-only mode
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward open(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form;
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate")
            && StringUtils.isNotBlank(request.getParameter(AWARD_ID_PARAMETER_NAME))) {
            Award award = findSelectedAward(request.getParameter(AWARD_ID_PARAMETER_NAME));
            initializeFormWithAward(awardForm, award);
        }
        
        return actionForward;
    }
    
    /**
     * This method is used to recalculate the Total Subaward amount in the Subaward panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateSubawardTotal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        approvedSubawardActionHelper.recalculateSubawardTotal(((AwardForm) form).getApprovedSubawardFormHelper());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
   
    /**
     * 
     * @see org.kuali.kra.award.web.struts.action.AwardAction#execute(org.apache.struts.action.ActionMapping, 
     *                                                                 org.apache.struts.action.ActionForm, 
     *                                                                 javax.servlet.http.HttpServletRequest, 
     *                                                                 javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form; 
        String commandParam = request.getParameter(KNSConstants.PARAMETER_COMMAND);
        if (StringUtils.isNotBlank(commandParam) && commandParam.equals("initiate")
            && StringUtils.isNotBlank(request.getParameter(AWARD_ID_PARAMETER_NAME))) {
            Award award = findSelectedAward(request.getParameter(AWARD_ID_PARAMETER_NAME));
            initializeFormWithAward(awardForm, award);
        }
        
        return actionForward;
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
        AwardForm awardMultiLookupForm = (AwardForm) form;
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        awardMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        awardMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        Award awardDocument = awardMultiLookupForm.getAwardDocument().getAward();
        getKeywordService().addKeywords(awardDocument, awardMultiLookupForm);
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

        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        List<AwardScienceKeyword> keywords = awardDocument.getAward().getKeywords();
        for (AwardScienceKeyword awardScienceKeyword : keywords) {
            awardScienceKeyword.setSelectKeyword(true);
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
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        KeywordsService keywordsService = KraServiceLocator.getService(KeywordsService.class);
        keywordsService.deleteKeyword(awardDocument.getAward()); 
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        
        AwardForm awardForm = ((AwardForm)form);
        AwardDocument awardDocument = awardForm.getAwardDocument();
        Award award = awardDocument.getAward();
        VersionHistory foundPending = findPendingVersion(award);
        
        ActionForward forward;
        if(foundPending != null) {
            Object question = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            forward = question == null ? showPromptForEditingPendingVersion(mapping, form, request, response) :
                                         processPromptForEditingPendingVersionResponse(mapping, request, response, awardForm, foundPending);
        } else {
            forward = createAndSaveNewAwardVersion(response, awardForm, awardDocument, award);
        }
        return forward;
    }

    private ActionForward createAndSaveNewAwardVersion(HttpServletResponse response, AwardForm awardForm,
                                                        AwardDocument awardDocument, Award award) throws VersionException, 
                                                                                                         WorkflowException, 
                                                                                                         IOException {
        Award newVersion = getVersioningService().createNewVersion(award);
        AwardDocument newAwardDocument = (AwardDocument) getDocumentService().getNewDocument(AwardDocument.class);
        newAwardDocument.getDocumentHeader().setDocumentDescription(awardDocument.getDocumentHeader().getDocumentDescription());
        newAwardDocument.setAward(newVersion);
        getDocumentService().saveDocument(newAwardDocument);
        getVersionHistoryService().createVersionHistory(newVersion, VersionStatus.PENDING, GlobalVariables.getUserSession().getPrincipalName());
        reinitializeAwardForm(awardForm, newAwardDocument);
        response.sendRedirect(makeDocumentOpenUrl(newAwardDocument));
        return null;
    }
    
    /**
     * 
     * This method adds a new AwardTransferringSponsor to the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().addAwardTransferringSponsor();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** 
     * This method removes an AwardTransferringSponsor from the list. 
     * It uses {@link KeywordsService} to process the request 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().deleteAwardTransferringSponsor(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method deletes a Funding proposal
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardFundingProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((AwardForm) form).getFundingProposalBean().deleteAwardFundingProposal(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * @return
     */
    protected VersioningService getVersioningService() {
        return KraServiceLocator.getService(VersioningService.class);
    }
    
    /**
     * This method locates an award for the specified awardId
     * @param awardId
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Award findSelectedAward(String awardId) {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put(AWARD_ID_PARAMETER_NAME, awardId);
        List<Award> awards = (List<Award>) getBusinessObjectService().findMatching(Award.class, fieldMap);
        return (Award) awards.get(0);
    }

    private void initializeFormWithAward(AwardForm awardForm, Award award) throws WorkflowException {
        reinitializeAwardForm(awardForm, findDocumentForAward(award));
    }

    private AwardDocument findDocumentForAward(Award award) throws WorkflowException {
        AwardDocument document = (AwardDocument) getDocumentService().getByDocumentHeaderId(award.getAwardDocument().getDocumentNumber());
        document.setAward(award);
        return document;
    }
    
    /**
     * This method prepares the AwardForm with the document found via the Award lookup
     * Because the helper beans may have preserved a different AwardForm, we need to reset these too
     * @param awardForm
     * @param document
     */
    private void reinitializeAwardForm(AwardForm awardForm, AwardDocument document) throws WorkflowException {
        awardForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        awardForm.setDocument(document);
        document.setDocumentSaveAfterAwardLookupEditOrVersion(true);
        awardForm.initialize();
    }
    
    private String makeDocumentOpenUrl(AwardDocument newAwardDocument) {
        String workflowUrl = getKualiConfigurationService().getPropertyString(KNSConstants.WORKFLOW_URL_KEY);
        return String.format(DOC_HANDLER_URL_PATTERN, workflowUrl, newAwardDocument.getDocumentNumber());
    }
    
    private VersionHistory findPendingVersion(Award award) {
        List<VersionHistory> histories = getVersionHistoryService().loadVersionHistory(Award.class, award.getAwardNumber());
        VersionHistory foundPending = null;
        for(VersionHistory history: histories) {
            if (history.getStatus() == VersionStatus.PENDING && award.getSequenceNumber() < history.getSequenceOwnerSequenceNumber()) {
                foundPending = history;
                break;
            }
        }
        return foundPending;
    }

    private ActionForward showPromptForEditingPendingVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                                HttpServletResponse response) throws Exception {
        return this.performQuestionWithoutInput(mapping, form, request, response, "EDIT_OR_VERSION_QUESTION_ID",
                                                getResources(request).getMessage(AWARD_VERSION_EDITPENDING_PROMPT_KEY),
                                                KNSConstants.CONFIRMATION_QUESTION,
                                                KNSConstants.MAPPING_CANCEL, "");
    }
    
    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, AwardForm awardForm, 
            VersionHistory foundPending) throws WorkflowException, 
                                                IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        if (ConfirmationQuestion.NO.equals(buttonClicked)) {
            forward = mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            initializeFormWithAward(awardForm, (Award) foundPending.getSequenceOwner());
            response.sendRedirect(makeDocumentOpenUrl(awardForm.getAwardDocument()));
            forward = null;
        }
        return forward;
    }
}
