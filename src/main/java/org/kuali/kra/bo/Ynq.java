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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.util.TypedArrayList;

public class Ynq extends KraPersistableBusinessObjectBase {

	private String questionId;
	private String dateRequiredFor;
	private String description;
	private Date effectiveDate;
	private String explanationRequiredFor;
	private String groupName;
	private Integer noOfAnswers;
	private String questionType;
	private String status;
    private List<YnqExplanation> ynqExplanations;

	public Ynq(){
		super();
		ynqExplanations = new TypedArrayList(YnqExplanation.class);
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDateRequiredFor() {
		return dateRequiredFor;
	}

	public void setDateRequiredFor(String dateRequiredFor) {
		this.dateRequiredFor = dateRequiredFor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExplanationRequiredFor() {
		return explanationRequiredFor;
	}

	public void setExplanationRequiredFor(String explanationRequiredFor) {
		this.explanationRequiredFor = explanationRequiredFor;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getNoOfAnswers() {
		return noOfAnswers;
	}

	public void setNoOfAnswers(Integer noOfAnswers) {
		this.noOfAnswers = noOfAnswers;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("questionId", getQuestionId());
		hashMap.put("dateRequiredFor", getDateRequiredFor());
		hashMap.put("description", getDescription());
		hashMap.put("effectiveDate", getEffectiveDate());
		hashMap.put("explanationRequiredFor", getExplanationRequiredFor());
		hashMap.put("groupName", getGroupName());
		hashMap.put("noOfAnswers", getNoOfAnswers());
		hashMap.put("questionType", getQuestionType());
		hashMap.put("status", getStatus());
		return hashMap;
	}

    public List<YnqExplanation> getYnqExplanations() {
        return ynqExplanations;
    }

    public void setYnqExplanations(List<YnqExplanation> ynqExplanations) {
        this.ynqExplanations = ynqExplanations;
    }
    
}
