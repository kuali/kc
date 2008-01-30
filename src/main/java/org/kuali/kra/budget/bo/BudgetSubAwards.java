package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Timestamp;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetSubAwards extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer subAwardNumber;
	private Integer budgetVersionNumber;
	private String comments;
	private String organizationName;
	private Integer subAwardStatusCode;
	private String subAwardXfdFile;
	private String subAwardXfdFileName;
	private String subAwardXmlFile;
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

	public String getSubAwardXfdFile() {
		return subAwardXfdFile;
	}

	public void setSubAwardXfdFile(String subAwardXfdFile) {
		this.subAwardXfdFile = subAwardXfdFile;
	}

	public String getSubAwardXfdFileName() {
		return subAwardXfdFileName;
	}

	public void setSubAwardXfdFileName(String subAwardXfdFileName) {
		this.subAwardXfdFileName = subAwardXfdFileName;
	}

	public String getSubAwardXmlFile() {
		return subAwardXmlFile;
	}

	public void setSubAwardXmlFile(String subAwardXmlFile) {
		this.subAwardXmlFile = subAwardXmlFile;
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
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("comments", getComments());
		hashMap.put("organizationName", getOrganizationName());
		hashMap.put("subAwardStatusCode", getSubAwardStatusCode());
		hashMap.put("subAwardXfdFile", getSubAwardXfdFile());
		hashMap.put("subAwardXfdFileName", getSubAwardXfdFileName());
		hashMap.put("subAwardXmlFile", getSubAwardXmlFile());
		hashMap.put("translationComments", getTranslationComments());
		hashMap.put("xfdUpdateTimestamp", getXfdUpdateTimestamp());
		hashMap.put("xfdUpdateUser", getXfdUpdateUser());
		hashMap.put("xmlUpdateTimestamp", getXmlUpdateTimestamp());
		hashMap.put("xmlUpdateUser", getXmlUpdateUser());
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
}
