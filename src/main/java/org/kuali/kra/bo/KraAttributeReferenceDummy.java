/*
 * Copyright 2005-2007 The Kuali Foundation.
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


import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.core.bo.AttributeReferenceDummy;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.KualiPercent;

/**
 * Attribute Reference Dummy Business Object
 */
public class KraAttributeReferenceDummy extends AttributeReferenceDummy {

    private String description;

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public final void setDescription(final String argDescription) {
        this.description = argDescription;
    }
}
