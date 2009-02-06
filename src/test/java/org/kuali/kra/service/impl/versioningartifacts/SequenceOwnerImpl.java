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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.kuali.kra.SequenceOwner;

/**
 * This test artifact represents a top level sequenceOwnerImpl; i.e. Award
 */
public class SequenceOwnerImpl implements SequenceOwner {
    private static final long serialVersionUID = 3354366183120742932L;
    
    private Long sequenceOwnerId;
    private String name;
    private Integer sequenceNumber;
    private SimpleAssociate associate;
    private List<SequenceAssociateChild> children;
    private List<SequenceAssociateAttachmentBO> attachments;
    
    public SequenceOwnerImpl() {
        this.name = "SequenceOwner";
        sequenceNumber = 1;
        setSequenceOwnerId(new Random().nextLong());
        children = new ArrayList<SequenceAssociateChild>();
        attachments = new ArrayList<SequenceAssociateAttachmentBO>();
    }
    
    public void add(SequenceAssociateChild sequenceAssociateChild) {
        children.add(sequenceAssociateChild);
        sequenceAssociateChild.setOwner(this);
    }
    
    public void add(SequenceAssociateAttachmentBO attachmentBO) {
        attachments.add(attachmentBO);
        attachmentBO.add(this);
    }
    
    public List<SequenceAssociateChild> getChildren() {
        return children;
    }
    
    public SimpleAssociate getAssociate() {
        return associate;
    }
    
    public void setAssociate(SimpleAssociate associate) {
        this.associate = associate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<SequenceAssociateAttachmentBO> getAttachments() {
        return attachments;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setChildren(List<SequenceAssociateChild> children) {
        this.children = children;
    }

    public void setAttachments(List<SequenceAssociateAttachmentBO> attachments) {
        this.attachments = attachments;
    }

    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newOwner) {
       // do nothing - this is root sequence association
    }
    
    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
       return this;
    }
    
    /**
     * @see org.kuali.kra.SequenceOwner#incrementSequenceNumber()
     */
    public void incrementSequenceNumber() {
        sequenceNumber++;
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

    public Long getSequenceOwnerId() {
        return sequenceOwnerId;
    }

    public void setSequenceOwnerId(Long sequenceOwnerId) {
        this.sequenceOwnerId = sequenceOwnerId;
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
        if (!(obj instanceof SequenceOwnerImpl)) {
            return false;
        }
        SequenceOwnerImpl other = (SequenceOwnerImpl) obj;
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

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
       setSequenceOwnerId(null); 
    }
}