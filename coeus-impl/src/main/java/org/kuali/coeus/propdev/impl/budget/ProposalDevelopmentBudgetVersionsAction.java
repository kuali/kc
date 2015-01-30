/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.coeus.common.budget.framework.copy.CopyPeriodsQuestion;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Struts Action class for the Proposal Development Budget Versions page
 */
public class ProposalDevelopmentBudgetVersionsAction extends ProposalDevelopmentAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentBudgetVersionsAction.class);

    private static final String TOGGLE_TAB = "toggleTab";
    private static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    private static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";

    /**
     * Main execute method that is run. Populates A map of rate types in the {@link HttpServletRequest} instance to be used
     * in the JSP. The map is called <code>rateClassMap</code> this is set everytime execute is called in this class. This should only
     * happen for the BudgetVersions tab. This ensures that even if {@link org.kuali.coeus.common.budget.framework.rate.RateClass} persisted data may change, it will update the map
     * correctly.
     * 
     * @param mapping {@link ActionMapping}
     * @param form {@link ActionForm} instance
     * @param request {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance 
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("rateClassMap", getBudgetRatesService().getBudgetRateClassMap("O"));
        
        final ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();

        
        return super.execute(mapping, form, request, response);
    }

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
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();

        Budget budgetDocument = 
                getBudgetService().addBudgetVersion(pdDoc, pdForm.getNewBudgetVersionName(), Collections.EMPTY_MAP);
        pdForm.setNewBudgetVersionName("");

        return mapping.findForward(Constants.MAPPING_BASIC); 
    }

    /**
     * This method opens a particular budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        BudgetRatesService budgetService = getBudgetRatesService();

        if (StringUtils.equalsIgnoreCase("TRUE", (String) pdForm.getEditingMode().get("modifyProposalBudget"))) {
            save(mapping, form, request, response);
        }
        
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();
        Budget budgetToOpen = pdDoc.getBudgetDocumentVersion(getSelectedLine(request));
        DocumentService documentService = getDocumentService();
        String routeHeaderId = budgetToOpen.getBudgetId().toString();
        BudgetParentDocument parentDocument = budgetToOpen.getBudgetParent().getDocument();

        this.checkProjectStartEndDateWarning(budgetToOpen);

        Collection<BudgetRate> allPropRates = budgetService.getSavedBudgetRates(budgetToOpen);
        
        if (budgetService.checkActivityTypeChange(allPropRates, pdDoc.getDevelopmentProposal().getActivityTypeCode())) {
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_SYNCH_BUDGET_RATE), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        } 
        else if(CollectionUtils.isEmpty(allPropRates)) {
            //Throw Empty Rates message
            return confirm(syncBudgetRateConfirmationQuestion(mapping, form, request, response,
                    KeyConstants.QUESTION_NO_RATES_ATTEMPT_SYNCH), CONFIRM_SYNCH_BUDGET_RATE, NO_SYNCH_BUDGET_RATE);
        }        

        String forward = buildForwardUrl(routeHeaderId);
        if (pdForm.isAuditActivated()) {
            forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        }
            
        return new ActionForward(forward, true);
    }
    
    /**
     * This method checks if the budget periods exceeds the project start/end dates
     * 
     */
    public void checkProjectStartEndDateWarning(Budget budget) {
        BudgetParentDocument parentDocument = budget.getBudgetParent().getDocument();
        if(parentDocument==null){
          return;
        }
        List<BudgetPeriod> aList = budget.getBudgetPeriods();
        
        if(parentDocument != null){
            Date parentStartDate = parentDocument.getBudgetParent().getRequestedStartDateInitial();
            Date parentEndDate = parentDocument.getBudgetParent().getRequestedEndDateInitial();
            Boolean aFlag = false;
            for(BudgetPeriod aBP : aList){
                if(parentStartDate.after(aBP.getStartDate()) || parentEndDate.before(aBP.getEndDate())){
                    aFlag = true;
                    break;
                }
            }
            if(aFlag){
                ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
                errorReporter.reportSoftError("projectDatesChanged", KeyConstants.PROJECT_START_END_DATE_CHANGED);
            }
        }
    }
    
    public ActionForward confirmSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, true);
    }

    public ActionForward noSynchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchBudgetRate(mapping, form, request, response, false);
    }

    private ActionForward synchBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();
        Budget budget = pdDoc.getDevelopmentProposal().getBudgets().get(getSelectedLine(request));
        String routeHeaderId = budget.getBudgetId().toString();
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            budget.setActivityTypeCode(pdDoc.getDevelopmentProposal().getActivityTypeCode());
            forward = forward.replace("budgetParameters.do?", "budgetParameters.do?syncBudgetRate=Y&");
        }
        if (pdForm.isAuditActivated()) {
            forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
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
     * @return
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        Budget versionToCopy = getSelectedVersion(pdForm, request);
        if (StringUtils.isNotBlank(request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME))) {
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
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
        
        pdForm.setSaveAfterCopy(true);
        return performQuestionWithoutInput(mapping, form, request, response, COPY_BUDGET_PERIOD_QUESTION, QUESTION_TEXT + versionToCopy.getBudgetVersionNumber() + ".", QUESTION_TYPE, pdForm.getMethodToCall(), "");
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();
        // check audit rules. If there is error, then budget can't have complete status
        boolean valid = true;

        if (!(new ProposalHierarcyActionHelper()).checkParentChildStatusMatch(pdDoc)) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        if (pdForm.isSaveAfterCopy()) {
            final List<ProposalDevelopmentBudgetExt> overviews = pdDoc.getDevelopmentProposal().getBudgets();
            final ProposalDevelopmentBudgetExt budget = overviews.get(overviews.size() - 1);
            final String copiedName = budget.getName();
            budget.setName("copied placeholder");
            LOG.debug("validating " + copiedName);
            valid = getBudgetService().isBudgetVersionNameValid(pdDoc.getBudgetParent(), copiedName);
            budget.setName(copiedName);
            pdForm.setSaveAfterCopy(!valid);
        }

        final ActionForward forward = super.save(mapping, form, request, response);

        // Need to facilitate releasing the Budget locks if user is redirected to Actions page
        if (forward != null && forward.getName().equalsIgnoreCase("actions")) {
            pdForm.setMethodToCall("actions");
            /**
             * If we are in audit mode, when we get redirected back to the actions tab, there was error in that the error
             * messages weren't being displayed correctly.  Clearing the AuditMessageMap "fixes" this problem. KRACOEUS-4746.
             */
            if (pdForm.isAuditActivated()) {
                GlobalVariables.getAuditErrorMap().clear();
            }
        }

        return forward;
    }   
    
    public ActionForward copyBudgetPeriodOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, true);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward copyBudgetAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (COPY_BUDGET_PERIOD_QUESTION.equals(question)) {
            copyBudget(form, request, false);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private Budget getSelectedVersion(ProposalDevelopmentForm proposalDevelopmentForm, HttpServletRequest request) {
        return proposalDevelopmentForm.getProposalDevelopmentDocument().getBudgetDocumentVersion(getSelectedLine(request));
    }
    
    private void copyBudget(ActionForm form, HttpServletRequest request, boolean copyPeriodOneOnly) throws WorkflowException {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Budget budgetToCopy = getSelectedVersion(proposalDevelopmentForm, request);
        copyBudget(pdDoc.getBudgetParent(), budgetToCopy, copyPeriodOneOnly);
    }
    
    private StrutsConfirmation syncBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_BUDGET_RATE,
                message, "");
    }
}
