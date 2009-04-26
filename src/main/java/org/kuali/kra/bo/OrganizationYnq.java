package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

public class OrganizationYnq extends KraPersistableBusinessObjectBase {

	private String organizationId;
	private String questionId;
	private String answer;
	private String explanation;
	private Date reviewDate;
    private Organization organization;
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
