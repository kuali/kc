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
 * Primary Key for the KimGroupGroup BO.
 */
@SuppressWarnings("serial")
public class KimGroupGroupId implements Serializable {
    
    @Column(name="PARENT_GROUP_ID")
    private Long parentGroupId;

    @Column(name="MEMBER_GROUP_ID")
    private Long memberGroupId;
    
    public Long getParentGroupId() {
        return this.parentGroupId;
    }
    
    public Long getMemberGroupId() {
        return this.memberGroupId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof KimGroupGroupId)) return false;
        if (obj == null) return false;
        KimGroupGroupId other = (KimGroupGroupId) obj;
        return ObjectUtils.equals(parentGroupId, other.parentGroupId) &&
               ObjectUtils.equals(memberGroupId, other.memberGroupId);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(parentGroupId).append(memberGroupId).toHashCode();
    }
}
