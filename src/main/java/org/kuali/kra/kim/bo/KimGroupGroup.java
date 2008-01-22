/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * The KIM Group-Group represents the association of two Groups.  One
 * Group is the parent and the other is a member of the parent Group.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimGroupGroup extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = -8643371519534909248L;
    
    private Long parentGroupId;
    private Long memberGroupId;

    /**
     * Get the Parent Group's ID.
     * @return the Parent Group's ID
     */
    public Long getParentGroupId() {
        return parentGroupId;
    }

    /**
     * Set the Parent Group's ID.
     * @param parentGroupId the Parent Group's ID
     */
    public void setParentGroupId(Long parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    /**
     * Get the Member Group's ID.
     * @return the Member Group's ID
     */
    public Long getMemberGroupId() {
        return memberGroupId;
    }

    /**
     * Set the Member Group's ID.
     * @param memberGroupId the Member Group's ID
     */
    public void setMemberGroupId(Long memberGroupId) {
        this.memberGroupId = memberGroupId;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("parentGroupId", getParentGroupId());
        map.put("memberGroupId", getMemberGroupId());
        return map;
    }
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
     */
    public void refresh() {
        // not going to implement unless necessary
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!obj.getClass().equals(this.getClass())) return false;
        KimGroupGroup groupGroup = (KimGroupGroup)obj;
        return parentGroupId.equals(groupGroup.parentGroupId) &&
               memberGroupId.equals(groupGroup.memberGroupId);
    }
}
