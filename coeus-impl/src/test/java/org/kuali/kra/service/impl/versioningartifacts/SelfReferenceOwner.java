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
import java.util.Collection;

public class SelfReferenceOwner implements SequenceOwner<SelfReferenceOwner>{
    private static final long serialVersionUID = 5794406936890268956L;
    
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
    
    public String getVersionNameField() {
        return "id";
    }

    @Override
    public String getVersionNameFieldValue() {
        return id.toString();
    }
}
