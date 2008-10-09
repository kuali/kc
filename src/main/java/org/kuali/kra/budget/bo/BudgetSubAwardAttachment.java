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

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import edu.mit.coeus.budget.bean.BudgetSubAwardAttachmentBean;

@IdClass(org.kuali.kra.budget.bo.id.BudgetSubAwardAttachmentId.class)
@Entity
@Table(name="BUDGET_SUB_AWARD_ATT")
public class BudgetSubAwardAttachment extends KraPersistableBusinessObjectBase {
    
    @Id
    @Column(name="CONTENT_ID")
    private String contentId;
    
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Id
    @Column(name="SUB_AWARD_NUMBER")
    private Integer subAwardNumber;
    
    @Id
    @Column(name="VERSION_NUMBER")
    private Integer budgetVersionNumber;
    
    @Column(name="ATTACHMENT")
	private byte[] attachment;
    
    @Column(name="CONTENT_TYPE")
	private String contentType;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false), 
                  @JoinColumn(name="VERSION_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="SUB_AWARD_NUMBER", insertable=false, updatable=false)})
    private BudgetSubAwards budgetSubAwards;
	
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
	
	public BudgetSubAwards getBudgetSubAwards() {
        return budgetSubAwards;
    }

    public void setBudgetSubAwards(BudgetSubAwards budgetSubAwards) {
        this.budgetSubAwards = budgetSubAwards;
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

