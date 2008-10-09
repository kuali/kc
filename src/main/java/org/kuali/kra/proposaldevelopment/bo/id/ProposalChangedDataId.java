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
 * Primary Key for the ProposalChangedData BO.
 */
@SuppressWarnings("serial")
public class ProposalChangedDataId implements Serializable {

    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
   
    @Column(name="COLUMN_NAME")
    private String columnName;
 
    @Column(name="CHANGE_NUMBER")
    private Integer changeNumber;
    
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public String getColumnName() {
        return this.columnName;
    }
    
    public Integer getChangeNumber() {
        return this.changeNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProposalChangedDataId)) return false;
        if (obj == null) return false;
        ProposalChangedDataId other = (ProposalChangedDataId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               StringUtils.equals(columnName, other.columnName) &&
               ObjectUtils.equals(changeNumber, other.changeNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(columnName).append(changeNumber).toHashCode();
    }
}
