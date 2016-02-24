/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
