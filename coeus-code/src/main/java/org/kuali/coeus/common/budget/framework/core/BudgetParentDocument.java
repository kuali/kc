/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.sys.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionCollection;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.datadictionary.KNSDocumentEntry;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.web.ui.ExtraButton;

import javax.persistence.MappedSuperclass;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BudgetParentDocument<T extends BudgetParent> extends KcTransactionalDocumentBase implements BudgetVersionCollection, BudgetDocumentTypeChecker, Permissionable {

    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

    public BudgetDocumentVersion getFinalBudgetVersion() {
        for (BudgetDocumentVersion version : getBudgetDocumentVersions()) {
            if (version.getBudgetVersionOverview().isFinalVersionFlag()) {
                return version;
            }
        }
        return null;
    }

    public void updateDocumentDescriptions(List<BudgetDocumentVersion> budgetVersionOverviews) {
        BudgetService budgetService = KcServiceLocator.getService(BudgetService.class);
        for (BudgetDocumentVersion budgetDocumentVersion : budgetVersionOverviews) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            if (budgetVersion.isDescriptionUpdatable() && !StringUtils.isBlank(budgetVersion.getDocumentDescription())) {
                budgetService.updateDocumentDescription(budgetVersion);
                budgetVersion.setDescriptionUpdatable(false);
            }
        }
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

    public List<HeaderNavigation> getBudgetHeaderNavigatorList() {
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KcServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        KNSDocumentEntry docEntry = (KNSDocumentEntry) dataDictionaryService.getDataDictionary().getDocumentEntry(BudgetDocument.class.getName());
        return docEntry.getHeaderNavigationList();
    }

    public abstract T getBudgetParent();

}
