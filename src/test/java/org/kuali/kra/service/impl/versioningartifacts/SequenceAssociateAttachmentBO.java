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

import org.kuali.kra.SeparatelySequenceableAssociate;

/**
 * This class represents an attachment type that needs to be managed in a 
 * many-to-many way to prevent unnecessary copying of attachments that can 
 * be very large
 */
public class SequenceAssociateAttachmentBO implements SeparatelySequenceableAssociate<SequenceOwnerImpl> {
    private static final long serialVersionUID = -1764304273143080320L;
    
    private Long attachmentId;
    private String name;
    //private List<SequenceOwnerImpl> owners;
    private Integer sequenceNumber;

    public SequenceAssociateAttachmentBO(String name) {
        this.name = name;
        setAttachmentId(new Random().nextLong());
        //owners = new ArrayList<SequenceOwnerImpl>();
    }
    
//    public void add(SequenceOwnerImpl newOwner) {
//        owners.add(newOwner);
//    }
    
    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

//    /**
//     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
//     */
//    public List<SequenceOwnerImpl> getSequenceOwners() {
//        return owners;
//    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        setAttachmentId(null);
    }
    
//    /**
//     * @see org.kuali.kra.SeparatelySequenceableAssociate#setSequenceOwners(java.util.List)
//     */
//    @SuppressWarnings("unchecked")
//    public void setSequenceOwners(List<SequenceOwnerImpl> owners) {
//        setOwners(owners);
//    }
    
//    /**
//     * @see org.kuali.kra.SeparatelySequenceableAssociate#getLatestOwner()
//     */
//    public SequenceOwnerImpl getLatestOwner() {
//        return owners.size() > 0 ? owners.get(owners.size() - 1) : null;
//    }
    
    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }

//    public List<SequenceOwnerImpl> getOwners() {
//        return owners;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setOwners(List<SequenceOwnerImpl> owners) {
//        this.owners = owners;
//    } 
}