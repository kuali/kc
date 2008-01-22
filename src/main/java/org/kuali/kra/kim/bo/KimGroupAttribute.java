/*
 * Copyright 2007 The Kuali Foundation
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

/**
 * The KIM Group Attribute represents one attribute (name/value) pair
 * for a Group.  Group's can have zero or more attributes.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimGroupAttribute extends KimAttribute {

	private static final long serialVersionUID = -2255690191635455239L;
	
	private Long groupId;

    /**
     * Get the Group ID this Attribute belongs to.
     * @return the Group ID this Attribute belongs to
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Set the Group ID that this Attribute belongs to.
     * @param groupId the Group ID the attribute belongs to
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = super.toStringMapper();
        map.put("groupId", getGroupId());
        return map;
	}
}
