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
package org.kuali.coeus.propdev.impl.hierarchy;

import java.io.Serializable;



public class HierarchyProposalSummary implements Serializable {

    private static final long serialVersionUID = -4513320772280178341L;

    private String proposalNumber;
    private Boolean synced;
    private String syncableBudgetDocumentNumber;
    private Boolean budgetSynced;

    /**
     * Gets the proposalNumber attribute.
     * 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Sets the proposalNumber attribute value.
     * 
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Sets the synced attribute value.
     * 
     * @param synced The synced to set.
     */
    public void setSynced(Boolean synced) {
        this.synced = synced;
    }

    /**
     * Gets the synced attribute.
     * 
     * @return Returns the synced.
     */
    public Boolean getSynced() {
        return synced;
    }

    public Boolean getBudgetSynced() {
        return budgetSynced;
    }

    public void setBudgetSynced(Boolean budgetSynced) {
        this.budgetSynced = budgetSynced;
    }

    public String getSyncableBudgetDocumentNumber() {
        return syncableBudgetDocumentNumber;
    }

    public void setSyncableBudgetDocumentNumber(String syncableBudgetDocumentNumber) {
        this.syncableBudgetDocumentNumber = syncableBudgetDocumentNumber;
    }
}
