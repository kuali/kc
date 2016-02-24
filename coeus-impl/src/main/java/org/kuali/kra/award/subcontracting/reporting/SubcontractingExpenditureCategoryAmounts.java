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
package org.kuali.kra.award.subcontracting.reporting;

public class SubcontractingExpenditureCategoryAmounts extends SubcontractingExpenditureCategoryAmountsBase {


    private static final long serialVersionUID = 2914392759850816282L;
    
    // transient 'fresh' flag to indicate if this BO represents an existing record or freshly created
    private transient boolean fresh = false;
    
    public SubcontractingExpenditureCategoryAmounts() {
        super();
    }
    
    public SubcontractingExpenditureCategoryAmounts(String awardNumber) {
        this();
        // set the PK
        this.setAwardNumber(awardNumber);
        // set the transient fresh flag
        this.setFresh(true);
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public boolean isFresh() {
        return fresh;
    }

}
