/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.framework.personnel;

import javax.persistence.*;


import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PER_DET_RATE_AND_BASE")
public class BudgetPersonnelRateAndBase extends AbstractBudgetRateAndBase {

    private static final long serialVersionUID = -3822394019599765292L;

    @PortableSequenceGenerator(name = "SEQ_BGT_PER_DET_RATE_BASE_ID")
    @GeneratedValue(generator = "SEQ_BGT_PER_DET_RATE_BASE_ID")
    @Id
    @Column(name = "BGT_PER_DET_RATE_AND_BASE_ID")
    private Long budgetPersonnelRateAndBaseId;

    @Column(name = "BUDGET_PERSONNEL_CAL_AMTS_ID")
    private Long budgetPersonnelCalculatedAmountId;

    @Column(name = "BUDGET_PERSONNEL_DETAILS_ID", insertable = false, updatable = false)
    private Long budgetPersonnelLineItemId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "PERSON_NUMBER")
    private Integer personNumber;

    @Column(name = "SALARY_REQUESTED")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal salaryRequested;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_PERSONNEL_DETAILS_ID", referencedColumnName = "BUDGET_PERSONNEL_DETAILS_ID")
    private BudgetPersonnelDetails budgetPersonnelLineItem;

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

	public BudgetPersonnelDetails getBudgetPersonnelLineItem() {
		return budgetPersonnelLineItem;
	}

	public void setBudgetPersonnelLineItem(
			BudgetPersonnelDetails budgetPersonnelLineItem) {
		this.budgetPersonnelLineItem = budgetPersonnelLineItem;
	}
}
