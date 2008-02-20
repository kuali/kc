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

import java.io.Serializable;
import java.util.List;

/**
 * Class representation of a Custom Attribute Group Business Object
 */
public class CustomAttributeGroup implements Serializable {

	private String fullName;
	private List<CustomAttributeDocument> customAttributeDocuments;

    /**
     * Gets the fullName attribute.
     * @return Returns the fullName.
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * Sets the fullName attribute value.
     * @param fullName The fullName to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * Sets the customAttributeDocuments attribute value.
     * @param customAttributeDocuments The customAttributeDocuments to set.
     */
    public void setCustomAttributeDocuments(List<CustomAttributeDocument> customAttributeDocuments) {
        this.customAttributeDocuments = customAttributeDocuments;
    }
    /**
     * Gets the customAttributeDocuments attribute.
     * @return Returns the customAttributeDocuments.
     */
    public List<CustomAttributeDocument> getCustomAttributeDocuments() {
        return customAttributeDocuments;
    }

}
