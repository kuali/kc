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
package org.kuali.kra.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for DocumentNextvalue BO.
 */
@SuppressWarnings("serial")
public class DocumentNextvalueId implements Serializable {
    
    @Column(name="PROPERTY_NAME")
    private String propertyName; 

    @Column(name="DOCUMENT_NUMBER")
    private String documentKey;
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public String getDocumentKey() {
        return this.documentKey;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DocumentNextvalueId)) return false;
        if (obj == null) return false;
        DocumentNextvalueId other = (DocumentNextvalueId) obj;
        return StringUtils.equals(propertyName, other.propertyName) &&
               StringUtils.equals(documentKey, other.documentKey);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(propertyName).append(documentKey).toHashCode();
    }
}
