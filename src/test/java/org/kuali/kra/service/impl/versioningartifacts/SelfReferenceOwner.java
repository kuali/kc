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

import org.kuali.kra.SequenceOwner;

public class SelfReferenceOwner implements SequenceOwner<SelfReferenceOwner>{
    
    public Long id = 1L;
    public Integer seq = 0;
    public final Collection<SelfReferenceAssociate> associates = new ArrayList<SelfReferenceAssociate>();
    
    /**
     * Gets the associates attribute. 
     * @return Returns the associates.
     */
    public Collection<SelfReferenceAssociate> getAssociates() {
        return associates;
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
     * Gets the seq attribute. 
     * @return Returns the seq.
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * Sets the seq attribute value.
     * @param seq The seq to set.
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getOwnerSequenceNumber() {
        return seq;
    }

    public void incrementSequenceNumber() {
        seq++;
    }

    public SelfReferenceOwner getSequenceOwner() {
        return this;
    }

    public void setSequenceOwner(SelfReferenceOwner newlyVersionedOwner) {
        //do nothing
    }

    public Integer getSequenceNumber() {
        return seq;
    }

    public void resetPersistenceState() {
        id = null;
    }
    
}
