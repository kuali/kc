/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.personnel;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PER_DET_RATE_AND_BASE")
public class BudgetPersonnelRateAndBase extends AbstractBudgetRateAndBase {

    private static final long serialVersionUID = -3822394019599765292L;

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BGT_PER_DET_RATE_BASE_ID")
    @GeneratedValue(generator = "SEQ_BGT_PER_DET_RATE_BASE_ID")
    @Id
    @Column(name = "BGT_PER_DET_RATE_AND_BASE_ID")
    private Long budgetPersonnelRateAndBaseId;

    @Column(name = "BUDGET_PERSONNEL_CAL_AMTS_ID")
    private Long budgetPersonnelCalculatedAmountId;

    @Column(name = "BUDGET_PERSONNEL_DETAILS_ID")
    private Long budgetPersonnelLineItemId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "PERSON_NUMBER")
    private Integer personNumber;

    @Column(name = "SALARY_REQUESTED")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal salaryRequested;

    /**
     * Gets the salaryRequested attribute. 
     * @return Returns the salaryRequested.
     */
    public ScaleTwoDecimal getSalaryRequested() {
        return salaryRequested;
    }

    /**
     * Sets the salaryRequested attribute value.
     * @param salaryRequested The salaryRequested to set.
     */
    public void setSalaryRequested(ScaleTwoDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
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

    /**
     * Gets the budgetPersonnelCalculatedAmountId attribute. 
     * @return Returns the budgetPersonnelCalculatedAmountId.
     */
    public Long getBudgetPersonnelCalculatedAmountId() {
        return budgetPersonnelCalculatedAmountId;
    }

    /**
     * Sets the budgetPersonnelCalculatedAmountId attribute value.
     * @param budgetPersonnelCalculatedAmountId The budgetPersonnelCalculatedAmountId to set.
     */
    public void setBudgetPersonnelCalculatedAmountId(Long budgetPersonnelCalculatedAmountId) {
        this.budgetPersonnelCalculatedAmountId = budgetPersonnelCalculatedAmountId;
    }

    /**
     * Gets the budgetPersonnelLineItemId attribute. 
     * @return Returns the budgetPersonnelLineItemId.
     */
    public Long getBudgetPersonnelLineItemId() {
        return budgetPersonnelLineItemId;
    }

    /**
     * Sets the budgetPersonnelLineItemId attribute value.
     * @param budgetPersonnelLineItemId The budgetPersonnelLineItemId to set.
     */
    public void setBudgetPersonnelLineItemId(Long budgetPersonnelLineItemId) {
        this.budgetPersonnelLineItemId = budgetPersonnelLineItemId;
    }

    /**
     * Gets the budgetPersonnelRateAndBaseId attribute. 
     * @return Returns the budgetPersonnelRateAndBaseId.
     */
    public Long getBudgetPersonnelRateAndBaseId() {
        return budgetPersonnelRateAndBaseId;
    }

    /**
     * Sets the budgetPersonnelRateAndBaseId attribute value.
     * @param budgetPersonnelRateAndBaseId The budgetPersonnelRateAndBaseId to set.
     */
    public void setBudgetPersonnelRateAndBaseId(Long budgetPersonnelRateAndBaseId) {
        this.budgetPersonnelRateAndBaseId = budgetPersonnelRateAndBaseId;
    }
}
