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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 * This is BO class for person training maintenance.
 */
@Entity 
@Table(name="PERSON_TRAINING")
public class PersonTraining extends KraPersistableBusinessObjectBase { 
	
	@Id 
	@Column(name="PERSON_TRAINING_ID")
	private Integer personTrainingId; 
	@Column(name="PERSON_ID")
	private String personId; 
	@Column(name="TRAINING_NUMBER")
	private Integer trainingNumber; 
	@Column(name="TRAINING_CODE")
	private Integer trainingCode; 
	@Column(name="DATE_REQUESTED")
	private Date dateRequested; 
	@Column(name="DATE_SUBMITTED")
	private Date dateSubmitted; 
	@Column(name="DATE_ACKNOWLEDGED")
	private Date dateAcknowledged; 
	@Column(name="FOLLOWUP_DATE")
	private Date followupDate; 
	@Column(name="SCORE")
	private String score; 
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="COMMENTS")
	private String comments; 
	
	private Person person;
	private Training training;
		
	public PersonTraining() { 

	} 
	
	public Integer getPersonTrainingId() {
		return personTrainingId;
	}

	public void setPersonTrainingId(Integer personTrainingId) {
		this.personTrainingId = personTrainingId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Integer getTrainingNumber() {
		return trainingNumber;
	}

	public void setTrainingNumber(Integer trainingNumber) {
		this.trainingNumber = trainingNumber;
	}

	public Integer getTrainingCode() {
		return trainingCode;
	}

	public void setTrainingCode(Integer trainingCode) {
		this.trainingCode = trainingCode;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Date getDateAcknowledged() {
		return dateAcknowledged;
	}

	public void setDateAcknowledged(Date dateAcknowledged) {
		this.dateAcknowledged = dateAcknowledged;
	}

	public Date getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("personTrainingId", getPersonTrainingId());
		hashMap.put("personId", getPersonId());
		hashMap.put("trainingNumber", getTrainingNumber());
		hashMap.put("trainingCode", getTrainingCode());
		hashMap.put("dateRequested", getDateRequested());
		hashMap.put("dateSubmitted", getDateSubmitted());
		hashMap.put("dateAcknowledged", getDateAcknowledged());
		hashMap.put("followupDate", getFollowupDate());
		hashMap.put("score", getScore());
		hashMap.put("comments", getComments());
		return hashMap;
	}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
	
}
