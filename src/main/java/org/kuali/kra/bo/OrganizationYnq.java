package org.kuali.kra.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;
import java.sql.Date;

@IdClass(org.kuali.kra.bo.id.OrganizationYnqId.class)
@Entity
@Table(name="ORGANIZATION_YNQ")
public class OrganizationYnq extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="ORGANIZATION_ID")
	private String organizationId;
	@Id
	@Column(name="QUESTION_ID")
	private String questionId;
	@Column(name="ANSWER")
	private String answer;
	@Column(name="EXPLANATION")
	private String explanation;
	@Column(name="REVIEW_DATE")
	private Date reviewDate;
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization organization;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="QUESTION_ID", insertable=false, updatable=false)
	private Ynq ynq;

	public OrganizationYnq(){
		super();
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("questionId", getQuestionId());
		hashMap.put("answer", getAnswer());
		hashMap.put("explanation", getExplanation());
		hashMap.put("reviewDate", getReviewDate());
		return hashMap;
	}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Ynq getYnq() {
        return ynq;
    }

    public void setYnq(Ynq ynq) {
        this.ynq = ynq;
    }
}

