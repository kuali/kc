/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetPeriodExt;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;

public class BudgetSummaryTotalsAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetSummaryTotalsAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return super.execute(mapping, form, request, response);
    }
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        Budget budget = getBudget(form);
        getBudgetCommonService(budget.getBudgetDocument().getParentDocument()).recalculateBudget(budget);
        return actionForward;
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Budget budget = getBudget(form);
        if(budget instanceof AwardBudgetExt){
            List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
            for (int i = 0; i < budgetPeriods.size(); i++) {
                AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriods.get(i);
                String val = request.getParameter("document.budget.budgetPeriods["+i+"].rateOverrideFlag");
                if(StringUtils.isNotBlank(val)){
                    awardBudgetPeriod.setRateOverrideFlag(Boolean.valueOf(val));
                }
            }
        }
        
        //ugly hack to work around OJB bug, unsure how else to fix issue though
        if (budget != null && budget instanceof ProposalDevelopmentBudgetExt) {
            this.getBusinessObjectService().findBySinglePrimaryKey(ProposalDevelopmentBudgetExt.class, budget.getBudgetId());
        }
        return super.save(mapping, form, request, response);
    }
    private AwardBudgetService getAwardBudgetService() {
        return KraServiceLocator.getService(AwardBudgetService.class);
    }
    private BudgetCalculationService getBudgetCalaculationService() {
        return KraServiceLocator.getService(BudgetCalculationService.class);
    }
    public ActionForward previousPeriodSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(StringUtils.isNotEmpty(request.getParameter("periodStartIndex")) && 
                StringUtils.isNotEmpty(request.getParameter("periodEndIndex"))) {
            int oldPeriodStartIndex = Integer.parseInt(request.getParameter("periodStartIndex"));
            int oldPeriodEndIndex = Integer.parseInt(request.getParameter("periodEndIndex"));
            
            int newPeriodStartIndex = oldPeriodStartIndex - Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE; 
            int newPeriodEndIndex = oldPeriodEndIndex - Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE; 
                    
            if(newPeriodStartIndex < 0) {
                newPeriodStartIndex = 0;
                newPeriodEndIndex = newPeriodStartIndex + (Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE -1);
            }
            request.setAttribute("startIndex", newPeriodStartIndex);
            request.setAttribute("endIndex", newPeriodEndIndex);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward nextPeriodSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Budget budget = getBudget(form);
        
        if(StringUtils.isNotEmpty(request.getParameter("periodStartIndex")) && 
                StringUtils.isNotEmpty(request.getParameter("periodEndIndex"))) {
            int oldPeriodStartIndex = Integer.parseInt(request.getParameter("periodStartIndex"));
            int oldPeriodEndIndex = Integer.parseInt(request.getParameter("periodEndIndex"));
            
            int newPeriodStartIndex = -1; 
            int newPeriodEndIndex = -1;
                    
            if(budget.getBudgetPeriods().size() > (oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE)) {
                newPeriodEndIndex = oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
                newPeriodStartIndex = oldPeriodStartIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
            } else if (budget.getBudgetPeriods().size() > (oldPeriodEndIndex +1) ) {
                newPeriodEndIndex = budget.getBudgetPeriods().size() -1 ;
                newPeriodStartIndex = newPeriodEndIndex - (Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE -1);
            }
            
            request.setAttribute("startIndex", newPeriodStartIndex);
            request.setAttribute("endIndex", newPeriodEndIndex);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method...
     * @param form
     * @return
     */
    private Budget getBudget(ActionForm form) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        return budget;
    }


}
