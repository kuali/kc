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
 * Primary Key for the ProposalExemptNumber BO.
 */
@SuppressWarnings("serial")
public class ProposalExemptNumberId implements Serializable {
    
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name="SPECIAL_REVIEW_NUMBER")
    private Integer specialReviewNumber;

    @Column(name="EXEMPTION_TYPE_CODE")
    private String exemptionTypeCode;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public Integer getSpecialReviewNumber() {
        return this.specialReviewNumber;
    }
    
    public String getExemptionTypeCode() {
        return this.exemptionTypeCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProposalExemptNumberId)) return false;
        if (obj == null) return false;
        ProposalExemptNumberId other = (ProposalExemptNumberId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               ObjectUtils.equals(specialReviewNumber, other.specialReviewNumber) &&
               StringUtils.equals(exemptionTypeCode, other.exemptionTypeCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(specialReviewNumber).append(exemptionTypeCode).toHashCode();
    }
}
