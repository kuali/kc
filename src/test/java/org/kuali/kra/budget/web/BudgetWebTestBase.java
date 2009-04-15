/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.web;

import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class BudgetWebTestBase extends ProposalDevelopmentWebTestBase {
    
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";
    
    private String documentNumber;
    
    protected HtmlPage getBudgetVersionsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                DEFAULT_PROPOSAL_TYPE_CODE,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        HtmlPage budgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        return budgetVersionsPage;
    }

    /**
     * Convenience method used because the conventional way for adding a budget version is:
     * <code>
        addBudgetVersion*getBudgetVersionsPage());
     * </code>
     * 
     * 
     * @see #getBudgetVersionsPage()
     * @see #addBudgetVersion(HtmlPage)
     * @return HtmlPage the resulting page after adding a budget version
     */
    protected HtmlPage addBudgetVersion() throws Exception {
        return addBudgetVersion(getBudgetVersionsPage());
    }

    protected HtmlPage addBudgetVersion(HtmlPage budgetVersionsPage) throws Exception {
        setFieldValue(budgetVersionsPage, NEW_BUDGET_VERSION_NAME, "Test Budget Version - 1");
        HtmlElement addBtn = getElementByName(budgetVersionsPage, ADD_BUDGET_VERSION_BUTTON, true);
        budgetVersionsPage = clickOn(addBtn);
        HtmlElement openBtn = getElementByName(budgetVersionsPage, "methodToCall.openBudgetVersion.line0.x");
        budgetVersionsPage = clickOn(openBtn);
        return budgetVersionsPage;
    }

    protected String getDocumentNumber() {
        return documentNumber;
    }

    protected void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    
}
