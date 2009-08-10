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
package org.kuali.kra.budget.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

public class BudgetDocument extends ResearchDocumentBase implements Copyable, SessionDocument {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6716733800206633452L;

    private static final String DOCUMENT_TYPE_CODE = "BUDG";

    private String parentDocumentKey;
    private BudgetParentDocument parentDocument;
    private List<Budget> budgets;
    
    public BudgetDocument(){
        super();
        budgets = new ArrayList<Budget>();
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        if(getParentDocumentKey()!=null){
            getService(ProposalStatusService.class).loadBudgetStatusByProposalDocumentNumber(getParentDocumentKey());
        }
    }

    @Override
    public void initialize() {
    }

    public Integer getHackedDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        
        // search for property and get the latest number - increment for next call
        for (Object element : getDocumentNextvalues()) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)element;
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }
        //TODO: need to solve the refresh issue. 
        //workaround till then
        /*****BEGIN BLOCK *****/
        if(propNextValue==1){
            BusinessObjectService bos = getService(BusinessObjectService.class);
            Map<String, String> pkMap = new HashMap<String,String>();
            pkMap.put("documentKey", ((ProposalDevelopmentDocument)getParentDocument()).getDevelopmentProposal().getProposalNumber());
            pkMap.put("propertyName", propertyName);
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)bos.findByPrimaryKey(DocumentNextvalue.class, pkMap);
            if(documentNextvalue!=null) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
                getDocumentNextvalues().add(documentNextvalue);
            }
        }
        /*****END BLOCK********/
        
        // property does not exist - set initial value and increment for next call
        if(propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            documentNextvalue.setDocumentKey(getDocumentNumber());
            getDocumentNextvalues().add(documentNextvalue);
        }
        setDocumentNextvalues(getDocumentNextvalues());
        return propNextValue;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);
        
        if (this.getParentDocument() != null) {
            if (this.getParentDocument().getBudgetDocumentVersions() != null) {
                this.updateDocumentDescriptions(this.getParentDocument().getBudgetDocumentVersions());
            }
        } else {
            this.refreshReferenceObject("parentDocument");
        }
 
        this.getBudget().getRateClassTypes();
        
        this.getBudget().handlePeriodToProjectIncomeRelationship();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        return managedLists;
    }

    /**
     * Gets the parentDocument attribute. 
     * @return Returns the parentDocument.
     */
    public BudgetParentDocument getParentDocument() {
        return parentDocument;
    }

    /**
     * Sets the parentDocument attribute value.
     * @param parentDocument The parentDocument to set.
     */
    public void setParentDocument(BudgetParentDocument parentDocument) {
        this.parentDocument = parentDocument;
    }

    /**
     * Gets the budgets attribute. 
     * @return Returns the budgets.
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Sets the budgets attribute value.
     * @param budgets The budgets to set.
     */
    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    /**
     * 
     * This method returns Budget object. Creates new budget instance if the budgets list is empty
     * @return Budget
     */
    public Budget getBudget(){
        if(budgets.isEmpty()){
            budgets.add(new Budget());
        }
        return budgets.get(0);
    }
    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getDocumentTypeCode()
     */
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
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

//    /**
//     * Gets the budgetVersionNumber attribute. 
//     * @return Returns the budgetVersionNumber.
//     */
//    public Integer getBudgetVersionNumber() {
//        return budgetVersionNumber;
//    }

//    /**
//     * Sets the budgetVersionNumber attribute value.
//     * @param budgetVersionNumber The budgetVersionNumber to set.
//     */
//    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
//        this.budgetVersionNumber = budgetVersionNumber;
//    }
//
}

