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
package org.kuali.kra.s2s.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the S2sAppSubmission BO.
 */
@SuppressWarnings("serial")
public class S2sAppSubmissionId implements Serializable {
    
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name="SUBMISSION_NUMBER")
    private Integer submissionNumber;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public Integer getSubmissionNumber() {
        return this.submissionNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof S2sAppSubmissionId)) return false;
        if (obj == null) return false;
        S2sAppSubmissionId other = (S2sAppSubmissionId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               ObjectUtils.equals(submissionNumber, other.submissionNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(submissionNumber).toHashCode();
    }
}
