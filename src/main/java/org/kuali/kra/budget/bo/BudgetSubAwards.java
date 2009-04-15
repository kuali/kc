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
package org.kuali.kra.budget.bo;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import edu.mit.coeus.budget.bean.BudgetSubAwardAttachmentBean;
import edu.mit.coeus.budget.bean.BudgetSubAwardBean;

public class BudgetSubAwards extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer subAwardNumber;
	private Integer budgetVersionNumber;
	private String comments;
	private String organizationName;
	private Integer subAwardStatusCode;
	private byte[] subAwardXfdFileData;
	private String subAwardXfdFileName;
	private String subAwardXmlFileData;
	private String translationComments;
	private Timestamp xfdUpdateTimestamp;
	private String xfdUpdateUser;
	private Timestamp xmlUpdateTimestamp;
	private String xmlUpdateUser;
	private List<BudgetSubAwardAttachment> budgetSubAwardAttachments;
    private List<BudgetSubAwardFiles> budgetSubAwardFiles;

//	private transient FormFile subAwardXfdFile;
//	private transient FormFile subAwardXmlFile;
	
    
    public BudgetSubAwards(){
        budgetSubAwardAttachments = new ArrayList<BudgetSubAwardAttachment>();
        budgetSubAwardFiles = new ArrayList<BudgetSubAwardFiles>();
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


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
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
        if(budgetSubAwardAttachments == null) {
            budgetSubAwardAttachments = new ArrayList<BudgetSubAwardAttachment>();
        }
        return budgetSubAwardAttachments;
    }

    public String getAttachmentContentIds() {
        final String SEPARATOR = "; ";
        StringBuilder sb = new StringBuilder();
        for (BudgetSubAwardAttachment attachment: getBudgetSubAwardAttachments()) {
            sb.append(attachment.getContentId());
            sb.append(SEPARATOR);
        }
        sb.deleteCharAt(sb.length() - 2);
        return sb.toString();
    }
    
    /**
     * Sets the budgetSubAwardAttachments attribute value.
     * @param budgetSubAwardAttachments The budgetSubAwardAttachments to set.
     */
    public void setBudgetSubAwardAttachments(List<BudgetSubAwardAttachment> budgetSubAwardAttachments) {
        this.budgetSubAwardAttachments = budgetSubAwardAttachments;
    }

//    public FormFile getSubAwardXfdFile() {
//        return subAwardXfdFile;
//    }
//
//    public void setSubAwardXfdFile(FormFile subAwardXfdFile) throws Exception {
//        this.subAwardXfdFile = subAwardXfdFile;
//        if(subAwardXfdFile != null && subAwardXfdFile.getFileData().length > 0) {
//            setSubAwardXfdFileData(subAwardXfdFile.getFileData());
//            setSubAwardXfdFileName(subAwardXfdFile.getFileName());
//            parseDataFromXfdFile();
//        }
//    }

    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }

    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }

    public String getSubAwardXmlFileData() {
        return subAwardXmlFileData;
    }

    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        this.subAwardXmlFileData = subAwardXmlFileData;
    }
    
//    @SuppressWarnings("unchecked")
//    private void parseDataFromXfdFile() throws Exception {
//        BudgetSubAwardBean budgetSubAwardBean = new BudgetSubAwardBean();
//        budgetSubAwardBean.setSubAwardXFD(getSubAwardXfdFileData());
//        new BudgetSubAwardReader().populateSubAward(budgetSubAwardBean);
//        
//        setSubAwardStatusCode(budgetSubAwardBean.getSubAwardStatusCode());
//        setSubAwardXmlFileData(new String(budgetSubAwardBean.getSubAwardXML()));
//        setTranslationComments(budgetSubAwardBean.getTranslationComments());
//        setSubAwardStatusCode(budgetSubAwardBean.getSubAwardStatusCode());
//        
//        parseAttachmentDataFromBudgetSubAwardBean(budgetSubAwardBean);
//    }
    
//    @SuppressWarnings("unchecked")
//    private void parseAttachmentDataFromBudgetSubAwardBean(BudgetSubAwardBean budgetSubAwardBean) {
//        List<BudgetSubAwardAttachmentBean> budgetSubAwardBeanAttachments = (List<BudgetSubAwardAttachmentBean>) budgetSubAwardBean.getAttachments();
//        List<BudgetSubAwardAttachment> budgetSubAwardAttachments =  new ArrayList<BudgetSubAwardAttachment>();
//        
//        for(BudgetSubAwardAttachmentBean budgetSubAwardAttachmentBean: budgetSubAwardBeanAttachments) {
//            budgetSubAwardAttachments.add(new BudgetSubAwardAttachment(budgetSubAwardAttachmentBean, getBudgetVersionNumber(), getSubAwardNumber()));            
//        }
//        
//        setBudgetSubAwardAttachments(budgetSubAwardAttachments);
//    }
    /**
     * Gets the budgetSubAwardFiles attribute. 
     * @return Returns the budgetSubAwardFiles.
     */
    public List<BudgetSubAwardFiles> getBudgetSubAwardFiles() {
        return budgetSubAwardFiles;
    }
    /**
     * Sets the budgetSubAwardFiles attribute value.
     * @param budgetSubAwardFiles The budgetSubAwardFiles to set.
     */
    public void setBudgetSubAwardFiles(List<BudgetSubAwardFiles> budgetSubAwardFiles) {
        this.budgetSubAwardFiles = budgetSubAwardFiles;
    }
    
}
