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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetPersonnelBudgetAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetPersonnelBudgetAction.class);
    /**
     * This method is used to navigate it to personnel budget page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward updatePersonnelBudgetView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PERSONNEL_BUDGET);
    }
    /**
     * This method is used to add a new Budget Line Item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelDetails newBudgetPersonnelDetails = budgetForm.getNewBudgetPersonnelDetails();        
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.addBudgetPersonnelDetails(budgetForm.getBudgetDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, newBudgetPersonnelDetails);
        budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        ActionForward actionForward = super.reload(mapping, form, request, response);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Map queryMap = new HashMap();
        queryMap.put("proposalNumber", budgetDocument.getProposalNumber());
        queryMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
        Collection budgetPersons = bos.findMatching(BudgetPerson.class, queryMap);
        budgetDocument.setBudgetPersons(budgetPersons==null?new ArrayList():(List)budgetPersons);
        return actionForward;
    }
    
    public ActionForward deleteBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(budgetForm.getBudgetDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method is used to return back to expenses tab
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward returnToExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
        budgetForm.getBudgetDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
        BudgetDocument budgetDocument = (BudgetDocument) budgetForm.getBudgetDocument();
        budgetDocument.refreshReferenceObject("budgetPeriods");       

        return mapping.findForward("expenses");
    }
    /**
     * This method calculates the salary
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateSalary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetLineItem selectedBudgetLineItem = budgetForm.getSelectedBudgetLineItem();
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));        
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetForm.getBudgetDocument(),selectedBudgetLineItem,budgetPersonnelDetails);
//        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
//        budgetCalculationService.calculateBudgetLineItem(budgetForm.getBudgetDocument(), budgetPersonnelDetails);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
}
