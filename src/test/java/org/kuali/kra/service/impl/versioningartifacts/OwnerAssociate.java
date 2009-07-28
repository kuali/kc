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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.kuali.kra.SequenceOwner;

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

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwnerImpl newOwnerReference) {
        setOwner(newOwnerReference);
    }

    /**
     * @see org.kuali.kra.SequenceOwner#getVersionNameField()
     */
    public String getVersionNameField() {
        return "name";
    }
}
