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
}
