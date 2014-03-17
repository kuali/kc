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
package org.kuali.coeus.common.framework.sequence.associate;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class is meant to be a common base class for SeparatelySequenceableAssociate BOs (for BOs that haven't already
 * extended a class).
 * 
 */
public abstract class SeparateAssociate extends KcPersistableBusinessObjectBase implements SeparatelySequenceableAssociate {

    private static final long serialVersionUID = -8385115657304261423L;

    private static final Integer INITIAL_VERSION = Integer.valueOf(0);

    //the pk of the BO - this can always be moved to a subclass if it conflict with a BOs PK requirements  
    private Long id;

    private Integer sequenceNumber = INITIAL_VERSION;

    @Override
    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    /**
     * Sets the sequence number.
     * @param sequenceNumber the sequence number.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++;
    }

    @Override
    public void resetPersistenceState() {
        this.setId(null);
    }

    /**
     * Gets the  id.
     * @return the  id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.sequenceNumber == null) ? 0 : this.sequenceNumber.hashCode());
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SeparateAssociate other = (SeparateAssociate) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.sequenceNumber == null) {
            if (other.sequenceNumber != null) {
                return false;
            }
        } else if (!this.sequenceNumber.equals(other.sequenceNumber)) {
            return false;
        }
        return true;
    }
}
