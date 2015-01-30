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
package org.kuali.kra.award.printing;

import org.kuali.rice.krad.bo.BusinessObjectBase;

public class AwardTransactionSelectorBean extends BusinessObjectBase {

    private Integer awardVersion;
    private Integer amountInfoIndex;
    private Boolean requireSignature;

    public void refresh() {
        //do nothing
    }

    public Integer getAwardVersion() {
        return awardVersion;
    }

    public void setAwardVersion(Integer awardVersion) {
        this.awardVersion = awardVersion;
    }

    public Boolean getRequireSignature() {
        return requireSignature;
    }

    public void setRequireSignature(Boolean requireSignature) {
        this.requireSignature = requireSignature;
    }

    public Integer getAmountInfoIndex() {
        return amountInfoIndex;
    }

    public void setAmountInfoIndex(Integer amountInfoIndex) {
        this.amountInfoIndex = amountInfoIndex;
    }
    

}
