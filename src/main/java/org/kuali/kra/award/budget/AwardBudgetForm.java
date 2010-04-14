/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class AwardBudgetForm extends BudgetForm {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9001767909548738932L;
    private String awardInMultipleNodeHierarchy;
    private String budgetParentId;

    public AwardBudgetForm() {
        super();
        AwardBudgetDocument budgetDocument = new AwardBudgetDocument();
        this.setDocument(budgetDocument);
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
        String externalImageURL = "kra.externalizable.images.url";
        
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new AwardBudgetTask(TaskName.POST_AWARD_BUDGET,doc ))) {
            String submitToGrantsGovImage = buildExtraButtonSourceURI("buttonsmall_postawardbudget.gif");
            addExtraButton("methodToCall.postAwardBudget", submitToGrantsGovImage, "Post Budget");
        
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
        return Boolean.toString(getAwardBudgetDocument().getParentDocument().getBudgetParent().getAwardFandaRate().isEmpty());
    }
    
}
