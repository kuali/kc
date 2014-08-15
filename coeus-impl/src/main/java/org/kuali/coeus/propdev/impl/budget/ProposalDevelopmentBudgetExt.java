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
package org.kuali.coeus.propdev.impl.budget;

import javax.persistence.*;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

/**
 * This class is just to hold the ProposalDevelopmentBudget. We should move PD Budget stuffs to this class later. 
 * Right now this is being used to represent extension class for Budget in repository.
 */
@Entity
@Table(name = "EPS_PROPOSAL_BUDGET_EXT")
@PrimaryKeyJoinColumn(name="BUDGET_ID", referencedColumnName="BUDGET_ID")
@DiscriminatorValue("PRDV")
public class ProposalDevelopmentBudgetExt extends Budget implements ProposalDevelopmentBudgetExtContract {

    private static final long serialVersionUID = 8234453927894053540L;

    @Id
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
    private DevelopmentProposal developmentProposal;
    
    @Column(name = "STATUS_CODE")
    private String budgetStatus;
    
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "STATUS_CODE", referencedColumnName = "BUDGET_STATUS_CODE", insertable = false, updatable = false)
    private BudgetStatus budgetStatusDo;
    
    @Column(name = "HIERARCHY_HASH_CODE")
    private Integer hierarchyLastSyncHashCode;

    public Integer getHierarchyLastSyncHashCode() {
        return hierarchyLastSyncHashCode;
    }

    public void setHierarchyLastSyncHashCode(Integer hierarchyLastSyncHashCode) {
        this.hierarchyLastSyncHashCode = hierarchyLastSyncHashCode;
    }

	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}

	@Override
	public DevelopmentProposal getBudgetParent() {
		return developmentProposal;
	}

	@Override
	public String getParentDocumentKey() {
		return developmentProposal.getProposalNumber();
	}
	
    public java.util.Date getBudgetStartDate() {
        return getDevelopmentProposal().getRequestedStartDateInitial();
    }

    public java.util.Date getBudgetEndDate() {
        return getDevelopmentProposal().getRequestedEndDateInitial();

    }

	public String getBudgetStatus() {
		return budgetStatus;
	}

	public void setBudgetStatus(String budgetStatus) {
		this.budgetStatus = budgetStatus;
	}

	public BudgetStatus getBudgetStatusDo() {
		return budgetStatusDo;
	}

	public void setBudgetStatusDo(BudgetStatus budgetStatusDo) {
		this.budgetStatusDo = budgetStatusDo;
	}
	
	public boolean isSummaryBudget() {
		for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
			if (!budgetPeriod.getBudgetLineItems().isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
