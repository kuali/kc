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
package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;

public class BudgetSubAwards extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer subAwardNumber;
	private Integer budgetVersionNumber;
	private String comments;
	private String organizationName;
	private Integer subAwardStatusCode;
	transient private FormFile subAwardXfdFile;
	private byte[] subAwardXfdFileData;
	private String subAwardXfdFileName;
	transient private FormFile subAwardXmlFile;
	private String translationComments;
	private Timestamp xfdUpdateTimestamp;
	private String xfdUpdateUser;
	private Timestamp xmlUpdateTimestamp;
	private String xmlUpdateUser;
	private List<BudgetSubAwardAttachment> budgetSubAwardAttachments;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getSubAwardStatusCode() {
		return subAwardStatusCode;
	}

	public void setSubAwardStatusCode(Integer subAwardStatusCode) {
		this.subAwardStatusCode = subAwardStatusCode;
	}

	public String getSubAwardXfdFileName() {
		return subAwardXfdFileName;
	}

	public void setSubAwardXfdFileName(String subAwardXfdFileName) {
		this.subAwardXfdFileName = subAwardXfdFileName;
	}

	public String getTranslationComments() {
		return translationComments;
	}

	public void setTranslationComments(String translationComments) {
		this.translationComments = translationComments;
	}

	public Timestamp getXfdUpdateTimestamp() {
		return xfdUpdateTimestamp;
	}

	public void setXfdUpdateTimestamp(Timestamp xfdUpdateTimestamp) {
		this.xfdUpdateTimestamp = xfdUpdateTimestamp;
	}

	public String getXfdUpdateUser() {
		return xfdUpdateUser;
	}

	public void setXfdUpdateUser(String xfdUpdateUser) {
		this.xfdUpdateUser = xfdUpdateUser;
	}

	public Timestamp getXmlUpdateTimestamp() {
		return xmlUpdateTimestamp;
	}

	public void setXmlUpdateTimestamp(Timestamp xmlUpdateTimestamp) {
		this.xmlUpdateTimestamp = xmlUpdateTimestamp;
	}

	public String getXmlUpdateUser() {
		return xmlUpdateUser;
	}

	public void setXmlUpdateUser(String xmlUpdateUser) {
		this.xmlUpdateUser = xmlUpdateUser;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("subAwardNumber", getSubAwardNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("comments", getComments());
		hashMap.put("organizationName", getOrganizationName());
		hashMap.put("subAwardStatusCode", getSubAwardStatusCode());		
		hashMap.put("subAwardXfdFileName", getSubAwardXfdFileName());		
		hashMap.put("translationComments", getTranslationComments());
		hashMap.put("xfdUpdateTimestamp", getXfdUpdateTimestamp());
		hashMap.put("xfdUpdateUser", getXfdUpdateUser());
		hashMap.put("xmlUpdateTimestamp", getXmlUpdateTimestamp());
		hashMap.put("xmlUpdateUser", getXmlUpdateUser());
		hashMap.put("updateTimestamp", this.getUpdateTimestamp());
        hashMap.put("updateUser", this.getUpdateUser());
		return hashMap;
	}

    /**
     * Gets the budgetSubAwardAttachments attribute. 
     * @return Returns the budgetSubAwardAttachments.
     */
    public List<BudgetSubAwardAttachment> getBudgetSubAwardAttachments() {
        return budgetSubAwardAttachments;
    }

    /**
     * Sets the budgetSubAwardAttachments attribute value.
     * @param budgetSubAwardAttachments The budgetSubAwardAttachments to set.
     */
    public void setBudgetSubAwardAttachments(List<BudgetSubAwardAttachment> budgetSubAwardAttachments) {
        this.budgetSubAwardAttachments = budgetSubAwardAttachments;
    }

    public FormFile getSubAwardXfdFile() {
        return subAwardXfdFile;
    }

    public void setSubAwardXfdFile(FormFile subAwardXfdFile) {
        this.subAwardXfdFile = subAwardXfdFile;
    }

    public FormFile getSubAwardXmlFile() {
        return subAwardXmlFile;
    }

    public void setSubAwardXmlFile(FormFile subAwardXmlFile) {
        this.subAwardXmlFile = subAwardXmlFile;
    }

    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }

    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }
}
