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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a sequence owner that is also an associate; i.e. a SubAward on an Award 
 */
public class OwnerAssociate implements SequenceOwner<SequenceOwnerImpl> {
    private static final long serialVersionUID = 8605301787439491786L;
    
    private Long ownerAssociateId;
    private String name;
    private Integer sequenceNumber;
    private Integer ownerSequenceNumber;
    private SimpleAssociate2 associate;
    private SequenceOwnerImpl owner;
    private List<SequenceAssociateChild2> children;
    private List<SequenceAssociateAttachmentBO2> attachments;

    /**
     * Constructs a OwnerAssociate
     * @param name
     */
    public OwnerAssociate(String name) {
        this.name = name;
        this.sequenceNumber = 1;
        setOwnerAssociateId(new Random().nextLong());
        children = new ArrayList<SequenceAssociateChild2>();
        attachments = new ArrayList<SequenceAssociateAttachmentBO2>();
    }

    /**
     * @param attachmentBO
     */
    public void add(SequenceAssociateAttachmentBO2 attachmentBO) {
        attachments.add(attachmentBO);
        //attachmentBO.add(this);
    }

    /**
     * @param sequenceAssociateChild
     */
    public void add(SequenceAssociateChild2 sequenceAssociateChild) {
        children.add(sequenceAssociateChild);
        sequenceAssociateChild.setOwner(this);
    }
    
    /**
     * Gets the associate attribute. 
     * @return Returns the associate.
     */
    public SimpleAssociate2 getAssociate() {
        return associate;
    }
    
    /**
     * Gets the attachments attribute. 
     * @return Returns the attachments.
     */
    public List<SequenceAssociateAttachmentBO2> getAttachments() {
        return attachments;
    }
    
    /**
     * Gets the children attribute. 
     * @return Returns the children.
     */
    public List<SequenceAssociateChild2> getChildren() {
        return children;
    }
    /**
     * Gets the name attribute. 
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the owner attribute. 
     * @return Returns the owner.
     */
    public SequenceOwnerImpl getOwner() {
        return owner;
    }

    /**
     * Gets the ownerAssociateId attribute. 
     * @return Returns the ownerAssociateId.
     */
    public Long getOwnerAssociateId() {
        return ownerAssociateId;
    }    
    
    /**
     * Gets the ownerSequenceNumber attribute. 
     * @return Returns the ownerSequenceNumber.
     */
    public Integer getOwnerSequenceNumber() {
        return ownerSequenceNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public SequenceOwnerImpl getSequenceOwner() {
        return owner;
    }
    
    public void incrementSequenceNumber() {
        this.sequenceNumber++;
    }

    public void resetPersistenceState() {
        this.ownerAssociateId = null;
    }

    /**
     * Sets the associate attribute value.
     * @param associate The associate to set.
     */
    public void setAssociate(SimpleAssociate2 associate) {
        this.associate = associate;
    }

    /**
     * Sets the attachments attribute value.
     * @param attachments The attachments to set.
     */
    public void setAttachments(List<SequenceAssociateAttachmentBO2> attachments) {
        this.attachments = attachments;
    }

    /**
     * Sets the children attribute value.
     * @param children The children to set.
     */
    public void setChildren(List<SequenceAssociateChild2> children) {
        this.children = children;
    }

    /**
     * Sets the name attribute value.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the owner attribute value.
     * @param owner The owner to set.
     */
    public void setOwner(SequenceOwnerImpl owner) {
        this.owner = owner;
        this.ownerSequenceNumber = owner != null ? owner.getSequenceNumber() : null;
    }

    /**
     * Sets the ownerAssociateId attribute value.
     * @param ownerAssociateId The ownerAssociateId to set.
     */
    public void setOwnerAssociateId(Long ownerAssociateId) {
        this.ownerAssociateId = ownerAssociateId;
    }

    /**
     * Sets the ownerSequenceNumber attribute value.
     * @param ownerSequenceNumber The ownerSequenceNumber to set.
     */
    public void setOwnerSequenceNumber(Integer ownerSequenceNumber) {
        this.ownerSequenceNumber = ownerSequenceNumber;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public void setSequenceOwner(SequenceOwnerImpl newOwnerReference) {
        setOwner(newOwnerReference);
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
