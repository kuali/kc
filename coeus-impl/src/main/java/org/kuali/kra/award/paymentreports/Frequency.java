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
package org.kuali.kra.award.paymentreports;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

/**
 * 
 * This class represents the Frequency Business Object.
 */
public class Frequency extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = 3635003841342435180L;

    private String frequencyCode;

    private String description;

    private Integer numberOfDays;

    private Integer numberOfMonths;

    private Boolean repeatFlag;

    private Integer advanceNumberOfDays;

    private Integer advanceNumberOfMonths;

    private boolean active;

    public Frequency() {
    }

    public String getFrequencyCode() {
        return frequencyCode;
    }

    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Boolean getRepeatFlag() {
        return repeatFlag;
    }

    public void setRepeatFlag(Boolean repeatFlag) {
        this.repeatFlag = repeatFlag;
    }

    public Integer getAdvanceNumberOfDays() {
        return advanceNumberOfDays;
    }

    public void setAdvanceNumberOfDays(Integer advanceNumberOfDays) {
        this.advanceNumberOfDays = advanceNumberOfDays;
    }

    public Integer getAdvanceNumberOfMonths() {
        return advanceNumberOfMonths;
    }

    public void setAdvanceNumberOfMonths(Integer advanceNumberOfMonths) {
        this.advanceNumberOfMonths = advanceNumberOfMonths;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
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
        if (!(obj instanceof Frequency)) {
            return false;
        }
        return equals((Frequency) obj);
    }

    /**
     * 
     * Convenience method for equality of Frequency
     * @param frequency
     * @return
     */
    public boolean equals(Frequency frequency) {
        if (frequencyCode == null) {
            if (frequency.frequencyCode != null) {
                return false;
            }
        } else if (!frequencyCode.equals(frequency.frequencyCode)) {
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
