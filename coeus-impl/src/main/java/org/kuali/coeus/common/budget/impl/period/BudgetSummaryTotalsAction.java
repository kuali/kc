/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.period;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.core.BudgetAction;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetPeriodExt;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
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
        getBudgetCommonService(budget.getBudgetParent()).recalculateBudget(budget);
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

    private Budget getBudget(ActionForm form) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        return budget;
    }
    
    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        super.populateAuthorizationFields(formBase);
        BudgetForm budgetForm = (BudgetForm) formBase;
        String navigateTo = budgetForm.getNavigateTo();
        Map documentActions = formBase.getDocumentActions();
        if ("summaryTotals".equalsIgnoreCase(navigateTo)) {
            if (documentActions.containsKey(KRADConstants.KUALI_ACTION_CAN_RELOAD)) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_RELOAD);
            }
        }
    }


}
