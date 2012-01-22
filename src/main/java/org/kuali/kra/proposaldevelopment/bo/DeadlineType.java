/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Class representation of the Deadline Type Business Object
 *
 */
public class DeadlineType extends KraPersistableBusinessObjectBase {

    private String deadlineTypeCode;

    private String description;

    /**
     * Gets the deadlineTypeCode attribute. 
     * @return Returns the deadlineTypeCode.
     */
    public String getDeadlineTypeCode() {
        return deadlineTypeCode;
    }

    /**
     * Sets the deadlineTypeCode attribute value.
     * @param deadlineTypeCode The deadlineTypeCode to set.
     */
    public void setDeadlineTypeCode(String deadlineTypeCode) {
        this.deadlineTypeCode = deadlineTypeCode;
    }

    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns the description attribute
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
