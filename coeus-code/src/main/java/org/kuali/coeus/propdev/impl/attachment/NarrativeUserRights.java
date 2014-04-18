/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.attachment.NarrativeUserRightsContract;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NARRATIVE_USER_RIGHTS")
@IdClass(NarrativeUserRights.NarrativeUserRightsId.class)
public class NarrativeUserRights extends KcPersistableBusinessObjectBase implements NarrativeUserRightsContract {

    @Id
    @Column(name = "MODULE_NUMBER")
    private Integer moduleNumber;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ACCESS_TYPE")
    private String accessType;

    @Transient
    private String personName;

    @Override
    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public static final class NarrativeUserRightsId implements Serializable, Comparable<NarrativeUserRightsId> {

        private String proposalNumber;

        private Integer moduleNumber;

        private String userId;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getModuleNumber() {
            return this.moduleNumber;
        }

        public void setModuleNumber(Integer moduleNumber) {
            this.moduleNumber = moduleNumber;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("moduleNumber", this.moduleNumber).append("userId", this.userId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final NarrativeUserRightsId rhs = (NarrativeUserRightsId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.moduleNumber, rhs.moduleNumber).append(this.userId, rhs.userId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.moduleNumber).append(this.userId).toHashCode();
        }

        @Override
        public int compareTo(NarrativeUserRightsId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.moduleNumber, other.moduleNumber).append(this.userId, other.userId).toComparison();
        }
    }
}
