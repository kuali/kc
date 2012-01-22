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
package org.kuali.kra.award.web.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingService;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewLinkEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;


/**
 * 
 * This class represents the Struts Action for Award page(AwardHome.jsp) 
 */
public class AwardHomeAction extends AwardAction { 
    
    private static final String AWARD_VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    private ApprovedSubawardActionHelper approvedSubawardActionHelper;
    private ParameterService parameterService;
    
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
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
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
        String commandParam = request.getParameter(KRADConstants.PARAMETER_COMMAND);
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
        GlobalVariables.getUserSession().removeObject(Constants.LINKED_FUNDING_PROPOSALS_KEY);
        AwardForm awardMultiLookupForm = (AwardForm) form;
        String lookupResultsBOClassName = request.getParameter(KRADConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KRADConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        awardMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        awardMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        Award awardDocument = awardMultiLookupForm.getAwardDocument().getAward();
        getKeywordService().addKeywords(awardDocument, awardMultiLookupForm);
        
        awardDocument.refreshReferenceObject("sponsor");
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Override to update funding proposal status as needed
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
      //if award is a root Award and direct/indirect view is enabled, then we need to sum the obligated and anticipated totals until we create
        //initial T&M doc.
        if(awardDocument.getAward().getAwardNumber().endsWith("-00001") && isDirectIndirectViewEnabled()) {
            setTotalsOnAward(awardDocument.getAward());
        }
        
        awardForm.getSpecialReviewHelper().prepareView();
        List<AwardSpecialReview> specialReviews = awardDocument.getAward().getSpecialReviews();
        List<String> linkedProtocolNumbers = awardForm.getSpecialReviewHelper().getLinkedProtocolNumbers();
        boolean isProtocolLinkingEnabled = awardForm.getSpecialReviewHelper().getIsProtocolLinkingEnabled();
        if (isProtocolLinkingEnabled) {
            if (applyRules(new SaveSpecialReviewLinkEvent<AwardSpecialReview>(awardDocument, specialReviews, linkedProtocolNumbers))) {
                awardForm.getSpecialReviewHelper().syncProtocolFundingSourcesWithSpecialReviews();
            }
        }
        awardForm.getProjectPersonnelBean().updateLeadUnit();
        if (this.getReportTrackingService().shouldAlertReportTrackingDetailChange(awardForm.getAwardDocument().getAward())) {
            GlobalVariables.getMessageMap().putWarning("document.awardList[0].awardExecutionDate", 
                    KeyConstants.REPORT_TRACKING_WARNING_UPDATE_FROM_DATE_CHANGE, "");
        }
        ActionForward forward = super.save(mapping, form, request, response);
        
        awardDocument.getAward().refreshReferenceObject("sponsor");
        Award award = awardDocument.getAward();
        award.setSponsorNihMultiplePi(getSponsorService().isSponsorNihMultiplePi(award));
        
        return forward;
    }
    
    private void setTotalsOnAward(Award award) {
        AwardAmountInfo aai = award.getLastAwardAmountInfo();
        aai.setAmountObligatedToDate(aai.getObligatedTotalDirect().add(aai.getObligatedTotalIndirect()));
        aai.setAnticipatedTotalAmount(aai.getAnticipatedTotalDirect().add(aai.getAnticipatedTotalIndirect()));
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // The user is prompted to save when the document is closed.  If they say yes, it will save the document but won't call back to the Action.save.
        // Therefore we need to ensure the Award number is populated or else it will just save the default Award number.
        AwardForm awardForm = (AwardForm) form;
        checkAwardNumber(awardForm.getAwardDocument().getAward());
        return super.close(mapping, awardForm, request, response);
    }
    
    /**
     * Override to add lookup helper params to global variables
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName) && parameterName.indexOf(".performLookup") != -1 && parameterName.contains("InstitutionalProposal")) {
            GlobalVariables.getUserSession().addObject(Constants.LINKED_FUNDING_PROPOSALS_KEY, ((AwardForm) form).getLinkedProposals());
        }

        return super.performLookup(mapping, form, request, response);
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
        ActionForward forward;
        
        AwardDocument parentSyncAward = getAwardSyncService().getAwardLockingHierarchyForSync(awardDocument, GlobalVariables.getUserSession().getPrincipalId()); 
        if (parentSyncAward != null) {
            KNSGlobalVariables.getMessageList().add("error.award.awardhierarchy.sync.locked", parentSyncAward.getDocumentNumber());
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(award, awardForm.getAwardHierarchyNodes())){
            VersionHistory foundPending = findPendingVersion(award);
            cleanUpUserSession();
            if(foundPending != null) {
                Object question = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                forward = question == null ? showPromptForEditingPendingVersion(mapping, form, request, response) :
                                             processPromptForEditingPendingVersionResponse(mapping, request, response, awardForm, foundPending);
            } else {
                forward = createAndSaveNewAwardVersion(response, awardForm, awardDocument, award);
            }    
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }

    private ActionForward createAndSaveNewAwardVersion(HttpServletResponse response, AwardForm awardForm,
                                                        AwardDocument awardDocument, Award award) throws Exception {
        awardForm.getAwardDocument().getAward().setNewVersion(true); 
        AwardDocument newAwardDocument = getAwardService().createNewAwardVersion(awardForm.getAwardDocument());
        getDocumentService().saveDocument(newAwardDocument);
        getAwardService().updateAwardSequenceStatus(newAwardDocument.getAward(), VersionStatus.PENDING);
        getVersionHistoryService().createVersionHistory(newAwardDocument.getAward(), VersionStatus.PENDING,
                GlobalVariables.getUserSession().getPrincipalName());
        reinitializeAwardForm(awardForm, newAwardDocument);
        return new ActionForward(makeDocumentOpenUrl(newAwardDocument), true);
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
    
    protected BudgetSummaryService getBudgetSummaryService() {
        return KraServiceLocator.getService(BudgetSummaryService.class);
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
        String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
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
                                                KRADConstants.CONFIRMATION_QUESTION,
                                                KRADConstants.MAPPING_CANCEL, "");
    }
    
    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, AwardForm awardForm, 
            VersionHistory foundPending) throws WorkflowException, 
                                                IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        if (ConfirmationQuestion.NO.equals(buttonClicked)) {
            forward = mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            initializeFormWithAward(awardForm, (Award) foundPending.getSequenceOwner());
            response.sendRedirect(makeDocumentOpenUrl(awardForm.getAwardDocument()));
            forward = null;
        }
        return forward;
    }
    
    public ActionForward applySponsorTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return fullSyncToAwardTemplate(mapping, form, request, response);
    }
}
