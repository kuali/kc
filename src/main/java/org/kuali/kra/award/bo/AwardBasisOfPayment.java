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
package org.kuali.kra.award.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;

public class AwardBasisOfPayment extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5594710065439322293L;
    private String basisOfPaymentCode; 
	private String description; 
	
//	private ValidAwardBasisPayment validAwardBasisPayment; 
//	private ValidBasisMethodPmt validBasisMethodPmt; 
	
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

//	public ValidAwardBasisPayment getValidAwardBasisPayment() {
//		return validAwardBasisPayment;
//	}
//
//	public void setValidAwardBasisPayment(ValidAwardBasisPayment validAwardBasisPayment) {
//		this.validAwardBasisPayment = validAwardBasisPayment;
//	}
//
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
		hashMap.put("basisOfPaymentCode", getBasisOfPaymentCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
	
}