package org.kuali.coeus.propdev.impl.budget.core;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.kra.bo.NextValueBase;

@Entity
@Table(name = "DOCUMENT_NEXTVALUE")
@DiscriminatorValue("PROPOSAL_BUDGET_NEXT_VALUE")
public class ProposalBudgetNextValue extends NextValueBase<ProposalDevelopmentBudgetExt> {

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "OBJ_ID")
	private ProposalDevelopmentBudgetExt parentObject;

    @Override
	public ProposalDevelopmentBudgetExt getParentObject() {
		return parentObject;
	}

	public void setParentObject(ProposalDevelopmentBudgetExt parentObject) {
		this.parentObject = parentObject;
	}
}
