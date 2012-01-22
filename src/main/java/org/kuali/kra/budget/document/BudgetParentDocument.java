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
package org.kuali.kra.budget.document;

import java.util.Collections;
import java.util.List;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionCollection;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.web.ui.ExtraButton;

@SuppressWarnings("serial")
public abstract class BudgetParentDocument<T extends BudgetParent> extends ResearchDocumentBase 
                                                            implements BudgetVersionCollection,BudgetDocumentTypeChecker, Permissionable  {
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
            return KraServiceLocator.getService(ParameterService.class);        
    }
    

    public BudgetDocumentVersion getFinalBudgetVersion() {
      for (BudgetDocumentVersion version : getBudgetDocumentVersions()) {
          if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
              return version;
          }
      }
      return null;
    }

    /**
     * This method gets the next budget version number for this proposal. We can't use documentNextVersionNumber because that
     * requires a save and we don't want to save the proposal development document before forwarding to the budget document.
     * 
     * @return Integer
     */
    public Integer getNextBudgetVersionNumber() {
        List<BudgetDocumentVersion> versions = getBudgetDocumentVersions();
        if (versions.isEmpty()) {
            return 1;
        }
        Collections.sort(versions);
        BudgetDocumentVersion lastVersion = versions.get(versions.size() - 1);
        return lastVersion.getBudgetVersionOverview().getBudgetVersionNumber() + 1;
    }
    public void addNewBudgetVersion(BudgetDocument budgetDocument, String name, boolean isDescriptionUpdatable) {
        Budget budget = budgetDocument.getBudget();
        BudgetDocumentVersion budgetDocumentVersion = new BudgetDocumentVersion();
        budgetDocumentVersion.setDocumentNumber(budgetDocument.getDocumentNumber());
        budgetDocumentVersion.setParentDocumentKey(getDocumentNumber());
        budgetDocumentVersion.setVersionNumber(budgetDocument.getVersionNumber());
        BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
        budgetVersion.setDocumentNumber(budgetDocument.getDocumentNumber());
        budgetVersion.setDocumentDescription(name);
        budgetVersion.setBudgetVersionNumber(budget.getBudgetVersionNumber());
        budgetVersion.setStartDate(budget.getStartDate());
        budgetVersion.setEndDate(budget.getEndDate());
        budgetVersion.setOhRateTypeCode(budget.getOhRateTypeCode());
        budgetVersion.setOhRateClassCode(budget.getOhRateClassCode());
        budgetVersion.setVersionNumber(budget.getVersionNumber());
        budgetVersion.setDescriptionUpdatable(isDescriptionUpdatable);
        
        String budgetStatusIncompleteCode = this.getParameterService().getParameterValueAsString(
                BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);
        budgetVersion.setFinalVersionFlag(false);
        
        getBudgetDocumentVersions().add(budgetDocumentVersion);
    }
    public BudgetDocumentVersion getBudgetDocumentVersion(int selectedLine) {
        return getBudgetDocumentVersions().get(selectedLine);
    }
    public abstract Permissionable getBudgetPermissionable();

    public abstract boolean isComplete();
    public abstract void saveBudgetFinalVersionStatus(BudgetDocument budgetDocument);
    public abstract void processAfterRetrieveForBudget(BudgetDocument budgetDocument);
    public abstract String getTaskGroupName();
    public abstract Task getParentAuthZTask(String taskName);
    public abstract ExtraButton configureReturnToParentTopButton();
    public List<HeaderNavigation> getBudgetHeaderNavigatorList(){
      DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
      DocumentEntry docEntry = (DocumentEntry) dataDictionaryService.getDataDictionary().getDocumentEntry(BudgetDocument.class.getName());
      return docEntry.getHeaderNavigationList();
    }
    public abstract T getBudgetParent();
}
