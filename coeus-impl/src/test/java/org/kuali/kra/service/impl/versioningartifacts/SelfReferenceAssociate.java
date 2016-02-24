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

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;

import java.util.ArrayList;
import java.util.Collection;

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
