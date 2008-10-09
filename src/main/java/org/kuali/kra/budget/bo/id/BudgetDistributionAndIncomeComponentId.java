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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Abstract Primary Key for the BudgetDistributionAndIncomeComponent BO.
 */
public abstract class BudgetDistributionAndIncomeComponentId implements Serializable {
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name="BUDGET_VERSION_NUMBER")
    private Integer budgetVersionNumber;

    @Id
    @Column(name="COST_SHARE_ID")
    private Integer documentComponentId;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public Integer getBudgetVersionNumber() {
        return this.budgetVersionNumber;
    }
    
    public Integer getDocumentComponentId() {
        return this.documentComponentId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BudgetDistributionAndIncomeComponentId)) return false;
        if (obj == null) return false;
        BudgetDistributionAndIncomeComponentId other = (BudgetDistributionAndIncomeComponentId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               ObjectUtils.equals(budgetVersionNumber, other.budgetVersionNumber) &&
               ObjectUtils.equals(documentComponentId, other.documentComponentId);
    }
    
    protected HashCodeBuilder getHashCodeBuilder() {
        return new HashCodeBuilder().append(proposalNumber).append(budgetVersionNumber).append(documentComponentId);
    }
}
