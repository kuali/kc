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
package org.kuali.coeus.common.budget.impl.struts;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetPeriodExt;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class BudgetSummaryTotalsAction extends BudgetAction {

    private static final String SUMMARY_TOTALS = "summaryTotals";
    private static final String START_INDEX = "startIndex";
    private static final String END_INDEX = "endIndex";
    private static final String PERIOD_END_INDEX = "periodEndIndex";
    private static final String PERIOD_START_INDEX = "periodStartIndex";

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
        getBudgetCommonService(budget.getBudgetParent()).calculateBudgetOnSave(budget);
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

    public ActionForward previousPeriodSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(StringUtils.isNotEmpty(request.getParameter(PERIOD_START_INDEX)) &&
                StringUtils.isNotEmpty(request.getParameter(PERIOD_END_INDEX))) {
            int oldPeriodStartIndex = Integer.parseInt(request.getParameter(PERIOD_START_INDEX));
            int oldPeriodEndIndex = Integer.parseInt(request.getParameter(PERIOD_END_INDEX));
            
            int newPeriodStartIndex = oldPeriodStartIndex - Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE; 
            int newPeriodEndIndex = oldPeriodEndIndex - Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE; 
                    
            if(newPeriodStartIndex < 0) {
                newPeriodStartIndex = 0;
                newPeriodEndIndex = newPeriodStartIndex + (Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE -1);
            }
            request.setAttribute(START_INDEX, newPeriodStartIndex);
            request.setAttribute(END_INDEX, newPeriodEndIndex);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward nextPeriodSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Budget budget = getBudget(form);
        
        if(StringUtils.isNotEmpty(request.getParameter(PERIOD_START_INDEX)) &&
                StringUtils.isNotEmpty(request.getParameter(PERIOD_END_INDEX))) {
            int oldPeriodStartIndex = Integer.parseInt(request.getParameter(PERIOD_START_INDEX));
            int oldPeriodEndIndex = Integer.parseInt(request.getParameter(PERIOD_END_INDEX));
            
            int newPeriodStartIndex = -1; 
            int newPeriodEndIndex = -1;
                    
            if(budget.getBudgetPeriods().size() > (oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE)) {
                newPeriodEndIndex = oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
                newPeriodStartIndex = oldPeriodStartIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
            } else if (budget.getBudgetPeriods().size() > (oldPeriodEndIndex +1) ) {
                newPeriodEndIndex = budget.getBudgetPeriods().size() -1 ;
                newPeriodStartIndex = newPeriodEndIndex - (Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE -1);
            }
            
            request.setAttribute(START_INDEX, newPeriodStartIndex);
            request.setAttribute(END_INDEX, newPeriodEndIndex);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private Budget getBudget(ActionForm form) {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        return budget;
    }
    
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        super.populateAuthorizationFields(formBase);
        BudgetForm budgetForm = (BudgetForm) formBase;
        String navigateTo = budgetForm.getNavigateTo();
        Map documentActions = formBase.getDocumentActions();
        if (SUMMARY_TOTALS.equalsIgnoreCase(navigateTo)) {
            if (documentActions.containsKey(KRADConstants.KUALI_ACTION_CAN_RELOAD)) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            }
        }
    }


}
