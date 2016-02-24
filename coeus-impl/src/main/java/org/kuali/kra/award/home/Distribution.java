/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
