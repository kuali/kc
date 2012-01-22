/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.subaward.customdata;

import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAssociate;
import org.kuali.kra.bo.CustomAttribute;

public class SubAwardCustomData extends SubAwardAssociate{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4125090813618033094L;

    private Long subAwardCustomDataId;

    private String value;

    private Long customAttributeId;
    private CustomAttribute customAttribute;
    private String subAwardCode; 


    private SubAward subAward;

    /**
     * Gets the customAttributeId attribute. 
     * @return Returns the customAttributeId.
     */
    public Long getCustomAttributeId() {
        return customAttributeId;
    }

    /**
     * Sets the customAttributeId attribute value.
     * @param customAttributeId The customAttributeId to set.
     */
    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    /**
     * Gets the subAwardCustomDataId attribute. 
     * @return Returns the subAwardCustomDataId.
     */
    public Long getSubAwardCustomDataId() {
        return subAwardCustomDataId;
    }

    /**
     * Sets the awardCustomDataId attribute value.
     * @param awardCustomDataId The awardCustomDataId to set.
     */
    public void setSubAwardCustomDataId(Long subAwardCustomDataId) {
        this.subAwardCustomDataId = subAwardCustomDataId;
    }

    /**
     * Gets the value attribute. 
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.subAwardCustomDataId = null;
    }

    /**
     * Sets the value attribute value.
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((subAwardCustomDataId == null) ? 0 : subAwardCustomDataId.hashCode());
        result = prime * result + ((customAttribute == null) ? 0 : customAttribute.hashCode());
        result = prime * result + ((customAttributeId == null) ? 0 : customAttributeId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubAwardCustomData other = (SubAwardCustomData) obj;
        if (subAwardCustomDataId == null) {
            if (other.subAwardCustomDataId != null)
                return false;
        }
        else if (!subAwardCustomDataId.equals(other.subAwardCustomDataId))
            return false;
        if (customAttribute == null) {
            if (other.customAttribute != null)
                return false;
        }
        else if (!customAttribute.equals(other.customAttribute))
            return false;
        if (customAttributeId == null) {
            if (other.customAttributeId != null)
                return false;
        }
        else if (!customAttributeId.equals(other.customAttributeId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        }
        else if (!value.equals(other.value))
            return false;
        return true;
    }

    /**
     * Gets the customAttribute attribute. 
     * @return Returns the customAttribute.
     */
    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    /**
     * Sets the customAttribute attribute value.
     * @param customAttribute The customAttribute to set.
     */
    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    
    
}
