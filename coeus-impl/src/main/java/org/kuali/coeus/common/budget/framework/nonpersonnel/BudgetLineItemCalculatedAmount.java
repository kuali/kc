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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemCalculatedAmountContract;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_DETAILS_CAL_AMTS")
public class BudgetLineItemCalculatedAmount extends AbstractBudgetCalculatedAmount implements BudgetLineItemCalculatedAmountContract {

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BUDGET_DETAILS_CAL_AMTS_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_DETAILS_CAL_AMTS_ID")
    @Id
    @Column(name = "BUDGET_DETAILS_CAL_AMTS_ID")
    private Long budgetLineItemCalculatedAmountId;

    private static final long serialVersionUID = -1755216989884993632L;

    public BudgetLineItemCalculatedAmount() {
    }

    @Override
    public Long getBudgetLineItemCalculatedAmountId() {
        return budgetLineItemCalculatedAmountId;
    }

    public void setBudgetLineItemCalculatedAmountId(Long budgetLineItemCalculatedAmountId) {
        this.budgetLineItemCalculatedAmountId = budgetLineItemCalculatedAmountId;
    }
}
