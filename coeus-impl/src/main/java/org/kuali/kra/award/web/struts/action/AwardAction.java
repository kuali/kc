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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.coi.framework.*;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper.ValidationState;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.*;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyBean;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncCreationService;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachmentFormBean;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingService;
import org.kuali.kra.award.paymentreports.closeout.CloseoutReportTypeValuesFinder;
import org.kuali.kra.award.service.AwardDirectFandADistributionService;
import org.kuali.kra.award.service.AwardReportsService;
import org.kuali.kra.award.service.AwardSponsorTermService;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.coeus.common.budget.framework.core.BudgetParentActionBase;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.award.infrastructure.AwardRoleConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.history.TransactionDetailType;
import org.kuali.kra.timeandmoney.rules.TimeAndMoneyAwardDateSaveRuleImpl;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyExistenceService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.rice.krad.util.KRADConstants.CONFIRMATION_QUESTION;

/**
 * 
 * This class represents base Action class for all the Award pages.
 */
public class AwardAction extends BudgetParentActionBase {
    protected static final String AWARD_ID_PARAMETER_NAME = "awardId";
    private static final String INITIAL_TRANSACTION_COMMENT = "Initial Time And Money creation transaction";
    private static final String REPORTS_PROPERTY_NAME = "Reports";
    private static final String PAYMENT_INVOICES_PROPERTY_NAME = "Payments and Invoices";
    private static final String COMFIRMATION_PARAM_STRING = "After Award {0} information is synchronized, make sure that the Award Sponsor Contacts information is also synchronized with the same sponsor template. Failing to do so will result in data inconsistency. Are you sure you want to replace current {0} information with selected {1} template information?";
    private static final String SUPER_USER_ACTION_REQUESTS = "superUserActionRequests";
    public static final String DATA_VALIDATION = "datavalidation";
    public static final String DOC_HANDLER = "docHandler";
    public static final String ERROR_AWARD_AWARDHIERARCHY_SYNC_LOCKED = "error.award.awardhierarchy.sync.locked";
    public static final String AWARD_DOCUMENT = "AwardDocument";
    public static final String SAVE = "save";
    public static final String AWARD_NUMBER = "awardNumber";
    public static final String ACTIVE = "active";
    public static final String ROOT_AWARD_NUMBER = "rootAwardNumber";
    public static final String VIEW_ONLY = "viewOnly";
    public static final String FULL_ENTRY = "fullEntry";
    public static final String TIMEANDMONEY_DOCUMENT = "timeandmoney document";
    public static final String ROOT_AWARD = "000000-00000";
    public static final String BACK_LOCATION = "&backLocation=";
    public static final String ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST = "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST";
    public static final String ENABLE_AWARD_ANT_OBL_DIRECT_INDIRECT_COST_TRUE = "1";
    public static final String AWARD_DOCUMENT_NUMBER = "awardDocumentNumber";
    public static final String BASIC = "basic";
    public static final String SELECTED_AWARD_NUMBER = "selectedAwardNumber";
    public static final String DOCUMENT_AWARD_AWARD_TEMPLATE = "document.award.awardTemplate";
    public static final String AWARD_TEMPLATE = "awardTemplate";
    public static final String PROCESS_SYNC_AWARD = "processSyncAward";
    public static final String METHOD_TO_CALL_SYNC_ACTION_CALLER = "methodToCall.syncActionCaller";
    public static final String CONFIRM_SYNC_ACTION_KEY = "confirmSyncActionKey";
    public static final String CONFIRM_SYNC_ACTION = "confirmSyncAction";
    public static final String REFUSE_SYNC_ACTION = "refuseSyncAction";

    public static final String DISABLE_ATTACHMENT_REMOVAL = "disableAttachmentRemoval";
    public static final String CURRENT_VERSION_BUDGETS = "currentVersionBudgets";

    //question constants
    private static final String QUESTION_VERIFY_SYNC="VerifySync";
    private static final String QUESTION_VERIFY_EMPTY_SYNC="VerifyEmptySync";

    private static final Log LOG = LogFactory.getLog( AwardAction.class );

    private enum SuperUserAction {
        SUPER_USER_APPROVE, TAKE_SUPER_USER_ACTIONS
    }

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

    private static final String DOCUMENT_ROUTE_QUESTION="DocRoute";

    private static final String ADD_SYNC_CHANGE_QUESTION = "document.question.awardhierarchy.sync";
    private static final String DEL_SYNC_CHANGE_QUESTION = "document.question.awardhierarchy.sync";

    private transient ParameterService parameterService;
    private transient AwardBudgetService awardBudgetService;
    private transient AwardService awardService;
    private transient ReportTrackingService reportTrackingService;
    private transient KcNotificationService notificationService;
    private transient SubAwardService subAwardService;
    TimeAndMoneyAwardDateSaveRuleImpl timeAndMoneyAwardDateSaveRuleImpl;
    private transient TimeAndMoneyVersionService timeAndMoneyVersionService;
    private transient ProjectPublisher projectPublisher;
    private transient ProjectRetrievalService projectRetrievalService;

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward docHandlerForward;
        cleanUpUserSession();
        docHandlerForward = handleLoadingDocument(mapping, awardForm, request, response);
        
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        //check to see if this document might be a part of an active award sync(if it is lock it)
        if (awardForm.getMethodToCall().equals(DOC_HANDLER)){
            AwardDocument parentSyncAward = 
                    getAwardSyncService().getAwardLockingHierarchyForSync(awardDocument, GlobalVariables.getUserSession().getPrincipalId()); 
            if (parentSyncAward != null) {
                KNSGlobalVariables.getMessageList().add(ERROR_AWARD_AWARDHIERARCHY_SYNC_LOCKED, parentSyncAward.getDocumentNumber());
                awardForm.setViewOnly(true);
            }
            setBooleanAwardInMultipleNodeHierarchyOnForm (awardDocument.getAward());
            awardForm.initializeFormOrDocumentBasedOnCommand();
            setBooleanAwardHasTandMOrIsVersioned(awardDocument.getAward());
            setSubAwardDetails(awardDocument.getAward());
            handlePlaceHolderDocument(awardForm, awardDocument);
        }

        ActionForward commandForward = handleDocHandlerForwards(mapping, awardForm, request, response);
        if (commandForward != null) {
        	return commandForward;
        } else {
        	return docHandlerForward;
        }
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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        if (awardForm.isAuditActivated()){
            awardForm.setUnitRulesMessages(getUnitRulesMessages(awardForm.getAwardDocument()));
        }
        if (GlobalVariables.getAuditErrorMap().isEmpty()) {
            getAuditHelper().auditConditionally((AwardForm) form);
        }
        
        return actionForward;
    }
    
    protected List<String> getUnitRulesMessages(AwardDocument awardDoc) {
        KrmsRulesExecutionService rulesService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
        return rulesService.processUnitValidations(awardDoc.getLeadUnitNumber(), awardDoc);
    }

    protected void populateAwardHierarchy(ActionForm form) throws WorkflowException {
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();

        List<String> order = new ArrayList<>();
        AwardHierarchyBean helperBean = awardForm.getAwardHierarchyBean();
        AwardHierarchy rootNode = helperBean.getRootNode();
        Award currentAward = awardDocument.getAward();
        awardForm.setRootAwardNumber(rootNode.getRootAwardNumber());
        buildAwardHierarchySourceAndTargetList(awardForm, currentAward);
    }
    

    protected TimeAndMoneyExistenceService getTimeAndMoneyExistenceService() {
        return KcServiceLocator.getService(TimeAndMoneyExistenceService.class);
    }
    
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward;
        AwardForm awardForm = (AwardForm) form;

        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(), awardForm.getAwardHierarchyBean().getRootNode().getAwardNumber())){
            forward = super.approve(mapping, form, request, response);
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }

        String routeHeaderId = awardForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, AWARD_DOCUMENT);
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }
    
    protected ActionForward submitAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        if(getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(), awardForm.getAwardHierarchyBean().getRootNode().getAwardNumber())){
            forward = super.route(mapping, form, request, response);
            populateAwardHierarchy(awardForm);
        }else{
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
        }
        
        String routeHeaderId = awardForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, AWARD_DOCUMENT);
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        awardForm.setAuditActivated(true);

        ValidationState status = getAuditHelper().isValidSubmission(awardForm, true);

        if (awardForm.getUnitRulesErrors().size() > 0) {
            status = ValidationState.ERROR;
        }
        
        if (status == ValidationState.WARNING) {
            return handleWarning(mapping, form, request, response);
        } else if(status == ValidationState.OK) {
           return submitAward(mapping, form, request, response);
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages(); 
            GlobalVariables.getMessageMap().putError(DATA_VALIDATION,KeyConstants.ERROR_WORKFLOW_SUBMISSION);
            return mapping.findForward(Constants.MAPPING_BASIC);
         }
    }

    protected AuditHelper getAuditHelper() {
        return KcServiceLocator.getService(AuditHelper.class);
    }

    protected ActionForward handleWarning(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        if(question == null){
            return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION,
                    "Validation Warning Exists. Are you sure want to submit to workflow routing.", KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
        } else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
            return submitAward(mapping, form, request, response);
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        AwardForm awardForm = (AwardForm) form;

        if (getTimeAndMoneyExistenceService().validateTimeAndMoneyRule(awardForm.getAwardDocument().getAward(),
                awardForm.getAwardHierarchyBean().getRootNode().getAwardNumber())) {
            awardForm.setAuditActivated(true);
            ValidationState status = getAuditHelper().isValidSubmission(awardForm, true);
            if (status == ValidationState.ERROR) {
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError(DATA_VALIDATION, KeyConstants.ERROR_WORKFLOW_SUBMISSION);
                forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            } else {
                forward = super.blanketApprove(mapping, form, request, response);
            }
        } else {
            getTimeAndMoneyExistenceService().addAwardVersionErrorMessage();
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        String routeHeaderId = awardForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_ACTIONS_PAGE, AWARD_DOCUMENT);
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;

        Award award = awardForm.getAwardDocument().getAward();
        checkAwardNumber(award);
        
        if (award.getAwardApprovedSubawards() == null || award.getAwardApprovedSubawards().isEmpty()) {
            award.setSubContractIndicator(Constants.NO_FLAG);
        } else {
            award.setSubContractIndicator(Constants.YES_FLAG);
        }
        if (award.getAwardTransferringSponsors() == null || award.getAwardTransferringSponsors().isEmpty()) {
            award.setTransferSponsorIndicator(Constants.NO_FLAG);
        } else {
            award.setTransferSponsorIndicator(Constants.YES_FLAG);
        }
        if (award.getKeywords() == null || award.getKeywords().isEmpty()) {
            award.setScienceCodeIndicator(Constants.NO_FLAG);
        } else {
            award.setScienceCodeIndicator(Constants.YES_FLAG);
        }

        ActionForward forward = super.save(mapping, form, request, response);
        if (awardForm.getMethodToCall().equals(SAVE) && awardForm.isAuditActivated()) {
            forward = mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }

        
        AwardHierarchyBean bean = awardForm.getAwardHierarchyBean();
        if (bean.saveHierarchyChanges()) {
            List<String> order = new ArrayList<>();
            awardForm.setAwardHierarchyNodes(bean.getAwardHierarchy(bean.getRootNode().getAwardNumber(), order));
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

        getProjectPublisher().publishProject(getProjectRetrievalService()
                .retrieveProject(awardForm.getAwardDocument().getAward().getAwardId().toString()));

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

    protected Award getAward(ActionForm form) {
        return getAwardDocument(form).getAward(); 
    }

    protected AwardDocument getAwardDocument(ActionForm form) {
        return ((AwardForm) form).getAwardDocument();
    }

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
        for(AwardCustomData customData : award.getAwardCustomDataList()) {
            customData.setAward(award);
        }
    }

    protected AwardNumberService getAwardNumberService() {
        return KcServiceLocator.getService(AwardNumberService.class);
    }
    
    /**
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
        
        String userId = GlobalVariables.getUserSession().getPrincipalName();
        Award award = awardDocument.getAward();
        getAwardService().updateAwardSequenceStatus(award, VersionStatus.PENDING);
        getVersionHistoryService().updateVersionHistory(award, VersionStatus.PENDING, userId);
        
        if(!awardForm.getAwardDocument().isDocumentSaveAfterVersioning()) {
            awardForm.getAwardHierarchyBean().createDefaultAwardHierarchy();
            awardForm.getAwardHierarchyBean().saveHierarchyChanges();            
        }
    }

    protected void createInitialAwardUsers(Award award) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        if (!systemAuthorizationService.hasRole(userId, award.getNamespace(), AwardRoleConstants.AWARD_MODIFIER.getAwardRole())) {
            kraAuthService.addDocumentLevelRole(userId, AwardRoleConstants.AWARD_MODIFIER.getAwardRole(), award);
        }
    }

    protected void populateStaticCloseoutReports(AwardForm form){
        CloseoutReportTypeValuesFinder closeoutReportTypeValuesFinder = new CloseoutReportTypeValuesFinder();

        form.getAwardCloseoutBean().addAwardCloseoutStaticItems(closeoutReportTypeValuesFinder.getKeyValues());
    }

    protected AwardHierarchyService getAwardHierarchyService(){
        return KcServiceLocator.getService(AwardHierarchyService.class);
    }

    protected boolean isValidSave(AwardForm awardForm) {
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        String leadUnitNumber = awardDocument.getLeadUnitNumber();
        if (StringUtils.isNotEmpty(leadUnitNumber) && checkNoMoreThanOnePI(awardDocument.getAward())) {
            String userId = GlobalVariables.getUserSession().getPrincipalId();
            UnitAuthorizationService authService = KcServiceLocator.getService(UnitAuthorizationService.class);
            return authService.hasMatchingQualifiedUnits(userId, Constants.MODULE_NAMESPACE_AWARD,
                    AwardPermissionConstants.MODIFY_AWARD.getAwardPermission(), leadUnitNumber);
        }
        return false; 
    }
    
    private boolean checkNoMoreThanOnePI(Award award) {
        int piCount = 0;
        int counter = 0;
        ArrayList<String> fields = new ArrayList<>();
        for (AwardPerson p : award.getProjectPersons()) {
            if (p.isPrincipalInvestigator()) {
                piCount++;
                fields.add("projectPersonnelBean.projectPersonnel[" + counter + "].contactRoleCode");
            }
            counter++;
        }
        boolean valid = piCount <= 1;
        if (!valid) {
            for (String field  : fields) {
                GlobalVariables.getMessageMap().putError(field, AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
            }
        }
        
        return valid;
    }

    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KRADConstants.PARAMETER_DOC_ID);
        sb.append("=");
        sb.append(docIdRequestParameter);
        return sb.toString();
    }

    public ActionForward home(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
        setBooleanAwardInMultipleNodeHierarchyOnForm(awardDocument.getAward());
        setBooleanAwardHasTandMOrIsVersioned(awardDocument.getAward());
        setSubAwardDetails(awardDocument.getAward());
        AwardAmountInfoService awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        return mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
    }

    public void setBooleanAwardInMultipleNodeHierarchyOnForm (Award award) {
        Map<String, Object> fieldValues = new HashMap<>();
        String awardNumber = award.getAwardNumber();
        fieldValues.put(AWARD_NUMBER, awardNumber);
        fieldValues.put(ACTIVE, Boolean.TRUE);
        List<AwardHierarchy> awardHierarchies = (List<AwardHierarchy>) getBusinessObjectService().findMatching(AwardHierarchy.class, fieldValues);
        if (awardHierarchies.size() == 0) {
            award.setAwardInMultipleNodeHierarchy(false);
        }else {
            Map<String, Object> newFieldValues = new HashMap<>();
            String rootAwardNumber = awardHierarchies.get(0).getRootAwardNumber();
            newFieldValues.put(ROOT_AWARD_NUMBER, rootAwardNumber);
            newFieldValues.put(ACTIVE, Boolean.TRUE);
            int matchingValues = getBusinessObjectService().countMatching(AwardHierarchy.class, newFieldValues);
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
     */
    public void setBooleanAwardHasTandMOrIsVersioned (Award award) {
        boolean previousVersionHasBeenEditedInTandMDocument = false;
        // what we really want to do is check to see if latest version of Awards has a T&M doc associated with it.
        // If it's versioned, that's OK, we still want to allow editing of the amounts and dates.
        List<VersionHistory> awardHistory = getVersionHistoryService().findVersionHistory(Award.class, award.getAwardNumber());
        if(awardHistory.size() > 1) {
            if (award.getSequenceNumber() == 1 && award.getAwardAmountInfos().size() > 2) {
                previousVersionHasBeenEditedInTandMDocument = true;
            } else if (award.getSequenceNumber() > 1 && award.getAwardAmountInfos().size() > 1) {
                previousVersionHasBeenEditedInTandMDocument = true;
            }
        }
        award.setAwardHasAssociatedTandMOrIsVersioned(previousVersionHasBeenEditedInTandMDocument);
    }

    public ActionForward contacts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Award award = getAward(form);

        award.initCentralAdminContacts();

        return mapping.findForward(Constants.MAPPING_AWARD_CONTACTS_PAGE);
    }

    public ActionForward commitments(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.MAPPING_AWARD_COMMITMENTS_PAGE);
    }


    protected void generateDirectFandADistribution(Award award) {
        if(!(award.getAwardEffectiveDate() == null)) {
            // delete entries that were added during previous T&M initiations but the doc cancelled.
            getBusinessObjectService().delete(award.getAwardDirectFandADistributions());
            
            Boolean autoGenerate = getParameterService().getParameterValueAsBoolean(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, 
                                                                                    KeyConstants.AUTO_GENERATE_TIME_MONEY_FUNDS_DIST_PERIODS); 
            if (autoGenerate) {
                AwardDirectFandADistributionService awardDirectFandADistributionService = getAwardDirectFandADistributionService();
                award.setAwardDirectFandADistributions
                                    (awardDirectFandADistributionService.
                                            generateDefaultAwardDirectFandADistributionPeriods(award));
            }
        }
    }

    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        ActionForward actionForward;
        //if award document is view only then we don't need to save document before opening T&M document.
        if ((!awardForm.getEditingMode().containsKey(VIEW_ONLY) || awardForm.getEditingMode().containsKey(FULL_ENTRY)) &&
                !awardDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
            this.save(mapping, form, request, response);
        }
        //if T&M document is created, there must be a project start date on the award.
        timeAndMoneyAwardDateSaveRuleImpl = new TimeAndMoneyAwardDateSaveRuleImpl();
        timeAndMoneyAwardDateSaveRuleImpl.enforceAwardStartDatePopulated(awardDocument.getAward());
        
        
        if(GlobalVariables.getMessageMap().hasNoErrors()){
            DocumentService documentService = KcServiceLocator.getService(DocumentService.class);    
            populateAwardHierarchy(form);
    
            Award currentAward = awardDocument.getAward();
    
            String rootAwardNumber = awardForm.getAwardHierarchyNodes().get(currentAward.getAwardNumber()).getRootAwardNumber();
            String documentNumber = getTimeAndMoneyVersionService().getCurrentTimeAndMoneyDocumentNumber(rootAwardNumber);
            
            Award rootAward = getAwardVersionService().getWorkingAwardVersion(rootAwardNumber);   

            if(documentNumber == null) {
            	if (!getTimeAndMoneyVersionService().validateCreateNewTimeAndMoneyDocument(rootAwardNumber)) {
            		return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            	}
                generateDirectFandADistribution(currentAward);
                TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
                timeAndMoneyDocument.getDocumentHeader().setDocumentDescription(TIMEANDMONEY_DOCUMENT);
                timeAndMoneyDocument.setRootAwardNumber(rootAwardNumber);
                timeAndMoneyDocument.setAwardNumber(rootAward.getAwardNumber());
                timeAndMoneyDocument.setAward(rootAward);
                AwardAmountTransaction aat = new AwardAmountTransaction();
                aat.setAwardNumber(ROOT_AWARD);//need to initialize one element in this collection because the doc is saved on creation.
                aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
                String defaultTxnTypeStr = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_TIME_AND_MONEY, ParameterConstants.DOCUMENT_COMPONENT, Constants.DEFAULT_TXN_TYPE_COPIED_AWARD);
                if(StringUtils.isNotEmpty(defaultTxnTypeStr)) {
                    aat.setTransactionTypeCode(Integer.parseInt(defaultTxnTypeStr));
                }                
                aat.setAwardNumber(rootAward.getAwardNumber());
                //any code for initial transaction and history.
                TransactionDetail transactionDetail  = addTransactionDetails(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT, rootAward.getAwardNumber(), rootAward.getSequenceNumber(),
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
                documentNumber = timeAndMoneyDocument.getDocumentHeader().getDocumentNumber();
            }

            String routeHeaderId = documentNumber;
            String backUrl = URLEncoder.encode(buildActionUrl(awardDocument.getDocumentNumber(), Constants.MAPPING_AWARD_HOME_PAGE, AWARD_DOCUMENT), StandardCharsets.UTF_8.name());
            String forward = buildForwardUrl(routeHeaderId) + BACK_LOCATION + backUrl;
            actionForward = new ActionForward(forward, true);
            //add this to session and leverage in T&M for return to award action.
            GlobalVariables.getUserSession  ().addObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + documentNumber, awardDocument.getDocumentNumber());            
        } else {
            actionForward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return actionForward;

    }

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
            transactionDetail.setAnticipatedIndirectAmount(new ScaleTwoDecimal(0));
            transactionDetail.setObligatedAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedDirectAmount(rootAward.getObligatedTotal());
            transactionDetail.setObligatedIndirectAmount(new ScaleTwoDecimal(0));
        }
        transactionDetail.setAwardNumber(rootAward.getAwardNumber());
        transactionDetail.setTransactionId(0L);
        transactionDetail.setTimeAndMoneyDocumentNumber(documentNumber);
        transactionDetail.setComments(commentsString);
        transactionDetail.setTransactionDetailType(TransactionDetailType.PRIMARY.toString());
        return transactionDetail;
        
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.DOCUMENT_COMPONENT, ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST);
        if(directIndirectEnabledValue.equals(ENABLE_AWARD_ANT_OBL_DIRECT_INDIRECT_COST_TRUE)) {
            returnValue = true;
        }
        return returnValue;
    }

    private void addNewAwardAmountInfoForInitialTransaction(Award rootAward, String documentNumber) {
        
        AwardAmountInfo rootAwardAmountInfo = rootAward.getLastAwardAmountInfo();
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardNumber(rootAward.getAwardNumber());
        newAwardAmountInfo.setSequenceNumber(rootAward.getSequenceNumber());
        newAwardAmountInfo.setFinalExpirationDate(rootAwardAmountInfo.getFinalExpirationDate());
        newAwardAmountInfo.setCurrentFundEffectiveDate(rootAwardAmountInfo.getCurrentFundEffectiveDate());
        newAwardAmountInfo.setObligationExpirationDate(rootAwardAmountInfo.getObligationExpirationDate());
        newAwardAmountInfo.setTimeAndMoneyDocumentNumber(documentNumber);
        newAwardAmountInfo.setTransactionId(null);
        newAwardAmountInfo.setAward(rootAward);
       //add transaction amounts to the AmountInfo
        if(isDirectIndirectViewEnabled()){
            newAwardAmountInfo.setAmountObligatedToDate(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setObligatedChange(rootAwardAmountInfo.getObligatedChange());
            newAwardAmountInfo.setObligatedChangeDirect(rootAwardAmountInfo.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedChangeIndirect(rootAwardAmountInfo.getObligatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChange(rootAwardAmountInfo.getAnticipatedChange());
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChangeDirect(rootAwardAmountInfo.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedChangeIndirect(rootAwardAmountInfo.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotalDirect().add(rootAward.getObligatedTotalIndirect()));
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotalDirect().add(rootAward.getAnticipatedTotalIndirect()));
        } else {
            newAwardAmountInfo.setAmountObligatedToDate(rootAwardAmountInfo.getAmountObligatedToDate());
            newAwardAmountInfo.setObligatedTotalDirect(rootAward.getObligatedTotalDirect());
            newAwardAmountInfo.setObligatedTotalIndirect(rootAward.getObligatedTotalIndirect());
            newAwardAmountInfo.setObligatedChange(rootAwardAmountInfo.getObligatedChange());
            newAwardAmountInfo.setObligatedChangeDirect(rootAwardAmountInfo.getObligatedChangeDirect());
            newAwardAmountInfo.setObligatedChangeIndirect(rootAwardAmountInfo.getObligatedChangeIndirect());
            newAwardAmountInfo.setAnticipatedChange(rootAwardAmountInfo.getAnticipatedChange());
            newAwardAmountInfo.setAnticipatedTotalAmount(rootAward.getAnticipatedTotal());
            newAwardAmountInfo.setAnticipatedTotalDirect(rootAward.getAnticipatedTotalDirect());
            newAwardAmountInfo.setAnticipatedTotalIndirect(rootAward.getAnticipatedTotalIndirect());
            newAwardAmountInfo.setAnticipatedChangeDirect(rootAwardAmountInfo.getAnticipatedChangeDirect());
            newAwardAmountInfo.setAnticipatedChangeIndirect(rootAwardAmountInfo.getAnticipatedChangeIndirect());
            newAwardAmountInfo.setObliDistributableAmount(rootAward.getObligatedTotal());
            newAwardAmountInfo.setAntDistributableAmount(rootAward.getAnticipatedTotal());
        }
        newAwardAmountInfo.setOriginatingAwardVersion(rootAward.getSequenceNumber());
        rootAward.getAwardAmountInfos().add(newAwardAmountInfo);
        getBusinessObjectService().save(rootAward);
    }
    
    public List<Award> getAwardVersions(String awardNumber) {
        return (List<Award>)getBusinessObjectService().findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(awardNumber), "sequenceNumber", true);
    }
    
    public AwardVersionService getAwardVersionService() {
        return KcServiceLocator.getService(AwardVersionService.class);
    }
    
    public ActionForward openWindow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String documentNumber = request.getParameter(AWARD_DOCUMENT_NUMBER);
        String awardNumber = request.getParameter(AWARD_NUMBER);
        AwardForm awardForm = (AwardForm)form;
        
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        AwardDocument awardDocument = (AwardDocument)documentService.getByDocumentHeaderId(documentNumber);
        Award award = getAwardService().getAwardAssociatedWithDocument(awardDocument.getDocumentNumber());
        awardForm.setCurrentAwardNumber(awardNumber);
        awardForm.setCurrentSeqNumber(award.getSequenceNumber().toString());
        awardDocument.setAward(award);
        awardForm.setDocument(awardDocument);
        populateAwardHierarchy(awardForm);
        return mapping.findForward(BASIC);
    }  
   
    private Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put(AWARD_NUMBER, goToAwardNumber);
        return map;
    }

    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     */
    public boolean isNewAward(AwardForm awardForm) {
        return awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }

    /**
     *
     * This method is a helper method to retrieve AwardSponsorTermService.
     */
    protected AwardDirectFandADistributionService getAwardDirectFandADistributionService() {
        return KcServiceLocator.getService(AwardDirectFandADistributionService.class);
    }

    /**
     *
     * This method gets called upon navigation to Payment, Reports and Terms tab.
     */
    public ActionForward paymentReportsAndTerms(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;

        setReportsAndTermsOnAwardForm(awardForm);

        return mapping.findForward(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
    }

    protected void setReportsAndTermsOnAwardForm(AwardForm awardForm) {
        AwardSponsorTermService awardSponsorTermService = getAwardSponsorTermService();
        List<KeyValue> sponsorTermTypes = awardSponsorTermService.retrieveSponsorTermTypesToAwardFormForPanelHeaderDisplay();
        awardForm.getSponsorTermFormHelper().setSponsorTermTypes(sponsorTermTypes);
        awardForm.getSponsorTermFormHelper().setNewSponsorTerms(awardSponsorTermService.getEmptyNewSponsorTerms(sponsorTermTypes));

        AwardReportsService awardReportsService = KcServiceLocator.getService(AwardReportsService.class);
        Map<String,Object> initializedObjects = awardReportsService.initializeObjectsForReportsAndPayments(
                                                    awardForm.getAwardDocument().getAward());
        awardForm.setReportClasses((List<ConcreteKeyValue>) initializedObjects.get(
                                      Constants.REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.getAwardReportsBean().setNewAwardReportTerms((List<AwardReportTerm>) initializedObjects.get(
                                          Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.getAwardReportsBean().setNewAwardReportTermRecipients((List<AwardReportTermRecipient>) initializedObjects.get(
                                                    Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.setReportClassForPaymentsAndInvoices((ReportClass) initializedObjects.get(
                                                        Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL));
        awardForm.buildReportTrackingBeans();

    }

    /**
     *
     * This method is a helper method to retrieve AwardSponsorTermService.
     */
    protected AwardSponsorTermService getAwardSponsorTermService() {
        return KcServiceLocator.getService(AwardSponsorTermService.class);
    }

    /**
     *
     * This method gets called upon navigation to Special Review tab.
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        ((AwardForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
    }

    /**
     *
     * This method gets called upon navigation to Special Review tab.
     */
    public ActionForward customData(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward(Constants.MAPPING_AWARD_CUSTOM_DATA_PAGE);
    }

    public ActionForward questions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.MAPPING_AWARD_QUESTIONS_PAGE);
    }

    public ActionForward permissions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        ((AwardForm)form).getPermissionsHelper().prepareView();
        return mapping.findForward(Constants.MAPPING_AWARD_PERMISSIONS_PAGE);
    }

    public ActionForward notesAndAttachments(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardCommentBean().setAwardCommentScreenDisplayTypesOnForm();
        awardForm.getAwardCommentBean().setAwardCommentHistoryFlags();
        setDisableAttachmentRemovalIndicator(((AwardForm) form).getAwardAttachmentFormBean());
        return mapping.findForward(Constants.MAPPING_AWARD_NOTES_AND_ATTACHMENTS_PAGE);
    }

    protected void setDisableAttachmentRemovalIndicator(AwardAttachmentFormBean awardAttachmentForm) {
        if (awardAttachmentForm != null) {
            awardAttachmentForm.setDisableAttachmentRemovalIndicator(getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                    ParameterConstants.DOCUMENT_COMPONENT, DISABLE_ATTACHMENT_REMOVAL));
        }
    }

   public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       AwardForm awardForm = (AwardForm) form;
       if (awardForm.getDocument().getDocumentNumber() == null) {
           //if we are entering this from the search results
           loadDocumentInForm(request, awardForm);
       }
       awardForm.getMedusaBean().setMedusaViewRadio("0");
       awardForm.getMedusaBean().setModuleName(Constants.AWARD_MODULE);
       awardForm.getMedusaBean().setModuleIdentifier(awardForm.getAwardDocument().getAward().getAwardId());
       awardForm.getMedusaBean().generateParentNodes();
       return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);
   }

    public ActionForward awardActions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {

        AwardForm awardForm = (AwardForm) form;
        String command = request.getParameter(KewApiConstants.COMMAND_PARAMETER);
        if(StringUtils.isNotEmpty(command) && KewApiConstants.DOCSEARCH_COMMAND.equals(command)) {
            loadDocumentInForm(request, awardForm); 
            WorkflowDocument workflowDoc = awardForm.getAwardDocument().getDocumentHeader().getWorkflowDocument();
            if(workflowDoc != null)
                awardForm.setDocTypeName(workflowDoc.getDocumentTypeName());
            request.setAttribute(SELECTED_AWARD_NUMBER, awardForm.getAwardDocument().getAward().getAwardNumber());
        } 
        populateAwardHierarchy(form); 

        return mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
    }

    public ActionForward budgets(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        String awardNumber = awardForm.getAwardDocument().getAward().getAwardNumber();
        // OJB sometimes erroneously returns a Budget instead of a AwardBudExt. The following line will
        // force OJB to load the AwardBudgetExt first making it return the right object in all subsequent
        // retrievals.
        getAwardService().findAwardsForAwardNumber(awardNumber).stream().forEach(award -> award.refreshReferenceObject(CURRENT_VERSION_BUDGETS));
        getAwardBudgetService().populateBudgetLimitSummary(awardForm.getBudgetLimitSummary(), awardForm.getAwardDocument().getAward());
        return mapping.findForward(Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE);
    }
    
	ActionForward handleLoadingDocument(ActionMapping mapping, AwardForm awardForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		String command = awardForm.getCommand();
		if (Constants.MAPPING_AWARD_ACTIONS_PAGE.equals(command)) {
			loadDocument(awardForm);
		} else if (Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE.equals(command)) {
			loadDocument(awardForm);
		} else if (Constants.MAPPING_AWARD_HOME_PAGE.equals(command)) {
			loadDocument(awardForm);
		} else {
			forward = super.docHandler(mapping, awardForm, request, response);
		}

		return forward;
	}
    
    ActionForward handleDocHandlerForwards(ActionMapping mapping, AwardForm awardForm, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        ActionForward forward = null;

        String command = awardForm.getCommand();
        if (Constants.MAPPING_AWARD_ACTIONS_PAGE.equals(command)) {
            forward = awardActions(mapping, awardForm, request, response);
        } else if (Constants.MAPPING_AWARD_BUDGET_VERSIONS_PAGE.equals(command)) {
            forward = budgets(mapping,awardForm,request,response);
        } else if (Constants.MAPPING_AWARD_HOME_PAGE.equals(command)) {
            forward = home(mapping,awardForm,request,response);
        }
        
        return forward;
    }

    protected void loadDocumentInForm(HttpServletRequest request, AwardForm awardForm)
    throws WorkflowException {
        String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
        AwardDocument retrievedDocument = (AwardDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
        awardForm.setDocument(retrievedDocument);
        request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
        handlePlaceHolderDocument(awardForm, retrievedDocument);        
    }

    @Override
    protected DocumentService getDocumentService() {
        return KRADServiceLocatorWeb.getDocumentService();
    }

    protected KualiRuleService getKualiRuleService() {
        return KcServiceLocator.getService(KualiRuleService.class);
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
     */
    public ActionForward syncAwardTemplate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        AwardTemplateSyncScope[] scopes;
        String syncScopes = getSyncScopesString( request );
        
        if (awardDocument.getAward().getTemplateCode() == null || awardDocument.getAward().getAwardTemplate() == null) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError(
                    StringUtils.isBlank(syncScopes) ? "document.award.awardTemplate" 
                            : String.format("document.award.awardTemplate.%s", StringUtils.substring(syncScopes,1)), 
                            KeyConstants.ERROR_NO_SPONSOR_TEMPLATE_FOUND);
            awardForm.setOldTemplateCode(null);
            awardForm.setTemplateLookup(false);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
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
        if (StringUtils.isNotBlank(syncScopes) && syncScopes.length() > 1 && syncScopes.contains(":")) {
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
        AwardTemplateSyncService awardTemplateSyncService = KcServiceLocator.getService(AwardTemplateSyncService.class);
        Map< AwardTemplateSyncScope,Boolean> requiresQuestionMap = new HashMap<>();
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

    public ActionForward fullSyncToAwardTemplate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();

        if( awardDocument.getAward().getTemplateCode() == null ) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError(DOCUMENT_AWARD_AWARD_TEMPLATE,KeyConstants.ERROR_NO_TEMPLATE_CODE);
            awardForm.setOldTemplateCode(null);
            awardForm.setTemplateLookup(false);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        } else {
            awardDocument.getAward().refreshReferenceObject(AWARD_TEMPLATE);
        }
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        boolean proceedToProcessSyncAward = true;
        
        if( question == null ) {
            //setup before forwarding to the processor.
            AwardTemplateSyncScope[] scopes;
            scopes = new AwardTemplateSyncScope[] { AwardTemplateSyncScope.FULL };
            HashMap<AwardTemplateSyncScope,Boolean> confirmMap = new HashMap<>();
            confirmMap.put(AwardTemplateSyncScope.FULL, true);
            awardForm.setCurrentSyncScopes(scopes);
            awardForm.setSyncRequiresConfirmationMap(confirmMap);
        } else if((QUESTION_VERIFY_SYNC + ":" + AwardTemplateSyncScope.FULL).equals(question)) {
            
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
                awardDocument.getAward().refreshReferenceObject(AWARD_TEMPLATE);
                awardForm.setOldTemplateCode(null);
                awardForm.setTemplateLookup(false);
            }
            
            
        }
     
        return proceedToProcessSyncAward?processSyncAward(mapping,form,request,response):mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    protected StrutsConfirmation buildAwardSyncParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form,
                                                                                 HttpServletRequest request, HttpServletResponse response, String questionId, String... params)
            throws Exception {
        StrutsConfirmation retval = new StrutsConfirmation();
        retval.setMapping(mapping);
        retval.setForm(form);
        retval.setRequest(request);
        retval.setResponse(response);
        retval.setQuestionId(questionId);
        retval.setQuestionType(CONFIRMATION_QUESTION);

        String questionText = COMFIRMATION_PARAM_STRING;
        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);

        return retval;

    }
    
    public ActionForward processSyncAward(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        AwardTemplateSyncService awardTemplateSyncService = KcServiceLocator.getService(AwardTemplateSyncService.class);
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        String question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        AwardTemplateSyncScope[] scopes = awardForm.getCurrentSyncScopes();
        AwardTemplateSyncScope[] scopesList = scopes;   // for maintaining the current scopes list
        ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();

        for (AwardTemplateSyncScope currentScope : scopes) {
            if (((question == null || !((StringUtils.equals(QUESTION_VERIFY_SYNC + ":" + currentScope, question))))
                    && awardForm.getSyncRequiresConfirmationMap().get(currentScope))
                    && !StringUtils.equals(QUESTION_VERIFY_EMPTY_SYNC + ":" + currentScope, question)) {

                String scopeSyncLabel = "";
                StrutsConfirmation confirmationQuestion;
                if (StringUtils.isNotEmpty(currentScope.getDisplayPropertyName())) {
                    scopeSyncLabel = kualiConfiguration.getPropertyValueAsString(currentScope.getDisplayPropertyName());
                }
                if (StringUtils.equals(scopeSyncLabel, REPORTS_PROPERTY_NAME) || StringUtils.equals(scopeSyncLabel, PAYMENT_INVOICES_PROPERTY_NAME)) {
                    confirmationQuestion = buildAwardSyncParameterizedConfirmationQuestion(mapping, form, request, response, (QUESTION_VERIFY_SYNC + ":" + currentScope),
                            scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription(), getScopeMessageToAddQuestion(currentScope));
                } else {
                    confirmationQuestion = buildParameterizedConfirmationQuestion(mapping, form, request, response, (QUESTION_VERIFY_SYNC + ":" + currentScope),
                            currentScope.equals(AwardTemplateSyncScope.FULL) ? KeyConstants.QUESTION_SYNC_FULL : KeyConstants.QUESTION_SYNC_PANEL,
                            scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription(), getScopeMessageToAddQuestion(currentScope));
                }
                confirmationQuestion.setCaller(PROCESS_SYNC_AWARD);
                awardForm.setCurrentSyncQuestionId((QUESTION_VERIFY_SYNC + ":" + currentScope));
                return (performQuestionWithoutInput(confirmationQuestion, ""));

            } else if ((StringUtils.equals(awardForm.getCurrentSyncQuestionId(), question)
                    && ConfirmationQuestion.YES.equals(buttonClicked))
                    || !awardForm.getSyncRequiresConfirmationMap().get(currentScope)) {
                if (LOG.isDebugEnabled())
                    LOG.debug("USER ACCEPTED SYNC OR NO CONFIRM REQUIRED FOR:" + currentScope + " CALLING SYNC SERVICE.");
                boolean templateHasScopedData = awardTemplateSyncService.templateContainsScopedData(awardDocument, currentScope);
                boolean scopeRequiresEmptyConfirm = ArrayUtils.contains(DEFAULT_SCOPES_REQUIRE_VERIFY_FOR_EMPTY, currentScope);

                if (awardDocument.getAward().getSequenceNumber() > 1
                        && !templateHasScopedData
                        && StringUtils.equals(awardForm.getCurrentSyncQuestionId(), (QUESTION_VERIFY_SYNC + ":" + currentScope))
                        && scopeRequiresEmptyConfirm) {
                    //we need to verify since the template has no data.
                    String scopeSyncLabel = "";
                    if (StringUtils.isNotEmpty(currentScope.getDisplayPropertyName()))
                        scopeSyncLabel = kualiConfiguration.getPropertyValueAsString(currentScope.getDisplayPropertyName());

                    StrutsConfirmation confirmationQuestion = buildParameterizedConfirmationQuestion(mapping, form, request, response, (
                                    QUESTION_VERIFY_EMPTY_SYNC + ":" + currentScope), KeyConstants.QUESTION_SYNC_PANEL_TO_EMPTY,
                            scopeSyncLabel, awardDocument.getAward().getAwardTemplate().getDescription());
                    awardForm.setCurrentSyncQuestionId((QUESTION_VERIFY_EMPTY_SYNC + ":" + currentScope));
                    confirmationQuestion.setCaller(PROCESS_SYNC_AWARD);
                    return performQuestionWithoutInput(confirmationQuestion, "");

                }

                AwardTemplateSyncScope[] s = {currentScope};
                awardTemplateSyncService.syncAwardToTemplate(awardDocument, s);
                scopesList = ArrayUtils.remove(scopesList, 0);    // maintaining the current list
                awardForm.setCurrentSyncScopes(scopesList);

            } else if (StringUtils.equals(awardForm.getCurrentSyncQuestionId(), question) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                if (LOG.isDebugEnabled())
                    LOG.debug("USER DECLINED " + currentScope + ", SKIPPING.");
                scopesList = ArrayUtils.remove(scopesList, 0);    // maintaining the current list
                awardForm.setCurrentSyncScopes(scopesList);
            } else {
                throw new RuntimeException("Do not know what to do in this case!");
            }
        }   
        awardForm.setOldTemplateCode(null);
        awardForm.setTemplateLookup(false);
        awardForm.setCurrentSyncScopes(null);
        awardForm.setCurrentSyncQuestionId(null);
        awardForm.buildReportTrackingBeans();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    
    
    private String getScopeMessageToAddQuestion( AwardTemplateSyncScope scope ) {
        ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
        String result = configurationService.getPropertyValueAsString("document.question.syncPanel.add.text."+scope);
        return result==null?"":result;
    }

    protected String getSyncScopesString(HttpServletRequest request) {
        String syncScopesList = null;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName) && parameterName.contains(".scopes")) {
            syncScopesList = StringUtils.substringBetween(parameterName, ".scopes", ".anchor");
        }
        return syncScopesList;
    }

    protected SponsorHierarchyService getSponsorHierarchyService() {
        return KcServiceLocator.getService(SponsorHierarchyService.class);
    }

    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KcServiceLocator.getService(AwardLockService.class);
    }


    protected VersionHistoryService getVersionHistoryService() {
        return KcServiceLocator.getService(VersionHistoryService.class);
    }

    @Override
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if( (StringUtils.isNotBlank(parameterName) && parameterName.contains(".performLookup") && parameterName.contains("templateCode:document.award.templateCode"))) {
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
     */
    public ActionForward syncActionCaller(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        String message = ADD_SYNC_CHANGE_QUESTION;
        if (awardForm.getAwardSyncBean().getPendingChanges().get(0).getSyncType().equals(AwardSyncType.DELETE_SYNC)) {
            message = DEL_SYNC_CHANGE_QUESTION;
        }
        //overwrite the method to call to call this instead of the original method which would result
        //in an error as the action should have already been performed
        request.setAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE, METHOD_TO_CALL_SYNC_ACTION_CALLER);
        ActionForward confirmAction = confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response,
                        CONFIRM_SYNC_ACTION_KEY, message),
                CONFIRM_SYNC_ACTION, REFUSE_SYNC_ACTION);
        if (confirmAction != null) {
            return confirmAction;
        } else {
            return awardForm.getAwardSyncBean().getCurrentForward();
        }
    }
    
    /**
     * If the user answers yes to a confirm sync action question call this method.
     */
    public ActionForward confirmSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardSyncBean().confirmPendingChanges();
        return null;
    }
    
    /**
     * If the user answers no to a confirm sync action question call this method.
     */
    public ActionForward refuseSyncAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
        awardForm.getAwardSyncBean().getPendingChanges().clear();
        return null;
    }
    
    protected AwardSyncCreationService getAwardSyncCreationService() {
        return KcServiceLocator.getService(AwardSyncCreationService.class);
    }
    
    protected AwardSyncService getAwardSyncService() {
        return KcServiceLocator.getService(AwardSyncService.class);
    }

    protected AwardBudgetService getAwardBudgetService() {
        if (awardBudgetService == null) {
            awardBudgetService = KcServiceLocator.getService(AwardBudgetService.class);
        }
        return awardBudgetService;
    } 
    
    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#populateAuthorizationFields(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     * If Award Infos or dates have been edited in a T&amp;M document, then we want to suppress the cancel action.
     */
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        
        AwardForm awardForm = (AwardForm) formBase;
        super.populateAuthorizationFields(formBase);
        AwardDocument awardDocument = awardForm.getAwardDocument();
        Award award = awardDocument.getAward();
        Map documentActions = formBase.getDocumentActions();
        //if Award version has been edited in T&M doc then we suppress cancel action.
        //workaround for copied awards.  On initial copy the Award has two entries in AAI table and originating award version of first entry is null.
        if (award.getAwardAmountInfos().size() > 1) {
                if(!(award.getAwardAmountInfos().size() == 2 && award.getAwardAmountInfos().get(0).getOriginatingAwardVersion() == null)
                                && documentActions.containsKey(KRADConstants.KUALI_ACTION_CAN_CANCEL)) {
                            documentActions.remove(KRADConstants.KUALI_ACTION_CAN_CANCEL);
                }       
        }   
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public ReportTrackingService getReportTrackingService() {
        if (reportTrackingService == null) {
            reportTrackingService = KcServiceLocator.getService(ReportTrackingService.class);
        }
        return reportTrackingService;
    }
    /**
     * This method will populate the subawards  if award is added as a funding source to perticular subaward
     */
    protected void setSubAwardDetails(Award award){
        award.setSubAwardList(getSubAwardService().getLinkedSubAwards(award));
    }

    protected KcNotificationService getNotificationService() {
        if (notificationService == null) {
            notificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    protected SubAwardService getSubAwardService() {
        if (subAwardService == null) {
            subAwardService = KcServiceLocator.getService(SubAwardService.class);
        }
        return subAwardService;
    }

    public void setSubAwardService(SubAwardService subAwardService) {
        this.subAwardService = subAwardService;
    }
    
    public ActionForward superUserActionHelper(SuperUserAction actionName, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AwardForm awardForm = (AwardForm) form;
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        awardForm.setAuditActivated(true);

        ValidationState status = ValidationState.OK;
        if (!awardForm.getDocument().getDocumentHeader().getWorkflowDocument().isEnroute()) {
            status = getAuditHelper().isValidSubmission(awardForm, true);
        }
        if (status == ValidationState.WARNING) {

            if(question == null){
                List<String>  selectedActionRequests = awardForm.getSelectedActionRequests();
                // Need to add the super user requests to user session because they are wiped out by 
                //the KualiRequestProcessor reset on 
                //clicking yes to the question. Retrieve again during actual routing and add to form.
                GlobalVariables.getUserSession().addObject(SUPER_USER_ACTION_REQUESTS, selectedActionRequests);
                try {
                    return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, 
                            "Validation Warning Exists. Are you sure want to submit to workflow routing.", 
                            KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
                }
                catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            } else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {   
                awardForm.setSelectedActionRequests((List<String>)GlobalVariables.getUserSession().retrieveObject(SUPER_USER_ACTION_REQUESTS));
                GlobalVariables.getUserSession().removeObject(SUPER_USER_ACTION_REQUESTS);
                switch (actionName) {
                    case SUPER_USER_APPROVE: 
                        return super.superUserApprove(mapping, awardForm, request, response);
                    case TAKE_SUPER_USER_ACTIONS:
                        return super.takeSuperUserActions(mapping, awardForm, request, response);
                }
            }  else {
                return forward;
            }
        }

        else if(status == ValidationState.OK){
            switch (actionName) {
                case SUPER_USER_APPROVE: 
                    return super.superUserApprove(mapping, awardForm, request, response);
                case TAKE_SUPER_USER_ACTIONS:
                    return super.takeSuperUserActions(mapping, awardForm, request, response);
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages(); 
            GlobalVariables.getMessageMap().putError(DATA_VALIDATION,KeyConstants.ERROR_WORKFLOW_SUBMISSION);
            return forward;
        }
        return forward;
    } 
    
    
    @Override
    public ActionForward superUserApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return superUserActionHelper(SuperUserAction.SUPER_USER_APPROVE, mapping, form, request, response);
    }
    
    @Override
    public ActionForward takeSuperUserActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return superUserActionHelper(SuperUserAction.TAKE_SUPER_USER_ACTIONS, mapping, form, request, response);
    }

    public TimeAndMoneyVersionService getTimeAndMoneyVersionService() {
		if (timeAndMoneyVersionService == null) {
			timeAndMoneyVersionService = KcServiceLocator.getService(TimeAndMoneyVersionService.class);
		}
		return timeAndMoneyVersionService;
	}

	public void setTimeAndMoneyVersionService(TimeAndMoneyVersionService timeAndMoneyVersionService) {
		this.timeAndMoneyVersionService = timeAndMoneyVersionService;
	}
	
	protected void buildAwardHierarchySourceAndTargetList(AwardForm awardForm, Award currentAward) {
		List<String> order = new ArrayList<>();
		if (StringUtils.isBlank(awardForm.getAwardHierarchyTargetAwardNumber())) {
			awardForm.setAwardHierarchyTargetAwardNumber(currentAward.getAwardNumber());
		}
	    Map<String,AwardHierarchyNode> awardHierarchyNodes = new HashMap<>();
	    Map<String,AwardHierarchy> awardHierarchyItems = 
	    		awardForm.getAwardHierarchyBean().getAwardHierarchy(awardForm.getAwardHierarchyTargetAwardNumber(), order);
	    getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
	    StringBuilder sourceAwardStrList = new StringBuilder();
	    StringBuilder targetAwardStrList = new StringBuilder();
	    for(String str:order){
	        sourceAwardStrList.append("'").append(str).append("',");
	        if (awardHierarchyNodes.get(str).isAwardDocumentFinalStatus()) {
	        	targetAwardStrList.append("'").append(str).append("',");
	        }
	    }
	    if (sourceAwardStrList.length() > 1) {
	    	awardForm.setAwardHierarchySourceAwardStrList(removeTrailingCommaIfExists(sourceAwardStrList.toString()));
	    }
	    if (targetAwardStrList.length() > 1) {
	    	awardForm.setAwardHierarchyTargetAwardStrList(removeTrailingCommaIfExists(targetAwardStrList.toString()));
	    }
	}
	
	protected String removeTrailingCommaIfExists(final String listStr) {
		String result = listStr;
		if (result.endsWith(",")) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}


    public ProjectPublisher getProjectPublisher() {
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (projectRetrievalService == null) {
            projectRetrievalService = KcServiceLocator.getService("awardProjectRetrievalService");
        }

        return projectRetrievalService;
    }

    public void setProjectRetrievalService(ProjectRetrievalService projectRetrievalService) {
        this.projectRetrievalService = projectRetrievalService;
    }

}
