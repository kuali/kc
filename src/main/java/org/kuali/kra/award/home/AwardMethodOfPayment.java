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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardMethodOfPayment extends KraPersistableBusinessObjectBase { 
	
	private String methodOfPaymentCode; 
	private String description; 
	
//	private ValidBasisMethodPmt validBasisMethodPmt; 
	
	public AwardMethodOfPayment() { 

	} 
	
	public String getMethodOfPaymentCode() {
		return methodOfPaymentCode;
	}

	public void setMethodOfPaymentCode(String methodOfPaymentCode) {
		this.methodOfPaymentCode = methodOfPaymentCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public ValidBasisMethodPmt getValidBasisMethodPmt() {
//		return validBasisMethodPmt;
//	}
//
//	public void setValidBasisMethodPmt(ValidBasisMethodPmt validBasisMethodPmt) {
//		this.validBasisMethodPmt = validBasisMethodPmt;
//	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("methodOfPaymentCode", getMethodOfPaymentCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((methodOfPaymentCode == null) ? 0 : methodOfPaymentCode.hashCode());
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
        AwardMethodOfPayment other = (AwardMethodOfPayment) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (methodOfPaymentCode == null) {
            if (other.methodOfPaymentCode != null)
                return false;
        }
        else if (!methodOfPaymentCode.equals(other.methodOfPaymentCode))
            return false;
        return true;
    }
	
}