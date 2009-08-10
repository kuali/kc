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
package org.kuali.kra.budget.personnel;

import java.util.LinkedHashMap;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;

public class BudgetPersonnelRateAndBase extends AbstractBudgetRateAndBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3822394019599765292L;
    private String personId;
    private Integer personNumber;
    private BudgetDecimal salaryRequested;
    /**
     * Gets the salaryRequested attribute. 
     * @return Returns the salaryRequested.
     */
    public BudgetDecimal getSalaryRequested() {
        return salaryRequested;
    }
    /**
     * Sets the salaryRequested attribute value.
     * @param salaryRequested The salaryRequested to set.
     */
    public void setSalaryRequested(BudgetDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
    }
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("personNumber", getPersonNumber());
        hashMap.put("personId", getPersonId());
        hashMap.put("salaryRequested", getSalaryRequested());
        return hashMap;
    }
    /**
     * Gets the personNumber attribute. 
     * @return Returns the personNumber.
     */
    public Integer getPersonNumber() {
        return personNumber;
    }
    /**
     * Sets the personNumber attribute value.
     * @param personNumber The personNumber to set.
     */
    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }
    /**
     * Gets the personId attribute. 
     * @return Returns the personId.
     */
    public String getPersonId() {
        return personId;
    }
    /**
     * Sets the personId attribute value.
     * @param personId The personId to set.
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

}
