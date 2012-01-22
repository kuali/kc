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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.core.BudgetCommonServiceFactory;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.DocumentService;

/**
 * This class contains methods common to ProposalDevelopment and Budget actions.
 */
public class BudgetParentActionBase extends KraTransactionalDocumentActionBase {
    

    protected static final String COPY_BUDGET_PERIOD_QUESTION = "copyBudgetQuestion";
    protected static final String QUESTION_TYPE = "copyPeriodsQuestion";
    protected static final String QUESTION_TEXT = "A new version of the budget will be created based on version ";
    
    /**
     * This method looks for the version corresponding to finalBudgetVersion in the list, then marks that version as final.
     * 
     * @param finalBudgetVersion
     * @param budgetVersions
     */
    protected void setFinalBudgetVersion(Integer finalBudgetVersion, List<BudgetVersionOverview> budgetVersions) {
        for (BudgetVersionOverview budgetVersion: budgetVersions) {
            if (budgetVersion.getBudgetVersionNumber().equals(finalBudgetVersion)) {
                budgetVersion.setFinalVersionFlag(true);
            } else {
                budgetVersion.setFinalVersionFlag(false);
            }
        }
    }
    
    /**
     * This method looks at the list of budgetVersions for the final version, then returns the version number.
     * 
     * @param budgetVersions
     * @return
     */
    protected Integer getFinalBudgetVersion(List<BudgetDocumentVersion> budgetVersions) {
        for (BudgetDocumentVersion budgetVersion: budgetVersions) {
            if (budgetVersion.getBudgetVersionOverview().isFinalVersionFlag()) {
                return budgetVersion.getBudgetVersionOverview().getBudgetVersionNumber();
            }
        }
        return null;
    }
    
    /**
     * This method sets the proposal budget status to the status of the final budget version.  If there is no final version, do nothing.
     * 
     * @param parentDocument
     */
    protected void setBudgetParentStatus(BudgetParentDocument parentDocument) {
        for (BudgetDocumentVersion budgetVersion: parentDocument.getBudgetDocumentVersions()) {
            if (budgetVersion.getBudgetVersionOverview().isFinalVersionFlag()) {
                parentDocument.getBudgetParent().setBudgetStatus(budgetVersion.getBudgetVersionOverview().getBudgetStatus());
                return;
            }
        }
    }
    
    /**
     * This method sets the budget status of the 'final' budget version (if it exists) to the proposal budget status
     * as indicated in the proposal development document.
     * 
     * @param parentDocument
     */
    protected void setBudgetStatuses(BudgetParentDocument parentDocument) {
        
        String budgetStatusIncompleteCode = getParameterService().getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        
        for (BudgetDocumentVersion budgetDocumentVersion: parentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion =  budgetDocumentVersion.getBudgetVersionOverview();
            if (budgetVersion.isFinalVersionFlag()) {
                budgetVersion.setBudgetStatus(parentDocument.getBudgetParent().getBudgetStatus());
            }
            else {
                budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);
            }
        }
    }
    
    /**
     * Copy the given budget version and add it to the given proposal.
     * 
     * @param budgetParentDocument
     * @param budgetToCopy
     * @param copyPeriodOneOnly if only the first budget period is to be copied
     */
    @SuppressWarnings("unchecked")
    protected void copyBudget(BudgetParentDocument budgetParentDocument, BudgetVersionOverview budgetToCopy, boolean copyPeriodOneOnly) 
    throws WorkflowException {
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocToCopy = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToCopy.getDocumentNumber());
        Budget budget = budgetDocToCopy.getBudget();
        if (copyPeriodOneOnly) {
            BudgetPeriod firstPeriod = budget.getBudgetPeriods().get(0);
            List<BudgetPeriod> newBudgetPeriods = new ArrayList<BudgetPeriod>();
            newBudgetPeriods.add(firstPeriod);
            budget.setBudgetPeriods(newBudgetPeriods);
        }
        BudgetCommonService<BudgetParent> budgetService = getBudgetCommonService(budgetParentDocument);
        BudgetDocument newBudgetDoc = budgetService.copyBudgetVersion(budgetDocToCopy);
        budgetParentDocument.refreshBudgetDocumentVersions();
        List<BudgetDocumentVersion> budgetVersions = budgetParentDocument.getBudgetDocumentVersions();
        for (BudgetDocumentVersion budgetDocumentVersion : budgetVersions) {
            BudgetVersionOverview versionOverview = budgetDocumentVersion.getBudgetVersionOverview();
            if(versionOverview.getBudgetVersionNumber().intValue()==budget.getBudgetVersionNumber().intValue()){
                versionOverview.setDescriptionUpdatable(true);
                versionOverview.setDocumentDescription(budgetToCopy.getDocumentDescription() + " " 
                                                        + budgetToCopy.getBudgetVersionNumber() + " copy");
            }
        }
    }
    /**
     * 
     * This method gets the BudgetCommonService
     * @param parentBudgetDocument
     * @return
     */
    private BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParentDocument parentBudgetDocument) {
        return BudgetCommonServiceFactory.createInstance(parentBudgetDocument);
    }

    protected void populateTabState(KualiForm form, String tabTitle) {
        form.getTabStates().put(WebUtils.generateTabKey(tabTitle), "OPEN");
    }
    
    
}
