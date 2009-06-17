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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;

public class BudgetSummaryTotalsAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetSummaryTotalsAction.class);

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
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        if(StringUtils.isNotEmpty(request.getParameter("periodStartIndex")) && 
                StringUtils.isNotEmpty(request.getParameter("periodEndIndex"))) {
            int oldPeriodStartIndex = Integer.parseInt(request.getParameter("periodStartIndex"));
            int oldPeriodEndIndex = Integer.parseInt(request.getParameter("periodEndIndex"));
            
            int newPeriodStartIndex = -1; 
            int newPeriodEndIndex = -1;
                    
            if(budgetDocument.getBudgetPeriods().size() > (oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE)) {
                newPeriodEndIndex = oldPeriodEndIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
                newPeriodStartIndex = oldPeriodStartIndex + Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE;
            } else if (budgetDocument.getBudgetPeriods().size() > (oldPeriodEndIndex +1) ) {
                newPeriodEndIndex = budgetDocument.getBudgetPeriods().size() -1 ;
                newPeriodStartIndex = newPeriodEndIndex - (Constants.BUDGET_SUMMARY_PERIOD_GROUP_SIZE -1);
            }
            
            request.setAttribute("startIndex", newPeriodStartIndex);
            request.setAttribute("endIndex", newPeriodEndIndex);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


}
