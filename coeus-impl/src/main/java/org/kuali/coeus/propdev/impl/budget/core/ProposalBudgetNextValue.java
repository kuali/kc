/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
