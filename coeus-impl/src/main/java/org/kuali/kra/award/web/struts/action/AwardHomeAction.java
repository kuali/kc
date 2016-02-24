/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.keyword.KeywordsService;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewLinkEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardConstants;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * This class represents the Struts Action for Award page(AwardHome.jsp) 
 */
public class AwardHomeAction extends AwardAction {

    private static final String AWARD_VERSION_EDITPENDING_PROMPT_KEY = "message.award.version.editpending.prompt";
    private static final String PENDING = "PENDING";

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
     * This method is used to add a new Award Cost Share.
     */
    public ActionForward addApprovedSubaward(ActionMapping mapping, ActionForm form, 
                                                HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        approvedSubawardActionHelper.addApprovedSubaward(((AwardForm) form).getApprovedSubawardFormHelper());        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to delete an Award Cost Share.
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
     * This method opens a document in read-only mode.
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
        if (StringUtils.isNotBlank(commandParam) && "redirectAwardHistoryFullViewForPopup".equals(commandParam)) {
            String awardDocumentNumber = request.getParameter("awardDocumentNumber");
            String awardNumber = request.getParameter("awardNumber");
            actionForward = redirectAwardHistoryFullViewForPopup(mapping, form, request, response, awardDocumentNumber, awardNumber);
        }
        
        return actionForward;
    }
    

    /**
     * This takes care of populating the ScienceKeywords in keywords list after the selected Keywords returns from <code>multilookup</code>
     */
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
     * Override to update funding proposal status as needed.
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();

        updateCurrentAwardAmountInfo(awardDocument.getAward());
        
        awardForm.getProjectPersonnelBean().updateLeadUnit();
        if (this.getReportTrackingService().shouldAlertReportTrackingDetailChange(awardForm.getAwardDocument().getAward())) {
            GlobalVariables.getMessageMap().putWarning("document.awardList[0].awardExecutionDate", 
                    KeyConstants.REPORT_TRACKING_WARNING_UPDATE_FROM_DATE_CHANGE, "");
        }
        
        boolean isAwardProtocolLinkingEnabled = getParameterService().getParameterValueAsBoolean(
                "KC-PROTOCOL", "Document", "irb.protocol.award.linking.enabled");
        List<AwardSpecialReview> specialReviews = awardDocument.getAward().getSpecialReviews();
        if (!isAwardProtocolLinkingEnabled || applyRules(new SaveSpecialReviewLinkEvent<>(awardDocument, specialReviews))) {
            forward = super.save(mapping, form, request, response);
            
            awardDocument.getAward().refreshReferenceObject("sponsor");
            Award award = awardDocument.getAward();

            persistSpecialReviewProtocolFundingSourceLink(award, isAwardProtocolLinkingEnabled);
        }

        return forward;
    }

    protected void updateCurrentAwardAmountInfo(Award award) {
        if (award.getAwardNumber().endsWith(Award.DEFAULT_AWARD_NUMBER)
        		|| award.getAwardNumber().endsWith(AwardConstants.ROOT_AWARD_SUFFIX)) {
            AwardAmountInfo currentAwardAmountInfo = award.getLastAwardAmountInfo();
            AwardAmountInfo previousAwardAmountInfo = getPreviousAwardAmountInfo(award);
            
            if (isDirectIndirectViewEnabled()) {
                setTotalsOnAward(award);
            }

            if (previousAwardAmountInfo != null) {
                currentAwardAmountInfo.setObligatedChange(currentAwardAmountInfo.getAmountObligatedToDate().subtract(previousAwardAmountInfo.getAmountObligatedToDate()));
                currentAwardAmountInfo.setObligatedChangeDirect(currentAwardAmountInfo.getObligatedTotalDirect().subtract(previousAwardAmountInfo.getObligatedTotalDirect()));
                currentAwardAmountInfo.setObligatedChangeIndirect(currentAwardAmountInfo.getObligatedTotalIndirect().subtract(previousAwardAmountInfo.getObligatedTotalIndirect()));
                currentAwardAmountInfo.setAnticipatedChange(currentAwardAmountInfo.getAnticipatedTotalAmount().subtract(previousAwardAmountInfo.getAnticipatedTotalAmount()));
                currentAwardAmountInfo.setAnticipatedChangeDirect(currentAwardAmountInfo.getAnticipatedTotalDirect().subtract(previousAwardAmountInfo.getAnticipatedTotalDirect()));
                currentAwardAmountInfo.setAnticipatedChangeIndirect(currentAwardAmountInfo.getAnticipatedTotalIndirect().subtract(previousAwardAmountInfo.getAnticipatedTotalIndirect()));
                currentAwardAmountInfo.setObliDistributableAmount(previousAwardAmountInfo.getObliDistributableAmount().add(currentAwardAmountInfo.getObligatedChange()));
                currentAwardAmountInfo.setAntDistributableAmount(previousAwardAmountInfo.getAntDistributableAmount().add(currentAwardAmountInfo.getAnticipatedChange()));
            } else {
                currentAwardAmountInfo.setObligatedChange(currentAwardAmountInfo.getAmountObligatedToDate());
                currentAwardAmountInfo.setObligatedChangeDirect(currentAwardAmountInfo.getObligatedTotalDirect());
                currentAwardAmountInfo.setObligatedChangeIndirect(currentAwardAmountInfo.getObligatedTotalIndirect());
                currentAwardAmountInfo.setAnticipatedChange(currentAwardAmountInfo.getAnticipatedTotalAmount());
                currentAwardAmountInfo.setAnticipatedChangeDirect(currentAwardAmountInfo.getAnticipatedTotalDirect());
                currentAwardAmountInfo.setAnticipatedChangeIndirect(currentAwardAmountInfo.getAnticipatedTotalIndirect());
                currentAwardAmountInfo.setObliDistributableAmount(currentAwardAmountInfo.getAmountObligatedToDate());
                currentAwardAmountInfo.setAntDistributableAmount(currentAwardAmountInfo.getAnticipatedTotalAmount());            	
            }
        }
    }

    protected AwardAmountInfo getPreviousAwardAmountInfo(Award award) {
        int awardAmountInfosSize = award.getAwardAmountInfos().size();
        if (awardAmountInfosSize > 1) {
            int previousAwardAmountInfoIndex = awardAmountInfosSize - 2;
            return award.getAwardAmountInfos().get(previousAwardAmountInfoIndex);
        } else {
            Award oldAward = getActiveAwardVersion(award);
            return oldAward != null ? oldAward.getLastAwardAmountInfo() : null;
        }
    }

	protected Award getActiveAwardVersion(Award award) {
		return getAwardVersionService().getActiveAwardVersion(award.getAwardNumber());
	}

    protected void setTotalsOnAward(final Award award) {
        final AwardAmountInfo aai = award.getLastAwardAmountInfo();
        if (aai == null) {
            return;
        }

        final ScaleTwoDecimal obligatedDirectTotal     = aai.getObligatedTotalDirect() != null ? aai.getObligatedTotalDirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal obligatedIndirectTotal   = aai.getObligatedTotalIndirect() != null ? aai.getObligatedTotalIndirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal anticipatedDirectTotal   = aai.getAnticipatedTotalDirect() != null ? aai.getAnticipatedTotalDirect() : new ScaleTwoDecimal(0);
        final ScaleTwoDecimal anticipatedIndirectTotal = aai.getAnticipatedTotalIndirect() != null ? aai.getAnticipatedTotalIndirect() : new ScaleTwoDecimal(0);

        aai.setAmountObligatedToDate(obligatedDirectTotal.add(obligatedIndirectTotal));
        aai.setAnticipatedTotalAmount(anticipatedDirectTotal.add(anticipatedIndirectTotal));
    }
    
    private void persistSpecialReviewProtocolFundingSourceLink(Award award, boolean isAwardProtocolLinkingEnabled) {
        if (isAwardProtocolLinkingEnabled) {
            SpecialReviewService specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
            
            for (AwardSpecialReview specialReview : award.getSpecialReviews()) {
                if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {                
                    String protocolNumber = specialReview.getProtocolNumber();
                    String fundingSourceNumber = award.getAwardNumber();
                    String fundingSourceTypeCode = FundingSourceType.AWARD;
                    String fundingSourceName = award.getSponsorName();
                    String fundingSourceTitle = award.getTitle();
                    
                    if (!specialReviewService.isLinkedToProtocolFundingSource(protocolNumber, fundingSourceNumber, fundingSourceTypeCode)) {
                        specialReviewService.addProtocolFundingSourceForSpecialReview(
                            protocolNumber, fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
                    }
                }
            }
        }
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // The user is prompted to save when the document is closed.  If they say yes, it will save the document but won't call back to the Action.save.
        // Therefore we need to ensure the Award number is populated or else it will just save the default Award number.
        AwardForm awardForm = (AwardForm) form;
        checkAwardNumber(awardForm.getAwardDocument().getAward());
        return super.close(mapping, awardForm, request, response);
    }
    
    /**
     * Override to add lookup helper params to global variables.
     */
    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName) && parameterName.contains(".performLookup") && parameterName.contains("InstitutionalProposal")) {
            GlobalVariables.getUserSession().addObject(Constants.LINKED_FUNDING_PROPOSALS_KEY, ((AwardForm) form).getLinkedProposals());
        }

        return super.performLookup(mapping, form, request, response);
    }

    @SuppressWarnings("unchecked")
    protected KeywordsService<AwardScienceKeyword> getKeywordService(){
        return KcServiceLocator.getService(KeywordsService.class);
    }
    /**
     * 
     * This method is for selecting all keywords if javascript is disabled on a browser.
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
     * It uses {@link KeywordsService} to process the request.
     */
    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        getKeywordService().deleteKeyword(awardDocument.getAward());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to handle the edit button action on an ACTIVE Award. If no Pending version exists for the same
     * awardNumber, a new Award version is created. If a Pending version exists, the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending version AwardDocument to be opened. Answering No 
     * simply returns the user to the ACTIVE document screen.
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
        
        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(award, awardForm.getAwardHierarchyBean().getRootNode().getAwardNumber())) {
            if (award.getAwardSequenceStatus().equalsIgnoreCase(PENDING)) {
                response.sendRedirect(buildForwardUrl(awardForm.getDocId()));
                return null;
            }
            VersionHistory foundPending = findPendingVersion(award);
            cleanUpUserSession();
            if(foundPending != null) {
                Object question = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                forward = question == null ? showPromptForEditingPendingVersion(mapping, form, request, response) :
                                             processPromptForEditingPendingVersionResponse(mapping, request, response, awardForm, foundPending);
            } else {
                forward = createAndSaveNewAwardVersion(awardForm);
            }    
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }

    private ActionForward createAndSaveNewAwardVersion(AwardForm awardForm) throws Exception {
        awardForm.getAwardDocument().getAward().setNewVersion(true); 
        AwardDocument newAwardDocument = getAwardService().createNewAwardVersion(awardForm.getAwardDocument());
        getDocumentService().saveDocument(newAwardDocument);
        getAwardService().updateAwardSequenceStatus(newAwardDocument.getAward(), VersionStatus.PENDING);
        getVersionHistoryService().updateVersionHistory(newAwardDocument.getAward(), VersionStatus.PENDING,
                GlobalVariables.getUserSession().getPrincipalName());
        reinitializeAwardForm(awardForm, newAwardDocument);
        return new ActionForward(buildForwardUrl(newAwardDocument.getDocumentNumber()), true);
    }
    
    /**
     * 
     * This method adds a new AwardTransferringSponsor to the list. 
     * It uses {@link KeywordsService} to process the request
     */
    public ActionForward addAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().addAwardTransferringSponsor();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** 
     * This method removes an AwardTransferringSponsor from the list. 
     * It uses {@link KeywordsService} to process the request
     */
    public ActionForward deleteAwardTransferringSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((AwardForm) form).getDetailsAndDatesFormHelper().deleteAwardTransferringSponsor(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteAwardFundingProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((AwardForm) form).getFundingProposalBean().deleteAwardFundingProposal(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * This method locates an award for the specified awardId.
     */
    protected Award findSelectedAward(String awardId) {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put(AWARD_ID_PARAMETER_NAME, awardId);
        List<Award> awards = (List<Award>) getBusinessObjectService().findMatching(Award.class, fieldMap);
        return awards.get(0);
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
     * Because the helper beans may have preserved a different AwardForm, we need to reset these too.
     */
    private void reinitializeAwardForm(AwardForm awardForm, AwardDocument document) throws WorkflowException {
        awardForm.populateHeaderFields(document.getDocumentHeader().getWorkflowDocument());
        awardForm.setDocument(document);
        document.setDocumentSaveAfterAwardLookupEditOrVersion(true);
        awardForm.initialize();
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
            response.sendRedirect(buildForwardUrl(awardForm.getAwardDocument().getDocumentNumber()));
            forward = null;
        }
        return forward;
    }
    
    public ActionForward applySponsorTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward result = fullSyncToAwardTemplate(mapping, form, request, response);
        
        awardForm.buildReportTrackingBeans();
        
        return result;
    }
    
    private ActionForward redirectAwardHistoryFullViewForPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String awardDocumentNumber, String awardNumber) throws Exception {
        //super.populateAwardHierarchy(form);
        AwardForm awardForm = (AwardForm)form;
        response.sendRedirect("awardHistory.do?methodToCall=openWindow&awardDocumentNumber=" + awardDocumentNumber + "&awardNumber=" + awardNumber + "&docTypeName=" + awardForm.getDocTypeName());
      
        return null;
    }

}
