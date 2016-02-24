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
package org.kuali.coeus.common.framework.custom.attr;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.custom.attr.CustomAttributeDocValueContract;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOM_ATTRIBUTE_DOC_VALUE")
@IdClass(CustomAttributeDocValue.CustomAttributeDocValueId.class)
public class CustomAttributeDocValue extends KcPersistableBusinessObjectBase implements DocumentCustomData, CustomAttributeDocValueContract {

    @Id
    @Column(name = "CUSTOM_ATTRIBUTE_ID")
    private Long id;

    @Id
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CUSTOM_ATTRIBUTE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private CustomAttribute customAttribute;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public Long getCustomAttributeId() {
        return getId();
    }

    @Override
    public void setCustomAttributeId(Long customAttributeId) {
        setId(customAttributeId);
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    @Override
    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public static final class CustomAttributeDocValueId implements Serializable, Comparable<CustomAttributeDocValueId> {

        private String documentNumber;

        private Long id;

        public String getDocumentNumber() {
            return this.documentNumber;
        }

        public void setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
        }

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("documentNumber", this.documentNumber).append("id", this.id).toString();
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
            return new EqualsBuilder().append(this.documentNumber, rhs.documentNumber).append(this.id, rhs.id).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.documentNumber).append(this.id).toHashCode();
        }

        @Override
        public int compareTo(CustomAttributeDocValueId other) {
            return new CompareToBuilder().append(this.documentNumber, other.documentNumber).append(this.id, other.id).toComparison();
        }
    }
}
