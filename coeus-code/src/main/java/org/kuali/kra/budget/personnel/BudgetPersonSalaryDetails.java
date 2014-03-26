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
package org.kuali.kra.budget.personnel;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class BudgetPersonSalaryDetails extends KcPersistableBusinessObjectBase {
    
    private Long budgetPersonSalaryDetailId;  
    
    private Integer personSequenceNumber;
    
    private Long budgetId;
    
    private Integer budgetPeriod;
    
    private String personId;
    
    private ScaleTwoDecimal baseSalary = ScaleTwoDecimal.ZERO;

    public Long getBudgetPersonSalaryDetailId() {
        return budgetPersonSalaryDetailId;
    }

    public void setBudgetPersonSalaryDetailId(Long budgetPersonSalaryDetailId) {
        this.budgetPersonSalaryDetailId = budgetPersonSalaryDetailId;
    }

    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public ScaleTwoDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(ScaleTwoDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

}
