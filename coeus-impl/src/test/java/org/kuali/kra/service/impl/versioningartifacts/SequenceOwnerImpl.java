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


    public SimpleAssociate getAssociate() {
        return associate;
    }
    

    public List<SequenceAssociateAttachmentBO> getAttachments() {
        return attachments;
    }
    

    public List<SequenceAssociateChild> getChildren() {
        return children;
    }
    

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
    
    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
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

    @Override
    public String getVersionNameField() {
        return "name";
    }

    @Override
    public String getVersionNameFieldValue() {
        return name;
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
    public void incrementSequenceNumber() {
        sequenceNumber++;
    }
    
    @Override
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

    @Override
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
