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
import java.util.Collection;

import org.kuali.kra.SequenceAssociate;

public class SelfReferenceAssociate implements SequenceAssociate<SelfReferenceOwner> {

    public Long id = 1L;
    public Integer seqNumber = 1;
    
    public SelfReferenceOwner owner;
    
    public final Collection<SelfReferenceAssociate> selfs = new ArrayList<SelfReferenceAssociate>();
    
    /**
     * Gets the selfs attribute. 
     * @return Returns the selfs.
     */
    public Collection<SelfReferenceAssociate> getSelfs() {
        return selfs;
    }

    /**
     * Gets the id attribute. 
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id attribute value.
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the seqNumber attribute. 
     * @return Returns the seqNumber.
     */
    public Integer getSeqNumber() {
        return seqNumber;
    }

    /**
     * Sets the seqNumber attribute value.
     * @param seqNumber The seqNumber to set.
     */
    public void setSeqNumber(Integer seqNumber) {
        this.seqNumber = seqNumber;
    }

    /**
     * Gets the owner attribute. 
     * @return Returns the owner.
     */
    public SelfReferenceOwner getOwner() {
        return owner;
    }

    /**
     * Sets the owner attribute value.
     * @param owner The owner to set.
     */
    public void setOwner(SelfReferenceOwner owner) {
        this.owner = owner;
    }

    public SelfReferenceOwner getSequenceOwner() {
        // TODO Auto-generated method stub
        return owner;
    }

    public void setSequenceOwner(SelfReferenceOwner newlyVersionedOwner) {
        owner = newlyVersionedOwner;
        
    }

    public Integer getSequenceNumber() {
        return seqNumber;
    }

    public void resetPersistenceState() {
        id = null;
    }

}
