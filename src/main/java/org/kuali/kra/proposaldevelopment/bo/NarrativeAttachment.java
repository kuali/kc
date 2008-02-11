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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;

public class NarrativeAttachment extends AttachmentDataSource{
	private Integer moduleNumber;
	private String proposalNumber;
//	private String fileName;
//	private String contentType;
    private byte[] narrativeData;

	public Integer getModuleNumber() {
		return moduleNumber;
	}

	public void setModuleNumber(Integer moduleNumber) {
		this.moduleNumber = moduleNumber;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public byte[] getNarrativeData() {
		return narrativeData;
	}

	public void setNarrativeData(byte[] narrativePdf) {
	    this.narrativeData = narrativePdf;
	}
	
	public byte[] getContent() {
	    return narrativeData;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("moduleNumber", getModuleNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("fileName", getFileName());
        hashMap.put("contentType", getContentType());
		return hashMap;
	}

}
