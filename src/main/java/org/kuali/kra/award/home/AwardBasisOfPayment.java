/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.home;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardBasisOfPayment extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5594710065439322293L;
    private String basisOfPaymentCode; 
	private String description; 
	
//	private List<ValidAwardBasisPayment> validAwardBasisPayments; 
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

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("basisOfPaymentCode", getBasisOfPaymentCode());
		hashMap.put("description", getDescription());
		return hashMap;
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((basisOfPaymentCode == null) ? 0 : basisOfPaymentCode.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((validBasisMethodPayments == null) ? 0 : validBasisMethodPayments.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardBasisOfPayment other = (AwardBasisOfPayment) obj;
        if (basisOfPaymentCode == null) {
            if (other.basisOfPaymentCode != null)
                return false;
        }
        else if (!basisOfPaymentCode.equals(other.basisOfPaymentCode))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (validBasisMethodPayments == null) {
            if (other.validBasisMethodPayments != null)
                return false;
        }
        else if (!validBasisMethodPayments.equals(other.validBasisMethodPayments))
            return false;
        return true;
    }
	
}