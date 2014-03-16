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

import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;
import org.kuali.kra.SkipVersioning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This test artifact represents a top level sequenceOwnerImpl; i.e. Award
 */
public class SequenceOwnerImpl implements SequenceOwner<SequenceOwnerImpl> {
    private static final long serialVersionUID = 3354366183120742932L;
    
    private Long sequenceOwnerId;
    private String name;
    private Integer sequenceNumber;
    private SimpleAssociate associate;
    private OwnerAssociate ownerAssociate;
    private List<SequenceAssociateChild> children;
    private List<SequenceAssociateAttachmentBO> attachments;
    
    @SkipVersioning
    private SimpleAssociate skipAssociate;
    
    @SkipVersioning
    private List<SequenceAssociateChild> skipChildren;
    
    /**
     * Constructs a SequenceOwnerImpl.java.
     */
    public SequenceOwnerImpl() {
        this.name = "SequenceOwner";
        sequenceNumber = 1;
        setSequenceOwnerId(new Random().nextLong());
        children = new ArrayList<SequenceAssociateChild>();
        skipChildren = new ArrayList<SequenceAssociateChild>();
        attachments = new ArrayList<SequenceAssociateAttachmentBO>();
    }

    /**
     * @param attachmentBO
     */
    public void add(SequenceAssociateAttachmentBO attachmentBO) {
        attachments.add(attachmentBO);
        //attachmentBO.add(this);
    }

    /**
     * @param sequenceAssociateChild
     */
    public void add(SequenceAssociateChild sequenceAssociateChild) {
        children.add(sequenceAssociateChild);
        sequenceAssociateChild.setOwner(this);
    }

    /**
     * @param sequenceAssociateChild
     */
    public void addSkipChild(SequenceAssociateChild sequenceAssociateChild) {
        skipChildren.add(sequenceAssociateChild);
        sequenceAssociateChild.setOwner(this);
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
     * @return
     */
    public SimpleAssociate getAssociate() {
        return associate;
    }
    
    /**
     * @return
     */
    public List<SequenceAssociateAttachmentBO> getAttachments() {
        return attachments;
    }
    
    /**
     * @return
     */
    public List<SequenceAssociateChild> getChildren() {
        return children;
    }
    
    /**
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ownerAssociate attribute. 
     * @return Returns the ownerAssociate.
     */
    public OwnerAssociate getOwnerAssociate() {
        return ownerAssociate;
    }
    
    /**
     * @see org.kuali.coeus.common.framework.sequence.owner.SequenceOwner#getOwnerSequenceNumber()
     */
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    /**
     * @see org.kuali.coeus.common.framework.sequence.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwnerImpl getSequenceOwner() {
       return this;
    }
    
    public Long getSequenceOwnerId() {
        return sequenceOwnerId;
    }

    /**
     * Gets the skipAssociate attribute. 
     * @return Returns the skipAssociate.
     */
    public SimpleAssociate getSkipAssociate() {
        return skipAssociate;
    }
    
    /**
     * Gets the skipChildren attribute. 
     * @return Returns the skipChildren.
     */
    public List<SequenceAssociateChild> getSkipChildren() {
        return skipChildren;
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.owner.SequenceOwner#getVersionNameField()
     */
    public String getVersionNameField() {
        return "name";
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

    /**
     * @see org.kuali.coeus.common.framework.sequence.owner.SequenceOwner#incrementSequenceNumber()
     */
    public void incrementSequenceNumber() {
        sequenceNumber++;
    }
    
    /**
     * @see org.kuali.coeus.common.framework.sequence.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
       setSequenceOwnerId(null); 
    }

    /**
     * @param associate
     */
    public void setAssociate(SimpleAssociate associate) {
        this.associate = associate;
    }
    
    /**
     * @param attachments
     */
    public void setAttachments(List<SequenceAssociateAttachmentBO> attachments) {
        this.attachments = attachments;
    }
    
    /**
     * @param children
     */
    public void setChildren(List<SequenceAssociateChild> children) {
        this.children = children;
    }
    
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ownerAssociate attribute value.
     * @param ownerAssociate The ownerAssociate to set.
     */
    public void setOwnerAssociate(OwnerAssociate ownerAssociate) {
        this.ownerAssociate = ownerAssociate;
    }

    /**
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#setSequenceOwner(org.kuali.coeus.common.framework.sequence.owner.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwnerImpl newOwner) {
       // do nothing - this is root sequence association
    }

    public void setSequenceOwnerId(Long sequenceOwnerId) {
        this.sequenceOwnerId = sequenceOwnerId;
    }
    
    /**
     * Sets the skipAssociate attribute value.
     * @param skipAssociate The skipAssociate to set.
     */
    public void setSkipAssociate(SimpleAssociate skipAssociate) {
        this.skipAssociate = skipAssociate;
    }

    /**
     * Sets the skipChildren attribute value.
     * @param skipChildren The skipChildren to set.
     */
    public void setSkipChildren(List<SequenceAssociateChild> skipChildren) {
        this.skipChildren = skipChildren;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%d]", name, sequenceNumber);
    }
}
