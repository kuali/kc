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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.util.TypedArrayList;

public class AwardTemplate extends AwardBase<AwardTemplateReportTerm,AwardTemplateComment,AwardTemplateTerm,AwardTemplateContact> { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3038831932003349194L;
    private String statusCode; 
    private String description;
    private AwardStatus awardTemplateStatus; 

	private List<AwardTemplateReportTerm> templateReportTerms; 
	private List<AwardTemplateComment> templateComments; 
	private List<AwardTemplateContact> templateContacts; 
    private List<AwardTemplateTerm> templateTerms; 
//	private Award award; 
	
	@SuppressWarnings("unchecked")
    public AwardTemplate() { 
	    templateContacts = new TypedArrayList(AwardTemplateContact.class);
	    templateComments = new TypedArrayList(AwardTemplateComment.class);
	    templateTerms = new TypedArrayList(AwardTemplateTerm.class);
	    templateReportTerms = new TypedArrayList(AwardTemplateReportTerm.class);
	} 
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public AwardStatus getAwardTemplateStatus() {
		return awardTemplateStatus;
	}

	public void setAwardTemplateStatus(AwardStatus awardTemplateStatus) {
		this.awardTemplateStatus = awardTemplateStatus;
	}

	public List<AwardTemplateReportTerm> getTemplateReportTerms() {
		return templateReportTerms;
	}

	public void setTemplateReportTerms(List<AwardTemplateReportTerm> templateReportTerms) {
		this.templateReportTerms = templateReportTerms;
	}


	public List<AwardTemplateContact> getTemplateContacts() {
		return templateContacts;
	}

	public void setTemplateContacts(List<AwardTemplateContact> templateContacts) {
		this.templateContacts = templateContacts;
	}


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("statusCode", getStatusCode());
		return hashMap;
	}


    /**
     * Gets the templateComments attribute. 
     * @return Returns the templateComments.
     */
    public List<AwardTemplateComment> getTemplateComments() {
        return templateComments;
    }

    /**
     * Sets the templateComments attribute value.
     * @param templateComments The templateComments to set.
     */
    public void setTemplateComments(List<AwardTemplateComment> templateComments) {
        this.templateComments = templateComments;
    }

    /**
     * Gets the templateTerms attribute. 
     * @return Returns the templateTerms.
     */
    public List<AwardTemplateTerm> getTemplateTerms() {
        return templateTerms;
    }

    /**
     * Sets the templateTerms attribute value.
     * @param templateTerms The templateTerms to set.
     */
    public void setTemplateTerms(List<AwardTemplateTerm> templateTerms) {
        this.templateTerms = templateTerms;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<AwardTemplateComment> getAwardComments() {
        return getTemplateComments();
    }

    @Override
    public List<AwardTemplateReportTerm> getAwardReportTermItems() {
        return getTemplateReportTerms();
    }

    @Override
    public List<AwardTemplateTerm> getAwardSponsorTerms() {
        return getTemplateTerms();
    }

    @Override
    public List<AwardTemplateContact> getSponsorContacts() {
        return getTemplateContacts();
    }
	
}