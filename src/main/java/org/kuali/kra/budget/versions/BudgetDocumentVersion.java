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
package org.kuali.kra.budget.versions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.bo.DocumentHeader;

public class BudgetDocumentVersion  extends KraPersistableBusinessObjectBase implements Comparable<BudgetDocumentVersion>{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2143813153034264031L;
    private String parentDocumentKey;
//    private ProposalDevelopmentDocument parentDocument;
    private List<BudgetVersionOverview> budgetVersionOverviews;
    private String documentNumber;
    private DocumentHeader documentHeader;

    
    public BudgetDocumentVersion(){
        budgetVersionOverviews = new ArrayList<BudgetVersionOverview>();
    }

//    /**
//     * Gets the parentDocument attribute. 
//     * @return Returns the parentDocument.
//     */
//    public ProposalDevelopmentDocument getParentDocument() {
//        return parentDocument;
//    }
//
//    /**
//     * Sets the parentDocument attribute value.
//     * @param parentDocument The parentDocument to set.
//     */
//    public void setParentDocument(ProposalDevelopmentDocument proposal) {
//        this.parentDocument = proposal;
//    }

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
    public BudgetVersionOverview getBudgetVersionOverview(){
        if(budgetVersionOverviews.isEmpty()){
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
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("parentDocumentKey", parentDocumentKey);
        return hashMap;
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

    

}
