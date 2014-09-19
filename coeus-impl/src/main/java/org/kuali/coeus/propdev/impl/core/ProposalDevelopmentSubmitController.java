package org.kuali.coeus.propdev.impl.core;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.rule.event.SendNotificationEvent;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.DocumentDetail;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSubmitController extends
		ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    @Autowired
    @Qualifier("kcNotificationService")
    private KcNotificationService kcNotificationService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;

    @Autowired
    @Qualifier("workflowDocumentActionsService")
    protected WorkflowDocumentActionsService workflowDocumentActionsService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=deleteProposal")
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
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitForReview")
    public  ModelAndView submitForReview(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception {
        
 	   if(proposalValidToRoute(form)) {
 		  return getTransactionalDocumentControllerService().route(form);
	   }
	   else {
		   return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
	   }
   } 
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=cancelProposal")
    public ModelAndView cancelProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
	   form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.CANCELED);
	   return getTransactionalDocumentControllerService().cancel(form);
    }
    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-SubmitPage"}) 
    public ModelAndView navigateToSubmit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).prepareSummaryPage(form);
        return super.navigate(form,result,request,response);
   }
  
    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=blanketApprove")
   public  ModelAndView blanketApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
       
	   if(proposalValidToRoute(form)) {
		   return getTransactionalDocumentControllerService().blanketApprove(form);
	   }
	   else {
		   return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
	   }
   	}
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=recall")
   public  ModelAndView recall(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception {
	   String successMessageKey = null;
	   Document document = form.getDocument();
	   if (getDocumentService().documentExists(document.getDocumentNumber())) {
           String recallExplanation = form.getDialogExplanations().get(KRADConstants.QUESTION_ACTION_RECALL_REASON);
           document = getDocumentService().recallDocument(document, recallExplanation, false);
           successMessageKey = RiceKeyConstants.MESSAGE_ROUTE_RECALLED;
       }
       if (successMessageKey != null) {
           getGlobalVariableService().getMessageMap().putInfo(KRADConstants.GLOBAL_MESSAGES, successMessageKey);
       }
	   
	   return getModelAndViewService().getModelAndView(form);
  } 
  
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=disapprove")
   public  ModelAndView disapprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)throws Exception {
	   String applicationUrl = getConfigurationService().getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
	   form.setReturnLocation(applicationUrl);
	   return   getTransactionalDocumentControllerService().disapprove(form);
   }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNotificationWizard")
    public ModelAndView prepareNotificationWizard(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        String step = "";
        if (form.getNotificationHelper().getNotificationRecipients().isEmpty()) {
            step = "0";
        } else {
            step = "2";
        }
        form.getActionParameters().put("Kc-SendNotification-Wizard.step",step);
        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addRecipients")
    public ModelAndView addRecipients(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        form.getNotificationHelper().getNotificationRecipients().addAll(getKcNotificationService().addRecipient(form.getAddRecipientHelper().getResults()));
        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performRecipientSearch")
    public ModelAndView performRecipientSearch(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.getAddRecipientHelper().getResults().clear();
        form.getAddRecipientHelper().setResults(getWizardControllerService().performWizardSearch(form.getAddRecipientHelper().getLookupFieldValues(), form.getAddRecipientHelper().getLineType()));
        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=sendNotifications")
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
        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=approve")
    public ModelAndView approve(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        form.setAuditActivated(true);

        if (!proposalValidToRoute(form)) {
            getGlobalVariableService().getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION);
            return getModelAndViewService().getModelAndView(form);
        }

        WorkflowDocument workflowDoc = form.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument();
        if (canGenerateRequestsInFuture(workflowDoc, getGlobalVariableService().getUserSession().getPrincipalId())) {
            DialogResponse dialogResponse = form.getDialogResponse("PropDev-SubmitPage-ReceiveFutureRequests");
            if(dialogResponse == null) {
                return getModelAndViewService().showDialog("PropDev-SubmitPage-ReceiveFutureRequests", true, form);
            }else if (dialogResponse.getResponseAsBoolean()){
                form.getWorkflowDocument().setReceiveFutureRequests();
            } else {
                form.getWorkflowDocument().setDoNotReceiveFutureRequests();
            }
        }

        getTransactionalDocumentControllerService().performWorkflowAction(form, UifConstants.WorkflowAction.APPROVE);
        return getModelAndViewService().getModelAndView(form);
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

        return (receiveFutureRequests == false && doNotReceiveFutureRequests == false);
    }

    private boolean canGenerateMultipleApprovalRequests(RoutingReportCriteria reportCriteria, String loggedInPrincipalId, String currentRouteNodeNames ) throws Exception {
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

    private boolean recipientMatchesUser(ActionRequest actionRequest, String loggedInPrincipalId) {
        if(actionRequest != null && loggedInPrincipalId != null ) {
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

  public GlobalVariableService getGlobalVariableService() {
      return globalVariableService;
  }

  public void setGlobalVariableService(GlobalVariableService globalVariableService) {
      this.globalVariableService = globalVariableService;
  }
  
  public ConfigurationService getConfigurationService() {
	  return configurationService;
  }
	
  public void setConfigurationService(ConfigurationService configurationService) {
	  this.configurationService = configurationService;
  }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
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
}