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
	private Boolean available;
	private String formName;
	private Boolean include;
	private Boolean mandatory;
	private String schemaUrl;

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

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Boolean isInclude() {
		return include;
	}

	public void setInclude(Boolean include) {
		this.include = include;
	}

	public Boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("oppNameSpace", getOppNameSpace());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("available", isAvailable());
		hashMap.put("formName", getFormName());
		hashMap.put("include", isInclude());
		hashMap.put("mandatory", isMandatory());
        hashMap.put("schemaUrl", getSchemaUrl());
		return hashMap;
	}

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }
}
