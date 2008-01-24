/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sOppForms extends KraPersistableBusinessObjectBase {
	private String oppNameSpace;
	private String proposalNumber;
	private String available;
	private String formName;
	private String include;
	private String mandatory;

	public String getOppNameSpace() {
		return oppNameSpace;
	}

	public void setOppNameSpace(String oppNameSpace) {
		this.oppNameSpace = oppNameSpace;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("oppNameSpace", getOppNameSpace());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("available", getAvailable());
		hashMap.put("formName", getFormName());
		hashMap.put("include", getInclude());
		hashMap.put("mandatory", getMandatory());
		return hashMap;
	}
}
