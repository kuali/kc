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
package org.kuali.coeus.common.framework.version.sequence;

import java.io.Serializable;

/**
 * This interface defines a class with sequence information.
 */
public interface Sequenceable extends Serializable {
    /**
     * This gets the current version number.
     * @return the sequence number
     */
    Integer getSequenceNumber();
    
    /**
     * This method resets the persistence state used to establish 
     * whether this is a new entity or an already-persisted entity.
     * 
     * During sequencing, the VersioningService needs to be able to
     * signal that a Sequenceable has changed state from an persisted
     * entity to a new, unpersisted one. Otherwise, versioning 
     * operations would result earlier SequenceOwners losing the 
     * association with older versions of associates.
     * 
     * This behavior comes into play whenever an associated object
     * is versioned. 
     * 
     * Typically, implementers will set primary key fields to null 
     * to trigger an INSERT during save.
     */
    void resetPersistenceState();
}
