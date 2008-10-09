/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the ProposalSpecialReview BO.
 */
@SuppressWarnings("serial")
public class ProposalSpecialReviewId implements Serializable {

    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name="SPECIAL_REVIEW_NUMBER")
    private Integer specialReviewNumber;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public Integer getSpecialReviewNumber() {
        return this.specialReviewNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProposalSpecialReviewId)) return false;
        if (obj == null) return false;
        ProposalSpecialReviewId other = (ProposalSpecialReviewId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               ObjectUtils.equals(specialReviewNumber, other.specialReviewNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(specialReviewNumber).toHashCode();
    }
}
