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

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;

import java.util.Random;

/**
 * This class is a simple SequenceAssociate
 */
public class SimpleAssociate implements SequenceAssociate<SequenceOwnerImpl> {
    private static final long serialVersionUID = 5671666754979103402L;
    
    private Long simpleAssociateId;
    private String name;
    private SequenceOwnerImpl owner;
    private Integer sequenceNumber;
    
    public SimpleAssociate(String name) {
        this.name = name;
        this.simpleAssociateId = 1L;
    }
    
    public void setOwner(SequenceOwnerImpl owner) {
        this.owner = owner;
        setSimpleAssociateId(new Random().nextLong());
        this.sequenceNumber = owner != null ? owner.getSequenceNumber() : null;
    }
    
    @Override
    public SequenceOwnerImpl getSequenceOwner() {
        return owner;
    }

    @Override
    public void setSequenceOwner(SequenceOwnerImpl newOwner) {
        setOwner(newOwner);        
    }

    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        // do nothing
    }
    
    

    /**
     * Gets the owner attribute. 
     * @return Returns the owner.
     */
    public SequenceOwnerImpl getOwner() {
        return owner;
    }

    public Long getSimpleAssociateId() {
        return simpleAssociateId;
    }

    public void setSimpleAssociateId(Long simpleAssociateId) {
        this.simpleAssociateId = simpleAssociateId;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
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
        if (!(obj instanceof SimpleAssociate)) {
            return false;
        }
        SimpleAssociate other = (SimpleAssociate) obj;
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
       setSimpleAssociateId(null); 
    }
}
