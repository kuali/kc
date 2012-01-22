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
package org.kuali.kra.award.paymentreports;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the FrequencyBase business object and is mapped
 * with FREQUENCY_BASE table.
 */
public class FrequencyBase extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5406416029670950959L;

    private String frequencyBaseCode;

    private String description;

    private String regenerationTypeName;

    private boolean active;

    /**
     * Constructs a FrequencyBase object.
     */
    public FrequencyBase() {
        regenerationTypeName = ReportRegenerationType.REGEN.name();
    }

    /**
     * 
     * This method...
     * @return
     */
    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }

    /**
     * 
     * @param frequencyBaseCode
     */
    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyBaseCode == null) ? 0 : frequencyBaseCode.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FrequencyBase)) {
            return false;
        }
        return equals((FrequencyBase) obj);
    }

    /**
     * 
     * Convenience method for equality of FrequencyBase
     * @param frequencyBase
     * @return
     */
    public boolean equals(FrequencyBase frequencyBase) {
        if (frequencyBaseCode == null) {
            if (frequencyBase.frequencyBaseCode != null) {
                return false;
            }
        } else if (!frequencyBaseCode.equals(frequencyBase.frequencyBaseCode)) {
            return false;
        }
        return true;
    }

    public ReportRegenerationType getReportRegenerationType() {
        if (StringUtils.isNotBlank(getRegenerationTypeName())) {
            return ReportRegenerationType.valueOf(getRegenerationTypeName());
        } else {
            return null;
        }
    }

    public void setReportRegenerationType(ReportRegenerationType type) {
        if (type != null) {
            setRegenerationTypeName(type.name());
        } else {
            setRegenerationTypeName(null);
        }
    }

    public String getRegenerationTypeName() {
        return regenerationTypeName;
    }

    public void setRegenerationTypeName(String regenerationTypeName) {
        this.regenerationTypeName = regenerationTypeName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
