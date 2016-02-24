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
package org.kuali.kra.award.home.approvedsubawards;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;

import java.io.Serializable;


public class ApprovedSubawardFormHelper implements Serializable {
    
    private AwardForm parent;
    
    private AwardApprovedSubaward newAwardApprovedSubaward;
    
    /**
     * Constructs a ApprovedSubawardFormHelper
     * @param parent
     */
    public ApprovedSubawardFormHelper(AwardForm parent) {
        this.parent = parent;
        this.newAwardApprovedSubaward = new AwardApprovedSubaward();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardApprovedSubaward = new AwardApprovedSubaward(); 
    }

    /**
     * Gets the newAwardApprovedSubaward attribute. 
     * @return Returns the newAwardApprovedSubaward.
     */
    public AwardApprovedSubaward getNewAwardApprovedSubaward() {
        return newAwardApprovedSubaward;
    }

    /**
     * Sets the newAwardApprovedSubaward attribute value.
     * @param newAwardApprovedSubaward The newAwardApprovedSubaward to set.
     */
    public void setNewAwardApprovedSubaward(AwardApprovedSubaward newAwardApprovedSubaward) {
        this.newAwardApprovedSubaward = newAwardApprovedSubaward;
    }

    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }

    public Object getData() {
        return getNewAwardApprovedSubaward();
    }
}

