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
package org.kuali.kra.budget.versions;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;

public class BudgetDocumentVersion extends KraPersistableBusinessObjectBase implements Comparable<BudgetDocumentVersion> {

    private static final String BUDGET_COMPLETE = "1";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2143813153034264031L;

    private String parentDocumentKey;

    private String parentDocumentTypeCode;

    private List<BudgetVersionOverview> budgetVersionOverviews;

    private String documentNumber;

    private DocumentHeader documentHeader;

    public BudgetDocumentVersion() {
        budgetVersionOverviews = new ArrayList<BudgetVersionOverview>();
    }

    /**
     * Gets the budgetVersionOverviews attribute. 
     * @return Returns the budgetVersionOverviews.
     */
    public List<BudgetVersionOverview> getBudgetVersionOverviews() {
        return budgetVersionOverviews;
    }

    /**
     * Sets the budgetVersionOverviews attribute value.
     * @param budgetVersionOverviews The budgetVersionOverviews to set.
     */
    public void setBudgetVersionOverviews(List<BudgetVersionOverview> budgets) {
        this.budgetVersionOverviews = budgets;
    }

    /**
     * 
     * This method returns Budget object. Creates new budget instance if the budgetVersionOverviews list is empty
     * @return Budget
     */
    public BudgetVersionOverview getBudgetVersionOverview() {
        if (budgetVersionOverviews.isEmpty()) {
            budgetVersionOverviews.add(new BudgetVersionOverview());
        }
        return budgetVersionOverviews.get(0);
    }

    /**
     * Gets the parentDocumentKey attribute. 
     * @return Returns the parentDocumentKey.
     */
    public String getParentDocumentKey() {
        return parentDocumentKey;
    }

    /**
     * Sets the parentDocumentKey attribute value.
     * @param parentDocumentKey The parentDocumentKey to set.
     */
    public void setParentDocumentKey(String parentDocumentNumber) {
        this.parentDocumentKey = parentDocumentNumber;
    }

    public Budget getFinalBudget() {
        for (BudgetVersionOverview budgetVersionOverview : this.getBudgetVersionOverviews()) {
            if (budgetVersionOverview != null && budgetVersionOverview.getBudgetStatus() != null && budgetVersionOverview.getBudgetStatus().equals(BUDGET_COMPLETE) && budgetVersionOverview.isFinalVersionFlag()) {
                Budget result = findBudget();
                if (result != null) {
                    return (Budget) result;
                }
            }
        }
        return null;
    }

    public boolean isBudgetComplete() {
        if (!getBudgetVersionOverviews().isEmpty()) {
            return BUDGET_COMPLETE.equals(getBudgetVersionOverview().getBudgetStatus());
        }
        return false;
    }

    /**
     * This method...
     * @param budgetVersionOverview
     * @return
     */
    public Budget findBudget() {
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        try {
            BudgetDocument budgetDoc = (BudgetDocument) docService.getByDocumentHeaderId(getDocumentNumber());
            return budgetDoc.getBudget();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @see java.lang.Comparable
     */
    public int compareTo(BudgetDocumentVersion otherVersion) {
        return getBudgetVersionOverview().getBudgetVersionNumber().compareTo(otherVersion.getBudgetVersionOverview().getBudgetVersionNumber());
    }

    /**
     * Gets the documentNumber attribute. 
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute value.
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the documentHeader attribute. 
     * @return Returns the documentHeader.
     */
    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    /**
     * Sets the documentHeader attribute value.
     * @param documentHeader The documentHeader to set.
     */
    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    /**
     * Gets the parentDocumentTypeCode attribute. 
     * @return Returns the parentDocumentTypeCode.
     */
    public String getParentDocumentTypeCode() {
        return parentDocumentTypeCode;
    }

    /**
     * Sets the parentDocumentTypeCode attribute value.
     * @param parentDocumentTypeCode The parentDocumentTypeCode to set.
     */
    public void setParentDocumentTypeCode(String parentDocumentTypeCode) {
        this.parentDocumentTypeCode = parentDocumentTypeCode;
    }
}
