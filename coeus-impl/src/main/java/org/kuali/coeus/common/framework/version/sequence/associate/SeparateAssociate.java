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
package org.kuali.coeus.common.framework.version.sequence.associate;

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
