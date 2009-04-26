/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
 * Class representation of the Person <code>{@link org.kuali.rice.kns.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.kns.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: gmcgrego $
 * @version $Revision: 1.3 $
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
