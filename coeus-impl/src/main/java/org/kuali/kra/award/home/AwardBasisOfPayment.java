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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.util.List;

public class AwardBasisOfPayment extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 5594710065439322293L;

    private String basisOfPaymentCode;

    private String description;

    private List<ValidBasisMethodPayment> validBasisMethodPayments;

    public AwardBasisOfPayment() {
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the validBasisMethodPayments attribute. 
     * @return Returns the validBasisMethodPayments.
     */
    public List<ValidBasisMethodPayment> getValidBasisMethodPayments() {
        return validBasisMethodPayments;
    }

    /**
     * Sets the validBasisMethodPayments attribute value.
     * @param validBasisMethodPayments The validBasisMethodPayments to set.
     */
    public void setValidBasisMethodPayments(List<ValidBasisMethodPayment> validBasisMethodPayments) {
        this.validBasisMethodPayments = validBasisMethodPayments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((basisOfPaymentCode == null) ? 0 : basisOfPaymentCode.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((validBasisMethodPayments == null) ? 0 : validBasisMethodPayments.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AwardBasisOfPayment other = (AwardBasisOfPayment) obj;
        if (basisOfPaymentCode == null) {
            if (other.basisOfPaymentCode != null) return false;
        } else if (!basisOfPaymentCode.equals(other.basisOfPaymentCode)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (validBasisMethodPayments == null) {
            if (other.validBasisMethodPayments != null) return false;
        } else if (!validBasisMethodPayments.equals(other.validBasisMethodPayments)) return false;
        return true;
    }
}
