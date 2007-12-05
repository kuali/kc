/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

/**
 * Class representation of the Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public class PersonEditableField extends KraPersistableBusinessObjectBase {
    private String fieldName;
    private boolean active;

    /**
     * Gets the value of fieldName
     *
     * @return the value of fieldName
     */
    public String getFieldName() {
        return this.fieldName;
    }
     
    /**
     * Sets the value of fieldName
     *
     * @param argFieldName Value to assign to this.fieldName
     */
    public void setFieldName(String argFieldName) {
        this.fieldName = argFieldName;
    }

    /**
     * Read access to active flag
     * @return boolean active or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Write access to active flag
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }


	@Override 
	protected LinkedHashMap toStringMapper() {
   	    LinkedHashMap hashmap = new LinkedHashMap();
        hashmap.put("fieldName", getFieldName());
		return hashmap;
	}

}
