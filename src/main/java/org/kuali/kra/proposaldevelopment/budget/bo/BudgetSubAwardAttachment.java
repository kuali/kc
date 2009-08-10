/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.budget.core.BudgetAssociate;

public class BudgetSubAwardAttachment extends BudgetAssociate {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2467480179750426256L;
    private byte[] attachment;
    private String contentId;
    private String contentType;
	private Integer subAwardNumber;
	
	public BudgetSubAwardAttachment() {
	    super();
	}
	
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Integer getSubAwardNumber() {
		return subAwardNumber;
	}

	public void setSubAwardNumber(Integer subAwardNumber) {
		this.subAwardNumber = subAwardNumber;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
		hashMap.put("contentId", getContentId());
//		hashMap.put("proposalNumber", getProposalNumber());
//        hashMap.put("versionNumber", getBudgetVersionNumber());
		hashMap.put("subAwardNumber", getSubAwardNumber());
		hashMap.put("contentType", getContentType());
		return hashMap;
	}
}
