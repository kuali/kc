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

import static org.kuali.RiceConstants.QUESTION_INST_ATTRIBUTE_NAME;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

public class BudgetParametersAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetParametersAction.class);
    private static final String CONFIRM_RECALCULATE_BUDGET_KEY = "calculateAllPeriods";
    private static final String CONFIRM_SAVE_BUDGET_KEY = "saveAfterQuestion";
    private static final String CONFIRM_HEADER_TAB_KEY = "headerTabAfterQuestion";
    private static final String DO_NOTHING = "doNothing";
    private static final String CONFIRM_SAVE_SUMMARY = "confirmSaveSummary";
    private static final String CONFIRM_DELETE_BUDGET_PERIOD = "confirmDeleteBudgetPeriod";
    private static final String CONFIRM_DEFAULT_BUDGET_PERIODS = "confirmDefaultBudgetPeriods";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        updateTotalCost(((BudgetForm) form).getDocument());
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        budgetDocument.getBudgetSummaryService().setupOldStartEndDate(
                budgetForm.getDocument(), false);
        if (StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            ((BudgetForm) form).getDocument().getBudgetRatesService().syncAllBudgetRates(((BudgetForm) form).getDocument());
            budgetForm.setSyncBudgetRate("");
            // jira-1848 : force to calc budget after sync
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetForm.getDocument());
        }
        return forward;
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        
        boolean rulePassed = getKualiRuleService().applyRules(
            new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument()));
        if (!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
            || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            if (isBudgetPeriodDateChanged(budgetForm.getDocument()) && isLineItemErrorOnly()) {
                GlobalVariables.setErrorMap(new ErrorMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                                                                          KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY_FOR_RATE_AND_DATE_CHANGE), CONFIRM_SAVE_SUMMARY, DO_NOTHING);
            } else {
                return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                               CONFIRM_SAVE_BUDGET_KEY, DO_NOTHING);
            }
        } else {
            updateThisBudgetVersion(budgetForm.getDocument());
            if (budgetForm.isUpdateFinalVersion()) {
                reconcileFinalBudgetFlags(budgetForm);
                setBudgetStatuses(budgetForm.getDocument().getProposal());
            }
            budgetDocument = budgetForm.getDocument();
            if (isBudgetPeriodDateChanged(budgetForm.getDocument()) && isLineItemErrorOnly()) {
                GlobalVariables.setErrorMap(new ErrorMap());
                return confirm(buildSaveBudgetSummaryConfirmationQuestion(mapping, form, request, response,
                                                                          KeyConstants.QUESTION_SAVE_BUDGET_SUMMARY), CONFIRM_SAVE_SUMMARY, "");
            }
            if (rulePassed) {
                // update campus flag if budget level flag is changed
                if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                    KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                                                                                                   budgetDocument.getOnOffCampusFlag());
                }
                if (budgetDocument.getFinalVersionFlag()) {
                    budgetDocument.getProposal().setBudgetStatus(budgetDocument.getBudgetStatus());
                }

                updateBudgetPeriodDbVersion(budgetDocument);
                return super.save(mapping, form, request, response);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward saveAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        
        updateThisBudgetVersion(budgetForm.getDocument());
        if (budgetForm.isUpdateFinalVersion()) {
            reconcileFinalBudgetFlags(budgetForm);
            setBudgetStatuses(budgetForm.getDocument().getProposal());
        }
        boolean rulePassed = getKualiRuleService().applyRules(
            new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument()));
        BudgetDocument budgetDocument = budgetForm.getDocument();
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                                                                                               budgetDocument.getOnOffCampusFlag());
            }
            if (budgetDocument.getFinalVersionFlag()) {
                budgetDocument.getProposal().setBudgetStatus(budgetDocument.getBudgetStatus());
            }
            updateBudgetPeriodDbVersion(budgetDocument);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward confirmSaveSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        updateThisBudgetVersion(budgetForm.getDocument());
        if (budgetForm.isUpdateFinalVersion()) {
            reconcileFinalBudgetFlags(budgetForm);
            setBudgetStatuses(budgetForm.getDocument().getProposal());
        }
        budgetForm.getDocument().getBudgetSummaryService().adjustStartEndDatesForLineItems(budgetForm.getDocument());
        boolean rulePassed = getKualiRuleService().applyRules(
                new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument()));
        BudgetDocument budgetDocument = budgetForm.getDocument();
        if (rulePassed) {
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                        budgetDocument.getOnOffCampusFlag());
            }
            if (budgetDocument.getFinalVersionFlag()) {
                budgetDocument.getProposal().setBudgetStatus(budgetDocument.getBudgetStatus());
            }
            updateBudgetPeriodDbVersion(budgetDocument);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to add a new Budget Period
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        // List<BudgetPeriod> budgetPeriods = budgetForm.getDocument().getBudgetPeriods();
        BudgetDocument budgetDocument = budgetForm.getDocument();
        if (getKualiRuleService().applyRules(
                new AddBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument(), newBudgetPeriod))) {
            budgetForm.getDocument().getBudgetSummaryService().addBudgetPeriod(budgetDocument, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(new BudgetPeriod());
            // TODO : per conversation with Geo. comment it out for now.
            // calculate should get called only when you press calculate or while saving
            // budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing Budget Period
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        int delPeriod = getLineToDelete(request);
        int viewPeriod = 0;
        if (budgetForm.getViewBudgetPeriod() != null) {
            viewPeriod = budgetForm.getViewBudgetPeriod();
        }
        List <BudgetLineItem> budgetLineItems = budgetDocument.getBudgetPeriods().get(delPeriod).getBudgetLineItems();
        if (viewPeriod > 0 && ((delPeriod+1) == viewPeriod || budgetDocument.getBudgetPeriods().size() == viewPeriod)) {
            budgetForm.setViewBudgetPeriod(Integer.valueOf(1));
        }
        if (budgetLineItems != null && budgetLineItems.size() > 0) {
            return confirm(buildDeleteBudgetPeriodConfirmationQuestion(mapping, form, request, response,
                     delPeriod+1), CONFIRM_DELETE_BUDGET_PERIOD, "");
        } else {
//            if (getKualiRuleService().applyRules(
//                    new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument(), delPeriod))) {
                budgetForm.getDocument().getBudgetSummaryService().deleteBudgetPeriod(budgetDocument, delPeriod);
                /* calculate all periods */
                budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
//            }
            return mapping.findForward(Constants.MAPPING_BASIC);            
        }
    }
    
    public ActionForward confirmDeleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        int delPeriod = getLineToDelete(request);
//        if (getKualiRuleService().applyRules(
//                new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument(), delPeriod))) {
            budgetForm.getDocument().getBudgetSummaryService().deleteBudgetPeriod(budgetDocument, delPeriod);
            /* calculate all periods */
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
//        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to recalculate Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to generate all Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward generateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(
                new GenerateBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getDocument()));
        BudgetDocument budgetDocument = budgetForm.getDocument();
        if (rulePassed) {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                        budgetDocument.getOnOffCampusFlag());
            }
            /* calculate first period - only period 1 exists at this point */
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
            /* generate all periods */
            budgetForm.getDocument().getBudgetSummaryService().generateAllPeriods(budgetDocument);

            /* calculate all periods */
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
            // reset the old start/end date if it is changed for some reason
            ((BudgetForm) form).getDocument().getBudgetSummaryService().setupOldStartEndDate(
                    ((BudgetForm) form).getDocument(), true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward questionCalculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();

        if (!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response),
                    CONFIRM_RECALCULATE_BUDGET_KEY, DO_NOTHING);
        }
        else {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                        budgetDocument.getOnOffCampusFlag());
            }
            /* calculate all periods */
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to calculate all Budget Period data
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RECALCULATE_BUDGET_KEY.equals(question)) {
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag())
                    || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument,
                        budgetDocument.getOnOffCampusFlag());
            }
            /* calculate all periods */
            budgetForm.getDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward doNothing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        budgetDocument.setOhRateClassCode(budgetForm.getOhRateClassCodePrevValue());
        budgetDocument.setUrRateClassCode(budgetForm.getUrRateClassCodePrevValue());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method builds a Opportunity Delete Confirmation Question as part of the Questions Framework
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildRecalculateBudgetConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_RECALCULATE_BUDGET_KEY,
                QUESTION_RECALCULATE_BUDGET_CONFIRMATION);
    }

    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    private void reconcileFinalBudgetFlags(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getDocument();
        if (budgetDocument.getFinalVersionFlag()) {
            // This version has been marked as final - update other versions.
            for (BudgetVersionOverview version : budgetDocument.getProposal().getBudgetVersionOverviews()) {
                if (!budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                    version.setFinalVersionFlag(false);
                }
            }
        }
        else {
            budgetForm.setFinalBudgetVersion(null);
        }
    }

    /**
     * 
     * This method to set the DB version# for budget periods. To eliminate optimistic locking problem. newly adjusted period has no
     * version number set, but its period may exist in DB.
     * 
     * @param budgetDocument
     */
    private void updateBudgetPeriodDbVersion(BudgetDocument budgetDocument) {
        // set version number for saving
        Map<String, String> budgetPeriodMap = new HashMap<String, String>();
        budgetPeriodMap.put("proposalNumber", budgetDocument.getProposalNumber());
        budgetPeriodMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber().toString());
        Collection<BudgetPeriod> existBudgetPeriods = KraServiceLocator.getService(BusinessObjectService.class).findMatching(
                BudgetPeriod.class, budgetPeriodMap);
        for (BudgetPeriod budgetPeriod : existBudgetPeriods) {
            for (BudgetPeriod newBudgetPeriod : budgetDocument.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriodId().equals(newBudgetPeriod.getBudgetPeriodId())) {
                    newBudgetPeriod.setVersionNumber(budgetPeriod.getVersionNumber());
                }
            }
        }

    }

    /**
     * 
     * This method period's total cost based on input from direct/indirect total cost.
     * @param budgetDocument
     */
    private void updateTotalCost(BudgetDocument budgetDocument) {
        BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalCost = BudgetDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getTotalDirectCost().isGreaterThan(BudgetDecimal.ZERO)
                    || budgetPeriod.getTotalIndirectCost().isGreaterThan(BudgetDecimal.ZERO)) {
                budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
            }
            totalDirectCost = totalDirectCost.add(budgetPeriod.getTotalDirectCost());
            totalIndirectCost = totalIndirectCost.add(budgetPeriod.getTotalIndirectCost());
            totalCost = totalCost.add(budgetPeriod.getTotalCost());
        }
        budgetDocument.setTotalDirectCost(totalDirectCost);
        budgetDocument.setTotalIndirectCost(totalIndirectCost);
        budgetDocument.setTotalCost(totalCost);

    }

    private void updateThisBudgetVersion(BudgetDocument budgetDocument) {
        for (BudgetVersionOverview version : budgetDocument.getProposal().getBudgetVersionOverviews()) {
            if (budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                version.setFinalVersionFlag(budgetDocument.getFinalVersionFlag());
                version.setBudgetStatus(budgetDocument.getBudgetStatus());
            }
        }
    }

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();

        if (!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(), budgetForm.getOhRateClassCodePrevValue())
                || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(), budgetForm.getUrRateClassCodePrevValue())) {
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), CONFIRM_HEADER_TAB_KEY,
                    DO_NOTHING);
        }

        return super.headerTab(mapping, form, request, response);
    }

    public ActionForward headerTabAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return super.headerTab(mapping, form, request, response);
    }

    /**
     * 
     * This method to check if the period duration has been changed.
     * @param budgetDocument
     * @return
     */
    private boolean isBudgetPeriodDateChanged(BudgetDocument budgetDocument) {
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getStartDate() != null && budgetPeriod.getOldStartDate() != null && 
                    budgetPeriod.getEndDate() != null && budgetPeriod.getOldEndDate() != null && 
                    (budgetPeriod.getStartDate().compareTo(budgetPeriod.getOldStartDate()) != 0
                    || budgetPeriod.getEndDate().compareTo(budgetPeriod.getOldEndDate()) != 0)) {
                return true;
            }

        }
        return false;
    }

    /**
     * 
     * This method is to build the confirmation mesasage if period duration changed and trying to save.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param message
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildSaveBudgetSummaryConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SAVE_SUMMARY,
                message, "");
    }
    
    /**
     * 
     * This method is to build the confirmation on deleting period which has line items.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteBudgetPeriodConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deletePeriod) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_HEADER_TAB_KEY,
                KeyConstants.QUESTION_DELETE_BUDGET_PERIOD, Integer.toString(deletePeriod));
    }

    /**
     * 
     * This method is to check the error map to see if there is any error other than line item date error.
     * line item date date error should be resolved with adjustlineitem start/end date.
     * This is called after rule verification and before save.
     * @return
     */
    private boolean isLineItemErrorOnly() {
        if (!GlobalVariables.getErrorMap().isEmpty()) {

            for (Iterator i = GlobalVariables.getErrorMap().entrySet().iterator(); i.hasNext();) {
                Map.Entry e = (Map.Entry) i.next();

                TypedArrayList errorList = (TypedArrayList) e.getValue();
                for (Iterator j = errorList.iterator(); j.hasNext();) {
                    ErrorMessage em = (ErrorMessage) j.next();
                    if (!em.getErrorKey().equals("error.lineItem.dateDoesNotmatch")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 
     * This method generate default budget periods (each one with 1 year span).
     * This is generally needed when project period is adjusted.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward defaultPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        String warningMessage = budgetForm.getDocument().getBudgetSummaryService().defaultWarningMessage(budgetDocument);
        
        if (StringUtils.isNotBlank(warningMessage)) {
            return confirm(buildDefaultBudgetPeriodsConfirmationQuestion(mapping, form, request, response,
                    warningMessage), CONFIRM_DEFAULT_BUDGET_PERIODS, "");
        } else {
                budgetForm.getDocument().getBudgetSummaryService().defaultBudgetPeriods(budgetDocument);
                return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
    }

    private StrutsConfirmation buildDefaultBudgetPeriodsConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_HEADER_TAB_KEY,
                KeyConstants.QUESTION_DEFAULT_BUDGET_PERIODS, message);
    }
   
    public ActionForward confirmDefaultBudgetPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        budgetForm.getDocument().getBudgetSummaryService().defaultBudgetPeriods(budgetDocument);
        budgetForm.getDocument().getBudgetSummaryService().adjustStartEndDatesForLineItems(budgetForm.getDocument());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
}
