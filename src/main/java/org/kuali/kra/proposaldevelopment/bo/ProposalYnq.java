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
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Transient;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.YnqConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalYnqId.class)
@Entity
@Table(name="EPS_PROP_YNQ")
public class ProposalYnq extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Id
	@Column(name="QUESTION_ID")
	private String questionId;
	
	@Column(name="ANSWER")
	private String answer;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="EXPLANATION")
	private String explanation;
	
	@Column(name="REVIEW_DATE")
	private Date reviewDate;
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="QUESTION_ID", insertable=false, updatable=false)
	private Ynq ynq;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;
    
    @Transient
    private String dummyAnswer;
    
    @Transient
    private boolean explanationRequried = true;
    
    @Transient
    private boolean reviewDateRequired = true;
    
    @Transient
    private String explanationRequiredDescription;
    
    @Transient
    private String reviewDateRequiredDescription;
    

    public Ynq getYnq() {
        return ynq;
    }

    public void setYnq(Ynq ynq) {
        this.ynq = ynq;
    }

    public ProposalYnq(){
		super();
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("questionId", getQuestionId());
		hashMap.put("answer", getAnswer());
		hashMap.put("explanation", getExplanation());
		hashMap.put("reviewDate", getReviewDate());
		return hashMap;
	}

    public String getDummyAnswer() {
        return dummyAnswer;
    }

    public void setDummyAnswer(String dummyAnswer) {
        this.dummyAnswer = dummyAnswer;
    }

    public boolean getExplanationRequried() {
        return explanationRequried;
    }

    public void setExplanationRequried(boolean explanationRequried) {
        this.explanationRequried = explanationRequried;
    }

    public boolean getReviewDateRequired() {
        return reviewDateRequired;
    }

    public void setReviewDateRequired(boolean reviewDateRequired) {
        this.reviewDateRequired = reviewDateRequired;
    }

    public String getExplanationRequiredDescription() {
        return explanationRequiredDescription;
    }

    public void setExplanationRequiredDescription(String explanationRequiredDescription) {
        this.explanationRequiredDescription = explanationRequiredDescription;
    }

    public String getReviewDateRequiredDescription() {
        return reviewDateRequiredDescription;
    }

    public void setReviewDateRequiredDescription(String reviewDateRequiredDescription) {
        this.reviewDateRequiredDescription = reviewDateRequiredDescription;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
}

