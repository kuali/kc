/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class AwardBudgetForm extends BudgetForm {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9001767909548738932L;
    private String awardInMultipleNodeHierarchy;
    private String budgetParentId;
    private AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount;
    
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
        ConfigurationService configurationService = KRADServiceLocator.getKualiConfigurationService();
        
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);
        if (tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask(TaskName.TOGGLE_AWARD_BUDGET_STATUS, doc))) {
            String toggleAwardStatusButtonImage = buildExtraButtonSourceURI("buttonsmall_toggleBudgetStatus.gif");
            addExtraButton("methodToCall.toggleAwardBudgetStatus", toggleAwardStatusButtonImage, "Toggle Budget Status");
        }
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask(TaskName.POST_AWARD_BUDGET,doc ))) {
            String postAwardBudgetImage = buildExtraButtonSourceURI("buttonsmall_postawardbudget.gif");
            addExtraButton("methodToCall.postAwardBudget", postAwardBudgetImage, "Post Budget");
        }
        
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new BudgetTask("awardBudget", "rejectBudget", doc))) {
            addExtraButton("methodToCall.reject", configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_reject.gif", "Reject");
        }
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new BudgetTask("awardBudget", "cancelBudget", doc))) {
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
        return new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.workflowDocumentStatus", abe.getAwardBudgetStatus().getDescription());
    }
    
    @Override
    protected HeaderField getHeaderDocInitiator(WorkflowDocument parentWorkflowDocument) {
        WorkflowDocument doc = getBudgetDocument().getDocumentHeader().getWorkflowDocument();
        return new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.initiatorNetworkId", doc.getInitiatorPrincipalId());
    }
    
    @Override
    protected HeaderField getHeaderDocCreateDate(WorkflowDocument parentWorkflowDocument) {
        Date ts = getBudgetDocument().getDocumentHeader().getWorkflowDocument().getDateCreated().toDate();
        String updateDateStr = CoreApiServiceLocator.getDateTimeService().toString(ts, "hh:mm a MM/dd/yyyy");
        return new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.createDate", updateDateStr);
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
    
    /**
     * 
     * @see org.kuali.kra.budget.web.struts.form.BudgetForm#getCanModifyBudgetRates()
     */
    @Override
    public boolean getCanModifyBudgetRates() {
        boolean retVal = this.getEditingMode().containsKey("modifyBudgets");
        return retVal;
    }
}