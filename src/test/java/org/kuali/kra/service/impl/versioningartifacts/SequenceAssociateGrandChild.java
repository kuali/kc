/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.Random;

import org.kuali.kra.SequenceAssociate;

/**
 * This class is the "grandchild" SequenceAssociate of the owner
 */
public class SequenceAssociateGrandChild implements SequenceAssociate<SequenceOwnerImpl> {
    private static final long serialVersionUID = -7334498072241996364L;
    
    private Long grandChildId;
    private SequenceOwnerImpl owner;
    private SequenceAssociateChild parent;
    private String name;
    private Integer sequenceNumber;
        
    public SequenceAssociateGrandChild(String name) {
        this.name = name;
        setGrandChildId(new Random().nextLong());
    }
    
    /**
     * Gets the parent attribute. 
     * @return Returns the parent.
     */
    public SequenceAssociateChild getParent() {
        return parent;
    }

    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(SequenceAssociateChild parent) {
        this.parent = parent;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwnerImpl getSequenceOwner() {
        return owner;
    }
    
    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwnerImpl newOwner) {
        setOwner(newOwner);
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Gets the owner attribute. 
     * @return Returns the owner.
     */
    public SequenceOwnerImpl getOwner() {
        return owner;
    }

    /**
     * Gets the name attribute. 
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the owner attribute value.
     * @param owner The owner to set.
     */
    public void setOwner(SequenceOwnerImpl owner) {
        this.owner = owner;
        this.sequenceNumber = owner != null ? owner.getSequenceNumber() : null;
    }

    /**
     * Sets the name attribute value.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        // do nothing
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    public Long getGrandChildId() {
        return grandChildId;
    }

    public void setGrandChildId(Long grandChildId) {
        this.grandChildId = grandChildId;
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
        if (!(obj instanceof SequenceAssociateGrandChild)) {
            return false;
        }
        SequenceAssociateGrandChild other = (SequenceAssociateGrandChild) obj;
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
    public String toString() {
        return name;
    }

    public void resetPersistenceState() {
        setGrandChildId(null);
    }
}