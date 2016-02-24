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

/**
 * 
 * This class represents the ValidFrequencyBase business object.
 */
public class ValidFrequencyBase extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -2875079003137515732L;

    private Integer validFrequencyBaseId;

    private String frequencyCode;

    private String frequencyBaseCode;

    private Frequency frequency;

    private FrequencyBase frequencyBase;


    public ValidFrequencyBase() {
        super();
    }

    /**
     * 
     * Constructs a ValidFrequencyBase.java.
     * @param frequencyCode
     */
    public ValidFrequencyBase(String frequencyCode, String frequencyBaseCode) {
        super();
        this.frequencyCode = frequencyCode;
        this.frequencyBaseCode = frequencyBaseCode;
    }


    public Integer getValidFrequencyBaseId() {
        return validFrequencyBaseId;
    }

    /**
     * 
     * @param validFrequencyBaseId
     */
    public void setValidFrequencyBaseId(Integer validFrequencyBaseId) {
        this.validFrequencyBaseId = validFrequencyBaseId;
    }


    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     * 
     * @param frequencyCode
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }


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


    public Frequency getFrequency() {
        return frequency;
    }

    /**
     * 
     * @param frequency
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }


    public FrequencyBase getFrequencyBase() {
        return frequencyBase;
    }

    /**
     * 
     * @param frequencyBase
     */
    public void setFrequencyBase(FrequencyBase frequencyBase) {
        this.frequencyBase = frequencyBase;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyBaseCode == null) ? 0 : frequencyBaseCode.hashCode());
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
        if (!(obj instanceof ValidFrequencyBase)) {
            return false;
        }
        return equals((ValidFrequencyBase) obj);
    }

    /**
     * 
     * Convenience method for equality of ValidFrequencyBase
     * @param validFrequencyBase
     * @return
     */
    public boolean equals(ValidFrequencyBase validFrequencyBase) {
        if (frequencyBaseCode == null) {
            if (validFrequencyBase.frequencyBaseCode != null) {
                return false;
            }
        } else if (!frequencyBaseCode.equals(validFrequencyBase.frequencyBaseCode)) {
            return false;
        }
        if (frequencyCode == null) {
            if (validFrequencyBase.frequencyCode != null) {
                return false;
            }
        } else if (!frequencyCode.equals(validFrequencyBase.frequencyCode)) {
            return false;
        }
        return true;
    }
}
