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
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalExemptNumberId.class)
@Entity
@Table(name="EPS_PROP_EXEMPT_NUMBER")
public class ProposalExemptNumber extends KraPersistableBusinessObjectBase {
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Id
	@Column(name="SPECIAL_REVIEW_NUMBER")
	private Integer specialReviewNumber;
    
    @Id
	@Column(name="EXEMPTION_TYPE_CODE")
	private String exemptionTypeCode;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="EXEMPTION_TYPE_CODE", insertable=false, updatable=false)
	private ExemptionType exemptionType;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="SPECIAL_REVIEW_NUMBER", insertable=false, updatable=false)})
    private ProposalSpecialReview proposalSpecialReview;
    
    public String getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    public Integer getSpecialReviewNumber() {
        return specialReviewNumber;
    }
    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
    }
    public String getExemptionTypeCode() {
        return exemptionTypeCode;
    }
    public void setExemptionTypeCode(String exemptionTypeCode) {
        this.exemptionTypeCode = exemptionTypeCode;
    }
    public ExemptionType getExemptionType() {
        return exemptionType;
    }
    public void setExemptionType(ExemptionType exemptionType) {
        this.exemptionType = exemptionType;
    }
    
    public ProposalSpecialReview getProposalSpecialReview() {
        return proposalSpecialReview;
    }
    
    public void setProposalSpecialReview(ProposalSpecialReview proposalSpecialReview) {
        this.proposalSpecialReview = proposalSpecialReview;
    }
    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("specialReviewNumber", getSpecialReviewNumber());
        hashMap.put("exemptionTypeCode", getExemptionTypeCode());
        return hashMap;
    }

}

