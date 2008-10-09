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

import edu.mit.coeus.budget.bean.BudgetSubAwardAttachmentBean;
import edu.mit.coeus.budget.bean.BudgetSubAwardBean;

@IdClass(org.kuali.kra.budget.bo.id.BudgetSubAwardFilesId.class)
@Entity
@Table(name="BUDGET_SUB_AWARD_FILES")
public class BudgetSubAwardFiles extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	@Id
    @Column(name="VERSION_NUMBER")
    private Integer budgetVersionNumber;
	@Id
	@Column(name="SUB_AWARD_NUMBER")
	private Integer subAwardNumber;
	@Column(name="SUB_AWARD_XFD_FILE")
	private byte[] subAwardXfdFileData;
	@Column(name="SUB_AWARD_XFD_FILE_NAME")
	private String subAwardXfdFileName;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="SUB_AWARD_XML_FILE")
	private String subAwardXmlFileData;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="VERSION_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="SUB_AWARD_NUMBER", insertable=false, updatable=false)})
    private BudgetSubAwards budgetSubAwards;
	
	

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("subAwardNumber", getSubAwardNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("updateTimestamp", this.getUpdateTimestamp());
        hashMap.put("updateUser", this.getUpdateUser());
		return hashMap;
	}


    /**
     * Gets the proposalNumber attribute. 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }


    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }


    /**
     * Gets the subAwardNumber attribute. 
     * @return Returns the subAwardNumber.
     */
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }


    /**
     * Sets the subAwardNumber attribute value.
     * @param subAwardNumber The subAwardNumber to set.
     */
    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }


    /**
     * Gets the budgetVersionNumber attribute. 
     * @return Returns the budgetVersionNumber.
     */
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }


    /**
     * Sets the budgetVersionNumber attribute value.
     * @param budgetVersionNumber The budgetVersionNumber to set.
     */
    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }


    /**
     * Gets the subAwardXfdFileData attribute. 
     * @return Returns the subAwardXfdFileData.
     */
    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }


    /**
     * Sets the subAwardXfdFileData attribute value.
     * @param subAwardXfdFileData The subAwardXfdFileData to set.
     */
    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }


    /**
     * Gets the subAwardXfdFileName attribute. 
     * @return Returns the subAwardXfdFileName.
     */
    public String getSubAwardXfdFileName() {
        return subAwardXfdFileName;
    }


    /**
     * Sets the subAwardXfdFileName attribute value.
     * @param subAwardXfdFileName The subAwardXfdFileName to set.
     */
    public void setSubAwardXfdFileName(String subAwardXfdFileName) {
        this.subAwardXfdFileName = subAwardXfdFileName;
    }


    /**
     * Gets the subAwardXmlFileData attribute. 
     * @return Returns the subAwardXmlFileData.
     */
    public String getSubAwardXmlFileData() {
        return subAwardXmlFileData;
    }


    /**
     * Sets the subAwardXmlFileData attribute value.
     * @param subAwardXmlFileData The subAwardXmlFileData to set.
     */
    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        this.subAwardXmlFileData = subAwardXmlFileData;
    }

    public BudgetSubAwards getBudgetSubAwards() {
        return budgetSubAwards;
    }

    public void setBudgetSubAwards(BudgetSubAwards budgetSubAwards) {
        this.budgetSubAwards = budgetSubAwards;
    }
}

