/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is a generic class for testing versioning
 */
public class SequenceAssociateChild implements SequenceAssociate<SequenceOwnerImpl> {
    private static final long serialVersionUID = 3354366183120742932L;

    private Long childId;
    private String name;
    private SequenceOwnerImpl owner;
    private Integer sequenceNumber;
    private List<SequenceAssociateGrandChild> children;
    
    public SequenceAssociateChild() {
        setChildId(new Random().nextLong());
        children = new ArrayList<SequenceAssociateGrandChild>();
    }
    
    public SequenceAssociateChild(String name) {
        this();
        this.name = name;
    }
    
    public void add(SequenceAssociateGrandChild grandChild) {
        children.add(grandChild);
        grandChild.setParent(this);
    }
    
    public List<SequenceAssociateGrandChild> getChildren() {
        return children;
    }

    public void setChildren(List<SequenceAssociateGrandChild> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SequenceOwnerImpl getOwner() {
        return owner;
    }

    public void setOwner(SequenceOwnerImpl owner) {
        this.owner = owner;
        this.sequenceNumber = owner != null ? owner.getSequenceNumber() : null;
    }

    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public SequenceOwnerImpl getSequenceOwner() {
        return owner;
    }
    
    @Override
    public void resetPersistenceState() {
        setChildId(null);
    }
    
    @Override
    public void setSequenceOwner(SequenceOwnerImpl newOwner) {
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
        if (!(obj instanceof SequenceAssociateChild)) {
            return false;
        }
        SequenceAssociateChild other = (SequenceAssociateChild) obj;
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
