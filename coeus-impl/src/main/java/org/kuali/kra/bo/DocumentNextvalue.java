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
package org.kuali.kra.bo;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DOCUMENT_NEXTVALUE")
@IdClass(DocumentNextvalue.DocumentNextvalueId.class)
public class DocumentNextvalue extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROPERTY_NAME")
    private String propertyName;

    @Id
    @Column(name = "DOCUMENT_NUMBER")
    private String documentKey;

    @Column(name = "NEXT_VALUE")
    private Integer nextValue;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getNextValue() {
        return nextValue;
    }

    public void setNextValue(Integer nextValue) {
        this.nextValue = nextValue;
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }
    
    public static final class DocumentNextvalueId implements Serializable, Comparable<DocumentNextvalueId> {

        private String propertyName;

        private String documentKey;

        public String getPropertyName() {
            return this.propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getDocumentKey() {
            return this.documentKey;
        }

        public void setDocumentKey(String documentKey) {
            this.documentKey = documentKey;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("propertyName", this.propertyName).append("documentKey", this.documentKey).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final DocumentNextvalueId rhs = (DocumentNextvalueId) other;
            return new EqualsBuilder().append(this.propertyName, rhs.propertyName).append(this.documentKey, rhs.documentKey).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.propertyName).append(this.documentKey).toHashCode();
        }

        @Override
        public int compareTo(DocumentNextvalueId other) {
            return new CompareToBuilder().append(this.propertyName, other.propertyName).append(this.documentKey, other.documentKey).toComparison();
        }
    }    

}
