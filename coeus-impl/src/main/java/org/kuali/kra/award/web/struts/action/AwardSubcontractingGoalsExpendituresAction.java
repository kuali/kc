/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.subcontracting.goalsAndExpenditures.AwardSubcontractingBudgetedGoals;
import org.kuali.kra.award.subcontracting.goalsAndExpenditures.AwardSubcontractingGoalsExpendituresForm;
import org.kuali.kra.award.subcontracting.goalsAndExpenditures.AwardSubcontractingGoalsExpendituresRule;
import org.kuali.kra.award.subcontracting.goalsAndExpenditures.AwardSubcontractingGoalsExpendituresService;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmounts;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("deprecation")
public class AwardSubcontractingGoalsExpendituresAction extends KualiAction {
    
    private static final String RELOAD = "reload";
    private static final String REFRESH = "refresh";
    private static final String CLEAR = "clear";
    private static final String PREFIX = "awardSubcontractingBudgetedGoals";

    
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
        String methodToCall = awardGoalsExpendituresForm.getMethodToCall();         
        // do pre-processing before dispatching to certain methods
        if( (StringUtils.equals(methodToCall, RELOAD)) || (StringUtils.equals(methodToCall, CLEAR)) || (StringUtils.equals(methodToCall, REFRESH)) ) {
            // remove any error messages from any abandoned edits of a BO (we can safely discard them on a refresh).
            removeErrorMessagesForAbandonedGoalsExpendituresBO();            
            // clear out any 'bad' residual input 
            awardGoalsExpendituresForm.getUnconvertedValues().clear();
            // clear out the unsaved changes flag
            awardGoalsExpendituresForm.setContainingUnsavedChanges(false);
        }
        
        // let superclass handle the main functionality of method dispatch etc.
        return super.execute(mapping, awardGoalsExpendituresForm, request, response);
    }
    
    
    // invoked only on the initial visit from the portal, all subsequent calls will be to other methods
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
        awardGoalsExpendituresForm.setDisplayGoalsExpendituresDetails(false);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }    
    
    
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form; 
        String awardNumber = awardGoalsExpendituresForm.getAwardNumber();
        // invoke the rule to validate the award number that was either lookup returned or user entered
        AwardSubcontractingGoalsExpendituresRule rule = new AwardSubcontractingGoalsExpendituresRule();                
        if(rule.validateAwardNumber(awardNumber)) {
            // use the service to obtain the goals BO for the given award number and set the BO on the form for display
            AwardSubcontractingBudgetedGoals budgetedGoalsBO = getGoalsExpendituresServiceImpl().getBudgetedGoalsBOForAward(awardNumber);
            awardGoalsExpendituresForm.setAwardSubcontractingBudgetedGoals(budgetedGoalsBO);
        }
        else {
            // set the flag on the form to not display the details since we don't have a valid PK 
            awardGoalsExpendituresForm.setDisplayGoalsExpendituresDetails(false);
        }
        
        // set the award Id on the form irrespective of the rule validation result; if validation failed an empty string will be set
        awardGoalsExpendituresForm.setAwardId(rule.getAwardId());
        setExpenditureAmountsOnForm(awardGoalsExpendituresForm);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // delegate to refresh method- DRY!
        ActionForward forward = refresh(mapping, form, request, response);
        if(GlobalVariables.getMessageMap().hasNoErrors()) {
            // set the display message
            AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
            String awardNumber = awardGoalsExpendituresForm.getAwardSubcontractingBudgetedGoals().getAwardNumber();
            KNSGlobalVariables.getMessageList().add(KeyConstants.AWARD_GOALS_RELOADED, new String[]{awardNumber});
        }
        return forward;
    }
    
    
    public ActionForward clear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // delegate to refresh method- DRY!
        ActionForward forward = refresh(mapping, form, request, response);
        // set the display message
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
        String awardNumber = awardGoalsExpendituresForm.getAwardSubcontractingBudgetedGoals().getAwardNumber();
        KNSGlobalVariables.getMessageList().add(KeyConstants.AWARD_GOALS_CLEARED, new String[]{awardNumber});
        return forward;
    }
    
    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
        // first check if the default validation went OK
        if(GlobalVariables.getMessageMap().hasNoErrors()) {
            AwardSubcontractingBudgetedGoals budgetedGoalsBO = awardGoalsExpendituresForm.getAwardSubcontractingBudgetedGoals();
            // then validate the supplied award number from the BO
            String awardNumber = budgetedGoalsBO.getAwardNumber();
            if((new AwardSubcontractingGoalsExpendituresRule()).validateAwardNumber(awardNumber)) {
                // use the service to save the BO
                getGoalsExpendituresServiceImpl().saveBudgetedGoalsBO(budgetedGoalsBO);                
                // add the successful save message for display 
                KNSGlobalVariables.getMessageList().add(KeyConstants.AWARD_GOALS_SAVED, new String[]{awardNumber});
                // reset the unsaved changes flag
                awardGoalsExpendituresForm.setContainingUnsavedChanges(false);
            }
            else {
                // set the flag on the form to not display the details, since somehow (spoofing?) a bad PK was sent in
                awardGoalsExpendituresForm.setDisplayGoalsExpendituresDetails(false);
            }
        }
        setExpenditureAmountsOnForm(awardGoalsExpendituresForm);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    
    
    public ActionForward recalculateBusinessTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm = (AwardSubcontractingGoalsExpendituresForm) form;
        // first check if the default validation went OK 
        if(GlobalVariables.getMessageMap().hasNoErrors()) {
            // then validate the supplied award number from the BO (just in case there was some inadvertent spoofing?)
            String awardNumber = awardGoalsExpendituresForm.getAwardSubcontractingBudgetedGoals().getAwardNumber();
            if((new AwardSubcontractingGoalsExpendituresRule()).validateAwardNumber(awardNumber)) {
                //the totals will be computed in the getters invoked from the tag during display of details
                                
            }
            else {
                // set the flag on the form to not display the details, since somehow (spoofing?) a bad PK was sent in
                awardGoalsExpendituresForm.setDisplayGoalsExpendituresDetails(false);
            }
        }
        setExpenditureAmountsOnForm(awardGoalsExpendituresForm);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private void setExpenditureAmountsOnForm(AwardSubcontractingGoalsExpendituresForm awardGoalsExpendituresForm) {
        // use the service to obtain the expenditure BO for the given award number and set the BO on the form for display
        String awardNumber = awardGoalsExpendituresForm.getAwardSubcontractingBudgetedGoals().getAwardNumber();
        SubcontractingExpenditureCategoryAmounts expenditureAmountsBO = getGoalsExpendituresServiceImpl().getExpenditureAmountsBOForAward(awardNumber);
        awardGoalsExpendituresForm.setSubcontractingExpenditureCategoryAmounts(expenditureAmountsBO);
    }
    
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }
    
    
    private void removeErrorMessagesForAbandonedGoalsExpendituresBO() {
        Set<String> properties = GlobalVariables.getMessageMap().getAllPropertiesWithErrors();
        // copy to a new set in order to avoid 'concurrent modification while iterating' problem
        Set<String> propertiesForIteration = new HashSet<String>(properties);
        for(String property: propertiesForIteration) {
            // remove error messages for property names that begin with lower-cased BO class name 
            if(property.startsWith(PREFIX)) {
                GlobalVariables.getMessageMap().removeAllErrorMessagesForProperty(property);
            }
        }
        // do the same for warnings as well
        properties = GlobalVariables.getMessageMap().getAllPropertiesWithWarnings();
        propertiesForIteration = new HashSet<String>(properties);
        for(String property: propertiesForIteration) {
            // remove warning messages for property names that begin with (lower-cased) BO class name
            if(property.startsWith(PREFIX)) {
                GlobalVariables.getMessageMap().removeAllWarningMessagesForProperty(property);
            }
        }
    }
    
    
    private AwardSubcontractingGoalsExpendituresService getGoalsExpendituresServiceImpl() {
        return KcServiceLocator.getService(AwardSubcontractingGoalsExpendituresService.class);
    }    

}
