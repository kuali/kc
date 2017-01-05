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
    @Override
    public void initialize() {
        super.initialize();
        getBudgetDocument().initialize();
    }

    public String getAwardInMultipleNodeHierarchy() {
        return awardInMultipleNodeHierarchy;
    }


    public void setAwardInMultipleNodeHierarchy(String awardInMultipleNodeHierarchy) {
        this.awardInMultipleNodeHierarchy = awardInMultipleNodeHierarchy;
    }

    @Override
    public String getActionPrefix(){
        return "awardBudget";
    }

    public AwardBudgetDocument getAwardBudgetDocument() {
        return super.getBudgetDocument();
    }

    @Override
    @SuppressWarnings("deprecation")
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
            addExtraButton("methodToCall.reject", configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_reject.gif", "Return");
        }
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new BudgetTask("awardBudget", "cancelBudget", doc.getAwardBudget()))) {
            addExtraButton("methodToCall.cancel", configurationService.getPropertyValueAsString(krImageURL) + "buttonsmall_cancel.gif", "Cancel");
        }
        
        return extraButtons;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }

    public void setBudgetParentId(String budgetParentId) {
        this.budgetParentId = budgetParentId;
    }

    public String getBudgetParentId() {
        return budgetParentId;
    }

    public String getFnARateFlagEditable(){
        return Boolean.toString(!getAwardBudgetDocument().getAwardBudget().getOhRatesNonEditable());
    }

    /*
     * Following 4 methods override base function of Budget. For Award Budgets, header should display info about budget,
     * not parent Award document.
     */
    @Override
    @SuppressWarnings("deprecation")
    protected HeaderField getHeaderDocNumber() {
        return new HeaderField("DataDictionary.DocumentHeader.attributes.documentNumber", getBudgetDocument() == null ? null : getBudgetDocument().getDocumentNumber()); 
    }

    @Override
    @SuppressWarnings("deprecation")
    protected HeaderField getHeaderDocStatus (WorkflowDocument parentWorkflowDocument) {
        AwardBudgetExt abe = this.getAwardBudgetDocument().getAwardBudget();
        return new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", abe.getAwardBudgetStatus().getDescription());
    }
    
    @Override
    @SuppressWarnings("deprecation")
    protected HeaderField getHeaderDocInitiator(WorkflowDocument parentWorkflowDocument) {
        WorkflowDocument doc = getBudgetDocument().getDocumentHeader().getWorkflowDocument();
        return new HeaderField("DataDictionary.AttributeReference.attributes.initiatorNetworkId", doc.getInitiatorPrincipalId());
    }
    
    @Override
    @SuppressWarnings("deprecation")
    protected HeaderField getHeaderDocCreateDate(WorkflowDocument parentWorkflowDocument) {
        Date ts = getBudgetDocument().getDocumentHeader().getWorkflowDocument().getDateCreated().toDate();
        String updateDateStr = CoreApiServiceLocator.getDateTimeService().toString(ts, "hh:mm a MM/dd/yyyy");
        return new HeaderField("DataDictionary.AttributeReference.attributes.createDate", updateDateStr);
    }

    public AwardBudgetPeriodSummaryCalculatedAmount getAwardBudgetPeriodSummaryCalculatedAmount() {
        return awardBudgetPeriodSummaryCalculatedAmount;
    }

    public void setAwardBudgetPeriodSummaryCalculatedAmount(
            AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount) {
        this.awardBudgetPeriodSummaryCalculatedAmount = awardBudgetPeriodSummaryCalculatedAmount;
    }
    /*
     * Remove "Modular Budget" tab from award budget  
     * */
    @Override
    @SuppressWarnings("deprecation")
    public HeaderNavigation[] getHeaderNavigationTabs() {
        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
        List<HeaderNavigation> resultList = new ArrayList<>();
 
        for (HeaderNavigation nav : navigation) {
            if (!StringUtils.equals(nav.getHeaderTabNavigateTo(),"modularBudget")) {
                resultList.add(nav);
            }
        }
        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }
    
    @Override
    public boolean getCanModifyBudgetRates() {
        return this.getEditingMode().containsKey("modifyBudgets");
    }
    

    public Award getAward() {
        return this.getAwardBudgetDocument().getBudget().getBudgetParent();
    }
    

    public ScaleTwoDecimal getObligatedTotal() {
        return getPreviousObligatedTotal().add(getObligatedChange());
    }

    public ScaleTwoDecimal getPreviousObligatedTotal() {
        //sum up all the previous changes
        AwardBudgetExt awardBudgetExt = this.getAwardBudgetDocument().getAwardBudget();
        Award award = this.getAwardBudgetDocument().getBudget().getBudgetParent();
        return getSumOfAllPreviousBudgetChanges(awardBudgetExt, award.getBudgets());
    }
    
    /**
     * This method sums up all the previous changes of the prior budget versions.
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
     * This method finds a particular budget in the list of budgets based on the budget id.  If no budget is found, a null is returned.
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
     * This method returns the difference in the obligation total between this budget, and the previous.
     */
    public ScaleTwoDecimal getObligatedChange() {
        AwardBudgetExt budget = this.getAwardBudgetDocument().getAwardBudget();
        if (budget != null && budget.getTotalCostLimit() != null) {
            return budget.getTotalCostLimit();
        } else {
            return ScaleTwoDecimal.ZERO;
        }
    }
}
