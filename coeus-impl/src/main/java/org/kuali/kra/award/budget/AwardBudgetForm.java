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
package org.kuali.kra.award.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetContainer;
import org.kuali.kra.award.budget.document.authorizer.BudgetTask;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AwardBudgetForm extends BudgetForm implements BudgetContainer {

    private static final long serialVersionUID = 9001767909548738932L;
    private String awardInMultipleNodeHierarchy;
    private String budgetParentId;
    private AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount;
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "AwardBudgetDocument";
    }
    
    public AwardBudgetForm() {
        super();
        awardBudgetPeriodSummaryCalculatedAmount = new AwardBudgetPeriodSummaryCalculatedAmount();
    }
    public void initialize() {
        super.initialize();
        getBudgetDocument().initialize();
    }
    /**
     * Gets the awardInMultipleNodeHierarchy attribute. 
     * @return Returns the awardInMultipleNodeHierarchy.
     */
    public String getAwardInMultipleNodeHierarchy() {
        return awardInMultipleNodeHierarchy;
    }

    /**
     * Sets the awardInMultipleNodeHierarchy attribute value.
     * @param awardInMultipleNodeHierarchy The awardInMultipleNodeHierarchy to set.
     */
    public void setAwardInMultipleNodeHierarchy(String awardInMultipleNodeHierarchy) {
        this.awardInMultipleNodeHierarchy = awardInMultipleNodeHierarchy;
    }
    public String getActionPrefix(){
        return "awardBudget";
    }

    public AwardBudgetDocument getAwardBudgetDocument() {
        return (AwardBudgetDocument)super.getBudgetDocument();
    }
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        AwardBudgetDocument doc = this.getAwardBudgetDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String krImageURL = Constants.KR_EXTERNALIZABLE_IMAGES_URI_KEY;
        ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
        
        TaskAuthorizationService tas = KcServiceLocator.getService(TaskAuthorizationService.class);
        if (tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask(TaskName.TOGGLE_AWARD_BUDGET_STATUS, doc))) {
            String toggleAwardStatusButtonImage = buildExtraButtonSourceURI("buttonsmall_toggleBudgetStatus.gif");
            addExtraButton("methodToCall.toggleAwardBudgetStatus", toggleAwardStatusButtonImage, "Toggle Budget Status");
        }
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask(TaskName.POST_AWARD_BUDGET,doc ))) {
            String postAwardBudgetImage = buildExtraButtonSourceURI("buttonsmall_postawardbudget.gif");
            addExtraButton("methodToCall.postAwardBudget", postAwardBudgetImage, "Post Budget");
        }
        
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask("rejectBudget", doc))) {
            addExtraButton("methodToCall.reject", configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_reject.gif", "Reject");
        }
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new BudgetTask("awardBudget", "cancelBudget", doc.getAwardBudget()))) {
            addExtraButton("methodToCall.cancel", configurationService.getPropertyValueAsString(krImageURL) + "buttonsmall_cancel.gif", "Cancel");
        }
        
        return extraButtons;
    }
    
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }
    /**
     * Sets the budgetParentId attribute value.
     * @param budgetParentId The budgetParentId to set.
     */
    public void setBudgetParentId(String budgetParentId) {
        this.budgetParentId = budgetParentId;
    }
    /**
     * Gets the budgetParentId attribute. 
     * @return Returns the budgetParentId.
     */
    public String getBudgetParentId() {
        return budgetParentId;
    }
    /**
     * This method is to define whether FnA rate type is editable in Budget Overview panel.
     * @return true if any FnA rates defined in award
     */
    public String getFnARateFlagEditable(){
        return Boolean.toString(!getAwardBudgetDocument().getAwardBudget().getOhRatesNonEditable());
    }

    /*
     * Following 4 methods override base function of Budget. For Award Budgets, header should display info about budget,
     * not parent Award document.
     */
    @Override
    protected HeaderField getHeaderDocNumber() {
        return new HeaderField("DataDictionary.DocumentHeader.attributes.documentNumber", getBudgetDocument() == null ? null : getBudgetDocument().getDocumentNumber()); 
    }

    @Override
    protected HeaderField getHeaderDocStatus (WorkflowDocument parentWorkflowDocument) {
        AwardBudgetExt abe = this.getAwardBudgetDocument().getAwardBudget();
        return new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", abe.getAwardBudgetStatus().getDescription());
    }
    
    @Override
    protected HeaderField getHeaderDocInitiator(WorkflowDocument parentWorkflowDocument) {
        WorkflowDocument doc = getBudgetDocument().getDocumentHeader().getWorkflowDocument();
        return new HeaderField("DataDictionary.AttributeReference.attributes.initiatorNetworkId", doc.getInitiatorPrincipalId());
    }
    
    @Override
    protected HeaderField getHeaderDocCreateDate(WorkflowDocument parentWorkflowDocument) {
        Date ts = getBudgetDocument().getDocumentHeader().getWorkflowDocument().getDateCreated().toDate();
        String updateDateStr = CoreApiServiceLocator.getDateTimeService().toString(ts, "hh:mm a MM/dd/yyyy");
        return new HeaderField("DataDictionary.AttributeReference.attributes.createDate", updateDateStr);
    }
    /**
     * Gets the awardBudgetPeriodSummaryCalculatedAmount attribute. 
     * @return Returns the awardBudgetPeriodSummaryCalculatedAmount.
     */
    public AwardBudgetPeriodSummaryCalculatedAmount getAwardBudgetPeriodSummaryCalculatedAmount() {
        return awardBudgetPeriodSummaryCalculatedAmount;
    }
    /**
     * Sets the awardBudgetPeriodSummaryCalculatedAmount attribute value.
     * @param awardBudgetPeriodSummaryCalculatedAmount The awardBudgetPeriodSummaryCalculatedAmount to set.
     */
    public void setAwardBudgetPeriodSummaryCalculatedAmount(
            AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount) {
        this.awardBudgetPeriodSummaryCalculatedAmount = awardBudgetPeriodSummaryCalculatedAmount;
    }
    /*
     * Remove "Modular Budget" tab from award budget  
     * */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
 
        for (HeaderNavigation nav : navigation) {
            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),"modularBudget")) {
           
            } else {
                resultList.add(nav);
            }
        }
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }
    
    @Override
    public boolean getCanModifyBudgetRates() {
        boolean retVal = this.getEditingMode().containsKey("modifyBudgets");
        return retVal;
    }
    
    /**
     * 
     * This method returns the award associated with the award budget.
     * @return
    */ 
    public Award getAward() {
        return this.getAwardBudgetDocument().getBudget().getBudgetParent();
    }
    
    /**
     * 
     * This method returns the obligated total for this award budget, which is getPreviousObligatedTotal().add(getObligatedChange()).
     * @return
     */
    public ScaleTwoDecimal getObligatedTotal() {
        // getPreviousObligatedTotal + getObligatedChange
        return getPreviousObligatedTotal().add(getObligatedChange());
    }
    
    /**
     * 
     * This method returns the previous budget's obligation amount.
     * @return
     */
    public ScaleTwoDecimal getPreviousObligatedTotal() {
        //sum up all the previous changes
        AwardBudgetExt awardBudgetExt = this.getAwardBudgetDocument().getAwardBudget();
        Award award = (Award) this.getAwardBudgetDocument().getBudget().getBudgetParent();
        return getSumOfAllPreviousBudgetChanges(awardBudgetExt, award.getBudgets());
    }
    
    /**
     * 
     * This method sums up all the previous changes of the prior budget versions.
     * @param curentAwardBudgetExt
     * @param allBudgets
     * @return
     */
    protected ScaleTwoDecimal getSumOfAllPreviousBudgetChanges(AwardBudgetExt curentAwardBudgetExt, List<? extends Budget> allBudgets) {
        if (curentAwardBudgetExt != null && curentAwardBudgetExt.getPrevBudget() != null) {
            ScaleTwoDecimal previousTotalCost = curentAwardBudgetExt.getPrevBudget().getTotalCostLimit();
            AwardBudgetExt previousAwardBudget = findAwardBudgetExt(curentAwardBudgetExt.getPrevBudget().getBudgetId(), allBudgets);
            return previousTotalCost.add(getSumOfAllPreviousBudgetChanges(previousAwardBudget, allBudgets));
        }
        return ScaleTwoDecimal.ZERO;
    }
    
    /**
     * 
     * This method finds a particular budget in the list of budgets based on the budget id.  If no budget is found, a null is returned.
     * @param budgetId
     * @param allBudgets
     * @return
     */
    protected AwardBudgetExt findAwardBudgetExt(Long budgetId, List<? extends Budget> allBudgets) {
        for (Budget budget : allBudgets) {
            if (budget.getBudgetId().equals(budgetId)) {
                return (AwardBudgetExt) budget;
            }
        }
        return null;
    }
    
    /**
     * 
     * This method returns the difference in the obligation total between this budget, and the previous.
     * @return
     */
    public ScaleTwoDecimal getObligatedChange() {
        //return getObligatedTotal().subtract(getPreviousObligatedTotal());
        AwardBudgetExt budget = this.getAwardBudgetDocument().getAwardBudget();
        if (budget != null && budget.getTotalCostLimit() != null) {
            return budget.getTotalCostLimit();
        } else {
            return ScaleTwoDecimal.ZERO;
        }
    }
}
