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
package org.kuali.kra.service.impl.versioningartifacts;

import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;

import java.util.Random;

/**
 * This test artifact represents a top level sequenceOwnerImpl; i.e. Award
 */
public class SimpleSequenceOwner implements SequenceOwner<SimpleSequenceOwner> {
    private static final long serialVersionUID = 3354366183120742932L;
    
    private Long sequenceOwnerId;
    private String name;
    private Integer sequenceNumber;
    

    public SimpleSequenceOwner() {
        this.name = "SequenceOwner";
        sequenceNumber = 1;
        setSequenceOwnerId(new Random().nextLong());
    }
    

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public void setSequenceOwner(SimpleSequenceOwner newOwner) {
       // do nothing - this is root sequence association
    }
    
    @Override
    public SimpleSequenceOwner getSequenceOwner() {
       return this;
    }
    
    @Override
    public void incrementSequenceNumber() {
        sequenceNumber++;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    public Long getSequenceOwnerId() {
        return sequenceOwnerId;
    }

    public void setSequenceOwnerId(Long sequenceOwnerId) {
        this.sequenceOwnerId = sequenceOwnerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SimpleSequenceOwner)) {
            return false;
        }
        SimpleSequenceOwner other = (SimpleSequenceOwner) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null) {
                return false;
            }
        } else if (!sequenceNumber.equals(other.sequenceNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public void resetPersistenceState() {
       setSequenceOwnerId(null); 
    }
    
    @Override
    public String toString() {
        return String.format("%s [%d]", name, sequenceNumber);
    }

    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    @Override
    public String getVersionNameField() {
        return "name";
    }

    @Override
    public String getVersionNameFieldValue() {
        return name;
    }
}
