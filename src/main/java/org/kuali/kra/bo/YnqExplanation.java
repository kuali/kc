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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@IdClass(org.kuali.kra.bo.id.YnqExplanationId.class)
@Entity
@Table(name="YNQ_EXPLANATION")
public class YnqExplanation extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="EXPLANATION_TYPE")
	private String explanationType;
	
	@Id
	@Column(name="QUESTION_ID")
	private String questionId; 
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="EXPLANATION")
	private String explanation; 
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="EXPLANATION_TYPE", insertable=false, updatable=false)
	private YnqExplanationType ynqExplanationType;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name="QUESTION_ID", insertable=false, updatable=false)
	private Ynq question;

	public YnqExplanation(){
		super();
	}

	public String getExplanationType() {
		return explanationType;
	}

	public void setExplanationType(String explanationType) {
		this.explanationType = explanationType;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Ynq getQuestion() {
        return question;
    }

    public void setQuestion(Ynq question) {
        this.question = question;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("explanationType", getExplanationType());
		hashMap.put("questionId", getQuestionId());
		hashMap.put("explanation", getExplanation());
		return hashMap;
	}

    public YnqExplanationType getYnqExplanationType() {
        return ynqExplanationType;
    }

    public void setYnqExplanationType(YnqExplanationType ynqExplanationType) {
        this.ynqExplanationType = ynqExplanationType;
    }

}

