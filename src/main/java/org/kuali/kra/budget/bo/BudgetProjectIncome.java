/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetProjectIncome extends KraPersistableBusinessObjectBase {
    // TODO - JF
    private static final long serialVersionUID = 1L;
    
    private BudgetDocument budgetDocument;
    
    private String proposalNumber;
    private Integer budgetVersionNumber;
    private Integer budgetPeriodNumber;
    private String description;
    private KualiDecimal projectIncome;

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }
    
    public Integer getBudgetPeriodNumber() {
        return budgetPeriodNumber;
    }

    public String getDescription() {
        return description;
    }

    public KualiDecimal getProjectIncome() {
        return projectIncome;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }
    
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber; 
    }
    
    public void setBudgetPeriodNumber(Integer budgetPeriodNumber) {
        this.budgetPeriodNumber = (budgetPeriodNumber != null && budgetPeriodNumber.intValue() > 0) ? budgetPeriodNumber : null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectIncome(KualiDecimal income) {
        this.projectIncome = income;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap();
        hashMap.put("budgetPeriodNumber", getBudgetPeriodNumber());
        hashMap.put("description", getDescription());
        hashMap.put("income", getProjectIncome());
        return hashMap;
    }

    public BudgetDocument getBudgetDocument() {
        return budgetDocument;
    }

    public void setBudgetDocument(BudgetDocument document) {
        this.budgetDocument = document;
    }
}
