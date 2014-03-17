/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.customdata;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.DocumentCustomData;

/**
 * This class is the BO representation of an Award Custom Data.
 */
public class AwardCustomData extends AwardAssociate implements DocumentCustomData {


    private static final long serialVersionUID = 4125090813618033094L;

    private Long awardCustomDataId;

    private String value;

    private Long customAttributeId;

    private CustomAttribute customAttribute;

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
     * Gets the awardCustomDataId attribute. 
     * @return Returns the awardCustomDataId.
     */
    public Long getAwardCustomDataId() {
        return awardCustomDataId;
    }

    /**
     * Sets the awardCustomDataId attribute value.
     * @param awardCustomDataId The awardCustomDataId to set.
     */
    public void setAwardCustomDataId(Long awardCustomDataId) {
        this.awardCustomDataId = awardCustomDataId;
    }

    /**
     * Gets the value attribute. 
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    @Override
    public void resetPersistenceState() {
        this.awardCustomDataId = null;
    }

    /**
     * Sets the value attribute value.
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardCustomDataId == null) ? 0 : awardCustomDataId.hashCode());
        result = prime * result + ((customAttribute == null) ? 0 : customAttribute.hashCode());
        result = prime * result + ((customAttributeId == null) ? 0 : customAttributeId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AwardCustomData other = (AwardCustomData) obj;
        if (awardCustomDataId == null) {
            if (other.awardCustomDataId != null) return false;
        } else if (!awardCustomDataId.equals(other.awardCustomDataId)) return false;
        if (customAttribute == null) {
            if (other.customAttribute != null) return false;
        } else if (!customAttribute.equals(other.customAttribute)) return false;
        if (customAttributeId == null) {
            if (other.customAttributeId != null) return false;
        } else if (!customAttributeId.equals(other.customAttributeId)) return false;
        if (value == null) {
            if (other.value != null) return false;
        } else if (!value.equals(other.value)) return false;
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
}
