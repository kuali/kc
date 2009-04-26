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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetSubAwardAttachment extends KraPersistableBusinessObjectBase {
    private byte[] attachment;
    private Integer budgetVersionNumber;
    private String contentId;
    private String contentType;
    private String proposalNumber;
	private Integer subAwardNumber;
	
	public BudgetSubAwardAttachment() {
	    super();
	}
	
//	public BudgetSubAwardAttachment(BudgetSubAwardAttachmentBean bean, Integer budgetVersionNumber, Integer subAwardNumber) {
//	    this();
//	    setBudgetVersionNumber(budgetVersionNumber);
//	    setSubAwardNumber(subAwardNumber);
//	    
//	    setAttachment(bean.getAttachment());
//	    setContentId(bean.getContentId());
//	    setContentType(bean.getContentType());
//	}
	
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getSubAwardNumber() {
		return subAwardNumber;
	}

	public void setSubAwardNumber(Integer subAwardNumber) {
		this.subAwardNumber = subAwardNumber;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
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
	
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("contentId", getContentId());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("subAwardNumber", getSubAwardNumber());
		hashMap.put("versionNumber", getVersionNumber());
//		hashMap.put("attachment", new String(getAttachment()));
		hashMap.put("contentType", getContentType());
		return hashMap;
	}
}
