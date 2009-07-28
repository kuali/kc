/*
 * Copyright 2006-2009 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.KeyConstants.BUDGET_VERSION_EXISTS;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_BUDGET_NAME_MISSING;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetVersionCollection;
import org.kuali.kra.budget.rules.BudgetDocumentRule;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddBudgetVersionRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddBudgetVersionEvent;
import org.kuali.rice.kns.util.GlobalVariables;
import static org.kuali.kra.logging.BufferedLogger.*;

/**
 * A composited rule of the {@link BudgetDocumentRule}. It is expected that the {@link BudgetDocumentRule} will call this rule directly on save,
 * so it does not use or require an event.
 * 
 **/
public class BudgetVersionRule  implements AddBudgetVersionRule {

    /**
     * Entry method for the business rule
     *
     * @param document is a {@link BudgetDocument} instance that the {@link BudgetVersionOverview} is getting added to
     * @returns true if it passed, false if it failed
     */
    public boolean processAddBudgetVersion(AddBudgetVersionEvent event) {
        BudgetVersionCollection versionCollection = (BudgetVersionCollection) ((ProposalDevelopmentDocument)event.getDocument()).getDevelopmentProposal();
        boolean retval = true;

        if (!isNameValid(event.getVersionName())) {
            retval = false;
            GlobalVariables.getErrorMap().putError("document.proposal.developmentProposalList[0].budgetVersionOverview.newBudgetVersionName", 
                    ERROR_BUDGET_NAME_MISSING, "Name");
        }
        
        if (containsVersionOverview(versionCollection, event.getVersionName())) {
            retval = false;
            GlobalVariables.getErrorMap().putError("document.proposal.developmentProposalList[0].budgetVersionOverview", BUDGET_VERSION_EXISTS);
        }            
        return retval;
    }

    /**
     * Validates the name of the {@link BudgetVersion} to be added.
     * 
     * @param newBudgetVersionName name of the {@link BudgetVersion} to be added.
     * @return true if the name is valid, false otherwise
     */
    private boolean isNameValid(String newBudgetVersionName) {
        return hasText(newBudgetVersionName);
    }


    /**
     * Determines if the given {@link List} of {@link BudgetVersionsOverview} instances contains the given {@link BudgetVersionOverview}. It does this by getting
     * the name of the {@link BudgetVersionOverview} and compares it to those in the {@link List}. We use this to prevent duplicate names among
     * {@link BudgetVersionOverview} instances.
     * 
     * @param document {@link ProposalDevelopmentDocument} containing {@link List} of {@link BudgetVersionOverview} instances
     * @param versionName is the name of the {@link BudgetVersionOverview} to look for
     * @returns true if it found <code>versionName</code> inside <code>document</code>, false otherwise
     */
    private boolean containsVersionOverview(BudgetVersionCollection document, String versionName) {
        for (BudgetVersionOverview version : document.getBudgetVersionOverviews()) {
            info("Comparing ", version.getDocumentDescription(), " to ", versionName);
            if (version.getDocumentDescription().equals(versionName)) {
                return true;
            }
        }
        return false;
    }
}
