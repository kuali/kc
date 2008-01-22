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
 * The KIM Person Attribute represents one attribute (name/value) pair
 * associated with a Person.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KimPersonAttribute extends KimAttribute {

	private static final long serialVersionUID = 2861440911751860350L;
	
	private Long personId;
	private Long namespaceId;
    
	/**
	 * Get the Person's ID.
	 * @return the ID of the Person this Person Attribute belongs to
	 */
	public Long getPersonId() {
        return personId;
    }

    /**
     * Get the Person's ID.
     * @param personId the ID of the Person this Person Attribute belongs to
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * Get the Namespace's ID.
     * @return the ID of the Namespace this Person Attribute resides in
     */
    public Long getNamespaceId() {
		return namespaceId;
	}

	/**
	 * Set the Namespace's ID.
	 * @param namespaceId the ID of the Namespace this Person Attribute resides in
	 */
	public void setNamespaceId(Long namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * @see org.kuali.kra.kim.bo.KimAttribute#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = super.toStringMapper();
        propMap.put("personId", getPersonId());
        propMap.put("namespaceId", getNamespaceId());
        return propMap;
	}
}
