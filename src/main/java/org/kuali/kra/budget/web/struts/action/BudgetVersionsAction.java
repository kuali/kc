/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web.struts.action;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.question.CopyPeriodsQuestion;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Struts Action class for requests from the Budget Versions page.
 */
public class BudgetVersionsAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetVersionsAction.class);
    private static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    private static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";

    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews()));
        setBudgetStatuses(budgetForm.getBudgetDocument().getProposal());
        
        return forward;
    }
    
    /* There is a certain amount of shared logic in the below methods. If you find yourself maintaining/refactoring this, consider consolidation. */

    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward addBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        BudgetDocument newBudgetDoc = budgetService.getNewBudgetVersion(pdDoc, budgetForm.getNewBudgetVersionName());
        
        PessimisticLock budgetLockForProposalDoc = null;
        for(PessimisticLock pdLock : pdDoc.getPessimisticLocks()) {
            if(pdLock.getLockDescriptor().contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                budgetLockForProposalDoc = pdLock;
                break;
            }
        }
        PessimisticLock budgetLockForBudgetDoc = KNSServiceLocator.getPessimisticLockService().generateNewLock(
                newBudgetDoc.getDocumentNumber(), budgetLockForProposalDoc.getLockDescriptor(), budgetLockForProposalDoc.getOwnedByUser());
        newBudgetDoc.addPessimisticLock(budgetLockForBudgetDoc);

        pdDoc.addNewBudgetVersion(newBudgetDoc, budgetForm.getNewBudgetVersionName(), false);
        budgetForm.setNewBudgetVersionName("");
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method opens a budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        if (!"TRUE".equals(budgetForm.getEditingMode().get(AuthorizationConstants.EditMode.VIEW_ONLY))) {
            save(mapping, form, request, response);
        }
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        if (KraServiceLocator.getService(BudgetService.class).checkActivityTypeChange(pdDoc,budgetDoc.getBudgetVersionNumber().toString())) {
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);

        } else {
            BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
            DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
            BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            if (!budgetDocument.getActivityTypeCode().equals(budgetDocument.getProposal().getActivityTypeCode())) {
                budgetDocument.setActivityTypeCode(budgetDocument.getProposal().getActivityTypeCode());
            }
            String forward = buildForwardUrl(routeHeaderId);
            return new ActionForward(forward, true);
        }
    }
    
    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, true);
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, false);
    }

    private ActionForward synchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();

        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        budgetDocument.setActivityTypeCode(budgetDocument.getProposal().getActivityTypeCode());
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            forward = forward.replace("budgetSummary.do?", "budgetSummary.do?syncBudgetRate=Y&");
        }
        return new ActionForward(forward, true);
    }


    /**
     * This method copies a budget version's data to a new budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      BudgetForm budgetForm = (BudgetForm) form;
      BudgetVersionOverview versionToCopy = getSelectedVersion(budgetForm, request);
      if (isNotBlank(request.getParameter(QUESTION_INST_ATTRIBUTE_NAME))) {
          Object buttonClicked = request.getParameter(QUESTION_CLICKED_BUTTON);
          if (CopyPeriodsQuestion.ONE.equals(buttonClicked)) {
              return copyBudgetPeriodOne(mapping, form, request, response);
          }
          else if (CopyPeriodsQuestion.ALL.equals(buttonClicked)) {
              return copyBudgetAllPeriods(mapping, form, request, response);
          } else {
              // URL hack, just return
              return mapping.findForward(Constants.MAPPING_BASIC);
          }
      }
      return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, budgetForm.getMethodToCall(), "");

    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        //setFinalBudgetVersion(budgetForm.getFinalBudgetVersion(), budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews());
        // TODO jira 780 - it indicated only from PD screen, not sure we need it here
        // if we don't implement it here, then it's not consistent.
        boolean valid = true;
        try {
            valid &= KraServiceLocator.getService(ProposalDevelopmentService.class).validateBudgetAuditRuleBeforeSaveBudgetVersion(budgetForm.getBudgetDocument().getProposal());
        } catch (Exception ex) {
            LOG.info("Audit rule check failed " + ex.getStackTrace());
        }
        if (!valid) {
            // set up error message to go to validate panel
            GlobalVariables.getErrorMap().putError("document.proposal.budgetVersionOverview["+Integer.toString(budgetForm.getFinalBudgetVersion()-1)+"].budgetStatus", KeyConstants.CLEAR_AUDIT_ERRORS_BEFORE_CHANGE_STATUS_TO_COMPLETE);
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            updateThisBudget(budgetForm.getBudgetDocument());
            setProposalStatus(budgetForm.getBudgetDocument().getProposal());
            return super.save(mapping, form, request, response);
        }

    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetDoc.getProposal().getBudgetVersionOverviews()));
        setBudgetStatuses(budgetDoc.getProposal());
        return forward;
    }
    
    public ActionForward copyBudgetPeriodOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, true);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward copyBudgetAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, false);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private void updateThisBudget(BudgetDocument budgetDocument) {
        for (BudgetVersionOverview version: budgetDocument.getProposal().getBudgetVersionOverviews()) {
            if (budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                budgetDocument.setFinalVersionFlag(version.isFinalVersionFlag());
                budgetDocument.setBudgetStatus(version.getBudgetStatus());
            }
        }
    }
    
    private BudgetVersionOverview getSelectedVersion(BudgetForm budgetForm, HttpServletRequest request) {
        return budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverview(getSelectedLine(request));
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToCopy = getSelectedVersion(budgetForm, request);
        copyBudget(pdDoc, budgetToCopy, copyPeriodOneOnly);
    }
    
    
    private StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }

}
