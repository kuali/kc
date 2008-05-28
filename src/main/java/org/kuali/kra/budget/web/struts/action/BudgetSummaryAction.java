/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.web.struts.action;

import static org.kuali.RiceConstants.QUESTION_INST_ATTRIBUTE_NAME;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_RECALCULATE_BUDGET_CONFIRMATION;

import java.util.Collection;
import java.util.HashMap;
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
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.DeleteBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

public class BudgetSummaryAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetSummaryAction.class);
    private static final String CONFIRM_RECALCULATE_BUDGET_KEY = "calculateAllPeriods";
    private static final String CONFIRM_SAVE_BUDGET_KEY = "saveAfterQuestion";
    private static final String CONFIRM_HEADER_TAB_KEY = "headerTabAfterQuestion";
    private static final String DO_NOTHING = "doNothing";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        updateTotalCost(((BudgetForm) form).getBudgetDocument());
        return forward;
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        if(!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(),budgetForm.getOhRateClassCodePrevValue()) || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(),budgetForm.getUrRateClassCodePrevValue())){
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), CONFIRM_SAVE_BUDGET_KEY, DO_NOTHING);            
        }else{
            if (budgetForm.isUpdateFinalVersion()) {
                reconcileFinalBudgetFlags(budgetForm);
                setBudgetStatuses(budgetForm.getBudgetDocument().getProposal());
            }
            boolean rulePassed = getKualiRuleService().applyRules(new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
            budgetDocument = budgetForm.getBudgetDocument();
            if(rulePassed){
                // update campus flag if budget level flag is changed
                if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag()) || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                    KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument, budgetDocument.getOnOffCampusFlag());
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
    
    public ActionForward saveAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        updateThisBudgetVersion(budgetForm.getBudgetDocument());
        if (budgetForm.isUpdateFinalVersion()) {
            reconcileFinalBudgetFlags(budgetForm);
            setBudgetStatuses(budgetForm.getBudgetDocument().getProposal());
        }
        boolean rulePassed = getKualiRuleService().applyRules(new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(rulePassed){
            // update campus flag if budget level flag is changed
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag()) || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument, budgetDocument.getOnOffCampusFlag());
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
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        //List<BudgetPeriod> budgetPeriods =  budgetForm.getBudgetDocument().getBudgetPeriods();
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(getKualiRuleService().applyRules(new AddBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), newBudgetPeriod))){
            budgetForm.getBudgetDocument().getBudgetSummaryService().addBudgetPeriod(budgetDocument, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(new BudgetPeriod());
           // TODO : per conversation with Geo.  comment it out for now.
            //calculate should get called only when you press calculate or while saving
           // budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing Budget Period
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int delPeriod = getLineToDelete(request);
        if(getKualiRuleService().applyRules(new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), delPeriod))){
            budgetForm.getBudgetDocument().getBudgetSummaryService().deleteBudgetPeriod(budgetDocument, delPeriod);
            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to generate all Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward generateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(new GenerateBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(rulePassed){
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag()) || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument, budgetDocument.getOnOffCampusFlag());
            }
            /* calculate first period - only period 1 exists at this point*/
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
            /* generate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().generateAllPeriods(budgetDocument);

            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward questionCalculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        if(!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(),budgetForm.getOhRateClassCodePrevValue()) || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(),budgetForm.getUrRateClassCodePrevValue())){
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), CONFIRM_RECALCULATE_BUDGET_KEY, DO_NOTHING);            
        }else{
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag()) || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument, budgetDocument.getOnOffCampusFlag());
            }
            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method is used to calculate all Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();        
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RECALCULATE_BUDGET_KEY.equals(question)) {        
            if (StringUtils.isBlank(budgetForm.getPrevOnOffCampusFlag()) || !budgetDocument.getOnOffCampusFlag().equals(budgetForm.getPrevOnOffCampusFlag())) {
                KraServiceLocator.getService(BudgetSummaryService.class).updateOnOffCampusFlag(budgetDocument, budgetDocument.getOnOffCampusFlag());
            }
            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward doNothing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetDocument.setOhRateClassCode(budgetForm.getOhRateClassCodePrevValue());
        budgetDocument.setUrRateClassCode(budgetForm.getUrRateClassCodePrevValue());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * 
     * This method builds a Opportunity Delete Confirmation Question as part of the Questions Framework
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildRecalculateBudgetConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {                
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_RECALCULATE_BUDGET_KEY, QUESTION_RECALCULATE_BUDGET_CONFIRMATION);
    }
    
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    private void reconcileFinalBudgetFlags(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if (budgetDocument.getFinalVersionFlag()) {
            // This version has been marked as final - update other versions.
            for (BudgetVersionOverview version: budgetDocument.getProposal().getBudgetVersionOverviews()) {
                if (!budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                    version.setFinalVersionFlag(false);
                }
            }
        } else {
            budgetForm.setFinalBudgetVersion(null);
        }
    }

    /**
     * 
     * This method to set the DB version# for budget periods.  To eliminate optimistic locking problem.
     * newly adjusted period has no version number set, but its period may exist in DB.
     * @param budgetDocument
     */
    private void updateBudgetPeriodDbVersion(BudgetDocument budgetDocument) {
        // set version number for saving
        Map<String, String> budgetPeriodMap = new HashMap<String, String>();
        budgetPeriodMap.put("proposalNumber", budgetDocument.getProposalNumber());
        budgetPeriodMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber().toString());
        Collection <BudgetPeriod> existBudgetPeriods = KraServiceLocator.getService(BusinessObjectService.class).findMatching(BudgetPeriod.class, budgetPeriodMap);
        for(BudgetPeriod budgetPeriod : existBudgetPeriods) {
            for (BudgetPeriod newBudgetPeriod : budgetDocument.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriod().equals(newBudgetPeriod.getBudgetPeriod())) {
                    newBudgetPeriod.setVersionNumber(budgetPeriod.getVersionNumber());
                }
            }
        }

    }
    
    private void updateTotalCost (BudgetDocument budgetDocument) {
        BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalCost = BudgetDecimal.ZERO;
        for(BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getTotalDirectCost().isGreaterThan(BudgetDecimal.ZERO) || budgetPeriod.getTotalIndirectCost().isGreaterThan(BudgetDecimal.ZERO)) {
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
        for (BudgetVersionOverview version: budgetDocument.getProposal().getBudgetVersionOverviews()) {
            if (budgetDocument.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                version.setFinalVersionFlag(budgetDocument.getFinalVersionFlag());
                version.setBudgetStatus(budgetDocument.getBudgetStatus());
            }
        }
    }
    
    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        if(!StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(),budgetForm.getOhRateClassCodePrevValue()) || !StringUtils.equalsIgnoreCase(budgetDocument.getUrRateClassCode(),budgetForm.getUrRateClassCodePrevValue())){
            return confirm(buildRecalculateBudgetConfirmationQuestion(mapping, form, request, response), CONFIRM_HEADER_TAB_KEY, DO_NOTHING);            
        }
        
        return super.headerTab(mapping, form, request, response);
    }
    
    public ActionForward headerTabAfterQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return super.headerTab(mapping, form, request, response);
    }
}
