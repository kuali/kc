/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.coeus.common.framework.custom.attr;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOM_ATTRIBUTE_DOC_VALUE")
@IdClass(CustomAttributeDocValue.CustomAttributeDocValueId.class)
public class CustomAttributeDocValue extends KcPersistableBusinessObjectBase implements DocumentCustomData {

    @Id
    @Column(name = "CUSTOM_ATTRIBUTE_ID")
    private Long customAttributeId;

    @Id
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CUSTOM_ATTRIBUTE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private CustomAttribute customAttribute;

    public Long getCustomAttributeId() {
        return customAttributeId;
    }

    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets the customAttribute attribute value.
     * @param customAttribute The customAttribute to set.
     */
    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    /**
     * Gets the customAttribute attribute.
     * @return Returns the customAttribute.
     */
    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public static final class CustomAttributeDocValueId implements Serializable, Comparable<CustomAttributeDocValueId> {

        private String documentNumber;

        private Long customAttributeId;

        public String getDocumentNumber() {
            return this.documentNumber;
        }

        public void setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
        }

        public Long getCustomAttributeId() {
            return this.customAttributeId;
        }

        public void setCustomAttributeId(Long customAttributeId) {
            this.customAttributeId = customAttributeId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("documentNumber", this.documentNumber).append("customAttributeId", this.customAttributeId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final CustomAttributeDocValueId rhs = (CustomAttributeDocValueId) other;
            return new EqualsBuilder().append(this.documentNumber, rhs.documentNumber).append(this.customAttributeId, rhs.customAttributeId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.documentNumber).append(this.customAttributeId).toHashCode();
        }

        @Override
        public int compareTo(CustomAttributeDocValueId other) {
            return new CompareToBuilder().append(this.documentNumber, other.documentNumber).append(this.customAttributeId, other.customAttributeId).toComparison();
        }
    }
}
