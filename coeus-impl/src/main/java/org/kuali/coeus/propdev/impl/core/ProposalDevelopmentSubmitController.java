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
package org.kuali.coeus.propdev.impl.core;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRulesEngine;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentActionBean;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentRejectionRule;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.propdev.impl.state.ProposalStateService;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewLinkEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actionrequest.service.ActionRequestService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.*;
import org.kuali.rice.kew.api.document.DocumentDetail;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Controller
public class ProposalDevelopmentSubmitController extends
		ProposalDevelopmentControllerBase {

    public static final String REJECT_ACTION_TYPE_CODE = "500";
    public static final String REJECT_NOTIFICATION = "Reject Notification";

    public static final String ANOTHER_USER_APPROVED_ACTION_TYPE_CODE = "501";
    public static final String ANOTHER_USER_APPROVED_NOTIFICATION = "Another User Approved Notification";
    public static final String PROPOSAL_NUMBER = "proposalNumber";
    public static final String PROPOSAL_STATE = "proposalState";
    private static final String ENABLE_PD_WORKFLOW_APPROVAL_COMMENTS = "ENABLE_PD_WORKFLOW_APPROVAL_COMMENTS";
    private static final String PROPOSAL_APPROVAL_ATTACHMENT = "Proposal approval attachment.";

    private final Logger LOGGER = Logger.getLogger(ProposalDevelopmentSubmitController.class);

    private static final String AUTO_SUBMIT_TO_SPONSOR_ON_FINAL_APPROVAL = "autoSubmitToSponsorOnFinalApproval";
	private static final String SUBMIT_TO_SPONSOR = "submitToSponsor";

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;
    
    @Autowired
    @Qualifier("institutionalProposalService")
    private InstitutionalProposalService institutionalProposalService;

    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;

    @Autowired
    @Qualifier("workflowDocumentActionsService")
    protected WorkflowDocumentActionsService workflowDocumentActionsService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;
    
    @Autowired
    @Qualifier("proposalDevelopmentNotificationRenderer")
    private ProposalDevelopmentNotificationRenderer renderer;
    
    @Autowired
    @Qualifier("kcBusinessRulesEngine")
    private KcBusinessRulesEngine kcBusinessRulesEngine;
    
    @Autowired
    @Qualifier("proposalStateService")
    private ProposalStateService proposalStateService;   
    
    @Autowired
    @Qualifier("specialReviewService")
    private SpecialReviewService specialReviewService; 

    @Autowired
    @Qualifier("proposalHierarchyService")
    private ProposalHierarchyService proposalHierarchyService;

    @Autowired
    @Qualifier("kcWorkflowService")
    private KcWorkflowService kcWorkflowService;

    @Autowired
    @Qualifier("actionRequestService")
    private ActionRequestService actionRequestService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=populateAdHocs")
    public ModelAndView populateAdHocs(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        populateAdHocRecipients(form.getProposalDevelopmentDocument());
        return getModelAndViewService().showDialog("PropDev-DocumentAdHocRecipientsSection", true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=saveAdHocChanges")
    public ModelAndView saveAdHocChanges(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=deleteProposal")
    public ModelAndView deleteProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {

        if (form.getProposalDevelopmentDocument().getDevelopmentProposal().isInHierarchy()) {
        	getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETE_PROPOSAL_IN_HIERARCHY);
            return getModelAndViewService().getModelAndView(form);
        }
        else {
        	getProposalDevelopmentService().deleteProposal(form.getProposalDevelopmentDocument());
            return getNavigationControllerService().returnToHub(form);
        }
    }
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitForReview")
    public  ModelAndView submitForReview(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
       populateAdHocRecipients(form.getProposalDevelopmentDocument());
       AuditHelper.ValidationState severityLevel = getValidationState(form);
 	   if(severityLevel.equals(AuditHelper.ValidationState.ERROR)) {
           return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
	   } else if (severityLevel.equals(AuditHelper.ValidationState.WARNING)) {
           return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_SECTION_WITH_SUBMIT, true, form);
	   } else {
           return internalSubmit(form);
       }
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=internalSubmit")
    public  ModelAndView internalSubmit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
        WorkflowDocument workflowDoc = form.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument();
        if (canGenerateRequestsInFuture(workflowDoc, getGlobalVariableService().getUserSession().getPrincipalId())) {
            DialogResponse dialogResponse = form.getDialogResponse("PropDev-SubmitPage-ReceiveFutureRequests");
            if(dialogResponse == null) {
                return getModelAndViewService().showDialog("PropDev-SubmitPage-ReceiveFutureRequests", false, form);
            }else if (dialogResponse.getResponseAsBoolean()){
                form.getWorkflowDocument().setReceiveFutureRequests();
            } else {
                form.getWorkflowDocument().setDoNotReceiveFutureRequests();
            }
        }
        form.setCanEditView(null);
        form.setEvaluateFlagsAndModes(true);
        if (ProposalState.REVISIONS_REQUESTED.equals(form.getDevelopmentProposal().getProposalStateTypeCode())) {
        	if (workflowDoc.isApprovalRequested()) {
        		workflowDoc.approve("Revisions Requested Re-Submit");
        	} else {
        		form.getProposalDevelopmentDocument().getActionRequests().stream()
        			.filter(actionRequest -> ProposalDevelopmentConstants.KewConstants.AGGREGATORS_REQUEST_FOR_REVIEW_ANNOTATION.equals(actionRequest.getAnnotation()))
        			.map(actionRequest -> getActionRequestService().findByActionRequestId(actionRequest.getId()))
        			.forEach(actionRequest -> getActionRequestService().deactivateRequest(null, actionRequest));
        		getTransactionalDocumentControllerService().route(form);
        	}
    	} else {
    		getTransactionalDocumentControllerService().route(form);
    	}
        getPessimisticLockService().releaseWorkflowPessimisticLocking(form.getProposalDevelopmentDocument());
        updateProposalAdminDetailsForSubmit(form.getDevelopmentProposal());
        return updateProposalState(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=cancelProposal")
    public ModelAndView cancelProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        form.setCanEditView(null);
        form.setEvaluateFlagsAndModes(true);
        form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.CANCELED);
       return getTransactionalDocumentControllerService().cancel(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-SubmitPage"})
    public ModelAndView navigateToSubmit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).prepareSummaryPage(form);
        return super.navigate(form, result, request, response);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=blanketApprove")
    public  ModelAndView blanketApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
        if (!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)){
            form.setCanEditView(null);
            form.setEvaluateFlagsAndModes(true);
            updateProposalAdminDetailsForBlanketApprove(form.getDevelopmentProposal());
            return getTransactionalDocumentControllerService().blanketApprove(form);
        }
        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
    }
   
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=recall")
   public  ModelAndView recall(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
	   String successMessageKey = null;
	   Document document = form.getDocument();
	   if (getDocumentService().documentExists(document.getDocumentNumber())) {
           String recallExplanation = form.getDialogExplanations().get(KRADConstants.QUESTION_ACTION_RECALL_REASON);
           getDocumentService().recallDocument(document, recallExplanation, false);
           successMessageKey = RiceKeyConstants.MESSAGE_ROUTE_RECALLED;
           final ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
           proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.REVISIONS_REQUESTED);
           DevelopmentProposal developmentProposal = getDataObjectService().save(proposalDevelopmentDocument.getDevelopmentProposal());
           developmentProposal.refreshReferenceObject(PROPOSAL_STATE);
       }
       if (successMessageKey != null) {
           getGlobalVariableService().getMessageMap().putInfo(KRADConstants.GLOBAL_MESSAGES, successMessageKey);
       }

       return getTransactionalDocumentControllerService().reload(form);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=disapproveProposal")
   public  ModelAndView disapproveProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
	   String applicationUrl = getConfigurationService().getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
	   form.setReturnLocation(applicationUrl);
       form.setCanEditView(null);
       form.setEvaluateFlagsAndModes(true);
       return getTransactionalDocumentControllerService().disapprove(form);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNotificationWizard")
    public ModelAndView prepareNotificationWizard(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        final String step = form.getNotificationHelper().getNotificationRecipients().isEmpty() ? "0" : "2";
        form.getActionParameters().put("Kc-SendNotification-Wizard.step", step);
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addRecipients")
    public ModelAndView addRecipients(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        form.getNotificationHelper().getNotificationRecipients().addAll(getKcNotificationService().addRecipient(form.getAddRecipientHelper().getResults()));
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performRecipientSearch")
    public ModelAndView performRecipientSearch(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        form.getAddRecipientHelper().getResults().clear();
        form.getAddRecipientHelper().setResults(getWizardControllerService().performWizardSearch(form.getAddRecipientHelper().getLookupFieldValues(), form.getAddRecipientHelper().getLineType()));
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=sendNotifications")
    public ModelAndView sendNotifications(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        KcNotification notification = form.getNotificationHelper().getNotification();
        List<NotificationTypeRecipient> notificationRecipients = form.getNotificationHelper().getNotificationRecipients();

        if (getKualiRuleService().applyRules(new SendNotificationEvent(document, notification, notificationRecipients))) {
            form.getNotificationHelper().sendNotification();
        }

        ProposalDevelopmentNotificationRenderer renderer = new ProposalDevelopmentNotificationRenderer(form.getDevelopmentProposal());
        ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(form.getDevelopmentProposal(), null, "Ad-Hoc Notification", renderer);

        form.getNotificationHelper().initializeDefaultValues(context);
        form.getAddRecipientHelper().reset();
        populateDeferredMessages(form);
        return getRefreshControllerService().refresh(form);
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelNotifications")
    public ModelAndView cancelNotifications(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        populateDeferredMessages(form);
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitToS2s")
    public  ModelAndView submitToS2s(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
        form.setGrantsGovSubmitFlag(true);
	    form.setShowSubmissionDetails(true);
        form.setDirtyForm(false);

        if (!requiresResubmissionPrompt(form)) {
    		if (validToSubmitToSponsor(form) ) {
    			//Generate IP in case auto generate IP and no IP hasn't been generated yet (in other words no submit to sponsor button clicked)
    			if(autogenerateInstitutionalProposal() && ! hasInstitutionalProposal(form.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalNumber())) {
    				submitApplication(form);
    			}
                handleSubmissionToS2S(form);
                return getModelAndViewService().getModelAndView(form,"PropDev-OpportunityPage");
            } else {
    			return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
    		}
        } else {
        	return getModelAndViewService().showDialog("PropDev-Resumbit-OptionsSection", true, form);
        }
    }

    protected void handleSubmissionToS2S(ProposalDevelopmentDocumentForm form) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
        try {
            submitS2sApplication(proposalDevelopmentDocument);
        } catch(S2SException ex) {
            LOGGER.error("Error submitting to s2s", ex);
            getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_ON_GRANTS_GOV_SUBMISSION,ex.getErrorMessage());
        } catch (S2sCommunicationException ex) {
        	LOGGER.error("Error submitting to s2s", ex);
        	getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(), ex.getMessageWithParams());
        }
    }

    protected boolean hasInstitutionalProposal(String proposalNumber) {
        return getProposalDevelopmentService().getInstitutionalProposal(proposalNumber) != null;
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitToSponsor")
    public  ModelAndView submitToSponsor(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {

    	if (!requiresResubmissionPrompt(form)) {
    		if(validToSubmitToSponsor(form) ) {
    			submitApplication(form);
                handleSubmissionNotification(form);
                form.setDeferredMessages(getGlobalVariableService().getMessageMap());
                return sendSubmitToSponsorNotification(form);
    		} else {
                form.setDataValidationItems(((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateDataValidation());
                return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
    		}
    	} else {
            return getModelAndViewService().showDialog("PropDev-Resumbit-OptionsSection", true, form);
    	}
    }

    protected ModelAndView sendSubmitToSponsorNotification(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentDocumentForm.getProposalDevelopmentDocument();
        ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(proposalDevelopmentDocument.getDevelopmentProposal(), "101", "Proposal Submitted");
        ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(proposalDevelopmentDocumentForm.getDevelopmentProposal());
        if (proposalDevelopmentDocumentForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            proposalDevelopmentDocumentForm.getNotificationHelper().initializeDefaultValues(context);
            return getModelAndViewService().showDialog("Kc-SendNotification-Wizard", true, proposalDevelopmentDocumentForm);
        } else {
            getKcNotificationService().sendNotification(context);
        }
        return getModelAndViewService().getModelAndView(proposalDevelopmentDocumentForm);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteLineNotificationRecipient")
    public ModelAndView deleteLine(@ModelAttribute("KualiForm") DocumentFormBase form, @RequestParam("actionParameters[" + UifParameters.SELECTED_COLLECTION_PATH + "]") String selectedCollectionPath) {
        getCollectionControllerService().deleteLine(form);

        final Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(form, selectedCollectionPath);
        form.getActionParameters().put("Kc-SendNotification-Wizard.step", collection.isEmpty() ? ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_STEP_0 : ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_STEP_2);
        return getModelAndViewService().showDialog("Kc-SendNotification-Wizard", true, form);
    }

    protected void handleSubmissionNotification(ProposalDevelopmentDocumentForm form) {
        getRenderer().setDevelopmentProposal(form.getDevelopmentProposal());
        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
        ProposalDevelopmentNotificationContext notificationContext = new ProposalDevelopmentNotificationContext(
                proposalDevelopmentDocument.getDevelopmentProposal(),
                ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_S2S_SUBMIT_ACTION_CODE,
                ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_S2S_SUBMIT_CONTEXT_NAME,
                getRenderer());
        form.getNotificationHelper().setNotificationContext(notificationContext);
        form.getNotificationHelper().initializeDefaultValues(notificationContext);
        final String step = form.getNotificationHelper().getNotificationRecipients().isEmpty() ? ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_STEP_0 :
                ProposalDevelopmentConstants.NotificationConstants.NOTIFICATION_STEP_2;
        form.getActionParameters().put("Kc-SendNotification-Wizard.step", step);
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=proceed")
    public  ModelAndView proceed(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
       return form.isGrantsGovSubmitFlag() ? submitToS2s(form) : submitToSponsor(form);
    }

    protected boolean validToSubmitToSponsor(ProposalDevelopmentDocumentForm form) {
    	boolean isValid = !getValidationState(form).equals(AuditHelper.ValidationState.ERROR);
    	isValid &= getKcBusinessRulesEngine().applyRules(new SubmitToSponsorEvent(form.getProposalDevelopmentDocument()));
    	return isValid;
    }
    
    public void submitApplication(ProposalDevelopmentDocumentForm proposalDevelopmentForm)throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        boolean isIPProtocolLinkingEnabled = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IRB, ParameterConstants.DOCUMENT_COMPONENT, Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK);
        
        List<ProposalSpecialReview> specialReviews = proposalDevelopmentDocument.getDevelopmentProposal().getPropSpecialReviews();
        
        if (!isIPProtocolLinkingEnabled || getKcBusinessRulesEngine().applyRules(new SaveSpecialReviewLinkEvent<>(proposalDevelopmentDocument, specialReviews))) {

            final boolean generateIp = !(autogenerateInstitutionalProposal() && ProposalDevelopmentConstants.ResubmissionOptions.DO_NOT_GENERATE_NEW_IP.equals(proposalDevelopmentForm.getResubmissionOption()));

        	if (generateIp) {
                proposalDevelopmentDocument.getDevelopmentProposal().setSubmitFlag(true);
            }
            setProposalStateType(generateIp, proposalDevelopmentDocument);

            String pCode = proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode();
            proposalDevelopmentForm.setCanEditView(null);
            proposalDevelopmentForm.setEvaluateFlagsAndModes(true);
            getTransactionalDocumentControllerService().save(proposalDevelopmentForm);
            if( !StringUtils.equals(pCode, proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode() )) {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(pCode);
                proposalDevelopmentDocument.getDevelopmentProposal().refresh();
                getDataObjectService().save(proposalDevelopmentDocument.getDevelopmentProposal());
            }

            updateProposalAdminDetailsForSubmitToSponsor(proposalDevelopmentDocument.getDevelopmentProposal());
    
            if (autogenerateInstitutionalProposal()) {
                generateInstitutionalProposal(proposalDevelopmentForm, isIPProtocolLinkingEnabled);
            }
        }
        
    }
    protected void setProposalStateType(boolean generateIp, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        if (generateIp) {
            if (ProposalState.APPROVED.equals(proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode())) {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVED_AND_SUBMITTED);
            } else {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(proposalDevelopmentDocument, false));
            }
        } else {
            if (proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVED);
            } else {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVAL_PENDING);
            }
        }
    }
    
    private void generateInstitutionalProposal(ProposalDevelopmentDocumentForm proposalDevelopmentForm, boolean isIPProtocolLinkingEnabled) {

        if (ProposalDevelopmentConstants.ResubmissionOptions.DO_NOT_GENERATE_NEW_IP.equals(proposalDevelopmentForm.getResubmissionOption())) {
            doNotGenerateIp(proposalDevelopmentForm);
        } else if (ProposalDevelopmentConstants.ResubmissionOptions.GENERATE_NEW_VERSION_OF_ORIGINAL_IP.equals(proposalDevelopmentForm.getResubmissionOption())) {
            generateNewVersionOfOrigIp(proposalDevelopmentForm, isIPProtocolLinkingEnabled);
        } else if (ProposalDevelopmentConstants.ResubmissionOptions.GENERATE_NEW_VERSION_OF_IP.equals(proposalDevelopmentForm.getResubmissionOption())) {
            generateNewVersionIp(proposalDevelopmentForm, isIPProtocolLinkingEnabled);
        } else if (null == proposalDevelopmentForm.getResubmissionOption() || ProposalDevelopmentConstants.ResubmissionOptions.GENERATE_NEW_IP.equals(proposalDevelopmentForm.getResubmissionOption())) {
            generateNewIp(proposalDevelopmentForm, isIPProtocolLinkingEnabled);
        } else {
            LOGGER.warn("Invalid resubmission option " + proposalDevelopmentForm.getResubmissionOption());
        }
    }

    protected void doNotGenerateIp(ProposalDevelopmentDocumentForm proposalDevelopmentForm) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();

        if (proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
            getGlobalVariableService().getMessageMap().putInfo(Constants.NO_FIELD, KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED);
        } else {
            getGlobalVariableService().getMessageMap().putInfo(Constants.NO_FIELD, KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED_INROUTE);
        }
    }

    protected void generateNewVersionOfOrigIp(ProposalDevelopmentDocumentForm proposalDevelopmentForm, boolean isIPProtocolLinkingEnabled) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        proposalDevelopmentForm.setInstitutionalProposalToVersion(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom());
        generateNewVersionIp(proposalDevelopmentForm, isIPProtocolLinkingEnabled);
    }

    protected void generateNewVersionIp(ProposalDevelopmentDocumentForm proposalDevelopmentForm, boolean isIPProtocolLinkingEnabled) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String versionNumber = createInstitutionalProposalVersion(
                proposalDevelopmentForm.getInstitutionalProposalToVersion(),
                proposalDevelopmentDocument.getDevelopmentProposal(),
                proposalDevelopmentDocument.getDevelopmentProposal().getFinalBudget());

        getGlobalVariableService().getMessageMap().putInfo(Constants.NO_FIELD,KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED, versionNumber, proposalDevelopmentForm.getInstitutionalProposalToVersion());

        Long institutionalProposalId = getActiveProposalId(proposalDevelopmentForm.getInstitutionalProposalToVersion());
        updateProposalAdminDetailsAfterInstPropCreation(proposalDevelopmentDocument.getDevelopmentProposal(), institutionalProposalId);
        persistSpecialReviewProtocolFundingSourceLink(institutionalProposalId, isIPProtocolLinkingEnabled);
    }

    protected void generateNewIp(ProposalDevelopmentDocumentForm proposalDevelopmentForm, boolean isIPProtocolLinkingEnabled) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String proposalNumber = createInstitutionalProposal(
                proposalDevelopmentDocument.getDevelopmentProposal(), proposalDevelopmentDocument.getDevelopmentProposal().getFinalBudget());
        getGlobalVariableService().getMessageMap().putInfo(Constants.NO_FIELD,KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED, proposalNumber);

        Long institutionalProposalId = getActiveProposalId(proposalNumber);
        updateProposalAdminDetailsAfterInstPropCreation(proposalDevelopmentDocument.getDevelopmentProposal(), institutionalProposalId);
        persistSpecialReviewProtocolFundingSourceLink(institutionalProposalId, isIPProtocolLinkingEnabled);
    }
    
    protected void persistSpecialReviewProtocolFundingSourceLink(Long institutionalProposalId, boolean isIPProtocolLinkingEnabled) {
        if (isIPProtocolLinkingEnabled) {
        	InstitutionalProposal institutionalProposal = getLegacyDataAdapter().findBySinglePrimaryKey(InstitutionalProposal.class, institutionalProposalId);
            institutionalProposal.getSpecialReviews().stream()
                    .filter(specialReview -> SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()))
                    .forEach(specialReview -> {
                String protocolNumber = specialReview.getProtocolNumber();
                String fundingSourceNumber = institutionalProposal.getProposalNumber();
                String fundingSourceTypeCode = FundingSourceType.INSTITUTIONAL_PROPOSAL;

                if (!getSpecialReviewService().isLinkedToProtocolFundingSource(protocolNumber, fundingSourceNumber, fundingSourceTypeCode)) {
                    String fundingSourceName = institutionalProposal.getSponsorName();
                    String fundingSourceTitle = institutionalProposal.getTitle();
                    getSpecialReviewService().addProtocolFundingSourceForSpecialReview(
                            protocolNumber, fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
                }
            });
        }
    }
    
    protected boolean requiresResubmissionPrompt(ProposalDevelopmentDocumentForm proposalDevelopmentForm) {
        ProposalDevelopmentViewHelperService proposalDevelopmentViewHelperService = (ProposalDevelopmentViewHelperService)proposalDevelopmentForm.getViewHelperService();
        if(proposalDevelopmentViewHelperService.isResubmissionPromptDialogEnabled()){
            return proposalDevelopmentViewHelperService.requiresResubmissionPrompt(proposalDevelopmentForm.getDevelopmentProposal(),
                    proposalDevelopmentForm.getResubmissionOption());
        }else {
            proposalDevelopmentForm.setResubmissionOption(getProposalDevelopmentService().getIPGenerateOption(proposalDevelopmentForm.getDevelopmentProposal()));
            return false;
        }
    }

    protected boolean autogenerateInstitutionalProposal() {
    	return getProposalDevelopmentService().autogenerateInstitutionalProposal();
    }
    
    
    private String createInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget) {
        final InstitutionalProposal institutionalProposal = getInstitutionalProposalService().createInstitutionalProposalVersion(proposalNumber, developmentProposal, budget);
        return institutionalProposal.getSequenceNumber().toString();
    }

    protected String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget) {
        final InstitutionalProposal institutionalProposal = getInstitutionalProposalService().createInstitutionalProposal(developmentProposal, budget);
        final String proposalNumber = institutionalProposal.getProposalNumber();
        final Long institutionalProposalId = getActiveProposalId(proposalNumber);
        updateProposalAdminDetailsAfterInstPropCreation(developmentProposal, institutionalProposalId);
        return proposalNumber;
    }
    
    private Long getActiveProposalId(String proposalNumber) {
        Collection<InstitutionalProposal> ips = getLegacyDataAdapter().findMatching(InstitutionalProposal.class, Collections.singletonMap(PROPOSAL_NUMBER, proposalNumber));
        return ((InstitutionalProposal) ips.toArray()[0]).getProposalId();
    }
    
    protected ProposalAdminDetails getProposalAdminDetailsForProposal(DevelopmentProposal proposal) {
    	ProposalAdminDetails adminDetails = 
    			getLegacyDataAdapter().findMatching(ProposalAdminDetails.class, Collections.singletonMap("devProposalNumber", proposal.getProposalNumber()))
    			.stream().findFirst().orElse(null);
    	if (adminDetails == null) {
    		adminDetails = new ProposalAdminDetails();
    		adminDetails.setDevelopmentProposal(proposal);
    		adminDetails.setDevProposalNumber(proposal.getProposalNumber());
    	}
    	return adminDetails;
    }
    
    protected void updateProposalAdminDetailsForSubmit(DevelopmentProposal proposal) {
    	ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
    	proposalAdminDetails.setDateSubmittedByDept(new Timestamp(System.currentTimeMillis()));
    	save(proposalAdminDetails);
    }
    
    protected void updateProposalAdminDetailsForReject(DevelopmentProposal proposal) {
    	ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
    	proposalAdminDetails.setDateReturnedToDept(new Timestamp(System.currentTimeMillis()));
    	save(proposalAdminDetails);
    }
    
    protected void updateProposalAdminDetailsForFinalApproval(DevelopmentProposal proposal) {
    	ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
    	proposalAdminDetails.setDateApprovedByOsp(new Timestamp(System.currentTimeMillis()));
    	save(proposalAdminDetails);
    }
    
    protected void updateProposalAdminDetailsForSubmitToSponsor(DevelopmentProposal proposal) {
    	ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
    	proposalAdminDetails.setDateSubmittedToAgency(new Timestamp(System.currentTimeMillis()));
    	save(proposalAdminDetails);
    }
    
    protected void updateProposalAdminDetailsAfterInstPropCreation(DevelopmentProposal proposal, Long instProposalId) {
        ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
        addCreateInstPropDetails(proposalAdminDetails);
        proposalAdminDetails.setInstProposalId(instProposalId);
        String loggedInUser = getGlobalVariableService().getUserSession().getPrincipalName();        
        proposalAdminDetails.setSignedBy(loggedInUser);
        save(proposalAdminDetails);
    }
    
    protected void updateProposalAdminDetailsForBlanketApprove(DevelopmentProposal proposal) {
        ProposalAdminDetails proposalAdminDetails = getProposalAdminDetailsForProposal(proposal);
        if (proposalAdminDetails.getDateSubmittedByDept() == null) {
        	proposalAdminDetails.setDateSubmittedByDept(new Timestamp(System.currentTimeMillis()));
        }
        if (proposalAdminDetails.getDateApprovedByOsp() == null) {
        	proposalAdminDetails.setDateApprovedByOsp(new Timestamp(System.currentTimeMillis()));
        }
        save(proposalAdminDetails);
    }

	protected void save(ProposalAdminDetails proposalAdminDetails) {
		getLegacyDataAdapter().save(proposalAdminDetails);
	}

	protected void addCreateInstPropDetails(ProposalAdminDetails proposalAdminDetails) {
		proposalAdminDetails.setInstPropCreateDate(new Timestamp(System.currentTimeMillis()));
        proposalAdminDetails.setInstPropCreateUser(getGlobalVariableService().getUserSession().getPrincipalName());
	}

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=approveCheck")
    public ModelAndView approveCheck(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        final Boolean featureFlag = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,
                "proposal.approval.dialog.enabled");

        if(!featureFlag) {
            return approve(form);
        }

        AuditHelper.ValidationState severityLevel = getValidationState(form);

        if(severityLevel.equals(AuditHelper.ValidationState.ERROR)) {
            return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_DIALOG_ID, true, form);
        } else if (severityLevel.equals(AuditHelper.ValidationState.WARNING)) {
            return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.DATA_VALIDATION_SECTION_WITH_APPROVE, true, form);
        } else {
            return approve(form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=approve")
    public ModelAndView approve(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{

        WorkflowDocument workflowDoc = form.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument();
        if (canGenerateRequestsInFuture(workflowDoc, getGlobalVariableService().getUserSession().getPrincipalId())) {
            DialogResponse frDialogResponse = form.getDialogResponse("PropDev-SubmitPage-ReceiveFutureRequests");
            if(frDialogResponse == null) {
                return getModelAndViewService().showDialog("PropDev-SubmitPage-ReceiveFutureRequests", false, form);
            } else if (frDialogResponse.getResponseAsBoolean()){
                form.getWorkflowDocument().setReceiveFutureRequests();
            } else {
                form.getWorkflowDocument().setDoNotReceiveFutureRequests();
            }
        }

        form.setAuditActivated(true);

        if (getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            getGlobalVariableService().getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION);
            return getModelAndViewService().getModelAndView(form);
        }

        List<NotificationTypeRecipient> recipients = getRelatedApproversFromActionRequest(form.getProposalDevelopmentDocument().getDocumentNumber(), getGlobalVariableService().getUserSession().getPrincipalId()).stream()
    			.map(this::createRecipientFromPerson).collect(toList());

        final boolean approvalComments = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, ENABLE_PD_WORKFLOW_APPROVAL_COMMENTS);
        if (approvalComments) {
            final DialogResponse approveDialogResponse = form.getDialogResponse("PropDev-SubmitPage-ApproveDialog");
            if (approveDialogResponse == null) {
                return getModelAndViewService().showDialog("PropDev-SubmitPage-ApproveDialog", false, form);
            } else if (!approveDialogResponse.getResponseAsBoolean()) {
                return getModelAndViewService().getModelAndView(form);
            } else if (approveDialogResponse.getResponseAsBoolean()) {
                form.setAnnotation(StringUtils.defaultString(form.getProposalDevelopmentApprovalBean().getActionReason()));
            }
        }

        getTransactionalDocumentControllerService().performWorkflowAction(form, UifConstants.WorkflowAction.APPROVE);

        if (approvalComments) {
            final String narrativeTypeCode = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.APPROVE_NARRATIVE_TYPE_CODE_PARAM);
            final DevelopmentProposal pbo = getProposalHierarchyService().getDevelopmentProposal(form.getDevelopmentProposal().getProposalNumber());
            final ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(pbo.getProposalDocument().getDocumentNumber());
            getProposalHierarchyService().createAndSaveActionNarrative(form.getProposalDevelopmentApprovalBean().getActionReason(), PROPOSAL_APPROVAL_ATTACHMENT, form.getProposalDevelopmentApprovalBean().getActionFile(), narrativeTypeCode, pDoc);
        }

        if (recipients.size() != 0) {
            sendAnotherUserApprovedNotification(form, recipients);
        }
        getPessimisticLockService().releaseWorkflowPessimisticLocking(form.getProposalDevelopmentDocument());
		if (getKcWorkflowService().isFinalApproval(workflowDoc)) {
			updateProposalAdminDetailsForFinalApproval(form.getDevelopmentProposal());
			if (form.getActionFlags().containsKey(SUBMIT_TO_SPONSOR)
					&& getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, AUTO_SUBMIT_TO_SPONSOR_ON_FINAL_APPROVAL)) {
				return submitToSponsor(form);
			}
		}
        form.setCanEditView(null);
        form.setEvaluateFlagsAndModes(true);
        return updateProposalState(form);
    }

    protected void sendAnotherUserApprovedNotification(ProposalDevelopmentDocumentForm form, List<NotificationTypeRecipient> recipients) {
        prepareNotification(form.getDevelopmentProposal());
        ProposalDevelopmentNotificationContext notificationContext = new ProposalDevelopmentNotificationContext(form.getDevelopmentProposal(),
                ANOTHER_USER_APPROVED_ACTION_TYPE_CODE, ANOTHER_USER_APPROVED_NOTIFICATION, getRenderer());
        form.getNotificationHelper().initializeDefaultValues(notificationContext);
        if (getKcNotificationService().getNotificationType(notificationContext) != null) {
            getKcNotificationService().sendNotification(notificationContext, form.getNotificationHelper().getNotification(), recipients);
        }
    }

    private boolean canGenerateRequestsInFuture(WorkflowDocument workflowDoc, String principalId) throws Exception {
        RoutingReportCriteria.Builder reportCriteriaBuilder = RoutingReportCriteria.Builder.createByDocumentId(workflowDoc.getDocumentId());
        reportCriteriaBuilder.setTargetPrincipalIds(Collections.singletonList(principalId));

        String currentRouteNodeNames = getKradWorkflowDocumentService().getCurrentRouteNodeNames(workflowDoc);

        return (hasAskedToNotReceiveFutureRequests(workflowDoc, principalId) && canGenerateMultipleApprovalRequests(reportCriteriaBuilder.build(), principalId, currentRouteNodeNames ));
    }

    private boolean hasAskedToNotReceiveFutureRequests(WorkflowDocument workflowDoc, String principalId) {
        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;

        Map<String, String> variables = workflowDoc.getVariables();

           for (Map.Entry<String,String> entry : variables.entrySet()) {
                String variableKey = entry.getKey();
                String variableValue = entry.getValue();
                if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && variableValue.toUpperCase().equals(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && variableKey.contains(principalId)) {
                    receiveFutureRequests = true;
                    break;
                }
                else if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && variableValue.toUpperCase().equals(KewApiConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && variableKey.contains(principalId)) {
                    doNotReceiveFutureRequests = true;
                    break;
                }
           }

        return (!receiveFutureRequests && !doNotReceiveFutureRequests);
    }

    protected boolean canGenerateMultipleApprovalRequests(RoutingReportCriteria reportCriteria, String loggedInPrincipalId, String currentRouteNodeNames ) throws Exception {
        int approvalRequestsCount = 0;

        DocumentDetail results1 = getWorkflowDocumentActionsService().executeSimulation(reportCriteria);
        for(ActionRequest actionRequest : results1.getActionRequests() ){
            if(actionRequest.isPending() && actionRequest.getActionRequested().getCode().equalsIgnoreCase(KewApiConstants.ACTION_REQUEST_APPROVE_REQ) &&
                    recipientMatchesUser(actionRequest, loggedInPrincipalId) && !StringUtils.contains( currentRouteNodeNames,actionRequest.getNodeName()) ) {
                approvalRequestsCount+=1;
            }
        }

        return (approvalRequestsCount > 0);
    }

    protected boolean recipientMatchesUser(ActionRequest actionRequest, String loggedInPrincipalId) {
        if (actionRequest != null && loggedInPrincipalId != null ) {
            List<ActionRequest> actionRequests =  Collections.singletonList(actionRequest);
            if(actionRequest.isRoleRequest()) {
                actionRequests = actionRequest.getChildRequests();
            }
            for( ActionRequest cActionRequest : actionRequests ) {
                String recipientUser = cActionRequest.getPrincipalId();
                if( ( recipientUser != null && recipientUser.equals(loggedInPrincipalId) )
                        || (StringUtils.isNotBlank(cActionRequest.getGroupId())
                        && getGroupService().isMemberOfGroup(loggedInPrincipalId, cActionRequest.getGroupId() ))) {
                    return true;
                }
            }
        }

        return false;
    }

    protected ModelAndView updateProposalState(ProposalDevelopmentDocumentForm form) throws Exception{
        if (getKcWorkflowService().isFinalApproval(form.getWorkflowDocument())) {
            form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVAL_GRANTED);
            getGlobalVariableService().getMessageMap().getInfoMessages().clear();
            getGlobalVariableService().getMessageMap().putInfoForSectionId(ProposalDevelopmentConstants.KradConstants.SUBMIT_PAGE, KeyConstants.APPROVAL_CYCLE_COMPLETE);
        } else {
            form.getDevelopmentProposal().setProposalStateTypeCode(getProposalStateService().getProposalStateTypeCode(form.getProposalDevelopmentDocument(), false));
        }
        getDataObjectService().wrap(form.getDevelopmentProposal()).fetchRelationship("proposalState");
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=reject")
    public ModelAndView reject(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        ProposalDevelopmentActionBean bean = form.getProposalDevelopmentRejectionBean();
        if (new ProposalDevelopmentRejectionRule().proccessProposalDevelopmentRejection(bean)){
            List<NotificationTypeRecipient> recipients = 
            		getAllCurrentApprovers(form.getProposalDevelopmentDocument().getDocumentNumber(), getGlobalVariableService().getUserSession().getPrincipalId()).stream()
            			.map(this::createRecipientFromPerson).collect(toList());
            getProposalHierarchyService().rejectProposalDevelopmentDocument(form.getDevelopmentProposal().getProposalNumber(), bean.getActionReason(),
                    getGlobalVariableService().getUserSession().getPrincipalId(),bean.getActionFile());
            updateProposalAdminDetailsForReject(form.getDevelopmentProposal());
            if (recipients.size() != 0) {
                sendRejectNotification(form, recipients);
            }
        }

        form.setCanEditView(null);
        form.setEvaluateFlagsAndModes(true);
        return getTransactionalDocumentControllerService().reload(form);
    }

    protected void sendRejectNotification(ProposalDevelopmentDocumentForm form, List<NotificationTypeRecipient> recipients) {
        prepareNotification(form.getDevelopmentProposal());
        ProposalDevelopmentNotificationContext notificationContext = new ProposalDevelopmentNotificationContext(
                                                                        form.getDevelopmentProposal(),
                                                                        REJECT_ACTION_TYPE_CODE, REJECT_NOTIFICATION, getRenderer());
        form.getNotificationHelper().initializeDefaultValues(notificationContext);
        if (getKcNotificationService().getNotificationType(notificationContext) != null) {
            getKcNotificationService().sendNotification(notificationContext, form.getNotificationHelper().getNotification(), recipients);
        }
    }

    protected void prepareNotification(DevelopmentProposal developmentProposal) {
        getRenderer().setDevelopmentProposal(developmentProposal);
        getRenderer().setProposalPerson(developmentProposal.getPrincipalInvestigator());
    }

    protected NotificationTypeRecipient createRecipientFromPerson(String personId) {
        NotificationTypeRecipient recipient = new NotificationTypeRecipient();
        recipient.setPersonId(personId);
        return recipient;
    }

    protected HashSet<String> getRelatedApproversFromActionRequest(String documentNumber, String loggedInUser) {
        final List<ActionRequestValue> allActionRequestsByDocumentId = getAllActionRequestsByDocumentId(documentNumber);
        return allActionRequestsByDocumentId.stream()
        		.filter(actionRequestValue -> loggedInUser.equals(actionRequestValue.getPrincipalId()))
        		.flatMap(this::getRelatedActionRequests)
        		.flatMap(this::getActionAndChildren)
        		.filter(ActionRequestValue::isActive)
        		.map(ActionRequestValue::getPrincipalId)
        		.filter(principalId -> principalId != null && !principalId.equals(loggedInUser))
        		.collect(toCollection(HashSet::new));
    }
    
    protected HashSet<String> getAllCurrentApprovers(String documentNumber, String loggedInUser) {
    	final List<ActionRequestValue> allActionRequestsByDocumentId = getAllActionRequestsByDocumentId(documentNumber);
    	return allActionRequestsByDocumentId.stream()
    		.filter(ActionRequestValue::isActive)
    		.map(ActionRequestValue::getPrincipalId)
    		.filter(principalId -> principalId != null && !principalId.equals(loggedInUser))
    		.collect(toCollection(HashSet::new));
    }
    
    protected Stream<ActionRequestValue> getActionAndChildren(ActionRequestValue actionRequestValue) {
    	List<ActionRequestValue> actions = new ArrayList<>();
    	actions.addAll(actionRequestValue.getChildrenRequests());
    	actions.add(actionRequestValue);
    	return actions.stream();
    }
    
    protected Stream<ActionRequestValue> getRelatedActionRequests(ActionRequestValue actionRequestValue) {
		List<ActionRequestValue> relatedActionRequests = new ArrayList<>();
		if (ActionRequestPolicy.FIRST.getCode().equalsIgnoreCase(actionRequestValue.getApprovePolicy()) 
				&& actionRequestValue.getParentActionRequest() != null) {
			relatedActionRequests.addAll(getRelatedActionRequests(actionRequestValue.getParentActionRequest()).collect(Collectors.toList()));
			relatedActionRequests.addAll(actionRequestValue.getParentActionRequest().getChildrenRequests());
		} else {
			relatedActionRequests.add(actionRequestValue);
		}
		return relatedActionRequests.stream();
	}
    
    public List<ActionRequestValue> getAllActionRequestsByDocumentId(String documentNumber) {
        return actionRequestService.findAllActionRequestsByDocumentId(documentNumber);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelReject")
    public ModelAndView cancelReject(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        form.setProposalDevelopmentRejectionBean(new ProposalDevelopmentActionBean());
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelApprove")
    public ModelAndView cancelApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        form.setProposalDevelopmentApprovalBean(new ProposalDevelopmentActionBean());
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=sendAdHocRequests")
    public ModelAndView sendAdHocRequests(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        form.setCanEditView(null);
        form.setEvaluateFlagsAndModes(true);
        return getTransactionalDocumentControllerService().sendAdHocRequests(form);
    }

	private void submitS2sApplication(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception{
	    getS2sSubmissionService().submitApplication(proposalDevelopmentDocument);
	}

	public S2sSubmissionService getS2sSubmissionService() {
		return s2sSubmissionService;
	}

	public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
		this.s2sSubmissionService = s2sSubmissionService;
	}

	public InstitutionalProposalService getInstitutionalProposalService() {
		return institutionalProposalService;
	}
	
	public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
		this.institutionalProposalService = institutionalProposalService;
	}
  
  public ConfigurationService getConfigurationService() {
	  return configurationService;
  }

    public void setConfigurationService(ConfigurationService configurationService) {
      this.configurationService = configurationService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public WizardControllerService getWizardControllerService() {
        return wizardControllerService;
    }

    public void setWizardControllerService(WizardControllerService wizardControllerService) {
        this.wizardControllerService = wizardControllerService;
    }

    public WorkflowDocumentService getKradWorkflowDocumentService() {
        return kradWorkflowDocumentService;
    }

    public void setKradWorkflowDocumentService(WorkflowDocumentService kradWorkflowDocumentService) {
        this.kradWorkflowDocumentService = kradWorkflowDocumentService;
    }

    public WorkflowDocumentActionsService getWorkflowDocumentActionsService() {
        return workflowDocumentActionsService;
    }

    public void setWorkflowDocumentActionsService(WorkflowDocumentActionsService workflowDocumentActionsService) {
        this.workflowDocumentActionsService = workflowDocumentActionsService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public ProposalHierarchyService getProposalHierarchyService() {
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    }

	public ProposalDevelopmentNotificationRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(ProposalDevelopmentNotificationRenderer renderer) {
		this.renderer = renderer;
	}

    public KcBusinessRulesEngine getKcBusinessRulesEngine() {
		return kcBusinessRulesEngine;
	}

    public void setKcBusinessRulesEngine(KcBusinessRulesEngine kcBusinessRulesEngine) {
		this.kcBusinessRulesEngine = kcBusinessRulesEngine;
	}

	public ProposalStateService getProposalStateService() {
		return proposalStateService;
	}

    public void setProposalStateService(ProposalStateService proposalStateService) {
		this.proposalStateService = proposalStateService;
	}
	public SpecialReviewService getSpecialReviewService() {
        return specialReviewService;
	}

    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
		this.specialReviewService = specialReviewService;
	}

    public KcWorkflowService getKcWorkflowService() {
        return kcWorkflowService;
    }

    public void setKcWorkflowService(KcWorkflowService kcWorkflowService) {
        this.kcWorkflowService = kcWorkflowService;
    }

    public ActionRequestService getActionRequestService() {
        return actionRequestService;
    }

    public void setActionRequestService(ActionRequestService actionRequestService) {
        this.actionRequestService = actionRequestService;
    }
}
