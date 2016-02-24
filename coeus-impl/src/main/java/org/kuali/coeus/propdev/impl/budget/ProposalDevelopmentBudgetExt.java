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
package org.kuali.coeus.propdev.impl.budget;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.bo.DocumentNextvalue;

@Entity
@Table(name = "EPS_PROPOSAL_BUDGET_EXT")
@PrimaryKeyJoinColumn(name="BUDGET_ID", referencedColumnName="BUDGET_ID")
@DiscriminatorValue(ProposalDevelopmentBudgetExt.PARENT_BUDGET_TYPE_CODE)
public class ProposalDevelopmentBudgetExt extends Budget implements ProposalDevelopmentBudgetExtContract {

    private static final long serialVersionUID = 8234453927894053540L;
    private static final String BUDGET_PERSON_GROUP_PD = "From Proposal Development";
    public static final String PARENT_BUDGET_TYPE_CODE = "PRDV";
    private static final String BUDGET_COMPLETE = "1";

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
    
    @OneToMany(orphanRemoval = false, cascade = { CascadeType.ALL })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "OBJ_ID")
    private List<DocumentNextvalue> nextValues;
    
    public ProposalDevelopmentBudgetExt() {
    	nextValues = new ArrayList<>();
    }

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

    public String getParentDocumentGroupName() {
    	return BUDGET_PERSON_GROUP_PD;
    }
	
    public java.util.Date getBudgetStartDate() {
        return getDevelopmentProposal().getRequestedStartDateInitial();
    }

    public java.util.Date getBudgetEndDate() {
        return getDevelopmentProposal().getRequestedEndDateInitial();
    }
    
    public boolean isBudgetComplete() {
        return BUDGET_COMPLETE.equals(getBudgetStatus());
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

	@Override
    public DocumentNextvalue getNewNextValue() {
    	return new DocumentNextvalue();
    }

	@Override
    public void add(DocumentNextvalue nextValue) {
    	if (StringUtils.isBlank(this.getObjectId())) {
			this.setObjectId(UUID.randomUUID().toString());
		}

		nextValue.setDocumentKey(this.getObjectId());
    	nextValues.add(nextValue);
    }

	@Override
	public List<DocumentNextvalue> getNextValues() {
		return nextValues;
	}

	public void setNextValues(List<DocumentNextvalue> nextValues) {
		this.nextValues = nextValues;
	}

}
