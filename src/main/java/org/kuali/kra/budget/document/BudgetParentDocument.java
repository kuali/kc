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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionCollection;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;

@SuppressWarnings("serial")
public abstract class BudgetParentDocument extends ResearchDocumentBase implements BudgetVersionCollection {
    public abstract String getBudgetStatus();
    public abstract void setBudgetStatus(String budgetStatus);
    public abstract String getActivityTypeCode();
    public abstract Date getRequestedStartDateInitial();
    public abstract Date getRequestedEndDateInitial();
    public abstract Task getParentAuthZTask(String taskName);
    public abstract boolean isComplete();
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
    public abstract ActivityType getActivityType();
    public abstract String getUnitNumber();
    public abstract Unit getUnit();
    public abstract BudgetDocumentVersion getBudgetDocumentVersion(int selectedLine);
    public abstract void addNewBudgetVersion(BudgetDocument newBudgetDoc, String versionName, boolean b);
    public abstract List<PersonRolodex> getPersonRolodexList();
    public abstract ProposalPersonRole getProposalNonEmployeeRole(Integer rolodexId);
    public abstract PersonRolodex getProposalEmployee(String personId);
    public abstract PersonRolodex getProposalNonEmployee(Integer rolodexId);
    public abstract ProposalPersonRole getProposalEmployeeRole(String personId);
    public abstract boolean isNih();
    public abstract Map<String, String> getNihDescription();
}
