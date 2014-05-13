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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sOppFormsContract;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "S2S_OPP_FORMS")
public class S2sOppForms extends KcPersistableBusinessObjectBase implements S2sOppFormsContract {

    @EmbeddedId
    private S2sOppFormsId s2sOppFormsId;

    @Column(name = "AVAILABLE")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean available;

    @Column(name = "FORM_NAME")
    private String formName;

    @Column(name = "INCLUDE")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean include;

    @Column(name = "MANDATORY")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean mandatory;

    @Transient
    private String schemaUrl;

    @Transient
    private Boolean selectToPrint;
    
    @Column(name = "USER_ATTACHED_FORM")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean userAttachedForm;

    public S2sOppFormsId getS2sOppFormsId() {
        return s2sOppFormsId;
    }

    public void setS2sOppFormsId(S2sOppFormsId s2sOppFormsId) {
        this.s2sOppFormsId = s2sOppFormsId;
    }

    public String getOppNameSpace() {
        return this.s2sOppFormsId.oppNameSpace;
    }

    public String getProposalNumber() {
        return this.s2sOppFormsId.proposalNumber;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public void setInclude(Boolean include) {
        this.include = include;
    }

    public Boolean getInclude() {
        return include;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }

    public Boolean getUserAttachedForm() {
        return userAttachedForm;
    }

    public void setUserAttachedForm(Boolean userAttachedForm) {
        this.userAttachedForm = userAttachedForm;
    }

    @Embeddable
    public static final class S2sOppFormsId implements Serializable, Comparable<S2sOppFormsId> {

        @Column(name = "OPP_NAME_SPACE")
        private String oppNameSpace;

        @Column(name = "PROPOSAL_NUMBER")
        private String proposalNumber;

        public String getOppNameSpace() {
            return this.oppNameSpace;
        }

        public void setOppNameSpace(String oppNameSpace) {
            this.oppNameSpace = oppNameSpace;
        }

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("oppNameSpace", this.oppNameSpace).append("proposalNumber", this.proposalNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final S2sOppFormsId rhs = (S2sOppFormsId) other;
            return new EqualsBuilder().append(this.oppNameSpace, rhs.oppNameSpace).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.oppNameSpace).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(S2sOppFormsId other) {
            return new CompareToBuilder().append(this.oppNameSpace, other.oppNameSpace).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
