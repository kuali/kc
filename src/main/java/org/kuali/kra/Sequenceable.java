/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra;

import java.io.Serializable;

/**
 * This interface defines a class with sequence information
 */
public interface Sequenceable extends Serializable {
    /**
     * This gets the current version number
     * @return
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
