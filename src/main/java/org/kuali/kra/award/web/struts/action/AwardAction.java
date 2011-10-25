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

import static org.apache.commons.lang.StringUtils.replace;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.kns.util.KNSConstants.CONFIRMATION_QUESTION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.AwardLockService;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.AwardTemplateSyncService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyBean;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyTempObject;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncCreationService;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.BudgetLimitSummaryHelper;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingService;
import org.kuali.kra.award.paymentreports.closeout.CloseoutReportTypeValuesFinder;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.web.struts.action.BudgetParentActionBase;
import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.AwardRoleConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.AwardDirectFandADistributionService;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.kra.service.AwardSponsorTermService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.TimeAndMoneyExistenceService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.kra.web.struts.action.AuditActionHelper.ValidationState;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * 
 * This class represents base Action class for all the Award pages.
 */
public class AwardAction extends BudgetParentActionBase {
    protected static final String AWARD_ID_PARAMETER_NAME = "awardId";
    private static final String AWARD_NUMBER_SERVICE = "awardNumberService";
    private static final String INITIAL_TRANSACTION_COMMENT = "Initial Time And Money creation transaction";
    private static final String REPORTS_PROPERTY_NAME = "Reports";
    private static final String PAYMENT_INVOICES_PROPERTY_NAME = "Payments and Invoices";
    private static final String COMFIRMATION_PARAM_STRING = "After Award {0} information is synchronized, make sure that the Award Sponsor Contacts information is also synchronized with the same sponsor template. Failing to do so will result in data inconsistency. Are you sure you want to replace current {0} information with selected {1} template information?";

    private ParameterService parameterService;
    private transient AwardBudgetService awardBudgetService;
    private transient AwardService awardService;
    private transient ReportTrackingService reportTrackingService;
    
    private static final Log LOG = LogFactory.getLog( AwardAction.class );
    
    //question constants
    private static final String QUESTION_VERIFY_SYNC="VerifySync";
    private static final String QUESTION_VERIFY_EMPTY_SYNC="VerifyEmptySync";
    
    private static final AwardTemplateSyncScope[] DEFAULT_SCOPES_REQUIRE_VERIFY_FOR_EMPTY = new AwardTemplateSyncScope[] {
            AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB,
            AwardTemplateSyncScope.SPONSOR_CONTACTS_TAB,
            AwardTemplateSyncScope.REPORTS_TAB
    };
    
    
    private static final AwardTemplateSyncScope[] DEFAULT_AWARD_TEMPLATE_SYNC_SCOPES = new AwardTemplateSyncScope[] { 
        AwardTemplateSyncScope.AWARD_PAGE,
        AwardTemplateSyncScope.COST_SHARE,
        AwardTemplateSyncScope.PAYMENTS_AND_INVOICES_TAB,
        AwardTemplateSyncScope.SPONSOR_CONTACTS_TAB,
        AwardTemplateSyncScope.TERMS_TAB,
        AwardTemplateSyncScope.REPORTS_TAB,
        AwardTemplateSyncScope.COMMENTS_TAB
        };

    private static final int NINE = 9;
    private static final int ZERO = 0;
    private static final String DOCUMENT_ROUTE_QUESTION="DocRoute";
    
    private static final String ADD_SYNC_CHANGE_QUESTION = "document.question.awardhierarchy.sync";
    private static final String DEL_SYNC_CHANGE_QUESTION = "document.question.awardhierarchy.sync";    
   
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward forward;
        cleanUpUserSession();
        forward = handleDocument(mapping, form, request, response, awardForm);
        
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        //check to see if this document might be a part of an active award sync(if it is lock it)
        AwardDocument parentSyncAward = 
            getAwardSyncService().getAwardLockingHierarchyForSync(awardDocument, GlobalVariables.getUserSession().getPrincipalId()); 
        if (parentSyncAward != null) {
            GlobalVariables.getMessageList().add("error.award.awardhierarchy.sync.locked", parentSyncAward.getDocumentNumber());
            awardForm.setViewOnly(true);
        }
        handlePlaceHolderDocument(awardForm, awardDocument);
        awardForm.initializeFormOrDocumentBasedOnCommand();
        setBooleanAwardInMultipleNodeHierarchyOnForm (awardDocument.getAward());
        setBooleanAwardHasTandMOrIsVersioned(awardDocument.getAward());
        
        return forward;
    }

    private void handlePlaceHolderDocument(AwardForm form, AwardDocument awardDocument) {
        if(awardDocument.isPlaceHolderDocument()) {
            Long awardId = form.getPlaceHolderAwardId();
            //If it is a placeholder document, we want to initialize it with the award that the user is viewing
            int currentAwardIndex = -1;
            Award currentAward = null;
            for(Award award : awardDocument.getAwardList()) {
                currentAwardIndex++;
                if(ObjectUtils.equals(award.getAwardId(), awardId)) {
                	currentAward = award;
                    break;
                }
            }
            if(currentAward != null) {
                awardDocument.getAwardList().remove(currentAwardIndex);
                awardDocument.getAwardList().add(0, currentAward);
                form.setViewOnly(true);                
            }
        }
    }
    
    protected void cleanUpUserSession() {
        GlobalVariables.getUserSession().removeObject(GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
    }
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        if (GlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((AwardForm) form);
        }
        
        return actionForward;
    }

    /**
     * This method populates the AwardHierarchy data
     * @param form
     * @throws WorkflowException 
     */
    protected void populateAwardHierarchy(ActionForm form) throws WorkflowException {
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        List<String> order = new ArrayList<String>();
        AwardHierarchyBean helperBean = awardForm.getAwardHierarchyBean();
        AwardHierarchy rootNode = helperBean.getRootNode();
        Map<String, AwardHierarchy> awardHierarchyNodes = helperBean.getAwardHierarchy(rootNode, order);
        Map<String,AwardHierarchyNode> awardHierarchyNodesMap = new HashMap<String, AwardHierarchyNode>();
        Award currentAward = awardDocument.getAward();
        getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyNodes, awardHierarchyNodesMap, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
        awardForm.setAwardHierarchyNodes(awardHierarchyNodes);
        awardForm.setRootAwardNumber(rootNode.getRootAwardNumber());
        awardForm.setOrder(order);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(String str:order){
            AwardHierarchyNode tempAwardNode = awardHierarchyNodesMap.get(str);
            sb1.append(tempAwardNode.getAwardNumber());
            sb1.append(KNSConstants.BLANK_SPACE).append("%3A");
            if(tempAwardNode.isAwardDocumentFinalStatus()) {
                sb2.append(tempAwardNode.getAwardNumber());
                sb2.append(KNSConstants.BLANK_SPACE).append("%3A");
            }
        }
        
        if(CollectionUtils.isNotEmpty(awardForm.getAwardHierarchyTempObjects())) {
            for(AwardHierarchyTempObject temp: awardForm.getAwardHierarchyTempObjects()){
                temp.setSelectBox1(sb1.toString());
                temp.setSelectBox2(sb2.toString());
            }
        }

    }
    

    protected TimeAndMoneyExistenceService getTimeAndMoneyExistenceService() {
        return KraServiceLocator.getService(TimeAndMoneyExistenceService.class);
    }
    
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        AwardForm awardForm = (AwardForm) form;

        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(), awardForm.getAwardHierarchyNodes())){
            forward = super.approve(mapping, form, request, response);
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }

        Long routeHeaderId = Long.parseLong(awardForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, "AwardDocument");
        
        ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }
    
    protected ActionForward submitAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(), awardForm.getAwardHierarchyNodes())){
            forward = super.route(mapping, form, request, response);
            populateAwardHierarchy(awardForm);
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
        }
        
        Long routeHeaderId = Long.parseLong(awardForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, "AwardDocument");
        
        ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AwardForm awardForm = (AwardForm) form;
        awardForm.setAuditActivated(true);

        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        ValidationState status = new AuditActionHelper().isValidSubmission(awardForm, true);
        
        if (status == ValidationState.WARNING) {
            if(question == null){
                return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, "Validation Warning Exists. Are you sure want to submit to workflow routing.", KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
            } else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                return submitAward(mapping, form, request, response);
            } else {
                return forward;
            }    
        }
        
        if(status == ValidationState.OK){
           return submitAward(mapping, form, request, response);
        } else {
            GlobalVariables.getErrorMap().clear(); 
            GlobalVariables.getErrorMap().putError("datavalidation",KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
            return forward;
         }
    }
    
    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        AwardForm awardForm = (AwardForm) form;

        if (getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(),
                awardForm.getAwardHierarchyNodes())) {
            awardForm.setAuditActivated(true);
            ValidationState status = new AuditActionHelper().isValidSubmission(awardForm, true);
            if (status == ValidationState.ERROR) {
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION, new String[] {});
                forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            } else {
                forward = super.blanketApprove(mapping, form, request, response);
            }
        } else {
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        Long routeHeaderId = Long.parseLong(awardForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, "AwardDocument");
        
        ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO: JF Are all of these saves in a single transaction?
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AwardForm awardForm = (AwardForm) form;

        Award award = awardForm.getAwardDocument().getAward();
        checkAwardNumber(award);
        String userId = GlobalVariables.getUserSession().getPrincipalName();

        boolean savingNewAward = award.getAwardId() == null;

        forward = super.save(mapping, form, request, response);
        if (awardForm.getMethodToCall().equals("save") && awardForm.isAuditActivated()) {
            forward = mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }

        boolean newAwardSaved = savingNewAward && award.getAwardId() != null;
        if (newAwardSaved) {
            getAwardService().updateAwardSequenceStatus(award, VersionStatus.PENDING);
            getVersionHistoryService().createVersionHistory(award, VersionStatus.PENDING, userId);
        }

        AwardHierarchyBean bean = awardForm.getAwardHierarchyBean();
        if (bean.saveHierarchyChanges()) {
            List<String> order = new ArrayList<String>();
            awardForm.setAwardHierarchyNodes(bean.getAwardHierarchy(bean.getRootNode().getAwardNumber(), order));
            awardForm.setOrder(order);
        }
        // generate hierarchy sync changes after save so all BOs have ids and parent ids set
        for (AwardSyncPendingChangeBean pendingChange : awardForm.getAwardSyncBean().getConfirmedPendingChanges()) {
            // refresh object to make sure all references have been loaded before the sync
            pendingChange.getObject().refresh();
            getAwardSyncCreationService().addAwardSyncChange(award, pendingChange);
        }
        // now we need to save the hierarchy changes
        getBusinessObjectService().save(award.getSyncChanges());
        awardForm.getAwardSyncBean().getConfirmedPendingChanges().clear();
        
        /**
         * deal with the award report tracking generation business.
         */
        getReportTrackingService().generateReportTrackingAndSave(award, false);

        return forward;
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward actionForward = super.reload(mapping, form, request, response);
        getReportTrackingService().refreshReportTracking(awardForm.getAwardDocument().getAward());
        return actionForward;        
    }

    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        
        if (awardForm.getViewFundingSource()) {
            return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
        } else {
            return super.close(mapping, form, request, response);
        }
    }

    /**
     * This method returns the award associated with the AwardDocument on the AwardForm
     * @return
     */
    protected Award getAward(ActionForm form) {
        return getAwardDocument(form).getAward(); 
    }
    
    /**
     * This method returns the AwardDocument
     * 
     * @param form
     * @return
     */
    protected AwardDocument getAwardDocument(ActionForm form) {
        return ((AwardForm) form).getAwardDocument();
    }
    
    /**
     * This method sets an award number on an award if the award number hasn't been initialized yet.
     * @param award
     */
    protected void checkAwardNumber(Award award) {
        if (Award.DEFAULT_AWARD_NUMBER.equals(award.getAwardNumber())) {
            AwardNumberService awardNumberService = getAwardNumberService();
            String awardNumber = awardNumberService.getNextAwardNumber();
            award.setAwardNumber(awardNumber);
        }
        if (Award.DEFAULT_AWARD_NUMBER.equals(award.getAwardAmountInfos().get(0).getAwardNumber())) {
            award.getAwardAmountInfos().get(0).setAwardNumber(award.getAwardNumber());
        }
        for(AwardApprovedSubaward approvedSubaward : award.getAwardApprovedSubawards()) {
            if(Award.DEFAULT_AWARD_NUMBER.equals(approvedSubaward.getAwardNumber())) {
                approvedSubaward.setAwardNumber(award.getAwardNumber());
            }
        }
        for(AwardComment comment : award.getAwardComments()) {
            comment.setAward(award);
        }
    }
    
    /**
     * 
     * This method is a helper method to retrieve AwardNumberService.
     * @return
     */
    protected AwardNumberService getAwardNumberService() {
        return KraServiceLocator.getService(AwardNumberService.class);
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     *
     * TODO JF: Handle initial save
     * 
     * One of these conditions exist when this method is called:
       1) This is a new award, created from the "Create Award" portal action. A new root node needs to be created
          a) prevAwardNumber and prevRootAwardNumber are null
          b) awardHierarchyBean.rootNodes.size() == 0
       2) This is a new award created from a hierarchy action. The node for this award should exist on the hierarchy bean
          a) prevAwardNumber and prevRootAwardNumber are ?
          b) awardHierarchyBean.rootNodes.size() == ?
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        createInitialAwardUsers(awardForm.getAwardDocument().getAward());
        populateStaticCloseoutReports(awardForm);
        //populateDefaultUnitContactsFromLeadUnit(awardForm);
        if(!awardForm.getAwardDocument().isDocumentSaveAfterVersioning()) {
            createDefaultAwardHierarchy(awardForm);            
        }
    }

    // TODO JF: Integrate behavior into bean; i.e. bean should create the new node
    protected void createDefaultAwardHierarchy(AwardForm awardForm) {
        String awardNumber = awardForm.getAwardDocument().getAward().getAwardNumber();
        AwardHierarchy newNode = new AwardHierarchy();
        newNode.setAwardNumber(awardNumber);
        newNode.setParentAwardNumber(determineParentAwardNumber(awardForm));
        newNode.setRootAwardNumber(determineRootAwardNumber(awardForm));
        newNode.setOriginatingAwardNumber(awardNumber);
        if(newNode.isRootNode()) {
            awardForm.getAwardHierarchyBean().setRootNode(newNode);
        }
    }
    
    //protected void populateDefaultUnitContactsFromLeadUnit(AwardForm awardForm) {
        //awardForm.getUnitContactsBean().fi
    //}

    /**
     * Create the original set of Award Users for a new Award Document.
     * The creator the award is assigned to the AWARD_MODIFIER role.
     *
     * @param doc
     */
    protected void createInitialAwardUsers(Award award) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthService.addRole(userId, AwardRoleConstants.AWARD_MODIFIER.getAwardRole(), award); 
    }

    /**
     *
     * This method populates the initial static AwardCloseout reports upon the creation of an Award.
     *
     * @param form
     */
    protected void populateStaticCloseoutReports(AwardForm form){
        CloseoutReportTypeValuesFinder closeoutReportTypeValuesFinder = new CloseoutReportTypeValuesFinder();

        form.getAwardCloseoutBean().addAwardCloseoutStaticItems(closeoutReportTypeValuesFinder.getKeyValues());
    }

    protected AwardHierarchyService getAwardHierarchyService(){
        return KraServiceLocator.getService(AwardHierarchyService.class);
    }

    /**
     * Can the Award be saved?  This method is normally overridden by
     * a subclass in order to invoke business rules to verify that the
     * Award can be saved.
     * @param awardForm the Award Form
     * @return true if the award can be saved; otherwise false
     */
    protected boolean isValidSave(AwardForm awardForm) {
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        String leadUnitNumber = awardDocument.getLeadUnitNumber();
        if (StringUtils.isNotEmpty(leadUnitNumber) && checkNoMoreThanOnePI(awardDocument.getAward())) {
            String userId = GlobalVariables.getUserSession().getPrincipalId();
            UnitAuthorizationService authService = KraServiceLocator.getService(UnitAuthorizationService.class);      
            //List<Unit> userUnits = authService.getUnits(userId, Constants.MODULE_NAMESPACE_AWARD, AwardPermissionConstants.CREATE_AWARD.getAwardPermission());
            return authService.hasMatchingQualifiedUnits(userId, Constants.MODULE_NAMESPACE_AWARD, 
                    AwardPermissionConstants.MODIFY_AWARD.getAwardPermission(), leadUnitNumber);
        }
        return false; 
    }
    
    private boolean checkNoMoreThanOnePI(Award award) {
        int piCount = 0;
        int counter = 0;
        ArrayList<String> fields = new ArrayList<String>();
        for (AwardPerson p : award.getProjectPersons()) {
            if (p.isPrincipalInvestigator()) {
                piCount++;
                fields.add("projectPersonnelBean.projectPersonnel[" + counter + "].contactRoleCode");
            }
            counter++;
        }
        boolean valid = piCount <= 1;
        if (!valid) {
            //projectPersonnelBean.contactRoleCode
            //projectPersonnelBean.projectPersonnel[0].contactRoleCode
            for (String field  : fields) {
                GlobalVariables.getMessageMap().putError(field, AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
            }
        }
        
        return valid;
    }

    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

    /**
     *
     * This method builds the string for the ActionForward
     * @param forwardPath
     * @param docIdRequestParameter
     * @return
     */
    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KNSConstants.PARAMETER_DOC_ID);
        sb.append("=");
        sb.append(docIdRequestParameter);
        return sb.toString();
    }


    /**
     *
     * This method gets called upon navigation to Awards tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward home(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        setBooleanAwardInMultipleNodeHierarchyOnForm (awardDocument.getAward());
        setBooleanAwardHasTandMOrIsVersioned(awardDocument.getAward());
        AwardAmountInfoService awardAmountInfoService = KraServiceLocator.getService(AwardAmountInfoService.class);
        int index = awardAmountInfoService.fetchIndexOfAwardAmountInfoWithHighestTransactionId(awardDocument.getAward().getAwardAmountInfos());
        
        return mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
    }
    
    

    /**
     * This method...
     * @param awardDocument
     * @param awardForm
     */
    @SuppressWarnings("unchecked")
    public void setBooleanAwardInMultipleNodeHierarchyOnForm (Award award) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        String awardNumber = award.getAwardNumber();
        fieldValues.put("awardNumber", awardNumber);
        fieldValues.put("active", Boolean.TRUE);
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<AwardHierarchy> awardHierarchies = (ArrayList) businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
        if (awardHierarchies.size() == 0) {
            award.setAwardInMultipleNodeHierarchy(false);
        }else {
            Map<String, Object> newFieldValues = new HashMap<String, Object>();
            String rootAwardNumber = awardHierarchies.get(0).getRootAwardNumber();
            newFieldValues.put("rootAwardNumber", rootAwardNumber);
            newFieldValues.put("active", Boolean.TRUE);
            int matchingValues = businessObjectService.countMatching(AwardHierarchy.class, newFieldValues);
            if (matchingValues > 1) {
                award.setAwardInMultipleNodeHierarchy(true);
            }else {
                award.setAwardInMultipleNodeHierarchy(false);
            }
        }
    }
    
    
    /**
     * If an Award has associated Time and Money document or been versioned and no previous version has been edited in a Time and Money document, 
     * then we want the money and date fields on Award to be read only.
     * @param awardDocument
     * @param awardForm
     */
    public void setBooleanAwardHasTandMOrIsVersioned (Award award) {
        boolean previousVersionHasBeenEditedInTandMDocument = false;
        List<Award> awards = getAwardVersions(award.getAwardNumber());
        if(awards.size() > 1) {
            previousVersionHasBeenEditedInTandMDocument = true;
        }else if(awards.size() == 1 && awards.get(0).getAwardAmountInfos().size() > 1){
            previousVersionHasBeenEditedInTandMDocument = true;
        }
//        for(Award awardVersion : awards) {
//            if(awardVersion.getSequenceNumber() == 1 && awardVersion.getAwardAmountInfos().size() > 2){
//                previousVersionHasBeenEditedInTandMDocument = true;
//                break;
//            }else if(awardVersion.getSequenceNumber() > 1 && awardVersion.getAwardAmountInfos().size() > 1){
//                previousVersionHasBeenEditedInTandMDocument = true;
//                break;
//            }
//        }
//        
        award.setAwardHasAssociatedTandMOrIsVersioned(previousVersionHasBeenEditedInTandMDocument);
    }
    
    

    /**
     *
     * This method gets called upon navigation to Contacts tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward contacts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SponsorService sponsorService = getSponsorService();
        Award award = getAward(form);
        AwardForm awardForm = (AwardForm) form;
        
        award.initCentralAdminContacts();

        return mapping.findForward(Constants.MAPPING_AWARD_CONTACTS_PAGE);
    }

    /**
     *
     * This method gets called upon navigation to Commitments tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward commitments(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.MAPPING_AWARD_COMMITMENTS_PAGE);
    }


    
    @SuppressWarnings({ "deprecation", "unchecked" })
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        AwardForm awardForm = (AwardForm) form;
        ActionForward actionForward;
        //if award document is view only then we don't need to save document before opening T&M document.
        if(!awardForm.getEditingMode().containsKey("viewOnly") || awardForm.getEditingMode().containsKey("fullEntry")){
            this.save(mapping, form, request, response);
        }
        
        if(GlobalVariables.getErrorMap().hasNoErrors()){
            //AwardForm awardForm = (AwardForm) form;
            DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
            boolean firstTimeAndMoneyDocCreation = Boolean.TRUE;
            TransactionDetail transactionDetail = null;
    
            populateAwardHierarchy(form);
    
            Award award = awardForm.getAwardDocument().getAward();
            if(isNewAward(awardForm) && !(award.getAwardEffectiveDate() == null)){
                AwardDirectFandADistributionService awardDirectFandADistributionService = getAwardDirectFandADistributionService();
                awardForm.getAwardDocument().getAward().setAwardDirectFandADistributions
                                    (awardDirectFandADistributionService.
                                            generateDefaultAwardDirectFandADistributionPeriods(awardForm.getAwardDocument().getAward()));
                getBusinessObjectService().save(awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions());
            }
    
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            String rootAwardNumber = awardForm.getAwardHierarchyNodes().get(award.getAwardNumber()).getRootAwardNumber();
            fieldValues.put("rootAwardNumber", rootAwardNumber);
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);

            List<TimeAndMoneyDocument> timeAndMoneyDocuments = 
                (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues, "documentNumber", true);
            
            Award rootAward = getWorkingAwardVersion(rootAwardNumber);   
            //this logic so we set Transaction Type on new T&M doc.  Defaults to "new" on first creation of T&M doc of a Root Award.
            TimeAndMoneyDocument timeAndMoneyDocument = getLastFinalTandMDocument(timeAndMoneyDocuments);
            //if timeAndMoneyDocument is null then either it is first creation or all the previous T&M docs have a route status of 'canceled'.
            if(timeAndMoneyDocuments.size() > 0 && timeAndMoneyDocument != null) {
                firstTimeAndMoneyDocCreation = Boolean.FALSE;
            }
    
            if(firstTimeAndMoneyDocCreation){
                timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
                timeAndMoneyDocument.getDocumentHeader().setDocumentDescription("timeandmoney document");
                timeAndMoneyDocument.setRootAwardNumber(rootAwardNumber);
                timeAndMoneyDocument.setAwardNumber(rootAward.getAwardNumber());
                timeAndMoneyDocument.setAward(rootAward);
                AwardAmountTransaction aat = new AwardAmountTransaction();
                aat.setAwardNumber("000000-00000");//need to initialize one element in this collection because the doc is saved on creation.
                aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                aat.setTransactionTypeCode(NINE);
                aat.setAwardNumber(rootAward.getAwardNumber());
                //any code for initial transaction and history.
                transactionDetail  = addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, rootAward.getAwardNumber(), rootAward.getSequenceNumber(),
                        timeAndMoneyDocument.getDocumentNumber(), INITIAL_TRANSACTION_COMMENT, rootAward);
                //need this check so we don't add additional AAI object if Award has been copied and then creating first T&M doc.
                if(rootAward.getAwardAmountInfos().size() < 2) {
                    addNewAwardAmountInfoForInitialTransaction(rootAward, timeAndMoneyDocument.getDocumentNumber());
                }else {
                    rootAward.getLastAwardAmountInfo().setTimeAndMoneyDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                    getBusinessObjectService().save(rootAward);
                }
                timeAndMoneyDocument.getAwardAmountTransactions().add(aat);
                documentService.saveDocument(timeAndMoneyDocument);
                getBusinessObjectService().save(transactionDetail);
            }
            Long routeHeaderId = timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            String forward = buildForwardUrl(routeHeaderId);
            actionForward = new ActionForward(forward, true);
        } else {
            actionForward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        return actionForward;

    }
        
    protected TimeAndMoneyDocument getLastFinalTandMDocument(List<TimeAndMoneyDocument> timeAndMoneyDocuments) throws WorkflowException {
        TimeAndMoneyDocument returnVal = null;
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        while(timeAndMoneyDocuments.size() > 0) {
            TimeAndMoneyDocument docWithWorkFlowData = 
                (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(timeAndMoneyDocuments.get(timeAndMoneyDocuments.size() - 1).getDocumentNumber());
            if(docWithWorkFlowData.getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
                timeAndMoneyDocuments.remove(timeAndMoneyDocuments.size() - 1);
            }else {
                returnVal = docWithWorkFlowData;
                break;
            }
        }
        return returnVal;
    }
    
    /*
     * 
     * This method creates a transactionDetail object and adds it to the list for persistence later.
     * 
     * @param sourceAwardNumber
     * @param destinationAwardNumber
     * @param sequenceNumber
     * @param pendingTransaction
     * @param currentAwardNumber
     * @param documentNumber
     * @param transactionDetailItems
     */
    protected TransactionDetail addTransactionDetails(String sourceAwardNumber, String destinationAwardNumber, Integer sequenceNumber, String documentNumber, 
            String commentsString, Award rootAward){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setSourceAwardNumber(sourceAwardNumber);
        transactionDetail.setSequenceNumber(sequenceNumber);
        transactionDetail.setDestinationAwardNumber(destinationAwardNumber);
        if(isDirectIndirectViewEnabled()){
            transactionDetail.setAnticipatedAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            transactionDetail.setAnticipatedDirectAmount(rootAward.getAnticipatedTotalDirect());
            transactionDetail.setAnticipatedIndirectAmount(rootAward.getAnticipatedTotalIndirect());
            transactionDetail.setObligatedAmount(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            transactionDetail.setObligatedDirectAmount(rootAward.getObligatedTotalDirect());
            transactionDetail.setObligatedIndirectAmount(rootAward.getObligatedTotalIndirect());
        } else {
            transactionDetail.setAnticipatedAmount(rootAward.getAnticipatedTotal());
            transactionDetail.setAnticipatedDirectAmount(rootAward.getAnticipatedTotal());
            transactionDetail.setAnticipatedIndirectAmount(new KualiDecimal(0));
            transactionDetail.setObligatedAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedDirectAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedIndirectAmount(new KualiDecimal(0));
        }
        transactionDetail.setAwardNumber(rootAward.getAwardNumber());
        transactionDetail.setTransactionId(new Long(0));
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        transactionDetail.setTransactionDetailType(TransactionDetailType.PRIMARY.toString());
        return transactionDetail;
        
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
        String directIndirectEnabledValue = getParameterService().getParameterValue(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    /*
     * add money to amount info Totals, and Distributables.
     * 
     */
    private void addNewAwardAmountInfoForInitialTransaction(Award rootAward, String documentNumber) {
        
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(rootAward.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(rootAward.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(rootAward.getLastAwardAmountInfo().getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(rootAward.getLastAwardAmountInfo().getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(rootAward.getLastAwardAmountInfo().getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(null);
        newAwardAmountInfo.setAward(rootAward);
       //add transaction amounts to the AmountInfo
        if(isDirectIndirectViewEnabled()){
            newAwardAmountInfo.setAmountObligatedToDate(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setObligatedChange(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setObligatedChangeDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedChangeIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChange(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChangeDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedChangeIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
        } else {
            newAwardAmountInfo.setAmountObligatedToDate(rootAward.getLastAwardAmountInfo().getAmountObligatedToDate());
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setObligatedChange(rootAward.getObligatedTotal());
            newAwardAmountInfo.setObligatedChangeDirect(new KualiDecimal(0));
            newAwardAmountInfo.setObligatedChangeIndirect(new KualiDecimal(0));
            newAwardAmountInfo.setAnticipatedChange(rootAward.getAnticipatedTotal());
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getLastAwardAmountInfo().getAnticipatedTotalAmount());
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChangeDirect(new KualiDecimal(0));
            newAwardAmountInfo.setAnticipatedChangeIndirect(new KualiDecimal(0));
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotal());
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotal());
        }
        newAwardAmountInfo.setOriginatingAwardVersion(rootAward.getSequenceNumber());
        rootAward.getAwardAmountInfos().add(newAwardAmountInfo);
        getBusinessObjectService().save(rootAward);
    }
    
    public List<Award> getAwardVersions(String awardNumber) {
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(awardNumber), "sequenceNumber", true);   
        return awards;
    }
    
    public Award getWorkingAwardVersion(String goToAwardNumber) {
        Award award = null;
        award = getPendingAwardVersion(goToAwardNumber);
        if (award == null) {
            award = getActiveAwardVersion(goToAwardNumber);
        }
        return award;
    }
    
    /*
     * This method retrieves the pending award version.
     * 
     * @param doc
     * @param goToAwardNumber
     */
    @SuppressWarnings("unchecked")
    public Award getPendingAwardVersion(String goToAwardNumber) {
        
        Award award = null;
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(goToAwardNumber), "sequenceNumber", true);
        if(!(awards.size() == 0)) {
            award = awards.get(awards.size() - 1);
        }
      
        return award;
    }
    
   
    private Award getActiveAwardVersion(String goToAwardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, goToAwardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMapToFindActiveAward(goToAwardNumber))).get(0);              
        }
        return award;
    }
    private Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }

    protected KraWorkflowService getKraWorkflowService() {
        return KraServiceLocator.getService(KraWorkflowService.class);
    }
    

    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     * @param awardForm
     * @return
     */
    public boolean isNewAward(AwardForm awardForm) {
        return awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }

    /**
     *
     * This method is a helper method to retrieve AwardSponsorTermService.
     * @return
     */
    protected AwardDirectFandADistributionService getAwardDirectFandADistributionService() {
        return KraServiceLocator.getService(AwardDirectFandADistributionService.class);
    }

    /**
     *
     * This method gets called upon navigation to Payment, Reports and Terms tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("all")
    public ActionForward paymentReportsAndTerms(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;

        setReportsAndTermsOnAwardForm(awardForm);

        return mapping.findForward(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
    }

    @SuppressWarnings("unchecked")
    protected void setReportsAndTermsOnAwardForm(AwardForm awardForm) {
        AwardSponsorTermService awardSponsorTermService = getAwardSponsorTermService();
        List<KeyLabelPair> sponsorTermTypes = awardSponsorTermService.retrieveSponsorTermTypesToAwardFormForPanelHeaderDisplay();
        awardForm.getSponsorTermFormHelper().setSponsorTermTypes(sponsorTermTypes);
        awardForm.getSponsorTermFormHelper().setNewSponsorTerms(awardSponsorTermService.getEmptyNewSponsorTerms(sponsorTermTypes));

        AwardReportsService awardReportsService = KraServiceLocator.getService(AwardReportsService.class);
        Map<String,Object> initializedObjects = awardReportsService.initializeObjectsForReportsAndPayments(
                                                    awardForm.getAwardDocument().getAward());
        awardForm.setReportClasses((List<KeyLabelPair>) initializedObjects.get(
                                      Constants.REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.getAwardReportsBean().setNewAwardReportTerms((List<AwardReportTerm>) initializedObjects.get(
                                          Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.getAwardReportsBean().setNewAwardReportTermRecipients((List<AwardReportTermRecipient>) initializedObjects.get(
                                                    Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.setReportClassForPaymentsAndInvoices((ReportClass) initializedObjects.get(
                                                        Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL));

    }

    /**
     *
     * This method is a helper method to retrieve AwardSponsorTermService.
     * @return
     */
    protected AwardSponsorTermService getAwardSponsorTermService() {
        return KraServiceLocator.getService(AwardSponsorTermService.class);
    }

    /**
     *
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        ((AwardForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
    }

    /**
     *
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward customData(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        return awardForm.getCustomDataHelper().awardCustomData(mapping, form, request, response);
    }

    /**
     *
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward questions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.MAPPING_AWARD_QUESTIONS_PAGE);
    }

    /**
     *
     * This method gets called upon navigation to Questions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward permissions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        ((AwardForm)form).getPermissionsHelper().prepareView();
        return mapping.findForward(Constants.MAPPING_AWARD_PERMISSIONS_PAGE);
    }

    /**
     *
     * This method gets called upon navigation to Permissions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward notesAndAttachments(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardCommentBean().setAwardCommentScreenDisplayTypesOnForm();
        awardForm.getAwardCommentBean().setAwardCommentHistoryFlags();
        return mapping.findForward(Constants.MAPPING_AWARD_NOTES_AND_ATTACHMENTS_PAGE);
    }
    
    /**
    *
    * This method gets called upon navigation to Medusa tab.
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       AwardForm awardForm = (AwardForm) form;
       if (awardForm.getDocument().getDocumentNumber() == null) {
           //if we are entering this from the search results
           loadDocumentInForm(request, awardForm);
       }
       awardForm.getMedusaBean().setMedusaViewRadio("0");
       awardForm.getMedusaBean().setModuleName("award");
       awardForm.getMedusaBean().setModuleIdentifier(awardForm.getAwardDocument().getAward().getAwardId());
       return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);
   }

    /**
     *
     * This method gets called upon navigation to Award Actions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */ 
    public ActionForward awardActions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {

        AwardForm awardForm = (AwardForm) form;
        String command = request.getParameter(KEWConstants.COMMAND_PARAMETER);
        if(StringUtils.isNotEmpty(command) && KEWConstants.DOCSEARCH_COMMAND.equals(command)) {
            loadDocumentInForm(request, awardForm); 
            KualiWorkflowDocument workflowDoc = awardForm.getAwardDocument().getDocumentHeader().getWorkflowDocument();
            if(workflowDoc != null)
                awardForm.setDocTypeName(workflowDoc.getDocumentType());
            request.setAttribute("selectedAwardNumber", awardForm.getAwardDocument().getAward().getAwardNumber());   
        } 
        populateAwardHierarchy(form); 

        return mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
    }


    /**
     *
     * This method gets called upon navigation to Awards tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward budgets(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        getAwardBudgetService().populateBudgetLimitSummary(awardForm.getBudgetLimitSummary(), awardForm.getAwardDocument());
        return mapping.findForward(Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE);
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param awardForm
     * @return
     * @throws Exception
     */
    ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, AwardForm awardForm) throws Exception {

        ActionForward forward = null;
        
        String command = awardForm.getCommand();
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            loadDocumentInForm(request, awardForm);
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            ActionForward baseForward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(buildForwardStringForActionListCommand(
                    baseForward.getPath(),awardForm.getDocument().getDocumentNumber()));
        } else if (Constants.MAPPING_AWARD_ACTIONS_PAGE.equals(command)) {
            loadDocument(awardForm);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }
        
        awardForm.getAwardDocument().populateCustomAttributes();
        
        if (Constants.MAPPING_AWARD_ACTIONS_PAGE.equals(command)) {
            forward = awardActions(mapping, awardForm, request, response);
        }
        
        return forward;
    }
    
    protected void loadDocument(KualiDocumentFormBase kualiForm) throws WorkflowException {
        super.loadDocument(kualiForm);
        Award award = ((AwardForm) kualiForm).getAwardDocument().getAward();
        award.setSponsorNihMultiplePi(getSponsorService().isSponsorNihMultiplePi(award));
    }
   
   /**
    *
    * loadDocumentInForm
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */    
    protected void loadDocumentInForm(HttpServletRequest request, AwardForm awardForm)
    throws WorkflowException {
        String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
        AwardDocument retrievedDocument = (AwardDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
        awardForm.setDocument(retrievedDocument);
        request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        handlePlaceHolderDocument(awardForm, retrievedDocument);        
    }
    
    /**
     *
     * @return
     */
    @Override
    protected DocumentService getDocumentService() {
        return KNSServiceLocator.getDocumentService();
    }

    /**
     *
     * This method is a helper method to retrieve KualiRuleService.
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
    
    
    /**
     * This method sets up a sponsor template synchronization loop.
     * It is called by the ui when a specific set of scopes need to by synchronized.
     * If no scopes are in the request, then a full synchronization to the scopes:
     * 
     * AWARD_PAGE
     * SPONSOR_CONTACTS_TAB
     * PAYMENTS_AND_INVOICES_TAB
     * TERMS_TAB
     * REPORTS_TAB
     * COMMENTS_TAB
     * 
     * is performed. This method generates and stores the list of scopes to sync
     * and the map to indicate if confirmation is necessary from the user before
     * a particular scope is synchronized and then forwards to the method the handles
     * the request loop.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncAwardTemplate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        AwardTemplateSyncService awardTemplateSyncService = KraServiceLocator.getService(AwardTemplateSyncService.class);
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        AwardTemplateSyncScope[] scopes;
        String syncScopes = getSyncScopesString( request );
        
        
        if( awardDocument.getAward().getTemplateCode() == null ) {
            //return now since there is no template code.
            GlobalVariables.getErrorMap().clear(); 
            GlobalVariables.getErrorMap().putError( StringUtils.isBlank(syncScopes)?"document.award.awardTemplate":String.format( "document.award.awardTemplate.%s",StringUtils.substring(syncScopes,1 )),KeyConstants.ERROR_NO_TEMPLATE_CODE,  new String[] {});
            awardForm.setOldTemplateCode(null);
            awardForm.setTemplateLookup(false);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if( question != null ) return processSyncAward(mapping, awardForm, request, response);
            

        awardForm.setCurrentSyncScopes(null);
        awardForm.setSyncRequiresConfirmationMap(null);
        
        /*
         * The format for the action string is:
         * methodToCall.syncAwardTemplate:SCOPE1:...:SCOPEN].anchor...
         * Where:
         * [PropertyName|MethodName] means to sync by a property name or a method name.
         * SCOPE1...SCOPEN : A ':' delimited list of scope names that should be synced. If none are specified then the sync is done for every field and method.
         * 
         */
        
               
        if (StringUtils.isNotBlank(syncScopes) && syncScopes.length() > 1 && syncScopes.indexOf(":")>-1) {
            String[] scopeStrings = StringUtils.split(StringUtils.substringAfter(syncScopes, ":"));
            scopes = new AwardTemplateSyncScope[scopeStrings.length];
            for( int i = 0; i < scopeStrings.length; i++ ) {
                scopes[i] = Enum.valueOf(AwardTemplateSyncScope.class, scopeStrings[i]);
            }
            awardForm.setSyncRequiresConfirmationMap(generateScopeRequiresConfirmationMap( scopes, awardDocument, false, false ));
            awardForm.setCurrentSyncScopes(scopes);
        } else {
            awardForm.setSyncRequiresConfirmationMap(generateScopeRequiresConfirmationMap( DEFAULT_AWARD_TEMPLATE_SYNC_SCOPES, awardDocument, false, false ));
            awardForm.setCurrentSyncScopes(DEFAULT_AWARD_TEMPLATE_SYNC_SCOPES);
        }

        return processSyncAward(mapping,form,request,response); 
    }

    private Map<AwardTemplateSyncScope, Boolean> generateScopeRequiresConfirmationMap( AwardTemplateSyncScope[] scopes, AwardDocument awardDocument,boolean skipCheck,boolean defaultValue ) {
        AwardTemplateSyncService awardTemplateSyncService = KraServiceLocator.getService(AwardTemplateSyncService.class);
        Map< AwardTemplateSyncScope,Boolean> requiresQuestionMap = new HashMap<AwardTemplateSyncScope,Boolean>();
        for( AwardTemplateSyncScope scope: scopes ) {
            if( skipCheck ) {
                requiresQuestionMap.put(scope, defaultValue);
            } else {
                if( awardTemplateSyncService.syncWillAlterData(awardDocument, scope) ) {
                    if( LOG.isDebugEnabled() )
                        LOG.debug(String.format( "%s:%s", scope, true ));
                    requiresQuestionMap.put(scope, true);  
                } else {
                    if( LOG.isDebugEnabled() )
                        LOG.warn(String.format( "%s:%s", scope, false ));
                    requiresQuestionMap.put(scope, false);
                }
            }
        }
        return requiresQuestionMap;
    }
    
    
    
    /**
     * This method sets up a full template sync.  This is called on return from a Sponsor Template Lookup.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fullSyncToAwardTemplate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();

        if( awardDocument.getAward().getTemplateCode() == null ) {
            //return now since there is no template code.
            GlobalVariables.getErrorMap().clear(); 
            GlobalVariables.getErrorMap().putError("document.award.awardTemplate",KeyConstants.ERROR_NO_TEMPLATE_CODE,  new String[] {});
            awardForm.setOldTemplateCode(null);
            awardForm.setTemplateLookup(false);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        } else {
            awardDocument.getAward().refreshReferenceObject("awardTemplate");
        }
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        boolean proceedToProcessSyncAward = true;
        
        if( question == null ) {
            //setup before forwarding to the processor.
            AwardTemplateSyncScope[] scopes;
            scopes = new AwardTemplateSyncScope[] { AwardTemplateSyncScope.FULL };
            HashMap<AwardTemplateSyncScope,Boolean> confirmMap = new HashMap<AwardTemplateSyncScope,Boolean>();
            confirmMap.put(AwardTemplateSyncScope.FULL, true);
            awardForm.setCurrentSyncScopes(scopes);
            awardForm.setSyncRequiresConfirmationMap(confirmMap);
        } else if( question!=null && (QUESTION_VERIFY_SYNC+":"+AwardTemplateSyncScope.FULL).equals(question) ) {
            
            if( ConfirmationQuestion.YES.equals(buttonClicked) ) {
                //if the award has a sequence number more than 1, we 
                //only select the template and the user must use the buttons on 
                //each panel to sync.
                if( awardDocument.getAward().getSequenceNumber() > 1 ) {
                    awardForm.setCurrentSyncScopes(new AwardTemplateSyncScope[] {});
                    proceedToProcessSyncAward=false;
                    awardForm.setTemplateLookup(false);
                    awardForm.setOldTemplateCode(null);
                } else {
                    awardForm.setCurrentSyncScopes(DEFAULT_AWARD_TEMPLATE_SYNC_SCOPES);
                    awardForm.setSyncRequiresConfirmationMap(generateScopeRequiresConfirmationMap( DEFAULT_AWARD_TEMPLATE_SYNC_SCOPES, awardDocument, false,false ));
                }
            } else {
                proceedToProcessSyncAward = false;
                awardDocument.getAward().setTemplateCode(awardForm.getOldTemplateCode());
                awardDocument.getAward().refreshReferenceObject("awardTemplate");
                awardForm.setOldTemplateCode(null);
                awardForm.setTemplateLookup(false);
            }
            
            
        }
     
        return proceedToProcessSyncAward?processSyncAward(mapping,form,request,response):mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    protected StrutsConfirmation buildAwardSyncParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String questionId, String configurationId, String... params)
            throws Exception {
        StrutsConfirmation retval = new StrutsConfirmation();
        retval.setMapping(mapping);
        retval.setForm(form);
        retval.setRequest(request);
        retval.setResponse(response);
        retval.setQuestionId(questionId);
        retval.setQuestionType(CONFIRMATION_QUESTION);

        String questionText = this.COMFIRMATION_PARAM_STRING;
        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);

        return retval;

    }
    
    public ActionForward processSyncAward(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        AwardTemplateSyncService awardTemplateSyncService = KraServiceLocator.getService(AwardTemplateSyncService.class);
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        String question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        AwardTemplateSyncScope[] scopes = awardForm.getCurrentSyncScopes();
        AwardTemplateSyncScope[] scopesList = scopes;   // for maintaining the current scopes list
        KualiConfigurationService kualiConfiguration = getService(KualiConfigurationService.class);
        
        for( int i = 0; i < scopes.length; i++ ) {
            AwardTemplateSyncScope currentScope = scopes[i];
            
            if (((question == null || !((StringUtils.equals(QUESTION_VERIFY_SYNC + ":" + currentScope, question))))
                        && awardForm.getSyncRequiresConfirmationMap().get(currentScope))
                    && !StringUtils.equals(QUESTION_VERIFY_EMPTY_SYNC + ":" + currentScope, question)) {
                        
                String scopeSyncLabel = "";
                StrutsConfirmation confirmationQuestion = new StrutsConfirmation();
                if( StringUtils.isNotEmpty(currentScope.getDisplayPropertyName())) {
                    scopeSyncLabel = kualiConfiguration.getPropertyString(currentScope.getDisplayPropertyName());
                }
                if( StringUtils.equals(scopeSyncLabel, REPORTS_PROPERTY_NAME) || StringUtils.equals(scopeSyncLabel, PAYMENT_INVOICES_PROPERTY_NAME)) {
                    confirmationQuestion = buildAwardSyncParameterizedConfirmationQuestion(mapping, form, request, response, (QUESTION_VERIFY_SYNC+":"+currentScope), 
                                currentScope.equals(AwardTemplateSyncScope.FULL)?KeyConstants.QUESTION_SYNC_FULL:KeyConstants.QUESTION_SYNC_PANEL,
                            scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription(), getScopeMessageToAddQuestion(currentScope));
                } else {
                    confirmationQuestion = buildParameterizedConfirmationQuestion(mapping, form, request, response, (QUESTION_VERIFY_SYNC+":"+currentScope), 
                            currentScope.equals(AwardTemplateSyncScope.FULL)?KeyConstants.QUESTION_SYNC_FULL:KeyConstants.QUESTION_SYNC_PANEL,
                            scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription(), getScopeMessageToAddQuestion(currentScope)); 
                }
                confirmationQuestion.setCaller("processSyncAward");
                awardForm.setCurrentSyncQuestionId( (QUESTION_VERIFY_SYNC+":"+currentScope) );
                return  (performQuestionWithoutInput( confirmationQuestion,""  ));
               
            } else if (( StringUtils.equals(awardForm.getCurrentSyncQuestionId(), question) 
                            &&  ConfirmationQuestion.YES.equals(buttonClicked)) 
                         || !awardForm.getSyncRequiresConfirmationMap().get(currentScope)) {                               
                    if( LOG.isDebugEnabled() ) 
                        LOG.debug( "USER ACCEPTED SYNC OR NO CONFIRM REQUIRED FOR:"+currentScope+" CALLING SYNC SERVICE." );
                    boolean templateHasScopedData = awardTemplateSyncService.templateContainsScopedData(awardDocument, currentScope);
                    boolean scopeRequiresEmptyConfirm = ArrayUtils.contains(DEFAULT_SCOPES_REQUIRE_VERIFY_FOR_EMPTY,currentScope);
                    
                    if( awardDocument.getAward().getSequenceNumber() > 1 
                            && !templateHasScopedData 
                            && StringUtils.equals( awardForm.getCurrentSyncQuestionId(), (QUESTION_VERIFY_SYNC+":"+currentScope) ) 
                            && scopeRequiresEmptyConfirm ) {
                       //we need to verify since the template has no data.
                        String scopeSyncLabel = "";
                        if( StringUtils.isNotEmpty(currentScope.getDisplayPropertyName()))
                            scopeSyncLabel = kualiConfiguration.getPropertyString(currentScope.getDisplayPropertyName());
                        
                        StrutsConfirmation confirmationQuestion = buildParameterizedConfirmationQuestion(mapping, form, request, response, (
                                    QUESTION_VERIFY_EMPTY_SYNC+":"+currentScope), KeyConstants.QUESTION_SYNC_PANEL_TO_EMPTY,
                                    scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription()); 
                        awardForm.setCurrentSyncQuestionId((QUESTION_VERIFY_EMPTY_SYNC+":"+currentScope));
                        confirmationQuestion.setCaller("processSyncAward");
                        return performQuestionWithoutInput(confirmationQuestion, "");
                        
                    } else  {
                        //anything to do here?
                    }
                    
                    AwardTemplateSyncScope[] s = { currentScope };
                    awardTemplateSyncService.syncAwardToTemplate(awardDocument, s);
                    scopesList = (AwardTemplateSyncScope[])ArrayUtils.remove(scopesList, 0);    // maintaining the current list
                    awardForm.setCurrentSyncScopes(scopesList);
                    
           } else if ( StringUtils.equals(awardForm.getCurrentSyncQuestionId(),question) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                if( LOG.isDebugEnabled() ) 
                    LOG.debug( "USER DECLINED "+currentScope +", SKIPPING." );
                scopesList = (AwardTemplateSyncScope[])ArrayUtils.remove(scopesList, 0);    // maintaining the current list
                awardForm.setCurrentSyncScopes(scopesList);
            } else {
                throw new RuntimeException( "Do not know what to do in this case!" );
            }
        }   
        awardForm.setOldTemplateCode(null);
        awardForm.setTemplateLookup(false);
        awardForm.setCurrentSyncScopes(null);
        awardForm.setCurrentSyncQuestionId(null);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    
    
    private String getScopeMessageToAddQuestion( AwardTemplateSyncScope scope ) {
        KualiConfigurationService configurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        String result = configurationService.getPropertyString("document.question.syncPanel.add.text."+scope);
        return result==null?"":result;
    }
    
    /**
     * Parses the method to call attribute to pick off the scopes to sync.
     *
     * @param request
     * @return returns the colon delimited list of scopes.  
     */
    protected String getSyncScopesString(HttpServletRequest request) {
        String syncScopesList = null;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName) && parameterName.indexOf(".scopes")!=-1) {
            syncScopesList = StringUtils.substringBetween(parameterName, ".scopes", ".anchor");
        }
        return syncScopesList;
    }
    
    
    protected KeyPersonnelService getKeyPersonnelService() {
        return KraServiceLocator.getService(KeyPersonnelService.class);
    }

    protected SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }

    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KraServiceLocator.getService(AwardLockService.class);
    }

    /**
     * @return
     */
    protected VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }

    private String determineRootAwardNumber(AwardForm awardForm) {
        String prevRootAwardNumber = awardForm.getPrevRootAwardNumber();
        return prevRootAwardNumber != null ? prevRootAwardNumber : awardForm.getAwardDocument().getAward().getAwardNumber();
    }

    private String determineParentAwardNumber(AwardForm awardForm) {
        String prevAwardNumber = awardForm.getPrevAwardNumber();
        return prevAwardNumber != null ? prevAwardNumber : Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
    }
    
    protected String getModuleIdentifierForOpeningDocument(HttpServletRequest request) {
        String moduleIdentifier = "";
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            moduleIdentifier = StringUtils.substringBetween(parameterName, ".moduleIdentifier", ".");
        }

        return moduleIdentifier;
    }
    
    private String getDocumentType(HttpServletRequest request) {
        String documentType = "";
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            documentType = StringUtils.substringBetween(parameterName, ".documentType", ".");
        }

        return documentType;
    }

   
    /**
     * KCAWD-494:If the user selects a sponsor template lookup, set a flag and store the current sponsor template code in the form.  The flag and the 
     * current value will be used on the return to check if the template has changed.
     * 
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiAction#performLookup(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if( (StringUtils.isNotBlank(parameterName) && parameterName.indexOf(".performLookup")!=-1 && parameterName.contains("templateCode:document.award.templateCode"))) {
            AwardForm awardForm = (AwardForm)form;
            awardForm.setTemplateLookup(true);
            ((AwardForm)form).setOldTemplateCode(((AwardForm)form).getAwardDocument().getAward().getTemplateCode() );
        }
        return super.performLookup(mapping, form, request, response);
    }
    
    /**
     * 
     * This should be called when an add or delete action is called that might be added to the sync queue.
     * It checks to ensure that syncMode is already enabled and will return an ActionForward for
     * the question or for the returnForward specified by the caller.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param syncType
     * @param object
     * @param attrName
     * @param returnForward
     * @return
     * @throws Exception
     */
    protected ActionForward confirmSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,
            AwardSyncType syncType, PersistableBusinessObject object, String awardAttrName, String attrName, ActionForward returnForward) 
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        if (awardForm.isSyncMode()) {
            awardForm.getAwardSyncBean().setCurrentForward(returnForward);
            awardForm.getAwardSyncBean().addPendingChange(syncType, object, awardAttrName, attrName);            
            return syncActionCaller(mapping, form, request, response);
        } else {
            return returnForward;
        }
    }
    
    /**
     * This should be called when a group add or delete action is called that might be added to the sync queue.
     * It checks to ensure that syncMode is already enabled and will return an ActionForward for
     * the question or for the returnForward specified by the caller.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param pendingChanges
     * @param returnForward
     * @return
     * @throws Exception
     */
    protected ActionForward confirmSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,
            List<AwardSyncPendingChangeBean> pendingChanges, ActionForward returnForward) 
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        if (awardForm.isSyncMode()) {
            awardForm.getAwardSyncBean().setCurrentForward(returnForward);
            awardForm.getAwardSyncBean().getPendingChanges().addAll(pendingChanges);
            return syncActionCaller(mapping, form, request, response);
        } else {
            return returnForward;
        }
    }    
    
    /**
     * When synchronizing a new addition or deletion call this method. It will confirm the change
     * and then add the change.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncActionCaller(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        String message = ADD_SYNC_CHANGE_QUESTION;
        if (awardForm.getAwardSyncBean().getPendingChanges().get(0).getSyncType().equals(AwardSyncType.DELETE_SYNC.getSyncValue())) {
            message = DEL_SYNC_CHANGE_QUESTION;
        }
        //overwrite the method to call to call this instead of the original method which would result
        //in an error as the action should have already been performed
        request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, "methodToCall.syncActionCaller");
        ActionForward confirmAction = confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                                              "confirmSyncActionKey", message), 
                                              "confirmSyncAction", "refuseSyncAction");
        if (confirmAction != null) {
            return confirmAction;
        } else {
            return awardForm.getAwardSyncBean().getCurrentForward();
        }
    }
    
    /**
     * If the user answers yes to a confirm sync action question call this method.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward confirmSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardSyncBean().confirmPendingChanges();
        return null;
    }
    
    /**
     * If the user answers no to a confirm sync action question call this method.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward refuseSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardSyncBean().getPendingChanges().clear();
        return null;
    }
    
    protected AwardSyncCreationService getAwardSyncCreationService() {
        return KraServiceLocator.getService(AwardSyncCreationService.class);
    }
    
    protected AwardSyncService getAwardSyncService() {
        return KraServiceLocator.getService(AwardSyncService.class);
    }

    protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KraServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    } 
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     * If Award Infos or dates have been edited in a T&M document, then we want to suppress the cancel action.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        super.populateAuthorizationFields(formBase);
        AwardForm awardForm = (AwardForm) formBase;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        Award award = awardDocument.getAward();
        Map documentActions = formBase.getDocumentActions();
        //if Award version has been edited in T&M doc then we suppress cancel action.
        //workaround for copied awards.  On initial copy the Award has two entries in AAI table and originating award version of first entry is null.
        if (award.getAwardAmountInfos().size() > 1) {
                if(!(award.getAwardAmountInfos().size() == 2 && award.getAwardAmountInfos().get(0).getOriginatingAwardVersion() == null)
                                && documentActions.containsKey(KNSConstants.KUALI_ACTION_CAN_CANCEL)) {
                            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_CANCEL);
                }       
        }   
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KraServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public ReportTrackingService getReportTrackingService() {
        if (reportTrackingService == null) {
            reportTrackingService = KraServiceLocator.getService(ReportTrackingService.class);
        }
        return reportTrackingService;
    }
}
