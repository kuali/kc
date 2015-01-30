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
package org.kuali.kra.timeandmoney.transactions;

import org.kuali.kra.award.AwardAssociate;

public class AwardTransactionType extends AwardAssociate {

    private static final long serialVersionUID = 1L;

    private Integer awardTransactionTypeCode;

    private String description;

    private boolean showInActionSummary;

    public AwardTransactionType() {
    }

    public Integer getAwardTransactionTypeCode() {
        return awardTransactionTypeCode;
    }

    public void setAwardTransactionTypeCode(Integer awardTransactionTypeCode) {
        this.awardTransactionTypeCode = awardTransactionTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getShowInActionSummary() {
        return showInActionSummary;
    }

    public void setShowInActionSummary(boolean showInActionSummary) {
        this.showInActionSummary = showInActionSummary;
    }

    public void resetPersistenceState() {
    }
}
