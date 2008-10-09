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

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for CustomAttributeDocValue BO.
 */
@SuppressWarnings("serial")
public class CustomAttributeDocValueId implements Serializable {
    
    @Column(name="CUSTOM_ATTRIBUTE_ID")
    private Integer customAttributeId;

    @Column(name="DOCUMENT_NUMBER")
    private String documentNumber;
    
    public Integer getCustomAttributeId() {
        return this.customAttributeId;
    }
    
    public String getDocumentNumber() {
        return this.documentNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CustomAttributeDocValueId)) return false;
        if (obj == null) return false;
        CustomAttributeDocValueId other = (CustomAttributeDocValueId) obj;
        return ObjectUtils.equals(customAttributeId, other.customAttributeId) &&
               StringUtils.equals(documentNumber, other.documentNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(customAttributeId).append(documentNumber).toHashCode();
    }
}
