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

import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelCalculatedAmountContract;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetCalculatedAmount;
import javax.persistence.*;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PERSONNEL_CAL_AMTS")
public class BudgetPersonnelCalculatedAmount extends AbstractBudgetCalculatedAmount implements BudgetPersonnelCalculatedAmountContract {

    private static final long serialVersionUID = 3100896964798965084L;

    @Column(name = "PERSON_NUMBER")
    private Integer personNumber;

    @PortableSequenceGenerator(name = "SEQ_BUDGET_PER_CAL_AMTS_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_PER_CAL_AMTS_ID")
    @Id
    @Column(name = "BUDGET_PERSONNEL_CAL_AMTS_ID")
    private Long budgetPersonnelCalculatedAmountId;

    @Column(name = "BUDGET_PERSONNEL_DETAILS_ID", insertable = false, updatable = false)
    private Long budgetPersonnelLineItemId;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_PERSONNEL_DETAILS_ID", referencedColumnName = "BUDGET_PERSONNEL_DETAILS_ID")
    private BudgetPersonnelDetails budgetPersonnelLineItem;

    @Override
    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    @Override
    public Long getBudgetPersonnelCalculatedAmountId() {
        return budgetPersonnelCalculatedAmountId;
    }

    public void setBudgetPersonnelCalculatedAmountId(Long budgetPersonnelCalculatedAmountId) {
        this.budgetPersonnelCalculatedAmountId = budgetPersonnelCalculatedAmountId;
    }

    @Override
    public Long getBudgetPersonnelLineItemId() {
        return budgetPersonnelLineItemId;
    }

    public void setBudgetPersonnelLineItemId(Long budgetPersonnelLineItemId) {
        this.budgetPersonnelLineItemId = budgetPersonnelLineItemId;
    }

	public BudgetPersonnelDetails getBudgetPersonnelLineItem() {
		return budgetPersonnelLineItem;
	}

	public void setBudgetPersonnelLineItem(
			BudgetPersonnelDetails budgetPersonnelLineItem) {
		this.budgetPersonnelLineItem = budgetPersonnelLineItem;
	}

	public Long getBudgetLineItemId() {
        return getBudgetPersonnelLineItem().getBudgetLineItemId();
    }

}
