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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.DocumentService;

import java.util.List;

/**
 * This class contains methods common to ProposalDevelopment and Budget actions.
 */
public class BudgetParentActionBase extends KcTransactionalDocumentActionBase {
    

    protected static final String COPY_BUDGET_PERIOD_QUESTION = "copyBudgetQuestion";
    protected static final String QUESTION_TYPE = "copyPeriodsQuestion";
    protected static final String QUESTION_TEXT = "A new version of the budget will be created based on version ";
    
    private BudgetService budgetService;
    private BudgetRatesService budgetRatesService;
    
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
     * @deprecated use BudgetService.setBudgetStatuses
     */
    @Deprecated
    protected void setBudgetStatuses(BudgetParentDocument parentDocument) {
        getBudgetService().setBudgetStatuses(parentDocument);
    }
    
    /**
     * Copy the given budget version and add it to the given proposal.
     * 
     * @param budgetParentDocument
     * @param budgetToCopy
     * @param copyPeriodOneOnly if only the first budget period is to be copied
     */
    @SuppressWarnings("unchecked")
    protected void copyBudget(BudgetParent budgetParent, BudgetVersionOverview budgetToCopy, boolean copyPeriodOneOnly) 
    throws WorkflowException {
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocToCopy = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToCopy.getDocumentNumber());
        Budget budget = budgetDocToCopy.getBudget();

        BudgetCommonService<BudgetParent> budgetService = getBudgetCommonService(budgetParent);
        Budget newBudget = budgetService.copyBudgetVersion(budget, copyPeriodOneOnly);
        List<? extends AbstractBudget> budgetVersions = budgetParent.getBudgetVersionOverviews();
        for (AbstractBudget versionOverview : budgetVersions) {
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
    private BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParent parentBudget) {
        return BudgetCommonServiceFactory.createInstance(parentBudget);
    }

    protected void populateTabState(KualiForm form, String tabTitle) {
        form.getTabStates().put(WebUtils.generateTabKey(tabTitle), "OPEN");
    }

    public BudgetService getBudgetService() {
        if (budgetService == null) {
            budgetService = KcServiceLocator.getService(BudgetService.class);
        }
        return budgetService;
    }

    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    public BudgetRatesService getBudgetRatesService() {
        if (budgetRatesService == null) {
            budgetRatesService = KcServiceLocator.getService(BudgetRatesService.class);
        }
        return budgetRatesService;
    }

    public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
        this.budgetRatesService = budgetRatesService;
    }
}
