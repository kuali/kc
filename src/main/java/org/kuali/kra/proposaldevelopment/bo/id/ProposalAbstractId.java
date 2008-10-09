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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key of the ProposalAbstract BO.
 */
@SuppressWarnings("serial")
public class ProposalAbstractId implements Serializable {

    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name="ABSTRACT_TYPE_CODE")
    private String abstractTypeCode;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public String getAbstractTypeCode() {
        return this.abstractTypeCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProposalAbstractId)) return false;
        if (obj == null) return false;
        ProposalAbstractId other = (ProposalAbstractId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               StringUtils.equals(abstractTypeCode, other.abstractTypeCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(abstractTypeCode).toHashCode();
    }
}
