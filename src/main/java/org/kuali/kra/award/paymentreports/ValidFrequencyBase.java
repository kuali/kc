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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the ValidFrequencyBase business object.
 */
public class ValidFrequencyBase extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2875079003137515732L;

    private Integer validFrequencyBaseId;

    private String frequencyCode;

    private String frequencyBaseCode;

    private Frequency frequency;

    private FrequencyBase frequencyBase;

    /**
     * 
     * Constructs a ValidFrequencyBase.java.
     */
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

    /**
     *
     * @return
     */
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

    /**
     * 
     * @return
     */
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

    /**
     * 
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

    /**
     * 
     * @return
     */
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyBaseCode == null) ? 0 : frequencyBaseCode.hashCode());
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
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
