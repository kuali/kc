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
package org.kuali.kra.budget.bo.id;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Primary Key for the BudgetProjectIncome BO.
 */
@SuppressWarnings("serial")
public class BudgetProjectIncomeId extends BudgetDistributionAndIncomeComponentId {
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name="BUDGET_VERSION_NUMBER")
    private Integer budgetVersionNumber;

    @Id
    @Column(name="COST_SHARE_ID")
    private Integer documentComponentId;

    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        return (obj instanceof BudgetProjectIncomeId);
    }
    
    public int hashCode() {
        return getHashCodeBuilder().toHashCode();
    }
}
