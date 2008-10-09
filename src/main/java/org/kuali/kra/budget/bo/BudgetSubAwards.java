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

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

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
import org.kuali.kra.budget.document.BudgetDocument;

import edu.mit.coeus.budget.bean.BudgetSubAwardAttachmentBean;
import edu.mit.coeus.budget.bean.BudgetSubAwardBean;

@IdClass(org.kuali.kra.budget.bo.id.BudgetSubAwardsId.class)
@Entity
@Table(name="BUDGET_SUB_AWARDS")
public class BudgetSubAwards extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	@Id
    @Column(name="VERSION_NUMBER")
    private Integer budgetVersionNumber;
	@Id
	@Column(name="SUB_AWARD_NUMBER")
	private Integer subAwardNumber;
	@Column(name="COMMENTS")
	private String comments;
	@Column(name="ORGANIZATION_NAME")
	private String organizationName;
	@Column(name="SUB_AWARD_STATUS_CODE")
	private Integer subAwardStatusCode;
	@Column(name="SUB_AWARD_XFD_FILE")
	private byte[] subAwardXfdFileData;
	@Column(name="SUB_AWARD_XFD_FILE_NAME")
	private String subAwardXfdFileName;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="SUB_AWARD_XML_FILE")
	private String subAwardXmlFileData;
	@Column(name="TRANSLATION_COMMENTS")
	private String translationComments;
	@Column(name="XFD_UPDATE_TIMESTAMP")
	private Timestamp xfdUpdateTimestamp;
	@Column(name="XFD_UPDATE_USER")
	private String xfdUpdateUser;
	@Column(name="XML_UPDATE_TIMESTAMP")
	private Timestamp xmlUpdateTimestamp;
	@Column(name="XML_UPDATE_USER")
	private String xmlUpdateUser;
	
	@OneToMany(
           targetEntity=org.kuali.kra.budget.bo.BudgetSubAwardAttachment.class, mappedBy="budgetSubAwards")
	private List<BudgetSubAwardAttachment> budgetSubAwardAttachments;
	
    @OneToMany(
           targetEntity=org.kuali.kra.budget.bo.BudgetSubAwardFiles.class, mappedBy="budgetSubAwards")
	private List<BudgetSubAwardFiles> budgetSubAwardFiles;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="VERSION_NUMBER", insertable = false, updatable = false)})
    private BudgetDocument budgetDocument;

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
    
    public BudgetDocument getBudgetDocument() {
        return budgetDocument;
    }
    
    public void setBudgetDocument(BudgetDocument budgetDocument) {
        this.budgetDocument = budgetDocument;
    }
}

