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
package org.kuali.kra.kim.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the KimGroupPerson BO.
 */
@SuppressWarnings("serial")
public class KimGroupPersonId implements Serializable {
    
    @Column(name="GROUP_ID")
    private Long groupId;

    @Column(name="PERSON_ID")
    private Long personId;
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public Long getPersonId() {
        return this.personId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof KimGroupPersonId)) return false;
        if (obj == null) return false;
        KimGroupPersonId other = (KimGroupPersonId) obj;
        return ObjectUtils.equals(groupId, other.groupId) &&
               ObjectUtils.equals(personId, other.personId);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(groupId).append(personId).toHashCode();
    }
}
