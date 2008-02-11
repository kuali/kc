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
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.YnqConstants;

public class ProposalYnq extends KraPersistableBusinessObjectBase {

	private String proposalNumber;
	private String questionId;
	private String answer;
	private String explanation;
	private Date reviewDate;
    private Ynq ynq;
    private String dummyAnswer;
    private boolean explanationRequried = true;
    private boolean reviewDateRequired = true;
    private String explanationRequiredDescription;
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
}
