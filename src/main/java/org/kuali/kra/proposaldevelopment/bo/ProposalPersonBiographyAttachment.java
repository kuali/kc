/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
public class ProposalPersonBiographyAttachment extends AttachmentDataSource {

	private Integer proposalPersonNumber;
    private String proposalNumber;
    private Integer biographyNumber;
	private byte[] biographyData;
//	private String fileName;
//    private String contentType;

	public ProposalPersonBiographyAttachment(){
		super();
	}

	public Integer getProposalPersonNumber() {
		return proposalPersonNumber;
	}

	public void setProposalPersonNumber(Integer proposalPersonNumber) {
		this.proposalPersonNumber = proposalPersonNumber;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public byte[] getBiographyData() {
		return biographyData;
	}

	public void setBiographyData(byte[] biographyData) {
		this.biographyData = biographyData;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalPersonNumber", getProposalPersonNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		//hashMap.put("bioAttachment", getBioAttachment());
		hashMap.put("fileName", getFileName());
		return hashMap;
	}

    @Override
    public byte[] getContent() {
        return biographyData;
    }

    public Integer getBiographyNumber() {
        return biographyNumber;
    }

    public void setBiographyNumber(Integer biographyNumber) {
        this.biographyNumber = biographyNumber;
    }

}
