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

import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetCalculatedAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_PERSONNEL_CAL_AMTS")
public class BudgetPersonnelCalculatedAmount extends AbstractBudgetCalculatedAmount {

    private static final long serialVersionUID = 3100896964798965084L;

    @Column(name = "PERSON_NUMBER")
    private Integer personNumber;

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BUDGET_PER_CAL_AMTS_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_PER_CAL_AMTS_ID")
    @Id
    @Column(name = "BUDGET_PERSONNEL_CAL_AMTS_ID")
    private Long budgetPersonnelCalculatedAmountId;

    @Column(name = "BUDGET_PERSONNEL_DETAILS_ID")
    private Long budgetPersonnelLineItemId;

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
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
}
