/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

/**
 * 
 * This class represents Distribution business object and is mapped
 * with DISTRIBUTION table.
 */
public class Distribution extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = -8638092879516673772L;

    private String ospDistributionCode;

    private String description;

    private boolean active;


    public Distribution() {
    }


    public String getOspDistributionCode() {
        return ospDistributionCode;
    }

    /**
     * 
     * @param ospDistributionCode
     */
    public void setOspDistributionCode(String ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }


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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((ospDistributionCode == null) ? 0 : ospDistributionCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Distribution)) {
            return false;
        }
        return equals((Distribution) obj);
    }

    /**
     * 
     * Convenience method for equality of Distribution
     * @param distribution
     * @return
     */
    public boolean equals(Distribution distribution) {
        if (ospDistributionCode == null) {
            if (distribution.ospDistributionCode != null) {
                return false;
            }
        } else if (!ospDistributionCode.equals(distribution.ospDistributionCode)) {
            return false;
        }
        return true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
