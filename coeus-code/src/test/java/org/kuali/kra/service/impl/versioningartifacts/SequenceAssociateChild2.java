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

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is a generic class for testing versioning
 */
public class SequenceAssociateChild2 implements SequenceAssociate<OwnerAssociate> {
    private static final long serialVersionUID = 3354366183120742932L;

    private Long childId;
    private String name;
    private OwnerAssociate owner;
    private Integer sequenceNumber;
    private List<SequenceAssociateGrandChild2> children;
    
    public SequenceAssociateChild2() {
        setChildId(new Random().nextLong());
        children = new ArrayList<SequenceAssociateGrandChild2>();
    }
    
    public SequenceAssociateChild2(String name) {
        this();
        this.name = name;
    }
    
    public void add(SequenceAssociateGrandChild2 grandChild) {
        children.add(grandChild);
        grandChild.setParent(this);
    }
    
    public List<SequenceAssociateGrandChild2> getChildren() {
        return children;
    }

    public void setChildren(List<SequenceAssociateGrandChild2> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerAssociate getOwner() {
        return owner;
    }

    public void setOwner(OwnerAssociate owner) {
        this.owner = owner;
        this.sequenceNumber = owner != null ? owner.getSequenceNumber() : null;
    }

    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public OwnerAssociate getSequenceOwner() {
        return owner;
    }
    
    @Override
    public void resetPersistenceState() {
        setChildId(null);
    }
    
    @Override
    public void setSequenceOwner(OwnerAssociate newOwner) {
        setOwner(newOwner);
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        // do nothing
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
        if (!(obj instanceof SequenceAssociateChild2)) {
            return false;
        }
        SequenceAssociateChild2 other = (SequenceAssociateChild2) obj;
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
}
